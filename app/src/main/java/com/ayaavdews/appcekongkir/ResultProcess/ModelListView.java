package com.ayaavdews.appcekongkir.ResultProcess;

public class ModelListView {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private String code, name, service, description, etd;
    private Integer value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getService(){
        return service;
    }

    public void setService(String service){
        this.service = service;
    }

    public String getDescription(){
        return  description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getEtd(){
        return etd;
    }

    public void setEtd(String etd){
        this.etd = etd;
    }

    public Integer getValue(){
        return value;
    }

    public void setValue (Integer value){
        this.value = value;
    }
}
