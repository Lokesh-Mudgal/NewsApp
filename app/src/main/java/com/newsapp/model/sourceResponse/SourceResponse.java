package com.newsapp.model.sourceResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lokesh Mudgal on 1/12/20.
 */
public class SourceResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<Sources> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Sources> getSources() {
        return sources;
    }

    public void setSources(List<Sources> sources) {
        this.sources = sources;
    }
}
