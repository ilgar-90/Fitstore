package com.actonica.fitstore.ApiResponsesGson;

import com.actonica.fitstore.Models.HistoryProgram;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ilgar on 24.07.2016.
 */
public class UserProgramsHistoryResponse {
    @SerializedName("user_programs")
    public List<HistoryProgram> historyPrograms;
}
