package com.ambiguous.buyornot.common.support.key;

/*
 *  Keys 규칙
 *  Redis에서는 콜론(:)으로 계층을 구분하는 것이 표준 관례 (서비스:도메인:식별자:용도)
    ex)
    ambiguous:{chatting}:chat
    ambiguous:{hotPosting}:like
* */

public class AmbiguousKey {
    private static final String PREFIX = "ambiguous";
}
