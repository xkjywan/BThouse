package com.bthouse.mvp.module;


public class UserResponse {

    private String imgUrl;
    private String refereeRole;
    private String refereeRoleName;
    private String phone;
    private String loginName;
    private String name;
    private String userId;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRefereeRole() {
        return refereeRole;
    }

    public void setRefereeRole(String refereeRole) {
        this.refereeRole = refereeRole;
    }

    public String getRefereeRoleName() {
        return refereeRoleName;
    }

    public void setRefereeRoleName(String refereeRoleName) {
        this.refereeRoleName = refereeRoleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
