package com.ambiguous.buyornot.hotposting.api.domain;

import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingCreateRequest;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.api.domain.Post;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import com.ambiguous.buyornot.stock.storage.StockRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HotPostingService {

    private HotPostingRepository hotPostingRepository;
    private StockRepository stockRepository;

    @Transactional
    public void register(HotPostingCreateRequest request){

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
        if(hotPostingRepository.existsByPostingId(post.getId())) return;

        LocalDateTime now = LocalDateTime.now();
        String symbol = stockRepository.findSymbolById(post.getStockId());
        HotPosting hotPosting = new HotPosting(
                post.getId(),
                post.getUserId(),
                symbol,
                post.getCreatedAt(),
                now,
                post.getCreatedAt().plusHours(72)

        );
        hotPostingRepository.save(hotPosting);
    }
}
