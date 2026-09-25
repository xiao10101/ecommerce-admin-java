package com.george.java.common.result;

import lombok.Getter;

@Getter
public enum ErrorCode {
    OK(0, "成功"),
    SYSTEM_ERROR(10000, "系统内部错误"),
    PARAM_ERROR(10001, "参数错误"),
    UNAUTHORIZED(40100, "未登录或凭证失效"),
    FORBIDDEN(40300, "无权限"),
    NOT_FOUND(40400, "资源不存在"),
    DUPLICATE_REQUEST(40900, "重复请求");

    private final int code;
    private final String message;


    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
