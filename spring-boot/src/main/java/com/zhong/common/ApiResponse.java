package com.zhong.common;



import com.zhong.enums.ApiResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ashui
 * @desc api返回接口数据包装类
 * @date 2022/4/11
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
        return new ApiResponse<T>(code, message, null);
    }

    public static <T> ApiResponse<T> of(int code, T data) {
        return new ApiResponse<T>(code, "", data);
    }

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<T>(code, message, data);
    }

    public static <T> ApiResponse<T> of(ApiResultCode resultCode, String message, T data) {
        return new ApiResponse<T>(resultCode.code, message, data);
    }

    /**
     * 返回成功
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<T>(ApiResultCode.SUCCESS.code, ApiResultCode.SUCCESS.message, null);
    }

    /**
     * 返回成功 ，传入数据
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(ApiResultCode.SUCCESS.code, ApiResultCode.SUCCESS.message, data);
    }

    /**
     * 返回成功 ，传入信息
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<T>(ApiResultCode.SUCCESS.code, message, null);
    }

    /**
     * 返回成功 ，传入数据和信息
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<T>(ApiResultCode.SUCCESS.code, message, data);
    }

    /**
     * 返回失败
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<T>(ApiResultCode.INTERNAL_SERVER_ERROR.code, ApiResultCode.SUCCESS.message, null);
    }

    /**
     * 返回失败
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<T>(ApiResultCode.INTERNAL_SERVER_ERROR.code, message, null);
    }

    /**
     * 返回失败
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<T>(ApiResultCode.INTERNAL_SERVER_ERROR.code, ApiResultCode.INTERNAL_SERVER_ERROR.message, data);
    }

    /**
     * 返回失败
     *
     * @author ashui
     * @date 2022/4/11
     */
    public static <T> ApiResponse<T> fail(String message, T data) {
        return new ApiResponse<T>(ApiResultCode.INTERNAL_SERVER_ERROR.code, message, data);
    }

    /**
     * @desc 判断数据返回是否正常
     * @author ashui
     * @date 2018/11/22
     */
    public boolean hasOk() {
        return ApiResultCode.SUCCESS.code == this.code;
    }


}
