package com.example.wz1.mysigninapplication.generators;

import com.example.wz1.ec.annotation.EntryGenerator;
import com.example.wz1.ec.core.wechat.templates.WXEntryTemplate;

/**
 * Created by wz on 2018/9/8.
 */
@EntryGenerator(
        packageName = "com.example.wz1.mysigninapplication",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
