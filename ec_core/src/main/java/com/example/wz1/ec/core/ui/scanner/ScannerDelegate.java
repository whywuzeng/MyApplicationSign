package com.example.wz1.ec.core.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.utils.callback.CallBackListener;
import com.example.wz1.ec.core.utils.callback.CallBackManager;
import com.example.wz1.ec.core.utils.callback.CallBackType;

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

    private ScannerView scannerView;

    @Override
    public Object setLayout() {
        return scannerView;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (scannerView==null)
        {
            scannerView = new ScannerView(_mActivity);
        }
        scannerView.setAutoFocus(true);
        scannerView.setResultHandler(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (scannerView!=null)
        {
            scannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (scannerView!=null)
        {
            scannerView.stopCameraPreview();
            scannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        CallBackListener callBack = CallBackManager.getInstances().getCallBack(CallBackType.ON_SCAN);
        if (callBack!=null)
        {
            callBack.execute(result.getContents());
        }
        pop();
    }
}
