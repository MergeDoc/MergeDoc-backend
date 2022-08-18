package com.mergedoc.backend.utils.response;

public class ApiResult {

    public static <T> ApiSuccessResult<T> success(T response) {
        return new ApiSuccessResult<>(response);
    }

    public static <T> ApiErrorResult<T> error(int code, T message) {
        return new ApiErrorResult<>(code, message);
    }

    public static class ApiSuccessResult<T> {
        private final T response;

        private ApiSuccessResult(T response) { this.response = response; }

        public T getResponse(T response) { return response; }
    }

    public static class ApiErrorResult<T> {
        private final int statusCode;
        private final T response;

        private ApiErrorResult(int statusCode, T response) {
            this.statusCode = statusCode;
            this.response = response;
        }

        public int getStatus() {
            return this.statusCode;
        }
        public T getResponse() {
            return this.response;
        }
    }
}
