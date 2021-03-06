package com.dev.wedrive.entity;

import com.dev.wedrive.CreateNewCarActivity;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class ApiCar {

    @SerializedName("uuid")
    @Getter
    public String uuid;

    @SerializedName("user_id")
    @Setter
    @Getter
    public int userId;

    @SerializedName("user")
    @Setter
    @Getter
    public ApiUser user;

    @SerializedName("brand")
    public String brand;

    @SerializedName("model")
    public String model;

    @SerializedName("year")
    public Integer year;

    @SerializedName("color")
    public String color;

    @SerializedName("number")
    public String number;

    @SerializedName("active")
    public boolean active;

    @SerializedName("image")
    @Getter
    public String image;

    public ApiCar() {
    }

    public ApiCar(CreateNewCarActivity activity) {
        load(activity);
    }

    public ApiCar load(CreateNewCarActivity activity) {
        brand = activity.getBrand().getText().toString();
        model = activity.getModel().getText().toString();
        year = Integer.parseInt(activity.getYear().getText().toString());
        color = activity.getColor().getText().toString();
        number = activity.getNumber().getText().toString();
        return this;
    }

}
