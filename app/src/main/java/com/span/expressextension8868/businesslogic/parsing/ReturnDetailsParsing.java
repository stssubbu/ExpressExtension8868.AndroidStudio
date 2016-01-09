package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.ReturnModel;

import org.json.JSONObject;

import java.util.Vector;

public class ReturnDetailsParsing {
    String OS;
    String RN;
    String TY;
    String CTY;
    String ETID;
    String FC;
    String ISHOLDING;
    String ORE;
    String TYBD;
    String TYED;

    public String getEODId() {
        return EODId;
    }

    public void setEODId(String EODId) {
        this.EODId = EODId;
    }

    String EODId;

    String RID, ISCTY, ISAPCH, ISOR, ISUR, ISFR, ISASCORP, ISIR, ExtensionType, ISFC;

    String EM, UDTS, FSID, ISPAID, PTY, IsCurrentYear, IsAddThreeMonth, ExtendedDueDate, GroupFilingType, GEN, Reason, ReasonCode, IsGroupReturn;

    Vector<ReturnModel> returnDetailsParseVector = new Vector<ReturnModel>();


    public Vector<ReturnModel> parse(String jsstring) {
        if (jsstring != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsstring);

                OS = jsonObj.getString("OS");

                RN = jsonObj.getString("RN");

                TY = jsonObj.getString("TY");

                CTY = jsonObj.getString("CTY");

                //  ETID = jsonObj.getString("ETID");

                FC = jsonObj.getString("FC");

                EODId = jsonObj.getString("EODId");

                //  ISHOLDING = jsonObj.getString("ISHOLDING");

                // ORE = jsonObj.getString("ORE");

                TYBD = jsonObj.getString("TYBD");

                TYED = jsonObj.getString("TYED");

                RID = jsonObj.getString("RID");

                ISCTY = jsonObj.getString("ISCTY");

                ISAPCH = jsonObj.getString("ISAPCH");

                //  ISOR = jsonObj.getString("ISOR");

                //  ISUR = jsonObj.getString("ISUR");

                ISFR = jsonObj.getString("ISFR");

                ISIR = jsonObj.getString("ISIR");

                // ISASCORP = jsonObj.getString("ISASCORP");

                EM = jsonObj.getString("EM");

                FSID = jsonObj.getString("FSID");

                UDTS = jsonObj.getString("UDTS");

                ISPAID = jsonObj.getString("ISPAID");

                PTY = jsonObj.getString("PTY");

                IsCurrentYear = jsonObj.getString("IsCurrentYear");

                ExtensionType = jsonObj.getString("ExtensionType");

                IsAddThreeMonth = jsonObj.getString("IsAddThreeMonth");

                ExtendedDueDate = jsonObj.getString("ExtendedDueDate");

                Reason = jsonObj.getString("Reason");

                ReasonCode = jsonObj.getString("ReasonCode");

                ISFC = jsonObj.getString("ISFC");

                GroupFilingType = jsonObj.getString("GroupFilingType");

                IsGroupReturn = jsonObj.getString("IsGroupReturn");

                GEN = jsonObj.getString("GEN");

                ReturnModel returnModel = new ReturnModel();

                returnModel.setFSID(FSID);

                returnModel.setUDTS(UDTS);

                returnModel.setOS(OS);

                returnModel.setEODId(EODId);

                returnModel.setRN(RN);

                returnModel.setTY(TY);

                returnModel.setCTY(CTY);

                returnModel.setETID(ETID);

                returnModel.setFC(FC);

                returnModel.setISHOLDING(ISHOLDING);

                returnModel.setORE(ORE);

                returnModel.setTYBD(TYBD);

                returnModel.setTYED(TYED);

                returnModel.setRID(RID);

                returnModel.setISCTY(ISCTY);

                returnModel.setISAPCH(ISAPCH);

                returnModel.setISFR(ISFR);

                returnModel.setISIR(ISIR);

                returnModel.setISASCORP(ISASCORP);

                returnModel.setEM(EM);

                returnModel.setISPAID(ISPAID);

                returnModel.setPTY(PTY);

                returnModel.setIsCurrentYear(IsCurrentYear);

                returnModel.setIsAddThreeMonth(IsAddThreeMonth);

                returnModel.setExtensionType(ExtensionType);

                returnModel.setReason(Reason);

                returnModel.setReasonCode(ReasonCode);

                returnModel.setISFC(ISFC);

                returnModel.setGroupFilingType(GroupFilingType);

                returnModel.setIsGroupReturn(IsGroupReturn);

                returnModel.setGEN(GEN);

                returnDetailsParseVector.add(returnModel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnDetailsParseVector;

    }

    public Vector<ReturnModel> parseReturnDetail(String jsstring) {
        if (jsstring != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsstring);

                ReturnModel returnModel = new ReturnModel();

                returnModel.setUDTS(jsonObj.getString("UDTS"));

                returnModel.setOS(jsonObj.getString("OS"));

                returnModel.setRN(jsonObj.getString("RN"));

                returnModel.setTY(jsonObj.getString("TY"));

                returnModel.setCTY(jsonObj.getString("CTY"));

                returnModel.setRID(jsonObj.getString("RID"));

                returnModel.setISFR(jsonObj.getString("ISFR"));

                returnModel.setEM(jsonObj.getString("EM"));

                returnModel.setISPAID(jsonObj.getString("ISPAID"));

                returnModel.setPTY(jsonObj.getString("PTY"));

                returnModel.setIsCurrentYear(jsonObj.getString("ISTY"));


                returnDetailsParseVector.add(returnModel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnDetailsParseVector;

    }


}
