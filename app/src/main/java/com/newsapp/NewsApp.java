package com.newsapp;

import android.app.Application;
import android.content.Context;
import com.newsapp.di.DaggerMainComponent;
import com.newsapp.di.MainComponent;
import com.newsapp.di.modules.AppModule;
import com.newsapp.di.modules.NetworkModule;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */


public final class NewsApp extends Application {

    private static MainComponent mainComponent;

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();

        mainComponent = DaggerMainComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.NEWS_API_URL))
                .build();

        mainComponent.inject(this);
    }
}
