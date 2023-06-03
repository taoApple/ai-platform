package com.ruoyi.gpt.mapper;

import com.ruoyi.gpt.domain.model.FStoryboardImages;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FStoryboardImagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FStoryboardImages record);

    int insertSelective(FStoryboardImages record);

    FStoryboardImages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FStoryboardImages record);

    int updateByPrimaryKey(FStoryboardImages record);

    int batchInsert(@Param("list") List<FStoryboardImages> list);
}