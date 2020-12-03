package com.newsapp.controllers.article;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.newsapp.R;
import com.newsapp.base.BaseActivity;
import com.newsapp.controllers.LinkActivity;
import com.newsapp.model.sourceNews.Article;
import com.newsapp.util.DateExtension;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.newsapp.controllers.LinkActivity.EXTRA_LINK;

/**
 * Created by Lokesh Mudgal on 3/12/20.
 */
public class ArticleActivity extends BaseActivity {

    public static String EXTRA_ARTICLE = "extra_article";

    @BindView(R.id.iv_article)
    ImageView articleIv;

    @BindView(R.id.tv_title)
    TextView titleTv;

    @BindView(R.id.tv_description)
    TextView descriptionTv;

    @BindView(R.id.tv_content)
    TextView contentTv;

    @BindView(R.id.tv_url)
    TextView linkTv;

    @BindView(R.id.tv_author)
    TextView authorTv;

    @BindView(R.id.tv_published_at)
    TextView publishedAtTv;

    private ArticleViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        viewModel = new ViewModelProvider(this).get(ArticleViewModel.class);

        if (bundle != null) {
            Article article = bundle.getParcelable(EXTRA_ARTICLE);
            viewModel.article.setValue(article);
        }

        setObserver();
    }

    private void setObserver() {
        viewModel.article.observe(this, article -> {
            Picasso.get().load(article.getUrlToImage()).noFade().into(articleIv);
            titleTv.setText(article.getTitle());
            descriptionTv.setText(article.getDescription());
            contentTv.setText(article.getContent());
            publishedAtTv.setText(getString(R.string.published_on, DateExtension.dateToShow(article.getPublishedAt())));
            authorTv.setText(getString(R.string.by, article.getAuthor()));
            linkTv.setText(article.getUrl());
            String text = "<u>" + article.getUrl() + "</u>";
            linkTv.setText(Html.fromHtml(text));


            linkTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startWebViewIntent = new Intent(ArticleActivity.this, LinkActivity.class);
                    startWebViewIntent.putExtra(EXTRA_LINK, article.getUrl());
                    startActivity(startWebViewIntent);
                }
            });
        });
    }

    @Override
    public void inject() {

    }
}
