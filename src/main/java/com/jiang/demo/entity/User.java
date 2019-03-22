package com.jiang.demo.entity;


import javax.persistence.*;
@Entity

@Table(name = "tbl_user")
public class User {

    /**
     * 自增主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 50)//这是和数据表对应的一个列
    private String userName;

    @Column(name = "user_age", length = 3)//这是和数据表对应的一个列
    private Integer userAge;

    @Column(name = "user_password", length = 30)//这是和数据表对应的一个列
    private String userPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
