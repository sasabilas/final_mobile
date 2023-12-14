package com.d121211100.kesehatan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("covid19/hospitals")
    Call<List<Hospital>> getHospitals();
}