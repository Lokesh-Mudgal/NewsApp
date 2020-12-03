package com.newsapp.customViews;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.R;
import com.newsapp.model.sourceResponse.Sources;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {

    public interface SourceCallback {
        void callback(Sources sources, int positionSelected);
    }

    private List<Sources> sourcesList;
    private final SourceCallback mSourceCallback;
    private int positionSelected = -1;

    public void setSourcesList(List<Sources> sourcesList, int sourceSelectedPosition) {
        this.sourcesList = sourcesList;
        positionSelected = sourceSelectedPosition;
        notifyDataSetChanged();
    }

    public void setItemSelected(int item) {
        positionSelected = item;
        notifyDataSetChanged();
    }

    public SourceAdapter(ArrayList<Sources> sourcesList, SourceCallback mSourceCallback) {
        this.sourcesList = sourcesList;
        this.mSourceCallback = mSourceCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sources sources = sourcesList.get(position);

        holder.sourceNameTv.setText(sources.getName());

        if (positionSelected == position) {
            holder.sourceCv.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.design_default_color_primary));
            holder.sourceNameTv.setTextColor(Color.WHITE);
        } else {
            holder.sourceCv.setCardBackgroundColor(Color.WHITE);
            holder.sourceNameTv.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(view -> {
            positionSelected = position;
            mSourceCallback.callback(sources, positionSelected);
        });
    }

    @Override
    public int getItemCount() {
        if (sourcesList.size() > 10)
            return 10;
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_source_name)
        TextView sourceNameTv;

        @BindView(R.id.source_cv)
        CardView sourceCv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
