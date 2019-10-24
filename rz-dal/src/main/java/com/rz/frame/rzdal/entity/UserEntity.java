package com.rz.frame.rzdal.entity;

import com.rz.frame.rzdal.annotate.RzEntity;

import java.security.Timestamp;

@RzEntity
public class UserEntity {
    private Long id;
    private String loginName;
    private String loginPassword;
    private String userName;
    private String userId;
    private Integer userSex;
    private Integer userAge;
    private Integer userMobileno;
    private Integer userEmail;
    private Integer userStatus;
    private Timestamp createTime;
    private Timestamp datachangeLasttime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserMobileno() {
        return userMobileno;
    }

    public void setUserMobileno(Integer userMobileno) {
        this.userMobileno = userMobileno;
    }

    public Integer getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(Integer userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }
}
