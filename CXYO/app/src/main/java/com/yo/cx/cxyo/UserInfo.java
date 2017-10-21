package com.yo.cx.cxyo;

/**
 * Created by simonwong on 21/10/2017.
 */

public class UserInfo {
    private String name, info, reason;

    private int image;

    public UserInfo(String name, String info, String reason, int image){
        this.name = name;
        this.info = info;
        this.reason = reason;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }

    public String getInfo(){
        return this.info;
    }

    public String getReason(){
        return this.reason;
    }

    public int getImage(){
        return this.image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setInfo(String info){
        this.info = info;
    }

    public void setImage(int image){
        this.image = image;
    }

}
