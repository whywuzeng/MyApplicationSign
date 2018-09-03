package com.example.wz1.mysigninapplication;

import com.example.wz1.ec.core.activites.ProxyActivity;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.mysigninapplication.delegate.HomeDelegate;

/**
 * Created by wz on 2018/9/1.
 */

public class Main2Activity extends ProxyActivity {
    @Override
    public BaseDelegate setRootDelegate() {

        return new HomeDelegate();
    }
}
