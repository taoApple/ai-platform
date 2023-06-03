package com.ruoyi.gpt.service;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.sd.dto.CopyWriteDTO;
import com.ruoyi.sd.dto.GptMessageDTO;

import java.util.List;

/**
 * GptService
 *
 * @author tao
 * @version 1.0
 * @description gpt调用
 * @date 2023/6/3 08:51
 */
public interface GptService {
    /**
     * 文案生成
     * @param dto
     * @return
     */
    R<List<GptMessageDTO>> copywriting(CopyWriteDTO dto,Long userId);


//    R<List<GptMessageDTO>>
}
