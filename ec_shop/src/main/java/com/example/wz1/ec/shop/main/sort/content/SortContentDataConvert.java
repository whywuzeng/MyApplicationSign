package com.example.wz1.ec.shop.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by wz on 2018/9/29.
 */

public class SortContentDataConvert {

    ArrayList<BaseSection> list = new ArrayList<>();

    public static SortContentDataConvert create( ){
       return new SortContentDataConvert();
    }

    public ArrayList<BaseSection> getArrayListItem(String json) {
        JSONObject object2 = JSON.parseObject(json);
        JSONArray data = object2.parseArray("data");
        int size = data.size();
        for (int i=0;i<size;i++)
        {
            JSONObject object= (JSONObject) data.get(i);
            Integer id = object.getInteger("id");
            String sectionName = object.getString("section");
            BaseSection baseSection = new BaseSection(true, sectionName, id);
            list.add(baseSection);
            JSONArray goods = object.getJSONArray("goods");
            int size1 = goods.size();
            for (int j=0;j<size1;j++)
            {
                JSONObject object1= (JSONObject) goods.get(j);
                Integer goods_id = object1.getInteger("goods_id");
                String goods_thumb = object1.getString("goods_thumb");
                String goods_name = object1.getString("goods_name");
                BaseSectionItemEntity baseSectionItemEntity = new BaseSectionItemEntity(goods_id, goods_thumb, goods_name);
                BaseSection baseSection1 = new BaseSection(baseSectionItemEntity);
                list.add(baseSection1);
            }
        }

        return list;
    }

}
