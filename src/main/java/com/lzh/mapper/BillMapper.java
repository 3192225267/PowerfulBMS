package com.lzh.mapper;

import com.lzh.pojo.Bill;
import com.lzh.pojo.BillJson;
import com.lzh.pojo.BillSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Mapper
public interface BillMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);

    List<Bill> getAll();

    Map<String,Object> getBill(Integer pay);

    @Select(" SELECT SUM(money) as billsum,COUNT(1) as billcount  FROM bill ")
    BillJson getsum();

    List<Bill> getpid(Integer pid);

    List<Bill> fuzzysearch(BillSearch bs);

}