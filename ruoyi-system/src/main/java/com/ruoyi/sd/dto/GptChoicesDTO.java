package com.ruoyi.sd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * GptChoicesDTO
 *
 * @author tao
 * @version 1.0
 * @description gpt choices
 * @date 2023/5/18 18:47
 */
public class GptChoicesDTO implements Serializable {

    private List<GptMessageDTO> message;

    private String finish_reason;

    private Double index;

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }

    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    public List<GptMessageDTO> getMessage() {
        return message;
    }

    public void setMessage(List<GptMessageDTO> message) {
        this.message = message;
    }
}
