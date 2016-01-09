package com.span.expressextension8868.businesslogic.parsing;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONObject;

/**
 * Created by STS-099 on 11/25/2015.
 */
public class BookInCareOfParse {

    BookIncareOfModel parseModel;

    public BookIncareOfModel parse(Context mContext, String response) {

        if (response != null && response.length() > 0) {


            Log.i("Book care", "Response : " + response);

            parseModel = new BookIncareOfModel();

                try {
                    JSONObject jsonObj = new JSONObject(response);

                    parseModel.setOS(jsonObj.getString("OS"));
                    parseModel.setEM(jsonObj.getString("EM"));


                    parseModel.setIsBCOBusiness(jsonObj.getBoolean("IsBCOBusiness"));
                    parseModel.setBookCareOfDetailsId(jsonObj.getString("BookCareOfDetailsId"));
                    parseModel.setBookInCareOf(jsonObj.getString("BookInCareOf"));
                    parseModel.setPhone(jsonObj.getString("Phone"));

                    parseModel.setSADayTimePhone(jsonObj.getString("SADayTimePhone"));
                    parseModel.setSAName(jsonObj.getString("SAName"));
                    parseModel.setSATitle(jsonObj.getString("SATitle"));


                parseModel.setBCOIsForeignAddress(jsonObj.getBoolean("BCOIsForeignAddress"));
                parseModel.setBCOIsSameAddress(jsonObj.getBoolean("BCOIsSameAddress"));
                parseModel.setBCOAddress1(jsonObj.getString("BCOAddress1"));
                parseModel.setBCOAddress2(jsonObj.getString("BCOAddress2"));
                parseModel.setBCOCity(jsonObj.getString("BCOCity"));
                parseModel.setBCOCountry(jsonObj.getString("BCOCountry"));
                parseModel.setBCOCountryId(jsonObj.getString("BCOCountryId"));
                parseModel.setBCOState(jsonObj.getString("BCOState"));
                parseModel.setBCOStateCode(jsonObj.getString("BCOStateCode"));
                parseModel.setBCOStateId(jsonObj.getString("BCOStateId"));
                parseModel.setBCOZip(jsonObj.getString("BCOZip"));
                parseModel.setFax(jsonObj.getString("Fax"));

                return parseModel;


            } catch (Exception e) {
                e.printStackTrace();

                new SendException(mContext, e);

            }

        }

        return null;
    }
}



