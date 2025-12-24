package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.hotposting.api.controller.redis.HotPostingRedisStore;
import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingCreateRequest;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotPostingService {

    private HotPostingRepository hotPostingRepository;
    private StockRepository stockRepository;
    private HotPostingRedisStore hotPostingRedisStore;
    private PostRepository postRepository;

    @Transactional
    public void register(HotPostingCreateRequest request){
        // 존재하는 게시글인지 검증
        if(!postRepository.existsById(request.postingId())){
            throw new IllegalArgumentException("Posting with id " + request.postingId() + " does not exist");
        }
        // 핫게시글에 이미 등록된 게시글인지 검증
        if(hotPostingRepository.existsByPostingId(request.postingId())){
            throw new IllegalStateException("이미 핫게시글로 등록된 게시글입니다.");
        }
        LocalDateTime now = LocalDateTime.now();

        HotPosting hotPosting = new HotPosting(
                request.postingId(),
                request.writerId(),
                request.symbol(),
                request.writeAt(),
                now,
                request.writeAt().plusHours(72)
        );

        hotPostingRepository.save(hotPosting);

    }

    // 자동 등록 서비스
    @Transactional
    public void registerFromPost(Post post){
        Long postId = post.getId();
        if(hotPostingRepository.existsByPostingId(postId)) return;

        LocalDateTime now = LocalDateTime.now();
        Long stockId = post.getStockId();
        // stockId가 잘못되었을 경우
        String symbol = Optional.ofNullable(stockRepository.findSymbolById(stockId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 종목: " + stockId));

        HotPosting hotPosting = new HotPosting(
                postId,
                post.getUserId(),
                symbol,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)

        );
        hotPostingRepository.save(hotPosting);

        // DB 저장 성공 후 Redis 반영
        hotPostingRedisStore.add(postId,stockId,hotPosting.getRegisteredAt());
    }
}
