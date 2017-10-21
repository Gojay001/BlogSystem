package cn.gojay.model;

import java.sql.Date;

/**
 * 博客实体类
 */
public class Blog {
    //id、内容、用户名、日期、标题
    private int blogId;
    private String blogText;
    private String name;
    private Date date;
    private String blogTitle;

    public Blog(int blogId, String blogText, String name, Date date, String blogTitle) {
        this.blogId = blogId;
        this.blogText = blogText;
        this.name = name;
        this.date = date;
        this.blogTitle = blogTitle;
    }

    public Blog(String blogText, String name, Date date, String blogTitle) {
        this.blogText = blogText;
        this.name = name;
        this.date = date;
        this.blogTitle = blogTitle;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}
