package com.example.lyl.myapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author lyl
 * @date 2017/10/23.
 */

public class Demo implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
