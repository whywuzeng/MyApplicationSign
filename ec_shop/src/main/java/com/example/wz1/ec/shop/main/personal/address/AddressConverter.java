package com.example.wz1.ec.shop.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.address
 */

public class AddressConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> getItemEntityList() throws Exception {
        String jsonData = getJsonData();
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            MultipleItemEntity build = new MultipleItemEntity.MultipleItemEntityBuild()
                    .putField(MultipleFields.ID, id)
                    .putField(MultipleFields.NAME, name)
                    .putField(MultipleFields.ITEM_TYPE, AddressItemType.ITEM_ADDRESS)
                    .putField(AddressItemFields.PHONE, phone)
                    .putField(AddressItemFields.ADDRESS, address)
                    .putField(AddressItemFields.DEFAULT, isDefault)
                    .build();
            list.add(build);
        }
        return list;
    }
}
