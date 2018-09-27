package com.example.wz1.ec.core.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by wz on 2018/9/18.
 */

public class MultipleItemEntity implements MultiItemEntity{

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE = new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUEUE);

    private MultipleItemEntity(LinkedHashMap<Object,Object> fields){
        MULTIPLE_FIELDS.putAll(fields);
    }
    //最后一个 type 只对应 ItemTYpe
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    public final <T> T getField(Object key)
    {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields()
    {
        return FIELDS_REFERENCE.get();
    }


    public static class MultipleItemEntityBuild{

        private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();

        public MultipleItemEntityBuild(){
            //把之前的数据清空
            MULTIPLE_FIELDS.clear();
        }

        public final MultipleItemEntityBuild putItemType(int values)
        {
            MULTIPLE_FIELDS.put(MultipleFields.ITEM_TYPE,values);
            return this;
        }

        public final MultipleItemEntityBuild putNameValues(String values){
            MULTIPLE_FIELDS.put(MultipleFields.NAME,values);
            return this;
        }

        public final MultipleItemEntityBuild putTextValues(String text)
        {
            MULTIPLE_FIELDS.put(MultipleFields.TEXT,text);
            return this;
        }

        public final MultipleItemEntityBuild putImageUrlValues(String url)
        {
            MULTIPLE_FIELDS.put(MultipleFields.IMAGE_URL, url);
            return this;
        }

        public final MultipleItemEntityBuild putTagValues(String tag)
        {
            MULTIPLE_FIELDS.put(MultipleFields.TAG,tag);
            return this;
        }

        public final MultipleItemEntityBuild putBannersValues(ArrayList<String> bannersvalues)
        {
            MULTIPLE_FIELDS.put(MultipleFields.BANNERS,bannersvalues);
            return this;
        }

        public final MultipleItemEntityBuild putSpansizeValues(int spansize)
        {
            MULTIPLE_FIELDS.put(MultipleFields.SPAN_SIZE,spansize);
            return this;
        }

        public final MultipleItemEntityBuild putFields(LinkedHashMap<?,?> map){
            MULTIPLE_FIELDS.putAll(map);
            return this;
        }

        public final MultipleItemEntityBuild putField(Object key,Object value)
        {
            MULTIPLE_FIELDS.put(key,value);
            return this;
        }

        public final MultipleItemEntity build(){
          return new MultipleItemEntity(MULTIPLE_FIELDS);
        }
    }

}
