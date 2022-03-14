package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 57783 on 2018/7/4.
 */
@Entity
public class UserLogin {

    @Id
    private String phone;

    private String password;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLogin(){

    }
}
