package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.OrderDetailModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 12/9/2015.
 */
public class SaveOrederDetailURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultSaveOrderDetail onAsyncResult;

    OrderDetailModel returnobject = new OrderDetailModel();

    private ProgressDialog pd;

    DatabaseHandler db;

    public SaveOrederDetailURL(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncResultSaveOrderDetail onAsyncResult) {

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

            returnobject = commonWebserviceMethods.getOrderDetailsByReturnId(jsondata, ApplicationConfig.SAVE_PAYMENT_DETAILS_BY_RETURN_ID, mContext);


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


    public interface OnAsyncResultSaveOrderDetail {

        public abstract void onResultSuccess(OrderDetailModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
