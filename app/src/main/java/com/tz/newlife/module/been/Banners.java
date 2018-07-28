package com.tz.newlife.module.been;

import java.io.Serializable;

public class Banners implements Serializable{

    private int pic;

    private String title;

    public Banners(int pic, String title) {
        this.pic = pic;
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
