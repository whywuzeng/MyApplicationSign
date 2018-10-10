package com.example.wz1.ec.core.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Administrator on 2018-10-10.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.scanner
 */

public class ScannerView extends ZBarScannerView {

    public ScannerView(Context context) {
        super(context);
    }

    public ScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new EcappViewFinderView(context);
    }
}
