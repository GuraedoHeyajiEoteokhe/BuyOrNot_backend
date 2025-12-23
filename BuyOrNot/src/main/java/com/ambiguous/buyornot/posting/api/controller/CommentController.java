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
            @RequestParam Long userId,
            @RequestBody CreateCommentRequest request
    ) {
        String userNickname = "nickName";
        commentService.createComment(postId, userId, userNickname, request);
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
            @RequestParam Long userId,
            @RequestBody UpdateCommentRequest request
    ) {
        commentService.updateComment(commentId, userId, request);
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
}
