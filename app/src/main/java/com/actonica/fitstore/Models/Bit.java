package com.actonica.fitstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ilgar on 09.07.2016.
 */
public class Bit extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("order_no")
    @Expose
    private Integer orderNo;
    @SerializedName("bit_exercises")
    @Expose
    private RealmList<BitExercise> bitExercises = new RealmList<BitExercise>();

    private boolean finished;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Integer value) {
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

    /**
     *
     * @return
     * The bitExercises
     */
    public RealmList<BitExercise> getBitExercises() {
        return bitExercises;
    }

    /**
     *
     * @param bitExercises
     * The bit_exercises
     */
    public void setBitExercises(RealmList<BitExercise> bitExercises) {
        this.bitExercises = bitExercises;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}