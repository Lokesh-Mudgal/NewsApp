package com.newsapp.di;

import com.newsapp.controllers.dashboard.DashboardViewModel;
import com.newsapp.controllers.dashboard.MainActivity;
import com.newsapp.NewsApp;
import com.newsapp.di.modules.AppModule;
import com.newsapp.di.modules.NetworkModule;

import dagger.Component;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */

@AppScope
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)
public interface MainComponent {

    void inject(NewsApp app);

    void inject(MainActivity mainActivity);
    void inject(DashboardViewModel viewModel);
}
