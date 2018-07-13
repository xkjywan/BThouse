package com.bthouse.mvp.module;

import java.io.Serializable;

public class IloginMoudle implements Serializable {

    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
