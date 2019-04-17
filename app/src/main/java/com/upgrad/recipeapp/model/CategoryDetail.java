package com.upgrad.recipeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDetail {

    @SerializedName("strMeal")
    @Expose
    private String strMeal;
    @SerializedName("strMealThumb")
    @Expose
    private String strMealThumb;
    @SerializedName("idMeal")
    @Expose
    private String idMeal;

    public CategoryDetail(String strMeal, String strMealThumb, String idMeal) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }
}
