package com.ruoyi.sd.dto;

import java.util.List;

/**
 * SdImagesDTO
 *
 * @author tao
 * @version 1.0
 * @description stable diffusion 返回图片
 * @date 2023/5/19 08:45
 */
public class SdImagesDTO {
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
