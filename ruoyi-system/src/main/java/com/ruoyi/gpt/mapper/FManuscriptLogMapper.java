package com.ruoyi.gpt.mapper;

import com.ruoyi.gpt.domain.model.FManuscriptLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FManuscriptLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FManuscriptLog record);

    int insertSelective(FManuscriptLog record);

    FManuscriptLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FManuscriptLog record);

    int updateByPrimaryKey(FManuscriptLog record);

    int batchInsert(@Param("list") List<FManuscriptLog> list);
}