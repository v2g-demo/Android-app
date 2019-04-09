package com.demo.v2g.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("zoom")
    @Expose
    private Integer zoom;
    @SerializedName("center")
    @Expose
    private Center center;
    @SerializedName("bounds")
    @Expose
    private Object bounds;
    @SerializedName("content")
    @Expose
    private List<Object> content = null;
    @SerializedName("links")
    @Expose
    private List<ContentLinks> links = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Object getBounds() {
        return bounds;
    }

    public void setBounds(Object bounds) {
        this.bounds = bounds;
    }

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

    public List<ContentLinks> getLinks() {
        return links;
    }

    public void setLinks(List<ContentLinks> links) {
        this.links = links;
    }

}