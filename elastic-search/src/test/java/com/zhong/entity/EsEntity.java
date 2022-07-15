package com.zhong.entity;

import com.zhong.es.model.EsBaseDTO;
import lombok.Data;

/**
 * @date 2022/7/15 16:45
 */
@Data
public class EsEntity extends EsBaseDTO {

    private String features;

    private String brandId;

    private Integer age;

}
