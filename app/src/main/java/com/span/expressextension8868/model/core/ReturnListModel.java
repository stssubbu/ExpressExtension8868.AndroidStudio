package com.span.expressextension8868.model.core;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

public class ReturnListModel {

    String[] FSID, FN, RID, FID, ISPAID, UId, RN, UDT, TY, TYBD, TYED, IST, ISLESS, BIDarray, GEN, TES, TP, PON, POCID, POISFORADD, POSN, POZIP, PSC, POCTY, POADD1, POADD2;

    String BId, _RID, AT, DID, _UID, EM, OS, AN, EC;


    public String getPOADD1(int p) {
        return POADD1[p];
    }

    public void setPOADD1(String[] pOADD1) {
        POADD1 = pOADD1;
    }

    public String getPOADD2(int p) {
        return POADD2[p];
    }

    public void setPOADD2(String[] pOADD2) {
        POADD2 = pOADD2;
    }

    public String getPON(int p) {
        return PON[p];
    }

    public void setPON(String[] pON) {
        PON = pON;
    }

    public String getPOCID(int p) {
        return POCID[p];
    }

    public void setPOCID(String[] pOCID) {
        POCID = pOCID;
    }

    public String getPOISFORADD(int p) {
        return POISFORADD[p];
    }

    public void setPOISFORADD(String[] pOISFORADD) {
        POISFORADD = pOISFORADD;
    }

    public String getPOSN(int p) {
        return POSN[p];
    }

    public void setPOSN(String[] pOSN) {
        POSN = pOSN;
    }

    public String getPOZIP(int p) {
        return POZIP[p];
    }

    public void setPOZIP(String[] pOZIP) {
        POZIP = pOZIP;
    }

    public String getPSC(int p) {
        return PSC[p];
    }

    public void setPSC(String[] pSC) {
        PSC = pSC;
    }

    public String getPOCTY(int p) {
        return POCTY[p];
    }

    public void setPOCTY(String[] pOCTY) {
        POCTY = pOCTY;
    }

    public String getTP(int p) {
        return TP[p];
    }

    public void setTP(String[] tP) {
        TP = tP;
    }

    public String getFN(int p) {
        return FN[p];
    }

    public void setFN(String[] fN) {
        FN = fN;
    }

    public String getGEN(int p) {
        return GEN[p];
    }

    public void setGEN(String[] gEN) {
        GEN = gEN;
    }

    public String getTES(int p) {
        return TES[p];
    }

    public void setTES(String[] tES) {
        TES = tES;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String eC) {
        EC = eC;
    }

    public String getAN() {
        return AN;
    }

    public void setAN(String aN) {
        AN = aN;
    }

    Vector<ReturnListModel> MRREs = new Vector<ReturnListModel>();

    public Vector<ReturnListModel> getMRREs() {
        return MRREs;
    }

    public void setMRREs(Vector<ReturnListModel> mRREs) {
        MRREs = mRREs;
    }

    int length;

    public String getOS() {
        return OS;
    }

    public void setOS(String oS) {
        OS = oS;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String eM) {
        EM = eM;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String dID) {
        DID = dID;
    }

    public String getAT() {
        return AT;
    }

    public void setAT(String aT) {
        AT = aT;
    }

    public String get_RID() {
        return _RID;
    }

    public void set_RID(String _RID) {
        this._RID = _RID;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRN(int p) {
        return this.RN[p];
    }

    public void setRN(String[] RN) {
        this.RN = RN;
    }

    public String getTYBD(int p) {
        return this.TYBD[p];
    }

    public void setTYBD(String[] TYBD) {
        this.TYBD = TYBD;
    }

    public String getTYED(int p) {
        return this.TYED[p];
    }

    public void setTYED(String[] TYED) {
        this.TYED = TYED;
    }

    public String getIST(int p) {
        return this.IST[p];
    }

    public void setIST(String[] IST) {
        this.IST = IST;
    }

    public String getISLESS(int p) {
        return this.ISLESS[p];
    }

    public void setISLESS(String[] ISLESS) {
        this.ISLESS = ISLESS;
    }

    public String getUDT(int p) {
        return this.UDT[p];
    }

    public void setUDT(String[] UDT) {
        this.UDT = UDT;
    }

    public String getTaxyear(int p) {
        return this.TY[p];
    }

    public void setTaxyear(String[] TY) {
        this.TY = TY;
    }

    public String getBid() {
        return this.BId;
    }

    public void setBid(String Bid) {
        this.BId = Bid;
    }

    public String getFSID(int p) {
        return this.FSID[p];
    }

    public void setFSID(String[] FSID) {
        this.FSID = FSID;
    }

    public String getRID(int p) {
        return this.RID[p];
    }

    public void setRID(String[] RID) {
        this.RID = RID;
    }

    public String getFID(int p) {
        return this.FID[p];
    }

    public void setFID(String[] FID) {
        this.FID = FID;
    }

    public String getISPAID(int p) {
        return this.ISPAID[p];
    }

    public void setISPAID(String[] ISPAID) {
        this.ISPAID = ISPAID;
    }

    public String getUid(int p) {
        return this.UId[p];
    }

    public void setUid(String[] uid) {
        this.UId = uid;
    }

    public String getRIDJSON() {
        String jsonstring = null;

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        try {
            obj.put("RID", this._RID);
            obj1.put("Return", obj);
            jsonstring = obj1.toString();

            System.out.println("Request RID Json in Model: " + jsonstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }

    public String getBidjson() {
        String jsonstring = null;

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();
        try {
            obj.put("BId", this.BId);

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this._UID);

            obj1.put("Return", obj);

            jsonstring = obj1.toString();

            System.out.println("Request Json in Model: " + jsonstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }

    public void setBIDarray(String[] bID2) {
        this.BIDarray = bID2;

    }

    public String getBIDarray(int p) {
        return this.BIDarray[p];

    }

}
