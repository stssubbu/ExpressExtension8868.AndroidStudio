package com.span.expressextension8868.businesslogic.parsing;

import android.content.Context;

import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 11/27/2015.
 */
public class SummaryModelParsing {

    SummaryModel model;

    public SummaryModel summaryParse(Context mContext, String response) {

        JSONObject object;

        if (response != null && response.length() > 0) {

            try {

                object = new JSONObject(response);

                model = new SummaryModel();

                model.setOS(object.getString("OS"));
                model.setEM(object.getString("EM"));

                model.setBId(object.getString("BId"));
                model.setRID(object.getString("RID"));
                model.setRN(object.getString("RN"));

                model.setCTY(object.getString("CTY"));
                model.setTY(object.getString("TY"));
                model.setPTY(object.getString("PTY"));
                model.setISCTY(object.getBoolean("ISCTY"));

                model.setTYBD(object.getString("TYBD"));
                model.setTYED(object.getString("TYED"));

                model.setExtendedDueDate(object.getString("ExtendedDueDate"));
                model.setExtensionType(object.getString("ExtensionType"));
                model.setFC(object.getString("FC"));
                model.setFCID(object.getString("FCID"));
                model.setISFC(object.getBoolean("ISFC"));
                model.setISPAID(object.getBoolean("ISPAID"));

                model.setFNAME(object.getString("FNAME"));

                JSONObject jsonObject = new JSONObject(object.getString("BCODetails"));

                model.setBN(jsonObject.getString("BN"));
                model.setEIN(jsonObject.getString("EIN"));

                model.setADD1(jsonObject.getString("ADD1"));
                model.setADD2(jsonObject.getString("ADD2"));
                model.setCITY(jsonObject.getString("CTY"));
                model.setSC(jsonObject.getString("SC"));
                model.setSID(jsonObject.getString("SID"));
                model.setSN(jsonObject.getString("SN"));
                model.setZIP(jsonObject.getString("ZIP"));
                model.setISFORADD(jsonObject.getBoolean("ISFORADD"));

                JSONObject jsonMobBCO = new JSONObject(object.getString("MobBCO"));

                model.setBookInCareOf(jsonMobBCO.getString("BookInCareOf"));
                model.setBCOAddress1(jsonMobBCO.getString("BCOAddress1"));
                model.setBCOAddress2(jsonMobBCO.getString("BCOAddress2"));
                model.setBCOCity(jsonMobBCO.getString("BCOCity"));
                model.setBCOCountry(jsonMobBCO.getString("BCOCountry"));
                model.setBCOState(jsonMobBCO.getString("BCOState"));
                model.setBCOStateCode(jsonMobBCO.getString("BCOStateCode"));
                model.setBCOZip(jsonMobBCO.getString("BCOZip"));
                model.setBCOIsForeignAddress(jsonMobBCO.getBoolean("BCOIsForeignAddress"));

                model.setIsGroupReturn(object.getBoolean("IsGroupReturn"));
                model.setGEN(object.getString("GEN"));
                model.setGroupFilingType(object.getString("GroupFilingType"));

                JSONArray jsonarrayObj = object.getJSONArray("HoldingCompaniesList");

                if (jsonarrayObj != null && jsonarrayObj.length() > 0) {

                    String nameGroupList = null;

                    for (int x = 0; x < jsonarrayObj.length(); x++) {

                        try {

                            JSONObject jsonObj = new JSONObject(jsonarrayObj.getString(x));
                            if (x == 0)

                                nameGroupList = jsonObj.getString("HoldingCompanyName") + ", " + jsonObj.getString("HoldingCompanyEIN");

                            else
                                nameGroupList = nameGroupList + "\n" + jsonObj.getString("HoldingCompanyName") + ", " + jsonObj.getString("HoldingCompanyEIN");


                        } catch (Exception e) {

                            e.printStackTrace();
                            new SendException(mContext, e);

                        }
                    }

                    if (nameGroupList != null)

                        model.setHoldingCompaniesList(nameGroupList);
                }

                model.setTentativeTaxAmount(object.getString("TentativeTaxAmount"));
                model.setCreditAmount(object.getString("CreditAmount"));
                model.setBalanceDue(object.getString("BalanceDue"));


                return model;

            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }
        }

        return null;

    }


}
