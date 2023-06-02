package com.ruoyi.sd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * GptDTO
 *
 * @author tao
 * @version 1.0
 * @description gpt请求实体
 * @date 2023/5/18 14:13
 */
public class GptDTO<T> implements Serializable {

    private String model = "gpt-3.5-turbo";

    private Float temperature = 0.7f;

    private List<T> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public List<T> getMessages() {
        return messages;
    }

    public void setMessages(List<T> messages) {
        this.messages = messages;
    }

    public void setMessages(T t){
        this.messages = List.of(t);
    }
}
