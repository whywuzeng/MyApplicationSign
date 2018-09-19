package com.example.wz1.ec.core.ui.recycle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by wz on 2018/9/18.
 */

public class MultipleItemEntity implements MultiItemEntity{

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS=new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE = new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUEUE);

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MULTIPLE_FIELDS.get(MultipleFields.ITEM_TYPE));
    }

    public final <T> T getField(Object key)
    {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields()
    {
        return FIELDS_REFERENCE.get();
    }


}
