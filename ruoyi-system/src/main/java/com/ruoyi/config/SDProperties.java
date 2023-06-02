package com.ruoyi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SDproperties
 *
 * @author tao
 * @version 1.0
 * @description stable diffusion 配置文件
 * @date 2023/5/18 09:15
 */
@Configuration(proxyBeanMethods = true)
@ConfigurationProperties(prefix = "stable-diffusion")
public class SDProperties {
    /**
     * gpt
     */
    private String chatGpt;
    /**
     * 翻译
     */
    private String translate;
    /**
     * sd
     */
    private String stableDiffusion;
    /**
     * gpt api key
     */
    private String gptKey;
    /**
     * 文案
     */
    private String copywriting;
    /**
     * 场景
     */
    private String scene;


    public String getChatGpt() {
        return chatGpt;
    }

    public void setChatGpt(String chatGpt) {
        this.chatGpt = chatGpt;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getStableDiffusion() {
        return stableDiffusion;
    }

    public void setStableDiffusion(String stableDiffusion) {
        this.stableDiffusion = stableDiffusion;
    }

    public String getGptKey() {
        return gptKey;
    }

    public void setGptKey(String gptKey) {
        this.gptKey = gptKey;
    }

    public String getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(String copywriting) {
        this.copywriting = copywriting;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
