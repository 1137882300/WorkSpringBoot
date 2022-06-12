package com.zhong.springboot.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiao-pang
 * @since 2022-06-12
 */
@TableName("basic")
public class Basic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDate updateTime;

    @TableField("version")
    @Version
    private Long version;


    public Integer getId() {
        return id;
    }

    public Basic setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Basic setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Basic setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Basic setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public Basic setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Basic setVersion(Long version) {
        this.version = version;
        return this;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    @Override
    public String toString() {
        return "Basic{" +
        "id=" + id +
        ", name=" + name +
        ", age=" + age +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", version=" + version +
        "}";
    }
}
