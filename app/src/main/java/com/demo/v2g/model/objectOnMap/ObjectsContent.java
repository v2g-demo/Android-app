package com.demo.v2g.model.objectOnMap;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectsContent {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("placeId")
    @Expose
    private String placeId;
    @SerializedName("formattedAddress")
    @Expose
    private String formattedAddress;
    @SerializedName("reference")
    @Expose
    private Object reference;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("rotationAngle")
    @Expose
    private Integer rotationAngle;
    @SerializedName("content")
    @Expose
    private List<Object> content = null;
    @SerializedName("links")
    @Expose
    private List<ObjectsLinks> links = null;

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

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

    public List<ObjectsLinks> getLinks() {
        return links;
    }

    public void setLinks(List<ObjectsLinks> links) {
        this.links = links;
    }

}
