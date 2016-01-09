package com.span.expressextension8868.model.core;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by STS-099 on 11/25/2015.
 */
public class BookIncareOfModel {

    String AT, UId, DId, RID, OS, EM;

    String BookCareOfDetailsId, BookInCareOf, Fax, Phone;

    String BCOAddress1, BCOAddress2, BCOCity, BCOCountry, BCOCountryId, BCOState, BCOStateCode, BCOStateId, BCOZip;

    boolean IsBCOBusiness, BCOIsForeignAddress, BCOIsSameAddress;

    String SADayTimePhone, SAName, SATitle;


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

    public String getAT() {
        return AT;
    }

    public void setAT(String AT) {
        this.AT = AT;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getBookCareOfDetailsId() {
        return BookCareOfDetailsId;
    }

    public void setBookCareOfDetailsId(String bookCareOfDetailsId) {
        BookCareOfDetailsId = bookCareOfDetailsId;
    }

    public String getBookInCareOf() {
        return BookInCareOf;
    }

    public void setBookInCareOf(String bookInCareOf) {
        BookInCareOf = bookInCareOf;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBCOAddress1() {
        return BCOAddress1;
    }

    public void setBCOAddress1(String BCOAddress1) {
        this.BCOAddress1 = BCOAddress1;
    }

    public String getBCOAddress2() {
        return BCOAddress2;
    }

    public void setBCOAddress2(String BCOAddress2) {
        this.BCOAddress2 = BCOAddress2;
    }

    public String getBCOCity() {
        return BCOCity;
    }

    public void setBCOCity(String BCOCity) {
        this.BCOCity = BCOCity;
    }

    public String getBCOCountry() {
        return BCOCountry;
    }

    public void setBCOCountry(String BCOCountry) {
        this.BCOCountry = BCOCountry;
    }

    public String getBCOCountryId() {
        return BCOCountryId;
    }

    public void setBCOCountryId(String BCOCountryId) {
        this.BCOCountryId = BCOCountryId;
    }

    public String getBCOState() {
        return BCOState;
    }

    public void setBCOState(String BCOState) {
        this.BCOState = BCOState;
    }

    public String getBCOStateCode() {
        return BCOStateCode;
    }

    public void setBCOStateCode(String BCOStateCode) {
        this.BCOStateCode = BCOStateCode;
    }

    public String getBCOStateId() {
        return BCOStateId;
    }

    public void setBCOStateId(String BCOStateId) {
        this.BCOStateId = BCOStateId;
    }

    public String getBCOZip() {
        return BCOZip;
    }

    public void setBCOZip(String BCOZip) {
        this.BCOZip = BCOZip;
    }

    public boolean isBCOBusiness() {
        return IsBCOBusiness;
    }

    public void setIsBCOBusiness(boolean isBCOBusiness) {
        IsBCOBusiness = isBCOBusiness;
    }

    public boolean isBCOIsForeignAddress() {
        return BCOIsForeignAddress;
    }

    public void setBCOIsForeignAddress(boolean BCOIsForeignAddress) {
        this.BCOIsForeignAddress = BCOIsForeignAddress;
    }

    public boolean isBCOIsSameAddress() {
        return BCOIsSameAddress;
    }

    public void setBCOIsSameAddress(boolean BCOIsSameAddress) {
        this.BCOIsSameAddress = BCOIsSameAddress;
    }

    public String getSADayTimePhone() {
        return SADayTimePhone;
    }

    public void setSADayTimePhone(String SADayTimePhone) {
        this.SADayTimePhone = SADayTimePhone;
    }

    public String getSAName() {
        return SAName;
    }

    public void setSAName(String SAName) {
        this.SAName = SAName;
    }

    public String getSATitle() {
        return SATitle;
    }

    public void setSATitle(String SATitle) {
        this.SATitle = SATitle;
    }

    public String getBooksInCareOfModel(Context mContext) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj1.put("BookCareOf", obj);

        } catch (JSONException e) {

            new SendException(mContext, e);

            e.printStackTrace();

        }
        Log.i("Books Request", "GET" + obj1.toString());

        Log.i("Books URL", "GET" + ApplicationConfig.GETBOOKINCAREOFBYRETURNID);

        return obj1.toString();


    }

    public String saveBooksInCareOfModel(Context mContext) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);


            obj.put("BookCareOfDetailsId", this.BookCareOfDetailsId);
            obj.put("IsBCOBusiness", this.IsBCOBusiness);
            obj.put("BookInCareOf", this.BookInCareOf);
            obj.put("Phone", this.Phone);

            obj.put("BCOIsForeignAddress", this.BCOIsForeignAddress);
            obj.put("BCOIsSameAddress", this.BCOIsSameAddress);
            obj.put("BCOAddress1", this.BCOAddress1);
            obj.put("BCOAddress2", this.BCOAddress2);
            obj.put("BCOCity", this.BCOCity);
            obj.put("BCOCountry", this.BCOCountry);
            obj.put("BCOCountryId", this.BCOCountryId);
            obj.put("BCOState", this.BCOState);
            obj.put("BCOStateCode", this.BCOStateCode);
            obj.put("BCOStateId", this.BCOStateId);
            obj.put("BCOZip", this.BCOZip);
            obj.put("Fax", this.Fax);


            obj.put("SAName", this.SAName);
            obj.put("SATitle", this.SATitle);
            obj.put("SADayTimePhone", this.SADayTimePhone);

            obj1.put("BookCareOf", obj);

        } catch (JSONException e) {

            new SendException(mContext, e);

            e.printStackTrace();

        }
        Log.i("Books Request", "GET" + obj1.toString());

        Log.i("Books URL", "GET" + ApplicationConfig.SAVE_BOOK_IN_CARE_OF);

        return obj1.toString();


    }
}
