package com.ayaavdews.appcekongkir.Model.waybill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class Details {
    @SerializedName("waybill_number")
    @Expose
    private String waybillNumber;
    @SerializedName("waybill_date")
    @Expose
    private String waybillDate;
    @SerializedName("waybill_time")
    @Expose
    private String waybillTime;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("shippper_name")
    @Expose
    private String shippperName;
    @SerializedName("shipper_address1")
    @Expose
    private String shipperAddress1;
    @SerializedName("shipper_address2")
    @Expose
    private Object shipperAddress2;
    @SerializedName("shipper_address3")
    @Expose
    private Object shipperAddress3;
    @SerializedName("shipper_city")
    @Expose
    private String shipperCity;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("receiver_address1")
    @Expose
    private String receiverAddress1;
    @SerializedName("receiver_address2")
    @Expose
    private String receiverAddress2;
    @SerializedName("receiver_address3")
    @Expose
    private String receiverAddress3;
    @SerializedName("receiver_city")
    @Expose
    private String receiverCity;

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getWaybillDate() {
        return waybillDate;
    }

    public void setWaybillDate(String waybillDate) {
        this.waybillDate = waybillDate;
    }

    public String getWaybillTime() {
        return waybillTime;
    }

    public void setWaybillTime(String waybillTime) {
        this.waybillTime = waybillTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getShippperName() {
        return shippperName;
    }

    public void setShippperName(String shippperName) {
        this.shippperName = shippperName;
    }

    public String getShipperAddress1() {
        return shipperAddress1;
    }

    public void setShipperAddress1(String shipperAddress1) {
        this.shipperAddress1 = shipperAddress1;
    }

    public Object getShipperAddress2() {
        return shipperAddress2;
    }

    public void setShipperAddress2(Object shipperAddress2) {
        this.shipperAddress2 = shipperAddress2;
    }

    public Object getShipperAddress3() {
        return shipperAddress3;
    }

    public void setShipperAddress3(Object shipperAddress3) {
        this.shipperAddress3 = shipperAddress3;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public void setReceiverAddress1(String receiverAddress1) {
        this.receiverAddress1 = receiverAddress1;
    }

    public String getReceiverAddress2() {
        return receiverAddress2;
    }

    public void setReceiverAddress2(String receiverAddress2) {
        this.receiverAddress2 = receiverAddress2;
    }

    public String getReceiverAddress3() {
        return receiverAddress3;
    }

    public void setReceiverAddress3(String receiverAddress3) {
        this.receiverAddress3 = receiverAddress3;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }
}
