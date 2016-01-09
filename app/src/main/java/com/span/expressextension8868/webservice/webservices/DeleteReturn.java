package com.span.expressextension8868.webservice.webservices;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;


import com.span.expressextension8868.controller.controller.DashboardActivity;
import com.span.expressextension8868.model.core.DatesModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

public class DeleteReturn extends AsyncTask<String, Void, String> {
    String RID, DID, AT, UID;

    Context mContext;

    ArrayList<String> returnobject;

    private ProgressDialog pd;

    int position;

    ListView lv;

    DeleteInterface deleteInterface;

    public DeleteReturn(String RID, Context context, String DID, String UID, String At, int selectedposition, ListView lv) {
        this.RID = RID;
        this.DID = DID;
        this.AT = At;
        this.UID = UID;
        this.mContext = context;
        this.lv = lv;
        position = selectedposition;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(mContext);
        pd.setMessage("Deleting Return Please wait...");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            JSONObject obj = new JSONObject();

            JSONObject obj1 = new JSONObject();

            obj.put("RID", RID);

            obj.put("AT", AT);

            obj.put("DId", DID);

            obj.put("UId", UID);

            obj1.put("Return", obj);

            System.out.println("DELETE REQUEST " + obj1.toString());

            CommonWebserviceMethods deleteBusinessService = new CommonWebserviceMethods();

            System.out.println("Delete Request " + obj1.toString());

            returnobject = deleteBusinessService.getosfieldvalue(obj1.toString(), ApplicationConfig.DELETERETURNBYID, mContext);

            if (deleteInterface != null)

                deleteInterface.onResultSuccess(returnobject);

        } catch (Exception e) {
            new SendException(mContext, e);
        }
        return null;
    }

    public void setOnResultListener(DeleteInterface onAsyncResult) {
        if (onAsyncResult != null) {
            this.deleteInterface = onAsyncResult;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pd.dismiss();


    }

    public interface DeleteInterface {
        public abstract void onResultSuccess(ArrayList<String> datesModel);

    }

}
