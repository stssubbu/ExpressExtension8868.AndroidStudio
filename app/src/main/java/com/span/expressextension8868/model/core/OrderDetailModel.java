package com.span.expressextension8868.model.core;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by STS-099 on 12/9/2015.
 */
public class OrderDetailModel {

    String AT, BId, DId, EM, OS, RID, UId;

    String FaxAlertPrice, TextAlertPrice, TextPhoneNumber, FaxNumber, Price, SKUId, SKU_Description, SKU_Name;

    boolean IsFaxAlertPaid, IsTextAlertPaid, ISPAID;

    int ShoppingCartId;

    public int getShoppingCartId() {
        return ShoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        ShoppingCartId = shoppingCartId;
    }

    public String getAT() {
        return AT;
    }

    public void setAT(String AT) {
        this.AT = AT;
    }

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String EM) {
        this.EM = EM;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getFaxAlertPrice() {
        return FaxAlertPrice;
    }

    public void setFaxAlertPrice(String faxAlertPrice) {
        FaxAlertPrice = faxAlertPrice;
    }

    public String getTextAlertPrice() {
        return TextAlertPrice;
    }

    public void setTextAlertPrice(String textAlertPrice) {
        TextAlertPrice = textAlertPrice;
    }

    public String getTextPhoneNumber() {
        return TextPhoneNumber;
    }

    public void setTextPhoneNumber(String textPhoneNumber) {
        TextPhoneNumber = textPhoneNumber;
    }

    public String getFaxNumber() {
        return FaxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        FaxNumber = faxNumber;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSKUId() {
        return SKUId;
    }

    public void setSKUId(String SKUId) {
        this.SKUId = SKUId;
    }

    public String getSKU_Description() {
        return SKU_Description;
    }

    public void setSKU_Description(String SKU_Description) {
        this.SKU_Description = SKU_Description;
    }

    public String getSKU_Name() {
        return SKU_Name;
    }

    public void setSKU_Name(String SKU_Name) {
        this.SKU_Name = SKU_Name;
    }

    public boolean isFaxAlertPaid() {
        return IsFaxAlertPaid;
    }

    public void setIsFaxAlertPaid(boolean isFaxAlertPaid) {
        IsFaxAlertPaid = isFaxAlertPaid;
    }

    public boolean isTextAlertPaid() {
        return IsTextAlertPaid;
    }

    public void setIsTextAlertPaid(boolean isTextAlertPaid) {
        IsTextAlertPaid = isTextAlertPaid;
    }

    public boolean ISPAID() {
        return ISPAID;
    }

    public void setISPAID(boolean ISPAID) {
        this.ISPAID = ISPAID;
    }


    public String getOrderDetails(Context mContext) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj1.put("PaymentDetail", obj);

        } catch (JSONException e) {

            new SendException(mContext, e);

            e.printStackTrace();

        }

        return obj1.toString();

    }


    public String saveOrderDetails(Context mContext) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        JSONObject ProductSKU = new JSONObject();

        JSONObject ReturnNotification = new JSONObject();

        try {
            obj.put("AT", this.AT);
            obj.put("UId", this.UId);
            obj.put("DId", this.DId);
            obj.put("RID", this.RID);
            obj.put("SelectedSKUId", this.SKUId);

            ProductSKU.put("SKUId", this.SKUId);

            ReturnNotification.put("IsTextAlertPaid", this.IsTextAlertPaid);
            ReturnNotification.put("IsFaxAlertPaid", this.IsFaxAlertPaid);
            ReturnNotification.put("TextPhoneNumber", this.TextPhoneNumber);
            ReturnNotification.put("FaxNumber", this.FaxNumber);
            ReturnNotification.put("UId", this.UId);
            ReturnNotification.put("RID", this.RID);

            obj.put("ProductSKU", ProductSKU);
            obj.put("ReturnNotification", ReturnNotification);

            obj1.put("PaymentDetail", obj);

        } catch (JSONException e) {

            new SendException(mContext, e);

            e.printStackTrace();

        }

        return obj1.toString();

    }


}
