package com.newsapp.controllers.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.R;
import com.newsapp.model.sourceNews.Article;
import com.newsapp.util.DateExtension;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lokesh Mudgal on 2/12/20.
 */
class SourceNewsAdapter extends RecyclerView.Adapter<SourceNewsAdapter.ViewHolder>{

    public interface NewsCallback{
        void callback(Article article);
    }

    private List<Article> articleList;
    private NewsCallback newsCallback;

    public SourceNewsAdapter(List<Article> articleList, NewsCallback newsCallback) {
        this.articleList = articleList;
        this.newsCallback = newsCallback;
    }

    public void setSourcesNewsList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_article,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articleList.get(position);
        loadImage(article.getUrlToImage(), holder.articleIv);
        holder.title.setText(article.getTitle());
        holder.author.setText(holder.itemView.getContext().getString(R.string.by, article.getAuthor()));
        holder.publishedAtTv.setText(holder.itemView.getContext().getString(R.string.published_on, DateExtension.dateToShow(article.getPublishedAt())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsCallback.callback(article);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    private void loadImage(String url, ImageView imageView){
        Picasso.get().load(url).noFade().into(imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_article)
        ImageView articleIv;

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_author)
        TextView author;

        @BindView(R.id.tv_published_at)
        TextView publishedAtTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
