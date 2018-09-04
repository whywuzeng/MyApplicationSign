package com.example.wz1.ec.core.net.download;

import android.os.AsyncTask;

import com.example.wz1.ec.core.net.RestCreator;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.IRequest;
import com.example.wz1.ec.core.net.back.ISucess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wz on 2018/9/2.
 */

public class DownLoadHandle {
    private final String URL;
    private final Map<String,Object> PARAMS;
    private final IError IERROR;
    private final IFailure IFAILURE;
    private final IRequest IREQUEST;
    private final ISucess ISUCESS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownLoadHandle(String URL, Map<String, Object> PARAMS, IError IERROR, IFailure IFAILURE, IRequest IREQUEST, ISucess ISUCESS, String DOWNLOAD_DIR, String EXTENSION, String NAME) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.IERROR = IERROR;
        this.IFAILURE = IFAILURE;
        this.IREQUEST = IREQUEST;
        this.ISUCESS = ISUCESS;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void download() {
        RestCreator.getRestservice().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SaveFileTask saveFileTask = new SaveFileTask(IREQUEST, ISUCESS);
                if (response.isSuccessful())
                {
                    saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,response.body(),NAME);
                    if (saveFileTask.isCancelled())
                    {
                        if (IREQUEST!=null)
                        {
                            IREQUEST.onRequestComplete();
                        }
                    }
                }else {
                    if (IERROR!=null)
                    {
                        IERROR.onError(response.code(),response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (IFAILURE!=null)
                {
                    IFAILURE.onFailure(t);
                }
            }
        });
    }
}
