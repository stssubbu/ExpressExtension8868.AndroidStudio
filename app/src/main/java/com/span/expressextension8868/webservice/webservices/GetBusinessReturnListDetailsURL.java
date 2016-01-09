package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

import java.util.Vector;

/**
 * Created by STS-099 on 11/13/2015.
 */
public class GetBusinessReturnListDetailsURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultGetBusinessReturnListDetailURL onAsyncResult;

    ReturnModel returnListModel;

    private ProgressDialog pd;


    public GetBusinessReturnListDetailsURL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


    }

    public void setOnResultListener(OnAsyncResultGetBusinessReturnListDetailURL onAsyncResult) {

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

            returnListModel = commonWebserviceMethods.getBusinessReturnListDetail(jsondata, ApplicationConfig.GET_RETURN_LIST_BY_BID, mContext);


            if (onAsyncResult != null) {
                onAsyncResult.onResultSuccess(returnListModel);
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

    public interface OnAsyncResultGetBusinessReturnListDetailURL {
        public abstract void onResultSuccess(ReturnModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
