package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.ExemptModel;
import com.span.expressextension8868.model.core.IRSReturnModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.webservice.serviceclass.ExemptService;

/**
 * Created by STS-099 on 11/20/2015.
 */
public class GetIRSPaymentDetailsURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    IRSPaymentInterface iRSPaymentInterface;

    IRSReturnModel iRSReturnModel = new IRSReturnModel();

    private ProgressDialog pd;

    public GetIRSPaymentDetailsURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;
    }

    public void setOnResultListener(IRSPaymentInterface onAsyncResult) {

        if (onAsyncResult != null) {
            this.iRSPaymentInterface = onAsyncResult;

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods service = new CommonWebserviceMethods();

            iRSReturnModel = service.getSaveIRSPaymentDetailById(jsondata, ApplicationConfig.GETIRSPAYMENTDETAILBYID, mContext);

            if (iRSPaymentInterface != null) {

                iRSPaymentInterface.onResultSuccess(iRSReturnModel);

            }
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        pd.dismiss();
    }

    public interface IRSPaymentInterface {

        public abstract void onResultSuccess(IRSReturnModel iRSReturnModel);

    }
}
