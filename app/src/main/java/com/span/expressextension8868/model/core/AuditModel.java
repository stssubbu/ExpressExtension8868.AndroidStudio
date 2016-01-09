package com.span.expressextension8868.model.core;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 11/30/2015.
 */
public class AuditModel {

    String OS, EM, AT, DId, UId, RID, BId;

    String EC, ET, ACT, CON;

    boolean ISFATAL;

    Vector<AuditModel> ErrorList, CautionList;

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String EM) {
        this.EM = EM;
    }

    public String getAT() {
        return AT;
    }

    public void setAT(String AT) {
        this.AT = AT;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String EC) {
        this.EC = EC;
    }

    public String getET() {
        return ET;
    }

    public void setET(String ET) {
        this.ET = ET;
    }

    public String getACT() {
        return ACT;
    }

    public void setACT(String ACT) {
        this.ACT = ACT;
    }

    public String getCON() {
        return CON;
    }

    public void setCON(String CON) {
        this.CON = CON;
    }

    public boolean ISFATAL() {
        return ISFATAL;
    }

    public void setISFATAL(boolean ISFATAL) {
        this.ISFATAL = ISFATAL;
    }

    public Vector<AuditModel> getErrorList() {
        return ErrorList;
    }

    public void setErrorList(Vector<AuditModel> errorList) {
        ErrorList = errorList;
    }

    public Vector<AuditModel> getCautionList() {
        return CautionList;
    }

    public void setCautionList(Vector<AuditModel> cautionList) {
        CautionList = cautionList;
    }

    public String getAuditDetails(Context mContext) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj1.put("Return", obj);

        } catch (JSONException e) {

            new SendException(mContext, e);

            e.printStackTrace();

        }
        Log.i("Audit Request", "GET" + obj1.toString());

        Log.i("Audit URL", "GET" + ApplicationConfig.AUDIT_FORM_8868);

        return obj1.toString();

    }
}
