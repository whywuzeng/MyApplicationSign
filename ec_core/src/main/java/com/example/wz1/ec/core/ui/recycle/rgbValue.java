package com.example.wz1.ec.core.ui.recycle;

import com.google.auto.value.AutoValue;

/**
 * Created by wz on 2018/9/27.
 */
@AutoValue
public abstract class rgbValue {
    public abstract int red();
    public abstract int green();
    public abstract int yellow();

    public static rgbValue create(int red,int green,int blue){
        return new AutoValue_rgbValue(red,green,blue);
    }
}
