package com.example.lyl.myapplication.retrofit;

/**
 * @author lyl
 * @date 2017/11/24.
 */

public class BaseResult {
    private String message;
    private boolean result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
