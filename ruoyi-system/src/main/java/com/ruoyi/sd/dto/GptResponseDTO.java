package com.ruoyi.sd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * GptResponseDTO
 *
 * @author tao
 * @version 1.0
 * @description gpt api返回
 * @date 2023/5/18 18:44
 */
public class GptResponseDTO<T> implements Serializable {
    private String id;

    private String object;

    private String created;

    private String model;

    private List<T> choices;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<T> getChoices() {
        return choices;
    }

    public void setChoices(List<T> choices) {
        this.choices = choices;
    }

    public GptResponseDTO(String id, String object, String created, String model, List<T> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }

    public GptResponseDTO(List<T> choices) {
        this.choices = choices;
    }
}
