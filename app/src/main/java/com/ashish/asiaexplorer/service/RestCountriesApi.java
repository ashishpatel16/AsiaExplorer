package com.ashish.asiaexplorer.service;

import com.ashish.asiaexplorer.data.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestCountriesApi {

    @GET("region/asia")
    Call<List<Country>> getAsianCountries();
}
