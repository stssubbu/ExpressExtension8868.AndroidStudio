package com.span.expressextension8868.businesslogic.parsing;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.model.core.IRSReturnModel;

import org.json.JSONObject;

/**
 * Created by STS-099 on 11/20/2015.
 */
public class IRSRetunParsing {

    IRSReturnModel balanceDueModel;

    public IRSReturnModel balanceReturnDueParsing(Context context, String response) {

        if (response != null) {

            balanceDueModel = new IRSReturnModel();

            Log.i("IRSPayment", "Get Response" + response);

            try {

                JSONObject jsonObj = new JSONObject(response);

                balanceDueModel.setOS(jsonObj.getString("OS"));

                balanceDueModel.setEM(jsonObj.getString("EM"));

                balanceDueModel.setRID(jsonObj.getString("RID"));

                balanceDueModel.setTOTBALDUE(jsonObj.getString("TOTBALDUE"));

                balanceDueModel.setTOTPAYMENTS(jsonObj.getString("TOTPAYMENTS"));

                balanceDueModel.setTOTTAX(jsonObj.getString("TOTTAX"));

                balanceDueModel.setIRSPaymentOptionId(jsonObj.getString("IRSPaymentOptionId"));

                balanceDueModel.setIRSPaymentId(jsonObj.getString("IRSPaymentId"));

                balanceDueModel.setAccountNumber(jsonObj.getString("AccountNumber"));

                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));

                balanceDueModel.setRTN(jsonObj.getString("RTN"));

                balanceDueModel.setPaymentDateStr(jsonObj.getString("PaymentDateStr"));

                balanceDueModel.setTaxpayerDayTimePhone(jsonObj.getString("TaxpayerDayTimePhone"));

                balanceDueModel.setAmount(jsonObj.getString("Amount"));

                balanceDueModel.setEFWPin(jsonObj.getString("EFWPin"));

                balanceDueModel.setIsEFWConsent(jsonObj.getString("IsEFWConsent"));


//                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));
//
//                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));
//
//                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));
//
//                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));
//
//                balanceDueModel.setAccountTypeID(jsonObj.getString("AccountTypeID"));


                return balanceDueModel;

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return null;
    }
}
