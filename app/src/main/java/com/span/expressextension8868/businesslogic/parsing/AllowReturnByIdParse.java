package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.ReturnListModel;
import com.span.expressextension8868.model.core.ReturnModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 12/28/2015.
 */
public class AllowReturnByIdParse {

    boolean allow;

    String EM, OS;

    ReturnModel returnListModel;

    int length;

    public ReturnModel parse(String jsstring) {

        if (jsstring != null) {
            try {

                returnListModel = new ReturnModel();

                JSONObject jsonObject = new JSONObject(jsstring);
                returnListModel.setOS(jsonObject.getString("OS"));
                returnListModel.setEM(jsonObject.getString("EM"));
                returnListModel.setISAF(jsonObject.getBoolean("ISAF"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnListModel;


    }

}
