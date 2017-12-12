package com.example.lyl.myapplication.tablayout.tablayout_viewpage_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lyl
 * @date 2017/12/8.
 * 懒加载
 */

public abstract class BaseLazyFragment extends Fragment {
    Unbinder unbinder;

    public static final String TAG = "lyl1";
    private String className = this.getClass().getSimpleName();

    private boolean isViewCreated = false;
    private boolean isLoadData = false;

    private boolean isVisable = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        LogUtils.eTag(BaseLazyFragment.TAG, className + ",onCreateView,");
        initView(view);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisable = true;
            loadData();
        } else {
            isVisable = false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    public void loadData() {
        if (isViewCreated && isVisable) {
            queryData();
        }
    }

    private void queryData() {
        ToastUtils.showShort("查询当前页数据");
        LogUtils.eTag(BaseLazyFragment.TAG, className + ",queryData,");
        isLoadData = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtils.eTag(BaseLazyFragment.TAG, className + ",onViewCreated,");
        isViewCreated = true;
        if (!isLoadData) {
            loadData();
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.eTag(BaseLazyFragment.TAG, className + ",onDestroy,");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        isLoadData = false;
        unbinder.unbind();
    }

    public void initView(View view) {

    }


    public abstract int getLayoutId();


    public static MyFragment _instanceMyFragment(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        myFragment.setArguments(bundle);
        return myFragment;
    }
}
