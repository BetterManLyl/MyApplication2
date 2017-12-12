package com.example.lyl.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyl on 2017/9/22.
 * 工单记录
 */

public class WorkSheetRecord implements Serializable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * handler : 1
         * picUrl : ["adada","adfad","adfadf","adfasfd"]
         * handleTime : 1505813792000
         * handleType : 完成工单
         * listCode : 201709191
         * handleResult : 完成
         * assist : ["runaadmin","administrator2016"]
         * title : 2017.09.19   17:36
         */

        private int handler;
        private String title;
        private String handleType;
        private String handleTime;
        private List<RecordInfo> recordInfoList;


        public List<RecordInfo> getRecordInfoList() {
            return recordInfoList;
        }

        public void setRecordInfoList(List<RecordInfo> recordInfoList) {
            this.recordInfoList = recordInfoList;
        }

        public String getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
        }


        public String getListCode() {
            return listCode;
        }

        public void setListCode(String listCode) {
            this.listCode = listCode;
        }

        private String listCode;

        public String getHandleType() {
            return handleType;
        }

        public void setHandleType(String handleType) {
            this.handleType = handleType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getHandler() {
            return handler;
        }

        public void setHandler(int handler) {
            this.handler = handler;
        }
        public static class RecordInfo {

            private String handleResult;

            private List<String> picUrl;
            private List<String> assist;


            public String getHandleResult() {
                return handleResult;
            }

            public void setHandleResult(String handleResult) {
                this.handleResult = handleResult;
            }


            public List<String> getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(List<String> picUrl) {
                this.picUrl = picUrl;
            }

            public List<String> getAssist() {
                return assist;
            }

            public void setAssist(List<String> assist) {
                this.assist = assist;
            }
        }

    }


}
