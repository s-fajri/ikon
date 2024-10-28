package com.example.ikonsultan;

import com.example.ikonsultan.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRepository {
    private final APIService apiService;

    public APIRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public Call<List<User>> getUsers() {
        return apiService.getUsers();
    }
}
