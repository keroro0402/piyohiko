package com.example.api.entity;

public class User {
    private Integer userId;
    private String loginId;
    private String password;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLoginId(String loginId){
        this.loginId = loginId;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getUserId(){
        return this.userId;
    }

    public String getLoginId(){
        return this.loginId;
    }

    public String getPassword(){
        return this.password;
    }

}
