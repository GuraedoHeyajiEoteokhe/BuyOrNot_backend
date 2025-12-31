package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.CommentReportRequest;
import com.ambiguous.buyornot.posting.api.domain.CommentReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentReportController {

    private final CommentReportService commentReportService;

    @PostMapping("/posts/{postId}/comments/{commentId}/reports")
    @Operation(summary = "댓글 신고 API입니다.")
    public ApiResult<?> report(
            @PathVariable Long commentId,
            @RequestBody CommentReportRequest request
    ) {
        commentReportService.report(commentId, request.userId(), request.type(), request.reason());
        return ApiResult.success();
    }
}
