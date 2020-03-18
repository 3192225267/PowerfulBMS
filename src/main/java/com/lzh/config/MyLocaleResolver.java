package com.lzh.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //解析区域信息
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取自定义请求头信息
        String l = httpServletRequest.getParameter("l");
     //获取浏览器上的区域信息
        Locale locale = httpServletRequest.getLocale();
        //获取当前操作系统 默认的区域信息
//        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)) {
            String[] split = l.split("_");
            //接收第1个参数为：语言代码， 国家代码
            locale = new Locale(split[0], split[1]);
        }
      return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
