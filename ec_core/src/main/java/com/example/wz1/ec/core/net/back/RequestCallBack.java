package com.example.wz1.ec.core.net.back;

import android.os.Handler;

import com.example.wz1.ec.core.ui.ECAppLoader;
import com.example.wz1.ec.core.ui.LoadStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wz on 2018/9/1.
 */

public class RequestCallBack  implements Callback<String> {

    public RequestCallBack(IError ierror, IFailure ifailure, IRequest irequest, ISucess isucess,LoadStyle style) {
        this.IERROR = ierror;
        this.IFAILURE = ifailure;
        this.IREQUEST = irequest;
        this.ISUCESS = isucess;
        this.loadStyle=style;
    }

    private final IError IERROR;
    private final IFailure IFAILURE;
    private final IRequest IREQUEST;
    private final ISucess ISUCESS;
    private final LoadStyle loadStyle;
    private static final Handler HANDLER=new Handler();


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (!response.isSuccessful())
        {
            //onError  如果是response.errorBody
            if (IERROR!=null)
                IERROR.onError(response.code(),response.message());
        }else if (call.isExecuted()){
            if (ISUCESS!=null) {
                ISUCESS.onSucess(response.body());
            }
            if (IREQUEST!=null)
            {
                IREQUEST.onRequestComplete();
            }
        }
        stopLoading();
    }

    private void stopLoading() {
//        if (loadStyle!=null)
//        {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ECAppLoader.stopLoading();
                }
            },1000);
//        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
       if (IFAILURE!=null) {
           IFAILURE.onFailure(t);
       }

       if (IREQUEST!=null)
           IREQUEST.onRequestComplete();

       stopLoading();
    }
}
