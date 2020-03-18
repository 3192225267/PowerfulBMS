package com.lzh.service.impl;


import com.lzh.mapper.ProviderMapper;
import com.lzh.pojo.Provider;
import com.lzh.service.ProviderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Provider)表服务实现类
 *
 * @author makejava
 * @since 2020-02-29 18:55:23
 */
@Service
public class ProviderServiceImpl implements ProviderService {
@Resource
private ProviderMapper ProviderMapper;

    @Override
    public int deleteByPrimaryKey(Integer pid) {
        return ProviderMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int insert(Provider record) {
        return ProviderMapper.insert(record);
    }

    @Override
    public int insertSelective(Provider record) {
        return ProviderMapper.insertSelective(record);
    }

    @Override
    public Provider selectByPrimaryKey(Integer pid) {
        return ProviderMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int updateByPrimaryKeySelective(Provider record) {
        return ProviderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Provider record) {
        return ProviderMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Provider> getAll() {
        return ProviderMapper.getAll();
    }

}