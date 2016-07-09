package com.actonica.fitstore.API;

import android.content.Context;

import com.actonica.fitstore.ApiResponsesGson.GetCategoriesResponse;
import com.actonica.fitstore.ApiResponsesGson.GetFullProgramResponse;
import com.actonica.fitstore.ApiResponsesGson.GetProducerProgramsResponse;
import com.actonica.fitstore.ApiResponsesGson.GetProgramsResponse;
import com.actonica.fitstore.ApiResponsesGson.RegisterUserResponse;
import com.actonica.fitstore.ApiResponsesGson.VerifyUserResponse;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;
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

    public static void getCategories(Context context, Callback<GetCategoriesResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);



        Call<GetCategoriesResponse> call = juiceFitAPI.getCategories(getToken(context));
        //asynchronous call
        call.enqueue(callback);
    }

    public static void getRelatedPrograms(String program_id, Context context, Callback<GetProgramsResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);
        Call<GetProgramsResponse> call = juiceFitAPI.getRelatedPrograms(program_id, getToken(context));
        //asynchronous call
        call.enqueue(callback);
    }

    public static void getProducerPrograms(String producer_id, Context context, Callback<GetProducerProgramsResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);
        Call<GetProducerProgramsResponse> call = juiceFitAPI.getProducerPrograms(producer_id, getToken(context));
        //asynchronous call
        call.enqueue(callback);
    }

    public static void getUserPrograms(Context context, Callback<GetProgramsResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);
        Call<GetProgramsResponse> call = juiceFitAPI.getUserPrograms(getToken(context));
        //asynchronous call
        call.enqueue(callback);
    }

    public static void getFullProgram(int program_id, Context context, Callback<GetFullProgramResponse> callback) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        JuiceFitAPI juiceFitAPI = retrofit.create(JuiceFitAPI.class);
        Call<GetFullProgramResponse> call = juiceFitAPI.getFullProgram(program_id, getToken(context));
        //asynchronous call
        call.enqueue(callback);
    }

    private static String getToken(Context context){
        SharedPrefsHelper sphelper = new SharedPrefsHelper(context);
        String saved_token = sphelper.getSavedToken();
        return saved_token;
    }

}
