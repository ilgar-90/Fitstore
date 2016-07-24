package com.actonica.fitstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ilgar on 24.07.2016.
 */
public class HistoryProgram implements Serializable {
    @SerializedName("program_id")
    @Expose
    private Integer programId;
    @SerializedName("added_at")
    @Expose
    private String addedAt;
    @SerializedName("finished_at")
    @Expose
    private String finishedAt;
    @SerializedName("program")
    @Expose
    private Program program;

    /**
     *
     * @return
     * The programId
     */
    public Integer getProgramId() {
        return programId;
    }

    /**
     *
     * @param programId
     * The program_id
     */
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    /**
     *
     * @return
     * The addedAt
     */
    public String getAddedAt() {
        return addedAt;
    }

    /**
     *
     * @param addedAt
     * The added_at
     */
    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    /**
     *
     * @return
     * The finishedAt
     */
    public String getFinishedAt() {
        return finishedAt;
    }

    /**
     *
     * @param finishedAt
     * The finished_at
     */
    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    /**
     *
     * @return
     * The program
     */
    public Program getProgram() {
        return program;
    }

    /**
     *
     * @param program
     * The program
     */
    public void setProgram(Program program) {
        this.program = program;
    }
}
