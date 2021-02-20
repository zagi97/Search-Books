package com.example.bookapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleInfo
{
    @SerializedName("retailPrice")
    @Expose
    RetailPrice retail_price;
    @SerializedName("buyLink")
    @Expose
    String buyLink;

    public RetailPrice getRetail_price()
    {
        return retail_price;
    }

    public String getBuyLink()
    {
        return buyLink;
    }

}
