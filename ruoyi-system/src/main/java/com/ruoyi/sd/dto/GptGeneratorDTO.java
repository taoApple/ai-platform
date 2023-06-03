package com.ruoyi.sd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CopywritingDTO
 *
 * @author tao
 * @version 1.0
 * @description 文案生成请求实体
 * @date 2023/6/3 08:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GptGeneratorDTO {
    /**
     * 内容
     */
    private String prompt;
    /**
     * 格式化
     */
    private String format;
    /**
     * 信息ID，可以为空
     */
    private Long id;
}
