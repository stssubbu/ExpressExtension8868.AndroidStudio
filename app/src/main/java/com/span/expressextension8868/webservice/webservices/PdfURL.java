package com.span.expressextension8868.webservice.webservices;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class PdfURL extends AsyncTask<String, Void, String> {
    String jsondata;

    PdfOnAsyncResultByCondition AuditonAsyncResult;

    String returnobject, pdfType;

    private ProgressDialog pd;

    Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = MyCustomProgressDialog.ctor(mContext);

        pd.show();


    }

    public PdfURL(String pdfType, String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;

        this.pdfType = pdfType;
    }

    public void setOnResultListener(PdfOnAsyncResultByCondition AuditonAsyncResult) {

        if (AuditonAsyncResult != null) {
            this.AuditonAsyncResult = AuditonAsyncResult;

        }
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods commonWebserviceMethods = new CommonWebserviceMethods();

            if (pdfType != null && pdfType.equalsIgnoreCase("Approval"))

                returnobject = commonWebserviceMethods.getpdfpath(jsondata, ApplicationConfig.VIEWAPPROVAL, mContext);

            else if (pdfType != null && pdfType.equalsIgnoreCase("Receipt"))

                returnobject = commonWebserviceMethods.getpdfpath(jsondata, ApplicationConfig.VIEWRECEIPT, mContext);

            else

                returnobject = commonWebserviceMethods.getpdfpath(jsondata, ApplicationConfig.VIEWFORM, mContext);


            if (AuditonAsyncResult != null) {
                AuditonAsyncResult.onResultSuccess(returnobject);
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

    public interface PdfOnAsyncResultByCondition {
        public abstract void onResultSuccess(String message);

        public abstract void onResultFail(int resultCode, String errorMessage);

    }
}
