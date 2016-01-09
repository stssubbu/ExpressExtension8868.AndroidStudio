package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by STS-099 on 11/20/2015.
 */
public class IRSReturnModel {

    String OS, EM, RID, TOTBALDUE, TOTPAYMENTS, TOTTAX;

    String AT, UId, DId, BId;

    String IRSPaymentOptionId, IRSPaymentId, AccountNumber, AccountTypeID, RTN, PaymentDateStr, TaxpayerDayTimePhone, EFWPin, IsEFWConsent;

    String Amount;


    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getIRSPaymentOptionId() {
        return IRSPaymentOptionId;
    }

    public void setIRSPaymentOptionId(String IRSPaymentOptionId) {
        this.IRSPaymentOptionId = IRSPaymentOptionId;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String EM) {
        this.EM = EM;
    }

    public String getIRSPaymentId() {
        return IRSPaymentId;
    }

    public void setIRSPaymentId(String IRSPaymentId) {
        this.IRSPaymentId = IRSPaymentId;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountTypeID() {
        return AccountTypeID;
    }

    public void setAccountTypeID(String accountTypeID) {
        AccountTypeID = accountTypeID;
    }

    public String getRTN() {
        return RTN;
    }

    public void setRTN(String RTN) {
        this.RTN = RTN;
    }

    public String getPaymentDateStr() {
        return PaymentDateStr;
    }

    public void setPaymentDateStr(String paymentDateStr) {
        PaymentDateStr = paymentDateStr;
    }

    public String getTaxpayerDayTimePhone() {
        return TaxpayerDayTimePhone;
    }

    public void setTaxpayerDayTimePhone(String taxpayerDayTimePhone) {
        TaxpayerDayTimePhone = taxpayerDayTimePhone;
    }

    public String getEFWPin() {
        return EFWPin;
    }

    public void setEFWPin(String EFWPin) {
        this.EFWPin = EFWPin;
    }

    public String getIsEFWConsent() {
        return IsEFWConsent;
    }

    public void setIsEFWConsent(String isEFWConsent) {
        IsEFWConsent = isEFWConsent;
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

    public String getTOTBALDUE() {
        return TOTBALDUE;
    }

    public void setTOTBALDUE(String TOTBALDUE) {
        this.TOTBALDUE = TOTBALDUE;
    }

    public String getTOTPAYMENTS() {
        return TOTPAYMENTS;
    }

    public void setTOTPAYMENTS(String TOTPAYMENTS) {
        this.TOTPAYMENTS = TOTPAYMENTS;
    }

    public String getTOTTAX() {
        return TOTTAX;
    }

    public void setTOTTAX(String TOTTAX) {
        this.TOTTAX = TOTTAX;
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

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }

    public String getBalaceDueDetail() {
        String jsonstring = null;

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", AT);

            obj.put("UId", UId);

            obj.put("DId", DId);

            obj.put("RID", RID);


            obj1.put("IRSPayment", obj);

            jsonstring = obj1.toString();

            Log.i("getBalanceDueDetail", "Request " + jsonstring);

            Log.i("getBalanceDueDetail", "URL " + ApplicationConfig.GET_USER_FOR_SIGNIN);

        } catch (JSONException e) {

            e.printStackTrace();

        }
        return jsonstring;
    }

    public String saveBalaceDueDetail() {
        String jsonstring = null;

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj.put("TOTBALDUE", this.TOTBALDUE);

            obj.put("TOTPAYMENTS", this.TOTPAYMENTS);

            obj.put("TOTTAX", this.TOTTAX);

            obj.put("IRSPaymentOptionId", this.IRSPaymentOptionId);
            obj.put("IRSPaymentId", this.IRSPaymentId);
            obj.put("AccountNumber", this.AccountNumber);
            obj.put("AccountTypeID", this.AccountTypeID);
            obj.put("RTN", this.RTN);
            obj.put("PaymentDateStr", this.PaymentDateStr);
            obj.put("TaxpayerDayTimePhone", this.TaxpayerDayTimePhone);
            obj.put("Amount", this.Amount);
            obj.put("EFWPin", this.EFWPin);


            obj1.put("IRSPayment", obj);

            jsonstring = obj1.toString();

            Log.i("SaveBalanceDueDetail", "Request " + jsonstring);


        } catch (JSONException e) {

            e.printStackTrace();

        }

        return jsonstring;
    }

    public String saveIRSPaymentDetail() {
        String jsonstring = null;

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", AT);

            obj.put("UId", UId);

            obj.put("DId", DId);

            obj.put("RID", RID);

            obj.put("TOTBALDUE", TOTBALDUE);

            obj.put("TOTPAYMENTS", TOTPAYMENTS);

            obj.put("TOTTAX", TOTTAX);

            obj.put("IRSPaymentOptionId", IRSPaymentOptionId);
            obj.put("IRSPaymentId", IRSPaymentId);
            obj.put("AccountNumber", AccountNumber);
            obj.put("AccountTypeID", AccountTypeID);
            obj.put("RTN", RTN);
            obj.put("PaymentDateStr", PaymentDateStr);
            obj.put("TaxpayerDayTimePhone", TaxpayerDayTimePhone);
            obj.put("Amount", Amount);
            obj.put("EFWPin", EFWPin);

            obj1.put("IRSPayment", obj);

            jsonstring = obj1.toString();

            Log.i("SaveBalanceDueDetail", "Request " + jsonstring);


        } catch (JSONException e) {

            e.printStackTrace();

        }

        return jsonstring;
    }
}
