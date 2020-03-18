package com.lzh.service;


import com.lzh.pojo.Provider;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Provider)表服务接口
 *
 * @author zry
 * @since 2020-02-29 18:55:23
 */
public interface ProviderService {
    int deleteByPrimaryKey(Integer pid);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);
    List<Provider> getAll();
}