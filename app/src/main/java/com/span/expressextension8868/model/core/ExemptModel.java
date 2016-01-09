package com.span.expressextension8868.model.core;

import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import org.json.JSONObject;

public class ExemptModel {

    String AT, DID, UID, RID, EODId, OS, EM, BId, TypeofSave;

    String organizationname, doingbusinessas, OtherDBANames, idonthaveein, ein, ssn, Addressaddress1, Addressaddress2, AddressCity, AddressStateid, AddressStateorProvince, AddressZipcode, AddressEmailaddress;

    String AddressCountryId, Addresstimezoneid, AddressisOutsideaddress, isitBusinesscheckbox, nameofPerson, phonenumber, BookAddressline1, BookAddressLine2, BookCity, BookStateid, BookStateorProvince;

    String BookisAddressoutSideUS, BookCountryId, BookZipcode, BookFaxnumber, Primaryname, Title, daytimePhonenumber, isaddresssameasBusiness;

    String formoforganization, formoforganizationOther, websiteaddress, address_phonenumber;

    public String getTypeofSave() {
        return TypeofSave;
    }

    public void setTypeofSave(String typeofSave) {
        TypeofSave = typeofSave;
    }

    public String getBId() {
        return BId;
    }

    public void setBId(String BId) {
        this.BId = BId;
    }

    public String getFormoforganizationOther() {
        return formoforganizationOther;
    }

    public void setFormoforganizationOther(String formoforganizationOther) {
        this.formoforganizationOther = formoforganizationOther;
    }

    public String getAddress_phonenumber() {
        return address_phonenumber;
    }

    public void setAddress_phonenumber(String address_phonenumber) {
        this.address_phonenumber = address_phonenumber;
    }

    public String getDoingbusinessas() {
        return doingbusinessas;
    }

    public void setDoingbusinessas(String doingbusinessas) {
        this.doingbusinessas = doingbusinessas;
    }

    public String getOtherDBANames() {
        return OtherDBANames;
    }

    public void setOtherDBANames(String otherDBANames) {
        OtherDBANames = otherDBANames;
    }

    public String getWebsiteaddress() {
        return websiteaddress;
    }

    public void setWebsiteaddress(String websiteaddress) {
        this.websiteaddress = websiteaddress;
    }

    public String getFormoforganization() {
        return formoforganization;
    }

