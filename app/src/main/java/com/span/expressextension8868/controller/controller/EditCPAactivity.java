package com.span.expressextension8868.controller.controller;

import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.span.expressextension8868.model.core.TaxCPAModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.SupportDialog;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.webservice.webservices.TaxCPAEditURL;
import com.span.expressextension8868.webservice.webservices.TaxCPAEditURL.CPAEditInterface;
import com.span.expressextension8868.webservice.webservices.TaxCPAURL;
import com.span.expressextension8868.webservice.webservices.TaxCPAURL.TaxCPAInterface;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;


public class EditCPAactivity extends Activity implements OnClickListener {

    Context mContext = EditCPAactivity.this;

    NetworkChangeReceiver receiver;

    EditText FirmNameEditText, EINEditText, FirstNameEditText, LastNameEditText, PtinEditText, AddressEditText, stateorprovince;

    EditText Addr2EditText, CityEditText, Zipcodeedittext, PhoneNumberEditText, FaxNumberEditText, EmailAddressEditText;

    EditText ZipCodeEditTextCopy, EmailAddressEditTextCopy;

    Button SubmitButton, CancelButton;

    Spinner state, Country;

    LinearLayout segmentedlayout;

    TextView EmailAddressText, CountryText, ZipCodeTextCopy, EmailAddressBusinessTextCopy, selfEmployedRadioTextView;

    TextView FirmNameText, EINText, FirstNameText, LastNameText, PTINText, firmdetailstext, preparertext, addressdetails;

    TextView AddressText1, AddressText2, CityText, StateText, ZipCodeText, PhoneNumberText, faxtext, EmailAddressBusinessText, OwnWorkRadioTextview;

    TextView loginname, logout, dashboardlink, support, emailmenu;

    LinearLayout FirmDetails, rootlayout, selfEmployedRadioLinear, OwnWorkRadioLinear, hidelayout, firmlayout, preparerlayout, addresslayout;

    // Relative Layout
    RelativeLayout menulayout;

    Vector<String> timezoneidvector = new Vector<String>();

    Vector<String> address_statevectorid = new Vector<String>();

    String _address_stateId, countryid;

    int statepos = 0, countrypos = 0;

    TaxCPAModel taxCPAmodel = new TaxCPAModel();

    String taxprid;

    CheckBox isaddressoutside;

    boolean flag = true;

    ImageView allSide, logolink;

    LinearLayout WholeLayout;

    DatabaseHandler databasehandler;

    boolean selfEmployedRadioLinearFlag = false;

    PopupWindow popupWindow;

