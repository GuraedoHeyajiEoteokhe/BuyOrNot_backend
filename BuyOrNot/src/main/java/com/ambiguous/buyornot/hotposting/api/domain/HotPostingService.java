package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.hotposting.api.controller.redis.HotPostingRedisStore;
import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingPassiveRequest;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HotPostingService {

    private HotPostingRepository hotPostingRepository;
    private StockRepository stockRepository;
    private HotPostingRedisStore hotPostingRedisStore;
    private PostRepository postRepository;

    @Transactional
    public void register(HotPostingPassiveRequest request){
        // 존재하는 게시글인지 검증
        Post post = postRepository.findById(request.postingId())
                .orElseThrow(() -> new IllegalArgumentException("Posting with id " + request.postingId() + " does not exist"));

        Long postId = post.getId();
        // 핫게시글에 이미 등록된 게시글인지 검증
        if(hotPostingRepository.existsByPostingId(postId)){
            throw new IllegalStateException("이미 핫게시글로 등록된 게시글입니다.");
        }
        LocalDateTime now = LocalDateTime.now();
        Long stockId = post.getStockId();

        // 존재하는 종목인지 검증
        if(stockRepository.existsById(stockId)){
            throw new IllegalStateException("존재하지 않는 종목입니다.");
        }

        HotPosting hotPosting = new HotPosting(
                postId,
                post.getUserId(),
                stockId,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)
        );

        LocalDateTime registeredAt = hotPosting.getCreatedAt();

        hotPostingRepository.save(hotPosting);
        hotPostingRedisStore.add(postId,stockId,registeredAt);
        hotPosting.markRedisSynced(registeredAt);

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
        if(stockRepository.existsById(stockId)){
            throw new IllegalStateException("존재하지 않는 종목입니다.");
        }

        HotPosting hotPosting = new HotPosting(
                postId,
                post.getUserId(),
                stockId,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)

        );

        LocalDateTime registeredAt = hotPosting.getCreatedAt();

        hotPostingRepository.save(hotPosting);

        // DB 저장 성공 후 Redis 반영
        hotPostingRedisStore.add(postId,stockId,registeredAt);

        // ✅ Redis 반영 성공 기록
        hotPosting.markRedisSynced(registeredAt);
    }
}
