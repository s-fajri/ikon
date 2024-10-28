package com.example.ikonsultan;

import com.example.ikonsultan.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET(("posts"))
    Call<List<User>> getUsers();
}
