package com.ambiguous.buyornot.posting.api.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchRequest {
    private Long userId;
    private String title;
}

