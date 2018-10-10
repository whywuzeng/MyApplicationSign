package com.example.wz1.ec.core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.ECAppDelegate;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Administrator on 2018-10-10.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.scanner
 */

public class ScannerDelegate extends ECAppDelegate  implements ZBarScannerView.ResultHandler{

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void handleResult(Result result) {

    }
}
