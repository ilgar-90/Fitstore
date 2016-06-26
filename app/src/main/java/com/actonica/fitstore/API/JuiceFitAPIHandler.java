package com.actonica.fitstore.API;

import com.actonica.fitstore.ApiResponsesGson.RegisterUserResponse;
import com.actonica.fitstore.ApiResponsesGson.VerifyUserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ilgar on 26.06.2016.
 */
public class JuiceFitAPIHandler {

    private static final String apiServiceBaseUrl = "http://api.juicefit.net";

    public static void resgisterUser(String phoneNumber, Callback<RegisterUserResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);

        Call<RegisterUserResponse> call = juiceFitAPI.registerUser(phoneNumber);
        //asynchronous call
        call.enqueue(callback);
    }

    public static void verifyUser(String phoneNumber, String phone_code, Callback<VerifyUserResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);

        Call<VerifyUserResponse> call = juiceFitAPI.verifyUser(phoneNumber, phone_code);
        //asynchronous call
        call.enqueue(callback);
    }

}
