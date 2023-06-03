package com.ruoyi.gpt.mapper;

import com.ruoyi.gpt.domain.model.FCopywriteLog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FCopywriteLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FCopywriteLog record);

    int insertSelective(FCopywriteLog record);

    FCopywriteLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FCopywriteLog record);

    int updateByPrimaryKey(FCopywriteLog record);

    int batchInsert(@Param("list") List<FCopywriteLog> list);
}