package com.zhong.common;


import lombok.Data;

import java.io.Serializable;

/**
 * @desc api返回接口数据包装类
 */
@Data
public class ApiResponse<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    private ApiResponse() {

    }

    private ApiResponse(T data) {
        this.data = data;
    }

    private ApiResponse(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> of(int code, T data) {
        return new ApiResponse<>(code, "", data);
    }

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> of(ApiResultCode resultCode, String message, T data) {
        return new ApiResponse<>(resultCode.code, message, data);
    }

    /**
     * 返回成功
     */
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(ApiResultCode.SUCCESS.code, ApiResultCode.SUCCESS.message, null);
    }

    /**
     * 返回成功 ，传入数据
     */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(ApiResultCode.SUCCESS.code, ApiResultCode.SUCCESS.message, data);
    }

    /**
     * 返回成功 ，传入信息
     */
    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(ApiResultCode.SUCCESS.code, message, null);
    }

    /**
     * 返回成功 ，传入数据和信息
     */
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(ApiResultCode.SUCCESS.code, message, data);
    }

    /**
     * 返回失败
     */
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<>(ApiResultCode.INTERNAL_SERVER_ERROR.code, ApiResultCode.SUCCESS.message, null);
    }

    /**
     * 返回失败
     */
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(ApiResultCode.INTERNAL_SERVER_ERROR.code, message, null);
    }

    /**
     * 返回失败
     */
    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(ApiResultCode.INTERNAL_SERVER_ERROR.code, ApiResultCode.INTERNAL_SERVER_ERROR.message, data);
    }

    /**
     * 返回失败
     */
    public static <T> ApiResponse<T> fail(String message, T data) {
        return new ApiResponse<>(ApiResultCode.INTERNAL_SERVER_ERROR.code, message, data);
    }

    /**
     * @desc 判断数据返回是否正常
     */
    public boolean hasOk() {
        return ApiResultCode.SUCCESS.code == this.code;
    }


}
