package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.model.core.IRSReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyCountURL;
import com.span.expressextension8868.webservice.webservices.GetIRSPaymentDetailsURL;
import com.span.expressextension8868.webservice.webservices.GetIRSPaymentDetailsURL.*;
import com.span.expressextension8868.webservice.webservices.SaveIRSPaymentDetailsURL;

/**
 * Created by STS-099 on 11/20/2015.
 */
public class BalanceDueFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    String BId, Mode;

    ProgressDialog pd;

    Utils utils;

    int FCId = 0;

    String EIN, BN;


    //componenet

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout, allLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;

    TextView text1, text2;

    com.rengwuxian.materialedittext.MaterialEditText estimatedTax_EditText, payment_made_EditText, balanceDue_EditText;

    android.support.v7.widget.CardView BDnext, BDcancelbutton;

    //getValue

    IRSReturnModel getIRSReturnModel, responseIRSReturnModel;

    GetIRSPaymentDetailsURL getIRSPaymentDetailsURL;

    //save

    IRSReturnModel saveBalanceDue, responseSaveBalanceDue;

    SaveIRSPaymentDetailsURL saveIRSPaymentDetailsURL;


    public BalanceDueFragment(Context mContext) {

        this.mContext = mContext;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            BId = AppConfigManager.getBID(mContext);

            Mode = AppConfigManager.getMode(mContext);

            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.balance_due_page, container, false);

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Initialization();

                onClick();

                addLeftFragment(0);

                Overridefonts.overrideFonts(mContext, WholeLayout);

                setTypeFont();

                GetBalanceDueDetailsByID();


            }

            return CommonTaxView;

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return null;
    }

    private void Initialization() {

        try {

            utils = new Utils();


            DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


            DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

            logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);


            DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
            DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
            DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

            DashBoardTitle.setText("BALANCE DUE");

            if (EIN != null && BN != null) {

                DashboardOrgEin.setText(EIN);

                DashboardOrgName.setText(BN);
            }

            allLayout = (LinearLayout) CommonTaxView.findViewById(R.id.allLayout);

            WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);

            text1 = (TextView) CommonTaxView.findViewById(R.id.text1);

            text2 = (TextView) CommonTaxView.findViewById(R.id.text2);

            BDnext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.BDnext);

            BDcancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.BDCancelbutton);


            estimatedTax_EditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.estimatedTax_EditText);

            estimatedTax_EditText.setText("");

            payment_made_EditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.payment_made_EditText);

            payment_made_EditText.setText("");

            balanceDue_EditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.balanceDue_EditText);

            balanceDue_EditText.setEnabled(false);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void onClick() {


        WholeLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
        allLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                WholeLayout.performClick();

                return false;
            }
        });


        logolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                ((Activity) mContext).finish();

            }
        });

        BDnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    nextPage();

                } else {

                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    SaveBalanceDueDetailsByID();

                }

            }
        });

        BDcancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Fragment newFragment = new ExtensionTypeFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.EXTENSION_TYPE_FRAGMENT);

