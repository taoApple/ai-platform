package com.ruoyi.gpt.mapper;

import com.ruoyi.gpt.domain.model.FStoryboardLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FStoryboardLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FStoryboardLog record);

    int insertSelective(FStoryboardLog record);

    FStoryboardLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FStoryboardLog record);

    int updateByPrimaryKey(FStoryboardLog record);

    int batchInsert(@Param("list") List<FStoryboardLog> list);
}