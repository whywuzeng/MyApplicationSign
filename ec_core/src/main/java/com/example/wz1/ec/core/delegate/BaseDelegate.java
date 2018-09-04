package com.example.wz1.ec.core.delegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wz1.ec.core.activites.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.Base.MySupportFragment;

/**
 * Created by wz on 2018/9/1.
 */

public abstract class BaseDelegate extends MySupportFragment {

    private View rootView;
    public abstract Object setLayout();
    private Unbinder mUnbinder=null;
    public abstract void BindView(@Nullable Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //判断是否为integer
        //判断是否为view
        //然后加载bni 注解
        //onBindView是新加载 不准子类重写onCreateView么

        if (setLayout() instanceof Integer)
        {
            Integer integer = (Integer) setLayout();
            rootView = inflater.inflate(integer, container, false);
        }else if (setLayout() instanceof View)
        {
            rootView= (View) setLayout();
        }

        if (rootView!=null)
        {
            mUnbinder= ButterKnife.bind(this,rootView);
            BindView(savedInstanceState);
        }
        return rootView;
    }

    protected ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null)
        {
            mUnbinder.unbind();
        }
    }
}
