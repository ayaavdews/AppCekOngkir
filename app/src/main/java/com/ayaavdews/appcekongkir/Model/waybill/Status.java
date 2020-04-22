package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class Status {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
