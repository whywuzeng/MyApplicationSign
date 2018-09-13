package com.example.wz1.mysigninapplication.generators;

import com.example.wz1.ec.annotation.AppRegisterGenerator;
import com.example.wz1.ec.core.wechat.templates.WXAppRegisterTemplate;

/**
 * Created by wz on 2018/9/8.
 */
@AppRegisterGenerator(
        packageName = "com.example.wz1.mysigninapplication",
        registerTemplate = WXAppRegisterTemplate.class
)
public interface WeChatAppRegister {
}
