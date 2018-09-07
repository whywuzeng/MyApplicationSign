package com.example.wz1.ec.shop.databasemanager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018-09-06.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.databasemanager
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String desc;
    @Generated(hash = 1644488982)
    public UserProfile(int userId, String name, String email, String phone,
            String desc) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.desc = desc;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
