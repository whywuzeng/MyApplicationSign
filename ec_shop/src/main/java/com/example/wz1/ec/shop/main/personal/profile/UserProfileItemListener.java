package com.example.wz1.ec.shop.main.personal.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.main.personal.list.ListItemBean;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.profile
 */

class UserProfileItemListener extends SimpleClickListener {

    private final BaseDelegate baseDelegate;
    private String[] mGenders={"男","女","保密"};

    public UserProfileItemListener(BaseDelegate delegate)
    {
        this.baseDelegate=delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position) {
        ListItemBean bean = (ListItemBean) adapter.getData().get(position);
        int mId = bean.getmId();
        switch (mId)
        {
            case 1:

                break;
            case 2:
                baseDelegate.start(bean.getDelegate());
                break;
            case 3:
                getGendersDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppCompatTextView value= view.findViewById(R.id.tv_arrow_value);
                        value.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:

                break;
                default:
                    break;
        }

    }

    public  void getGendersDialog(DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(baseDelegate.getContext());
        builder.setSingleChoiceItems(mGenders,0,listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
