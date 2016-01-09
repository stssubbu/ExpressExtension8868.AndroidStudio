package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.CreditModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.PdfURL;
import com.span.expressextension8868.webservice.webservices.SendEmailURL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by STS-099 on 12/12/2015.
 */
public class TransmitSuccessFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;

    String emailResponse;

    // progress

    private ProgressDialog pd;

    //

    LinearLayout logolayout;

    TextView DashBoardTitle;

    android.support.v7.widget.CardView gotodashboard;

    android.support.v7.widget.CardView pdfbtn, email, summaryEmail, summaryPdfbtn;

    LinearLayout whole;

    TextView congratulations, informtext, informtext1, emailText, pdfbtnText, summaryEmailText, summaryPdfbtnText;

    // View for Common Header
    View HeaderView;

    ImageView transmitimage;

    //add dialog

    AlertDialog emailDialog;

    android.support.v7.widget.CardView sendEmail, emailCancel;

    EditText emailTo, emailSubject, emailContent;

    JSONObject obj = new JSONObject();

    JSONObject obj1 = new JSONObject();


    public TransmitSuccessFragment(Context mContext) {

        this.mContext = mContext;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.transmit_success, container, false);


                Initialization();

                setOncLickListener();

                setTypeFont();


                pageShown();


                addLeftFragment();

            }

            InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

            in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return CommonTaxView;

    }


    private void pageShown() {

        if (AppConfigManager.getFirstMode(mContext).equalsIgnoreCase("FIRST")) {
            congratulations.setVisibility(View.VISIBLE);

            transmitimage.setVisibility(View.VISIBLE);

            informtext.setText("Your application for the Extension of your Tax Return is on its way to the IRS via the e-file system. It may take a few moments for the IRS to process your extension. ");

            informtext1.setVisibility(View.VISIBLE);

            informtext1.setText("Please note that there may be a slight delay due to the volume of returns filed with the IRS during peak times and major tax deadlines.");
        } else if (AppConfigManager.getFirstMode(mContext).equalsIgnoreCase("PAID")) {
            congratulations.setVisibility(View.INVISIBLE);

            transmitimage.setVisibility(View.GONE);

            informtext.setText("Your application for the Extension of your Tax Return has been paper filed successfully");

            informtext1.setVisibility(View.GONE);

        } else if (AppConfigManager.getFirstMode(mContext).equalsIgnoreCase("FIRSTPAID")) {
            congratulations.setVisibility(View.VISIBLE);

            transmitimage.setVisibility(View.GONE);

            informtext.setText("Your application for the Extension of your Tax Return has been paper filed successfully");

            informtext1.setVisibility(View.GONE);
        } else if (AppConfigManager.getFirstMode(mContext).equalsIgnoreCase("SECOND")) {
            congratulations.setVisibility(View.INVISIBLE);

            transmitimage.setVisibility(View.VISIBLE);

            informtext.setText("Your application for the Extension of your Tax Return is on its way to the IRS via the e-file system. It may take a few moments for the IRS to process your extension. ");

            informtext1.setVisibility(View.VISIBLE);

            informtext1.setText("Please note that there may be a slight delay due to the volume of returns filed with the IRS during peak times and major tax deadlines.");
        }


    }


    private void setOncLickListener() {

        logolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                ((Activity) mContext).finish();
            }
        });


        summaryPdfbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PDFViewer("Form8868");

            }
        });

        pdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PDFViewer("Receipt");

            }
        });

        gotodashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent manage = new Intent(mContext, DashboardActivity.class);

                startActivity(manage);

                ((Activity) mContext).finish();
            }
        });

        summaryEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                addPopup("Form8868");

                emailDialog.show();


//                Intent i = new Intent(mContext, SendEmail.class);
//
//                startActivity(i);
//
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

        email.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                addPopup("Receipt");

                emailDialog.show();