// Commit the transaction
                transaction.commit();

            }
        });


        estimatedTax_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String amt1 = "0", amt2 = "0";

                if (estimatedTax_EditText.getText() != null && estimatedTax_EditText.getText().toString().trim().length() > 0) {

                    amt1 = estimatedTax_EditText.getText().toString();

                } else {

                    amt1 = "0";
                }

                if (payment_made_EditText.getText() != null && payment_made_EditText.getText().toString().trim().length() > 0) {

                    amt2 = payment_made_EditText.getText().toString();

                } else {

                    amt2 = "0";
                }


                try {

                    int value = Integer.parseInt(amt1) - Integer.parseInt(amt2);

                    if (value > 0)

                        balanceDue_EditText.setText(String.valueOf(value));

                    else

                        balanceDue_EditText.setText("0");

                } catch (Exception e) {

                    balanceDue_EditText.setText("0");

                    e.printStackTrace();

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

//                if (estimatedTax_EditText.length() < 2) {
//
//                    estimatedTax_EditText.setText("$ 0");
//
//                    estimatedTax_EditText.setSelection(2);
//
//                } else {
//
//                    if (!balanceDue_EditText.getText().toString().contains("$")) {
//
//
//                        String value = balanceDue_EditText.getText().toString();
//
//                        balanceDue_EditText.setText("$ " + value);
//
//                    }
//                }

            }
        });

        payment_made_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String amt1 = "0", amt2 = "0";

                if (estimatedTax_EditText.getText() != null && estimatedTax_EditText.getText().toString().trim().length() > 0) {

                    amt1 = estimatedTax_EditText.getText().toString();

                } else {

                    amt1 = "0";
                }

                if (payment_made_EditText.getText() != null && payment_made_EditText.getText().toString().trim().length() > 0) {

                    amt2 = payment_made_EditText.getText().toString();

                } else {

                    amt2 = "0";
                }


                try {

                    int value = Integer.parseInt(amt1) - Integer.parseInt(amt2);

                    if (value > 0)

                        balanceDue_EditText.setText(String.valueOf(value));

                    else

                        balanceDue_EditText.setText("0");

                } catch (Exception e) {

                    balanceDue_EditText.setText("0");

                    e.printStackTrace();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

//                if (payment_made_EditText.length() < 2) {
//
//                    payment_made_EditText.setText("$ 0");
//
//                    payment_made_EditText.setSelection(2);
//                } else {
//
//                    if (!payment_made_EditText.getText().toString().contains("$")) {
//
//
//                        String value = payment_made_EditText.getText().toString();
//
//
//                        payment_made_EditText.setText("$ " + value);
//
//                    }
//                }

            }
        });

        balanceDue_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // balanceDue_EditText.setText("$ " + s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

