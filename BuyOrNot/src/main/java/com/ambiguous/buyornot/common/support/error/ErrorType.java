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
    DEFAULT_ARGUMENT_NOT_VALID_ISNULL(HttpStatus.BAD_REQUEST, ErrorCode.E401, "유효하지 않은 값입니다.", LogLevel.ERROR)


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