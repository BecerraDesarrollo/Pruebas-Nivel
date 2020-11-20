package com.ecommercefarm.test.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    String URL="https://us-central1-pruebas-nivel.cloudfunctions.net/";
    @GET("getMessages") Call<List<Book>> all();
    @GET("getMessages?genre=fantasy") Call<List<Book>> filter();
}
