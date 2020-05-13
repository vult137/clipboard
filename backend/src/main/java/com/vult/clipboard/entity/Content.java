package com.vult.clipboard.entity;

import java.util.Date;

/**
 * @Author: Songyi Wang
 * @Email: wangsongyi08@gmail.com
 * @Date: 5/4/2020 7:36 PM
 */
public class Content {

    public Content() {}

    public Content(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public Content(Integer id, String title, String context, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private Integer id;
    private String title;
    private String context;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
