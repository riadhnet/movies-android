package com.riadh.movies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Dates extends BaseModel {

    private static final long serialVersionUID = -733375066385752114L;

    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}