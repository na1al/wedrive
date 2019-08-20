package com.dev.wedrive.entity;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class ApiLocation implements ApiLocationInterface, TypeInterface {

    @SerializedName("uuid")
    @Getter
    public String uuid;

    @SerializedName("user_id")
    @Getter
    public Integer userId;

    @SerializedName("route_uuid")
    public String routeUuid;

    @SerializedName("type")
    @Getter
    public String type;

    @SerializedName("time")
    @Getter
    public String time;

    @SerializedName("gap")
    @Getter
    public String gap;

    @SerializedName("latitude")
    @Getter
    public double latitude;

    @SerializedName("longitude")
    @Getter
    public double longitude;

    /**
     *
     */
    public ApiLocation() {

    }

    /**
     * @param latLng
     */
    public ApiLocation(LatLng latLng) {
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
    }

    /**
     * @param latLng
     * @param route
     */
    public ApiLocation(LatLng latLng, ApiRoute route) {
        this.routeUuid = route.uuid;
        this.type = route.type + "_location";
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
    }
    
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

}