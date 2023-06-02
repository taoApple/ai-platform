package com.ruoyi.web.controller.sd;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.config.SDProperties;
import com.ruoyi.sd.dto.*;
import com.ruoyi.sd.kit.GptKit;
import com.ruoyi.sd.kit.HttpClientByJDk11;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.List;

/**
 * StableDiffusionController
 *
 * @author tao
 * @version 1.0
 * @description sd
 * @date 2023/5/18 11:13
 */
@Api("stable-diffusion")
@RestController
@RequestMapping("/sd")
public class StableDiffusionController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SDProperties prop;

    @Autowired
    private GptKit gptKit;

    @ApiOperation("场景调用")
    @PostMapping("scene")
    public R scene(@RequestBody SdDTO dto){
        String format = String.format(prop.getScene(), dto.getPrompt());
        List<GptMessageDTO> gptMessageDTOS = gptKit.gptRequest(format);
        log.debug(JSONObject.toJSONString(gptMessageDTOS));
        return R.ok(gptMessageDTOS);
    }

    @ApiOperation("文案生成")
    @PostMapping("copywriting")
    public R copywriting(@RequestBody SdDTO dto){
        String format = String.format(prop.getCopywriting(), dto.getPrompt());
        return R.ok(gptKit.gptRequest(format));
    }

    @ApiOperation("文生图")
    @PostMapping("sd")
    public R sd(@RequestBody SdDTO dto){
        try {
//            String translate = translate(dto);
//            dto.setPrompt(translate);
            String s = HttpClientByJDk11.doPost(prop.getStableDiffusion(), null, JSON.toJSONString(dto));
            SdImagesDTO sdImagesDTO = JSON.parseObject(s, SdImagesDTO.class);
            return R.ok(sdImagesDTO);
        } catch (RemoteException e) {
            log.error("",e);
            return R.fail();
        }
    }
    @ApiOperation("中译英")
    @PostMapping("translate")
    public String translate(@RequestBody SdDTO dto){
        try {
            String s = HttpClientByJDk11.doPost(prop.getTranslate(), null, JSON.toJSONString(List.of(dto.getPrompt())));
            JSONArray jsonObject = JSONArray.parse(s);
            return jsonObject.get(0).toString();
        } catch (RemoteException e) {
            log.error("",e);
        }
        return null;
    }



    public void toArray(String content){

    }

}
