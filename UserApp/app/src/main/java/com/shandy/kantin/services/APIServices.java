package com.shandy.kantin.services;

import com.shandy.kantin.model.FoodDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("/data.json")
    Call<List<FoodDetails>> getFoodData();
}
