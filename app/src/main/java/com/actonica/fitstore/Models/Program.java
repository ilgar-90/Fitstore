package com.actonica.fitstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by ilgar on 27.06.2016.
 */

public class Program extends RealmObject implements Serializable {
    @SerializedName(value="id", alternate={"program_id"})
    @Expose
    private Integer id;
    @SerializedName("part_no")
    @Expose
    private Integer partNo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("zip_file")
    @Expose
    private String zipFile;
    @SerializedName("zip_file_size")
    @Expose
    private Integer zipFileSize;
    @SerializedName("parts_total")
    @Expose
    private Integer partsTotal;
    @SerializedName("trainings_total")
    @Expose
    private String trainingsTotal;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("producer")
    @Expose
    private Producer producer;
    @SerializedName("trainings")
    @Expose
    private RealmList<Training> trainings = new RealmList<Training>();
    @SerializedName("exercises")
    @Expose
    private RealmList<Exercise> exercises = new RealmList<Exercise>();
    @SerializedName("next_training_id")
    @Expose
    private Integer next_training_id;

    private boolean used;
    private String lastUsedAt;

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
     * The partNo
     */
    public Integer getPartNo() {
        return partNo;
    }

    /**
     *
     * @param partNo
     * The part_no
     */
    public void setPartNo(Integer partNo) {
        this.partNo = partNo;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The cover
     */
    public String getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(String cover) {
        this.cover = cover;
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
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The deletedAt
     */
    public String getDeletedAt() {
        return deletedAt;
    }

    /**
     *
     * @param deletedAt
     * The deleted_at
     */
    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     *
     * @return
     * The zipFile
     */
    public String getZipFile() {
        return zipFile;
    }

    /**
     *
     * @param zipFile
     * The zip_file
     */
    public void setZipFile(String zipFile) {
        this.zipFile = zipFile;
    }

    /**
     *
     * @return
     * The zipFileSize
     */
    public Integer getZipFileSize() {
        return zipFileSize;
    }

    /**
     *
     * @param zipFileSize
     * The zip_file_size
     */
    public void setZipFileSize(Integer zipFileSize) {
        this.zipFileSize = zipFileSize;
    }

    /**
     *
     * @return
     * The partsTotal
     */
    public Integer getPartsTotal() {
        return partsTotal;
    }

    /**
     *
     * @param partsTotal
     * The parts_total
     */
    public void setPartsTotal(Integer partsTotal) {
        this.partsTotal = partsTotal;
    }

    /**
     *
     * @return
     * The trainingsTotal
     */
    public String getTrainingsTotal() {
        return trainingsTotal;
    }

    /**
     *
     * @param trainingsTotal
     * The trainings_total
     */
    public void setTrainingsTotal(String trainingsTotal) {
        this.trainingsTotal = trainingsTotal;
    }

    /**
     *
     * @return
     * The plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     *
     * @param plan
     * The plan
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     *
     * @return
     * The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     *
     * @param producer
     * The producer
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     *
     * @return
     * The trainings
     */
    public RealmList<Training> getTrainings() {
        return trainings;
    }

    /**
     *
     * @param trainings
     * The trainings
     */
    public void setTrainings(RealmList<Training> trainings) {
        this.trainings = trainings;
    }

    /**
     *
     * @return
     * The exercises
     */
    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    /**
     *
     * @param exercises
     * The exercises
     */
    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }


    public Integer getNext_training_id() {
        return next_training_id;
    }

    public void setNext_training_id(Integer next_training_id) {
        this.next_training_id = next_training_id;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(String lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }
}


