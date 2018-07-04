package com.tz.newlife.module.been;

import java.io.Serializable;

public class Fruit implements Serializable{
    //实现Serializable接口将Fruit的对象转换成可传输状态(序列化)
    //序列化后的对象不仅可以保存到本地也可以进行网路传输
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




