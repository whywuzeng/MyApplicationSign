package com.example.wz1.ec.shop.main.sort.content;

/**
 * Created by wz on 2018/9/29.
 */

public class BaseSectionItemEntity {
    public int goodsId;
    public String goodsThumb;

    public BaseSectionItemEntity(int goodsId, String goodsThumb, String goodsName) {
        this.goodsId = goodsId;
        this.goodsThumb = goodsThumb;
        this.goodsName = goodsName;
    }

    public String goodsName;
}
