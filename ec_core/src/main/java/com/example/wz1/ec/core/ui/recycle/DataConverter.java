package com.example.wz1.ec.core.ui.recycle;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by wz on 2018/9/18.
 */

public abstract class DataConverter {
    protected ArrayList<MultipleItemEntity> list=new ArrayList<>();

    protected abstract ArrayList<MultipleItemEntity> getItemEntityList() ;

    private String JsonData=null;

    public DataConverter setJsonData(String jsonData) {
        JsonData = jsonData;
        return this;
    }

    public String getJsonData() throws Exception {
        if (TextUtils.isEmpty(JsonData))
        {
            throw new Exception("json Data not null");
        }
        return JsonData;
    }
}
