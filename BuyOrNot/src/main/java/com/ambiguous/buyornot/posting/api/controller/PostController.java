package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.CreatePostDto;
import com.ambiguous.buyornot.posting.api.domain.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/stocks/{stockId}/posts")
    @Operation(summary = "게시물 생성 API입니다.")
    public ApiResult<?> createPost(
            @PathVariable Long stockId,
            @RequestBody CreatePostDto dto
    ) {
        Long userId = 1L;
        String userNickname = "nickName";
        postService.createPost(stockId, userId, userNickname, dto);

        return ApiResult.success();
    }
}
