package com.bthouse.mvp.module;


import java.io.Serializable;

/**
 * 用户基础类
 */
public class UserModel implements Serializable {

    private String username;
    private String user_id;
    private String avatar;
    private String token;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel(String username, String user_id, String avatar, String token) {
        this.username = username;
        this.user_id = user_id;
        this.avatar = avatar;
        this.token = token;
    }
}
