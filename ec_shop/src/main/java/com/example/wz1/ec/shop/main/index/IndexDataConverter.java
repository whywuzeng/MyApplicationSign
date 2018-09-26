package com.example.wz1.ec.shop.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.ItemType;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by wz on 2018/9/19.
 */

public class IndexDataConverter extends DataConverter{

    @Override
    protected ArrayList<MultipleItemEntity> getItemEntityList() {
         JSONArray data = null;
        try {
            data = JSON.parseObject(getJsonData()).getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final int size=data.size();
        for (int i=0;i<size;i++)
        {
            JSONObject jsonObject = (JSONObject) data.get(i);
            String goodsId = jsonObject.getString("goodsId");
            String spanSize = jsonObject.getString("spanSize");
            JSONArray banners = jsonObject.getJSONArray("banners");
            String text = jsonObject.getString("text");
            String images = jsonObject.getString("images");

            ArrayList<String> bannerslist=new ArrayList<>();

            int type=0;
            if (text==null&&images!=null)
            {
                type= ItemType.IMAGE_URL;
            }
            else if (text!=null&&images==null)
            {
                type=ItemType.TEXT;
            }
            else if (images!=null)
            {
                type=ItemType.TEXT_IMAGE;
            }else if (banners!=null)
            {
                type=ItemType.BANNAR;
                final int bannersize = banners.size();
                for (int j=0;j<bannersize;j++)
                {
                    bannerslist.add(banners.getString(j));
                }
            }

            MultipleItemEntity build = new MultipleItemEntity.MultipleItemEntityBuild()
                    .putItemType(type)
                    .putBannersValues(bannerslist)
                    .putImageUrlValues(images)
                    .putTextValues(text)
                    .putSpansizeValues(spanSize)
                    .putField(MultipleFields.ID, goodsId).build();
            list.add(build);

        }
        return list;
    }
}