//                Intent i = new Intent(mContext, SendEmail.class);
//
//                startActivity(i);
//
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });


    }

    public void PDFViewer(String formType) {
        String jsonString = null;
        try {
            obj = new JSONObject();

            obj1 = new JSONObject();

            try {

                obj1 = new JSONObject();

                obj.put("RID", AppConfigManager.getReturnRID(mContext));

                obj.put("UId", AppConfigManager.getLoggedUid(mContext));

                obj.put("DId", AppConfigManager.getDID(mContext));

                obj.put("AT", AppConfigManager.getAccessToken(mContext));

                obj1.put("Return", obj);

                jsonString = obj1.toString();

            } catch (JSONException e) {

                e.printStackTrace();
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }

        PdfURL pdfurl = new PdfURL(formType, jsonString, mContext);

        PdfURL.PdfOnAsyncResultByCondition pdfasync = new PdfURL.PdfOnAsyncResultByCondition() {

            @Override
            public void onResultSuccess(String message) {

                String url = "http://docs.google.com/viewer?url=" + message;

                Intent web = new Intent(mContext, WebActivity.class);

                web.putExtra("URL", url);

                mContext.startActivity(web);


            }

            @Override
            public void onResultFail(int resultCode, String errorMessage) {

            }
        };
        pdfurl.setOnResultListener(pdfasync);

        pdfurl.execute();

    }

    private void Initialization() {

        utils = new Utils();

        TypeFaceClass ty = new TypeFaceClass(mContext);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        DashBoardTitle.setText("E-FILE AND PRINT");

        gotodashboard = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.gotodashboard);

        pdfbtn = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.pdfbtn);

        email = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.email);

        summaryEmail = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.summaryEmail);

        summaryPdfbtn = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.summaryPdfbtn);

        whole = (LinearLayout) CommonTaxView.findViewById(R.id.whole);

        congratulations = (TextView) CommonTaxView.findViewById(R.id.congratulations);

        emailText = (TextView) CommonTaxView.findViewById(R.id.emailText);

        pdfbtnText = (TextView) CommonTaxView.findViewById(R.id.pdfbtnText);

        summaryEmailText = (TextView) CommonTaxView.findViewById(R.id.summaryEmailText);

        summaryPdfbtnText = (TextView) CommonTaxView.findViewById(R.id.summaryPdfbtnText);

        informtext = (TextView) CommonTaxView.findViewById(R.id.informtext);

        informtext1 = (TextView) CommonTaxView.findViewById(R.id.informtext1);

        transmitimage = (ImageView) CommonTaxView.findViewById(R.id.transmitimage);


    }


    private void addPopup(String emailType) {

        LayoutInflater factory = LayoutInflater.from(mContext);
        final View deleteDialogView = factory.inflate(
                R.layout.editsendemail, null);

        dialogInitial(deleteDialogView);

        dialogOnclick(emailType);

        emailDialog = new AlertDialog.Builder(mContext).create();
        emailDialog.setView(deleteDialogView);

    }

    private void dialogInitial(View deleteDialogView) {


        try {

            sendEmail = (android.support.v7.widget.CardView) deleteDialogView.findViewById(R.id.sendEmail);

            emailCancel = (android.support.v7.widget.CardView) deleteDialogView.findViewById(R.id.emailCancel);

            emailTo = (EditText) deleteDialogView.findViewById(R.id.emailTo);

            emailSubject = (EditText) deleteDialogView.findViewById(R.id.emailSubject);

            emailContent = (EditText) deleteDialogView.findViewById(R.id.emailContent);

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }


    }

    private void dialogOnclick(final String emailType) {

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int x = SetInputs(emailType);

                    Log.e("FLAG X", "X value" + x);

                    if (x == 1)
                        SendEmailnow(emailType);


                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }

            }
        });

        emailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    emailDialog.dismiss();

                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                    imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }


            }
        });
    }

    public int SetInputs(String emailType) {

        int flag = 1;

        try {

            obj = new JSONObject();

            obj1 = new JSONObject();

            obj.put("RID", AppConfigManager.getReturnRID(mContext));

            obj.put("UId", AppConfigManager.getLoggedUid(mContext));

            obj.put("EA", AppConfigManager.getUserName(mContext));

            if (emailTo.getText().toString().equalsIgnoreCase("")) {
                emailTo.setError("Enter email address");
                flag = 0;
            }

            if (isValidEmail(emailTo.getText().toString()) == true) {
                obj.put("SEA", emailTo.getText().toString());
            } else {
                emailTo.setError("Enter valid Email Address");
                flag = 0;
            }

            obj.put("SUB", emailSubject.getText().toString());

            obj.put("MES", emailContent.getText().toString());

            obj.put("AT", AppConfigManager.getAccessToken(mContext));

            obj.put("DId", AppConfigManager.getDID(mContext));

            if (emailType != null && emailType.equalsIgnoreCase("Form8868"))

                obj1.put("MobSendForm", obj);

            else
                obj1.put("SendForm", obj);


            Log.e("Request X", "Request value" + obj1.toString());
        } catch (JSONException e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
        return flag;
    }

    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            // android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    void SendEmailnow(String emailType) {

        try {

            SendEmailURL sendemailurl = new SendEmailURL(emailType, obj1.toString(), mContext);

            SendEmailURL.OnAsyncResultByConditionEmail onasyncEmail = new SendEmailURL.OnAsyncResultByConditionEmail() {

                @Override
                public void onResultSuccess(final String message) {

                    emailResponse = message;

                    handler.post(emailRunnable);
                }

                @Override
                public void onResultFail(int resultCode, String errorMessage) {

                }

            };

            sendemailurl.setOnResultListener(onasyncEmail);
            sendemailurl.execute();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    Handler handler = new Handler();

    Runnable emailRunnable = new Runnable() {
        @Override
        public void run() {

            try {
                if (emailResponse != null && !emailResponse.equalsIgnoreCase("null")) {
                    if (emailResponse.equalsIgnoreCase("Success")) {

                        utils.successMessage(mContext, "Email sent Successfully");

                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                        imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                        emailTo.setText("");

                        emailSubject.setText("");

                        emailContent.setText("");

                        emailDialog.dismiss();
                    }

                    if (emailResponse.equalsIgnoreCase("Failure")) {

                        utils.errorMessage(mContext, "Email Sending Failure");

                        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(emailTo.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailContent.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(emailSubject.getWindowToken(), 0);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }
        }
    };


    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, whole);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        congratulations.setTypeface(typeFaceClass.NotoSans_Bold());

        emailText.setTypeface(typeFaceClass.fontawesome_webfont());

        pdfbtnText.setTypeface(typeFaceClass.fontawesome_webfont());

        summaryEmailText.setTypeface(typeFaceClass.fontawesome_webfont());

        summaryPdfbtnText.setTypeface(typeFaceClass.fontawesome_webfont());


    }

    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 5);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

}


