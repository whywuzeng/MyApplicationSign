package com.example.wz1.ec.shop.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz 购物车
 * <p>
 * com.example.wz1.ec.shop.main.cart
 */

public class SortDelegate extends BaseItemBottomDelegate {
    private static final String TAG = "SortDelegate";
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public Object setLayout() {

        return R.layout.sort_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new SortAdapter());
    }

    class SortAdapter extends RecyclerView.Adapter<SortAdapter.ViewHolder> {

        private ArrayList<Integer> list = new ArrayList<>();

        private void initData() {
            for (int i = 0; i < 20; i++) {
                list.add(i);
            }
        }

        public SortAdapter() {
            initData();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = getLayoutInflater().inflate(R.layout.sort_item_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(rootView);
            Log.e(TAG, "onCreateViewHolder: getChildCount : "+parent.getChildCount() +" item count :"+ getItemCount());
//            getLayoutPosition(): "+viewHolder.getLayoutPosition()+" getAdapterPosition()  "+viewHolder.getAdapterPosition()

            return new ViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.button.setText(list.get(position)+"");
            Log.e(TAG, "onBindViewHolder : "+holder.toString()+"position :"+position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R2.id.button)
            AppCompatButton button;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
