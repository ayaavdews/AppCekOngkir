package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class Query {
    @SerializedName("waybill")
    @Expose
    private String waybill;
    @SerializedName("courier")
    @Expose
    private String courier;

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}
