package com.span.expressextension8868.model.core;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ManageBusinessModel1 {

    String[] businessname, name, POADD1, POADD2, phone, POCITY, Bid, dba,
            POSTATE, ein, POZIP, web, _POCID, _POSN, _POISFORADD, _ADD1, _ADD2,
            _CTY, _CID, _SC, _ZIP, ISFORADD, SN;

    String businessnamestring, einstring, ADD1, ADD2, SC, ZIP, PON, CTY,
            ISFORADD1;

    int length;

    String businessname1, name1, POCID, SN1, MODE, CANDEL;

    String ein1, CID;

    String uid, bname, AT, DId, EM, OS, IsCurrentYearFiled;

    String _businessname, _Add1, _Add2, _city, _sn, _zipcode, _websiteaddress,
            _phone, POISFORADD, POSN;

    public String getIsCurrentYearFiled() {
        return IsCurrentYearFiled;
    }

    public void setIsCurrentYearFiled(String isCurrentYearFiled) {
        IsCurrentYearFiled = isCurrentYearFiled;
    }

    public String getCANDEL() {
        return CANDEL;
    }

    public void setCANDEL(String cANDEL) {
        CANDEL = cANDEL;
    }

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

    public String getMODE() {
        System.out.println("GET MODE " + this.MODE);
        return this.MODE;
    }

    public void setMODE(String mODE) {
        System.out.println("SET MODE " + mODE);
        this.MODE = mODE;
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

    public String getSN1() {
        return SN1;
    }

    public void setSN1(String sN1) {
        SN1 = sN1;
    }

    public String getISFORADD1() {
        return ISFORADD1;
    }

    public void setISFORADD1(String iSFORADD1) {
        ISFORADD1 = iSFORADD1;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String cID) {
        CID = cID;
    }

    public String getSN(int p) {
        return SN[p];
    }

    public void setSN(String[] sN) {
        SN = sN;
    }

    public String getISFORADD(int p) {
        return ISFORADD[p];
    }

    public void setISFORADD(String[] iSFORADD) {
        ISFORADD = iSFORADD;
    }

    public String get_CTY(int p) {
        return _CTY[p];
    }

    public void set_CTY(String[] _CTY) {
        this._CTY = _CTY;
    }

    public String get_CID(int p) {
        return _CID[p];
    }

    public void set_CID(String[] _CID) {
        this._CID = _CID;
    }

    public String get_SC(int p) {
        return _SC[p];
    }

    public void set_SC(String[] _SC) {
        this._SC = _SC;
    }

    public String get_ZIP(int p) {
        return _ZIP[p];
    }

    public void set_ZIP(String[] _ZIP) {
        this._ZIP = _ZIP;
    }

    public String get_ADD1(int p) {
        return _ADD1[p];
    }

    public void set_ADD1(String[] _ADD1) {
        this._ADD1 = _ADD1;
    }

    public String get_ADD2(int p) {
        return _ADD2[p];
    }

    public void set_ADD2(String[] _ADD2) {
        this._ADD2 = _ADD2;
    }

    public String getPOCIDstring() {
        return this.POCID;
    }

    public void setPOCIDstring(String POCID) {
        this.POCID = POCID;
    }

    public String getPOSN() {
        return POSN;
    }

    public void setPOSN(String pOSN) {
        POSN = pOSN;
    }

    public String getPOISFORADD() {
        return POISFORADD;
    }

    public void setPOISFORADD(String pOISFORADD) {
        POISFORADD = pOISFORADD;
    }

    String _ein;

    String _name, _Bid, _DBA;

    public String getCTY() {
        return CTY;
    }

    public void setCTY(String cTY) {
        CTY = cTY;
    }

    public String getPON() {
        return PON;
    }

    public void setPON(String pON) {
        PON = pON;
    }

    public String getADD1() {
        return ADD1;
    }

    public void setADD1(String aDD1) {
        ADD1 = aDD1;
    }

    public String getADD2() {
        return ADD2;
    }

    public void setADD2(String aDD2) {
        ADD2 = aDD2;
    }

    public String getSC() {
        return SC;
    }

    public void setSC(String sC) {
        SC = sC;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String zIP) {
        ZIP = zIP;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        Log.e("IN GET LENGTH ", "GET LENGTH " + length);
        return this.length;
    }

    public void setBusinessname(String[] businessname) {

        this.businessname = businessname;
    }

    public String getBusinessname(int p) {

        return this.businessname[p];
    }

    public String[] getBusinessnamearray() {

        return this.businessname;
    }

    public void setEin(String[] ein2) {
        this.ein = ein2;
    }

    public String getEin(int p) {
        return this.ein[p];
    }

    public void setname(String[] name) {
        this.name = name;
    }

    public String getName(int p) {
        return this.name[p];
    }

    public void setBId(String[] Bid) {
        this.Bid = Bid;
    }

    public String getBId(int p) {
        return this.Bid[p];
    }

    public void setDBA(String[] dba) {
        this.dba = dba;
    }

    public String getDBA(int p) {
        return this.dba[p];
    }

    public void setAddressforchild1(String[] address) {

        this.POADD1 = address;
    }

    public String getaddressfrochild1(int group) {

        return this.POADD1[group];
    }

    public void setAddressforchild2(String[] address2) {

        this.POADD2 = address2;
    }

    public String getaddressfrochild2(int group) {

        return this.POADD2[group];
    }

    public String getstate(int p) {
        return this.POSTATE[p];
    }

    public void setstate(String[] state) {
        this.POSTATE = state;
    }

    public void setcity(String[] city) {
        this.POCITY = city;
    }

    public String getcity(int p) {
        return this.POCITY[p];
    }

    public void setzipcode(String[] zipcode) {
        this.POZIP = zipcode;
    }

    public String getzipcode(int p) {
        return this.POZIP[p];
    }

    public void setwebsiteaddress(String[] web) {
        this.web = web;
    }

    public String getwebsiteaddress(int p) {
        return this.web[p];
    }

    public void setphone(String[] name) {
        this.phone = name;
    }

    public String getphone(int p) {
        return this.phone[p];
    }

    public String getuidjson() {
        String jsonstring = null;

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("UId", this.uid);

            obj.put("AT", this.AT);

            obj.put("DId", this.DId);

            obj.put("BId", this._Bid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            Log.e("BusinessList Request: ", "Request" + jsonstring);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }

    public void setBusinessnamestring(String _businessname) {
        this._businessname = _businessname;

    }

    public void setEinstring(String _ein) {

        this._ein = _ein;
    }

    public void setnamestring(String _name) {

        this._name = _name;
    }

    public void setBIdstring(String _Bid) {

        this._Bid = _Bid;

    }

    public void setDBAstring(String _DBA) {

        this._DBA = _DBA;

    }

    public void setAddressforchild1string(String _Add1) {

        this._Add1 = _Add1;

    }

    public void setAddressforchild2string(String _Add2) {

        this._Add2 = _Add2;

    }

    public void setcitystring(String _city) {

        this._city = _city;

    }

    public void setstatestring(String _sn) {

        this._sn = _sn;

    }

    public void setzipcodestring(String _zipcode) {

        this._zipcode = _zipcode;

    }

    public void setwebsiteaddressstring(String _websiteaddress) {

        this._websiteaddress = _websiteaddress;

    }

    public void setphonestring(String _phone) {

        this._phone = _phone;

    }

    public String getBusinessnamestring() {
        return this._businessname;

    }

    public String getEinstring() {

        return this._ein;
    }

    public String getnamestring() {

        return this._name;
    }

    public String getBIdstring() {

        return this._Bid;

    }

    public String getDBAstring() {

        return this._DBA;

    }

    public String getAddressforchild1string() {

        return this._Add1;

    }

    public String getAddressforchild2string() {

        return this._Add2;

    }

    public String getcitystring() {

        return this._city;

    }

    public String getstatestring() {

        return this._sn;

    }

    public String getzipcodestring() {

        return this._zipcode;

    }

    public String getwebsiteaddressstring() {

        return this._websiteaddress;

    }

    public String getphonestring() {

        return this._phone;

    }

    public void setPOSN(String[] _POSN) {
        this._POSN = _POSN;

    }

    public String getPOSN(int p) {
        return this._POSN[p];
    }

    public void setPOCID(String[] _POCID) {
        this._POCID = _POCID;

    }

    public String getPOCID(int p) {
        return this._POCID[p];
    }

    public void setPOSISFORADD(String[] _POISFORADD) {
        this._POISFORADD = _POISFORADD;

    }

    public String getPOSISFORADD(int p) {
        return this._POISFORADD[p];

    }

    public String getBidjson() {
        String jsonstring = null;

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        try {
            obj.put("BId", this._Bid);

            obj.put("AT", this.AT);

            obj.put("DId", this.DId);

            obj.put("UId", this.uid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Business Detail Request", jsonstring);

        return jsonstring;
    }

}
