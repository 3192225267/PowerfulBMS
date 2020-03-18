package com.lzh.controller;

import com.lzh.pojo.Provider;
import com.lzh.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class ProviderController {
    @Autowired
    private ProviderService proservice;
//    数据初始化
    @RequestMapping("prodata")
    public String prodata(Model model,@RequestParam(value = "type",defaultValue = "provider_data") String type){
     List<Provider>plist=   proservice.getAll();
     model.addAttribute("plist",plist);
        return "provider/"+type;
    }
//    删除供应商
    @GetMapping("delprovider")
    @ResponseBody
    public int delprovider(Integer pid){
        int n=   proservice.deleteByPrimaryKey(pid);
        return n;
    }


    @GetMapping("/provider_view")
    public String provider_view(){

        return "provider/provider_view";
    }
    @GetMapping("/updateProvider")
    public String updprovider(){
        System.out.println("ok");
        return "provider/updateProvider";
    }
//    重定向到上面的 updateProvider 进行跳转，否则请求不到静态资源
    @GetMapping("provider/{id}")
    public String updprovider(@PathVariable("id") Integer id,
                              @RequestParam(value = "type",defaultValue = "provider_view")String type, HttpSession session){
        Provider provider=   proservice.selectByPrimaryKey(id);
        session.setAttribute("pd",provider);

        return "redirect:/"+type;
    }

    @PutMapping("modifyprovider")
    @ResponseBody
    public int modifyprovider(Provider provider,Model model){
        int n=proservice.updateByPrimaryKeySelective(provider);
        if(n>0){
        }
        return n;
    }

    @PutMapping("add_provide")
    @ResponseBody
    public int add_provide(Provider provider){
        int n=proservice.insertSelective(provider);
        return n;
    }


}
