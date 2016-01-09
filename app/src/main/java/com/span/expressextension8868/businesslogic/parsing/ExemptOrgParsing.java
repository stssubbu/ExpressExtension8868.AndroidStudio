package com.span.expressextension8868.businesslogic.parsing;


import com.span.expressextension8868.model.core.ExemptModel;

import org.json.JSONObject;

public class ExemptOrgParsing {
    String OS, EM, RID, EODId, BId;

    String organizationname, idonthaveein, ein, ssn, Addressaddress1, Addressaddress2, AddressCity, AddressStateid, AddressStateorProvince, AddressZipcode, AddressEmailaddress;

    String AddressCountryId, Addresstimezoneid, AddressisOutsideaddress, isitBusinesscheckbox, nameofPerson, phonenumber, BookAddressline1, BookAddressLine2, BookCity, BookStateid, BookStateorProvince;

    String BookisAddressoutSideUS, BookCountryId, BookZipcode, BookFaxnumber, Primaryname, Title, daytimePhonenumber, isaddresssameasBusiness;

    ExemptModel exemptModel = new ExemptModel();

    public ExemptModel parse(String jsstring) {
        if (jsstring != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsstring);

                OS = jsonObj.getString("OS");

                EM = jsonObj.getString("EM");

                RID = jsonObj.getString("RID");

                BId = jsonObj.getString("BId");

                EODId = jsonObj.getString("EODId");

                organizationname = jsonObj.getString("BN");

                //idonthaveein = jsonObj.getString("IsNoEIN");

                ein = jsonObj.getString("EIN");

                //	ssn = jsonObj.getString("SN");

                Addressaddress1 = jsonObj.getString("ADD1");

                Addressaddress2 = jsonObj.getString("ADD2");

                AddressCity = jsonObj.getString("CTY");

                AddressStateid = jsonObj.getString("SID");

                AddressStateorProvince = jsonObj.getString("SN");

                AddressZipcode = jsonObj.getString("ZIP");

                AddressEmailaddress = jsonObj.getString("EA");

                AddressCountryId = jsonObj.getString("CID");

                Addresstimezoneid = jsonObj.getString("TZ");

                AddressisOutsideaddress = jsonObj.getString("ISFORADD");

                //isitBusinesscheckbox = jsonObj.getString("IsBCOBusiness");

                nameofPerson = jsonObj.getString("PON");

                phonenumber = jsonObj.getString("PH");

                BookAddressline1 = jsonObj.getString("POADD1");

                BookAddressLine2 = jsonObj.getString("POADD2");

                BookCity = jsonObj.getString("POCTY");

                BookStateid = jsonObj.getString("POSID");

                BookStateorProvince = jsonObj.getString("POSN");

                BookisAddressoutSideUS = jsonObj.getString("POISFORADD");

                BookCountryId = jsonObj.getString("POCID");

                BookZipcode = jsonObj.getString("POZIP");

                //BookFaxnumber = jsonObj.getString("Fax");

                //	Primaryname = jsonObj.getString("SAName");

                //	Title = jsonObj.getString("SATitle");

                //daytimePhonenumber = jsonObj.getString("SADayTimePhone");


                // Set values to model
                exemptModel.setOS(OS);

                exemptModel.setEM(EM);

                exemptModel.setRID(RID);

                exemptModel.setEODId(EODId);

                exemptModel.setBId(BId);

                exemptModel.setOrganizationname(organizationname);

                exemptModel.setIdonthaveein(idonthaveein);

                exemptModel.setEin(ein);

                exemptModel.setSsn(ssn);

                exemptModel.setAddressaddress1(Addressaddress1);

                exemptModel.setAddressaddress2(Addressaddress2);

                exemptModel.setAddressCity(AddressCity);

                exemptModel.setAddressStateid(AddressStateid);

                exemptModel.setAddressStateorProvince(AddressStateorProvince);

                exemptModel.setAddressZipcode(AddressZipcode);

                exemptModel.setAddressEmailaddress(AddressEmailaddress);

                exemptModel.setAddressCountryId(AddressCountryId);

                exemptModel.setAddresstimezoneid(Addresstimezoneid);

                exemptModel.setAddressisOutsideaddress(AddressisOutsideaddress);

                exemptModel.setIsitBusinesscheckbox(isitBusinesscheckbox);

                exemptModel.setNameofPerson(nameofPerson);

                exemptModel.setPhonenumber(phonenumber);

                exemptModel.setBookAddressline1(BookAddressline1);

                exemptModel.setBookAddressLine2(BookAddressLine2);

                exemptModel.setBookCity(BookCity);

                exemptModel.setBookStateid(BookStateid);

                exemptModel.setBookStateorProvince(BookStateorProvince);

                exemptModel.setBookisAddressoutSideUS(BookisAddressoutSideUS);

                exemptModel.setBookCountryId(BookCountryId);

                exemptModel.setBookZipcode(BookZipcode);

                exemptModel.setBookFaxnumber(BookFaxnumber);

                exemptModel.setPrimaryname(Primaryname);

                exemptModel.setTitle(Title);

                exemptModel.setDaytimePhonenumber(daytimePhonenumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return exemptModel;

    }
}
