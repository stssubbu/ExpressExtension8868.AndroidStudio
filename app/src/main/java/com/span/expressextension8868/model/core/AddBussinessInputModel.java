package com.span.expressextension8868.model.core;

import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONException;
import org.json.JSONObject;

public class AddBussinessInputModel {

    String organizationname, doingbusinessas, ein;

    String address_address1, address_address2, address_city, address_stateorprovince, address_zipcode, address_emailaddress, address_websiteaddress, address_phonenumber;

    String principalofcname, principalofcphonenumber, principaladdress1, principaladdress2, principalcity, principalstateorprovince, principalzipcode;

    String addressoutsideus_address, principal_address_same_as_business, principal_address_outside_us;

    String username, OS, EM;

    String formoforganizationother = "", formoforganizationid, addressstateid, addresscountryid, principalstateid, principalcountryid;

    String TZID;

    String Uid;

    String Bid, AT, DID;

    String osfield, em;

    String AddressStateShort;

    public String getAddressStateShort() {
        return AddressStateShort;
    }

    public void setAddressStateShort(String addressStateShort) {
        AddressStateShort = addressStateShort;
    }

    public String getosfield() {
        return osfield;
    }

    public void setosfield(String os_osfield) {
        osfield = os_osfield;
    }

    public String geterrormessage() {
        return this.em;
    }

    public void seterrormessage(String em) {
        this.em = em;
    }

    public String getEM() {
        return EM;
    }

