package com.span.expressextension8868.webservice.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;

/**
 * Created by STS-099 on 11/25/2015.
 */
public class SaveBookInCareOfDetails extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultSaveBookIncareOf onAsyncResult;

    BookIncareOfModel returnobject = new BookIncareOfModel();

    private ProgressDialog pd;

    DatabaseHandler db;

    public SaveBookInCareOfDetails(String jsstring, Context context) {

        this.jsondata = jsstring;

        mContext = context;


        db = new DatabaseHandler(mContext);

        db.getWritableDatabase();


    }

    public void setOnResultListener(OnAsyncResultSaveBookIncareOf onAsyncResult) {

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

            returnobject = commonWebserviceMethods.getBookInCareOfByReturnId(jsondata, ApplicationConfig.SAVE_BOOK_IN_CARE_OF, mContext);


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

    public interface OnAsyncResultSaveBookIncareOf {
        public abstract void onResultSuccess(BookIncareOfModel message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
