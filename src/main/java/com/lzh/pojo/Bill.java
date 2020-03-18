package com.lzh.pojo;

import java.util.Date;

public class Bill {
    private Integer bid;

    private String bill_code;

    private String  bill_name;

    private String bill_com;

    private Integer bill_num;

    private Double money;

    private Integer pay;

    private Integer pid;

    private Date create_date;

    private Provider provider;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getBill_name() {
        return bill_name;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public String getBill_com() {
        return bill_com;
    }

    public void setBill_com(String bill_com) {
        this.bill_com = bill_com;
    }

    public Integer getBill_num() {
        return bill_num;
    }

    public void setBill_num(Integer bill_num) {
        this.bill_num = bill_num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}