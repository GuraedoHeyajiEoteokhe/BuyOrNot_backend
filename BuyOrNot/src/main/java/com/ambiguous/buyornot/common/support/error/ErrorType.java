package com.ambiguous.buyornot.common.support.error;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

public enum ErrorType {

    // 기본 에러 발생
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),

    // 조회 실패 에러
    DEFAULT_ERROR_FIND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E501, "조회를 실패하였습니다.", LogLevel.ERROR),

    // 유효하지 않은 인자값 경고
    DEFAULT_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, ErrorCode.E400, "An unexpected error has occurred.", LogLevel.WARN),
    DEFAULT_ARGUMENT_NOT_VALID_ISNULL(HttpStatus.BAD_REQUEST, ErrorCode.E401, "유효하지 않은 값입니다.", LogLevel.ERROR),

    // 핫포스팅
    // HotPosting - 4xx
    HOTPOSTING_POST_NOT_FOUND(
            HttpStatus.NOT_FOUND, ErrorCode.H404,
            "존재하지 않는 게시글입니다.", LogLevel.WARN),

    HOTPOSTING_STOCK_NOT_FOUND(
            HttpStatus.NOT_FOUND, ErrorCode.H404,
            "존재하지 않는 종목입니다.", LogLevel.WARN),

    HOTPOSTING_NOT_FOUND(
            HttpStatus.NOT_FOUND, ErrorCode.H404,
            "핫게시글로 등록되지 않은 게시글입니다.", LogLevel.WARN),

    HOTPOSTING_ALREADY_EXISTS(
            HttpStatus.CONFLICT, ErrorCode.H409,
            "이미 핫게시글로 등록된 게시글입니다.", LogLevel.WARN),

    HOTPOSTING_FORBIDDEN(
            HttpStatus.FORBIDDEN, ErrorCode.H403,
            "관리자만 접근할 수 있습니다.", LogLevel.WARN),

    HOTPOSTING_INVALID_REQUEST(
            HttpStatus.BAD_REQUEST, ErrorCode.H400,
            "요청 값이 올바르지 않습니다.", LogLevel.WARN),

    // HotPosting - 5xx
    HOTPOSTING_REDIS_SYNC_FAILED(
            HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.H500,
            "Redis 반영에 실패했습니다.", LogLevel.ERROR)

    ;

    private final HttpStatus status;

    private final ErrorCode code;

    private final String message;

    private final LogLevel logLevel;

    ErrorType(HttpStatus status, ErrorCode code, String message, LogLevel logLevel) {

        this.status = status;
        this.code = code;
        this.message = message;
        this.logLevel = logLevel;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

}