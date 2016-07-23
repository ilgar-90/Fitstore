package com.actonica.fitstore.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilgar on 09.07.2016.
 */
public class Training implements Serializable {
    
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_no")
    @Expose
    private Integer orderNo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("bits")
    @Expose
    private List<Bit> bits = new ArrayList<>();

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
     * The bits
     */
    public List<Bit> getBits() {
        return bits;
    }

    /**
     *
     * @param bits
     * The bits
     */
    public void setBits(List<Bit> bits) {
        this.bits = bits;
    }

}