//                if (balanceDue_EditText.length() < 2) {
//
//                    balanceDue_EditText.setText("$ 0");
//
//                    balanceDue_EditText.setSelection(2);
//
//                } else {
//
//                    if (!balanceDue_EditText.getText().toString().contains("$")) {
//
//
//                        String value = balanceDue_EditText.getText().toString();
//
//                        balanceDue_EditText.setText("$ " + value);
//
//                    }
//                }


            }
        });
    }

    private void GetBalanceDueDetailsByID() {

        getIRSReturnModel = new IRSReturnModel();

        getIRSReturnModel.setAT(AppConfigManager.getAccessToken(mContext));

        getIRSReturnModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getIRSReturnModel.setRID(AppConfigManager.getReturnRID(mContext));

        getIRSReturnModel.setDId(AppConfigManager.getDID(mContext));

        if (getIRSPaymentDetailsURL != null)

            getIRSPaymentDetailsURL.cancel(true);

        getIRSPaymentDetailsURL = new GetIRSPaymentDetailsURL(getIRSReturnModel.getBalaceDueDetail(), mContext);

        getIRSPaymentDetailsURL.setOnResultListener(getDetails);

        getIRSPaymentDetailsURL.execute();

    }

    IRSPaymentInterface getDetails = new IRSPaymentInterface() {

        @Override
        public void onResultSuccess(IRSReturnModel iRSReturnModel) {

            responseIRSReturnModel = iRSReturnModel;

            handler.post(getRunnable);

        }
    };
    Handler handler = new Handler();

    Runnable getRunnable = new Runnable() {
        @Override
        public void run() {

            if (responseIRSReturnModel != null) {

                if (responseIRSReturnModel.getOS() != null && responseIRSReturnModel.getOS().equalsIgnoreCase("Success")) {

                    if (responseIRSReturnModel.getTOTTAX() != null && responseIRSReturnModel.getTOTTAX().length() > 0 && !responseIRSReturnModel.getTOTTAX().equalsIgnoreCase("0")) {

                        estimatedTax_EditText.setText(responseIRSReturnModel.getTOTTAX());

                    } else {

                        estimatedTax_EditText.setText("");
                    }

                    Log.i("", "payment : " + responseIRSReturnModel.getTOTPAYMENTS());


                    if (responseIRSReturnModel.getTOTPAYMENTS() != null && responseIRSReturnModel.getTOTPAYMENTS().length() > 0 && !responseIRSReturnModel.getTOTPAYMENTS().equalsIgnoreCase("0")) {

                        payment_made_EditText.setText(responseIRSReturnModel.getTOTPAYMENTS().trim());

                    } else {

                        payment_made_EditText.setText("");
                    }

                    if (responseIRSReturnModel.getTOTBALDUE() != null && responseIRSReturnModel.getTOTBALDUE().length() > 0) {

                        balanceDue_EditText.setText(responseIRSReturnModel.getTOTBALDUE());

                    } else {

                        balanceDue_EditText.setText("0");
                    }
                } else if (responseIRSReturnModel.getOS() != null && responseIRSReturnModel.getOS().equalsIgnoreCase("Failure")) {

                    if (responseIRSReturnModel.getEM() != null && !responseIRSReturnModel.getEM().equalsIgnoreCase("null")) {

                        if (responseIRSReturnModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your Session is Expired");
                            Logout.logout(mContext);

                        } else {

                            utils.errorMessage(mContext, responseIRSReturnModel.getEM());

                        }
                    }
                }


            }

            if (Mode != null && Mode.equalsIgnoreCase("View"))
                disableLayout();


        }
    };

    private void SaveBalanceDueDetailsByID() {

        getIRSReturnModel = new IRSReturnModel();

        getIRSReturnModel.setAT(AppConfigManager.getAccessToken(mContext));

        getIRSReturnModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getIRSReturnModel.setRID(AppConfigManager.getReturnRID(mContext));

        getIRSReturnModel.setDId(AppConfigManager.getDID(mContext));

        if (estimatedTax_EditText.getText() != null && estimatedTax_EditText.getText().toString().length() > 0)

            getIRSReturnModel.setTOTTAX(estimatedTax_EditText.getText().toString());

        else

            getIRSReturnModel.setTOTTAX("0");


        if (payment_made_EditText.getText() != null && payment_made_EditText.getText().toString().length() > 0)

            getIRSReturnModel.setTOTPAYMENTS(payment_made_EditText.getText().toString());

        else

            getIRSReturnModel.setTOTPAYMENTS("0");

        if (balanceDue_EditText.getText() != null && balanceDue_EditText.getText().toString().length() > 0)

            getIRSReturnModel.setTOTBALDUE(balanceDue_EditText.getText().toString());

        else

            getIRSReturnModel.setTOTBALDUE("0");

        getIRSReturnModel.setIRSPaymentOptionId(responseIRSReturnModel.getIRSPaymentOptionId());
        getIRSReturnModel.setIRSPaymentId(responseIRSReturnModel.getIRSPaymentId());
        getIRSReturnModel.setAccountNumber(responseIRSReturnModel.getAccountNumber());
        getIRSReturnModel.setAccountTypeID(responseIRSReturnModel.getAccountTypeID());
        getIRSReturnModel.setRTN(responseIRSReturnModel.getRTN());

        if (responseIRSReturnModel.getPaymentDateStr() != null && !responseIRSReturnModel.getPaymentDateStr().equalsIgnoreCase("null"))
            getIRSReturnModel.setPaymentDateStr(responseIRSReturnModel.getPaymentDateStr());

        if (responseIRSReturnModel.getTaxpayerDayTimePhone() != null && !responseIRSReturnModel.getTaxpayerDayTimePhone().equalsIgnoreCase("null"))
            getIRSReturnModel.setTaxpayerDayTimePhone(responseIRSReturnModel.getTaxpayerDayTimePhone());
        getIRSReturnModel.setAmount(responseIRSReturnModel.getAmount());
        getIRSReturnModel.setEFWPin(responseIRSReturnModel.getEFWPin());

        if (getIRSPaymentDetailsURL != null)

            getIRSPaymentDetailsURL.cancel(true);

        saveIRSPaymentDetailsURL = new SaveIRSPaymentDetailsURL(getIRSReturnModel.saveBalaceDueDetail(), mContext);

        saveIRSPaymentDetailsURL.setOnResultListener(saveDetails);

        saveIRSPaymentDetailsURL.execute();

    }

    SaveIRSPaymentDetailsURL.SaveBalanceDueInterface saveDetails = new SaveIRSPaymentDetailsURL.SaveBalanceDueInterface() {

        @Override
        public void onResultSuccess(IRSReturnModel iRSReturnModel) {

            responseSaveBalanceDue = iRSReturnModel;

            handler.post(saveBalanceDueRunnable);

        }
    };

    Runnable saveBalanceDueRunnable = new Runnable() {
        @Override
        public void run() {

            if (responseSaveBalanceDue != null) {

                if (responseSaveBalanceDue.getOS() != null && responseSaveBalanceDue.getOS().equalsIgnoreCase("Success")) {

                    utils.successMessage(mContext, "Balance Due Saved Successfully");

                    nextPage();

                } else if (responseSaveBalanceDue.getOS() != null && responseSaveBalanceDue.getOS().equalsIgnoreCase("Failure")) {

                    if (responseSaveBalanceDue.getEM() != null && !responseSaveBalanceDue.getEM().equalsIgnoreCase("null")) {

                        if (responseSaveBalanceDue.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your Session is Expired");
                            Logout.logout(mContext);

                        } else {

                            utils.errorMessage(mContext, responseSaveBalanceDue.getEM());

                        }
                    }
                }
            }

        }
    };

    private void nextPage() {


        if (balanceDue_EditText != null && balanceDue_EditText.getText() != null && balanceDue_EditText.getText().toString().trim().equalsIgnoreCase("0")) {

            booksInCarePageFragment();

        } else {

            try {

                if (AppConfigManager.getFCID(mContext) != null && !AppConfigManager.getFCID(mContext).equalsIgnoreCase("null"))

                    FCId = Integer.parseInt(AppConfigManager.getFCID(mContext));

            } catch (Exception e) {

                e.printStackTrace();
                new SendException(mContext, e);

            }
            switch (FCId) {

                case 0:

                    booksInCarePageFragment();
                    break;

                case 1:

                    booksInCarePageFragment();
                    break;

                case 2:

                    IRSPageFragment();
                    break;

                case 4:

                    IRSPageFragment();
                    break;

                case 5:

                    IRSPageFragment();
                    break;

                case 6:

                    IRSPageFragment();
                    break;

                case 7:

                    IRSPageFragment();
                    break;

                case 8:

                    booksInCarePageFragment();
                    break;

                case 9:

                    IRSPageFragment();
                    break;

                case 10:

                    booksInCarePageFragment();
                    break;

                case 11:

                    IRSPageFragment();
                    break;

                case 12:

                    IRSPageFragment();
                    break;

                case 25:

                    IRSPageFragment();
                    break;


                default:

                    booksInCarePageFragment();
                    break;

            }
        }

    }


    private void IRSPageFragment() {


        Fragment newFragment = new IRSPayment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.IRS_PAYMENT_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    private void booksInCarePageFragment() {

        //// TODO: 11/23/2015

        Fragment newFragment = new BooksInCareOf(mContext, false);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    private void addLeftFragment(int sId) {

        Fragment newFragment = new TaxLeftFragment(mContext, sId);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    private void setTypeFont() {

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Italic());


    }

    private void disableLayout() {

        DisableEdittext.disableAll(mContext, WholeLayout);

//        formspinner.setAdapter(null);
//        formspinner.setEnabled(false);
//        formspinner.setClickable(false);
//        formspinner.setFocusable(false);

    }
}
