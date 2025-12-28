package com.ambiguous.buyornot.posting.api.controller;

import com.ambiguous.buyornot.common.support.response.ApiResult;
import com.ambiguous.buyornot.posting.api.controller.request.PostReportRequest;
import com.ambiguous.buyornot.posting.api.domain.PostReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostReportController {

    private final PostReportService postReportService;

    @PostMapping("/posts/{postId}/reports")
    @Operation(summary = "게시글 신고 API입니다.")
    public ApiResult<?> report(
            @PathVariable Long postId,
            @RequestBody PostReportRequest request
    ) {
        postReportService.report(postId, request.userId(), request.type(), request.reason());
        return ApiResult.success();
    }
}
