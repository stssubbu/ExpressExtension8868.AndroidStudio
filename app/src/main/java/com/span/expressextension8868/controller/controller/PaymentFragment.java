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
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.CreditModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.AddressURL;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by STS-099 on 12/10/2015.
 */
public class PaymentFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;

    String totalPayment, CardType, EIN, BN;

    boolean isPaid;

    int SCountryId = 0, SStateId = 0, selectedMonth = -1, selectedYear = -1;

    // Array list

    // Array list

    Integer[] imageArray = {R.drawable.visa, R.drawable.master, R.drawable.disnet, R.drawable.ae};

    ArrayList<String> listOfPattern = new ArrayList<String>();

    ArrayList<String> CountryNameList, StateNameList, cardTypeArrList;

    ArrayList<String> MonthArrList, YearArrList;

    String[] cardTypeList, MonthList;

    // progress

    private ProgressDialog pd;


    // component

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;

    android.support.v7.widget.CardView Next, Cancel;

    TextView PaymentTotalPrice, CancelText, NextText, text1, text2;


    com.rengwuxian.materialedittext.MaterialEditText NameOnCardtext, CardNumberText, cvvText;

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner monthSpinner, yearSpinner;

    LinearLayout CardLayout, BCCountryLayout;


    CheckBox BCOusideAddressCheckBox;

    com.rengwuxian.materialedittext.MaterialEditText BCAddress1Edittext1, BCAddresse2ditText2, BCCityeditText, BCStateorprovinceEdit, BCZipeditText, BCFaxEditText;

    com.rengwuxian.materialedittext.MaterialEditText BCNameEditText, BCTitleEditText, BCDayPhoneEditText;

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner BCStateSpinner, BCCountrySpinner;

    CreditModel creditModel;


    AddBussinessInputModel businessLookupModel;

    AddBussinessInputModel addressValues;


    public PaymentFragment(Context mContext, String totalPayment, boolean isPaid) {

        this.mContext = mContext;

        this.totalPayment = totalPayment;

        this.isPaid = isPaid;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);


            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.payment_page, container, false);


                Initialization();

                onClick();

                setTypeFont();


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

    private void Initialization() {

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("YOUR PAYMENT OPTIONS");


        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }


        utils = new Utils();

        WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);

        Next = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.Next);

        Cancel = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.Cancel);

        CancelText = (TextView) CommonTaxView.findViewById(R.id.CancelText);
        NextText = (TextView) CommonTaxView.findViewById(R.id.NextText);
        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);


        PaymentTotalPrice = (TextView) CommonTaxView.findViewById(R.id.PaymentTotalPrice);

        if (totalPayment != null && !totalPayment.equalsIgnoreCase("null"))
            PaymentTotalPrice.setText(utils.decimalAmountValue(mContext, totalPayment));

        NameOnCardtext = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.NameOnCardtext);
        CardNumberText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.CardNumberText);
        cvvText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.cvvText);


        monthSpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.monthSpinner);
        yearSpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.yearSpinner);


        CardLayout = (LinearLayout) CommonTaxView.findViewById(R.id.CardLayout);

        if (isPaid)

            CardLayout.setVisibility(View.GONE);

        else {


            listOfPattern = new ArrayList<String>();

            listOfPattern = Utils.listOfPattern();

            BCCountryLayout = (LinearLayout) CommonTaxView.findViewById(R.id.BCCountryLayout);

            BCCountryLayout.setVisibility(View.GONE);

            BCOusideAddressCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.BCOusideAddressCheckBox);

            BCAddress1Edittext1 = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCAddress1Edittext1);
            BCAddresse2ditText2 = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCAddresse2ditText2);
            BCCityeditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCCityeditText);
            BCStateorprovinceEdit = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCStateorprovinceEdit);
            BCStateorprovinceEdit.setVisibility(View.GONE);
            BCZipeditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCZipeditText);
            BCFaxEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCFaxEditText);


            BCNameEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCNameEditText);
            BCTitleEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCTitleEditText);
            BCDayPhoneEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCDayPhoneEditText);


            BCStateSpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.BCStateSpinner);
            BCStateSpinner.setVisibility(View.VISIBLE);
            BCCountrySpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.BCCountrySpinner);

            getDatabaseValues();
            cardTypePopUp();
            creditMonthPopUp();
            creditYearPopUp();

            //    setAddressValues();

            getAddressValue();


        }

    }

    private void getAddressValue() {

        businessLookupModel = new AddBussinessInputModel();

        businessLookupModel.setAT(AppConfigManager.getAccessToken(mContext));

        businessLookupModel.setDID(AppConfigManager.getDID(mContext));

        businessLookupModel.setUId(AppConfigManager.getLoggedUid(mContext));

        businessLookupModel.setBId(AppConfigManager.getBID(mContext));

        AddressURL addressURL = new AddressURL(businessLookupModel.getAddressRequest(), mContext);

        addressURL.setOnResultListener(onAsyncAddress);

        addressURL.execute();
    }

    AddressURL.OnAsyncAddress onAsyncAddress = new AddressURL.OnAsyncAddress() {


        @Override
        public void onResultSuccess(AddBussinessInputModel message) {

            addressValues = message;

            handler.post(addressRunnable);
        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler handler = new Handler();
    Runnable addressRunnable = new Runnable() {
        @Override
        public void run() {

            try {


                if (addressValues != null && addressValues.getOS() != null && addressValues.getOS().equalsIgnoreCase("Success")) {

//                    if (addressValues.getPrincipalOfcPhonenumber() != null && !addressValues.getPrincipalOfcPhonenumber().equalsIgnoreCase("null") && addressValues.getPrincipalOfcPhonenumber().length() > 0) {
//
//                        utils.blockWatcher1(BCPhoneNumberEditText, addressValues.getPrincipalOfcPhonenumber(), phoneTextWatcer);
//
//                    }


                    if (addressValues.getAddressOutSideus_Address().equalsIgnoreCase("true")) {

                        BCOusideAddressCheckBox.setChecked(true);

                        BCStateSpinner.setVisibility(View.GONE);

                        BCStateorprovinceEdit.setVisibility(View.VISIBLE);

                        BCCountryLayout.setVisibility(View.VISIBLE);

                        if (addressValues.getAddress_Stateorprovince() != null && !addressValues.getAddress_Stateorprovince().equalsIgnoreCase("null") && addressValues.getAddress_Stateorprovince().length() > 0) {

                            BCStateorprovinceEdit.setText(addressValues.getAddress_Stateorprovince());

                        }

                        if (addressValues.getAddressCountryId() != null && !addressValues.getAddressCountryId().equalsIgnoreCase("null") && addressValues.getAddressCountryId().length() > 0) {

                            String id = databasehandler.getCountryNameFromCID(addressValues.getAddressCountryId());

                            SCountryId = Integer.parseInt(addressValues.getAddressCountryId());

                            if (id != null && !id.equalsIgnoreCase("null")) {
                                try {


                                    BCCountrySpinner.setText(id);


                                } catch (Exception e) {

                                    e.printStackTrace();

                                    new SendException(mContext, e);


                                }
                            }

                        }

                        if (addressValues.getAddress_Zipcode() != null && !addressValues.getAddress_Zipcode().equalsIgnoreCase("null") && addressValues.getAddress_Zipcode().length() > 0) {


                            BCZipeditText.setText(addressValues.getAddress_Zipcode());

                        }

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(16);

                        BCZipeditText.setHint("ZIP or Postal Code");

                        BCZipeditText.setFloatingLabelText("ZIP or Postal Code");

                        BCZipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                        BCZipeditText.setFilters(maxlengthzip);

                        BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                    } else {

                        BCOusideAddressCheckBox.setChecked(false);

                        BCStateSpinner.setVisibility(View.VISIBLE);
                        BCStateorprovinceEdit.setVisibility(View.GONE);
                        BCCountryLayout.setVisibility(View.GONE);

                        if (addressValues.getAddress_Stateorprovince() != null && !addressValues.getAddress_Stateorprovince().equalsIgnoreCase("null") && addressValues.getAddress_Stateorprovince().length() > 0) {

                            BCStateSpinner.setText(addressValues.getAddress_Stateorprovince());

                            String id = databasehandler.getStateIDFromName(BCStateSpinner.getText().toString());

                            if (id != null && !id.equalsIgnoreCase("null")) {
                                try {

                                    SStateId = Integer.parseInt(id);

                                } catch (Exception e) {

                                    e.printStackTrace();

                                    new SendException(mContext, e);


                                }
                            }

                        }
                        if (addressValues.getAddress_Zipcode() != null && !addressValues.getAddress_Zipcode().equalsIgnoreCase("null") && addressValues.getAddress_Zipcode().length() > 0) {

                            BCZipeditText.setText(addressValues.getAddress_Zipcode());
                        }

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(10);

                        BCZipeditText.setHint("ZIP Code");

                        BCZipeditText.setFloatingLabelText("ZIP Code");

                        BCZipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                        BCZipeditText.setFilters(maxlengthzip);

                        BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                    }


                    if (addressValues.getAddress_Address1() != null && !addressValues.getAddress_Address1().equalsIgnoreCase("null") && addressValues.getAddress_Address1().length() > 0) {

                        BCAddress1Edittext1.setText(addressValues.getAddress_Address1());


                    }

                    if (addressValues.getAddress_Address2() != null && !addressValues.getAddress_Address2().equalsIgnoreCase("null") && addressValues.getAddress_Address2().length() > 0) {

                        BCAddresse2ditText2.setText(addressValues.getAddress_Address2());
                    }

                    if (addressValues.getAddress_City() != null && !addressValues.getAddress_City().equalsIgnoreCase("null") && addressValues.getAddress_City().length() > 0) {


                        BCCityeditText.setText(addressValues.getAddress_City());

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }


        }
    };


    private void getDatabaseValues() {

        if (databasehandler == null) {

            databasehandler = new DatabaseHandler(mContext);
        }

        try {

            CountryNameList = databasehandler.getCountrynameList();

            if (CountryNameList != null && CountryNameList.size() > 0) {

                ArrayAdapter<String> spinner = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, CountryNameList);

                BCCountrySpinner.setAdapter(spinner);

            }


        } catch (Exception e) {

            new SendException(mContext, e);

            e.printStackTrace();
        }

        try {

            StateNameList = databasehandler.getStatenameList();

            if (StateNameList != null && StateNameList.size() > 0) {


                ArrayAdapter<String> spinner = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, StateNameList);

                BCStateSpinner.setAdapter(spinner);

            }


        } catch (Exception e) {

            new SendException(mContext, e);

            e.printStackTrace();
        }


    }

    private void cardTypePopUp() {

        try {

            cardTypeList = mContext.getResources().getStringArray(R.array.cardtypearray);

            cardTypeArrList = new ArrayList<String>();

            for (String file : cardTypeList) {

                cardTypeArrList.add(file);
            }

        } catch (Exception e) {

            new SendException(mContext, e);

            e.printStackTrace();
        }

    }

    private void creditMonthPopUp() {
        try {


            MonthList = mContext.getResources().getStringArray(R.array.montharray);

            MonthArrList = new ArrayList<String>();

            for (String file : MonthList) {

                MonthArrList.add(file);
            }

            if (MonthArrList != null && MonthArrList.size() > 0) {


                ArrayAdapter<String> spinner = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, MonthArrList);

                monthSpinner.setAdapter(spinner);

            }
        } catch (Exception e) {

            new SendException(mContext, e);

            e.printStackTrace();
        }

    }

    private void creditYearPopUp() {
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);

            YearArrList = new ArrayList<String>();

            for (int i = 0; i < 15; i++) {

                YearArrList.add(String.valueOf(year));

                year++;

            }
            if (YearArrList != null && YearArrList.size() > 0) {

                ArrayAdapter<String> spinner = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, YearArrList);

                yearSpinner.setAdapter(spinner);

            }
        } catch (Exception e) {

            new SendException(mContext, e);

            e.printStackTrace();
        }

    }


    private void setAddressValues() {


        try {

            ArrayList<String> address = new ArrayList<String>();

            address = AppConfigManager.getOrgAddress(mContext);

            if (address != null) {

                BCAddress1Edittext1.setText(address.get(0));

                BCAddresse2ditText2.setText(address.get(1));

                BCCityeditText.setText(address.get(2));


                if (address.get(6).equalsIgnoreCase("true")) {

                    BCOusideAddressCheckBox.setChecked(true);

                    BCCountrySpinner.setText(address.get(4));

                    BCStateorprovinceEdit.setText(address.get(3));

                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(16);

                    BCZipeditText.setHint("Postal Code");

                    BCZipeditText.setFilters(maxlengthzip);

                    String id = databasehandler.getCountryPosFromName(BCStateSpinner.getText().toString());

                    if (id != null && !id.equalsIgnoreCase("null")) {
                        try {

                            SCountryId = Integer.parseInt(id);

                        } catch (Exception e) {

                            e.printStackTrace();

                            new SendException(mContext, e);


                        }
                    }


                } else {

                    BCOusideAddressCheckBox.setChecked(false);

                    BCStateSpinner.setText(address.get(3));

                    String id = databasehandler.getStateIDFromName(BCStateSpinner.getText().toString());

                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(10);

                    BCZipeditText.setHint("ZIP Code");

                    BCZipeditText.setFilters(maxlengthzip);

                    if (id != null && !id.equalsIgnoreCase("null")) {
                        try {

                            SStateId = Integer.parseInt(id);

                        } catch (Exception e) {

                            e.printStackTrace();

                            new SendException(mContext, e);


                        }
                    }


                }


                BCZipeditText.setText(address.get(5));


            }

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    private void onClick() {


        BCOusideAddressCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {

                    BCStateSpinner.setVisibility(View.GONE);

                    BCStateorprovinceEdit.setVisibility(View.VISIBLE);

                    BCCountryLayout.setVisibility(View.VISIBLE);


                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(16);

                    BCZipeditText.setHint("ZIP or Postal Code");

                    BCZipeditText.setFloatingLabelText("ZIP or Postal Code");

                    BCZipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                    BCZipeditText.setFilters(maxlengthzip);

                    BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                } else {

                    BCOusideAddressCheckBox.setChecked(false);

                    BCStateSpinner.setVisibility(View.VISIBLE);
                    BCStateorprovinceEdit.setVisibility(View.GONE);
                    BCCountryLayout.setVisibility(View.GONE);


                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(10);

                    BCZipeditText.setHint("ZIP Code");

                    BCZipeditText.setFloatingLabelText("ZIP Code");

                    BCZipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                    BCZipeditText.setFilters(maxlengthzip);

                    BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                }


            }
        });

        BCCountrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        BCStateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

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

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                Fragment newFragment = new OrderDetailsFragment(mContext);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.rightFragment, newFragment);

                transaction.addToBackStack(FragmentNameConfig.ORDER_DETAIL_FRAGMENT);

                transaction.commit();
