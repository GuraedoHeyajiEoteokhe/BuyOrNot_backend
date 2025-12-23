package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.CreateCommentRequest;
import com.ambiguous.buyornot.posting.api.domain.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    @Operation(summary = "댓글/대댓글 작성 API입니다.")
    public ApiResult<?> createComment(
            @PathVariable Long postId,
            @RequestParam Long userId,
            @RequestBody CreateCommentRequest request
    ) {
        String userNickname = "nickName";
        commentService.createComment(postId, userId, userNickname, request);
        return ApiResult.success();
    }
}
