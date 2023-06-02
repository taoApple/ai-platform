package com.ruoyi.sd.kit;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.ruoyi.config.SDProperties;
import com.ruoyi.sd.dto.GptChoicesDTO;
import com.ruoyi.sd.dto.GptDTO;
import com.ruoyi.sd.dto.GptMessageDTO;
import com.ruoyi.sd.dto.GptResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * GptKit
 *
 * @author tao
 * @version 1.0
 * @description gpt页面调用工具类
 * @date 2023/5/30 17:09
 */
@Slf4j
@Component
@AllArgsConstructor
public class GptKit {
    private SDProperties prop;
    public List<GptMessageDTO> gptRequest(String text){
        Map<String,String> header = Map.of(
                "Authorization", "Bearer ".concat(prop.getGptKey())
        );
        GptDTO<GptMessageDTO> gptDTO = new GptDTO<>();
        GptMessageDTO gptMessageDTO = new GptMessageDTO();
        gptMessageDTO.setRole("assistant");
        gptMessageDTO.setContent(text);
        gptDTO.setMessages(gptMessageDTO);
        String s = null;
        try {
            s = HttpClientByJDk11.doPost(prop.getChatGpt(),header, JSON.toJSONString(gptDTO));
            GptResponseDTO<GptChoicesDTO> o = (GptResponseDTO<GptChoicesDTO>)JSON.parseObject(s, new TypeReference<GptResponseDTO<GptChoicesDTO>>() {});
            if(o.getChoices().isEmpty()){
                return null;
            }
            List<GptMessageDTO> messages = o.getChoices().get(0).getMessage();
            if(messages.isEmpty()){
                return null;
            }
            return messages;
        } catch (RemoteException e) {
            log.error("",e);
            return null;
        }
    }
}
