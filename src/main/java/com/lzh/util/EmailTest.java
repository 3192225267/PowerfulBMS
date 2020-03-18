package com.lzh.util;

public class EmailTest {
    public static void main(String[] args) {

            RandomUtils randomUtils = new RandomUtils();
            String random = randomUtils.getRandom();
            try {
                MailUtil.send_mail("1778429556@qq.com", random);

            }catch (Exception e){
                e.printStackTrace();
            }

    }
}
