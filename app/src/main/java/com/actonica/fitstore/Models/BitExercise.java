package com.actonica.fitstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ilgar on 09.07.2016.
 */
public class BitExercise extends RealmObject implements Serializable {

    @PrimaryKey
    @SerializedName("exercise_id")
    @Expose
    private Integer exerciseId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("order_no")
    @Expose
    private Integer orderNo;

    /**
     *
     * @return
     * The exerciseId
     */
    public Integer getExerciseId() {
        return exerciseId;
    }

    /**
     *
     * @param exerciseId
     * The exercise_id
     */
    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    /**
     *
     * @return
     * The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return
     * The type
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The orderNo
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     *
     * @param orderNo
     * The order_no
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}