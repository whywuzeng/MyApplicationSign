package com.example.wz1.ec.core.delegate.web.event;

import java.util.HashMap;

/**
 * Created by wz on 2018/10/3.
 */

public class EventManager {
    private final HashMap<Object,Event> map=new HashMap<>();

    private final static class HOLDER{
        private static EventManager manager=new EventManager();
    }

    public static EventManager getInstance(){
        return HOLDER.manager;
    }

    //增加事件
    public void addEvent(Object key,Event value){
        map.put(key,value);
    }

    public Event createEvent(Object key){
        Event event = map.get(key);
        if (event!=null)
        {
            return event;
        }
        return null;
    }
}
