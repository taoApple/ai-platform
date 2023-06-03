package com.ruoyi.gpt.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 分镜脚本图片
 */
@ApiModel(description = "分镜脚本图片")
public class FStoryboardImages {
    public static final String COL_ID = "id";
    public static final String COL_STORYBOARD_ID = "storyboard_id";
    public static final String COL_CONTENT = "content";
    public static final String COL_CONTENT_ID = "content_id";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_CREAET_USER_ID = "creaet_user_id";
    public static final String COL_IMAGE = "image";
    public static final String COL_STATUS = "status";
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 分镜脚本id
     */
    @ApiModelProperty(value = "分镜脚本id")
    private Integer storyboardId;

    /**
     * 脚本内容
     */
    @ApiModelProperty(value = "脚本内容")
    private String content;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer contentId;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer creaetUserId;

    /**
     * base64
     */
    @ApiModelProperty(value = "base64")
    private String image;

    /**
     * 确认状态 0:确认,1:未确认
     */
    @ApiModelProperty(value = "确认状态 0:确认,1:未确认")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoryboardId() {
        return storyboardId;
    }

    public void setStoryboardId(Integer storyboardId) {
        this.storyboardId = storyboardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreaetUserId() {
        return creaetUserId;
    }

    public void setCreaetUserId(Integer creaetUserId) {
        this.creaetUserId = creaetUserId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}