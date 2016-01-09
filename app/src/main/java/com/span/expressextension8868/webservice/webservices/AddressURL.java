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

/**
 * Created by STS-099 on 12/30/2015.
 */
public class AddressURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncAddress onAsyncResult;

    AddBussinessInputModel returnobject;

    private ProgressDialog pd;

    DatabaseHandler db;

    public AddressURL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncAddress onAsyncResult) {

        if (onAsyncResult != null) {

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

            returnobject = commonWebserviceMethods.GetBusinessAddressDetailById(jsondata, ApplicationConfig.GET_ADDRESS_BY_ID, mContext);


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


    public interface OnAsyncAddress {

        public abstract void onResultSuccess(AddBussinessInputModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
