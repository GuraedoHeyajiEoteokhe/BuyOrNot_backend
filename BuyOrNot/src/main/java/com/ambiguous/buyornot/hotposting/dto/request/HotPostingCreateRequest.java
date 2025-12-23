package com.ambiguous.buyornot.hotposting.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HotPostingCreateRequest {

    private Long postingId;
    private Long writerId;
    private String symbol;
    private LocalDateTime writeAt;

}
