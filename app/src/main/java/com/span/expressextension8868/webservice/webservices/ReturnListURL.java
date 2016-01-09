package com.span.expressextension8868.webservice.webservices;

import java.util.Vector;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.span.expressextension8868.model.core.ReturnListModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;


public class ReturnListURL extends AsyncTask<String, Void, String> {
    String jsondata;

    Context mContext;

    OnAsyncResultByCondition7 onAsyncResult;

    Vector<ReturnListModel> returnobject = new Vector<ReturnListModel>();

    private ProgressDialog pd;

    private ProgressBar progressBar;

    public ReturnListURL(String jsstring, Context context) {
        this.jsondata = jsstring;

        this.mContext = context;
    }

    public void setOnResultListener(OnAsyncResultByCondition7 onAsyncResult) {

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

        // Activity activity = (Activity) mContext;
        //
        // ViewGroup layout = (ViewGroup)
        // activity.findViewById(android.R.id.content).getRootView();
        //
        // progressBar = new ProgressBar(mContext, null,
        // android.R.attr.progressBarStyleLarge);
        //
        // progressBar = new ProgressBar(mContext);
        //
        // progressBar.setIndeterminate(true);
        //
        // progressBar.setIndeterminateDrawable(mContext.getResources().getDrawable(R.drawable.loader_2));
        //
        // progressBar.setVisibility(View.VISIBLE);
        //
        // RelativeLayout.LayoutParams params = new
        //
        // RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
        // RelativeLayout.LayoutParams.MATCH_PARENT);
        //
        // RelativeLayout rl = new RelativeLayout(mContext);
        //
        // rl.setGravity(Gravity.CENTER);
        //
        // rl.addView(progressBar);
        //
        // layout.addView(rl, params);

    }

    @Override
    protected String doInBackground(String... params) {

        try {
            CommonWebserviceMethods returnListService = new CommonWebserviceMethods();

            returnobject = returnListService.getreturnlist(jsondata, ApplicationConfig.GETRETURNSLISTBYBUSINESSID, mContext);

            System.out.println("RETURNOBJECT LENGTH " + returnobject.size());

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

        // progressBar.setVisibility(View.GONE);
    }

    public interface OnAsyncResultByCondition7 {

        public abstract void onResultSuccess(Vector<ReturnListModel> message);

        public abstract void onResultFail(int resultCode, String errorMessage);
    }
}
