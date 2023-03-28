package com.example.page;

public class MBean {
    private int icon;
    private int img;
    private int likeNum;
    private String name;

    public MBean(int icon, int img, int likeNum, String name) {
        this.icon = icon;
        this.img = img;
        this.likeNum = likeNum;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
//wnd 5000 ms