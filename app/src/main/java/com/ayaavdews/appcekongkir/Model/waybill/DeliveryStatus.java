package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class DeliveryStatus {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("pod_receiver")
    @Expose
    private String podReceiver;
    @SerializedName("pod_date")
    @Expose
    private String podDate;
    @SerializedName("pod_time")
    @Expose
    private String podTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPodReceiver() {
        return podReceiver;
    }

    public void setPodReceiver(String podReceiver) {
        this.podReceiver = podReceiver;
    }

    public String getPodDate() {
        return podDate;
    }

    public void setPodDate(String podDate) {
        this.podDate = podDate;
    }

    public String getPodTime() {
        return podTime;
    }

    public void setPodTime(String podTime) {
        this.podTime = podTime;
    }
}
