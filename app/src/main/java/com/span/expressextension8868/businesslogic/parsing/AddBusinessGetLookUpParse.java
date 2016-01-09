package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.AddBussinessInputModel;

import org.json.JSONObject;

import java.util.Vector;

public class AddBusinessGetLookUpParse {

    String FOOTHER, EIN, ADD1, ADD2, BN, CID, CTY, DBA, EA, FOOID, ISFORADD, ISSAME, PH, POADD1, POADD2, POCID, POCTY, POISFORADD, PON, POPH, POSID, POSN, POZIP, SID, SN, ZIP, WA, TZID, OS, EM;

    Vector<AddBussinessInputModel> returnobject = new Vector<AddBussinessInputModel>();

    public Vector<AddBussinessInputModel> parse(String jsstring) {
        if (jsstring != null) {

            System.out.println("JSONSTRING " + jsstring);

            try {
                JSONObject jsonObj = new JSONObject(jsstring);

                OS = jsonObj.getString("OS");
                EM = jsonObj.getString("EM");
                EIN = jsonObj.getString("EIN");

                System.out.println("EIN " + EIN);

                ADD1 = jsonObj.getString("ADD1");
                ADD2 = jsonObj.getString("ADD2");
                BN = jsonObj.getString("BN");
                CID = jsonObj.getString("CID");
                CTY = jsonObj.getString("CTY");
                DBA = jsonObj.getString("DBA");
                EA = jsonObj.getString("EA");
                FOOID = jsonObj.getString("FOOID");
                ISFORADD = jsonObj.getString("ISFORADD");
                ISSAME = jsonObj.getString("ISSAME");
                PH = jsonObj.getString("PH");
                POADD1 = jsonObj.getString("POADD1");
                POADD2 = jsonObj.getString("POADD2");
                POCID = jsonObj.getString("POCID");
                POCTY = jsonObj.getString("POCTY");
                POISFORADD = jsonObj.getString("POISFORADD");
                PON = jsonObj.getString("PON");
                POPH = jsonObj.getString("POPH");
                POSID = jsonObj.getString("POSID");
                POSN = jsonObj.getString("POSN");
                POZIP = jsonObj.getString("POZIP");
                SID = jsonObj.getString("SID");
                SN = jsonObj.getString("SN");
                ZIP = jsonObj.getString("ZIP");
                WA = jsonObj.getString("WA");
                FOOTHER = jsonObj.getString("FOOTHER");
                TZID = jsonObj.getString("TZID");

                AddBussinessInputModel addbussinessinputmodel = new AddBussinessInputModel();

                addbussinessinputmodel.setOS(OS);
                addbussinessinputmodel.setEM(EM);
                addbussinessinputmodel.setOrganizationName(BN);
                addbussinessinputmodel.setDoingBusinessas(DBA);
                addbussinessinputmodel.setEin(EIN);
                addbussinessinputmodel.setFormOfOrganizationId(FOOID);
                addbussinessinputmodel.setFormOfOrganizationOther(FOOTHER);

                /****** Address Details ******/

                addbussinessinputmodel.setAddressOutSideus_Address(ISFORADD);        // Check US address or Not
                addbussinessinputmodel.setAddress_Address1(ADD1);
                addbussinessinputmodel.setAddress_Address2(ADD2);
                addbussinessinputmodel.setAddress_City(CTY);
                addbussinessinputmodel.setAddressStateID(SID);                        // IF "US" Address

                addbussinessinputmodel.setAddress_Stateorprovince(SN);    // IF "OutSide US" Address
                addbussinessinputmodel.setAddressCountryId(CID);                    // IF "OutSide US" Address
                addbussinessinputmodel.setAddress_Zipcode(ZIP);
                addbussinessinputmodel.setAddress_Emailaddress(EA);
                addbussinessinputmodel.setAddress_Websiteaddress(WA);
                addbussinessinputmodel.setAddress_Phonenumber(PH);

                /****** Principal Officer Details ******/

                addbussinessinputmodel.setPrincipalOfcName(PON);
                addbussinessinputmodel.setPrincipalOfcPhonenumber(POPH);

                addbussinessinputmodel.setPrincipal_Address_Same_As_Business(ISSAME);        // Check Same Address or Not
                addbussinessinputmodel.setPrincipal_Address_Outside_Us(POISFORADD);                    // Check US address or Not

                addbussinessinputmodel.setPrincipalAddress1(POADD1);
                addbussinessinputmodel.setPrincipalAddress2(POADD2);
                addbussinessinputmodel.setPrincipalCity(POCTY);
                addbussinessinputmodel.setPrincipalStateId(POSID);                    // IF "US" Address

                addbussinessinputmodel.setPrincipalStateorProvince(POSN);    // IF "OutSide US" Address
                addbussinessinputmodel.setPrincipalCountryId(POCID);                // IF "OutSide US" Address

                addbussinessinputmodel.setPrincipalZipcode(POZIP);
                addbussinessinputmodel.setTZID(TZID);


                returnobject.add(addbussinessinputmodel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnobject;

    }
}
