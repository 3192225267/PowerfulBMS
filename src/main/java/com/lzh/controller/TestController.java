package com.lzh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
public class TestController {
    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/t")
    public void testSimpleMail()throws MessagingException {
        //封装简单的邮件内容
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        //通过消息帮助对象来设置发送的内容 ,第二个参数为true表示可以发送附件
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        //标题
        messageHelper.setSubject("放假通知");
        //第2个参数为true,才可以发送html代码
        messageHelper.setText("<h2 style='color:red'>春节放假7天</h2>", true);

        //发送附件
        messageHelper.addAttachment("1.jpg", new File("C:\\wallhaven-96yljd.jpg"));
        //发件人
        messageHelper.setFrom("3192225267@qq.com");
        //收件人
        messageHelper.setTo("1778429556@qq.com");

        javaMailSender.send(mimeMessage);

    }
    @RequestMapping("/tast")
    @ResponseBody
    public String t1() throws Exception {
        stringRedisTemplate.opsForValue().set("94_409","刘振河和他的孩子们");
        stringRedisTemplate.opsForHash().put("409","大儿子","刘禹来");
        stringRedisTemplate.opsForHash().put("409","二儿子","关胤");
        stringRedisTemplate.opsForHash().put("409","三儿子","常羽鹏");
        stringRedisTemplate.opsForHash().put("409","小儿子","孙佳伟");
        String haizimen=(String )stringRedisTemplate.opsForHash().get("409","小儿子");
        return haizimen;
    }

//    @RequestMapping("/ttst")
//    @ResponseBody
//    public String ttst() throws Exception {
//        stringRedisTemplate.opsForValue().set("94_409","刘振河和他的孩子们");
//        stringRedisTemplate.opsForHash().put("409","大儿子","刘禹来");
//        stringRedisTemplate.opsForHash().put("409","二儿子","关胤");
//        stringRedisTemplate.opsForHash().put("409","三儿子","常羽鹏");
//        stringRedisTemplate.opsForHash().put("409","小儿子","孙佳伟");
//        String haizimen=(String )stringRedisTemplate.opsForHash().get("409","小儿子");
//        return haizimen;
//    }
}
