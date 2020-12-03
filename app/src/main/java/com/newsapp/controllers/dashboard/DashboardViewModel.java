package com.newsapp.controllers.dashboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.newsapp.NewsApp;
import com.newsapp.data.Api;
import com.newsapp.data.ResponseNews;
import com.newsapp.data.ResponseState;
import com.newsapp.model.sourceNews.SourceNewsResponse;
import com.newsapp.model.sourceResponse.SourceResponse;
import com.newsapp.model.sourceResponse.Sources;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lokesh Mudgal on 2/12/20.
 */
public final class DashboardViewModel extends ViewModel {

    @Inject
    Api api;

    private final MutableLiveData<ResponseNews<SourceResponse>> _sourceResponse = new MutableLiveData<ResponseNews<SourceResponse>>();
    final LiveData<ResponseNews<SourceResponse>> sourceResponse;

    final MutableLiveData<Integer> sourceSelectedPosition = new MutableLiveData<Integer>();

    private final MutableLiveData<ResponseNews<SourceNewsResponse>> _sourceNewsResponse = new MutableLiveData<ResponseNews<SourceNewsResponse>>();
    final LiveData<ResponseNews<SourceNewsResponse>> sourceNewsResponse;

    final MutableLiveData<Sources> sourceSelected = new MutableLiveData<Sources>();

    public DashboardViewModel() {
        sourceResponse = _sourceResponse;
        sourceSelectedPosition.setValue(-1);
        sourceNewsResponse = _sourceNewsResponse;
        NewsApp.getMainComponent().inject(this);
    }

    final void getAllSources(String apiKey) {
        _sourceResponse.setValue(new ResponseNews<SourceResponse>(null, ResponseState.LOADING));
        api.getAllSources(apiKey)
                .enqueue(new Callback<SourceResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SourceResponse> call,@NonNull Response<SourceResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            _sourceResponse.setValue(new ResponseNews<SourceResponse>(response.body(), ResponseState.SUCCESS));
                        } else
                            _sourceResponse.setValue(new ResponseNews<>(null, ResponseState.ERROR));
                    }

                    @Override
                    public void onFailure(@NonNull Call<SourceResponse> call, @NonNull Throwable t) {
                        _sourceResponse.setValue(new ResponseNews<>(null, ResponseState.ERROR));
                    }
                });
    }

    final void getSourceAllNews(String sources, String apiKey) {
        _sourceNewsResponse.setValue(new ResponseNews<SourceNewsResponse>(null, ResponseState.LOADING));
        api.getSourceAllNews(sources, apiKey)
                .enqueue(new Callback<SourceNewsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SourceNewsResponse> call, @NonNull Response<SourceNewsResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            _sourceNewsResponse.setValue(new ResponseNews<SourceNewsResponse>(response.body(), ResponseState.SUCCESS));
                        } else
                            _sourceNewsResponse.setValue(new ResponseNews<SourceNewsResponse>(null, ResponseState.ERROR));
                    }

                    @Override
                    public void onFailure(@NonNull Call<SourceNewsResponse> call,@NonNull Throwable t) {
                        _sourceNewsResponse.setValue(new ResponseNews<SourceNewsResponse>(null, ResponseState.ERROR));
                    }
                });
    }

    public int getSourceSelectedPosition() {
        if (sourceSelectedPosition.getValue() != null)
            return sourceSelectedPosition.getValue();
        else return 0;
    }

    void setSourceSelected(Sources sources, int position) {
        sourceSelected.setValue(sources);
        sourceSelectedPosition.setValue(position);
    }

}
