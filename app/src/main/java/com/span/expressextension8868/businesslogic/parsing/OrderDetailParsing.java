package com.span.expressextension8868.businesslogic.parsing;

import android.content.Context;

import com.span.expressextension8868.model.core.OrderDetailModel;
import com.span.expressextension8868.utils.utility.SendException;

import org.json.JSONObject;

/**
 * Created by STS-099 on 12/9/2015.
 */
public class OrderDetailParsing {

    OrderDetailModel model;

    public OrderDetailModel orderParse(Context mContext, String response) {

        JSONObject object, SKUObject, returnObject;

        if (response != null && response.length() > 0) {

            try {

                object = new JSONObject(response);

                model = new OrderDetailModel();

                model.setOS(object.getString("OS"));
                model.setEM(object.getString("EM"));

                model.setISPAID(object.getBoolean("ISPAID"));

                model.setShoppingCartId(object.getInt("ShoppingCartId"));

                SKUObject = new JSONObject(object.getString("ProductSKU"));

                returnObject = new JSONObject(object.getString("ReturnNotification"));

                model.setSKUId(SKUObject.getString("SKUId"));
                model.setSKU_Description(SKUObject.getString("SKU_Description"));
                model.setSKU_Name(SKUObject.getString("SKU_Name"));
                model.setPrice(SKUObject.getString("Price"));

                model.setFaxAlertPrice(object.getString("FaxAlertPrice"));
                model.setTextAlertPrice(object.getString("TextAlertPrice"));


                model.setIsFaxAlertPaid(returnObject.getBoolean("IsFaxAlertPaid"));
                model.setIsTextAlertPaid(returnObject.getBoolean("IsTextAlertPaid"));

                model.setTextPhoneNumber(returnObject.getString("TextPhoneNumber"));
                model.setFaxNumber(returnObject.getString("FaxNumber"));


                return model;

            } catch (Exception e) {


                e.printStackTrace();
                new SendException(mContext, e);
            }

        }
        return null;
    }
}