package com.example.wz1.ec.shop.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.app.AccountManager;
import com.example.wz1.ec.shop.databasemanager.DatabaseManager;
import com.example.wz1.ec.shop.databasemanager.UserProfile;
import com.example.wz1.ec.shop.databasemanager.Userpass;

/**
 * Created by Administrator on 2018-09-06.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.sign
 */

public class SignDataHanlder  {
    public static void SignIn(String result, ISignInListener listener)
    {
        //数据库保存
        //登录成功
        JSONObject body = JSON.parseObject(result).getJSONObject("body");
        Userpass userpass = new Userpass();
        userpass.setPassword(body.getString("password"));
        userpass.setName(body.getString("name"));
        userpass.setId(body.getString("id"));
        DatabaseManager.getInstanse().getUserpassDao().insertOrReplace(userpass);
        listener.onSignInSuccess(SignInTag.SIGN_IN);
        AccountManager.setCheckState();
    }

    public static void SignUp(String result,ISignInListener listener)
    {
        JSONObject body = JSON.parseObject(result).getJSONObject("body");
        UserProfile userProfile = new UserProfile();
        userProfile.setName(body.getString("name"));
        userProfile.setPhone(body.getString("password"));
        userProfile.setEmail(body.getString("email"));
        userProfile.setUserId(body.getInteger("id"));
        DatabaseManager.getInstanse().getUserProfileDao().insertOrReplace(userProfile);
        listener.onSignInSuccess(SignInTag.SIGN_UP);
        AccountManager.setCheckState();
    }
}
