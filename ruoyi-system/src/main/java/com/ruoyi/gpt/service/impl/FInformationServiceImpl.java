package com.ruoyi.gpt.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.gpt.domain.FInformation;
import com.ruoyi.gpt.mapper.FInformationMapper;
import com.ruoyi.gpt.service.IFInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资料信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-02
 */
@Service
public class FInformationServiceImpl implements IFInformationService
{
    @Autowired
    private FInformationMapper fInformationMapper;

    /**
     * 查询资料信息
     * 
     * @param id 资料信息主键
     * @return 资料信息
     */
    @Override
    public FInformation selectFInformationById(Long id)
    {
        return fInformationMapper.selectFInformationById(id);
    }

    /**
     * 查询资料信息列表
     * 
     * @param fInformation 资料信息
     * @return 资料信息
     */
    @Override
    public List<FInformation> selectFInformationList(FInformation fInformation)
    {
        return fInformationMapper.selectFInformationList(fInformation);
    }

    /**
     * 新增资料信息
     * 
     * @param fInformation 资料信息
     * @return 结果
     */
    @Override
    public int insertFInformation(FInformation fInformation)
    {
        fInformation.setCreateTime(DateUtils.getNowDate());
        return fInformationMapper.insertFInformation(fInformation);
    }

    /**
     * 修改资料信息
     * 
     * @param fInformation 资料信息
     * @return 结果
     */
    @Override
    public int updateFInformation(FInformation fInformation)
    {
        fInformation.setUpdateTime(DateUtils.getNowDate());
        return fInformationMapper.updateFInformation(fInformation);
    }

    /**
     * 批量删除资料信息
     * 
     * @param ids 需要删除的资料信息主键
     * @return 结果
     */
    @Override
    public int deleteFInformationByIds(Long[] ids)
    {
        return fInformationMapper.deleteFInformationByIds(ids);
    }

    /**
     * 删除资料信息信息
     * 
     * @param id 资料信息主键
     * @return 结果
     */
    @Override
    public int deleteFInformationById(Long id)
    {
        return fInformationMapper.deleteFInformationById(id);
    }
}
