package com.lzh.service;

import com.lzh.pojo.Bill;
import com.lzh.pojo.BillJson;
import com.lzh.pojo.BillSearch;

import java.util.List;
import java.util.Map;

/**
 * (Bill)表服务接口
 *
 * @author zry
 * @since 2020-02-29 18:55:23
 */
public interface BillService {
    int deleteByPrimaryKey(Integer bid);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> getAll();

    Map<String,Object> getBill(Integer pay);

    BillJson getsum();

    List<Bill> getpid(Integer pid);

    List<Bill> fuzzysearch(BillSearch bs);

}