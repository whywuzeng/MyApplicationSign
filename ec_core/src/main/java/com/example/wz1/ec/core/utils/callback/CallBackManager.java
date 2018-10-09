package com.example.wz1.ec.core.utils.callback;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2018-10-09.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.utils.callback
 */

public class CallBackManager  {

    private WeakHashMap<Object,CallBackListener> map =new WeakHashMap<>();

    private static final class HOLDER {
        private static final CallBackManager CALL_BACK_MANAGER=new CallBackManager();
    }

    public static CallBackManager getInstances(){
        return HOLDER.CALL_BACK_MANAGER;
    }

    public void addCallBack(Object key,CallBackListener listener)
    {
        map.put(key,listener);
    }

    public CallBackListener getCallBack(Object key)
    {
        return map.get(key);
    }
}
