package com.dev.wedrive.service;

import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;

import com.dev.wedrive.api.ApiResponse;
import com.dev.wedrive.api.Callback;
import com.dev.wedrive.entity.ApiLocation;
import com.dev.wedrive.entity.ApiRoute;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class LocationService {


    /**
     * @param func
     */
    public void getNearestLocations(String type, final Consumer<ArrayList<ApiLocation>> func) {
        ApiService.getInstance().location().getNearestLocations(type).enqueue(new Callback<ApiResponse<ArrayList<ApiLocation>>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success)
                    func.accept((ArrayList<ApiLocation>) response.getData());

            }
        });
    }

    /**
     * Receive active passenger location if that was created before.
     * Only one active passenger location can be.
     *
     * @param func
     */
    public void getActiveLocations(Consumer<ArrayList<ApiLocation>> func) {
        ApiService.getInstance().location().getActiveLocations().enqueue(new Callback<ApiResponse<List<ApiLocation>>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success)
                    func.accept((ArrayList<ApiLocation>) response.getData());
            }
        });
    }

    /**
     * Receive active passenger location if that was created before.
     * Only one active passenger location can be.
     *
     * @param func
     */
    public void getActivePassengerLocation(Consumer<ApiLocation> func) {
        ApiService.getInstance().location().getActivePassengerLocation().enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {
                func.accept((ApiLocation) response.getData());
            }
        });
    }

    /**
     * @param route
     * @param func
     */
    public void getLocationsByRoute(ApiRoute route, final Consumer<List<ApiLocation>> func) {
        ApiService.getInstance().location().getRouteLocations(route.uuid).enqueue(new Callback<ApiResponse<List<ApiLocation>>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((List<ApiLocation>) response.getData());
                }
            }
        });
    }

    /**
     * @param uuid
     * @param func
     */
    public void getLocation(String uuid, final Consumer<ApiLocation> func) {
        ApiService.getInstance().location().getLocationInfo(uuid).enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {
                func.accept((ApiLocation) response.getData());
            }
        });

    }

    /**
     * @param location
     * @param func
     */
    public void createLocation(ApiLocation location, Consumer<ApiLocation> func) {

        ApiService.getInstance().location().createLocation(location).enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ApiLocation) response.getData());
                }
            }
        });
    }

    /**
     * @param location
     * @param func
     */
    public void updateLocation(ApiLocation location, Consumer<ApiLocation> func) {

        ApiService.getInstance().location().updateLocation(location.uuid, location).enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ApiLocation) response.getData());
                }
            }
        });
    }

    /**
     * @param location
     * @param func
     */
    public void deleteLocation(ApiLocation location, Consumer<ApiLocation> func) {
        ApiService.getInstance().location().deleteLocation(location.uuid).enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ApiLocation) response.getData());
                }
            }
        });
    }


    /**
     * Update my location
     *
     * @param latLng
     */
    public void updateMyLocation(LatLng latLng) {
        ApiService.getInstance().location().positionLocation(new ApiLocation(latLng)).enqueue(new Callback<ApiResponse<ApiLocation>>() {
            @Override
            public void onResult(ApiResponse response) {

            }
        });
    }

}
