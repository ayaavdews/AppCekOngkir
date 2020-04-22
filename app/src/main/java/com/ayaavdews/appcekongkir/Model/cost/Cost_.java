package com.ayaavdews.appcekongkir.Model.cost;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cost_ {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("etd")
    @Expose
    private String etd;
    @SerializedName("note")
    @Expose
    private String note;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
