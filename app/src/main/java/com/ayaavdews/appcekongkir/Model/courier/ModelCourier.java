package com.ayaavdews.appcekongkir.Model.courier;

public class ModelCourier {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private String spinnerText;
    private String spinnerID;
    private int spinnerImage;

    public ModelCourier(String spinnerText, String spinnerID, int spinnerImage) {
        this.spinnerText = spinnerText;
        this.spinnerID = spinnerID;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerText() {
        return spinnerText;
    }

    public String getSpinnerID() {
        return spinnerID;
    }

    public int getSpinnerImage() {
        return spinnerImage;
    }

}
