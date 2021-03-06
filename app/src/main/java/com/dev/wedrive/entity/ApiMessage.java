package com.dev.wedrive.entity;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class ApiMessage {

    public static final String STATUS_NEW = "new";
    public static final String STATUS_READ = "read";

    @SerializedName("uuid")
    @Getter
    public String uuid;

    @SerializedName("user_id")
    @Setter
    @Getter
    public int userId = 0;

    @SerializedName("user")
    @Setter
    @Getter
    public ApiUser user;

    @SerializedName("recipient_id")
    public int recipientId = 0;

    @SerializedName("recipient")
    @Setter
    @Getter
    public ApiUser recipient;

    @SerializedName("message")
    @Setter
    @Getter
    public String message = "";

    @SerializedName("status")
    @Setter
    @Getter
    public String status;

    @SerializedName("request_uuid")
    @Setter
    @Getter
    public String requestUuid;

    @SerializedName("chat")
    @Setter
    @Getter
    public ApiMessageChat chat;

    @SerializedName("created_at")
    @Setter
    @Getter
    public Timestamp createdAt;

    public ApiMessage() {
    }

    public ApiMessage(ApiUser recipient, String message) {
        this.recipientId = recipient.getId();
        this.message = message.trim();

    }

}
