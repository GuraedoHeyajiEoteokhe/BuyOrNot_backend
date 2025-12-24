package com.ambiguous.buyornot.hotposting.api.controller.request;

import java.time.LocalDateTime;

public record HotPostingCreateRequest(
        Long postingId,
        Long writerId,
        String symbol,
        LocalDateTime writeAt
) { }

