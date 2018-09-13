package com.example.wz1.ec.core.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.delegate.bottom
 */

public class TableBeanBuild {

    private final LinkedHashMap<TableBean,BaseItemBottomDelegate> linkedHashMap=new LinkedHashMap<>();

    public TableBeanBuild addTable(TableBean key,BaseItemBottomDelegate value){
        linkedHashMap.put(key,value);
        return this;
    }

    public TableBeanBuild addTables(LinkedHashMap<TableBean,BaseItemBottomDelegate> map)
    {
        this.linkedHashMap.putAll(map);
        return this;
    }

    public static TableBeanBuild build() {
        return new TableBeanBuild();
    }

    public final LinkedHashMap<TableBean,BaseItemBottomDelegate>  getItem()
    {
        return this.linkedHashMap;
    }
}
