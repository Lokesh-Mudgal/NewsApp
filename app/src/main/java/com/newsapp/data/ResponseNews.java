package com.newsapp.data;

/**
 * Created by Lokesh Mudgal on 2/12/20.
 */
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
