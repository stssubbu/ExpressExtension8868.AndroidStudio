package com.span.expressextension8868.controller.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.IRSReturnModel;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.GetIRSPaymentDetailsURL;
import com.span.expressextension8868.webservice.webservices.SaveIRSPaymentDetailsURL;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by STS-099 on 11/21/2015.
 */
public class IRSPayment extends Fragment {

    Context mContext;

    View CommonTaxView;

    Utils utils;

    List<String> accoutTypeArraylist;

    ProgressDialog pd;

    int accTypeId = 0;

    String EIN, BN, Mode;


    //

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout;

    TextView ETCancelbutton, DashBoardTitle, DashboardOrgEin, DashboardOrgName, text1;


    android.support.v7.widget.CardView IRScancelbutton, IRSnext;

    //

    RadioGroup paySelectRadioGroup;

    RadioButton electronicFuneRadio, EFTPSRadio;

    LinearLayout ElectronicFundsLinearLayout, EFTPSLinearLayout;

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner BAccType;

    com.rengwuxian.materialedittext.MaterialEditText BAccNumberEditText, BRoutingNumberEditText, begindateTextview, BPhoneNumberEditText, EWFPinEditText;

    CheckBox EWF_PIN_CheckBox, EFTPAcceptcheckBox;

    //getValue

    IRSReturnModel getIRSReturnModel, responseIRSReturnModel;

    GetIRSPaymentDetailsURL getIRSPaymentDetailsURL;

    //save IRS

    IRSReturnModel saveBalanceDue, responseSaveBalanceDue;

    SaveIRSPaymentDetailsURL saveIRSPaymentDetailsURL;

    public IRSPayment(Context mContext) {

        this.mContext = mContext;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            Mode = AppConfigManager.getMode(mContext);


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.irspaymentoption, container, false);

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Initialization();

                onClick();

                setSpinnerAdapter();

                Overridefonts.overrideFonts(mContext, WholeLayout);

                setTypeFont();

                addLeftFragment(0);


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

        utils = new Utils();

        accoutTypeArraylist = new ArrayList<String>();

        accoutTypeArraylist = Arrays.asList(mContext.getResources().getStringArray(R.array.Account_Type_Array));


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);


        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("IRS PAYMENT OPTION");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }

        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);


        IRScancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.IRSCancelbutton);

        IRSnext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.IRSnext);

        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);

        paySelectRadioGroup = (RadioGroup) CommonTaxView.findViewById(R.id.paySelectRadioGroup);

        electronicFuneRadio = (RadioButton) CommonTaxView.findViewById(R.id.electronicFuneRadio);

        EFTPSRadio = (RadioButton) CommonTaxView.findViewById(R.id.EFTPSRadio);

        ElectronicFundsLinearLayout = (LinearLayout) CommonTaxView.findViewById(R.id.ElectronicFundsLinearLayout);

        ElectronicFundsLinearLayout.setVisibility(View.VISIBLE);

        EFTPSLinearLayout = (LinearLayout) CommonTaxView.findViewById(R.id.EFTPSLinearLayout);

        EFTPSLinearLayout.setVisibility(View.GONE);


        BAccType = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.BAccType);

        BAccNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BAccNumberEditText);

        BRoutingNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BRoutingNumberEditText);

        begindateTextview = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.begindateTextview);

        BPhoneNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BPhoneNumberEditText);

        EWFPinEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.EWFPinEditText);

        EWF_PIN_CheckBox = (CheckBox) CommonTaxView.findViewById(R.id.EWF_PIN_CheckBox);

        EFTPAcceptcheckBox = (CheckBox) CommonTaxView.findViewById(R.id.EFTPAcceptcheckBox);


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

        logolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                ((Activity) mContext).finish();


            }
        });

        IRScancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                FragmentManager fm = getActivity().getSupportFragmentManager();

                Fragment newFragment = new BalanceDueFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.BALANCE_DUE);

