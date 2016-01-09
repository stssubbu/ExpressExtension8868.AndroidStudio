package com.span.expressextension8868.businesslogic.parsing;

import android.util.Log;

import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 11/13/2015.
 */
public class BusinessDetailReturnListDetailParsing {

    Vector<ReturnModel> currentYearVector, previoudYearVector, prePreviousYearVector;

    ReturnModel returnModel, AllReturnModel;

    Vector<AuditModel> ErrorVector;

    BookIncareOfModel parseModel;


    public ReturnModel parse(String jsstring) {

        if (jsstring != null) {

            try {
                JSONObject Obj = new JSONObject(jsstring);

                JSONArray jsonarrayObj = new JSONArray();

                jsonarrayObj = Obj.getJSONArray("TaxReturnList");

                if (jsonarrayObj != null && jsonarrayObj.length() > 0) {

                    AllReturnModel = new ReturnModel();

                    currentYearVector = new Vector<ReturnModel>();
                    previoudYearVector = new Vector<ReturnModel>();
                    prePreviousYearVector = new Vector<ReturnModel>();

                    int currentyear = Utils.getCurrentyear() - 1;

                    int previousYear = Utils.getCurrentyear() - 2;

                    int prePreviousYear = Utils.getCurrentyear() - 3;

                    for (int i = 0; i < jsonarrayObj.length(); i++) {

                        JSONObject jsonObj = new JSONObject(jsonarrayObj.getString(i));

                        returnModel = new ReturnModel();

                        returnModel.setOS(jsonObj.getString("OS"));

                        returnModel.setFSID(jsonObj.getString("FSID"));

                        returnModel.setFID(jsonObj.getString("FID"));

                        returnModel.setUDTS(jsonObj.getString("UDTS"));

                        returnModel.setRN(jsonObj.getString("RN"));

                        returnModel.setTY(jsonObj.getString("TY"));

                        returnModel.setTP(jsonObj.getString("TP"));

                        returnModel.setCTY(jsonObj.getString("CTY"));

                        returnModel.setGEN(jsonObj.getString("GEN"));

                        //   returnModel.setETID(jsonObj.getString("ETID"));

                        returnModel.setFC(jsonObj.getString("FC"));

                        //   returnModel.setISHOLDING(jsonObj.getString("ISHOLDING"));

                        // returnModel.setORE(jsonObj.getString("ORE"));

                        returnModel.setTYBD(jsonObj.getString("TYBD"));

                        returnModel.setTYED(jsonObj.getString("TYED"));

                        returnModel.setRID(jsonObj.getString("RID"));

                        returnModel.setISCTY(jsonObj.getString("ISCTY"));

                        returnModel.setISAPCH(jsonObj.getString("ISAPCH"));

                        //returnModel.setISOR(jsonObj.getString("ISOR"));

                        // returnModel.setISUR(jsonObj.getString("ISUR"));

                        returnModel.setISFR(jsonObj.getString("ISFR"));

                        returnModel.setISIR(jsonObj.getString("ISIR"));


                        //   returnModel.setISASCORP(jsonObj.getString("ISASCORP"));

                        returnModel.setEM(jsonObj.getString("EM"));

                        returnModel.setISPAID(jsonObj.getString("ISPAID"));

                        returnModel.setPTY(jsonObj.getString("PTY"));

                        returnModel.setIsCurrentYear(jsonObj.getString("IsCurrentYear"));

                        returnModel.setIsAddThreeMonth(jsonObj.getString("IsAddThreeMonth"));

                        returnModel.setExtensionType(jsonObj.getString("ExtensionType"));

                        returnModel.setExtendedDueDate(jsonObj.getString("ExtendedDueDate"));

                        returnModel.setFNAME(jsonObj.getString("FNAME"));

                        returnModel.setISLESS(jsonObj.getString("ISLESS"));

                        returnModel.setIST(jsonObj.getString("IST"));

                        returnModel.setTES(jsonObj.getString("TES"));

                        if (jsonObj.getString("BCODetails") != null && !jsonObj.getString("BCODetails").equalsIgnoreCase("null")) {

                            JSONObject bcoObj = new JSONObject(jsonObj.getString("BCODetails"));
                            if (bcoObj != null && bcoObj.length() > 0) {


                                Log.i("Book care", "Response : " + bcoObj);

                                parseModel = new BookIncareOfModel();

                                try {

                                    parseModel.setOS(bcoObj.getString("OS"));
                                    parseModel.setEM(bcoObj.getString("EM"));


                                    parseModel.setBookInCareOf(bcoObj.getString("PON"));
                                    parseModel.setBCOIsForeignAddress(bcoObj.getBoolean("POISFORADD"));
                                    parseModel.setBCOAddress1(bcoObj.getString("POADD1"));
                                    parseModel.setBCOAddress2(bcoObj.getString("POADD2"));
                                    parseModel.setBCOCity(bcoObj.getString("POCTY"));
                                    parseModel.setBCOCountryId(bcoObj.getString("POCID"));
                                    parseModel.setBCOState(bcoObj.getString("POSN"));
                                    parseModel.setBCOStateCode(bcoObj.getString("PSC"));
                                    parseModel.setBCOStateId(bcoObj.getString("POSID"));
                                    parseModel.setBCOZip(bcoObj.getString("POZIP"));

                                    returnModel.setParseModel(parseModel);

                                } catch (Exception e) {
                                    e.printStackTrace();


                                }

                            }
                        }

                        if (jsonObj.getString("MRREs") != null && !jsonObj.getString("MRREs").equalsIgnoreCase("null")) {

                            JSONArray ErrorArray = jsonObj.getJSONArray("MRREs");

                            if (ErrorArray != null && ErrorArray.length() > 0) {

                                ErrorVector = new Vector<AuditModel>();


                                for (int j = 0; j < ErrorArray.length(); j++) {

                                    try {

                                        JSONObject errorObj = new JSONObject(ErrorArray.getString(i));

                                        AuditModel model = new AuditModel();

                                        model.setEM(errorObj.getString("EM"));
                                      //  model.setACT(errorObj.getString("ACT"));
                                       // model.setCON(errorObj.getString("CON"));
                                      //  model.setEC(errorObj.getString("EC"));
                                      //  model.setET(errorObj.getString("ET"));
                                      //  model.setISFATAL(errorObj.getBoolean("ISFATAL"));


                                        ErrorVector.add(model);


                                    } catch (Exception e) {

                                        e.printStackTrace();

                                    }

                                }

                                if (ErrorVector != null && ErrorVector.size() > 0) {

                                    returnModel.setErrorVector(ErrorVector);
                                }

                            }
                        }

                        if (returnModel.getTY().equalsIgnoreCase(String.valueOf(currentyear)))
                            currentYearVector.add(returnModel);

                        else if (returnModel.getTY().equalsIgnoreCase(String.valueOf(previousYear)))
                            previoudYearVector.add(returnModel);

                        else
                            prePreviousYearVector.add(returnModel);


                        AllReturnModel.setCurrentYearVector(currentYearVector);

                        AllReturnModel.setPrevioudYearVector(previoudYearVector);

                        AllReturnModel.setPrePreviousYearVector(prePreviousYearVector);

                    }

                    return AllReturnModel;

                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        }

        return null;

    }


}
