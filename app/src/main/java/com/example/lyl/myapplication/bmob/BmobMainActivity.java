package com.example.lyl.myapplication.bmob;

import com.blankj.utilcode.util.ToastUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author lyl
 * @date 2017/12/11.
 */

public class BmobMainActivity extends BaseActivity {
    private Person person;

    @Override
    public int getLayoutId() {
        return R.layout.bmob_activity;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        person = new Person();
        person.setName("lyl");
        person.setAddress("China");
        person.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                ToastUtils.showShort(s);
                if (e != null) {
                    ToastUtils.showShort("存储失败" + e.getMessage());
                } else {
                    ToastUtils.showShort("存储成功");
                }
            }
        });
    }
}
