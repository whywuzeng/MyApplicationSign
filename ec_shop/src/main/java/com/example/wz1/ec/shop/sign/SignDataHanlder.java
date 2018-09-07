package com.example.wz1.ec.shop.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2018-09-06.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.sign
 */

public class SignDataHanlder  {
    public static void SignIn(String result)
    {
        JSONObject body = JSON.parseObject(result).getJSONObject("body");
        
    }
}
