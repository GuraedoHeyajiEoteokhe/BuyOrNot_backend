package com.ambiguous.buyornot.hotposting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingCreateRequest;

import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingPassiveRequest;
import com.ambiguous.buyornot.hotposting.api.domain.HotPostingService;
import com.ambiguous.buyornot.hotposting.api.storage.HotPostingRepository;
import com.ambiguous.buyornot.posting.storage.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotposting")
public class HotPostingController {

    private final HotPostingService hotPostingService;
    private final HotPostingRepository hotPostingRepository;
    private final PostRepository postRepository;

    // 관리자만 접근 가능 추가
    @PostMapping
    public ApiResult<?> create(@RequestBody HotPostingPassiveRequest request){

        hotPostingService.register(request);
        return ApiResult.success();
    }

    @DeleteMapping
    public ApiResult<?> delete(@RequestBody HotPostingPassiveRequest request){
        hotPostingService.removeByAdmin(request);
        return ApiResult.success();
    }

    // 핫포스팅 목록 조회
    @GetMapping("/lists/{stockId}")
    public ApiResult<?> getHotPostings(@PathVariable Long stockId) {
        return ApiResult.success(hotPostingService.getHotPostingTop30(stockId));
    }

}