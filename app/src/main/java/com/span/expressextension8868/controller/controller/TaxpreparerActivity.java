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
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.span.expressextension8868.webservice.webservices.TaxCPAURL;
import com.span.expressextension8868.webservice.webservices.TaxCPAURL.TaxCPAInterface;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;


public class TaxpreparerActivity extends Activity implements OnClickListener {

    Context mContext = TaxpreparerActivity.this;

    EditText FirmNameEditText, EINEditText, FirstNameEditText, LastNameEditText, PtinEditText, AddressEditText;

    EditText Addr2EditText, CityEditText, Zipcodeedittext, EmailAddressEditText, stateorprovince;

    EditText ZipCodeEditTextCopy, EmailAddressEditTextCopy;

    FrameLayout SubmitButton;

    Spinner state, Country;

    TextView FirmNameText, EINText, FirstNameText, LastNameText, PTINText, firmdetailstext, preparertext, addressdetails;

    TextView AddressText1, AddressText2, CityText, StateText, ZipCodeText, EmailAddressBusinessText, CountryText;

    TextView ZipCodeTextCopy, EmailAddressBusinessTextCopy, OwnWorkRadioTextview, selfEmployedRadioTextView;

    TextView loginname, logout, dashboardlink, support, emailmenu;

    LinearLayout FirmDetails, rootlayout, OwnWorkRadioLinear, selfEmployedRadioLinear;

    // Relative Layout
    RelativeLayout menulayout;

    Vector<String> timezoneidvector = new Vector<String>();

    Vector<String> address_statevectorid = new Vector<String>();

    String _address_stateId = "0", countryid = "0";

    TaxCPAModel taxCPAmodel = new TaxCPAModel();

    LinearLayout WholeLayout;

    ImageView allSide;

    boolean flag = true;

    int statepos = 0, countrypos = 0;

    Intent i;

    CheckBox isaddressoutside;

    DatabaseHandler databasehandler;

    PopupWindow popupWindow;

    View popupView;

    NetworkChangeReceiver receiver;

