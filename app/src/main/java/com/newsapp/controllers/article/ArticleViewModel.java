package com.newsapp.controllers.article;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.newsapp.model.sourceNews.Article;

/**
 * Created by Lokesh Mudgal on 3/12/20.
 */
public class ArticleViewModel extends ViewModel {

    final MutableLiveData<Article> article = new MutableLiveData<Article>();
}
