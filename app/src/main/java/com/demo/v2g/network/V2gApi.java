package com.demo.v2g.network;

import com.demo.v2g.model.maps.MapsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface V2gApi {

    @GET("maps")
    Call<MapsResponse> getAllMaps();
}
