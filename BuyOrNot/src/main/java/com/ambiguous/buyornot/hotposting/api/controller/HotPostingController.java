package com.ambiguous.buyornot.hotposting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.hotposting.api.controller.request.HotPostingCreateRequest;

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
    public ApiResult<?> create(@RequestBody HotPostingCreateRequest request){

        // 존재하는 게시글인지 검증
        if(!postRepository.existsById(request.postingId())){
            throw new IllegalArgumentException("Posting with id " + request.postingId() + " does not exist");
        }
        // 핫게시글에 이미 등록된 게시글인지 검증
        if(hotPostingRepository.existsByPostingId(request.postingId())){
            throw new IllegalStateException("이미 핫게시글로 등록된 게시글입니다.");
        }

        hotPostingService.register(request);
        return ApiResult.success();
    }


}