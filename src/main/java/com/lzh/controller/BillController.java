package com.lzh.controller;

import com.lzh.mapper.BillMapper;
import com.lzh.pojo.Bill;
import com.lzh.pojo.BillSearch;
import com.lzh.pojo.Provider;
import com.lzh.service.BillService;
import com.lzh.service.ProviderService;
import com.lzh.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
@Controller
public class BillController {
    @Autowired
    private BillService blservice;
    @Autowired
    private ProviderService pservice;
    @Resource
    private BillMapper billMapper;
    @GetMapping("billdata")
    public String table(Model model, BillSearch bs,@RequestParam(value = "type",defaultValue = "billData") String type){

        model.addAttribute("pay",bs.getPay());
        model.addAttribute("pid",bs.getPid());
        model.addAttribute("billName",bs.getName());
        List<Bill> blist= blservice.fuzzysearch(bs);
        List<Provider> plist=  pservice.getAll();
        model.addAttribute("bllist",blist);
        model.addAttribute("plist",plist);
        return "bill/"+type;
    }

    @RequestMapping("delbill")
    @ResponseBody
    public Integer delbill(Integer id){
        int n= billMapper.deleteByPrimaryKey(id);
        if(n>0){
            return n;
        }
        return n;
    }


    @GetMapping("/seeBill")
    public String provider_view(){

        return "bill/seeBill";
    }
    @GetMapping("/updateBill")
    public String updprovider(){

        return "bill/updateBill";
    }
    @RequestMapping("bill/{id}")
    public String updbill(@PathVariable("id") Integer id,
                          @RequestParam(value = "type",defaultValue = "seeBill")String type, HttpSession session){
        Bill b= billMapper.selectByPrimaryKey(id);
       List<Provider>plist= pservice.getAll();
        session.setAttribute("bl",b);
        session.setAttribute("pl",plist);
        return "redirect:/"+type;
    }
    @RequestMapping("modifybill")
    public String modifybill(Bill  bill, Integer pid){
        bill.setPid(pid);
        bill.setCreate_date(new Date());
      int n= blservice.updateByPrimaryKeySelective(bill);
      if(n>0){

          return "redirect:billdata";
      }
      else{
          return "redirect:billdata";
      }

    }
    @RequestMapping("dropout")
    public String dropout(HttpSession session) {
    session.removeAttribute("u");
        return "redirect:/";
    }

    @RequestMapping("getBypd")
    @ResponseBody
    public List<Bill> getBypd(Integer pid) {
        List<Bill>blist = blservice.getpid(pid);
        return blist;
    }

    @RequestMapping("add_bill")
    @ResponseBody
    public int add_bill(Bill bill) {
        System.out.println("yaho");
        RandomUtils randomUtils = new RandomUtils();
        String random = randomUtils.getRandom();
        bill.setBill_code(random);
        bill.setCreate_date(new Date());
       int n = blservice.insertSelective(bill);
        return n;
    }
}
