package com.zhong.enums;

import java.util.stream.Stream;

/**
 * @author ashui
 * @desc 返回状态码
 * @date 2018/7/3
 */
public enum ApiResultCode {

    // 成功
    SUCCESS(200, "Succ"),

    // 接口不存在
    NOT_FOUND(404, "Not Found"),

    // 提示用户报错
    INTERNAL_SERVER_ERROR(500, "Error"),

    EXIST(501, "确定要删除吗");

    public final int code;
    public final String message;

    ApiResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResultCode getResultCodeByCode(int code) {
        return Stream.of(values())
                .filter(x -> x.code == code)
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }

}
