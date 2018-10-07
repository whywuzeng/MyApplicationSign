package com.example.wz1.ec.shop.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by wz on 2018/10/7.
 */

public class CartDataConvert extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> getItemEntityList()  {
        JSONObject object = null;
        try {
            object = JSON.parseObject(getJsonData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray dataArr = object.getJSONArray("data");
        int size = dataArr.size();
        for (int i=0;i<size;i++)
        {
            final JSONObject data = dataArr.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            MultipleItemEntity build = new MultipleItemEntity.MultipleItemEntityBuild()
                    .putField(MultipleFields.ID, id)
                    .putField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM_TYPE)
                    .putField(MultipleFields.IMAGE_URL, thumb)
                    .putField(ShopCartFieldType.desc, desc)
                    .putField(ShopCartFieldType.title, title)
                    .putField(ShopCartFieldType.count, count)
                    .putField(ShopCartFieldType.price, price)
                    .putField(ShopCartFieldType.position, i)
                    .putField(ShopCartFieldType.is_select, false)
                    .build();

            list.add(build);
        }

        return list;
    }
}