// Commit the transaction
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                if (ValidationCheck() == 1) {

                    TaxPageFragment();

                }

            }
        });


        CardNumberText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String ccNum = s.toString();

                CardType = "";

                if (ccNum.length() >= 2) {

                    for (int i = 0; i < listOfPattern.size(); i++) {

                        if (ccNum.substring(0, 2).matches(listOfPattern.get(i))) {

                            CardNumberText.setCompoundDrawablesWithIntrinsicBounds(imageArray[i], 0, 0, 0);

                            CardType = cardTypeArrList.get(i);

                        }
                    }
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {

                String ccNum = s.toString();

                if (!s.toString().equalsIgnoreCase("")) {

                    for (int i = 0; i < listOfPattern.size(); i++) {

                        if (ccNum.matches(listOfPattern.get(i))) {

                            CardNumberText.setCompoundDrawablesWithIntrinsicBounds(imageArray[i], 0, 0, 0);

                            CardType = cardTypeArrList.get(i);

                        }
                    }
                } else {

                    CardNumberText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.creditempty, 0, 0, 0);

                }

            }

        });

        BCStateSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SStateId = position;

                if (position > 0)
                    BCStateSpinner.setError(null);

                else
                    BCStateSpinner.setError("Book InCareOf State is required");

            }
        });

        BCCountrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SCountryId = position;

                if (position > 0)
                    BCCountrySpinner.setError(null);

                else
                    BCCountrySpinner.setError("Book InCareOf Country is required");

            }
        });

        BCZipeditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!BCOusideAddressCheckBox.isChecked()) {
                    utils.maskingzipcode(mContext, BCZipeditText);
                }

            }
        });

        monthSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedMonth = position;

            }
        });

        yearSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedYear = position;

            }
        });

    }

    protected int ValidationCheck() {
        int flag = 1;

        creditModel = new CreditModel();

        if (NameOnCardtext.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            NameOnCardtext.setError("Name is Required");
        } else {
            NameOnCardtext.setError(null);
            creditModel.setFirstName(NameOnCardtext.getText().toString());
        }

//        if (lastnameedittext.getText().toString().equalsIgnoreCase("")) {
//            flag = 0;
//            lastnameedittext.setError("Last name Required");
//        } else {
//            lastnameedittext.setError(null);
//            creditModel.setLastName(lastnameedittext.getText().toString());
//        }

        if (CardType != null && !CardType.equalsIgnoreCase("")) {

            CardNumberText.setError(null);
            creditModel.setCardType(CardType);

        } else {

            flag = 0;
            CardNumberText.setError("Invalid Card Number");

        }

        if (CardNumberText.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            CardNumberText.setError("Credit card Number is Required");
        } else if (CardNumberText.getText().toString().length() < 15) {
            flag = 0;
            CardNumberText.setError("Please Enter minimum 15 digits");
        } else {
            CardNumberText.setError(null);

            creditModel.setCreditCardnumber(CardNumberText.getText().toString());
        }

        if (BCOusideAddressCheckBox.isChecked()) {
            creditModel.setIsforadd("true");
        } else {
            creditModel.setIsforadd("false");
        }


        if (selectedMonth == -1) {
            flag = 0;
            monthSpinner.setError("Please select the Month");
        } else {
            creditModel.setExpirymonth(String.valueOf(selectedMonth + 1));
        }

        if (selectedMonth > -1 && selectedYear > -1) {
            if (selectedMonth + 1 < Utils.getCurrentmonth() && selectedYear == 0) {
                flag = 0;

                utils.errorMessage(mContext, "Credit Card is Expired");
            }
        }

        if (selectedYear == -1) {
            flag = 0;
            yearSpinner.setError("Please select the year");
        } else {
            creditModel.setExpiryyear(yearSpinner.getText().toString());
        }

        if (cvvText.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            cvvText.setError("Please Enter the Card Code");
        } else {
            creditModel.setCvc(cvvText.getText().toString());
        }


        if (BCAddress1Edittext1.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            BCAddress1Edittext1.setError("Address 1 is required");
        } else {
            creditModel.setAddress1(BCAddress1Edittext1.getText().toString());
        }

        creditModel.setAddress2(BCAddresse2ditText2.getText().toString());

        if (BCCityeditText.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            BCCityeditText.setError("City is required");
        } else {
            creditModel.setCity(BCCityeditText.getText().toString());
        }

        if (BCStateSpinner.isShown()) {
            if (SStateId == 0) {
                flag = 0;
                BCStateSpinner.setError("State Required");
            } else {
                creditModel.setState(databasehandler.getStateIDFromPosition(String.valueOf(SStateId)));
            }
        }

        if (BCStateorprovinceEdit.isShown()) {
            if (BCStateorprovinceEdit.getText().toString().equalsIgnoreCase("")) {
                flag = 0;
                BCStateorprovinceEdit.setError("State is Required");
            } else {
                creditModel.setStateorprovince(BCStateorprovinceEdit.getText().toString());
            }

        }

        if (BCCountryLayout.isShown()) {
            if (SCountryId == 0) {
                flag = 0;
                BCCountrySpinner.setError("Country is Required");
            } else {
                creditModel.setCountry(databasehandler.getCountryIDFromPosition(String.valueOf(SCountryId)));
            }

        }

        if (BCZipeditText.getText().toString().equalsIgnoreCase("")) {
            flag = 0;
            BCZipeditText.setError("ZIP Code is Required");
        } else if (!BCOusideAddressCheckBox.isChecked() && !Utils.isValidZipCode(BCZipeditText.getText().toString())) {
            flag = 0;
            BCZipeditText.setError("Invalid ZIP Code");
        } else {
            creditModel.setZip(BCZipeditText.getText().toString());
        }

        return flag;
    }

    private void TaxPageFragment() {


        Fragment newFragment = new Transmit_IRS_Page(mContext, creditModel);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.REVIEW_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        CancelText.setTypeface(typeFaceClass.NotoSans_Regular());
        NextText.setTypeface(typeFaceClass.NotoSans_Regular());
        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());


    }
}
