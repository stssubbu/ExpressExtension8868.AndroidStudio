package com.span.expressextension8868.model.core;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by STS-099 on 11/27/2015.
 */
public class SummaryModel {

    String OS, EM, AT, DId, UId, RID, RN, BId, CTY, TY, PTY, TYBD, TYED;

    String ExtendedDueDate, ExtensionType, FC, FNAME, BN, EIN, ADD1, ADD2, CITY, SC, SID, SN, ZIP, FCID;

    boolean ISCTY, BCOIsForeignAddress, ISFORADD, IsGroupReturn, ISFC, ISPAID;

    String BookInCareOf, BCOAddress1, BCOAddress2, BCOCity, BCOCountry, BCOState, BCOStateCode, BCOZip;

    String GEN, GroupFilingType, HoldingCompaniesList;

    String TentativeTaxAmount, CreditAmount, BalanceDue;


    public boolean ISPAID() {
        return ISPAID;
    }

    public void setISPAID(boolean ISPAID) {
        this.ISPAID = ISPAID;
    }

    public boolean ISFC() {
        return ISFC;
    }

    public void setISFC(boolean ISFC) {
        this.ISFC = ISFC;
    }

    public String getFCID() {
        return FCID;
    }

    public void setFCID(String FCID) {
        this.FCID = FCID;
    }

    public boolean isBCOIsForeignAddress() {
        return BCOIsForeignAddress;
    }

    public void setBCOIsForeignAddress(boolean BCOIsForeignAddress) {
        this.BCOIsForeignAddress = BCOIsForeignAddress;
    }

    public String getRN() {
        return RN;
    }

    public void setRN(String RN) {
        this.RN = RN;
    }

    public boolean ISFORADD() {
        return ISFORADD;
    }

    public void setISFORADD(boolean ISFORADD) {
        this.ISFORADD = ISFORADD;
    }

    public boolean isGroupReturn() {
        return IsGroupReturn;
    }

    public void setIsGroupReturn(boolean isGroupReturn) {
        IsGroupReturn = isGroupReturn;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getBN() {
        return BN;
    }

    public void setBN(String BN) {
        this.BN = BN;
    }

    public String getEIN() {
        return EIN;
    }

    public void setEIN(String EIN) {
        this.EIN = EIN;
    }

    public String getADD1() {
        return ADD1;
    }

    public void setADD1(String ADD1) {
        this.ADD1 = ADD1;
    }

    public String getADD2() {
        return ADD2;
    }

    public void setADD2(String ADD2) {
        this.ADD2 = ADD2;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String SC) {
        this.SC = SC;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }


    public String getBookInCareOf() {
        return BookInCareOf;
    }

    public void setBookInCareOf(String bookInCareOf) {
        BookInCareOf = bookInCareOf;
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

    public String getBCOZip() {
        return BCOZip;
    }

    public void setBCOZip(String BCOZip) {
        this.BCOZip = BCOZip;
    }


    public String getGEN() {
        return GEN;
    }

    public void setGEN(String GEN) {
        this.GEN = GEN;
    }

    public String getGroupFilingType() {
        return GroupFilingType;
    }

    public void setGroupFilingType(String groupFilingType) {
        GroupFilingType = groupFilingType;
    }

    public String getHoldingCompaniesList() {
        return HoldingCompaniesList;
    }

    public void setHoldingCompaniesList(String holdingCompaniesList) {
        HoldingCompaniesList = holdingCompaniesList;
    }

    public String getTentativeTaxAmount() {
        return TentativeTaxAmount;
    }

    public void setTentativeTaxAmount(String tentativeTaxAmount) {
        TentativeTaxAmount = tentativeTaxAmount;
    }

    public String getCreditAmount() {
        return CreditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        CreditAmount = creditAmount;
    }

    public String getBalanceDue() {
        return BalanceDue;
    }

    public void setBalanceDue(String balanceDue) {
        BalanceDue = balanceDue;
    }

    public boolean ISCTY() {
        return ISCTY;
    }

    public void setISCTY(boolean ISCTY) {
        this.ISCTY = ISCTY;
    }

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

    public String getCTY() {
        return CTY;
    }

    public void setCTY(String CTY) {
        this.CTY = CTY;
    }

    public String getTY() {
        return TY;
    }

    public void setTY(String TY) {
        this.TY = TY;
    }

    public String getPTY() {
        return PTY;
    }

    public void setPTY(String PTY) {
        this.PTY = PTY;
    }

    public String getTYBD() {
        return TYBD;
    }

    public void setTYBD(String TYBD) {
        this.TYBD = TYBD;
    }

    public String getTYED() {
        return TYED;
    }

    public void setTYED(String TYED) {
        this.TYED = TYED;
    }

    public String getExtendedDueDate() {
        return ExtendedDueDate;
    }

    public void setExtendedDueDate(String extendedDueDate) {
        ExtendedDueDate = extendedDueDate;
    }

    public String getExtensionType() {
        return ExtensionType;
    }

    public void setExtensionType(String extensionType) {
        ExtensionType = extensionType;
    }

    public String getFC() {
        return FC;
    }

    public void setFC(String FC) {
        this.FC = FC;
    }

    public String getSummaryDetails(Context mContext) {

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
        Log.i("Summary Request", "GET" + obj1.toString());

        Log.i("Summary URL", "GET" + ApplicationConfig.GET_SUMMARY_DETAILS);

        return obj1.toString();

    }
}
