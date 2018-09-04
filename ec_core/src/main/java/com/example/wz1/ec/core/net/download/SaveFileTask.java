package com.example.wz1.ec.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.wz1.ec.core.app.ECApp;
import com.example.wz1.ec.core.net.back.IRequest;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.utils.file.FileUtil;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * Created by wz on 2018/9/2.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest IREQUEST;
    private final ISucess ISUCESS;

    public SaveFileTask(IRequest IREQUEST, ISucess ISUCESS) {
        this.IREQUEST = IREQUEST;
        this.ISUCESS = ISUCESS;
    }

    @Override
    protected File doInBackground(Object[] objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        ResponseBody body = (ResponseBody) objects[2];
        String name = (String) objects[3];

        if (TextUtils.isEmpty(downloadDir))
        {
            downloadDir="downloadDir";
        }
        if (TextUtils.isEmpty(extension))
        {
            extension="";
        }
        File file;
        if (TextUtils.isEmpty(name))
        {
            file= FileUtil.writeToDisk(body.byteStream(),downloadDir,extension.toUpperCase(),extension);
        }else {
            file= FileUtil.writeToDisk(body.byteStream(),downloadDir,name);
        }

        return file;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (IREQUEST!=null)
        {
            IREQUEST.onRequestComplete();
        }
        if (ISUCESS!=null)
        {
            ISUCESS.onSucess(file.getPath());
        }

        autoInstallApk(file);
    }

    public void autoInstallApk(File file){
        //判断是否是 apk文件
        //然后去 intent
        if (FileUtil.getExtension(file.getPath()).equals("apk"))
        {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            ECApp.getApplicationContext().startActivity(intent);
        }
    }
}
