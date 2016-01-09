package com.span.expressextension8868.businesslogic.parsing;

import com.span.expressextension8868.model.core.Transmitmodel;

import org.json.JSONObject;

/**
 * Created by STS-099 on 12/11/2015.
 */
public class TransmitParse {

    String osField, Uid, EM;

    Transmitmodel transmitmodel = new Transmitmodel();

    public Transmitmodel parse(String jsstring) {
        if (jsstring != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsstring);

                osField = jsonObj.getString("OS");

                Uid = jsonObj.getString("UId");

                EM = jsonObj.getString("EM");

                transmitmodel.setosfield(osField);

                transmitmodel.setEM(EM);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return transmitmodel;

    }
}
