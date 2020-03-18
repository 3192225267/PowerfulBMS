package com.lzh.service.impl;


import com.lzh.mapper.BillMapper;
import com.lzh.pojo.Bill;
import com.lzh.pojo.BillJson;
import com.lzh.pojo.BillSearch;
import com.lzh.service.BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Bill)表服务实现类
 *
 * @author makejava
 * @since 2020-02-29 18:55:23
 */
@Service
public class BillServiceImpl implements BillService {
@Resource
private BillMapper billMapper;


    @Override
    public int deleteByPrimaryKey(Integer bid) {
        return billMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(Bill record) {
        return billMapper.insert(record);
    }

    @Override
    public int insertSelective(Bill record) {
        return billMapper.insertSelective(record);
    }

    @Override
    public Bill selectByPrimaryKey(Integer bid) {
        return billMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(Bill record) {
        return billMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Bill record) {
        return billMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Bill> getAll() {
        return billMapper.getAll();
    }

    @Override
    public Map<String,Object> getBill(Integer pay) {

            return billMapper.getBill(pay);

    }

    @Override
    public BillJson getsum() {
        return billMapper.getsum();
    }

    @Override
    public List<Bill> getpid(Integer pid) {
        return billMapper.getpid(pid);
    }

    @Override
    public List<Bill> fuzzysearch(BillSearch bs) {
        return billMapper.fuzzysearch(bs);
    }

}