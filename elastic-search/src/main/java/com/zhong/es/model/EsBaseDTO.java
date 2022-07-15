package com.zhong.es.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/7/14 20:17
 */
@Data
public class EsBaseDTO implements Serializable {

    /**
     * ID
     */
    private String id;

    /**
     * 时间戳，精确到毫秒
     */
    private Long ts;

}
