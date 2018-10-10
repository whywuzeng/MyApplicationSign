package com.example.wz1.ec.core.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by Administrator on 2018-10-10.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.scanner
 */

public class EcappViewFinderView extends ViewFinderView {
    public EcappViewFinderView(Context context) {
        super(context);
    }

    public EcappViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder=true;
        mBorderPaint.setColor(Color.YELLOW);
    }

}
