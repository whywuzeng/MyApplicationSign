package com.example.wz1.ec.shop.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.example.wz1.ec.shop.launcher.ScrollerDelegate;
import com.joanzapata.iconify.widget.IconButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-05.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.sign
 */

public class SignInDelegate extends CheckDelegate {

    @BindView(R2.id.edit_signup_name)
    TextInputEditText editSignupName;
    @BindView(R2.id.edit_signup_password)
    TextInputEditText editSignupPassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.ibtn_link_wechat)
    IconButton ibtnLinkWechat;
    private ISignInListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener= (ISignInListener) _mActivity;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signin;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }

    public boolean checkFrom(){
        boolean ischeck=true;
        String editSignName = editSignupName.getText().toString();
        if (TextUtils.isEmpty(editSignName))
        {
            editSignupName.setError("账号不能为空");
            ischeck=false;
        }
        String editSignPassword= editSignupPassword.getText().toString();
        if (TextUtils.isEmpty(editSignPassword)||editSignPassword.length()<6)
        {
            editSignupPassword.setError("密码输入有误");
            ischeck=false;
        }
        return ischeck;
    }

    //登录
    @OnClick(R2.id.btn_sign_up)
    public void onViewClicked() {
        if (checkFrom()){
            RestClient build = new RestClient.RestClientBuild().url("list")
                    .params("name", editSignupName.getText().toString())
                    .params("password", editSignupPassword.getText().toString())
                    .context(_mActivity)
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            ToastUtils.showShort(message);
                        }
                    }).failure(new IFailure() {
                        @Override
                        public void onFailure(Throwable t) {
                            ToastUtils.showShort(t.getLocalizedMessage());
                        }
                    }).sucess(new ISucess() {
                        @Override
                        public void onSucess(String result) {
                            SignDataHanlder.SignIn(result,listener);
                        }
                    }).build();

            build.post();
        }
    }

    //连接微信
    @OnClick(R2.id.ibtn_link_wechat)
    public void onLinkWechatClicked() {
        // TODO: 2018/9/8 测试 singleTask作用  再测试下 singleTop
        getSupportDelegate().start(new ScrollerDelegate(),SINGLETASK);
    }

    //去注册
    @OnClick(R2.id.btn_sign_in)
    public void onBtnSignInClicked(){
        getSupportDelegate().start(new SignUpDelegate(),SINGLETASK);
    }
}
