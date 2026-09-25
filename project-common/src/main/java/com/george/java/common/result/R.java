package com.george.java.common.result;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class R<T> implements Serializable {

    private final int code;
    private final String message;
    private final T data;
    private final long timestamp;

    private R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> R<T> fail(ErrorCode ec) {
        return new R<>(ec.getCode(), ec.getMessage(), null);
    }

    public static <T> R<T> fail(ErrorCode ec, String customMessage) {
        return new R<>(ec.getCode(), customMessage, null);
    }

    public static <T>R<T> ok(T pong) {
        return new R<>(200, "成功",  pong);
    }
}
