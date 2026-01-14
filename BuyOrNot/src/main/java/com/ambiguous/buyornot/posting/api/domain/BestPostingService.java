package com.ambiguous.buyornot.posting.api.domain;

import com.ambiguous.buyornot.posting.api.controller.response.BestPostSummaryResponse;
import com.ambiguous.buyornot.posting.storage.BestPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BestPostingService {

    private final BestPostingRepository bestPostingRepository;

    public Page<BestPostSummaryResponse> getBestPosts(Pageable pageable) {
        return bestPostingRepository.findBestPosts(pageable);
    }

    public Page<BestPostSummaryResponse> getBestPostsByStockId(Long stockId, Pageable pageable) {
        return bestPostingRepository.findBestPostsByStockId(stockId, pageable);
    }
}
