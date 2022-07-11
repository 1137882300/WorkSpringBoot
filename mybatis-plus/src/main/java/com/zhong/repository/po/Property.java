package com.zhong.repository.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiao-pang
 * @since 2022-07-11
 */
@TableName("t_property")
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "property_id", type = IdType.AUTO)
    private Long propertyId;

    /**
     * 外部属性id
     */
    @TableField("outer_property_id")
    private String outerPropertyId;

    /**
     * 属性类型,商品属性0，销售属性1
     */
    @TableField("property_type")
    private Integer propertyType;

    /**
     * 所属平台
     */
    @TableField("platform")
    private String platform;

    @TableField("creator_id")
    private Long creatorId;

    @TableField("modified_id")
    private Long modifiedId;

    /**
     * 展示类型：1.文本输入 2.单选 3.多选 4.下拉选择
     */
    @TableField("show_type")
    private Boolean showType;

    /**
     * 是否支持拓展：1.是 0.否 
     */
    @TableField("is_extendable")
    private Boolean isExtendable;

    /**
     * 是否支持帅选：1.是 0.否
     */
    @TableField("is_searchable")
    private Boolean isSearchable;

    /**
     * 是否启用：1.是 0.否
     */
    @TableField("is_enable")
    private Boolean isEnable;

    @TableField("is_deleted")
    private Boolean isDeleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField("features")
    private String features;

    @TableField("version")
    @Version
    private Long version;

    @TableField("status")
    private Long status;


    public Long getPropertyId() {
        return propertyId;
    }

    public Property setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public String getOuterPropertyId() {
        return outerPropertyId;
    }

    public Property setOuterPropertyId(String outerPropertyId) {
        this.outerPropertyId = outerPropertyId;
        return this;
    }

    public Integer getPropertyType() {
        return propertyType;
    }

    public Property setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public Property setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Property setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public Long getModifiedId() {
        return modifiedId;
    }

    public Property setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
        return this;
    }

    public Boolean getShowType() {
        return showType;
    }

    public Property setShowType(Boolean showType) {
        this.showType = showType;
        return this;
    }

    public Boolean getIsExtendable() {
        return isExtendable;
    }

    public Property setIsExtendable(Boolean isExtendable) {
        this.isExtendable = isExtendable;
        return this;
    }

    public Boolean getIsSearchable() {
        return isSearchable;
    }

    public Property setIsSearchable(Boolean isSearchable) {
        this.isSearchable = isSearchable;
        return this;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public Property setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
        return this;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public Property setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Property setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public Property setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getFeatures() {
        return features;
    }

    public Property setFeatures(String features) {
        this.features = features;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Property setVersion(Long version) {
        this.version = version;
        return this;
    }

    public Long getStatus() {
        return status;
    }

    public Property setStatus(Long status) {
        this.status = status;
        return this;
    }

    public static final String PROPERTY_ID = "property_id";

    public static final String OUTER_PROPERTY_ID = "outer_property_id";

    public static final String PROPERTY_TYPE = "property_type";

    public static final String PLATFORM = "platform";

    public static final String CREATOR_ID = "creator_id";

    public static final String MODIFIED_ID = "modified_id";

    public static final String SHOW_TYPE = "show_type";

    public static final String IS_EXTENDABLE = "is_extendable";

    public static final String IS_SEARCHABLE = "is_searchable";

    public static final String IS_ENABLE = "is_enable";

    public static final String IS_DELETED = "is_deleted";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String FEATURES = "features";

    public static final String VERSION = "version";

    public static final String STATUS = "status";

    @Override
    public String toString() {
        return "Property{" +
        "propertyId=" + propertyId +
        ", outerPropertyId=" + outerPropertyId +
        ", propertyType=" + propertyType +
        ", platform=" + platform +
        ", creatorId=" + creatorId +
        ", modifiedId=" + modifiedId +
        ", showType=" + showType +
        ", isExtendable=" + isExtendable +
        ", isSearchable=" + isSearchable +
        ", isEnable=" + isEnable +
        ", isDeleted=" + isDeleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", features=" + features +
        ", version=" + version +
        ", status=" + status +
        "}";
    }
}
