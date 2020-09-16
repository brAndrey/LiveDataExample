package com.example.livedataexample.service;

import com.example.livedataexample.model.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {

    @GET("feed.json")
    Call<BlogWrapper> getPopularBlog();
}
