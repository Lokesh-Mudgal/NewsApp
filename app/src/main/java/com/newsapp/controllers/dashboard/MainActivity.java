package com.newsapp.controllers.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.NewsApp;
import com.newsapp.R;
import com.newsapp.base.BaseActivity;
import com.newsapp.controllers.article.ArticleActivity;
import com.newsapp.customViews.SourceToolBar;
import com.newsapp.model.sourceNews.Article;
import com.newsapp.model.sourceNews.SourceNewsResponse;
import com.newsapp.model.sourceResponse.SourceResponse;
import com.newsapp.model.sourceResponse.Sources;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lokesh Mudgal on 3/12/20.
 */

public class MainActivity extends BaseActivity {

    public static String EXTRA_ARTICLE = "extra_article";

    private DashboardViewModel viewModel;
    private SourceNewsAdapter sourceNewsAdapter;

    @BindView(R.id.source_toolbar)
    SourceToolBar sourceToolBar;

    @BindView(R.id.rv_source_news)
    RecyclerView sourceNewsArticleRv;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        setObserver();

        sourceNewsAdapter = new SourceNewsAdapter(new ArrayList<Article>(), article -> {
            Intent openArticleIntent = new Intent(this, ArticleActivity.class);
            openArticleIntent.putExtra(EXTRA_ARTICLE, article);
            startActivity(openArticleIntent);
        });
        sourceNewsArticleRv.setAdapter(sourceNewsAdapter);
        viewModel.getAllSources(getString(R.string.api_key));

    }

    private void setObserver() {

        viewModel.sourceResponse.observe(this, sourceResponse -> {
            switch (sourceResponse.getState()) {
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    SourceResponse response = sourceResponse.getData();
                    sourceToolBar.setSourceList(response.getSources(), viewModel.getSourceSelectedPosition(), (source, positionSelected) -> viewModel.setSourceSelected(source, positionSelected));
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, getString(R.string.api_failed_error), Toast.LENGTH_SHORT).show();
                    break;
            }

        });

        viewModel.sourceSelectedPosition.observe(this, item -> {
            sourceToolBar.setItemSelected(item);
        });

        viewModel.sourceSelected.observe(this, this::callForSourceNews);

        viewModel.sourceNewsResponse.observe(this, sourceNewsResponse -> {
            switch (sourceNewsResponse.getState()) {
                case LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    progressBar.setVisibility(View.GONE);
                    SourceNewsResponse response = sourceNewsResponse.getData();
                    sourceNewsAdapter.setSourcesNewsList(response.getArticles());
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, getString(R.string.api_failed_error), Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void callForSourceNews(Sources sources) {
        viewModel.getSourceAllNews(sources.getId(), getString(R.string.api_key));
    }


    @Override
    public void inject() {
        NewsApp.getMainComponent().inject(this);
    }

}
