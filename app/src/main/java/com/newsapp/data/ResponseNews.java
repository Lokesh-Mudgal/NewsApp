package com.newsapp.data;

/**
 * Created by Lokesh Mudgal on 2/12/20.
 */
//created this response class to handle api status and response type properly by view model
public class ResponseNews<T> {
    T data;
    ResponseState state;

    public ResponseNews(T data, ResponseState state) {
        this.data = data;
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseState getState() {
        return state;
    }

    public void setState(ResponseState state) {
        this.state = state;
    }
}
