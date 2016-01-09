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
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.BookIncareOfModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.AddressURL;
import com.span.expressextension8868.webservice.webservices.GetBooksInCareOfURL;
import com.span.expressextension8868.webservice.webservices.SaveBookInCareOfDetails;

import java.util.ArrayList;

/**
 * Created by STS-099 on 11/25/2015.
 */
public class BooksInCareOf extends Fragment {

    Context mContext;

    View CommonTaxView;

    DatabaseHandler databasehandler;

    Utils utils;

    String RID, EIN, BN, Mode;

    boolean fromIRS;

    int SCountryId = 0, SStateId = 0;

    // Array list


    ArrayList<String> CountryNameList, StateNameList;


    // progress

    private ProgressDialog pd;

    // get books

    GetBooksInCareOfURL getBooksInCareOfURL;

    BookIncareOfModel getResponseValues, saveBookModel, saveResponseValues;

    SaveBookInCareOfDetails saveBookInCareOfDetails;


    //components

    //componenet

    LinearLayout logolayout, DashboardOrgDetailsLayout, WholeLayout, allLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName, text1, text2;

    android.support.v7.widget.CardView BCnext, BCCancelbutton;

    TextView BCnextText, BCCancelbuttonText;


    CheckBox BCIsBusinessCheckBox, BCIsSameAddress, BCOusideAddressCheckBox;

    com.rengwuxian.materialedittext.MaterialEditText BCNameOfPersonEditText, BCPhoneNumberEditText;

    LinearLayout BCCountryLayout;

    com.rengwuxian.materialedittext.MaterialEditText BCAddress1Edittext1, BCAddresse2ditText2, BCCityeditText, BCStateorprovinceEdit, BCZipeditText, BCFaxEditText;

    com.rengwuxian.materialedittext.MaterialEditText BCNameEditText, BCTitleEditText, BCDayPhoneEditText;

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner BCStateSpinner, BCCountrySpinner;

    public BooksInCareOf(Context mContext, boolean fromIRS) {

        this.mContext = mContext;

        this.fromIRS = fromIRS;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {
            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            Mode = AppConfigManager.getMode(mContext);


            CommonTaxView = inflater.inflate(R.layout.books_in_care, container, false);

            InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

            in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            Initialization();

            onClick();

            setTypeFont();

            addLeftFragment();

            getDatabaseValues();

            GetReturnDetailsByID();


        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return CommonTaxView;

    }


    private void Initialization() {

        databasehandler = new DatabaseHandler(mContext);

        utils = new Utils();

        try {

            DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

            DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

            logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

            DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
            DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
            DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

            DashBoardTitle.setText("BOOKS ARE IN THE CARE OF");

            if (EIN != null && BN != null) {

                DashboardOrgEin.setText(EIN);

                DashboardOrgName.setText(BN);
            }

            WholeLayout = (LinearLayout) CommonTaxView.findViewById(R.id.WholeLayout);

            allLayout = (LinearLayout) CommonTaxView.findViewById(R.id.allLayout);

            BCnext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.BCnext);
            BCCancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.BCCancelbutton);

            BCnextText = (TextView) CommonTaxView.findViewById(R.id.BCnextText);
            BCCancelbuttonText = (TextView) CommonTaxView.findViewById(R.id.BCCancelbuttonText);

            text1 = (TextView) CommonTaxView.findViewById(R.id.text1);
            text2 = (TextView) CommonTaxView.findViewById(R.id.text2);
            BCIsBusinessCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.BCIsBusinessCheckBox);
            BCIsSameAddress = (CheckBox) CommonTaxView.findViewById(R.id.BCIsSameAddress);
            BCOusideAddressCheckBox = (CheckBox) CommonTaxView.findViewById(R.id.BCOusideAddressCheckBox);

            BCNameOfPersonEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCNameOfPersonEditText);
            BCPhoneNumberEditText = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.BCPhoneNumberEditText);


            BCCountryLayout = (LinearLayout) CommonTaxView.findViewById(R.id.BCCountryLayout);
            BCCountryLayout.setVisibility(View.GONE);
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


        } catch (Exception e) {
            new SendException(mContext, e);

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

        BCCancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                if (fromIRS) {

                    Fragment newFragment = new IRSPayment(mContext);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                    transaction.replace(R.id.rightFragment, newFragment);

                    transaction.addToBackStack(FragmentNameConfig.IRS_PAYMENT_FRAGMENT);

// Commit the transaction
                    transaction.commit();


                } else {

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


            }
        });

        BCnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    SummaryPageFragment();

                } else {

                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                    if (isBookValidation()) {

                        saveBookDetails();

                    }

                }

            }
        });

        BCIsBusinessCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    BCNameOfPersonEditText.setHint("Name of Business");

                    BCNameOfPersonEditText.setFloatingLabelText("Name of Business");

                } else {

                    BCNameOfPersonEditText.setHint("Name of Person");

                    BCNameOfPersonEditText.setFloatingLabelText("Name of Person");
                }

            }
        });

        BCIsSameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    try {


                        if (addressValues != null && addressValues.getOS() != null && addressValues.getOS().equalsIgnoreCase("Success")) {

                            if (addressValues.getPrincipalOfcPhonenumber() != null && !addressValues.getPrincipalOfcPhonenumber().equalsIgnoreCase("null") && addressValues.getPrincipalOfcPhonenumber().length() > 0) {

                                utils.blockWatcher1(BCPhoneNumberEditText, addressValues.getPrincipalOfcPhonenumber(), phoneTextWatcer);

                            }


                            if (addressValues.getPrincipal_Address_Outside_Us().equalsIgnoreCase("true")) {

                                BCOusideAddressCheckBox.setOnCheckedChangeListener(null);

                                BCOusideAddressCheckBox.setChecked(true);

                                BCOusideAddressCheckBox.setOnCheckedChangeListener(adderessChecked);

                                BCStateSpinner.setVisibility(View.GONE);

                                BCStateorprovinceEdit.setVisibility(View.VISIBLE);

                                BCCountryLayout.setVisibility(View.VISIBLE);

                                if (addressValues.getPrincipalStateorProvince() != null && !addressValues.getPrincipalStateorProvince().equalsIgnoreCase("null") && addressValues.getPrincipalStateorProvince().length() > 0) {

                                    utils.blockWatcher1(BCStateorprovinceEdit, addressValues.getPrincipalStateorProvince(), stateOrProvinceTextWatcer);

                                }

                                if (addressValues.getPrincipalCountryId() != null && !addressValues.getPrincipalCountryId().equalsIgnoreCase("null") && addressValues.getPrincipalCountryId().length() > 0) {

                                    String id = databasehandler.getCountryNameFromCID(addressValues.getPrincipalCountryId());

                                    SCountryId = Integer.parseInt(addressValues.getPrincipalCountryId());

                                    if (id != null && !id.equalsIgnoreCase("null")) {
                                        try {


                                            BCCountrySpinner.setText(id);


                                        } catch (Exception e) {

                                            e.printStackTrace();

                                            new SendException(mContext, e);


                                        }
                                    }

                                }

                                if (addressValues.getPrincipalZipcode() != null && !addressValues.getPrincipalZipcode().equalsIgnoreCase("null") && addressValues.getPrincipalZipcode().length() > 0) {


                                    utils.blockWatcher1(BCZipeditText, addressValues.getPrincipalZipcode(), ZipTextWatcer);

                                }

                                InputFilter[] maxlengthzip = new InputFilter[1];

                                maxlengthzip[0] = new InputFilter.LengthFilter(16);

                                BCZipeditText.setHint("ZIP or Postal Code");

                                BCZipeditText.setFloatingLabelText("ZIP or Postal Code");

                                BCZipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                                BCZipeditText.setFilters(maxlengthzip);

                                BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                            } else {


                                BCOusideAddressCheckBox.setOnCheckedChangeListener(null);
                                BCOusideAddressCheckBox.setChecked(false);
                                BCOusideAddressCheckBox.setOnCheckedChangeListener(adderessChecked);

                                BCStateSpinner.setVisibility(View.VISIBLE);
                                BCStateorprovinceEdit.setVisibility(View.GONE);
                                BCCountryLayout.setVisibility(View.GONE);

                                if (addressValues.getPrincipalStateorProvince() != null && !addressValues.getPrincipalStateorProvince().equalsIgnoreCase("null") && addressValues.getPrincipalStateorProvince().length() > 0) {

                                    BCStateSpinner.setText(addressValues.getPrincipalStateorProvince());

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
                                if (addressValues.getPrincipalZipcode() != null && !addressValues.getPrincipalZipcode().equalsIgnoreCase("null") && addressValues.getPrincipalZipcode().length() > 0) {

                                    utils.blockWatcher1(BCZipeditText, addressValues.getPrincipalZipcode(), ZipTextWatcer);
                                }

                                InputFilter[] maxlengthzip = new InputFilter[1];

                                maxlengthzip[0] = new InputFilter.LengthFilter(10);

                                BCZipeditText.setHint("ZIP Code");

                                BCZipeditText.setFloatingLabelText("ZIP Code");

                                BCZipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                                BCZipeditText.setFilters(maxlengthzip);

                                BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                            }


                            if (addressValues.getPrincipalAddress1() != null && !addressValues.getPrincipalAddress1().equalsIgnoreCase("null") && addressValues.getPrincipalAddress1().length() > 0) {

                                utils.blockWatcher1(BCAddress1Edittext1, addressValues.getPrincipalAddress1(), Address1TextWatcer);


                            }

                            if (addressValues.getPrincipalAddress2() != null && !addressValues.getPrincipalAddress2().equalsIgnoreCase("null") && addressValues.getPrincipalAddress2().length() > 0) {

                                utils.blockWatcher1(BCAddresse2ditText2, addressValues.getPrincipalAddress2(), Address2TextWatcer);
                            }

                            if (addressValues.getPrincipalCity() != null && !addressValues.getPrincipalCity().equalsIgnoreCase("null") && addressValues.getPrincipalCity().length() > 0) {


                                utils.blockWatcher1(BCCityeditText, addressValues.getPrincipalCity(), cityTextWatcer);

                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                }
            }
        });


        BCOusideAddressCheckBox.setOnCheckedChangeListener(adderessChecked);


        BCDayPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {


                utils.MaskingPhone(mContext, BCDayPhoneEditText);
            }
        });


        BCStateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });


        BCCountrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(CommonTaxView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        BCStateSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SStateId = position;

                BCIsSameAddress.setChecked(false);

                if (position > 0)
                    BCStateSpinner.setError(null);

                else
                    BCStateSpinner.setError("State is required");

            }
        });

        BCCountrySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SCountryId = position;

                BCIsSameAddress.setChecked(false);

                if (position > 0)
                    BCCountrySpinner.setError(null);

                else
                    BCCountrySpinner.setError("Country is required");

            }
        });

        BCZipeditText.addTextChangedListener(ZipTextWatcer);

        BCPhoneNumberEditText.addTextChangedListener(phoneTextWatcer);

        BCFaxEditText.addTextChangedListener(FaxTextWatcer);

        BCAddress1Edittext1.addTextChangedListener(Address1TextWatcer);

        BCAddresse2ditText2.addTextChangedListener(Address2TextWatcer);
        BCCityeditText.addTextChangedListener(cityTextWatcer);

        BCStateorprovinceEdit.addTextChangedListener(stateOrProvinceTextWatcer);


    }

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


    private void GetReturnDetailsByID() {

        BookIncareOfModel getBooksModel = new BookIncareOfModel();

        getBooksModel.setAT(AppConfigManager.getAccessToken(mContext));

        getBooksModel.setUId(AppConfigManager.getLoggedUid(mContext));

        getBooksModel.setRID(AppConfigManager.getReturnRID(mContext));

        getBooksModel.setDId(AppConfigManager.getDID(mContext));

        if (getBooksInCareOfURL != null)

            getBooksInCareOfURL.cancel(true);

        getBooksInCareOfURL = new GetBooksInCareOfURL(getBooksModel.getBooksInCareOfModel(mContext), mContext);

        getBooksInCareOfURL.setOnResultListener(getBookInCareAsync);

        getBooksInCareOfURL.execute();


    }

    GetBooksInCareOfURL.OnAsyncResultGetBookIncareOf getBookInCareAsync = new GetBooksInCareOfURL.OnAsyncResultGetBookIncareOf() {


        @Override
        public void onResultSuccess(BookIncareOfModel message) {

            getResponseValues = message;

            handler.post(getBooksRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    Handler handler = new Handler();

    Runnable getBooksRunnable = new Runnable() {
        @Override
        public void run() {

            if (getResponseValues != null && getResponseValues.getOS() != null && getResponseValues.getOS().equalsIgnoreCase("Success")) {

                if (getResponseValues != null && getResponseValues.getBookCareOfDetailsId() != null && !getResponseValues.getBookCareOfDetailsId().equalsIgnoreCase("0")) {

                    BCIsBusinessCheckBox.setChecked(getResponseValues.isBCOBusiness());

                    if (getResponseValues.isBCOBusiness()) {

                        BCNameOfPersonEditText.setHint("Name of Business");

                    } else {

                        BCNameOfPersonEditText.setHint("Name of Person");

                    }

                    if (getResponseValues.getBookInCareOf() != null && !getResponseValues.getBookInCareOf().equalsIgnoreCase("null") && getResponseValues.getBookInCareOf().length() > 0) {

                        BCNameOfPersonEditText.setText(getResponseValues.getBookInCareOf());

                    }

                    if (getResponseValues.getPhone() != null && !getResponseValues.getPhone().equalsIgnoreCase("null") && getResponseValues.getPhone().length() > 0) {

                        utils.blockWatcher1(BCPhoneNumberEditText, getResponseValues.getPhone(), phoneTextWatcer);

                    }


                    BCOusideAddressCheckBox.setChecked(getResponseValues.isBCOIsForeignAddress());

                    if (getResponseValues.isBCOIsForeignAddress()) {

                        BCStateSpinner.setVisibility(View.GONE);
                        BCStateorprovinceEdit.setVisibility(View.VISIBLE);
                        BCCountryLayout.setVisibility(View.VISIBLE);

                        if (getResponseValues.getBCOState() != null && !getResponseValues.getBCOState().equalsIgnoreCase("null") && getResponseValues.getBCOState().length() > 0) {

                            utils.blockWatcher1(BCStateorprovinceEdit, getResponseValues.getBCOState(), stateOrProvinceTextWatcer);

                        }

                        if (getResponseValues.getBCOCountryId() != null && !getResponseValues.getBCOCountryId().equalsIgnoreCase("null") && getResponseValues.getBCOCountryId().length() > 0) {


                            String id = databasehandler.getCountryNameFromCID(getResponseValues.getBCOCountryId());

                            if (id != null && !id.equalsIgnoreCase("null")) {
                                try {

                                    BCCountrySpinner.setText(id);

                                    SCountryId = Integer.parseInt(getResponseValues.getBCOCountryId());


                                } catch (Exception e) {

                                    e.printStackTrace();

                                    new SendException(mContext, e);


                                }
                            }

                        }

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(10);

                        BCZipeditText.setHint("ZIP Code");

                        BCZipeditText.setFloatingLabelText("ZIP Code");

                        BCZipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                        BCZipeditText.setFilters(maxlengthzip);

                        BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                    } else {

                        BCStateSpinner.setVisibility(View.VISIBLE);
                        BCStateorprovinceEdit.setVisibility(View.GONE);
                        BCCountryLayout.setVisibility(View.GONE);

                        if (getResponseValues.getBCOState() != null && !getResponseValues.getBCOState().equalsIgnoreCase("null") && getResponseValues.getBCOState().length() > 0) {

                            BCStateSpinner.setText(getResponseValues.getBCOState());

                            String id = databasehandler.getStateIDFromName(BCStateSpinner.getText().toString());

                            if (id != null && !id.equalsIgnoreCase("null") && id.length() > 0) {
                                try {

                                    SStateId = Integer.parseInt(id);

                                } catch (Exception e) {

                                    e.printStackTrace();

                                    new SendException(mContext, e);

                                }
                            }

                        }

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(16);

                        BCZipeditText.setHint("ZIP or Postal Code");

                        BCZipeditText.setFloatingLabelText("ZIP or Postal Code");

                        BCZipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                        BCZipeditText.setFilters(maxlengthzip);

                        BCZipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                    }


                    if (getResponseValues.getBCOAddress1() != null && !getResponseValues.getBCOAddress1().equalsIgnoreCase("null") && getResponseValues.getBCOAddress1().length() > 0) {


                        utils.blockWatcher1(BCAddress1Edittext1, getResponseValues.getBCOAddress1(), Address1TextWatcer);

                    }

                    if (getResponseValues.getBCOAddress2() != null && !getResponseValues.getBCOAddress2().equalsIgnoreCase("null") && getResponseValues.getBCOAddress2().length() > 0) {

                        utils.blockWatcher1(BCAddresse2ditText2, getResponseValues.getBCOAddress2(), Address2TextWatcer);

                    }

                    if (getResponseValues.getBCOCity() != null && !getResponseValues.getBCOCity().equalsIgnoreCase("null") && getResponseValues.getBCOCity().length() > 0) {


                        utils.blockWatcher1(BCCityeditText, getResponseValues.getBCOCity(), cityTextWatcer);

                    }

                    if (getResponseValues.getBCOZip() != null && !getResponseValues.getBCOZip().equalsIgnoreCase("null") && getResponseValues.getBCOZip().length() > 0) {

                        utils.blockWatcher1(BCZipeditText, getResponseValues.getBCOZip(), ZipTextWatcer);

                    }

                    if (getResponseValues.getFax() != null && !getResponseValues.getFax().equalsIgnoreCase("null") && getResponseValues.getFax().length() > 0) {

                        utils.blockWatcher1(BCFaxEditText, getResponseValues.getFax(), FaxTextWatcer);

                    }


                    if (getResponseValues.getSAName() != null && !getResponseValues.getSAName().equalsIgnoreCase("null") && getResponseValues.getSAName().length() > 0) {

                        BCNameEditText.setText(getResponseValues.getSAName());

                    }

                    if (getResponseValues.getSATitle() != null && !getResponseValues.getSATitle().equalsIgnoreCase("null") && getResponseValues.getSATitle().length() > 0) {

                        BCTitleEditText.setText(getResponseValues.getSATitle());

                    }

                    if (getResponseValues.getSADayTimePhone() != null && !getResponseValues.getSADayTimePhone().equalsIgnoreCase("null") && getResponseValues.getSADayTimePhone().length() > 0) {

                        BCDayPhoneEditText.setText(getResponseValues.getSADayTimePhone());

                    }

                    BCIsSameAddress.setChecked(getResponseValues.isBCOIsSameAddress());


                }
            } else if (getResponseValues.getOS().equalsIgnoreCase("Failure")) {

                if (getResponseValues.getEM() != null && !getResponseValues.getEM().equalsIgnoreCase("null")) {

                    if (getResponseValues.getEM().equalsIgnoreCase("Access Token is invalid")) {

                        utils.errorMessage(mContext, "Your session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, getResponseValues.getEM());

                    }
                }
            }

            getAddressValue();
            if (Mode != null && Mode.equalsIgnoreCase("View"))
                disableLayout();
        }
    };

    private boolean isBookValidation() {

        boolean valid = true;

        saveBookModel = new BookIncareOfModel();

        saveBookModel.setAT(AppConfigManager.getAccessToken(mContext));

        saveBookModel.setDId(AppConfigManager.getDID(mContext));

        saveBookModel.setUId(AppConfigManager.getLoggedUid(mContext));

        saveBookModel.setRID(AppConfigManager.getReturnRID(mContext));

        saveBookModel.setBookCareOfDetailsId(getResponseValues.getBookCareOfDetailsId());

        saveBookModel.setIsBCOBusiness(BCIsBusinessCheckBox.isChecked());

        saveBookModel.setBCOIsSameAddress(BCIsSameAddress.isChecked());

        saveBookModel.setBCOIsForeignAddress(BCOusideAddressCheckBox.isChecked());

        if (BCFaxEditText.getText() != null && BCFaxEditText.getText().toString().trim().length() > 0) {

            saveBookModel.setFax(BCFaxEditText.getText().toString().trim());
        }

        if (BCNameOfPersonEditText.getText() != null && BCNameOfPersonEditText.getText().toString().trim().length() > 0) {

            BCNameOfPersonEditText.setError(null);

            saveBookModel.setBookInCareOf(BCNameOfPersonEditText.getText().toString().trim());

        } else {

            valid = false;

            BCNameOfPersonEditText.setError("Name is required");


        }

        if (BCPhoneNumberEditText.getText() != null && BCPhoneNumberEditText.getText().toString().trim().length() > 0) {

            if (Utils.isValidPhone(BCPhoneNumberEditText.getText().toString().trim())) {

                BCPhoneNumberEditText.setError(null);

                saveBookModel.setPhone(BCPhoneNumberEditText.getText().toString().trim());

            } else {

                valid = false;

                BCPhoneNumberEditText.setError("Invalid Phone number");
            }

        } else {

            valid = false;

            BCPhoneNumberEditText.setError("Phone number is required");


        }

        if (BCAddress1Edittext1.getText() != null && BCAddress1Edittext1.getText().toString().trim().length() > 0) {

            BCAddress1Edittext1.setError(null);

            saveBookModel.setBCOAddress1(BCAddress1Edittext1.getText().toString().trim());

        } else {

            valid = false;

            BCAddress1Edittext1.setError("Address is required");


        }

        if (BCAddresse2ditText2.getText() != null && BCAddresse2ditText2.getText().toString().trim().length() > 0) {

            saveBookModel.setBCOAddress2(BCAddresse2ditText2.getText().toString().trim());

        }

        if (BCCityeditText.getText() != null && BCCityeditText.getText().toString().trim().length() > 0) {

            BCCityeditText.setError(null);

            saveBookModel.setBCOCity(BCCityeditText.getText().toString().trim());

        } else {

            valid = false;

            BCCityeditText.setError("City is required");


        }

        if (BCOusideAddressCheckBox.isChecked()) {

            if (BCStateorprovinceEdit.getText() != null && BCStateorprovinceEdit.getText().toString().trim().length() > 0) {

                saveBookModel.setBCOState(BCStateorprovinceEdit.getText().toString().trim());

                saveBookModel.setBCOStateId("0");

            } else {

                valid = false;

                BCStateorprovinceEdit.setError("State or Province is required");
            }

            if (BCCountrySpinner.getText() != null && BCCountrySpinner.getText().toString().trim().length() > 0 && SCountryId > 0) {

                BCCountrySpinner.setError(null);

                saveBookModel.setBCOCountry(BCCountrySpinner.getText().toString().trim());

                String id = databasehandler.getCountryIDfromName(BCCountrySpinner.getText().toString().trim());

                if (id != null)

                    saveBookModel.setBCOCountryId(id);

            } else {

                valid = false;

                BCCountrySpinner.setError("Country is required");


            }


        } else {
            if (BCStateSpinner.getText() != null && BCStateSpinner.getText().toString().trim().length() > 0 && SStateId > 0) {

                BCStateorprovinceEdit.setError(null);

                saveBookModel.setBCOState(BCStateSpinner.getText().toString().trim());

                String id = databasehandler.getStateIDFromName(BCStateSpinner.getText().toString().trim());

                if (id != null)

                    saveBookModel.setBCOStateId(id);

                else
                    saveBookModel.setBCOStateId("0");

            } else {

                valid = false;

                BCStateSpinner.setError("State is required");
            }


        }


        if (BCZipeditText.getText() != null && BCZipeditText.getText().toString().trim().length() > 0) {

            if (!BCOusideAddressCheckBox.isChecked() && !Utils.isValidZipCode(BCZipeditText.getText().toString().trim())) {

                valid = false;

                BCZipeditText.setError("Invalid Zip Code");


            } else {

                BCZipeditText.setError(null);

                saveBookModel.setBCOZip(BCZipeditText.getText().toString().trim());

            }

        } else {

            valid = false;

            BCZipeditText.setError("ZIP Code is required");


        }


        if (BCNameEditText.getText() != null && BCNameEditText.getText().toString().trim().length() > 0) {

            BCNameEditText.setError(null);

            saveBookModel.setSAName(BCNameEditText.getText().toString().trim());

        } else {

            valid = false;

            BCNameEditText.setError("Signing Authority Name is required");


        }

        if (BCTitleEditText.getText() != null && BCTitleEditText.getText().toString().trim().length() > 0) {

            BCTitleEditText.setError(null);

            saveBookModel.setSATitle(BCTitleEditText.getText().toString().trim());

        } else {

            valid = false;

            BCTitleEditText.setError("Signing Authority Title is required");


        }

        if (BCDayPhoneEditText.getText() != null && BCDayPhoneEditText.getText().toString().trim().length() > 0 && Utils.isValidPhone(BCDayPhoneEditText.getText().toString().trim())) {

            BCDayPhoneEditText.setError(null);

            saveBookModel.setSADayTimePhone(BCDayPhoneEditText.getText().toString().trim());

        } else {

            valid = false;

            BCDayPhoneEditText.setError("Signing Authority Phone is required");


        }


        return valid;
    }

    private void saveBookDetails() {

        if (getBooksInCareOfURL != null)

            getBooksInCareOfURL.cancel(true);

        saveBookInCareOfDetails = new SaveBookInCareOfDetails(saveBookModel.saveBooksInCareOfModel(mContext), mContext);

        saveBookInCareOfDetails.setOnResultListener(saveBookInCareAsync);

        saveBookInCareOfDetails.execute();


    }

    SaveBookInCareOfDetails.OnAsyncResultSaveBookIncareOf saveBookInCareAsync = new SaveBookInCareOfDetails.OnAsyncResultSaveBookIncareOf() {


        public void onResultSuccess(BookIncareOfModel message) {

            saveResponseValues = message;

            handler.post(saveBooksRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };


    Runnable saveBooksRunnable = new Runnable() {
        @Override
        public void run() {

            if (saveResponseValues != null) {

                if (saveResponseValues.getOS().equalsIgnoreCase("Success")) {

                    utils.successMessage(mContext, "Your Books are in the care of is saved successfully!!!");

                    SummaryPageFragment();

                } else if (saveResponseValues.getOS().equalsIgnoreCase("Failure")) {

                    if (saveResponseValues.getEM() != null && !saveResponseValues.getEM().equalsIgnoreCase("null")) {

                        if (saveResponseValues.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your session is Expired");

                            Logout.logout(mContext);
                        } else {

                            utils.errorMessage(mContext, saveResponseValues.getEM());

                        }
                    }
                }
            }

        }
    };


    private void addLeftFragment() {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, 1);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    private void SummaryPageFragment() {

        //// TODO: 11/23/2015

        Fragment newFragment = new SummaryFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.rightFragment, newFragment);

        transaction.addToBackStack(FragmentNameConfig.SUMMARY_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    private void setTypeFont() {

        Overridefonts.overrideFonts(mContext, WholeLayout);

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());

        BCnextText.setTypeface(typeFaceClass.NotoSans_Regular());
        BCCancelbuttonText.setTypeface(typeFaceClass.NotoSans_Regular());
    }

    private void disableLayout() {

        DisableEdittext.disableAll(mContext, WholeLayout);

        BCStateSpinner.setAdapter(null);
        BCStateSpinner.setEnabled(false);
        BCStateSpinner.setClickable(false);
        BCStateSpinner.setFocusable(false);


        BCCountrySpinner.setAdapter(null);
        BCCountrySpinner.setEnabled(false);
        BCCountrySpinner.setClickable(false);
        BCCountrySpinner.setFocusable(false);

    }

    AddBussinessInputModel businessLookupModel;

    AddBussinessInputModel addressValues;

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

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    TextWatcher phoneTextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
            utils.MaskingPhone(mContext, BCPhoneNumberEditText);
        }
    };

    TextWatcher ZipTextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!BCOusideAddressCheckBox.isChecked()) {
                utils.maskingzipcode(mContext, BCZipeditText);
            }

        }
    };

    TextWatcher FaxTextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

            utils.MaskingPhone(mContext, BCFaxEditText);


        }
    };

    TextWatcher Address1TextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };

    TextWatcher Address2TextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };

    TextWatcher cityTextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };


    TextWatcher stateOrProvinceTextWatcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BCIsSameAddress.setChecked(false);
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };


    CompoundButton.OnCheckedChangeListener adderessChecked = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            BCIsSameAddress.setChecked(false);

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

            } else {

                InputFilter[] maxlengthzip = new InputFilter[1];

                maxlengthzip[0] = new InputFilter.LengthFilter(10);

                BCZipeditText.setHint("ZIP Code");

                BCZipeditText.setFloatingLabelText("ZIP Code");

                BCZipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                BCZipeditText.setFilters(maxlengthzip);

                BCStateSpinner.setVisibility(View.VISIBLE);
                BCStateorprovinceEdit.setVisibility(View.GONE);
                BCCountryLayout.setVisibility(View.GONE);

            }

        }
    };


}
