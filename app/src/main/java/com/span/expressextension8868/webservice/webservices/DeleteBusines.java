package com.span.expressextension8868.webservice.webservices;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;


import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

public class DeleteBusines extends AsyncTask<String, Void, String> {
    String BID, UID, DID, AT;

    Context mContext;

    ArrayList<String> returnobject;

    private ProgressDialog pd;

    Deleterefresh deletereferesh;

    public DeleteBusines(String BID, String UID, Context context, String DID,
                         String AT) {
        this.BID = BID;

        this.UID = UID;

        this.DID = DID;

        this.AT = AT;

        this.mContext = context;

    }

    public void setOnResultListener(Deleterefresh deletereferesh) {

        if (deletereferesh != null) {
            this.deletereferesh = deletereferesh;

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(mContext);

        pd.setMessage("Deleting Organization Please wait...");

        pd.setCancelable(false);

        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("BId", this.BID);

            obj.put("UId", this.UID);

            obj.put("DId", this.DID);

            obj.put("AT", this.AT);

            obj.put("LGT", "DELETE");

            obj1.put("Business", obj);

            CommonWebserviceMethods deleteBusinessService = new CommonWebserviceMethods();

            returnobject = deleteBusinessService.getosfieldvalue(
                    obj1.toString(), ApplicationConfig.UPDATEEXEMPTORGANIZATIONDETAILS,
                    mContext);

            if (deletereferesh != null) {
                deletereferesh.onResultSuccess(returnobject.get(0),
                        returnobject.get(1));
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        pd.dismiss();

    }

    public interface Deleterefresh {

        public abstract void onResultSuccess(String message, String messageerror);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }

}
