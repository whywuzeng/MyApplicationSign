package com.example.wz1.mysigninapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.wz1.mysigninapplication.layoutmanager.ScollLinearLayoutManager;

/**
 * Created by Administrator on 2018-09-03.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class RedBookActivity  extends AppCompatActivity{

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_redbook_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        //设置adpter
        mRecyclerView.setAdapter(new SplashAdapter(this));
        //设置linnerlayoutManager
        mRecyclerView.setLayoutManager(new ScollLinearLayoutManager(this));

        mRecyclerView.smoothScrollToPosition(Integer.MAX_VALUE/2);

    }

    public class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.MyViewHodler>{

        private final Context context;
        private final LayoutInflater from;

        public SplashAdapter(Context context) {
            this.context = context;
            from = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = from.inflate(R.layout.splash_item_layout, parent, false);
            return new MyViewHodler(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {

        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class MyViewHodler extends RecyclerView.ViewHolder{

            private final View mImageView;

            public MyViewHodler(View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.imageview);
            }
        }
    }
}
