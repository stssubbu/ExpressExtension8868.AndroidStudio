package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.AddBussinessInputModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 11/11/2015.
 */
public class BusinessListParse {


    Vector<AddBussinessInputModel> returnobject;


    public Vector<AddBussinessInputModel> parse(String jsstring) {
        if (jsstring != null) {

            System.out.println("JSONSTRING for business list " + jsstring);

            try {
                JSONObject Obj = new JSONObject(jsstring);

                JSONArray jsonarrayObj = new JSONArray();

                jsonarrayObj = Obj.getJSONArray("BusinessDetailList");

                if (jsonarrayObj != null && jsonarrayObj.length() > 0) {

                    returnobject = new Vector<AddBussinessInputModel>();

                    for (int x = 0; x < jsonarrayObj.length(); x++) {

                        try {

                            JSONObject jsonObj = new JSONObject(jsonarrayObj.getString(x));


                            AddBussinessInputModel addbussinessinputmodel = new AddBussinessInputModel();

                            addbussinessinputmodel.setOS(jsonObj.getString("OS"));
                            addbussinessinputmodel.setEM(jsonObj.getString("EM"));
                            addbussinessinputmodel.setOrganizationName(jsonObj.getString("BN"));
                            addbussinessinputmodel.setDoingBusinessas(jsonObj.getString("DBA"));
                            addbussinessinputmodel.setEin(jsonObj.getString("EIN"));
                            addbussinessinputmodel.setBId(jsonObj.getString("BId"));

                            addbussinessinputmodel.setFormOfOrganizationId(jsonObj.getString("FOOID"));
                            addbussinessinputmodel.setFormOfOrganizationOther(jsonObj.getString("FOOTHER"));

                            /****** Address Details ******/

                            addbussinessinputmodel.setAddressOutSideus_Address(jsonObj.getString("ISFORADD"));        // Check US address or Not
                            addbussinessinputmodel.setAddress_Address1(jsonObj.getString("ADD1"));
                            addbussinessinputmodel.setAddress_Address2(jsonObj.getString("ADD2"));
                            addbussinessinputmodel.setAddress_City(jsonObj.getString("CTY"));
                            addbussinessinputmodel.setAddressStateID(jsonObj.getString("SID"));// IF "US" Address
                            addbussinessinputmodel.setAddressStateShort(jsonObj.getString("SC"));


                            addbussinessinputmodel.setAddress_Stateorprovince(jsonObj.getString("SN"));    // IF "OutSide US" Address
                            addbussinessinputmodel.setAddressCountryId(jsonObj.getString("CID"));                    // IF "OutSide US" Address
                            addbussinessinputmodel.setAddress_Zipcode(jsonObj.getString("ZIP"));
                            addbussinessinputmodel.setAddress_Emailaddress(jsonObj.getString("EA"));
                            addbussinessinputmodel.setAddress_Websiteaddress(jsonObj.getString("WA"));
                            addbussinessinputmodel.setAddress_Phonenumber(jsonObj.getString("PH"));

                            /****** Principal Officer Details ******/

                            addbussinessinputmodel.setPrincipalOfcName(jsonObj.getString("PON"));
                            addbussinessinputmodel.setPrincipalOfcPhonenumber(jsonObj.getString("POPH"));

                            addbussinessinputmodel.setPrincipal_Address_Same_As_Business(jsonObj.getString("ISSAME"));        // Check Same Address or Not
                            addbussinessinputmodel.setPrincipal_Address_Outside_Us(jsonObj.getString("POISFORADD"));                    // Check US address or Not

                            addbussinessinputmodel.setPrincipalAddress1(jsonObj.getString("POADD1"));
                            addbussinessinputmodel.setPrincipalAddress2(jsonObj.getString("POADD2"));
                            addbussinessinputmodel.setPrincipalCity(jsonObj.getString("POCTY"));
                            addbussinessinputmodel.setPrincipalStateId(jsonObj.getString("POSID"));                    // IF "US" Address

                            addbussinessinputmodel.setPrincipalStateorProvince(jsonObj.getString("POSN"));    // IF "OutSide US" Address
                            addbussinessinputmodel.setPrincipalCountryId(jsonObj.getString("POCID"));                // IF "OutSide US" Address

                            addbussinessinputmodel.setPrincipalZipcode(jsonObj.getString("POZIP"));
                            addbussinessinputmodel.setTZID(jsonObj.getString("TZID"));

                            returnobject.add(addbussinessinputmodel);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return returnobject;

                } else
                    return null;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;

    }
}
