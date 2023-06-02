package com.ruoyi.sd.dto;

import java.io.Serializable;

/**
 * GptMessageDTO
 *
 * @author tao
 * @version 1.0
 * @description gpt请求消息
 * @date 2023/5/18 14:15
 */
public class GptMessageDTO implements Serializable {
    /**
     * 角色
     */
    private String role;
    /**
     * 内容
     */
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
