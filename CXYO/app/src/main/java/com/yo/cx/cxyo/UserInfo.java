package com.yo.cx.cxyo;

/**
 * Created by simonwong on 21/10/2017.
 */

public class UserInfo {
    private String name, gender, reason;

    private int image, age;

    public UserInfo(String name, String gender, int age, String reason, int image){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.reason = reason;
        this.image = image;
    }

    public UserInfo(String name, String details,int image){
        this.name = name;
        this.reason = details;
        this.image = image;
    }

    public UserInfo(String name, int image){
        this.name = name;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }

    public String getGender(){
        return this.gender;
    }

    public int getAge() { return this.age;}

    public String getReason(){
        return this.reason;
    }

    public int getImage(){
        return this.image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(int age) { this.age = age; }

    public void setImage(int image){
        this.image = image;
    }

}
