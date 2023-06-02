package com.ruoyi.gpt.mapper;


import com.ruoyi.gpt.domain.FInformation;

import java.util.List;

/**
 * 资料信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-02
 */
public interface FInformationMapper 
{
    /**
     * 查询资料信息
     * 
     * @param id 资料信息主键
     * @return 资料信息
     */
    public FInformation selectFInformationById(Long id);

    /**
     * 查询资料信息列表
     * 
     * @param fInformation 资料信息
     * @return 资料信息集合
     */
    public List<FInformation> selectFInformationList(FInformation fInformation);

    /**
     * 新增资料信息
     * 
     * @param fInformation 资料信息
     * @return 结果
     */
    public int insertFInformation(FInformation fInformation);

    /**
     * 修改资料信息
     * 
     * @param fInformation 资料信息
     * @return 结果
     */
    public int updateFInformation(FInformation fInformation);

    /**
     * 删除资料信息
     * 
     * @param id 资料信息主键
     * @return 结果
     */
    public int deleteFInformationById(Long id);

    /**
     * 批量删除资料信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFInformationByIds(Long[] ids);
}
