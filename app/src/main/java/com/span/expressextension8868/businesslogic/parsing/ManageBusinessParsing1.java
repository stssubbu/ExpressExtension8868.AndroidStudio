package com.span.expressextension8868.businesslogic.parsing;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.span.expressextension8868.model.core.ManageBusinessModel1;


public class ManageBusinessParsing1 {
    String[] businessname, POAdd1, POAdd2, POcity, sn, POzipcode, websiteaddress, phone, _POCID, _POSN, _POISFORADD, ISFORADD, SN;

    String[] ein;

    String[] name, Bid, DBA, _ADD1, _ADD2, _CTY, _CID, _SC, _ZIP;

    String _businessname, _POAdd1, _POAdd2, _POcity, _sn, _POzipcode, _websiteaddress, _phone, POISFORADD, ADD1, ADD2, SC, ZIP, PON, CTY, CANDEL;

    String _ein, CID, ISFORADD1, SN1, EM, OS;

    String _name, _Bid, _DBA, MODE = null;

    Vector<ManageBusinessModel1> returnobject = new Vector<ManageBusinessModel1>();

    Vector<ManageBusinessModel1> returnobject1 = new Vector<ManageBusinessModel1>();

    Vector<Vector<ManageBusinessModel1>> Vectorobject = new Vector<Vector<ManageBusinessModel1>>();

    ManageBusinessModel1 managebusinessodel = new ManageBusinessModel1();

