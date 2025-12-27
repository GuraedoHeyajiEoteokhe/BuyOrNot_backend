package com.ambiguous.buyornot.posting.api.controller.request;

import com.ambiguous.buyornot.posting.api.domain.ReactionType;

public record PostReactionRequest(
        Long userId,
        ReactionType type
) {
}
