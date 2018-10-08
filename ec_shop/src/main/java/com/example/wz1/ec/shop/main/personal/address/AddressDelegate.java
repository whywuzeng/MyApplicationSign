package com.example.wz1.ec.shop.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.address
 */

public class AddressDelegate extends ECAppDelegate {
    @BindView(R2.id.icon_address_add)
    IconTextView iconAddressAdd;
    @BindView(R2.id.rv_address)
    RecyclerView rvAddress;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        rvAddress.setLayoutManager(new LinearLayoutManager(_mActivity));
        RestClient address = new RestClient.RestClientBuild()
                .url("address")
                .sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        DataConverter dataConverter = new AddressConverter().setJsonData(result);
                        AddressAdapter addressAdapter = null;
                        try {
                            addressAdapter = new AddressAdapter(dataConverter.getItemEntityList());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        rvAddress.setAdapter(addressAdapter);
                    }
                }).build();
        address.get();
    }


    @OnClick(R2.id.icon_address_add)
    public void onViewClicked() {

    }
}
