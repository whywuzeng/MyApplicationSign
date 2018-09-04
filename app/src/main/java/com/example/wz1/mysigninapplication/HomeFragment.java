package com.example.wz1.mysigninapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wz1.ec.core.Custom.CustomLayoutManager;
import com.example.wz1.mysigninapplication.Base.BaseMainFragment;
import com.example.wz1.mysigninapplication.Bean.Article;
import com.example.wz1.mysigninapplication.adapter.HomeAdapter;
import com.example.wz1.mysigninapplication.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;

/**
 * Created by Administrator on 2018-08-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class HomeFragment extends BaseMainFragment {

    private String[] mTitles;

    private String[] mContents;

    private Toolbar mToolbar;
    private RecyclerView mRecy;
    private HomeAdapter mAdapter;
    private Button button;
    private Button button11;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        button =(Button)view.findViewById(R.id.button);
        button11 =(Button)view.findViewById(R.id.button11);

        mTitles = getResources().getStringArray(R.array.array_title);
        mContents = getResources().getStringArray(R.array.array_content);

        mAdapter = new HomeAdapter(_mActivity);
        CustomLayoutManager manager = new CustomLayoutManager();
        mRecy.setLayoutManager(manager);
        mRecy.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                start(DetailFragment.newInstance(mAdapter.getItem(position).getTitle()));
            }
        });

        // Init Datas
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int index = (int) (Math.random() * 3);
            Article article = new Article(mTitles[index], mContents[index]);
            articleList.add(article);
        }
        mAdapter.setDatas(articleList);

        ((ISupportActivity) _mActivity).setFragmentAnimator(new DefaultHorizontalAnimator());
        test1();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postInvalidate();
            }
        });
    }

    /**
     * 类似于 Activity的 onNewIntent()
     */
    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);

        Toast.makeText(_mActivity, args.getString("from"), Toast.LENGTH_SHORT).show();
    }

    public void test1(){
        //android.support.design.widget.CoordinatorLayout$LayoutParams
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                mRecy.setLayoutParams(new CoordinatorLayout.LayoutParams(500,400));
////                mRecy.postInvalidate();
//                mRecy.invalidate();
            }
        },2000);

//        postInvalidate();

    }

    private void postInvalidate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                mRecy.setLayoutParams(new CoordinatorLayout.LayoutParams(500,400));
//                mRecy.postInvalidate();
                mRecy.invalidate();
                long id = Thread.currentThread().getId();
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }).start();
    }
}
