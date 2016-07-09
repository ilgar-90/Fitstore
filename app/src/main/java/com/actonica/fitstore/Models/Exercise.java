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
public class Exercise extends RealmObject {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("avatar_vid")
    @Expose
    private String avatarVid;
    @SerializedName("avatar_img")
    @Expose
    private String avatarImg;
    @SerializedName("video_description")
    @Expose
    private String videoDescription;
    @SerializedName("video_description_img")
    @Expose
    private String videoDescriptionImg;
    @SerializedName("video_rules")
    @Expose
    private String videoRules;
    @SerializedName("video_rules_img")
    @Expose
    private String videoRulesImg;
    @SerializedName("video_errors")
    @Expose
    private String videoErrors;
    @SerializedName("video_errors_img")
    @Expose
    private String videoErrorsImg;
    /*@SerializedName("replacements")
    @Expose
    private RealmList<RealmObject> replacements = new RealmList<RealmObject>(); //TODO Handle replacements!!!*/

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
     * The avatarVid
     */
    public String getAvatarVid() {
        return avatarVid;
    }

    /**
     *
     * @param avatarVid
     * The avatar_vid
     */
    public void setAvatarVid(String avatarVid) {
        this.avatarVid = avatarVid;
    }

    /**
     *
     * @return
     * The avatarImg
     */
    public String getAvatarImg() {
        return avatarImg;
    }

    /**
     *
     * @param avatarImg
     * The avatar_img
     */
    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    /**
     *
     * @return
     * The videoDescription
     */
    public String getVideoDescription() {
        return videoDescription;
    }

    /**
     *
     * @param videoDescription
     * The video_description
     */
    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    /**
     *
     * @return
     * The videoDescriptionImg
     */
    public String getVideoDescriptionImg() {
        return videoDescriptionImg;
    }

    /**
     *
     * @param videoDescriptionImg
     * The video_description_img
     */
    public void setVideoDescriptionImg(String videoDescriptionImg) {
        this.videoDescriptionImg = videoDescriptionImg;
    }

    /**
     *
     * @return
     * The videoRules
     */
    public String getVideoRules() {
        return videoRules;
    }

    /**
     *
     * @param videoRules
     * The video_rules
     */
    public void setVideoRules(String videoRules) {
        this.videoRules = videoRules;
    }

    /**
     *
     * @return
     * The videoRulesImg
     */
    public String getVideoRulesImg() {
        return videoRulesImg;
    }

    /**
     *
     * @param videoRulesImg
     * The video_rules_img
     */
    public void setVideoRulesImg(String videoRulesImg) {
        this.videoRulesImg = videoRulesImg;
    }

    /**
     *
     * @return
     * The videoErrors
     */
    public String getVideoErrors() {
        return videoErrors;
    }

    /**
     *
     * @param videoErrors
     * The video_errors
     */
    public void setVideoErrors(String videoErrors) {
        this.videoErrors = videoErrors;
    }

    /**
     *
     * @return
     * The videoErrorsImg
     */
    public String getVideoErrorsImg() {
        return videoErrorsImg;
    }

    /**
     *
     * @param videoErrorsImg
     * The video_errors_img
     */
    public void setVideoErrorsImg(String videoErrorsImg) {
        this.videoErrorsImg = videoErrorsImg;
    }

    /**
     *
     * @return
     * The replacements
     */
    //public RealmList<RealmObject> getReplacements() {
        //return replacements;
    //}

    /**
     *
     * @param replacements
     * The replacements
     */
    //public void setReplacements(RealmList<RealmObject> replacements) {
        //this.replacements = replacements;
    //}

}