    public void setFormoforganization(String formoforganization) {
        this.formoforganization = formoforganization;
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

    public String getEODId() {
        return EODId;
    }

    public void setEODId(String eODId) {
        EODId = eODId;
    }

    public String getIsaddresssameasBusiness() {
        return isaddresssameasBusiness;
    }

    public void setIsaddresssameasBusiness(String isaddresssameasBusiness) {
        this.isaddresssameasBusiness = isaddresssameasBusiness;
    }

    public String getBookisAddressoutSideUS() {
        return BookisAddressoutSideUS;
    }

    public void setBookisAddressoutSideUS(String bookisAddressoutSideUS) {
        BookisAddressoutSideUS = bookisAddressoutSideUS;
    }

    public String getAddressCountryId() {
        return AddressCountryId;
    }

    public void setAddressCountryId(String addressCountryId) {
        AddressCountryId = addressCountryId;
    }

    public String getBookCountryId() {
        return BookCountryId;
    }

    public void setBookCountryId(String bookCountryId) {
        BookCountryId = bookCountryId;
    }

    public String getAT() {
        return AT;
    }

    public void setAT(String aT) {
        AT = aT;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String dID) {
        DID = dID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String uID) {
        UID = uID;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String rID) {
        RID = rID;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getIdonthaveein() {
        return idonthaveein;
    }

    public void setIdonthaveein(String idonthaveein) {
        this.idonthaveein = idonthaveein;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddressaddress1() {
        return Addressaddress1;
    }

    public void setAddressaddress1(String addressaddress1) {
        Addressaddress1 = addressaddress1;
    }

    public String getAddressaddress2() {
        return Addressaddress2;
    }

    public void setAddressaddress2(String addressaddress2) {
        Addressaddress2 = addressaddress2;
    }

    public String getAddressCity() {
        return AddressCity;
    }

    public void setAddressCity(String addressCity) {
        AddressCity = addressCity;
    }

    public String getAddressStateid() {
        return AddressStateid;
    }

    public void setAddressStateid(String addressStateid) {
        AddressStateid = addressStateid;
    }

    public String getAddressStateorProvince() {
        return AddressStateorProvince;
    }

    public void setAddressStateorProvince(String addressStateorProvince) {
        AddressStateorProvince = addressStateorProvince;
    }

    public String getAddressZipcode() {
        return AddressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        AddressZipcode = addressZipcode;
    }

    public String getAddressEmailaddress() {
        return AddressEmailaddress;
    }

    public void setAddressEmailaddress(String addressEmailaddress) {
        AddressEmailaddress = addressEmailaddress;
    }

    public String getAddresstimezoneid() {
        return Addresstimezoneid;
    }

    public void setAddresstimezoneid(String addresstimezoneid) {
        Addresstimezoneid = addresstimezoneid;
    }

    public String getAddressisOutsideaddress() {
        return AddressisOutsideaddress;
    }

    public void setAddressisOutsideaddress(String addressisOutsideaddress) {
        AddressisOutsideaddress = addressisOutsideaddress;
    }

    public String getIsitBusinesscheckbox() {
        return isitBusinesscheckbox;
    }

    public void setIsitBusinesscheckbox(String isitBusinesscheckbox) {
        this.isitBusinesscheckbox = isitBusinesscheckbox;
    }

    public String getNameofPerson() {
        return nameofPerson;
    }

    public void setNameofPerson(String nameofPerson) {
        this.nameofPerson = nameofPerson;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBookAddressline1() {
        return BookAddressline1;
    }

    public void setBookAddressline1(String bookAddressline1) {
        BookAddressline1 = bookAddressline1;
    }

    public String getBookAddressLine2() {
        return BookAddressLine2;
    }

    public void setBookAddressLine2(String bookAddressLine2) {
        BookAddressLine2 = bookAddressLine2;
    }

    public String getBookCity() {
        return BookCity;
    }

    public void setBookCity(String bookCity) {
        BookCity = bookCity;
    }

    public String getBookStateid() {
        return BookStateid;
    }

    public void setBookStateid(String bookStateid) {
        BookStateid = bookStateid;
    }

    public String getBookStateorProvince() {
        return BookStateorProvince;
    }

    public void setBookStateorProvince(String bookStateorProvince) {
        BookStateorProvince = bookStateorProvince;
    }

    public String getBookZipcode() {
        return BookZipcode;
    }

    public void setBookZipcode(String bookZipcode) {
        BookZipcode = bookZipcode;
    }

    public String getBookFaxnumber() {
        return BookFaxnumber;
    }

    public void setBookFaxnumber(String bookFaxnumber) {
        BookFaxnumber = bookFaxnumber;
    }

    public String getPrimaryname() {
        return Primaryname;
    }

    public void setPrimaryname(String primaryname) {
        Primaryname = primaryname;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDaytimePhonenumber() {
        return daytimePhonenumber;
    }

    public void setDaytimePhonenumber(String daytimePhonenumber) {
        this.daytimePhonenumber = daytimePhonenumber;
    }


    public String getExemptorganizationSaveRequest() {

        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();

        try {
            obj.put("AT", this.AT);
            obj.put("DId", this.DID);
            obj.put("UId", this.UID);
            obj.put("BId", this.BId);
            obj.put("BN", this.organizationname);
            obj.put("DBA", this.doingbusinessas);
            obj.put("ODBA", this.getOtherDBANames());

            obj.put("EIN", this.ein);
            obj.put("ISFORADD", this.AddressisOutsideaddress);
            obj.put("ADD1", this.Addressaddress1);

            obj.put("ADD2", this.Addressaddress2);
            obj.put("CTY", this.AddressCity);

            obj.put("FOOID", this.formoforganization);
            obj.put("FOOTHER", this.formoforganizationOther);


            obj.put("SID", this.AddressStateid);
            obj.put("SN", this.AddressStateorProvince);
            obj.put("CID", this.AddressCountryId);
            obj.put("TZID", this.Addresstimezoneid);

            obj.put("ZIP", this.AddressZipcode);
            obj.put("PH", this.address_phonenumber);


            obj.put("EA", this.AddressEmailaddress);
            obj.put("WA", this.websiteaddress);


            obj.put("PON", this.nameofPerson);
            obj.put("POPH", this.phonenumber);

            obj.put("POISFORADD", this.BookisAddressoutSideUS);
            obj.put("ISSAME", this.isaddresssameasBusiness);
            obj.put("POADD1", this.BookAddressline1);
            obj.put("POADD2", this.BookAddressLine2);
            obj.put("POCTY", this.BookCity);
            obj.put("POSID", this.BookStateid);
            obj.put("POSN", this.BookStateorProvince);
            obj.put("POCID", this.BookCountryId);
            obj.put("POZIP", this.BookZipcode);
            obj.put("LGT", this.TypeofSave);

//            obj.put("SAName", this.Primaryname);
//            obj.put("SATitle", this.Title);
//            obj.put("SADayTimePhone", this.daytimePhonenumber);
//            obj.put("SSN", this.ssn);
//            obj.put("IsNoEIN", this.idonthaveein);


//            obj.put("IsBCOBusiness", this.isitBusinesscheckbox);
//            obj.put("Fax", this.BookFaxnumber);
//            obj.put("EmailAddress", this.AddressEmailaddress);


            obj1.put("Business", obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("getExemptorganizationSaveRequest", obj1.toString());
        Log.i("getExemptorganizationSaveRequest URL", ApplicationConfig.UPDATEEXEMPTORGANIZATIONDETAILS);

        return obj1.toString();

    }

}
