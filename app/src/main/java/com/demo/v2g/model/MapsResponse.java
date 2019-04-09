package com.demo.v2g.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapsResponse {

    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    @SerializedName("content")
    @Expose
    private List<Content> content = null;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}