// Commit the transaction
                transaction.commit();

            }
        });

        IRSnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    booksInCarePageFragment();

                } else {
                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                    getIRSReturnModel = new IRSReturnModel();

                    if (electronicFuneRadio.isChecked()) {

                        getIRSReturnModel.setIRSPaymentOptionId("1");

                        if (isElectronicFund()) {

                            SaveBalanceDueDetailsByID();

                        }

                    } else if (EFTPSRadio.isChecked()) {

                        getIRSReturnModel.setIRSPaymentOptionId("0");

                        if (isEFTP()) {

                            SaveBalanceDueDetailsByID();
                        }

                    }

                }
            }
        });


        paySelectRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.electronicFuneRadio:

                        ElectronicFundsLinearLayout.setVisibility(View.VISIBLE);
                        EFTPSLinearLayout.setVisibility(View.GONE);
                        break;

                    case R.id.EFTPSRadio:

                        ElectronicFundsLinearLayout.setVisibility(View.GONE);
                        EFTPSLinearLayout.setVisibility(View.VISIBLE);
                        break;

                    default:

                        ElectronicFundsLinearLayout.setVisibility(View.VISIBLE);
                        EFTPSLinearLayout.setVisibility(View.GONE);
                        break;
                }
            }
        });

        BPhoneNumberEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                BPhoneNumberEditText.setError(null);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                utils.MaskingPhone(mContext, BPhoneNumberEditText);
            }
        });

        BAccType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                accTypeId = position + 1;

            }
        });

        begindateTextview.setOnClickListener(new View.OnClickListener() {
                                                 @SuppressLint("NewApi")
                                                 @Override
                                                 public void onClick(View v) {

                                                     try {


                                                         final Calendar c = Calendar.getInstance();

                                                         final Calendar cMax = Calendar.getInstance();

                                                         cMax.add(Calendar.DATE, 4);

                                                         int mYear = c.get(Calendar.YEAR);
                                                         int mMonth = c.get(Calendar.MONTH);
                                                         int mDay = c.get(Calendar.DAY_OF_MONTH);

                                                         Calendar now = Calendar.getInstance();
                                                         DatePickerDialog dpd = DatePickerDialog.newInstance(
                                                                 new DatePickerDialog.OnDateSetListener() {
                                                                     @Override
                                                                     public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                                                         Calendar myCalendar = Calendar.getInstance();

                                                                         myCalendar.set(Calendar.YEAR, year);
                                                                         myCalendar.set(Calendar.MONTH, monthOfYear);
                                                                         myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                                                         String myFormat = "MM/dd/yyyy"; // In which you need put here
                                                                         SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                                                                         String date = sdf.format(myCalendar.getTime());
                                                                         begindateTextview.setText(date);

                                                                     }
                                                                 },
                                                                 now.get(Calendar.YEAR),
                                                                 now.get(Calendar.MONTH),
                                                                 now.get(Calendar.DAY_OF_MONTH)
                                                         );
                                                         dpd.setMinDate(c);
                                                         dpd.setMaxDate(cMax);

                                                         dpd.setAccentColor(Color.parseColor("#00ccff"));


                                                         dpd.setTitle("Select Request Payment Date");

                                                         dpd.show(((Activity) mContext).getFragmentManager(), "Datepickerdialog");


                                                     } catch (Exception e) {

                                                         e.printStackTrace();

                                                         new SendException(mContext, e);
                                                     }

                                                 }
                                             }

        );


    }


    private boolean isElectronicFund() {

        boolean validation = true;

        getIRSReturnModel.setTOTBALDUE(responseIRSReturnModel.getTOTBALDUE());

        getIRSReturnModel.setTOTTAX(responseIRSReturnModel.getTOTTAX());

        getIRSReturnModel.setTOTPAYMENTS(responseIRSReturnModel.getTOTPAYMENTS());


        if (BAccType != null && BAccType.getText() != null && !BAccType.getText().toString().equalsIgnoreCase("null") && BAccType.getText().toString().trim().length() > 0) {

            getIRSReturnModel.setAccountTypeID(String.valueOf(accTypeId));


        } else {

            BAccType.setError("Please Select Account Type");

            validation = false;

        }

        if (BAccNumberEditText != null && BAccNumberEditText.getText() != null && !BAccNumberEditText.getText().toString().equalsIgnoreCase("null") && BAccNumberEditText.getText().toString().trim().length() > 0) {

            getIRSReturnModel.setAccountNumber(BAccNumberEditText.getText().toString());


        } else {

            BAccNumberEditText.setError("Please Enter Bank Account Number");

            validation = false;

        }

        if (BRoutingNumberEditText != null && BRoutingNumberEditText.getText() != null && BRoutingNumberEditText.getText().toString().trim().length() > 0) {

            try {
                int RtnValue = Integer.parseInt(BRoutingNumberEditText.getText().toString().substring(0, 2));

                Log.i("rtn", "rtn value : " + RtnValue);

                if (RtnValue >= 00 && RtnValue <= 12 || RtnValue >= 21 && RtnValue <= 32 || RtnValue >= 61 && RtnValue <= 72 || RtnValue == 80) {
                    getIRSReturnModel.setRTN(BRoutingNumberEditText.getText().toString());

                } else {
                    validation = false;
                    BRoutingNumberEditText.setError("Invalid Routing Number");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {

            BRoutingNumberEditText.setError("Please Enter Bank Routing Number");

            validation = false;

        }

        if (begindateTextview != null && begindateTextview.getText() != null && begindateTextview.getText().toString().trim().length() > 0) {

            getIRSReturnModel.setPaymentDateStr(begindateTextview.getText().toString());

        } else {

            begindateTextview.setError("Please Enter Request Payment Date");

            validation = false;

        }

        if (BPhoneNumberEditText != null && BPhoneNumberEditText.getText() != null && BPhoneNumberEditText.getText().toString().trim().length() > 0) {

            getIRSReturnModel.setTaxpayerDayTimePhone(BPhoneNumberEditText.getText().toString());


        } else {

            BPhoneNumberEditText.setError("Please Enter the Taxpayer Day Time Phone");

            validation = false;

        }


        if (EWFPinEditText != null && EWFPinEditText.getText() != null && EWFPinEditText.getText().toString().trim().length() > 0) {

            getIRSReturnModel.setEFWPin(EWFPinEditText.getText().toString());

        } else {

            EWFPinEditText.setError("Please Enter EFW PIN");

            validation = false;

        }

        if (EWF_PIN_CheckBox != null && EWF_PIN_CheckBox.isChecked()) {

            getIRSReturnModel.setIsEFWConsent("true");


        } else {

            getIRSReturnModel.setIsEFWConsent("false");

            utils.errorMessage(mContext, "Select Electronic Funds Withdrawal Consent");

            validation = false;

        }


        return validation;

    }


    private boolean isEFTP() {

        boolean validation = true;

        if (EFTPAcceptcheckBox != null && EFTPAcceptcheckBox.isChecked()) {

            getIRSReturnModel.setIsEFWConsent("true");

        } else {

            getIRSReturnModel.setIsEFWConsent("false");

            utils.errorMessage(mContext, "Select the Terms and Conditions");

            validation = false;
        }


        return validation;

    }

    private void setSpinnerAdapter() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, accoutTypeArraylist);

        BAccType.setAdapter(adapter);

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

    GetIRSPaymentDetailsURL.IRSPaymentInterface getDetails = new GetIRSPaymentDetailsURL.IRSPaymentInterface() {

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

                    if (responseIRSReturnModel.getIRSPaymentOptionId() != null && responseIRSReturnModel.getIRSPaymentOptionId().equalsIgnoreCase("1")) {

                        electronicFuneRadio.setChecked(true);

                        EFTPSRadio.setChecked(false);

                        ElectronicFundsLinearLayout.setVisibility(View.VISIBLE);

                        EFTPSLinearLayout.setVisibility(View.GONE);

                        if (responseIRSReturnModel.getAccountTypeID() != null && responseIRSReturnModel.getAccountTypeID().equalsIgnoreCase("1")) {
                            try {

                                accTypeId = Integer.parseInt(responseIRSReturnModel.getAccountTypeID());

                            } catch (Exception e) {

                                accTypeId = 1;

                                new SendException(mContext, e);
                            }


                            BAccType.setText(accoutTypeArraylist.get(0));

                        } else {

                            try {

                                accTypeId = Integer.parseInt(responseIRSReturnModel.getAccountTypeID());

                            } catch (Exception e) {

                                accTypeId = 2;

                                new SendException(mContext, e);
                            }

                            BAccType.setText(accoutTypeArraylist.get(1));
                        }

                        if (responseIRSReturnModel.getAccountNumber() != null && !responseIRSReturnModel.getAccountNumber().equalsIgnoreCase("null")) {

                            BAccNumberEditText.setText(responseIRSReturnModel.getAccountNumber());
                        } else {

                            BAccNumberEditText.setText("");
                        }


                        if (responseIRSReturnModel.getRTN() != null && !responseIRSReturnModel.getRTN().equalsIgnoreCase("null")) {

                            BRoutingNumberEditText.setText(responseIRSReturnModel.getRTN());

                        } else {

                            BRoutingNumberEditText.setText("");
                        }
                        if (responseIRSReturnModel.getPaymentDateStr() != null && !responseIRSReturnModel.getPaymentDateStr().equalsIgnoreCase("null")) {

                            begindateTextview.setText(responseIRSReturnModel.getPaymentDateStr());

                        } else {

                            begindateTextview.setText("");
                        }


                        if (responseIRSReturnModel.getTaxpayerDayTimePhone() != null && !responseIRSReturnModel.getTaxpayerDayTimePhone().equalsIgnoreCase("null")) {

                            BPhoneNumberEditText.setText(responseIRSReturnModel.getTaxpayerDayTimePhone());

                        } else {

                            BPhoneNumberEditText.setText("");
                        }

                        if (responseIRSReturnModel.getEFWPin() != null && !responseIRSReturnModel.getEFWPin().equalsIgnoreCase("null")) {

                            EWFPinEditText.setText(responseIRSReturnModel.getEFWPin());

                        } else {

                            EWFPinEditText.setText("");
                        }

                        EWF_PIN_CheckBox.setChecked(true);

                        EFTPAcceptcheckBox.setChecked(false);

                    } else if (responseIRSReturnModel.getIRSPaymentOptionId() != null && responseIRSReturnModel.getIRSPaymentOptionId().equalsIgnoreCase("0")) {

                        electronicFuneRadio.setChecked(false);

                        EFTPSRadio.setChecked(true);

                        ElectronicFundsLinearLayout.setVisibility(View.GONE);

                        EFTPSLinearLayout.setVisibility(View.VISIBLE);

                        EWF_PIN_CheckBox.setChecked(false);

                        EFTPAcceptcheckBox.setChecked(true);

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


                //// TODO: 11/23/2015


            }
            if (Mode != null && Mode.equalsIgnoreCase("View"))
                disableLayout();


        }
    };

    private void SaveBalanceDueDetailsByID() {


        getIRSReturnModel.setAT(AppConfigManager.getAccessToken(mContext));

        getIRSReturnModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getIRSReturnModel.setRID(AppConfigManager.getReturnRID(mContext));

        getIRSReturnModel.setDId(AppConfigManager.getDID(mContext));

        getIRSReturnModel.setTOTTAX(responseIRSReturnModel.getTOTTAX());

        getIRSReturnModel.setTOTPAYMENTS(responseIRSReturnModel.getTOTPAYMENTS());

        getIRSReturnModel.setTOTBALDUE(responseIRSReturnModel.getTOTBALDUE());

        if (getIRSPaymentDetailsURL != null)

            getIRSPaymentDetailsURL.cancel(true);

        saveIRSPaymentDetailsURL = new SaveIRSPaymentDetailsURL(getIRSReturnModel.saveIRSPaymentDetail(), mContext);

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

                    utils.successMessage(mContext, "IRS Payment Saved Successfully");

                    try {

                        booksInCarePageFragment();

                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);

                    }


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
        EWF_PIN_CheckBox.setTypeface(typeFaceClass.NotoSans_Bold());
        EFTPAcceptcheckBox.setTypeface(typeFaceClass.NotoSans_Bold());
    }

    private void disableLayout() {

        DisableEdittext.disableAll(mContext, WholeLayout);

        BAccType.setAdapter(null);
        BAccType.setEnabled(false);
        BAccType.setClickable(false);

    }

}
