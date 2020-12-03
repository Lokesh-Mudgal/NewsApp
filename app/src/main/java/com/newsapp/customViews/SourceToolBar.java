package com.newsapp.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.R;
import com.newsapp.model.sourceNews.Source;
import com.newsapp.model.sourceResponse.Sources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
public class SourceToolBar extends FrameLayout {

    public void setItemSelected(int item) {
        if (sourceAdapter != null)
            sourceAdapter.setItemSelected(item);
    }

    public interface SourceSelectCallback{
        void callback(Sources source, int positionSelected);
    }

    private final SourceAdapter sourceAdapter;
    private SourceSelectCallback sourceSelectCallback;

    public SourceToolBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setElevation(10f);
        this.setClickable(true);

        View inflatedView = LayoutInflater.from(context).inflate(R.layout.custom_soursce_toolbar, this, true);
        RecyclerView sourcesRv = inflatedView.findViewById(R.id.rv_sources);

        sourceAdapter = new SourceAdapter(new ArrayList<Sources>(), (sources, positionSelected) -> {
            sourceSelectCallback.callback(sources, positionSelected);
        });
        sourcesRv.setAdapter(sourceAdapter);
    }

    public void setSourceList(List<Sources> list, int sourceSelectedPosition ,SourceSelectCallback sourceSelectCallback){
        sourceAdapter.setSourcesList(list, sourceSelectedPosition);
        this.sourceSelectCallback = sourceSelectCallback;
    }
}
