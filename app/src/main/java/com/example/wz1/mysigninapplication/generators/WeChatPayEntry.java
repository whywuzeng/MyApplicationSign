package com.example.wz1.mysigninapplication.generators;

import com.example.wz1.ec.annotation.PayEntryGenerator;
import com.example.wz1.ec.core.wechat.templates.WXPayEntryTemplate;

/**
 * Created by wz on 2018/9/8.
 */
@PayEntryGenerator(
        packageName = "com.example.wz1.mysigninapplication",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
