package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import com.span.expressextension8868.model.core.AddBussinessInputModel;

import com.span.expressextension8868.webservice.serviceclass.GetBusinessLookUpService;

import com.span.expressextension8868.utils.utility.ApplicationConfig;

import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;

import com.span.expressextension8868.utils.utility.SendException;

import android.app.ProgressDialog;

import android.content.Context;

import android.os.AsyncTask;

public class GetBusinessLookupURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultBusinessLookUP onAsyncResult;

    Vector<AddBussinessInputModel> returnobject = new Vector<AddBussinessInputModel>();

    private ProgressDialog pd;

    public GetBusinessLookupURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;
    }

    public void setOnResultListener(OnAsyncResultBusinessLookUP onAsyncResult) {

        if (onAsyncResult != null) {
            System.out.println("OBJECT " + onAsyncResult);

            this.onAsyncResult = onAsyncResult;

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
            GetBusinessLookUpService addbusinessgetbusinesslookup = new GetBusinessLookUpService();

            returnobject = addbusinessgetbusinesslookup.getbusinessdata(jsondata, ApplicationConfig.GETBUSINESSFROMLOOKUP, mContext);

            if (onAsyncResult != null) {
                onAsyncResult.onResultSuccess(returnobject);
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

    public interface OnAsyncResultBusinessLookUP {
        public abstract void onResultSuccess(Vector<AddBussinessInputModel> message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
