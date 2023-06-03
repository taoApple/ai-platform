package com.ruoyi.gpt.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 文案记录
 */
@ApiModel(description = "文案记录")
public class FCopywriteLog {
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_REQUEST = "request";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_CREAET_USER_ID = "creaet_user_id";
    public static final String COL_CONTENT = "content";
    public static final String COL_STATUS = "status";
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String request;

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
     * 返回内容
     */
    @ApiModelProperty(value = "返回内容")
    private String content;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}