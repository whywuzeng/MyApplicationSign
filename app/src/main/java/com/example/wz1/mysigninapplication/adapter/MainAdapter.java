package com.example.wz1.mysigninapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wz1.mysigninapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2018-08-29.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.adapter
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public MainAdapter(Context context, List<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
    }

    /**
     * 增加数据
     */
    public void addData(int position) {
        mDatas.add(position, "add");
        notifyItemInserted(position);//注意这里
    }

    /**
     * 移除数据
     */
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);//注意这里
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}

