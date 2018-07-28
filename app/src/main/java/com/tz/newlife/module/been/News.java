package com.tz.newlife.module.been;

import java.io.Serializable;

public class News implements Serializable{

    private String title;

    private String content;

    private int pic;

    private String conments;


    public News(String title, String content, int pic, String comments) {
        this.title = title;
        this.content = content;
        this.pic = pic;
        this.conments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getConments() {
        return conments;
    }

    public void setConments(String conments) {
        this.conments = conments;
    }
}
