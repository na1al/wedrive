package com.dev.wedrive.service;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;

import com.dev.wedrive.api.ApiResponse;
import com.dev.wedrive.api.Callback;
import com.dev.wedrive.data.Pager;
import com.dev.wedrive.data.Slice;
import com.dev.wedrive.entity.ApiLocation;
import com.dev.wedrive.entity.ApiRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestService {


    public void getMyRequests(final Consumer<ArrayList<ApiRequest>> func) {
        ApiService.getInstance().request().getMyRequests().enqueue(new Callback<ApiResponse<ArrayList<ApiRequest>>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ArrayList<ApiRequest>) response.getData());
                }
            }
        });
    }

    public void getMyRequests(Pager pager, final Consumer<ArrayList<ApiRequest>> func) {
        ApiService.getInstance().request().getMyRequests(pager.getPage()).enqueue(new Callback<ApiResponse<ArrayList<ApiRequest>>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ArrayList<ApiRequest>) response.getData());
                }
            }
        });
    }

    public void getRequest(String uuid, final Consumer<ApiRequest> func) {
        ApiService.getInstance().request().getInfo(uuid).enqueue(new Callback<ApiResponse<ApiRequest>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success)
                    func.accept((ApiRequest) response.getData());
            }
        });
    }

    public void createRequest(ApiLocation location, ApiRequest request, final Consumer<ApiRequest> func) {
        ApiService.getInstance().request().createRequest(location.uuid, request).enqueue(new Callback<ApiResponse<ApiRequest>>() {
            @Override
            public void onResult(ApiResponse response) {
                if (response instanceof ApiResponse.Success) {
                    func.accept((ApiRequest) response.getData());
                }
            }
        });
    }

    public void setStatus(ApiRequest request, String status) {
        setStatus(request, status, (r) -> {
        });
    }

    public void setStatus(ApiRequest request, String status, final Consumer<ApiRequest> func) {
        Map<String, String> mStatus = new HashMap<>();
        mStatus.put("status", status);
        ApiService.getInstance().request().setStatus(request.uuid, mStatus).enqueue(new Callback<ApiResponse<ApiRequest>>() {
            @Override
            public void onResult(ApiResponse response) {
                func.accept((ApiRequest) response.getData());
            }
        });
    }

}
