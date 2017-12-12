package com.example.lyl.myapplication.rxandroid;

import android.content.pm.ResolveInfo;

import com.blankj.utilcode.util.AppUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyl
 * @date 2017/11/16.
 */

class AppInfoRich implements Serializable {
    private List<AppUtils.AppInfo> list = new ArrayList<>();
    private ResolveInfo resolveInfo;

    public AppInfoRich(ResolveInfo resolveInfo) {
        this.resolveInfo=resolveInfo;
    }

    public ResolveInfo getResolveInfo() {
        return resolveInfo;
    }

    public void setResolveInfo(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
    }

    public List<AppUtils.AppInfo> getList() {
        return list;
    }

    public void setList(List<AppUtils.AppInfo> list) {
        this.list = list;
    }
}