    public void setEM(String eM) {
        EM = eM;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String oS) {
        OS = oS;
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

    public String getTZID() {
        return TZID;
    }

    public void setTZID(String tZID) {
        TZID = tZID;
    }

    public void setUId(String Uid) {
        this.Uid = Uid;
    }

    public String getUId() {
        return this.Uid;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }


    public String getFormOfOrganizationId() {
        return formoforganizationid;
    }

    public void setFormOfOrganizationId(String formoforganizationid) {
        this.formoforganizationid = formoforganizationid;
    }

    public String getAddressStateId() {
        return addressstateid;
    }

    public void setAddressStateID(String addressstatename) {
        this.addressstateid = addressstatename;
    }

    public String getAddressCountryId() {
        return addresscountryid;
    }

    public void setAddressCountryId(String addresscountryid) {
        this.addresscountryid = addresscountryid;

    }

    public String getPrincipalStateId() {
        return principalstateid;
    }

    public void setPrincipalStateId(String principalstateid) {
        this.principalstateid = principalstateid;

    }

    public String getPrincipalCountryId() {
        return principalcountryid;
    }

    public void setPrincipalCountryId(String principalcountryid) {

        this.principalcountryid = principalcountryid;
    }


    public String getAddressOutSideus_Address() {
        return addressoutsideus_address;
    }

    public void setAddressOutSideus_Address(String addressoutsideus_address) {
        if (addressoutsideus_address.equalsIgnoreCase("false")) {
            this.addressoutsideus_address = "false";
        } else {
            this.addressoutsideus_address = "true";
        }
    }

    public String getPrincipal_Address_Same_As_Business() {
        return principal_address_same_as_business;
    }

    public void setPrincipal_Address_Same_As_Business(String principal_address_same_as_business) {
        this.principal_address_same_as_business = principal_address_same_as_business;
    }

    public String getPrincipal_Address_Outside_Us() {
        return principal_address_outside_us;
    }

    public void setPrincipal_Address_Outside_Us(String principal_address_outside_us) {
        if (principal_address_outside_us.equalsIgnoreCase("false")) {
            this.principal_address_outside_us = "false";
        } else {
            this.principal_address_outside_us = "true";
        }
    }


    public String getOrganizationName() {
        return organizationname;
    }

    public void setOrganizationName(String organizationname) {
        this.organizationname = organizationname;
    }

    public void setDoingBusinessas(String doingbusinessas) {
        this.doingbusinessas = doingbusinessas;
    }

    public String getDoingBusinessas() {
        return doingbusinessas;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getEin() {
        return ein;
    }

    public void setAddress_Address1(String address_address1) {
        this.address_address1 = address_address1;
    }

    public String getAddress_Address1() {
        return address_address1;
    }

    public String getAddress_Address2() {
        return address_address2;
    }

    public void setAddress_Address2(String address_address2) {
        this.address_address2 = address_address2;
    }

    public String getAddress_City() {
        System.out.println("IN get city " + this.address_city);
        return this.address_city;

    }

    public void setAddress_City(String address_city) {
        this.address_city = address_city;
        System.out.println("IN set city " + address_city);
    }

    public String getAddress_Stateorprovince() {
        return address_stateorprovince;
    }

    public void setAddress_Stateorprovince(String address_stateorprovince) {
        this.address_stateorprovince = address_stateorprovince;

    }

    public String getAddress_Zipcode() {
        return address_zipcode;
    }

    public void setAddress_Zipcode(String address_zipcode) {
        this.address_zipcode = address_zipcode;
    }

    public String getAddress_Emailaddress() {
        return address_emailaddress;
    }

    public void setAddress_Emailaddress(String address_emailaddress) {
        this.address_emailaddress = address_emailaddress;
    }

    public String getAddress_Websiteaddress() {
        return address_websiteaddress;
    }

    public void setAddress_Websiteaddress(String address_websiteaddress) {
        this.address_websiteaddress = address_websiteaddress;
    }

    public String getAddress_Phonenumber() {
        return address_phonenumber;
    }

    public void setAddress_Phonenumber(String address_phonenumber) {
        this.address_phonenumber = address_phonenumber;
    }

    public String getPrincipalOfcName() {
        return principalofcname;
    }

    public void setPrincipalOfcName(String principalofcname) {
        this.principalofcname = principalofcname;
    }

    public String getPrincipalOfcPhonenumber() {
        return principalofcphonenumber;
    }

    public void setPrincipalOfcPhonenumber(String principalofcphonenumber) {
        this.principalofcphonenumber = principalofcphonenumber;
    }

    public String getPrincipalAddress1() {
        return principaladdress1;
    }

    public void setPrincipalAddress1(String principaladdress1) {
        this.principaladdress1 = principaladdress1;
    }

    public String getPrincipalAddress2() {
        return principaladdress2;
    }

    public void setPrincipalAddress2(String principaladdress2) {
        this.principaladdress2 = principaladdress2;
    }

    public String getPrincipalCity() {
        return principalcity;
    }

    public void setPrincipalCity(String principalcity) {
        this.principalcity = principalcity;
    }

    public String getPrincipalStateorProvince() {
        return principalstateorprovince;
    }

    public void setPrincipalStateorProvince(String principalstateorprovince) {
        this.principalstateorprovince = principalstateorprovince;
    }

    public String getPrincipalZipcode() {
        return principalzipcode;
    }

    public void setPrincipalZipcode(String principalzipcode) {
        this.principalzipcode = principalzipcode;
    }

    private String requestinput() {
        /*JSONObject obj=new JSONObject();
        JSONObject obj1=new JSONObject();
		
		
		/*try
		{
			/*
			 * 
			obj.put("UN", AppConfigManager.getUserName(mcontext));
			obj.put("BN", "STC");
			obj.put("DBA", "Software");
			obj.put("EIN", "12-3456999");
			obj.put("FOOID", "1");
			obj.put("ISFORADD", "0");						// CheckBox - Is address is Foreign
			obj.put("ADD1", "South Street, Washinton");
			obj.put("ADD2", "Near to Signal");
			obj.put("CTY", "WINDSOR CT");
			obj.put("SID", "15");
			obj.put("SN", " ");
			obj.put("CID", " ");
			obj.put("ZIP", "11854");
			obj.put("EA", "balabsmn@gmail.com");
			obj.put("WA", "www.spantechnologyandservices.com");
			obj.put("PH", "2344687874");
			obj.put("PON", "Peter");
			obj.put("POPH", "2344687874");
			obj.put("POISFORADD", "0");
			obj.put("ISSAME", "1");
			obj.put("POADD1", "South Street, Washinton");
			obj.put("POADD2", "Near to Signal");
			obj.put("POCTY", "WINDSOR CT");
			obj.put("POSID", "15");
			obj.put("POSN", " ");
			obj.put("POCID", " ");
			obj.put("POZIP", "11854");
			obj1.put("MobBusinessDetail", obj);
			
			
			
			
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Request : "+obj1.toString());
		*/
        return null;

    }

    public String getrequestforbusilnesslookup() {
        String jsonrequest = null;
        try {
            JSONObject requestobject = new JSONObject();

            requestobject.put("EIN", this.ein);

            requestobject.put("UId", this.Uid);

            requestobject.put("AT", this.AT);

            jsonrequest = requestobject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonrequest;

    }

    public String getjsonobj() {
        String jsonstring = null;
        try {
            JSONObject obj = new JSONObject();


            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("UId", this.Uid);

            obj.put("DId", this.DID);

            obj.put("BN", this.organizationname);

            obj.put("DBA", this.doingbusinessas);

            obj.put("EIN", this.ein);

            obj.put("ISFORADD", this.addressoutsideus_address);

            obj.put("ADD1", this.address_address1);

            obj.put("ADD2", this.address_address2);

            obj.put("CTY", this.address_city);

            obj.put("SID", this.addressstateid);

            obj.put("FOOID", this.formoforganizationid);

            obj.put("FOOTHER", this.formoforganizationother);

            obj.put("SN", this.address_stateorprovince);

            obj.put("CID", this.addresscountryid);

            obj.put("ZIP", this.address_zipcode);

            obj.put("EA", this.address_emailaddress);

            obj.put("WA", this.address_websiteaddress);

            obj.put("PH", this.address_phonenumber);

            obj.put("PON", this.principalofcname);

            obj.put("ISSAME", this.principal_address_same_as_business);

            obj.put("POISFORADD", this.principal_address_outside_us);

            obj.put("POPH", this.principalofcphonenumber);

            obj.put("POADD1", this.principaladdress1);

            obj.put("POADD2", this.principaladdress2);

            obj.put("POCTY", this.principalcity);

            obj.put("POSN", this.principalstateorprovince);

            obj.put("POZIP", this.principalzipcode);

            obj.put("POSID", this.principalstateid);

            obj.put("POCID", this.principalcountryid);

            obj.put("LGT", "ADD");

            obj.put("TZID", this.TZID);

            obj1.put("BusinessDetail", obj);


            jsonstring = obj1.toString();
            System.out.println("Json in Model " + obj1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }

    public String getJsonObjBusinessLookUP() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("EIN", this.ein);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public String getBusinessDetail() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("BId", this.Bid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    public String getBusinessListRequestModel() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public String getBusinessListDetailRequestModel() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("BId", this.Bid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    public String getBusinessReturnListDetailRequestModel() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("BId", this.Bid);

            obj1.put("Return", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    public String getjsonobjEdit() {
        String jsonstring = null;
        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("BId", this.Bid);

            obj.put("BN", this.organizationname);

            obj.put("DBA", this.doingbusinessas);

            obj.put("EIN", this.ein);

            obj.put("ISFORADD", this.addressoutsideus_address);

            obj.put("ADD1", this.address_address1);

            obj.put("ADD2", this.address_address2);

            obj.put("CTY", this.address_city);

            obj.put("SID", this.addressstateid);

            obj.put("FOOID", this.formoforganizationid);

            obj.put("FOOTHER", this.formoforganizationother);

            obj.put("SN", this.address_stateorprovince);

            obj.put("CID", this.addresscountryid);

            obj.put("ZIP", this.address_zipcode);

            obj.put("EA", this.address_emailaddress);

            obj.put("WA", this.address_websiteaddress);

            obj.put("PH", this.address_phonenumber);

            obj.put("PON", this.principalofcname);

            obj.put("ISSAME", this.principal_address_same_as_business);

            obj.put("POISFORADD", this.principal_address_outside_us);

            obj.put("POPH", this.principalofcphonenumber);

            obj.put("POADD1", this.principaladdress1);

            obj.put("POADD2", this.principaladdress2);

            obj.put("POCTY", this.principalcity);

            obj.put("POSN", this.principalstateorprovince);

            obj.put("POZIP", this.principalzipcode);

            obj.put("POSID", this.principalstateid);

            obj.put("POCID", this.principalcountryid);

            obj.put("LGT", "UPDATE");

            obj.put("OS", "Success");

            obj.put("TZID", this.TZID);

            obj1.put("BusinessDetail", obj);


            jsonstring = obj1.toString();

            System.out.println("Json in Model " + obj1.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }


    public void setFormOfOrganizationOther(String formoforganizationother) {
        this.formoforganizationother = formoforganizationother;

    }

    public String getFormOfOrganizationOther() {
        return this.formoforganizationother;

    }

    public void setBId(String bid2) {
        this.Bid = bid2;
    }

    public String getBId() {
        return this.Bid;
    }

    public String getBidjson() {
        String jsonstring = null;

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("BId", this.Bid);

            obj.put("AT", this.AT);

            obj.put("UId", this.Uid);

            obj.put("DId", this.DID);

            obj1.put("MobBusinessDetail", obj);

            jsonstring = obj1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonstring;
    }

    public String getAddressRequest() {

        String jsonstring = null;

        try {

            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("AT", this.AT);

            obj.put("DId", this.DID);

            obj.put("UId", this.Uid);

            obj.put("BId", this.Bid);

            obj1.put("Business", obj);

            jsonstring = obj1.toString();

            return jsonstring;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
}
