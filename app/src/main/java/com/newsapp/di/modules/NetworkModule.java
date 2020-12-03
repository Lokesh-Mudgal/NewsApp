package com.newsapp.di.modules;

import com.google.gson.GsonBuilder;
import com.newsapp.BuildConfig;
import com.newsapp.data.Api;
import com.newsapp.di.AppScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
//created to initialize retrofit instance only once and to use everywhere
@Module
public final class NetworkModule {
    private final String baseUrl;

    public NetworkModule(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Provides
    @AppScope
    public final Retrofit provideRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS);


        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);


        return new Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public final Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
