package com.dev.wedrive.service;

import com.dev.wedrive.Constants;
import com.dev.wedrive.api.ApiCarInterface;
import com.dev.wedrive.api.ApiDeviceTokenInterface;
import com.dev.wedrive.api.ApiInformInterface;
import com.dev.wedrive.api.ApiLocationInterface;
import com.dev.wedrive.api.ApiMessageInterface;
import com.dev.wedrive.api.ApiProfileInterface;
import com.dev.wedrive.api.ApiRequestInterface;
import com.dev.wedrive.api.ApiRouteInterface;
import com.dev.wedrive.api.ApiUserInterface;
import com.dev.wedrive.entity.ApiToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {

    private Retrofit mRetrofit;

    private static ApiService apiServiceInstance;

    @Getter
    private ApiToken token;

    @Setter
    protected OnTokenChange onTokenChangeListener;

    private ApiService() {

        //TODO: delete
        token = new ApiToken("4lNNZIlFlW9uEiJgKNALaiDyXBpC1dChiqZdW44H-QNJKkvv86Fs5Jq_FeYZQUrt");
        token.userId = 2;

//        //TODO: delete
//        token = new ApiToken("rggF3it2gbaWiQNmsIrLLgriU-S71o5evimoG61tSwy-Iry8Xuc0j3TnumDZLd1h");
//        token.userId = 1;
//
//        //TODO: delete
//        token = new ApiToken("1uc_mJm824imz15nvF99NrGlab-0bPtaYmZLebQi7u9_sq2Qh510NIczB9vhwfXd");
//        token.userId = 25;


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder newRequest = chain.request().newBuilder();

                if (token != null)
                    newRequest.addHeader("Authorization", "Bearer " + token.code);


                return chain.proceed(newRequest.build());
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiService getInstance() {
        if (apiServiceInstance == null)
            apiServiceInstance = new ApiService();

        return apiServiceInstance;
    }

    public void setToken(ApiToken token) {
        this.token = token;
        if (onTokenChangeListener != null)
            onTokenChangeListener.onChange(token);
    }

    public interface OnTokenChange {
         void onChange(ApiToken token);
    }

    public ApiRouteInterface route() {
        return mRetrofit.create(ApiRouteInterface.class);
    }

    public ApiCarInterface car() {
        return mRetrofit.create(ApiCarInterface.class);
    }

    public ApiLocationInterface location() {
        return mRetrofit.create(ApiLocationInterface.class);
    }

    public ApiProfileInterface profile() {
        return mRetrofit.create(ApiProfileInterface.class);
    }

    public ApiUserInterface user() {
        return mRetrofit.create(ApiUserInterface.class);
    }

    public ApiRequestInterface request() {
        return mRetrofit.create(ApiRequestInterface.class);
    }

    public ApiInformInterface inform() {
        return mRetrofit.create(ApiInformInterface.class);
    }

    public ApiMessageInterface messages() {
        return mRetrofit.create(ApiMessageInterface.class);
    }

    public ApiDeviceTokenInterface deviceToken() {
        return mRetrofit.create(ApiDeviceTokenInterface.class);
    }

}
