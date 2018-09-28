package com.example.wz1.ec.shop.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by wz on 2018/9/28.
 */

public class VerticalListDataConverter extends DataConverter{

    @Override
    protected ArrayList<MultipleItemEntity> getItemEntityList()   {
        String jsonData = null;
        try {
            jsonData = getJsonData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(jsonData);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        int size = jsonArray.size();
        for (int i=0;i<size;i++)
        {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
            Integer id = jsonObject1.getInteger("id");
            String name = jsonObject1.getString("name");


            MultipleItemEntity build = new MultipleItemEntity.MultipleItemEntityBuild().putField(MultipleFields.ID, id)
                    .putNameValues(name)
                    .putField(MultipleFields.TAG,false)
                    .build();

            list.add(build);
        }
        list.get(0).setFields(MultipleFields.TAG,true);
        return list;
    }
}
