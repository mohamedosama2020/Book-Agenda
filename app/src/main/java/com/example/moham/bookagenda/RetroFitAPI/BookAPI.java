package com.example.moham.bookagenda.RetroFitAPI;

import com.example.moham.bookagenda.BaseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BookAPI {

    public static  final String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @GET
    Call<BaseObject> getBaseObject(@Url String url);
}
