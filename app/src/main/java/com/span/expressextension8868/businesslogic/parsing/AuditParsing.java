package com.span.expressextension8868.businesslogic.parsing;

import android.content.Context;

import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by STS-099 on 11/30/2015.
 */
public class AuditParsing {

    AuditModel getAuditModel;

    Vector<AuditModel> ErrorVector, CautionVector;


    public AuditModel parsing(Context mContext, String response) {


        if (response != null && !response.equalsIgnoreCase("null") && response.length() > 0) {

            try {

                getAuditModel = new AuditModel();

                JSONObject object = new JSONObject(response);


                getAuditModel.setOS(object.getString("OS"));
                getAuditModel.setEM(object.getString("EM"));

                JSONArray ErrorArray = object.getJSONArray("MAEs");

                if (ErrorArray != null && ErrorArray.length() > 0) {

                    ErrorVector = new Vector<AuditModel>();
                    CautionVector = new Vector<AuditModel>();

                    for (int i = 0; i < ErrorArray.length(); i++) {

                        try {

                            JSONObject jsonObj = new JSONObject(ErrorArray.getString(i));

                            AuditModel model = new AuditModel();

                            model.setEM(jsonObj.getString("EM"));
                            model.setACT(jsonObj.getString("ACT"));
                            model.setCON(jsonObj.getString("CON"));
                            model.setEC(jsonObj.getString("EC"));
                            model.setET(jsonObj.getString("ET"));
                            model.setISFATAL(jsonObj.getBoolean("ISFATAL"));


                            if (model.ISFATAL()) {

                                ErrorVector.add(model);

                            } else {

                                CautionVector.add(model);

                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                            new SendException(mContext, e);

                        }

                    }

                    if (ErrorVector != null && ErrorVector.size() > 0) {

                        getAuditModel.setErrorList(ErrorVector);
                    }
                    if (CautionVector != null && CautionVector.size() > 0) {

                        getAuditModel.setCautionList(CautionVector);
                    }

                }

                return getAuditModel;


            } catch (Exception e) {

                e.printStackTrace();
                new SendException(mContext, e);

            }

        }

        return null;
    }
}
