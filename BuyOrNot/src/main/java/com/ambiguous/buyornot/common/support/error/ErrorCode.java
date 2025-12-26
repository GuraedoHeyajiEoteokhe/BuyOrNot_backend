package com.ambiguous.buyornot.common.support.error;

public enum ErrorCode {

    // 기본 에러 코드
    E500,   // 에러
    E501,   // 조회에러

    // 유효하지 않은 인자값
    E400,   // 경고
    E401,   // 에러

    // HotPosting - 4xx
    H400,
    H401,
    H403,
    H404,
    H409,

    // HotPosting - 5xx
    H500
}
