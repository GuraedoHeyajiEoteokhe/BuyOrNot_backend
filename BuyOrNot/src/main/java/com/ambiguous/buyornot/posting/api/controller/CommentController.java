package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.CreateCommentRequest;
import com.ambiguous.buyornot.posting.api.controller.request.UpdateCommentRequest;
import com.ambiguous.buyornot.posting.api.controller.response.CommentResponse;
import com.ambiguous.buyornot.posting.api.domain.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}")
    @Operation(summary = "댓글/대댓글 작성 API입니다.")
    public ApiResult<?> createComment(
            @PathVariable Long postId,
            @RequestBody CreateCommentRequest request
    ) {
        commentService.createComment(postId, request);
        return ApiResult.success();
    }

    @GetMapping("/posts/{postId}")
    @Operation(summary = "게시글 댓글 목록 조회 API입니다.")
    public ApiResult<List<CommentResponse>> getComments(
            @PathVariable Long postId
    ) {
        return ApiResult.success(commentService.getCommentsByPostId(postId));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정 API입니다.")
    public ApiResult<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request
    ) {
        commentService.updateComment(commentId, request);
        return ApiResult.success();
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제 API입니다.")
    public ApiResult<?> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.deleteComment(commentId, userId);
        return ApiResult.success();
    }

    @PutMapping("/{commentId}/pin")
    @Operation(summary = "댓글 고정 API입니다.")
    public ApiResult<?> pinComment(
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.pinComment(commentId, userId);
        return ApiResult.success();
    }

    @DeleteMapping("/{commentId}/pin")
    @Operation(summary = "댓글 고정 해제 API입니다.")
    public ApiResult<?> unpinComment(
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        commentService.unpinComment(commentId, userId);
        return ApiResult.success();
    }
}
