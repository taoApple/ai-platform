package com.ruoyi.gpt.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资料信息对象 f_information
 * 
 * @author ruoyi
 * @date 2023-06-02
 */
public class FInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 资料名称 */
    @Excel(name = "资料名称")
    private String name;

    /** 资料内容 */
    @Excel(name = "资料内容")
    private String content;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long creaetUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCreaetUserId(Long creaetUserId) 
    {
        this.creaetUserId = creaetUserId;
    }

    public Long getCreaetUserId() 
    {
        return creaetUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("content", getContent())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("creaetUserId", getCreaetUserId())
            .toString();
    }
}
