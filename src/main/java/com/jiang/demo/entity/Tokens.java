package com.jiang.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/18
 */

@Entity
public class Tokens {

    @Id
    @GeneratedValue
    private Integer tokenid;

    @OneToOne(fetch = FetchType.EAGER)
    private UserInfo userInfo;

    private String token;

    private Date buildtime;

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(Date buildtime) {
        this.buildtime = buildtime;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "tokenid=" + tokenid +
                ", userInfo=" + userInfo +
                ", token='" + token + '\'' +
                ", buildtime=" + buildtime +
                '}';
    }
}
