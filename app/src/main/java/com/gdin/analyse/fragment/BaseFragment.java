package com.gdin.analyse.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Fragment
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    public Context mContext;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Log.d("TAG", "fragment->setUserVisibleHint");
        if (getUserVisibleHint()) { //页面可用时调用，实现懒加载
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
//        setHasOptionsMenu(true);
//        Log.d("TAG", "fragment->onCreate");
    }


    //创建该Fragment的视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initView();
        }
        unbinder = ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    //当Activity的onCreate方法返回时调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }



    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        showBtn();
        if (!isFirst)
            return;
        initData(mRootView);
        isFirst = false;


    }

    //do something
    protected void onInvisible() {
    }

    public abstract View initView();

    public abstract void initData(View view);
    public abstract void showBtn();

    //与onCreateView想对应，当该Fragment的视图被移除时调用
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder==null)
            return;
        unbinder.unbind();
    }
}