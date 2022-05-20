package com.linda.bizmkononi.Network;

import com.linda.bizmkononi.Models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    @POST("/auth/register")
    Call<Users> createUser(
            @Body Users users);

    @POST ("/auth/login")
    Call<Users> loginUser(@Body Users users);
}
