package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class Result {
    @SerializedName("delivered")
    @Expose
    private Boolean delivered;
    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("delivery_status")
    @Expose
    private DeliveryStatus deliveryStatus;
    @SerializedName("manifest")
    @Expose
    private List<Manifest> manifest = null;

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<Manifest> getManifest() {
        return manifest;
    }

    public void setManifest(List<Manifest> manifest) {
        this.manifest = manifest;
    }
}
