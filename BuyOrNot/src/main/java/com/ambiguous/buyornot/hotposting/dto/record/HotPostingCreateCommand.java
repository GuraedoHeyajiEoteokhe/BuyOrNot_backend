package com.ambiguous.buyornot.hotposting.dto.record;

import java.time.LocalDateTime;

public record HotPostingCreateCommand(
        Long postingId,
        Long writerId,
        String symbol,
        LocalDateTime writeAt
) {
    public HotPostingCreateCommand {
        if (postingId == null) throw new IllegalArgumentException("postingId는 필수");
        if (writerId == null) throw new IllegalArgumentException("writerId는 필수");
        if (symbol == null || symbol.isBlank()) throw new IllegalArgumentException("symbol은 필수");
        if (writeAt == null) throw new IllegalArgumentException("writeAt은 필수");
    }
}
