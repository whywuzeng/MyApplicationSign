package com.example.wz1.ec.shop.sign;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018-09-05.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.sign
 */

public class SignUpDelegate extends ECAppDelegate {


    @BindView(R2.id.edit_signup_name)
    TextInputEditText editSignupName;
    @BindView(R2.id.edit_signup_email)
    TextInputEditText editSignupEmail;
    @BindView(R2.id.edit_signup_phone)
    TextInputEditText editSignupPhone;
    @BindView(R2.id.edit_signup_password)
    TextInputEditText editSignupPassword;
    @BindView(R2.id.edit_signup_re_password)
    TextInputEditText editSignupRePassword;
    Unbinder unbinder;
    private ISignInListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener= (ISignInListener) _mActivity;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_signup;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
    }

    public void checkFrom() {
        Editable nameText = editSignupName.getText();
        Editable emailText = editSignupEmail.getText();
        Editable phoneText = editSignupPhone.getText();
        Editable passwordText = editSignupPassword.getText();
        Editable rePassText = editSignupRePassword.getText();
        if (TextUtils.isEmpty(nameText)) {
            editSignupName.setError("姓名不能为空", getDrawable());
        }
        if (TextUtils.isEmpty(emailText) || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            editSignupEmail.setError("邮箱格式有误", getDrawable());
        }

        if (TextUtils.isEmpty(phoneText) || !Patterns.PHONE.matcher(phoneText).matches()) {
            editSignupPhone.setError("手机格式有误", getDrawable());
        }

        if (TextUtils.isEmpty(passwordText) || passwordText.length() < 6) {
            editSignupPassword.setError("电话号码不能为空，不能少于6位");
        }
        if (TextUtils.isEmpty(rePassText) || rePassText.toString().equals(passwordText.toString())) {
            editSignupRePassword.setError("保持密码一致");
        }
    }

    private Drawable getDrawable() {
        return getProxyActivity().getResources().getDrawable(R.drawable.shape_dot_grey);
    }

    @OnClick(R2.id.btn_sign_up)
    public void signUpOnclick(View view){
        checkFrom();
        Editable nameText = editSignupName.getText();
        Editable emailText = editSignupEmail.getText();
        Editable phoneText = editSignupPhone.getText();
        Editable passwordText = editSignupPassword.getText();
        Editable rePassText = editSignupRePassword.getText();
        WeakHashMap<String,Object> params=new WeakHashMap<>();
        params.put("name",nameText.toString());
        params.put("email",emailText.toString());
        params.put("phone",phoneText.toString());
        params.put("password",passwordText.toString());
        RestClient build = new RestClient.RestClientBuild().url("/list")
                .params(params)
                .context(getProxyActivity())
                .sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        SignDataHanlder.SignUp(result,listener);
                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                }).failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {

                    }
                }).build();
        build.post();

    }

    @OnClick(R2.id.tv_link_sign_in)
    public void linkSignInClick(View view)
    {

    }
}
