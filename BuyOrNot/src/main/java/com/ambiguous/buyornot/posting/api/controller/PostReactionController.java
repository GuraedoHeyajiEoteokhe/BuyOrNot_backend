package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.PostReactionRequest;
import com.ambiguous.buyornot.posting.api.domain.PostReactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostReactionController {

    private final PostReactionService postReactionService;

    @PostMapping("/posts/{postId}/reactions")
    @Operation(summary = "게시글 반응 토글 API입니다.")
    public ApiResult<?> react(
            @PathVariable Long postId,
            @RequestBody PostReactionRequest request
    ) {
        postReactionService.react(postId, request.userId(), request.type());
        return ApiResult.success();
    }
}
