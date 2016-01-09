package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.ExemptModel;
import com.span.expressextension8868.webservice.serviceclass.ExemptService;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class ExemptOrgURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    ExemptInterface exemptInterface;

    ExemptModel exemptModel = new ExemptModel();


    private ProgressDialog pd;

    public ExemptOrgURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;
    }

    public void setOnResultListener(ExemptInterface onAsyncResult) {

        if (onAsyncResult != null) {
            this.exemptInterface = onAsyncResult;

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
            ExemptService exemptService = new ExemptService();

            exemptModel = exemptService.getPersonalResponse(jsondata, ApplicationConfig.UPDATEEXEMPTORGANIZATIONDETAILS, mContext);

            if (exemptInterface != null) {
                exemptInterface.onResultSuccess(exemptModel);
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

    public interface ExemptInterface {

        public abstract void onResultSuccess(ExemptModel exemptModel);

    }
}
