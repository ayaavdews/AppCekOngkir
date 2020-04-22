package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class Manifest {
    @SerializedName("manifest_code")
    @Expose
    private String manifestCode;
    @SerializedName("manifest_description")
    @Expose
    private String manifestDescription;
    @SerializedName("manifest_date")
    @Expose
    private String manifestDate;
    @SerializedName("manifest_time")
    @Expose
    private String manifestTime;
    @SerializedName("city_name")
    @Expose
    private String cityName;

    public String getManifestCode() {
        return manifestCode;
    }

    public void setManifestCode(String manifestCode) {
        this.manifestCode = manifestCode;
    }

    public String getManifestDescription() {
        return manifestDescription;
    }

    public void setManifestDescription(String manifestDescription) {
        this.manifestDescription = manifestDescription;
    }

    public String getManifestDate() {
        return manifestDate;
    }

    public void setManifestDate(String manifestDate) {
        this.manifestDate = manifestDate;
    }

    public String getManifestTime() {
        return manifestTime;
    }

    public void setManifestTime(String manifestTime) {
        this.manifestTime = manifestTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
