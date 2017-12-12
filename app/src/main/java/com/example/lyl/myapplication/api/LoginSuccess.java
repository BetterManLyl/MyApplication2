package com.example.lyl.myapplication.api;

/**
 * @author lyl
 * @date 2017/11/19.
 */

public class LoginSuccess {

    /**
     * result : true
     * message : 登录成功
     * token : 10-5b51d8beb8b044a6852806b4c1582090
     */

    private boolean result;
    private String message;
    private String token;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