    View popupView;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_new_activity_cpa_profile_activity);

        initialization();

        Overridefonts.overrideFonts(mContext, rootlayout);

        setonclickListener();

        setSpinnerInputs();

        GetinputsfromLookup();

        receiver = new NetworkChangeReceiver();

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

        //SideMenuPopUp();

        try {
            Slidingdrawerfunc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Slidingdrawerfunc() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Get an Analytics tracker to report app starts & uncaught exceptions
        // etc.
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }


    private void GetinputsfromLookup() {

        try {
            TaxCPAModel taxCPAModel = new TaxCPAModel();

            taxCPAModel.setAT(AppConfigManager.getAccessToken(mContext));

            taxCPAModel.setUID(AppConfigManager.getLoggedUid(mContext));

            taxCPAModel.setDID(AppConfigManager.getDID(mContext));

            TaxCPAEditURL taxcpaediturl = new TaxCPAEditURL(taxCPAModel.getEditJSONObj(), mContext);

            CPAEditInterface onasync = new CPAEditInterface() {

                @Override
                public void onResultSuccess(final Vector<TaxCPAModel> message) {
                    if (!message.get(0).getOS().equalsIgnoreCase("null") && message.get(0).getOS().equalsIgnoreCase("Success")) {
                        try {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    try {
                                        if (message.get(0).getISSEM().equalsIgnoreCase("true")) {
                                            FirmNameEditText.setVisibility(View.GONE);

                                            FirmNameText.setVisibility(View.GONE);

                                            EINEditText.setVisibility(View.GONE);

                                            EINText.setVisibility(View.GONE);

                                            selfEmployedRadioLinear.performClick();

                                        } else if (message.get(0).getISSEM().equalsIgnoreCase("false")) {
                                            FirmNameEditText.setVisibility(View.VISIBLE);

                                            FirmNameText.setVisibility(View.VISIBLE);

                                            EINEditText.setVisibility(View.VISIBLE);

                                            EINText.setVisibility(View.VISIBLE);

                                            OwnWorkRadioLinear.performClick();

                                            FirmNameEditText.setText(message.get(0).getFIRN());

                                            EINEditText.setText(message.get(0).getEIN());
                                        }

                                        FirstNameEditText.setText(message.get(0).getFN());

                                        LastNameEditText.setText(message.get(0).getLN());

                                        PtinEditText.setText(message.get(0).getPTIN());

                                        AddressEditText.setText(message.get(0).getADD1());

                                        if (!message.get(0).getADD2().equalsIgnoreCase("null") && message.get(0).getADD2() != null) {
                                            Addr2EditText.setText(message.get(0).getADD2());
                                        } else {
                                            Addr2EditText.setText("");
                                        }

                                        CityEditText.setText(message.get(0).getCTY());

                                        state.setSelection(Integer.parseInt(databasehandler.getStatePositionFromSID(message.get(0).getSID())));

                                        Country.setSelection(Integer.parseInt(databasehandler.getCountryPosFromCID(message.get(0).getCID())));

                                        if (message.get(0).getISFORADD().equalsIgnoreCase("true")) {
                                            isaddressoutside.setChecked(true);

                                            Zipcodeedittext.setText(message.get(0).getZIP());

                                            ZipCodeEditTextCopy.setText(message.get(0).getZIP());

                                            EmailAddressEditTextCopy.setText(message.get(0).getEA());

                                            EmailAddressEditText.setText(message.get(0).getEA());

                                            stateorprovince.setText(message.get(0).getSN());
                                        } else {
                                            isaddressoutside.setChecked(false);

                                            Zipcodeedittext.setText(message.get(0).getZIP());

                                            ZipCodeEditTextCopy.setText(message.get(0).getZIP());

                                            EmailAddressEditTextCopy.setText(message.get(0).getEA());

                                            EmailAddressEditText.setText(message.get(0).getEA());
                                        }

                                        taxprid = message.get(0).getTPREID();
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                        new SendException(mContext, e);
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();

                            new SendException(mContext, e);
                        }
                    } else if (message.get(0).getOS().equalsIgnoreCase("Failure") && !message.get(0).getOS().equalsIgnoreCase("null") && message.get(0).getEM() != null && !message.get(0).getEM().equalsIgnoreCase("null") && message.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {
                        Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();

                        Logout.logout(mContext);
                    }
                }

                @Override
                public void onResultFail(int resultCode, String errorMessage) {

                }

            };

            taxcpaediturl.setOnResultListener(onasync);

            taxcpaediturl.execute();
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    private void setSpinnerInputs() {
        databasehandler = new DatabaseHandler(mContext);

        setSpinnerAdapter(databasehandler.getCountrynameList(), Country);

        setSpinnerAdapter(databasehandler.getStatenameList(), state);

    }

    public void setSpinnerAdapter(ArrayList<String> list, Spinner spinner) {

        try {
            ArrayAdapter<String> Spinneradapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnerdataxml, R.id.spinnertext, list);

            spinner.setAdapter(Spinneradapter);
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    private void setonclickListener() {

        try {
            SubmitButton.setOnClickListener(this);

            CancelButton.setOnClickListener(this);

            support.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mDrawerLayout.closeDrawer(menulayout);

                    SupportDialog support = new SupportDialog(mContext);

                    support.showSupport();
                }
            });

            dashboardlink.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {

                        startActivity(new Intent(EditCPAactivity.this, DashboardActivity.class));

                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }

                }
            });


            ZipCodeEditTextCopy.addTextChangedListener(new TextWatcher() {

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
                    if (!isaddressoutside.isChecked()) {
                        maskingzipcode(ZipCodeEditTextCopy);
                    }

                }
            });

            Zipcodeedittext.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (!isaddressoutside.isChecked()) {
                        maskingzipcode(Zipcodeedittext);
                    }

                }
            });

            logout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        mDrawerLayout.closeDrawer(menulayout);

                        new AlertDialog.Builder(mContext).setTitle("Log off?").setMessage("Are you sure you want to log off?").setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {

                                    new ProgressTask().execute();

                                } catch (Exception e) {
                                    e.printStackTrace();

                                    new SendException(mContext, e);
                                }

                            }
                        }).setIcon(R.drawable.logoff).show();
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                }
            });

            allSide.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        mDrawerLayout.openDrawer(menulayout);
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                }
            });


            selfEmployedRadioLinear.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    selfEmployedRadioLinear.setBackgroundResource(R.drawable.segmentedblueself);

                    OwnWorkRadioLinear.setBackgroundResource(R.drawable.segmentedwhiteself);

                    OwnWorkRadioTextview.setTextColor(Color.parseColor("#808080"));

                    selfEmployedRadioTextView.setTextColor(Color.parseColor("#FFFFFF"));

                    FirmNameText.setVisibility(View.GONE);

                    FirmNameEditText.setVisibility(View.GONE);

                    EINText.setVisibility(View.GONE);

                    EINEditText.setVisibility(View.GONE);

                    selfEmployedRadioLinearFlag = true;

                    FirmDetails.setVisibility(View.GONE);


                }
            });

            OwnWorkRadioLinear.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    OwnWorkRadioLinear.setBackgroundResource(R.drawable.segmentedblue);

                    selfEmployedRadioLinear.setBackgroundResource(R.drawable.segmentedwhite);

                    OwnWorkRadioTextview.setTextColor(Color.parseColor("#FFFFFF"));

                    selfEmployedRadioTextView.setTextColor(Color.parseColor("#808080"));

                    FirmNameText.setVisibility(View.VISIBLE);

                    FirmNameEditText.setVisibility(View.VISIBLE);

                    EINText.setVisibility(View.VISIBLE);

                    EINEditText.setVisibility(View.VISIBLE);

                    selfEmployedRadioLinearFlag = false;

                    FirmDetails.setVisibility(View.VISIBLE);


                }
            });

            isaddressoutside.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        Country.setVisibility(View.VISIBLE);

                        CountryText.setVisibility(View.VISIBLE);

                        state.setVisibility(View.GONE);

                        stateorprovince.setVisibility(View.VISIBLE);

                        // Visible the original Email and Zip fields

                        Zipcodeedittext.setVisibility(View.VISIBLE);

                        ZipCodeText.setVisibility(View.VISIBLE);

                        EmailAddressBusinessText.setVisibility(View.VISIBLE);

                        EmailAddressEditText.setVisibility(View.VISIBLE);

                        // Gone the copied Email and Zip fields

                        EmailAddressBusinessTextCopy.setVisibility(View.GONE);

                        EmailAddressEditTextCopy.setVisibility(View.GONE);

                        ZipCodeTextCopy.setVisibility(View.GONE);

                        ZipCodeEditTextCopy.setVisibility(View.GONE);

                        StateText.setText("*State or Province");

                        setfirstletterred(StateText.getText().toString(), StateText);

                    } else {
                        Country.setVisibility(View.GONE);

                        CountryText.setVisibility(View.GONE);

                        state.setVisibility(View.VISIBLE);

                        stateorprovince.setVisibility(View.GONE);

                        // 	Gone the original Email and Zip fields

                        Zipcodeedittext.setVisibility(View.GONE);

                        ZipCodeText.setVisibility(View.GONE);

                        EmailAddressBusinessText.setVisibility(View.GONE);

                        EmailAddressEditText.setVisibility(View.GONE);

                        // Visible the copied Email and Zip fields

                        EmailAddressBusinessTextCopy.setVisibility(View.VISIBLE);

                        EmailAddressEditTextCopy.setVisibility(View.VISIBLE);

                        ZipCodeTextCopy.setVisibility(View.VISIBLE);

                        ZipCodeEditTextCopy.setVisibility(View.VISIBLE);

                        StateText.setText("*State");

                        setfirstletterred(StateText.getText().toString(), StateText);
                    }


                }
            });

            firmlayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return false;
                }
            });

            preparerlayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return false;
                }
            });

            addresslayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return false;
                }
            });


            hidelayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return false;
                }
            });


            WholeLayout.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    return false;
                }
            });

            state.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    statepos = position;

                    _address_stateId = databasehandler.getStateIDList().get(position);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Country.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    countrypos = position;

                    countryid = databasehandler.getCountryIDFromPosition(String.valueOf(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });


            PtinEditText.addTextChangedListener(new TextWatcher() {
                int len = 0;

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    len = s.length();
                }

                @Override
                public void afterTextChanged(Editable s) {

                    try {
                        String PtEin = PtinEditText.getText().toString();

                        if (s.length() == 1 && len < s.length()) {

                            PtinEditText.setText(String.format("P%s", PtEin.substring(0, 1)));

                            PtinEditText.setSelection(PtinEditText.getText().length());

                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }

                }
            });

            EINEditText.addTextChangedListener(new TextWatcher() {
                int len = 0;

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    len = s.length();
                }

                @Override
                public void afterTextChanged(Editable s) {

                    try {
                        String Phone = EINEditText.getText().toString();

                        if (s.length() == 2 && len < s.length()) {

                            EINEditText.setText(String.format("%s-", Phone.substring(0, 2)));

                            EINEditText.setSelection(EINEditText.getText().length());

                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                }
            });

        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    private void initialization() {

        try {
            TypeFaceClass typeface = new TypeFaceClass(mContext);

            EmailAddressBusinessText = (TextView) findViewById(R.id.EmailAddressBusinessText);

            FirmNameText = (TextView) findViewById(R.id.FirmNameText);

            EINText = (TextView) findViewById(R.id.EINText);

            FirstNameText = (TextView) findViewById(R.id.FirstNameText);

            LastNameText = (TextView) findViewById(R.id.LastNameText);

            PTINText = (TextView) findViewById(R.id.PTINText);

            segmentedlayout = (LinearLayout) findViewById(R.id.segmentedlayout);

            AddressText1 = (TextView) findViewById(R.id.AddressText);

            AddressText2 = (TextView) findViewById(R.id.AddressText2);

            CityText = (TextView) findViewById(R.id.CityText);

            StateText = (TextView) findViewById(R.id.StateText);

            ZipCodeText = (TextView) findViewById(R.id.ZipCodeText);

            allSide = (ImageView) findViewById(R.id.allSide);

            WholeLayout = (LinearLayout) findViewById(R.id.wholelayout);

            Country = (Spinner) findViewById(R.id.Country);

            stateorprovince = (EditText) findViewById(R.id.stateorprovince);

            isaddressoutside = (CheckBox) findViewById(R.id.isaddressoutside);

            CountryText = (TextView) findViewById(R.id.CountryText);

            ZipCodeTextCopy = (TextView) findViewById(R.id.ZipCodeTextCopy);

            EmailAddressBusinessTextCopy = (TextView) findViewById(R.id.EmailAddressBusinessTextCopy);

            ZipCodeEditTextCopy = (EditText) findViewById(R.id.ZipCodeEditTextCopy);

            EmailAddressEditTextCopy = (EditText) findViewById(R.id.EmailAddressEditTextCopy);

            selfEmployedRadioLinear = (LinearLayout) findViewById(R.id.JointRadio);

            OwnWorkRadioLinear = (LinearLayout) findViewById(R.id.IndividualRadio);

            selfEmployedRadioTextView = (TextView) findViewById(R.id.jointTextView);

            OwnWorkRadioTextview = (TextView) findViewById(R.id.individualTextview);

            loginname = (TextView) findViewById(R.id.loginname);

            loginname.setTypeface(typeface.RobotoCondensed());

            support = (TextView) findViewById(R.id.Support);

            support.setTypeface(typeface.RobotoCondensed());

            dashboardlink = (TextView) findViewById(R.id.Dashboardlink);

            dashboardlink.setTypeface(typeface.RobotoCondensed());

            logout = (TextView) findViewById(R.id.Logout);

            logout.setTypeface(typeface.RobotoCondensed());

            menulayout = (RelativeLayout) findViewById(R.id.menulayout);

            emailmenu = (TextView) findViewById(R.id.emailmenu);

            emailmenu.setTypeface(typeface.RobotoCondensed());

            menulayout.setClickable(true);

            emailmenu.setText(AppConfigManager.getUserName(mContext));

            loginname.setText("Hi " + AppConfigManager.getContactname(mContext) + " !");

            setfirstletterred(getResources().getText(R.string._firm_name_).toString(), FirmNameText);
            setfirstletterred(getResources().getText(R.string._ein_).toString(), EINText);
            setfirstletterred(getResources().getText(R.string._first_name_).toString(), FirstNameText);
            setfirstletterred(getResources().getText(R.string._last_name_).toString(), LastNameText);
            setfirstletterred(getResources().getText(R.string._ptin_).toString(), PTINText);
            setfirstletterred(getResources().getText(R.string._address_line_1_).toString(), AddressText1);
            setfirstletterred(getResources().getText(R.string._city_).toString(), CityText);
            setfirstletterred(getResources().getText(R.string._state_).toString(), StateText);
            setfirstletterred(getResources().getText(R.string._zip_code_).toString(), ZipCodeText);
            setfirstletterred(getResources().getText(R.string._email_address_).toString(), EmailAddressBusinessText);
            setfirstletterred(ZipCodeTextCopy.getText().toString(), ZipCodeTextCopy);
            setfirstletterred(EmailAddressBusinessTextCopy.getText().toString(), EmailAddressBusinessTextCopy);
            setfirstletterred(CountryText.getText().toString(), CountryText);

            FirmNameEditText = (EditText) findViewById(R.id.FirmNameEditText);

            EINEditText = (EditText) findViewById(R.id.EINEditText);

            FirstNameEditText = (EditText) findViewById(R.id.FirstNameEditText);

            LastNameEditText = (EditText) findViewById(R.id.LastNameEditText);

            PtinEditText = (EditText) findViewById(R.id.PtinEditText);

            AddressEditText = (EditText) findViewById(R.id.AddressEditText);

            Addr2EditText = (EditText) findViewById(R.id.Addr2EditText);

            CityEditText = (EditText) findViewById(R.id.CityEditText);

            Zipcodeedittext = (EditText) findViewById(R.id.ZipCodeEditText);

            EmailAddressEditText = (EditText) findViewById(R.id.EmailAddressEditText);

            state = (Spinner) findViewById(R.id.State);

            SubmitButton = (Button) findViewById(R.id.SubmitButton);

            CancelButton = (Button) findViewById(R.id.CancelButton);

            FirmDetails = (LinearLayout) findViewById(R.id.FirmDetails);

            rootlayout = (LinearLayout) findViewById(R.id.rootlayout);

            hidelayout = (LinearLayout) findViewById(R.id.hidelayout);

            firmlayout = (LinearLayout) findViewById(R.id.firmlayout);

            preparerlayout = (LinearLayout) findViewById(R.id.preparerlayout);

            addresslayout = (LinearLayout) findViewById(R.id.addresslayout);

        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            // android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidZipCode(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "^\\d{5}(-\\d{4})?$";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidPhone(String target) {
        Pattern phoneNumberPattern = Pattern.compile("\\(" + "[0-9]{3}" + "\\)" + " " + "[0-9]{3}" + "\\-" + "[0-9]{4}");

        Matcher phoneNumberMatches = phoneNumberPattern.matcher(target);

        boolean CheckPhoneNumberMatch = phoneNumberMatches.matches();

        return CheckPhoneNumberMatch;
    }

    private void setfirstletterred(String text, TextView tv) {
        try {
            SpannableString spannable = new SpannableString(text);

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#D74937")), 0, 1, 0);

            spannable.setSpan(new SuperscriptSpan(), 0, 1, 0);

            tv.setText(spannable);
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public void setSpinnerAdapter(Vector<String> list, Spinner spinner) {

        try {
            ArrayAdapter<String> setFormOrgAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnerdataxml, R.id.spinnertext, list);

            spinner.setAdapter(setFormOrgAdapter);
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    public int SetInputsforSave() {
        int flag = 1;
        try {

            taxCPAmodel.setAT(AppConfigManager.getAccessToken(mContext));

            taxCPAmodel.setDID(AppConfigManager.getDID(mContext));

            taxCPAmodel.setUID(AppConfigManager.getLoggedUid(mContext));

            taxCPAmodel.setTPREID(taxprid);

            if (selfEmployedRadioLinearFlag == false) {
                taxCPAmodel.setISSEM("false");

                if (FirmDetails.isShown() == true) {
                    if (FirmNameEditText.isShown() == true) {
                        if (FirmNameEditText.getText().toString().equalsIgnoreCase("") == true) {
                            flag = 0;
                            FirmNameEditText.setError("Enter the Firm Name");
                        } else {
                            taxCPAmodel.setFIRN(FirmNameEditText.getText().toString());
                        }

                    } else {
                        taxCPAmodel.setFIRN("");
                    }

                    if (EINEditText.isShown() == true) {
                        if (EINEditText.getText().toString().equalsIgnoreCase("") == true) {
                            flag = 0;
                            EINEditText.setError("Enter the EIN");
                        } else if (EINEditText.getText().toString().length() < 10) {
                            flag = 0;
                            EINEditText.setError("EIN should be 9 digits");
                        } else {
                            taxCPAmodel.setEIN(EINEditText.getText().toString());
                        }
                    } else {
                        taxCPAmodel.setEIN("");
                    }
                }
            } else {
                taxCPAmodel.setISSEM("true");

                taxCPAmodel.setFIRN("");

                taxCPAmodel.setEIN("");

            }

            if (FirstNameEditText.getText().toString().equalsIgnoreCase("") == true) {
                flag = 0;
                FirstNameEditText.setError("Enter The First Name");
            } else {
                taxCPAmodel.setFN(FirstNameEditText.getText().toString());
            }

            if (LastNameEditText.getText().toString().equalsIgnoreCase("") == true) {
                flag = 0;
                LastNameEditText.setError("Enter the Last Name");

            } else {
                taxCPAmodel.setLN(LastNameEditText.getText().toString());
            }

            if (PtinEditText.getText().toString().equalsIgnoreCase("") == true) {
                flag = 0;
                PtinEditText.setError("Enter the PTIN");
            } else if (PtinEditText.getText().toString().length() < 9) {
                flag = 0;
                PtinEditText.setError("PTIN should be 8 digits");
            } else {
                taxCPAmodel.setPTIN(PtinEditText.getText().toString());
            }

            if (AddressEditText.getText().toString().equalsIgnoreCase("") == true) {
                flag = 0;
                AddressEditText.setError("Enter the Address");
            } else {
                taxCPAmodel.setADD1(AddressEditText.getText().toString());
            }

            taxCPAmodel.setADD2(Addr2EditText.getText().toString());

            if (CityEditText.getText().toString().equalsIgnoreCase("") == true) {
                flag = 0;
                CityEditText.setError("Enter the City");
            } else {
                taxCPAmodel.setCTY(CityEditText.getText().toString());
            }

            if (state.isShown()) {
                if (state.getSelectedItemPosition() == 0) {
                    flag = 0;
                    Toast.makeText(getBaseContext(), "State Required", Toast.LENGTH_SHORT).show();
                } else {
                    taxCPAmodel.setSID(_address_stateId);
                }
            } else {
                taxCPAmodel.setSID("0");
            }

            if (Country.isShown()) {
                if (countrypos == 0) {
                    flag = 0;
                    Toast.makeText(getBaseContext(), "Country Required", Toast.LENGTH_SHORT).show();
                } else {
                    taxCPAmodel.setCID(countryid);
                }
            } else {
                taxCPAmodel.setCID("0");
            }

            if (isaddressoutside.isChecked()) {
                taxCPAmodel.setISFORADD("true");
            } else {
                taxCPAmodel.setISFORADD("false");
            }

            if (ZipCodeEditTextCopy.isShown()) {
                if (ZipCodeEditTextCopy.getText().toString().equalsIgnoreCase("")) {
                    flag = 0;
                    ZipCodeEditTextCopy.setError("ZIP Code Required");
                } else if (!isaddressoutside.isChecked() && isValidZipCode(ZipCodeEditTextCopy.getText().toString()) == false) {
                    flag = 0;
                    ZipCodeEditTextCopy.setError("Enter valid Zip Code");
                } else {
                    taxCPAmodel.setZIP(ZipCodeEditTextCopy.getText().toString());
                }
            }


            if (Zipcodeedittext.isShown()) {
                if (Zipcodeedittext.getText().toString().equalsIgnoreCase("")) {
                    flag = 0;

                    Zipcodeedittext.setError("ZIP Code Required");
                } else if (!isaddressoutside.isChecked() && isValidZipCode(Zipcodeedittext.getText().toString()) == false) {
                    flag = 0;

                    Zipcodeedittext.setError("Enter valid Zip Code");
                } else {
                    taxCPAmodel.setZIP(Zipcodeedittext.getText().toString());
                }
            }


            if (EmailAddressEditTextCopy.isShown()) {
                if (EmailAddressEditTextCopy.getText().toString().equalsIgnoreCase("")) {
                    flag = 0;
                    EmailAddressEditTextCopy.setError("Email Address Required");
                } else if (!isaddressoutside.isChecked() && !isValidEmail(EmailAddressEditTextCopy.getText().toString())) {
                    flag = 0;
                    EmailAddressEditTextCopy.setError("Enter Valid Email Address");
                } else {
                    taxCPAmodel.setEA(EmailAddressEditTextCopy.getText().toString());
                }
            }

            if (EmailAddressEditText.isShown()) {
                if (EmailAddressEditText.getText().toString().equalsIgnoreCase("") == true) {
                    flag = 0;
                    EmailAddressEditText.setError("Email Address is required");
                } else if (!isaddressoutside.isChecked() && !isValidEmail(EmailAddressEditText.getText().toString())) {
                    flag = 0;
                    EmailAddressEditText.setError("Enter Valid Email Address");
                } else {
                    taxCPAmodel.setEA(EmailAddressEditText.getText().toString());
                }
            }
            if (stateorprovince.isShown()) {
                if (stateorprovince.getText().toString().equalsIgnoreCase("")) {
                    flag = 0;
                    stateorprovince.setError("State or Province Requried");
                } else {
                    taxCPAmodel.setSN(stateorprovince.getText().toString());
                }
            } else {
                taxCPAmodel.setSN("");
            }

            Log.e("FLAG IN SETINPUTS", "Flag value " + flag);

        } catch (Exception e) {
            new SendException(mContext, e);
        }
        return flag;
    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {


                case R.id.SubmitButton:

                    try {
                        int x = SetInputsforSave();

                        Log.e("x vlaue", "x value " + x);

                        if (x == 1) {
                            SaveCPA();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                    break;
                case R.id.CancelButton:

				/*try
                {
					Intent i = new Intent(EditCPAactivity.this, DashboardActivity.class);
					
					startActivity(i);

					finish();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					new SendException(mContext, e);
				}*/
                    break;
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    private class ProgressTask extends AsyncTask<String, Void, Void> {

        private ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = MyCustomProgressDialog.ctor(mContext);

            pd.show();
        }

        @Override
        protected Void doInBackground(String... args) {

            try {
                startActivity(new Intent(EditCPAactivity.this, LoginActivity.class));
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                new SendException(mContext, e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void success) {
            super.onPostExecute(success);

            pd.dismiss();
        }
    }

    public void maskingzipcode(EditText ed) {

        try {
            String Phone = ed.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if ((Phone.length() == 1)) {
                flag = true;

            } else if ((Phone.length() >= 6) && (WithOutSpecialChar.length() >= 5) && (Phone.length() == WithOutSpecialChar.length())) {
                ed.setText(String.format(WithOutSpecialChar.substring(0, 5) + "-" + WithOutSpecialChar.substring(5, WithOutSpecialChar.length())));
                ed.setSelection(ed.getText().length());
            } else if ((Phone.length() == 6)) {
                flag = false;
            } else if ((Phone.length() == 6) && flag == true) {
                ed.setText(String.format("%s-", WithOutSpecialChar.substring(0, 5)));
                ed.setSelection(ed.getText().length());
                flag = false;
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    private void SaveCPA() {

        try {
            TaxCPAURL taxCPAURL = new TaxCPAURL(taxCPAmodel.getJSONObj(), mContext);

            TaxCPAInterface onasync12 = new TaxCPAInterface() {

                @Override
                public void onResultSuccess(final Vector<TaxCPAModel> message) {

                    if (message.get(0).getOS().equalsIgnoreCase("Success")) {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                try {

                                    Intent i = new Intent(EditCPAactivity.this, DashboardActivity.class);

                                    startActivity(i);

                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();

                                    new SendException(mContext, e);
                                }

                            }
                        });

                    } else if (message.get(0).getOS() != null && message.get(0).getOS().equalsIgnoreCase("Failure") && message.get(0).getEM() != null && message.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {

                        Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();

                        Logout.logout(mContext);
                    } else if (message.get(0).getOS().equalsIgnoreCase("Failure") && !message.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {
                        Toast.makeText(getBaseContext(), "Some Error Occured, Please Try again", Toast.LENGTH_LONG).show();

                    }

                }

            };
            taxCPAURL.setOnResultListener(onasync12);
            taxCPAURL.execute();
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

}
