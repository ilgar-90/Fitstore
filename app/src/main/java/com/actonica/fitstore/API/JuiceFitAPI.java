package com.actonica.fitstore.API;

import com.actonica.fitstore.ApiResponsesGson.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ilgar on 26.06.2016.
 */
public interface JuiceFitAPI {
    @POST("/users/register")
    Call<RegisterUserResponse> registerUser(@Query("phone") String phone);

    @POST("/users/verify")
    Call<VerifyUserResponse> verifyUser(@Query("phone") String phone,  @Query("phone_code") String phone_code);

    @GET("/categories")
    Call<GetCategoriesResponse> getCategories(@Query("token") String token);

    @GET("/programs/{program_id}/related")
    Call<GetProgramsResponse> getRelatedPrograms(@Path("program_id") String program_id, @Query("token") String token);

    @GET("/producers/{producer_id}/programs")
    Call<GetProducerProgramsResponse> getProducerPrograms(@Path("producer_id") String producer_id, @Query("token") String token);

    @GET("/users/programs")
    Call<GetProgramsResponse> getUserPrograms(@Query("token") String token);

    @GET("/programs/{program_id}")
    Call<GetFullProgramResponse> getFullProgram(@Path("program_id") int program_id, @Query("token") String token);

    @POST("/users/programs/{program_id}/delete")
    Call<ProgramInteractionResponse> removeProgram(@Path("program_id") int program_id, @Query("token") String token);

    @POST("/users/programs/{program_id}/start")
    Call<ProgramInteractionResponse> startProgram(@Path("program_id") int program_id, @Query("token") String token);

    @GET("/users/programs/history")
    Call<UserProgramsHistoryResponse> getUserHistory(@Query("token") String token);
}