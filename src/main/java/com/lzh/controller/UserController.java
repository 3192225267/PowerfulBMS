package com.lzh.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzh.pojo.Bill;
import com.lzh.pojo.BillJson;
import com.lzh.pojo.IndexJson;
import com.lzh.pojo.User;
import com.lzh.service.BillService;
import com.lzh.service.UserService;
import com.lzh.util.MailUtil;
import com.lzh.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-02-29 18:55:23
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BillService blservice;

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @RequestMapping("login")
    @ResponseBody
    public User Login(String name, String pwd, HttpSession session){
       User u= userService.Login(name, DigestUtils.md5DigestAsHex(pwd.getBytes()));
       if(u!=null&&u.getStart().equals(1)){
            session.setAttribute("loginUser",u.getNumber());
           session.setAttribute("u",u);
           return u;
       }else{
           return u;
       }
    }

   // index页面初始化：给定首页要显示的数据
   @RequestMapping("initindex")
   public String initpublic(Map<String,Object> map,Model model){
       //查询未付款订单
       Map<String,Object> undone= blservice.getBill(0);
       //查询已付款订单
       Map<String,Object> completed= blservice.getBill(1);
       //查询付款中订单
       Map<String,Object> processing= blservice.getBill(2);
       BillJson all= blservice.getsum();
       System.out.println(all.getBillcount()+all.getBillsum());
       map.put("undone",undone);
       map.put("completed",completed);
       map.put("processing",processing);
       map.put("all",all);
       PageHelper.startPage(1,10);
       List<Bill> blist=blservice.getAll();
       PageInfo<Bill>page=new PageInfo<>(blist);
       model.addAttribute("page",page.getList());
       return "index";
   }

@RequestMapping("initindexdadta")
@ResponseBody
public User initindexdadta(Map<String,Object> map,HttpSession session,Model model){
    User u=(User)session.getAttribute("u");
    return u;
}
//    注册：返回   1为注册成功，2为当前账号已被注册，3为验证码错误
    @RequestMapping("/rifister")
    @ResponseBody
    public int rifister(User user,String yzEmail,HttpSession session){
        Boolean bol= userService.verification(user.getNumber());
        if(bol){
            String code=(String)session.getAttribute("code");
            //如果注册成功，则n=1，失败则返回0.
            if(yzEmail.equals(code)){
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                int n= userService.insertSelective(user);
                return n;
            }else{
                return 3;
            }
        }
        //如果bol为false，则证明当前账号已被注册，返回2
        System.out.println("2");
        return 2;

    }


    //找回密码邮箱验证：取出当前session里的验证码，与用户输入的作比对，正确返回0，错误返回1
    @RequestMapping("/confirm")
    @ResponseBody
    public int confirm(String number,String yzEmail,HttpSession session){

            String code=(String)session.getAttribute("code");
            //如果注册成功，则n=1，失败则返回0.
            if(yzEmail.equals(code)){
                return 0;
            }else{
                return 1;
            }
    }

   //找回密码
    @RequestMapping("/getbackpwd")
    @ResponseBody
    public int getbackpwd(String number,String newpwd){
        User u=new User();
        u.setNumber(number);
        u.setPassword( DigestUtils.md5DigestAsHex(newpwd.getBytes()));
        //如果注册成功，则n=1，失败则返回0.
        int n=userService.getBackPwd(u);
        if(n>0){
            return 0;
        }else{
            return 1;
        }
    }
    @RequestMapping("/userdata")
    public String userdata(Model model){
       List<User> ulist= userService.getAll();
     model.addAttribute("ulist",ulist);
        return "user/UsersData";
    }

    @RequestMapping("seeuser")
    public String seeuser(Integer id,Model model){
       User u= userService.selectByPrimaryKey(id);
       model.addAttribute("user",u);
       return "user/user_view";
    }
    @RequestMapping("upduserpwd")
    public String upduserpwd(String oldpwd,String newpwd){

        return "user/updatepwd";
    }
    @RequestMapping("modifypwd")
    @ResponseBody
    public Integer modifypwd(String oldpwd,String newpwd,HttpSession session){
       User u=(User) session.getAttribute("u");
       if(DigestUtils.md5DigestAsHex(oldpwd.getBytes()).equals(u.getPassword())){
           u.setPassword(DigestUtils.md5DigestAsHex(newpwd.getBytes()));
        int n= userService.updateByPrimaryKeySelective(u);
        if (n>0){

            return 0;
        }else {
            return 1;
        }
       }
       else{
           return 2;
       }
    }

    @RequestMapping("/lock")
    public String lock(){

        return "user/lockscreen";
    }

    @RequestMapping("/unlock")
    @ResponseBody
    public Integer unlock(String password,HttpSession session){
       User u=(User) session.getAttribute("u");
       if (u.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
           return 0;
       }
        session.setAttribute("start","密码有误");
        return 1;

    }
    @RequestMapping("/initedituser")
    public String initedituser(HttpSession session){
       User u=(User) session.getAttribute("u");

        return "user/User_information";
    }
    @PutMapping("/edituser")
    public String editUser(User user){
       int n= userService.updateByPrimaryKeySelective(user);

        if(n>0){
            return "redirect:/initindex";
        }
       else{
           return "redirect:/initedituser";
       }
    }

//    发送邮件
    @RequestMapping("send_Email")
    @ResponseBody
    public int setEmail(String number,String email,HttpSession session){

            RandomUtils randomUtils = new RandomUtils();
            String random = randomUtils.getRandom();
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                //标题
                message.setSubject("Powerful验证码：");
                message.setText(random);
                //发件人
                message.setFrom("3192225267@qq.com");
                //收件人
                message.setTo(email);
                javaMailSender.send(message);
                session.setAttribute("code", random);
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }
//        try {
//            MailUtil.send_mail(email, random);
//            System.out.println("邮件发送成功!");
//            session.setAttribute("code",random);
//            return 0;
//        }catch (Exception e){
//            e.printStackTrace();
//            return 1;
//       }

    //验证用户
    @RequestMapping("/nameyz")
    @ResponseBody
    public int nameyz(String number){

     Boolean bol=   userService.verification(number);
        System.out.println("bol"+bol);
     if(bol){
         return 0;
     }
        return 1;
    }

}