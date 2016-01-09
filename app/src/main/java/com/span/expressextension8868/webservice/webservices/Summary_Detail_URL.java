package com.span.expressextension8868.webservice.webservices;

/**
 * Created by STS-099 on 11/27/2015.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.SummaryModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

public class Summary_Detail_URL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultGetSummaryDetail onAsyncResult;

    SummaryModel returnobject = new SummaryModel();

    private ProgressDialog pd;

    DatabaseHandler db;

    public Summary_Detail_URL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncResultGetSummaryDetail onAsyncResult) {

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
            CommonWebserviceMethods commonWebserviceMethods = new CommonWebserviceMethods();

            returnobject = commonWebserviceMethods.getSummaryReturnDetailsByReturnId(jsondata, ApplicationConfig.GET_SUMMARY_DETAILS, mContext);


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



    public interface OnAsyncResultGetSummaryDetail {

        public abstract void onResultSuccess(SummaryModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
