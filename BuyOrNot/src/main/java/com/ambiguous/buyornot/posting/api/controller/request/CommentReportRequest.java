package com.ambiguous.buyornot.posting.api.controller.request;

import com.ambiguous.buyornot.posting.api.domain.ReportType;

public record CommentReportRequest(
        Long userId,
        ReportType type,
        String reason
) {}
