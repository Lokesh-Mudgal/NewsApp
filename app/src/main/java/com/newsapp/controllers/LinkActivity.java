package com.newsapp.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.newsapp.R;
import com.newsapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lokesh Mudgal on 3/12/20.
 */
public class LinkActivity extends BaseActivity {
    public static String EXTRA_LINK = "extra_link";

    @BindView(R.id.wv_content)
    WebView contentWv;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        ButterKnife.bind(this);

        WebSettings settings = contentWv.getSettings();
        settings.setJavaScriptEnabled(true);
        contentWv.setWebChromeClient(new WebChromeClient());
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);

        contentWv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        String link = getIntent().getExtras().getString(EXTRA_LINK);

        contentWv.loadUrl(link);

    }

    @Override
    public void inject() {

    }
}
