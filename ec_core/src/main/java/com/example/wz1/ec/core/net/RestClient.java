package com.example.wz1.ec.core.net;

import android.content.Context;

import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.IRequest;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.net.back.RequestCallBack;
import com.example.wz1.ec.core.net.download.DownLoadHandle;
import com.example.wz1.ec.core.ui.ECAppLoader;
import com.example.wz1.ec.core.ui.LoadStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * //Client 客户端
 * Created by wz on 2018/9/1.
 */

public class RestClient {

    //配置各种参数  URL Parmas （map mitipart不允许map为null） ， back回调，okhttp requestBody也是参数
    private final String URL;
    private final  Map<String,Object> PARAMS;
    private final IError IERROR;
    private final IFailure IFAILURE;
    private final IRequest IREQUEST;
    private final ISucess ISUCESS;
    private final RequestBody MREQUESTBODY;
    private final LoadStyle mLoadStyle;
    private final Context context;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private RestClient(String url, Map<String, Object> params, IError ierror, IFailure ifailure, IRequest irequest, ISucess isucess, RequestBody mRequestbody, LoadStyle loadStyle, Context context,File file,String download_dir,String extension,String name) {
        this.URL = url;
        this.PARAMS = params;
        this.IERROR = ierror;
        this.IFAILURE = ifailure;
        this.IREQUEST = irequest;
        this.ISUCESS = isucess;
        this.MREQUESTBODY = mRequestbody;
        this.mLoadStyle=loadStyle;
        this.context =context;
        this.FILE=file;
        this.DOWNLOAD_DIR=download_dir;
        this.EXTENSION=extension;
        this.NAME=name;
    }

    public final void request(HttpMethod method){

        Call<String> mCall=null;

        RestService restservice = RestCreator.getRestservice();

        if (IREQUEST!=null)
        {
            IREQUEST.onRequestStart();
        }

        if (mLoadStyle != null) {
            showLoading(context, mLoadStyle);
        }
        else if (context != null) {
            showLoading(context);
        }

        switch (method)
        {
            case GET:
                 mCall = restservice.get(this.URL, this.PARAMS);
                break;
            case POST:
                mCall=restservice.post(this.URL,this.PARAMS);
                break;
            case PUT:
                mCall=restservice.put(this.URL,this.PARAMS);
                break;
            case DELETE:
                mCall=restservice.delete(this.URL,this.PARAMS);
                break;
            case POST_RAW:
                mCall= restservice.postraw(this.URL,this.MREQUESTBODY);
                break;
            case PUT_RAW:
               mCall= restservice.putraw(this.URL,this.MREQUESTBODY);
                break;
            case UPLOAD:
                //上传
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
               mCall= restservice.upload(URL,part);
                break;

            case DOWNLOAD:
                //下载

                break;
        }

        if (mCall!=null)
        {
            mCall.enqueue(new RequestCallBack(this.IERROR,this.IFAILURE,this.IREQUEST,this.ISUCESS,this.mLoadStyle));
        }
    }

    private void showLoading(Context context,LoadStyle style)
    {
        ECAppLoader.showLoading(context,style);
    }

    private void showLoading(Context context)
    {
        ECAppLoader.showLoading(context);
    }

    public final void get()
    {
        request(HttpMethod.GET);
    }

    public final void post()
    {
        request(HttpMethod.POST);
    }

    public final void postraw(){
        if (MREQUESTBODY !=null) {
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put()
    {
        if (MREQUESTBODY !=null) {
            request(HttpMethod.PUT);
        }
    }

    public final void putraw(){
        request(HttpMethod.PUT_RAW);
    }

    public final void delete()
    {
        request(HttpMethod.DELETE);
    }

    public final void upload()
    {
        request(HttpMethod.UPLOAD);
    }

    public final void download()
    {
       new DownLoadHandle(URL,PARAMS,IERROR,IFAILURE,IREQUEST,ISUCESS,DOWNLOAD_DIR,EXTENSION,NAME).download();
    }

    /**
     * 静态类 clientbuild  只允许 restClient实例化
     */
    public static class RestClientBuild {

        private  String URL;
        private Map<String, Object> PARAMS=RestCreator.params;
        private  IError IERROR;
        private  IFailure IFAILURE;
        private  IRequest IREQUEST;
        private  ISucess ISUCESS;
        private RequestBody mRequestBody;
        private LoadStyle mLoadStyle;
        private Context mContext;
        private File file;
        private  String download_dir;
        private  String extension;
        private  String name;


        public RestClientBuild() {

        }

        public final RestClientBuild url(String URL)
        {
            this.URL=URL;
            return this;
        }

        public final RestClientBuild params(WeakHashMap<String,Object> params)
        {
            this.PARAMS.putAll(params);
            return this;
        }

        public final RestClientBuild params(String key,Object values)
        {
            this.PARAMS.put(key,values);
            return this;
        }

        public final RestClientBuild raw(String json)
        {
            this.mRequestBody =RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json);
            return this;
        }

        public final RestClientBuild file(File file)
        {
            this.file=file;
            return this;
        }

        public final RestClientBuild style(LoadStyle style)
        {
            this.mLoadStyle=style;
            return this;
        }

        public final RestClientBuild context(Context context)
        {
            this.mContext=context;
            return this;
        }

        public final RestClientBuild error(IError iError)
        {
            this.IERROR=iError;
            return this;
        }

        public final RestClientBuild failure(IFailure iFailure)
        {
            this.IFAILURE=iFailure;
            return this;
        }

        public final RestClientBuild sucess(ISucess iSucess)
        {
            this.ISUCESS=iSucess;
            return this;
        }

        public final RestClientBuild request(IRequest iRequest)
        {
            this.IREQUEST=iRequest;
            return this;
        }

        public final RestClientBuild downloadDir(String download_dir){
            this.download_dir=download_dir;
            return this;
        }

        public final RestClientBuild extension(String extension)
        {
            this.extension=extension;
            return this;
        }

        public final RestClientBuild name(String name)
        {
            this.name=name;
            return this;
        }

        public final RestClient build()
        {
            RestClient restClient = new RestClient(URL, PARAMS, IERROR, IFAILURE, IREQUEST, ISUCESS, mRequestBody,mLoadStyle,mContext,file,download_dir,extension,name);
            return restClient;
        }

    }
}
