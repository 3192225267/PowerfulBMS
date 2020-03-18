package com.lzh.mapper;

import com.lzh.pojo.Provider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProviderMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);
    @Select("select * from Provider")
    List<Provider> getAll();
}