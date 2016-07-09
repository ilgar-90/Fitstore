package com.actonica.fitstore.ApiResponsesGson;
import com.actonica.fitstore.Models.Program;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ilgar on 29.06.2016.
 */
public class GetProgramsResponse {
    @SerializedName(value="related_programs", alternate={"user_programs"})
    public List<Program> programs;
}
