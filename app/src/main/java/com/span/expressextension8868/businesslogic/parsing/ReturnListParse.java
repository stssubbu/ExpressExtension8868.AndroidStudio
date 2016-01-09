package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.ReturnListModel;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;


public class ReturnListParse {
    String[] FSID, RID, FID, ISPAID, UId, RN, UDT, TY, TYBD, TYED, ISLESS, IST, CTY, BID, GEN, TES, FN, TP, PON, POCID, POISFORADD, POSN, POZIP, PSC, POCTY;

    String[] POADD1, POADD2;

    String EM, OS;

    Vector<ReturnListModel> returnobject = new Vector<ReturnListModel>();

    int length;

    public Vector<ReturnListModel> parse(String jsstring) {
        if (jsstring != null) {
            try {

                JSONObject jsonObject = new JSONObject(jsstring);

                JSONArray jsonObj = new JSONArray();

                jsonObj = jsonObject.getJSONArray("TaxReturnList");

                BID = new String[jsonObj.length()];
                FSID = new String[jsonObj.length()];
                RID = new String[jsonObj.length()];
                FID = new String[jsonObj.length()];
                UId = new String[jsonObj.length()];
                UDT = new String[jsonObj.length()];
                RN = new String[jsonObj.length()];
                TY = new String[jsonObj.length()];
                ISPAID = new String[jsonObj.length()];
                TYBD = new String[jsonObj.length()];
                TYED = new String[jsonObj.length()];
                ISLESS = new String[jsonObj.length()];
                IST = new String[jsonObj.length()];
                TES = new String[jsonObj.length()];
                GEN = new String[jsonObj.length()];
                FN = new String[jsonObj.length()];
                TP = new String[jsonObj.length()];
                PON = new String[jsonObj.length()];
                POCID = new String[jsonObj.length()];
                POISFORADD = new String[jsonObj.length()];
                POSN = new String[jsonObj.length()];
                POZIP = new String[jsonObj.length()];
                PSC = new String[jsonObj.length()];
                POCTY = new String[jsonObj.length()];
                POADD1 = new String[jsonObj.length()];
                POADD2 = new String[jsonObj.length()];
                length = jsonObj.length();

                for (int x = 0; x < jsonObj.length(); x++) {
                    FSID[x] = jsonObj.getJSONObject(x).getString("FSID");
                    RID[x] = jsonObj.getJSONObject(x).getString("RID");
                    FID[x] = jsonObj.getJSONObject(x).getString("FID");
                    if (FID[x].equalsIgnoreCase("4")) {
                        if (jsonObj.getJSONObject(x).getString("BCODetails") != null && !jsonObj.getJSONObject(x).getString("BCODetails").toString().equalsIgnoreCase("null")) {
                            PON[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("PON");
                            POCID[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POCID");
                            POISFORADD[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POISFORADD");
                            POSN[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POSN");
                            POZIP[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POZIP");
                            PSC[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("PSC");
                            POCTY[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POCTY");
                            POADD1[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POADD1");
                            POADD2[x] = jsonObj.getJSONObject(x).getJSONObject("BCODetails").getString("POADD2");
                        }
                    }
                    UId[x] = jsonObj.getJSONObject(x).getString("UId");
                    UDT[x] = jsonObj.getJSONObject(x).getString("UDTS");
                    TY[x] = jsonObj.getJSONObject(x).getString("TY");
                    ISPAID[x] = jsonObj.getJSONObject(x).getString("ISPAID");
                    RN[x] = jsonObj.getJSONObject(x).getString("RN");
                    TYBD[x] = jsonObj.getJSONObject(x).getString("TYBD");
                    TYED[x] = jsonObj.getJSONObject(x).getString("TYED");
                    ISLESS[x] = jsonObj.getJSONObject(x).getString("ISLESS");
                    IST[x] = jsonObj.getJSONObject(x).getString("IST");
                    BID[x] = jsonObj.getJSONObject(x).getString("BId");
                    GEN[x] = jsonObj.getJSONObject(x).getString("GEN");
                    TES[x] = jsonObj.getJSONObject(x).getString("TES");
                    FN[x] = jsonObj.getJSONObject(x).getString("FNAME");
                    TP[x] = jsonObj.getJSONObject(x).getString("TP");
                }

                ReturnListModel returnListModel = new ReturnListModel();

                returnListModel.setOS(jsonObject.getString("OS"));
                returnListModel.setEM(jsonObject.getString("EM"));
                returnListModel.setFSID(FSID);
                returnListModel.setFID(FID);
                returnListModel.setRID(RID);
                returnListModel.setUid(UId);
                returnListModel.setISPAID(ISPAID);
                returnListModel.setRN(RN);
                returnListModel.setUDT(UDT);
                returnListModel.setTaxyear(TY);
                returnListModel.setLength(length);
                returnListModel.setTYBD(TYBD);
                returnListModel.setTYED(TYED);
                returnListModel.setISLESS(ISLESS);
                returnListModel.setIST(IST);
                returnListModel.setBIDarray(BID);
                returnListModel.setEM(EM);
                returnListModel.setGEN(GEN);
                returnListModel.setTES(TES);
                returnListModel.setFN(FN);
                returnListModel.setTP(TP);
                returnListModel.setPON(PON);
                returnListModel.setPOCID(POCID);
                returnListModel.setPOCTY(POCTY);
                returnListModel.setPOISFORADD(POISFORADD);
                returnListModel.setPOSN(POSN);
                returnListModel.setPOZIP(POZIP);
                returnListModel.setPSC(PSC);
                returnListModel.setPOADD1(POADD1);
                returnListModel.setPOADD2(POADD2);
                returnobject.add(returnListModel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnobject;

    }
}
