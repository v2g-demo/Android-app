package com.demo.v2g.network;

import com.demo.v2g.model.maps.MapsResponse;
import com.demo.v2g.model.objectOnMap.ObjectsOnMapResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface V2gApi {

    @GET("maps")
    Call<MapsResponse> getAllMaps();

    @GET("maps/{object_id}/objects")
    Call<ObjectsOnMapResponse> getAllObject(@Path(value = "object_id", encoded = true) String objectId);
}
