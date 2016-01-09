package com.span.expressextension8868.businesslogic.parsing;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

import com.span.expressextension8868.model.core.ReturnListModel;


public class Rejectedreturnparsing {

    String EM, OS;

    String MessageRejectedreturn, errormessage, errorcode, erroraction;

    Vector<ReturnListModel> returnobject = new Vector<ReturnListModel>();

    Vector<ReturnListModel> MRREsModels = new Vector<ReturnListModel>();

    int length;

    public Vector<ReturnListModel> parse(String jsstring) {
        if (jsstring != null) {
            try {

                ReturnListModel returnListModel = new ReturnListModel();

                JSONObject jsonObject = new JSONObject(jsstring);

                returnListModel.setOS(jsonObject.getString("OS"));

                returnListModel.setEM(jsonObject.getString("EM"));

                JSONArray jsonlist = new JSONArray();

                JSONArray jsonsublist = new JSONArray();

                jsonlist = jsonObject.getJSONArray("TaxReturnList");

                if (jsonlist.getJSONObject(0).getString("MRREs").equalsIgnoreCase("null")) {
                    jsonsublist = new JSONArray();
                } else {
                    jsonsublist = jsonlist.getJSONObject(0).getJSONArray("MRREs");
                }

                Log.e("jsonsublist Length", "jsonsublist.length()" + jsonsublist.length());

                for (int i = 0; i < jsonsublist.length(); i++) {

                    ReturnListModel MRREsModel = new ReturnListModel();

                    MRREsModel.setEM(jsonsublist.getJSONObject(i).getString("EM"));

                    Log.e("parsing REj erros EM", jsonsublist.getJSONObject(i).getString("EM"));

                    MRREsModel.setAN(jsonsublist.getJSONObject(i).getString("AN"));

                    Log.e("parsing REj erros AN", jsonsublist.getJSONObject(i).getString("AN"));

                    MRREsModel.setEC(jsonsublist.getJSONObject(i).getString("EC"));

                    Log.e("parsing REj erros EC", jsonsublist.getJSONObject(i).getString("EC"));

                    MRREsModels.add(MRREsModel);

                }

                if (jsonsublist != null) {
                    returnListModel.setMRREs(MRREsModels);
                }

                returnobject.add(returnListModel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnobject;

    }

    private Vector<ReturnListModel> getMRREsList(String TaxReturnList) {
        try {

            JSONArray taxReturnList = (JSONArray) new JSONTokener(TaxReturnList).nextValue();

            JSONObject MRREsObj = (JSONObject) new JSONTokener(taxReturnList.get(0).toString()).nextValue();

            JSONArray MRREsList = (JSONArray) new JSONTokener(MRREsObj.getString("MRREs")).nextValue();

            Vector<ReturnListModel> MRREsModels = new Vector<ReturnListModel>();

            Log.e("MRREsList.length()", "MRRe's size " + MRREsList.length());

            for (int i = 0; i < MRREsList.length(); i++) {

                ReturnListModel MRREsModel = new ReturnListModel();

                JSONObject centerList = (JSONObject) new JSONTokener(MRREsList.get(i).toString()).nextValue();

                MRREsModel.setEM(centerList.getString("EM"));

                MRREsModel.setAN(centerList.getString("AN"));

                MRREsModel.setEC(centerList.getString("EC"));

                MRREsModel.setLength(MRREsList.length());

                MRREsModels.add(MRREsModel);

            }

            return MRREsModels;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
