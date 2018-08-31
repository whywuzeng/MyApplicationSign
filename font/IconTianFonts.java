package com.example.wz1.mysigninapplication.font;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018-08-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.font
 */

public enum  IconTianFonts implements Icon{

    icon_xie('\u344f'),
    icon_move('\u345a'),
    icon_home('\u345f');

    private char character;

    IconTianFonts(char character) {
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
