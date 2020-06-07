package com.uigitdev.loginscreen.services;

import com.uigitdev.loginscreen.model.FoodDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YummyAPIServices {

    @GET("/data.json")
    Call<List<FoodDetails>> getFoodData();
}
