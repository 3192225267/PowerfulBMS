package com.lzh.pojo;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public class IndexJson {
   private Map<String,Map<String,Object>> map;
   private PageInfo<?> page;
   private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public PageInfo<?> getPage() {
        return page;
    }

    public void setPage(PageInfo<?> page) {
        this.page = page;
    }
}
