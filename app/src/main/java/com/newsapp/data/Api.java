package com.newsapp.data;

import com.newsapp.model.sourceNews.SourceNewsResponse;
import com.newsapp.model.sourceResponse.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
public interface Api {

    @GET("/v2/sources")
    Call<SourceResponse> getAllSources(@Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<SourceNewsResponse> getSourceAllNews(@Query("sources") String source, @Query("apiKey") String apiKey);

}
