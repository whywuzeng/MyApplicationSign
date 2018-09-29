package com.example.wz1.ec.shop.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by wz on 2018/9/29.
 */

public class BaseSection extends SectionEntity<BaseSectionItemEntity> {

    private int id;

    public BaseSection(boolean isHeader, String header, int id) {
        super(isHeader, header);
        this.id = id;
    }

    public BaseSection(BaseSectionItemEntity baseSectionItemEntity) {
        super(baseSectionItemEntity);
    }
}
