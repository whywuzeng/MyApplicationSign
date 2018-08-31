package com.example.wz1.mysigninapplication.font;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by Administrator on 2018-08-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.font
 */

public class IconTianModule implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return IconTianFonts.values();
    }
}
