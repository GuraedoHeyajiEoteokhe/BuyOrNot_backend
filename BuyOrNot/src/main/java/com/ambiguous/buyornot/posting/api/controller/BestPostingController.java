package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.domain.BestPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/best-posting")
@RequiredArgsConstructor
public class BestPostingController {

    private final BestPostingService bestPostingService;

    // 전체 베스트 목록
    @GetMapping("/best-postings")
    public ApiResult<?> getBestPostings(
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        return ApiResult.success(bestPostingService.getBestPosts(pageable));
    }


    // 종목별 베스트 목록

    @GetMapping("/stocks/{stockId}/best-postings")
    public ApiResult<?> getBestPostingsByStockId(
            @PathVariable Long stockId,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        return ApiResult.success(bestPostingService.getBestPostsByStockId(stockId, pageable));
    }
}
