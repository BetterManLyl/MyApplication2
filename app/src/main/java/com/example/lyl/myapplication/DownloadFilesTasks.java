package com.example.lyl.myapplication;

import android.app.DownloadManager;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by lyl on 2017/7/14.
 */

public class DownloadFilesTasks extends AsyncTask <URL ,Integer,Long>{

    @Override
    protected Long doInBackground(URL... params) {

        int count=params.length;
        long totalSize=0;
        for (int i = 0; i < count; i++) {

        }
        return totalSize;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    public void ss(){
        new DownloadFilesTasks().execute();
    }
}
