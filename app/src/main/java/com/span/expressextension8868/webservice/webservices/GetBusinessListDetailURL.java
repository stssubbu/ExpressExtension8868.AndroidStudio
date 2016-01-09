package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import java.util.Vector;

/**
 * Created by STS-099 on 11/11/2015.
 */
public class GetBusinessListDetailURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultGetBusinessListDetailURL onAsyncResult;

    Vector<AddBussinessInputModel> returnobject = new Vector<AddBussinessInputModel>();

    private ProgressDialog pd;

    DatabaseHandler db;

    public GetBusinessListDetailURL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncResultGetBusinessListDetailURL onAsyncResult) {

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

            returnobject = commonWebserviceMethods.getbusinessListDetail(jsondata, ApplicationConfig.GET_BUSINESS_LIST_DETAIL_BY_BID, mContext);


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

    public interface OnAsyncResultGetBusinessListDetailURL {
        public abstract void onResultSuccess(Vector<AddBussinessInputModel> message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
