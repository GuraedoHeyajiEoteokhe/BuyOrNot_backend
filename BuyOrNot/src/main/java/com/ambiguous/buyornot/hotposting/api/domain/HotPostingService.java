package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.common.support.error.CoreException;
import com.ambiguous.buyornot.common.support.error.ErrorType;
import com.ambiguous.buyornot.hotposting.api.controller.redis.HotPostingRedisStore;
import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingPassiveRequest;
import com.ambiguous.buyornot.hotposting.api.controller.response.HotPostingSummaryResponse;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotPostingService {

    private final HotPostingRepository hotPostingRepository;
    private final StockRepository stockRepository;
    private final HotPostingRedisStore hotPostingRedisStore;
    private final PostRepository postRepository;

    // 관리자용 등록
    @Transactional
    public void register(HotPostingPassiveRequest request){
        // 존재하는 게시글인지 검증
        Post post = postRepository.findById(request.postingId())
                .orElseThrow(() -> new CoreException(ErrorType.HOTPOSTING_POST_NOT_FOUND));

        Long postId = post.getId();
        // 핫게시글에 이미 등록된 게시글인지 검증
        if(hotPostingRepository.existsByPostingId(postId)){
            throw new CoreException(ErrorType.HOTPOSTING_ALREADY_EXISTS);
        }
        LocalDateTime now = LocalDateTime.now();
        Long stockId = post.getStockId();

        // 존재하는 종목인지 검증
        if(!stockRepository.existsById(stockId)){
            throw new CoreException(ErrorType.HOTPOSTING_STOCK_NOT_FOUND);
        }

        HotPosting hotPosting = new HotPosting(
                postId,
                stockId,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)
        );

        LocalDateTime registeredAt = now;

        hotPostingRepository.save(hotPosting);
        // Redis 반영 (실패하면 에러)
        try {
            hotPostingRedisStore.add(postId, stockId, registeredAt);
            hotPosting.markRedisSynced(registeredAt);
            hotPostingRepository.save(hotPosting);
        } catch (Exception e) {
            throw new CoreException(ErrorType.HOTPOSTING_REDIS_SYNC_FAILED);
        }

    }

    // 관리자가 삭제 (hard delete)
    @Transactional
    public void removeByAdmin(HotPostingPassiveRequest request){
        Long postId = request.postingId();

        // 존재하는 게시글인지 검증
        postRepository.findById(postId)
                .orElseThrow(() -> new CoreException(ErrorType.HOTPOSTING_POST_NOT_FOUND));;

        // 핫포스팅에 등록된 게시글인지 검증
        HotPosting hp = hotPostingRepository.findByPostingId(postId)
                .orElseThrow(() -> new CoreException(ErrorType.HOTPOSTING_NOT_FOUND));;

        hotPostingRedisStore.remove(hp.getPostingId(), hp.getStockId());
        hotPostingRepository.delete(hp);
    }

    // 자동 등록 서비스
    @Transactional
    public void registerFromPost(Post post){
        Long postId = post.getId();
        if(hotPostingRepository.existsByPostingId(postId)) return;

        LocalDateTime now = LocalDateTime.now();
        Long stockId = post.getStockId();
        // stockId가 잘못되었을 경우
        // 존재하는 종목인지 검증
        if(!stockRepository.existsById(stockId)){
            throw new CoreException(ErrorType.HOTPOSTING_STOCK_NOT_FOUND);
        }

        HotPosting hotPosting = new HotPosting(
                postId,
                stockId,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)

        );

        LocalDateTime registeredAt = now;

        hotPostingRepository.save(hotPosting);


        try {
            hotPostingRedisStore.add(postId, stockId, registeredAt);
            hotPosting.markRedisSynced(registeredAt);
            hotPostingRepository.save(hotPosting);
        } catch (Exception e) {
            // 자동 등록은 실패해도 다음 작업 계속
            // redisSynced는 기본 false니까 그대로 둠
            log.warn("Redis sync failed for postId={}", postId, e);
        }
    }

    // posting delete 서비스에서 호출이 필요함 (hard delete)
    // 사용자가 posting을 삭제했을 경우 hotposting에 등록된 게시글이면 redis와 hotposting DB에서 삭제한다.
    // 포스팅을 지우기전 호출이 필요하다.
    @Transactional
    public void removeByPostDeleted(Long postId){
        HotPosting hp = hotPostingRepository.findByPostingId(postId).orElse(null);
        if (hp == null) return;

        hotPostingRedisStore.remove(hp.getPostingId(), hp.getStockId());
        hotPostingRepository.delete(hp);
    }

    // 스케줄러

    @Transactional
    public void autoRegisterHotPostings() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusHours(1);

        List<Post> candidates = postRepository.findHotCandidates(oneHourAgo, 10L);

        for (Post p : candidates) {
            registerFromPost(p);
        }
    }

    @Transactional
    public void cleanupExpiredFromRedis() {
        LocalDateTime now = LocalDateTime.now();

        List<HotPosting> expired =
                hotPostingRepository.findByExpireAtBeforeAndRedisSyncedTrue(now);

        for (HotPosting hotPosting : expired) {
            hotPostingRedisStore.remove(hotPosting.getPostingId(), hotPosting.getStockId());
            hotPosting.markRedisUnsynced();
        }
    }

    @Transactional(readOnly = true)
    public List<HotPostingSummaryResponse> getHotPostingTop30(Long stockId) {

        // 1) Redis에서 Top30 postingId 가져오기
        List<Long> ids = hotPostingRedisStore.getTop30Ids(stockId);
        if (ids.isEmpty()) return List.of();

        // 2) DB에서 Post들 한 번에 조회
        List<Post> posts = postRepository.findByIdIn(ids);

        // 3) Redis 순서대로 정렬하기 위한 인덱스 맵
        Map<Long, Integer> order = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            order.put(ids.get(i), i);
        }

        // 4) 정렬 + DTO 매핑
        return posts.stream()
                .sorted(Comparator.comparingInt(p -> order.getOrDefault(p.getId(), Integer.MAX_VALUE)))
                .map(p -> new HotPostingSummaryResponse(
                        p.getId(),
                        p.getStockId(),
                        p.getUserId(),
                        p.getUserNickname(),
                        p.getTitle(),
                        p.getViewCount(),
                        p.getLikeCount(),
                        p.getDislikeCount(),
                        p.getCreatedAt()
                ))
                .toList();
    }


}
