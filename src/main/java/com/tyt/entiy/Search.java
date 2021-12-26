package com.tyt.entiy;

import java.util.Date;
import java.util.List;

/**
 * @author zhy
 * @since 2021/12/26 10:06
 */
public class Search {
    private String search;
    private int size;
    private Date date;
    private List<String> list;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
