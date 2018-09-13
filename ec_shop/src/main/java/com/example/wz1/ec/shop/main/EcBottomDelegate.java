package com.example.wz1.ec.shop.main;

import com.example.wz1.ec.core.delegate.bottom.BaseBottomDelegate;
import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.delegate.bottom.TableBean;
import com.example.wz1.ec.shop.main.cart.CartDelegate;
import com.example.wz1.ec.shop.main.discover.DiscoverDelegate;
import com.example.wz1.ec.shop.main.index.IndexDelegate;
import com.example.wz1.ec.shop.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    protected int setIndex() {
        return 0;
    }

    @Override
    protected LinkedHashMap<TableBean, BaseItemBottomDelegate> getItem() {
        LinkedHashMap<TableBean,BaseItemBottomDelegate> map=new LinkedHashMap<>();
        TableBean indexbean = new TableBean("主页", "{fa-home}");
        TableBean sortbean = new TableBean("分类", "{fa-sort}");
        TableBean cartbean = new TableBean("购物", "{icon-home}");
        TableBean discoverbean = new TableBean("发现", "{icon-tianmao}");
        map.put(indexbean,new IndexDelegate());
        map.put(sortbean,new SortDelegate());
        map.put(cartbean,new CartDelegate());
        map.put(discoverbean,new DiscoverDelegate());
        return map;
    }
}
