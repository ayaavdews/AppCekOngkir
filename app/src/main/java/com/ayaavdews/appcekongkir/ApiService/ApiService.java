package com.ayaavdews.appcekongkir.ApiService;

import com.ayaavdews.appcekongkir.Model.cost.ItemCost;
import com.ayaavdews.appcekongkir.Model.waybill.ItemWaybill;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 * Create By : Aya Avdews
 * XII RPL A / 17.006892
 * SMKN 2 Surakarta
 * */

public interface ApiService {
    String JSONURL = "https://pro.rajaongkir.com/api/";
    String APIKEY  = "key:3f3bce6f9e0d62d356f48cb8040b5653";

    // Cost
    @FormUrlEncoded
    @POST("cost")
    Call<ItemCost> getCost(
            @Field("key") String Token,
            @Field("origin") String origin,
            @Field("originType") String originType,
            @Field("destination") String destination,
            @Field("destinationType") String destinationType,
            @Field("weight") String weight,
            @Field("courier") String courier
    );

    @FormUrlEncoded
    @POST("waybill")
    Call<ItemWaybill> getWaybill(
            @Field("key") String key,
            @Field("waybill") String waybill,
            @Field("courier") String courier
    );
}
