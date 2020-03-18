package com.lzh.JVMTets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Test1 {

    public static void main(String[] args) {

        try {
            ClassLoader classLoader=   Class.forName("java.lang.String").getClassLoader();


            ClassLoader classLoader2=  Thread.currentThread().getContextClassLoader();

            ClassLoader classLoader1=  ClassLoader.getSystemClassLoader().getParent();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
