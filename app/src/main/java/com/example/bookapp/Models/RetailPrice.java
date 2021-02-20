package com.example.bookapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice
{
    @SerializedName("amount")
    @Expose
    String amount;
    @SerializedName("currencyCode")
    @Expose
    String currencyCode;

    public String getAmount()
    {
        return amount;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

}
