package com.example.wz1.mysigninapplication.font;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018-08-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.font
 */

public enum IconEcFonts implements Icon{
    icon_my('\ue659'),
    icon_scan('\ue649');

    private char character;

    IconEcFonts(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
