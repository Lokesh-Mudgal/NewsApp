package com.newsapp.di.modules;

import android.content.Context;

import androidx.annotation.NonNull;

import com.newsapp.NewsApp;
import com.newsapp.di.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */

@Module
public final class AppModule {
    private final NewsApp app;

    @Provides
    @AppScope
    @NonNull
    public final Context provideAppContext(){
        return (Context)this.app;
    }

    @Provides
    @AppScope
    @NonNull
    public final NewsApp provideApp(){
        return this.app;
    }


    public AppModule(@NonNull NewsApp app){
        this.app = app;
    }
}