    boolean selfEmployedRadioLinearFlag = false;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_activity_cpa_profile_activity);

        initialization();

        Overridefonts.overrideFonts(mContext, rootlayout);

        setonclickListener();

        setSpinnerInputs();

        i = getIntent();

        NewUserCongratulation();

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

        OwnWorkRadioLinear.performClick();

        receiver = new NetworkChangeReceiver();

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //SideMenuPopUp();

        try {
            Slidingdrawerfunc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

    }


    private void Slidingdrawerfunc() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    private void setSpinnerInputs() {
        try {
            databasehandler = new DatabaseHandler(mContext);

            setSpinnerAdapter(databasehandler.getCountrynameList(), Country);

            setSpinnerAdapter(databasehandler.getStatenameList(), state);
        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }
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

            WholeLayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return false;
                }
            });


            SubmitButton.setOnClickListener(this);


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
                        /*startActivity(new Intent(ManageBusinessActivity.this, DashboardActivity.class));
						finish();*/
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }

                }
            });

            Zipcodeedittext.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!isaddressoutside.isChecked()) {
                        maskingzipcode(Zipcodeedittext);
                    }


                }
            });

            ZipCodeEditTextCopy.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!isaddressoutside.isChecked()) {
                        maskingzipcode(ZipCodeEditTextCopy);
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

                    try {
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
                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }


                }
            });

            OwnWorkRadioLinear.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
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
                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }


                }
            });

            isaddressoutside.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    try {
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
                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }


                }
            });


            rootlayout.setOnTouchListener(new OnTouchListener() {

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
                    try {
                        statepos = position;

                        _address_stateId = databasehandler.getStateIDList().get(position);
                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Country.setOnItemSelectedListener(new OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        countrypos = position;

                        countryid = databasehandler.getCountryIDList().get(position);
                    } catch (Exception e) {

                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub

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

            PtinEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    try {
                        if (!hasFocus) {

                            Log.e("length", String.valueOf(PtinEditText.getText().toString().length()));

                            if (PtinEditText.getText().toString().length() != 9) {

                                PtinEditText.setText("");

                            } else if (PtinEditText.getText().toString().length() == 9) {

                                if (PtinEditText.getText().toString().indexOf("P") != -1) {

                                    Log.e("present", "ss");
                                } else {

                                    Log.e("present", "NO");

                                    PtinEditText.setText("");
                                }

                            }

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

					/*try
					{
						String Phone = EINEditText.getText().toString();

						if (s.length() == 2 && len < s.length())
						{

							EINEditText.setText(String.format("%s-", Phone.substring(0, 2)));

							EINEditText.setSelection(EINEditText.getText().length());

						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
						new SendException(mContext, e);
					}*/

                    try {
                        masking(EINEditText);
                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });

            EINEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (!hasFocus) {

                        if (EINEditText.getText().toString().length() != 10) {

                            EINEditText.setText("");

                        }
                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

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

            SubmitButton = (FrameLayout) findViewById(R.id.SubmitButton);

            FirmDetails = (LinearLayout) findViewById(R.id.FirmDetails);

            rootlayout = (LinearLayout) findViewById(R.id.rootlayout);

            EmailAddressEditText.setText(AppConfigManager.getUserName(mContext));

            EmailAddressEditTextCopy.setText(AppConfigManager.getUserName(mContext));

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

    }

    private void NewUserCongratulation() {

        startActivity(new Intent(TaxpreparerActivity.this, NewuserCongratulations.class));

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
            e.printStackTrace();

            new SendException(mContext, e);
        }
    }

    public void setSpinnerAdapter(Vector<String> list, Spinner spinner) {

        try {
            ArrayAdapter<String> setFormOrgAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnerdataxml, R.id.spinnertext, list);

            spinner.setAdapter(setFormOrgAdapter);
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
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

    public void masking(EditText ed) {

        try {
            String Phone = ed.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if ((Phone.length() == 1)) {
                flag = true;

            } else if ((Phone.length() >= 3) && (WithOutSpecialChar.length() >= 3) && (Phone.length() == WithOutSpecialChar.length())) {
                ed.setText(String.format(WithOutSpecialChar.substring(0, 2) + "-" + WithOutSpecialChar.substring(2, WithOutSpecialChar.length())));
                ed.setSelection(ed.getText().length());
            } else if ((Phone.length() == 3)) {
                flag = false;
            } else if ((Phone.length() == 2) && flag == true) {
                ed.setText(String.format("%s-", WithOutSpecialChar.substring(0, 2)));
                ed.setSelection(ed.getText().length());
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }

    }

    public int SetInputsforSave() {
        int flag = 1;

        try {

            taxCPAmodel.setAT(AppConfigManager.getAccessToken(mContext));

            taxCPAmodel.setDID(AppConfigManager.getDID(mContext));

            taxCPAmodel.setUID(AppConfigManager.getLoggedUid(mContext));

            taxCPAmodel.setTPREID("0");

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
                if (statepos == 0) {
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

        switch (v.getId()) {


            case R.id.SubmitButton:

                try {
                    int x = SetInputsforSave();
                    if (x == 1) {
                        SaveCPA();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }

                break;


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
                startActivity(new Intent(TaxpreparerActivity.this, LoginActivity.class));

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

    private void SaveCPA() {

        try {
            TaxCPAURL taxCPAURL = new TaxCPAURL(taxCPAmodel.getJSONObj(), mContext);

            TaxCPAInterface onasync12 = new TaxCPAInterface() {

                @Override
                public void onResultSuccess(final Vector<TaxCPAModel> taxCPAVector) {

                    try {
                        if (taxCPAVector.get(0).getOS().equalsIgnoreCase("Success")) {

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {/*

									Intent i = new Intent(TaxpreparerActivity.this, AddPersonalInfoActivity.class).putExtra("ID", "2").putExtra("From", "sign");

									startActivity(i);

									finish();

								*/
                                    startActivity(new Intent(TaxpreparerActivity.this, DashboardActivity.class));

                                    finish();

                                }
                            });
                        } else if (taxCPAVector.get(0).getOS() != null && taxCPAVector.get(0).getOS().equalsIgnoreCase("Failure") && taxCPAVector.get(0).getEM() != null && !taxCPAVector.get(0).getEM().equalsIgnoreCase("null") && taxCPAVector.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {

                            Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();

                            Logout.logout(mContext);
                        } else if (taxCPAVector.get(0).getOS().equalsIgnoreCase("Failure") && !taxCPAVector.get(0).getEM().equalsIgnoreCase("Access Token is invalid")) {
                            Toast.makeText(getBaseContext(), "Some Error Occured, Please Try again", Toast.LENGTH_LONG).show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            };
            taxCPAURL.setOnResultListener(onasync12);

            taxCPAURL.execute();
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

    }

}
