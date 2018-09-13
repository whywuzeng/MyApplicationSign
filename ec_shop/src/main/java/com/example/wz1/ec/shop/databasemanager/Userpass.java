package com.example.wz1.ec.shop.databasemanager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wz on 2018/9/8.
 */
@Entity(nameInDb = "user_pass")
public class Userpass {
    @Id
    private String password;
    private String name;
    private String id;
    @Generated(hash = 1578497095)
    public Userpass(String password, String name, String id) {
        this.password = password;
        this.name = name;
        this.id = id;
    }
    @Generated(hash = 208516798)
    public Userpass() {
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
