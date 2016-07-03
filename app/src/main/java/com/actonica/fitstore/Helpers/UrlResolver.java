package com.actonica.fitstore.Helpers;

/**
 * Created by ilgar on 27.06.2016.
 */
public class UrlResolver {
    static String producerAvatarUrl = "http://juicefit.net/uploads/producers/";
    static String programImageUrl = "http://juicefit.net/uploads/programs/";
    static String programZipUrl = "http://juicefit.net/uploads/zip/";
    static String exerciseVideoUrl = "http://juicefit.net/uploads/exercises/";

    public static String getProgramAvatar(String avatar){
        return String.format("%s%s", programImageUrl, avatar);
    }

    public static String getProducerAvatar(String avatar){
        return String.format("%s%s", producerAvatarUrl, avatar);
    }

    public static String getProgramDownloadUrl(String zipName){
        return String.format("%s%s", programZipUrl, zipName);
    }
}
