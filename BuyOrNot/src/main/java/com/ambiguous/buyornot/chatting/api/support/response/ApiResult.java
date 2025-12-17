package com.ambiguous.buyornot.chatting.api.support.response;

import com.ambiguous.buyornot.chatting.api.support.error.ErrorMessage;
import com.ambiguous.buyornot.chatting.api.support.error.ErrorType;
import lombok.Getter;

@Getter
public class ApiResult<S> {
    private final ResultType result;
    private final S data;
    private final ErrorMessage error;

    private ApiResult(ResultType result, S data, ErrorMessage error) {
        this.result = result;
        this.data = data;
        this.error = error;
    }

    public static ApiResult<?> success() {
        return new ApiResult<>(ResultType.SUCCESS, null, null);
    }

    public static <S> ApiResult<S> success(S data) {
        return new ApiResult<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResult<?> error(ErrorType error) {
        return new ApiResult<>(ResultType.ERROR, null, new ErrorMessage(error));
    }

    public static ApiResult<?> error(ErrorType error, Object errorData) {
        return new ApiResult<>(ResultType.ERROR, null, new ErrorMessage(error, errorData));
    }
}