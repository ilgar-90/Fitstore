package com.actonica.fitstore.API;

import com.actonica.fitstore.ApiResponsesGson.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ilgar on 26.06.2016.
 */
public interface JuiceFitAPI {
    @POST("/users/register")
    Call<RegisterUserResponse> registerUser(@Query("phone") String phone);

    @POST("/users/verify")
    Call<VerifyUserResponse> verifyUser(@Query("phone") String phone,  @Query("phone_code") String phone_code);
}