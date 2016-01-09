package com.span.expressextension8868.model.core;

import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class ReturnModel {

    String OS, RN, TY, TP, CTY, ISCTY, ETID, FC, ISHOLDING, ORE, TYBD, TYED, ISIR, ISAPCH, ISCR, ISOR, ISUR, ISFR, ISASCORP, ACDD, EXDD, ISSHORT, EM, ISFC, UDTS, FSID, ISPAID;

    String REM, AN, EC, Length, PTY, IsCurrentYear, IsAddThreeMonth, ISOUTCOUNTRY, GEN;

    String ExtendedDueDate, GroupFilingType, Reason, ReasonCode, IsGroupReturn, EODId, ExtensionType;

    String UId, RID, DId, AT, BId;

    String FID, FNAME, ISLESS, IST, TES;

    Vector<ReturnModel> currentYearVector, previoudYearVector, prePreviousYearVector;

    Vector<AuditModel> ErrorVector;


    boolean ISAF;

    public boolean ISAF() {
        return ISAF;
    }

    public void setISAF(boolean ISAF) {
        this.ISAF = ISAF;
    }

    public Vector<AuditModel> getErrorVector() {
        return ErrorVector;
    }

    public void setErrorVector(Vector<AuditModel> errorVector) {
        ErrorVector = errorVector;
    }

    public Vector<ReturnModel> getCurrentYearVector() {
        return currentYearVector;
    }

    public void setCurrentYearVector(Vector<ReturnModel> currentYearVector) {
        this.currentYearVector = currentYearVector;
    }

    public Vector<ReturnModel> getPrevioudYearVector() {
        return previoudYearVector;
    }

    public void setPrevioudYearVector(Vector<ReturnModel> previoudYearVector) {
        this.previoudYearVector = previoudYearVector;
    }

    public Vector<ReturnModel> getPrePreviousYearVector() {
        return prePreviousYearVector;
    }

    public void setPrePreviousYearVector(Vector<ReturnModel> prePreviousYearVector) {
        this.prePreviousYearVector = prePreviousYearVector;
    }

    BookIncareOfModel parseModel;

    public BookIncareOfModel getParseModel() {
        return parseModel;
    }

    public void setParseModel(BookIncareOfModel parseModel) {
        this.parseModel = parseModel;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getISLESS() {
        return ISLESS;
    }

    public void setISLESS(String ISLESS) {
        this.ISLESS = ISLESS;
    }

    public String getIST() {
        return IST;
    }

    public void setIST(String IST) {
        this.IST = IST;
    }

    public String getTES() {
        return TES;
    }

    public void setTES(String TES) {
        this.TES = TES;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getExtensionType() {
        return ExtensionType;
    }

    public void setExtensionType(String extensionType) {
        ExtensionType = extensionType;
    }

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }

    public String getTP() {
        return TP;
    }

    public void setTP(String TP) {
        this.TP = TP;
    }

    public String getEODId() {
        return EODId;
    }

    public void setEODId(String eODId) {
        EODId = eODId;
    }

    public String getExtendedDueDate() {
        return ExtendedDueDate;
    }

    public void setExtendedDueDate(String extendedDueDate) {
        ExtendedDueDate = extendedDueDate;
    }

    public String getGroupFilingType() {
        return GroupFilingType;
    }

    public void setGroupFilingType(String groupFilingType) {
        GroupFilingType = groupFilingType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getReasonCode() {
        return ReasonCode;
    }

    public void setReasonCode(String reasonCode) {
        ReasonCode = reasonCode;
    }

    public String getIsGroupReturn() {
        return IsGroupReturn;
    }

    public void setIsGroupReturn(String isGroupReturn) {
        IsGroupReturn = isGroupReturn;
    }

    public String getGEN() {
        return GEN;
    }

    public void setGEN(String gEN) {
        GEN = gEN;
    }

    public String getISOUTCOUNTRY() {
        return ISOUTCOUNTRY;
    }

    public void setISOUTCOUNTRY(String iSOUTCOUNTRY) {
        ISOUTCOUNTRY = iSOUTCOUNTRY;
    }

    public String getCTY() {
        return CTY;
    }

    public void setCTY(String cTY) {
        CTY = cTY;
    }

    public String getIsAddThreeMonth() {
        return IsAddThreeMonth;
    }

    public void setIsAddThreeMonth(String isAddThreeMonth) {
        IsAddThreeMonth = isAddThreeMonth;
    }

    public String getIsCurrentYear() {
        return IsCurrentYear;
    }

    public void setIsCurrentYear(String isCurrentYear) {
        IsCurrentYear = isCurrentYear;
    }

    public String getPTY() {
        return PTY;
    }

    public void setPTY(String pTY) {
        PTY = pTY;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    Vector<ReturnModel> MRREs;

    public String getREM() {
        return REM;
    }

    public void setREM(String rEM) {
        REM = rEM;
    }

    public String getAN() {
        return AN;
    }

    public void setAN(String aN) {
        AN = aN;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String eC) {
        EC = eC;
    }

    public Vector<ReturnModel> getMRREs() {
        return MRREs;
    }

    public void setMRREs(Vector<ReturnModel> mRREs) {
        MRREs = mRREs;
    }

    public String getISPAID() {
        return ISPAID;
    }

    public void setISPAID(String iSPAID) {
        ISPAID = iSPAID;
    }

    public String getFSID() {
        return FSID;
    }

    public void setFSID(String fSID) {
        FSID = fSID;
    }

    public String getUDTS() {
        return UDTS;
    }

    public void setUDTS(String uDTS) {
        UDTS = uDTS;
    }

    public String getEM() {
        return EM;
    }

    public String getISFC() {
        return ISFC;
    }

    public void setISFC(String iSFC) {
        ISFC = iSFC;
    }

    public void setEM(String eM) {
        EM = eM;
    }

    public String getACDD() {
        return ACDD;
    }

    public void setACDD(String aCDD) {
        ACDD = aCDD;
    }

    public String getEXDD() {
        return EXDD;
    }

    public void setEXDD(String eXDD) {
        EXDD = eXDD;
    }

    public String getISSHORT() {
        return ISSHORT;
    }

    public void setISSHORT(String iSSHORT) {
        ISSHORT = iSSHORT;
    }

    public String getISASCORP() {
        return ISASCORP;
    }

    public void setISASCORP(String iSASCORP) {
        ISASCORP = iSASCORP;
    }

    public String getISIR() {
        return ISIR;
    }

    public void setISIR(String iSIR) {
        ISIR = iSIR;
    }

    public String getISAPCH() {
        return ISAPCH;
    }

    public void setISAPCH(String iSAPCH) {
        ISAPCH = iSAPCH;
    }

    public String getISCR() {
        return ISCR;
    }

    public void setISCR(String iSCR) {
        ISCR = iSCR;
    }

    public String getISOR() {
        return ISOR;
    }

    public void setISOR(String iSOR) {
        ISOR = iSOR;
    }

    public String getISUR() {
        return ISUR;
    }

    public void setISUR(String iSUR) {
        ISUR = iSUR;
    }

    public String getISFR() {
        return ISFR;
    }

    public void setISFR(String iSFR) {
        ISFR = iSFR;
    }


    public String getUId() {
        return UId;
    }

    public void setUId(String uId) {
        UId = uId;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String rID) {
        RID = rID;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String dId) {
        DId = dId;
    }

    public String getAT() {
        return AT;
    }

    public void setAT(String aT) {
        AT = aT;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String oS) {
        OS = oS;
    }

    public String getRN() {
        return RN;
    }

    public void setRN(String rN) {
        RN = rN;
    }

    public String getTY() {
        return TY;
    }

    public void setTY(String tY) {
        TY = tY;
    }


    public String getISCTY() {
        return ISCTY;
    }

    public void setISCTY(String iSCTY) {
        ISCTY = iSCTY;
    }

    public String getETID() {
        return ETID;
    }

    public void setETID(String eTID) {
        ETID = eTID;
    }

    public String getFC() {
        return FC;
    }

    public void setFC(String fC) {
        FC = fC;
    }

    public String getISHOLDING() {
        return ISHOLDING;
    }

    public void setISHOLDING(String iSHOLDING) {
        ISHOLDING = iSHOLDING;
    }

    public String getORE() {
        return ORE;
    }

    public void setORE(String oRE) {
        ORE = oRE;
    }

    public String getTYBD() {
        return TYBD;
    }

    public void setTYBD(String tYBD) {
        TYBD = tYBD;
    }

    public String getTYED() {
        return TYED;
    }

    public void setTYED(String tYED) {
        TYED = tYED;
    }


    public String GetSaveReturnDetailsRequest() {
        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj.put("FC", this.FC);

            obj.put("BId", this.BId);

            obj.put("IsCurrentYear", IsCurrentYear);

            obj.put("EODId", this.EODId);

            obj.put("ISCTY", this.ISCTY);

            obj.put("TYBD", this.TYBD);

            obj.put("TYED", this.TYED);

            obj.put("ISIR", this.ISIR);

            obj.put("ISFR", this.ISFR);

            obj.put("ISAPCH", this.ISAPCH);

            obj.put("ISFC", this.ISFC);

            obj.put("TY", this.TY);

            obj1.put("Return", obj);
        } catch (JSONException e) {

            e.printStackTrace();

        }
        Log.i("Return Details Save Request", obj1.toString());

        Log.i("Return Details Request URL", ApplicationConfig.SAVETAXYEARDETAILS);

        return obj1.toString();
    }


    public String GetSaveExtensionTypeRequest() {
        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj.put("EODId", this.EODId);

            obj.put("TY", this.TY);

            obj.put("TYBD", this.TYBD);

            obj.put("TYED", this.TYED);


            obj.put("IsAddThreeMonth", this.IsAddThreeMonth);

            obj.put("ExtensionType", this.ExtensionType);

            obj.put("ExtendedDueDate", this.ExtendedDueDate);

            obj.put("ISFC", this.ISFC);

            obj.put("IsGroupReturn", this.IsGroupReturn);

            obj.put("GEN", this.GEN);

            obj.put("GroupFilingType", this.GroupFilingType);

            obj.put("ReasonCode", this.ReasonCode);

            obj.put("Reason", this.Reason);


            obj1.put("Return", obj);
        } catch (JSONException e) {

            e.printStackTrace();

        }
        Log.i("Return Details Save Request", obj1.toString());

        Log.i("Return Details Request URL", ApplicationConfig.SAVE8868EXTENSIONDETAILS);

        return obj1.toString();
    }

    public String allowReturnTypeRequest() {
        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj.put("BId", this.BId);


            obj.put("ExtensionType", this.ExtensionType);

            obj.put("FC", this.FC);

            obj.put("TY", this.TY);


            obj1.put("Return", obj);
        } catch (JSONException e) {

            e.printStackTrace();

        }
        Log.i("allowReturnTypeRequest", "Request : " + obj1.toString());

        Log.i("allowReturnTypeRequest", " Request URL : " + ApplicationConfig.ALLOW_RETURN_BY_ID);

        return obj1.toString();
    }

    public String GetReturnDetailsRequest() {
        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);

            obj.put("UId", this.UId);

            obj.put("DId", this.DId);

            obj.put("RID", this.RID);

            obj1.put("Return", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Return Details Request", obj1.toString());

        Log.i("Return Details Request URL", ApplicationConfig.GETRETURNDETAILSBYRETURNID);

        return obj1.toString();
    }
}


