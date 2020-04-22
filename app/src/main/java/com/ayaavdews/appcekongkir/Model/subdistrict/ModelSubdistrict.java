package com.ayaavdews.appcekongkir.Model.subdistrict;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class ModelSubdistrict {
    private String subdistrict_name;
    private String subdistrict_id;
    private String province;
    private String city;

    public ModelSubdistrict(String subdistrict_name, String subdistrict_id, String province, String city){
        this.subdistrict_name = subdistrict_name;
        this.subdistrict_id   = subdistrict_id;
        this.province         = province;
        this.city             = city;
    }

    public String getSubdistrict_name(){
        return subdistrict_name;
    }

    public void setSubdistrict_name(String subdistrict_name){
        this.subdistrict_name = subdistrict_name;
    }

    public String getSubdistrict_id(){
        return subdistrict_id;
    }

    public void setSubdistrict_id(String subdistrict_id){
        this.subdistrict_id = subdistrict_id;
    }

    public String getProvince(){
        return province;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

}
