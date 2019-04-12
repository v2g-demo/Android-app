package com.demo.v2g.model.objectsOnMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectsOnMapResponse {
    @SerializedName("content")
    @Expose
    private List<ObjectsContent> content = null;


    public List<ObjectsContent> getContent() {
        return content;
    }

    public void setContent(List<ObjectsContent> content) {
        this.content = content;
    }

}