    public Vector<Vector<ManageBusinessModel1>> parse(String jsstring) {
        if (jsstring != null) {
            try {

                JSONObject jsonObject = new JSONObject(jsstring);

                managebusinessodel.setOS(jsonObject.getString("OS"));

                managebusinessodel.setEM(jsonObject.getString("EM"));

                JSONArray jsonObj = new JSONArray();

                jsonObj = jsonObject.getJSONArray("BusinessDetailList");

                businessname = new String[jsonObj.length()];
                ein = new String[jsonObj.length()];
                name = new String[jsonObj.length()];
                Bid = new String[jsonObj.length()];
                DBA = new String[jsonObj.length()];
                POAdd1 = new String[jsonObj.length()];
                POAdd2 = new String[jsonObj.length()];
                POcity = new String[jsonObj.length()];
                sn = new String[jsonObj.length()];
                POzipcode = new String[jsonObj.length()];
                websiteaddress = new String[jsonObj.length()];
                phone = new String[jsonObj.length()];
                _POSN = new String[jsonObj.length()];
                _POCID = new String[jsonObj.length()];
                _POISFORADD = new String[jsonObj.length()];
                _ADD1 = new String[jsonObj.length()];
                _ADD2 = new String[jsonObj.length()];
                ISFORADD = new String[jsonObj.length()];
                SN = new String[jsonObj.length()];

                for (int x = 0; x < jsonObj.length(); x++) {

                    ManageBusinessModel1 managebusinessodel1 = new ManageBusinessModel1();

                    businessname[x] = jsonObj.getJSONObject(x).getString("BN");
                    ein[x] = jsonObj.getJSONObject(x).getString("EIN");
                    name[x] = jsonObj.getJSONObject(x).getString("PON");
                    Bid[x] = jsonObj.getJSONObject(x).getString("BId");
                    DBA[x] = jsonObj.getJSONObject(x).getString("DBA");
                    POAdd1[x] = jsonObj.getJSONObject(x).getString("POADD1");
                    POAdd2[x] = jsonObj.getJSONObject(x).getString("POADD2");
                    POcity[x] = jsonObj.getJSONObject(x).getString("POCTY");
                    sn[x] = jsonObj.getJSONObject(x).getString("PSC");
                    POzipcode[x] = jsonObj.getJSONObject(x).getString("POZIP");
                    websiteaddress[x] = jsonObj.getJSONObject(x).getString("WA");
                    phone[x] = jsonObj.getJSONObject(x).getString("POPH");
                    _POSN[x] = jsonObj.getJSONObject(x).getString("POSN");
                    _POCID[x] = jsonObj.getJSONObject(x).getString("POCID");
                    _POISFORADD[x] = jsonObj.getJSONObject(x).getString("POISFORADD");
                    _ADD1[x] = jsonObj.getJSONObject(x).getString("ADD1");
                    _ADD2[x] = jsonObj.getJSONObject(x).getString("ADD2");

                    _businessname = jsonObj.getJSONObject(x).getString("BN");
                    _ein = jsonObj.getJSONObject(x).getString("EIN");
                    _name = jsonObj.getJSONObject(x).getString("PON");
                    _Bid = jsonObj.getJSONObject(x).getString("BId");
                    _DBA = jsonObj.getJSONObject(x).getString("DBA");
                    _POAdd1 = jsonObj.getJSONObject(x).getString("POADD1");
                    _POAdd2 = jsonObj.getJSONObject(x).getString("POADD2");
                    _POcity = jsonObj.getJSONObject(x).getString("POCTY");
                    _sn = jsonObj.getJSONObject(x).getString("PSC");
                    _POzipcode = jsonObj.getJSONObject(x).getString("POZIP");
                    _websiteaddress = jsonObj.getJSONObject(x).getString("WA");
                    _phone = jsonObj.getJSONObject(x).getString("POPH");
                    POISFORADD = jsonObj.getJSONObject(x).getString("POISFORADD");
                    ADD1 = jsonObj.getJSONObject(x).getString("ADD1");
                    ADD2 = jsonObj.getJSONObject(x).getString("ADD2");
                    SC = jsonObj.getJSONObject(x).getString("SC");
                    ZIP = jsonObj.getJSONObject(x).getString("ZIP");
                    PON = jsonObj.getJSONObject(x).getString("PON");
                    CTY = jsonObj.getJSONObject(x).getString("CTY");
                    CID = jsonObj.getJSONObject(x).getString("CID");
                    ISFORADD1 = jsonObj.getJSONObject(x).getString("ISFORADD");
                    SN1 = jsonObj.getJSONObject(x).getString("SN");
                    MODE = jsonObj.getJSONObject(x).getString("MODE");
                    CANDEL = jsonObj.getJSONObject(x).getString("CANDEL");

                    managebusinessodel1.setPOISFORADD(POISFORADD);
                    managebusinessodel1.setCANDEL(CANDEL);
                    managebusinessodel1.setBusinessnamestring(_businessname);
                    managebusinessodel1.setEinstring(_ein);
                    managebusinessodel1.setnamestring(_name);
                    managebusinessodel1.setBIdstring(_Bid);
                    managebusinessodel1.setDBAstring(_DBA);
                    managebusinessodel1.setAddressforchild1string(_POAdd1);
                    managebusinessodel1.setAddressforchild2string(_POAdd2);
                    managebusinessodel1.setcitystring(_POcity);
                    managebusinessodel1.setstatestring(_sn);
                    managebusinessodel1.setzipcodestring(_POzipcode);
                    managebusinessodel1.setwebsiteaddressstring(_websiteaddress);
                    managebusinessodel1.setphonestring(_phone);
                    managebusinessodel1.setADD1(ADD1);
                    managebusinessodel1.setADD2(ADD2);
                    managebusinessodel1.setSC(SC);
                    managebusinessodel1.setZIP(ZIP);
                    managebusinessodel1.setPON(PON);
                    managebusinessodel1.setCTY(CTY);
                    managebusinessodel1.setCID(CID);
                    managebusinessodel1.setISFORADD1(ISFORADD1);
                    managebusinessodel1.setSN1(SN1);
                    managebusinessodel1.setMODE(MODE);
                    returnobject1.add(managebusinessodel1);

                }

                managebusinessodel.setLength(jsonObj.length());
                Log.e("Length", "Length " + jsonObj.length());
                managebusinessodel.setBusinessname(businessname);
                managebusinessodel.setEin(ein);
                managebusinessodel.setname(name);
                managebusinessodel.setBId(Bid);
                managebusinessodel.setDBA(DBA);
                managebusinessodel.setAddressforchild1(POAdd1);
                managebusinessodel.setAddressforchild2(POAdd2);
                managebusinessodel.setcity(POcity);
                managebusinessodel.setstate(sn);
                managebusinessodel.setzipcode(POzipcode);
                managebusinessodel.setwebsiteaddress(websiteaddress);
                managebusinessodel.setphone(phone);
                managebusinessodel.setPOSN(_POSN);
                managebusinessodel.setPOCID(_POCID);
                managebusinessodel.setPOSISFORADD(_POISFORADD);
                managebusinessodel.set_ADD1(_ADD1);
                managebusinessodel.set_ADD2(_ADD2);

                returnobject.add(managebusinessodel);

                System.out.println("LENGTH OF VECTOR: " + returnobject.size());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("in catch");
            }

        }
        Vectorobject.add(returnobject);
        Vectorobject.add(returnobject1);

        return Vectorobject;

    }
}
