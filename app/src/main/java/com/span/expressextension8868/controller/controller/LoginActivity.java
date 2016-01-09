package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.android.gms.analytics.GoogleAnalytics;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.LoginModel;
import com.span.expressextension8868.model.core.LogininputModel;
import com.span.expressextension8868.model.core.SignupModel;
import com.span.expressextension8868.model.core.SignupinputModel;
import com.span.expressextension8868.utils.utility.AndroidDeviceInfo;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ConnectionDetector;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.LoginURL;
import com.span.expressextension8868.webservice.webservices.LoginURL.LoginInterface;
import com.span.expressextension8868.webservice.webservices.SignupURL;
import com.span.expressextension8868.webservice.webservices.SignupURL.SignUpInterface;

import java.util.Vector;

public class LoginActivity extends AppCompatActivity {

    Utils utils;

    // For username and password to Login
    EditText username, password;

    // Input Text Fields for SignUp process
    EditText emailaddress, passwordforsignup, contactname, phonenumber;

    // Store Received inputs from EditText username and password field
    String userdata, passdata;

    // To Login with username and password
    android.support.v7.widget.CardView submitforlogin, Signup, submit;

    // Button for Intent to SignUp Activity

    TextView createAccountTextview, SubmitTextVeiw, createsubmitTextview;

    Typeface typeface;

    // Model to set and get Login Success or not
    LoginModel loginmodel;

    TextView copyRightText;

    TextView ExistingUserText, NewUserTextView, forgetpassword, callnumber, donthaveaccounttextview;

    Context mContext = LoginActivity.this;

    AndroidDeviceInfo androidDeviceInfo;

    CheckBox rememberme, taxprofessional;

    ImageView fb, tw, gp, yt, li, call, backtologin;

    ConnectionDetector connectiondetector = new ConnectionDetector(mContext);

    LinearLayout rootlayout, signupcontent, logincontent;

    NetworkChangeReceiver receiver;

    // Model Class for SignUp process with set and get of Inputs
    SignupinputModel signupinputmodel = new SignupinputModel();

    // Model Class to set and get OS Success or Failure, i.e SignUp Success or
    // Failure
    SignupModel signupmodel;

    Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.newlogin);

        // Calling Method for Initializing EditText and Button Id's

        typeface = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");


        initialization();

        OverrideBoldTexts();

        // Calling Method for Login Button Listener

        focusListener();

        setonClickListener();

        checkRememberme();

        androidDeviceInfo = new AndroidDeviceInfo(mContext);

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);

        receiver = new NetworkChangeReceiver();

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    }

    @Override
    public void onBackPressed() {
        finish();
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

    private void OverrideBoldTexts() {

        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");

            username.setTypeface(type);
            password.setTypeface(type);


            emailaddress.setTypeface(type);
            passwordforsignup.setTypeface(type);
            contactname.setTypeface(type);
            phonenumber.setTypeface(type);

            createAccountTextview.setTypeface(type, Typeface.BOLD);
            SubmitTextVeiw.setTypeface(type, Typeface.BOLD);
            createsubmitTextview.setTypeface(type, Typeface.BOLD);

            copyRightText.setTypeface(type);

            ExistingUserText.setTypeface(type, Typeface.BOLD);
            NewUserTextView.setTypeface(type, Typeface.BOLD);

            forgetpassword.setTypeface(type);

            callnumber.setTypeface(type, Typeface.BOLD);
            donthaveaccounttextview.setTypeface(type, Typeface.BOLD);

            rememberme.setTypeface(type);
            taxprofessional.setTypeface(type);

        } catch (Exception e) {
            e.printStackTrace();
            // new SendException(mContext, e);
        }
    }

    private void checkRememberme() {

        try {
            if (AppConfigManager.getRememberme(mContext) != null && AppConfigManager.getRememberme(mContext).equalsIgnoreCase("true")) {
                username.setText(AppConfigManager.getUserName(mContext));

                password.setText(AppConfigManager.getPassWord(mContext));

                rememberme.setChecked(true);
            }
            if (AppConfigManager.getRememberme(mContext) != null && AppConfigManager.getRememberme(mContext).equalsIgnoreCase("false")) {
                username.setText("");

                password.setText("");

                rememberme.setChecked(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // new SendException(mContext, e);
        }
    }

    // Initializing the Objects to Id's
    public void initialization() {

        try {
            utils = new Utils();

            callnumber = (TextView) findViewById(R.id.callnumber);

            rootlayout = (LinearLayout) findViewById(R.id.rootlayout);

            signupcontent = (LinearLayout) findViewById(R.id.signupcontent);

            signupcontent.setVisibility(View.GONE);

            logincontent = (LinearLayout) findViewById(R.id.logincontent);

            logincontent.setVisibility(View.VISIBLE);

            ExistingUserText = (TextView) findViewById(R.id.ExistingUserText);

            NewUserTextView = (TextView) findViewById(R.id.NewUserTextView);

            username = (EditText) findViewById(R.id.username);


            donthaveaccounttextview = (TextView) findViewById(R.id.donthaveaccounttextview);

            password = (EditText) findViewById(R.id.password);

            submitforlogin = (android.support.v7.widget.CardView) findViewById(R.id.submitforlogin);

            Signup = (android.support.v7.widget.CardView) findViewById(R.id.Signup);

            createAccountTextview = (TextView) findViewById(R.id.createAccountTextview);

            SubmitTextVeiw = (TextView) findViewById(R.id.SubmitTextVeiw);

            createsubmitTextview = (TextView) findViewById(R.id.createsubmitTextview);

            forgetpassword = (TextView) findViewById(R.id.forgetpassword);

            rememberme = (CheckBox) findViewById(R.id.rememberme);

            copyRightText = (TextView) findViewById(R.id.copyRightText);

            copyRightText.setText("\u00A9 " + Utils.getCurrentyear() + " " + getResources().getText(R.string.allrights));

            fb = (ImageView) findViewById(R.id.fb);

            tw = (ImageView) findViewById(R.id.tw);

            gp = (ImageView) findViewById(R.id.gp);

            yt = (ImageView) findViewById(R.id.yt);

            li = (ImageView) findViewById(R.id.li);

            call = (ImageView) findViewById(R.id.call);

            backtologin = (ImageView) findViewById(R.id.backtologin);

            emailaddress = (EditText) findViewById(R.id.emailaddress);

            passwordforsignup = (EditText) findViewById(R.id.passwordforsignup);

            contactname = (EditText) findViewById(R.id.contactname);

            phonenumber = (EditText) findViewById(R.id.phonenumber);

            submit = (android.support.v7.widget.CardView) findViewById(R.id.submit);

            taxprofessional = (CheckBox) findViewById(R.id.taxprofessional);

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    // Getting the text from username and password EditText fields
    public void setuserpass() {

        userdata = username.getText().toString();

        passdata = password.getText().toString();
    }

    private void loginActivity() {
        try {
            final LogininputModel logininputmodel = new LogininputModel();

            setuserpass();
            try {
                if (userdata.equalsIgnoreCase("")) {
                    username.setError("Email Address is required");
                }
                if (passdata.equalsIgnoreCase("")) {
                    password.setError("Password is required");
                }
                if ((!userdata.equalsIgnoreCase("")) && (!passdata.equalsIgnoreCase(""))) {
                    if (Utils.isValidEmail(username.getText().toString())) {
                        if (rememberme.isChecked()) {
                            AppConfigManager.saveRememberme(mContext, "true");
                        }

                        if (!rememberme.isChecked()) {
                            AppConfigManager.saveRememberme(mContext, "false");
                        }

                        logininputmodel.setuserpassforobj(userdata, passdata);

                        logininputmodel.setUQId(Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID));

                        logininputmodel.setDN(androidDeviceInfo.getDeviceName(mContext));

                        logininputmodel.setDOS("Android");

                        logininputmodel.setMDN(androidDeviceInfo.getDeviceManufacturer(mContext));

                        logininputmodel.setMOD(androidDeviceInfo.getDeviceModel(mContext));

                        logininputmodel.setVer(androidDeviceInfo.getDeviceOsVersion(mContext));

                        LoginURL loginurl = new LoginURL(logininputmodel.getjsonobj(), mContext);

                        LoginInterface onasync = new LoginInterface() {
                            @Override
                            public void onResultSuccess(Vector<LoginModel> loginParseVector) {
                                Vector<LoginModel> returnobject = new Vector<LoginModel>();

                                returnobject = loginParseVector;

                                loginmodel = new LoginModel();

                                loginmodel = returnobject.get(0);

                                AppConfigManager.saveLoggedUid(mContext, loginmodel.getUid());

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (logininputmodel != null) {
                                            try {

                                                if (loginmodel.getosfield().equalsIgnoreCase("Success")) {
                                                    AppConfigManager.savePhone(mContext, loginmodel.getPh());

                                                    AppConfigManager.saveUserName(mContext, userdata, passdata);

                                                    AppConfigManager.saveAccessToken(mContext, loginmodel.getAT());

                                                    AppConfigManager.saveContactName(mContext, loginmodel.getCN());

                                                    AppConfigManager.saveDID(mContext, loginmodel.getDId());

                                                    AppConfigManager.saveCPA(mContext, loginmodel.getUT());

                                                    Log.e("BALA", loginmodel.getUT() + " " + loginmodel.getISHPRO());

                                                    if ((Integer.parseInt(loginmodel.getUT()) == 2) && (loginmodel.getISHPRO().equalsIgnoreCase("false"))) {
                                                        Log.e("BALA", "IN TAX");

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                                                        builder.setTitle(null);
                                                        builder.setMessage("Please use our regular site(WWW.EXPRESSTAXEXEMPT.COM) if you are Tax Professional/CPA");
                                                        builder.setPositiveButton("Ok", null);
                                                        builder.setCancelable(false);
                                                        builder.show();

                                                        /*//startActivity(new Intent(LoginActivity.this, TaxpreparerActivity.class));

                                                        finish();*/
                                                    } else if ((Integer.parseInt(loginmodel.getUT()) == 2) && (loginmodel.getISHPRO().equalsIgnoreCase("true") && (loginmodel.getISBE().equalsIgnoreCase("true")))) {
                                                        Log.e("BALA", "IN Dash");

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                                                        builder.setTitle(null);
                                                        builder.setMessage("Please use our regular site(WWW.EXPRESSTAXEXEMPT.COM) if you are Tax Professional/CPA");
                                                        builder.setPositiveButton("Ok", null);
                                                        builder.setCancelable(false);
                                                        builder.show();

//                                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));
//
//                                                        finish();

                                                    } else if ((Integer.parseInt(loginmodel.getUT()) == 2) && (loginmodel.getISHPRO().equalsIgnoreCase("true") && (loginmodel.getISBE().equalsIgnoreCase("false")))) {

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.MyAlertDialogStyle);
                                                        builder.setTitle(null);
                                                        builder.setMessage("Please use our regular site(WWW.EXPRESSTAXEXEMPT.COM) if you are Tax Professional/CPA");
                                                        builder.setPositiveButton("Ok", null);
                                                        builder.setCancelable(false);
                                                        builder.show();
//                                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("ID", "0").putExtra("TO_LAYOUT", "AddOrgDetail"));
//
//                                                        finish();

													/*startActivity(new Intent(LoginActivity.this, ExtensionTypeActivity.class).putExtra("ID", "0"));

													finish();
*/
                                                    } else if ((Integer.parseInt(loginmodel.getUT()) == 1) && loginmodel.getISBE().equalsIgnoreCase("true")) {
                                                        Log.e("BALA", "IN Normal BO");

                                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                                                        finish();

                                                    } else if ((Integer.parseInt(loginmodel.getUT()) == 1) && loginmodel.getISBE().equalsIgnoreCase("false")) {

                                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("ID", "0").putExtra("TO_LAYOUT", "AddOrgDetail"));

                                                        finish();

                                                    } else {

                                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                                                        finish();

                                                    }

                                                }

                                                if (loginmodel.getosfield().equalsIgnoreCase("Failure")) {

                                                    utils.errorMessage(mContext, "Sign In Failure. Check EMAIL ADDRESS and PASSWORD!!!");

                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                new SendException(mContext, e);
                                            }
                                        }
                                    }
                                });
                            }

                        };
                        loginurl.setOnResultListener(onasync);

                        loginurl.execute();
                    } else {
                        username.setError("Email Address is invalid");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    public void clearText() {

        emailaddress.setText("");

        passwordforsignup.setText("");

        contactname.setText("");

        phonenumber.setText("");

    }

    public int setInputsforSignup() {
        int x = 1;

        try {

            if (emailaddress.getText().toString().equalsIgnoreCase("")) {
                x = 0;
                emailaddress.setError("Email Address is required");
            } else if (!Utils.isValidEmail(emailaddress.getText().toString())) {
                x = 0;
                emailaddress.setError("Email Address is invalid");
            } else {
                signupinputmodel.setEmailAddress(emailaddress.getText().toString());
            }

            if (passwordforsignup.getText().toString().equalsIgnoreCase("")) {
                x = 0;
                passwordforsignup.setError("Password is required");
            } else if (passwordforsignup.getText().toString().length() < 6) {
                x = 0;
                passwordforsignup.setError("Enter minimum 6 characters");
            } else {

                signupinputmodel.setPassword(passwordforsignup.getText().toString());
            }

            if (contactname.getText().toString().equalsIgnoreCase("")) {
                x = 0;
                contactname.setError("Contact Name is required");
            } else {
                signupinputmodel.setContactName(contactname.getText().toString());
            }

            signupinputmodel.setHowuFindus("6");

            if (taxprofessional.isChecked()) {
                signupinputmodel.setUserType("2");
            } else {
                signupinputmodel.setUserType("1");
            }

            signupinputmodel.setCOM("ANDROID");

            signupinputmodel.setDN(androidDeviceInfo.getDeviceName(mContext));

            signupinputmodel.setDOS("Android");

            signupinputmodel.setMDN(androidDeviceInfo.getDeviceManufacturer(mContext));

            signupinputmodel.setMOD(androidDeviceInfo.getDeviceModel(mContext));

            signupinputmodel.setUQID(Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID));

            signupinputmodel.setVer(androidDeviceInfo.getDeviceOsVersion(mContext));

            if (phonenumber.getText().toString().equalsIgnoreCase("")) {
                phonenumber.setError("Phone Number is required");
                x = 0;
            } else if (!Utils.isValidPhone(phonenumber.getText().toString())) {
                phonenumber.setError("Invalid Entry");
                x = 0;
            } else {
                signupinputmodel.setPhoneNumber(phonenumber.getText().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

        return x;
    }

    public void signupactivity() {
        final String phone = phonenumber.getText().toString();

        SignupURL signupurl = new SignupURL(signupinputmodel.getjsonobj(), mContext);

        SignUpInterface onasync = new SignUpInterface() {

            @Override
            public void onResultSuccess(Vector<SignupModel> SignupParseVector) {

                Vector<SignupModel> returnobject = new Vector<SignupModel>();

                returnobject = SignupParseVector;

                signupmodel = new SignupModel();

                signupmodel = returnobject.get(0);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (signupmodel.getosfield().equalsIgnoreCase("Success")) {

                                AppConfigManager.saveAccessToken(mContext, signupmodel.getAT());

                                AppConfigManager.savePhone(mContext, phone);

                                AppConfigManager.saveLoggedUid(mContext, signupmodel.getUID());

                                AppConfigManager.saveDID(mContext, signupmodel.getDID());

                                AppConfigManager.saveContactName(mContext, signupmodel.getcontactname());

                                AppConfigManager.saveUserName(mContext, emailaddress.getText().toString(), "");

                                AppConfigManager.saveCPA(mContext, signupmodel.getUT());

                                clearText();

                                if ((Integer.parseInt(signupmodel.getUT()) == 2)) {
                                    Log.e("BALA", "IN TAX");

                                    startActivity(new Intent(LoginActivity.this, TaxpreparerActivity.class));

                                    finish();

                                } else if ((Integer.parseInt(signupmodel.getUT()) == 1)) {
                                    Log.e("BALA", "IN Normal BO");

                                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("ID", "0").putExtra("From", "sign").putExtra("TO_LAYOUT", "AddOrgDetail"));

                                    finish();

                                }

                            }
                            if (signupmodel.getisAlreadyexist().equalsIgnoreCase("true") && signupmodel.getosfield().equalsIgnoreCase("Failure")) {
                                emailaddress.setError("This Email Address already exists");

                            }
                            if (signupmodel.getisAlreadyexist().equalsIgnoreCase("false") && signupmodel.getosfield().equalsIgnoreCase("Failure")) {

                                utils.errorMessage(mContext, "Sign Up Service Failed!!!");

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            new SendException(mContext, e);
                        }

                    }
                });
            }

        };
        signupurl.setOnResultListener(onasync);
        signupurl.execute();
    }

    // Onclick Event Method for Login
    public void setonClickListener() {

        try {


            rootlayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return false;
                }
            });

            rememberme.setOnCheckedChangeListener(new OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AppConfigManager.saveRememberme(mContext, "true");
                    }

                    if (!isChecked) {
                        AppConfigManager.saveRememberme(mContext, "false");
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

            fb.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ExpressExtension")));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });

            tw.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/irstaxextension")));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });
            gp.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/116273116422147713679/about")));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });
            yt.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/ExpressExtension")));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });
            li.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/company/express-extension")));
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }
                }
            });
            forgetpassword.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        Intent forgetIntent = new Intent(LoginActivity.this, Forgetpassword.class);

                        forgetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                        forgetIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                        startActivityForResult(forgetIntent, 101);
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }

                }
            });

            username.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    username.setError(null);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            password.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    password.setError(null);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            password.setOnEditorActionListener(new OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (!Utils.isValidEmail(username.getText().toString())) {
                            username.setError("Email Address is invalid");
                        }
                        if (connectiondetector.isConnectingToInternet()) {
                            loginActivity();
                        } else {

                            utils.errorMessage(mContext, "No Internet Connection!!!");

                        }

                    }

                    return false;
                }
            });

            phonenumber.addTextChangedListener(new TextWatcher() {

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

                    try {
                        String Phone = phonenumber.getText().toString();

                        String WithOutSpecialChar = Phone.replaceAll("\\W", "");

                        if (Phone.length() == 3 || Phone.length() == 6 || Phone.length() == 10) {
                            flag = true;
                        }

                        if ((WithOutSpecialChar.length() == 3) && (flag == true)) {
                            flag = false;

                            phonenumber.setText(String.format("(%s)", WithOutSpecialChar.substring(0, 3)));

                            phonenumber.setSelection(phonenumber.getText().length());

                        } else if ((WithOutSpecialChar.length() == 6) && (flag == true)) {

                            flag = false;

                            phonenumber.setText(String.format("(%s) %s", WithOutSpecialChar.substring(0, 3),

                                    WithOutSpecialChar.substring(3, 6)));

                            phonenumber.setSelection(phonenumber.getText().length());

                        } else if ((WithOutSpecialChar.length() == 10) && flag == true) {

                            flag = false;

                            phonenumber.setText(String.format("(%s) %s-%s", WithOutSpecialChar.substring(0, 3),

                                    WithOutSpecialChar.substring(3, 6), WithOutSpecialChar.substring(6, 10)));

                            phonenumber.setSelection(phonenumber.getText().length());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        new SendException(mContext, e);
                    }
                }

            });

            submit.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (setInputsforSignup() == 1) {
                        signupactivity();
                    }

                }
            });

            submitforlogin.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {


                        loginActivity();


                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                }
            });

            backtologin.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    final Animation slideoutleft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_middle);

                    final Animation slideoutleft1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_middle);

                    final Animation slideinright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_enter);

                    slideoutleft1.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            signupcontent.setVisibility(View.GONE);

                            logincontent.setVisibility(View.VISIBLE);

                            logincontent.startAnimation(slideoutleft);
                        }
                    });

                    slideinright.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }
                    });

                    backtologin.setVisibility(View.INVISIBLE);

                    donthaveaccounttextview.setVisibility(View.VISIBLE);

                    Signup.setVisibility(View.VISIBLE);

                    username.requestFocus();

                    signupcontent.startAnimation(slideoutleft1);

                }
            });

            Signup.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        emailaddress.requestFocus();

                        Animation slidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottomdown);

                        final Animation slideinright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_middle);

                        slideinright.setAnimationListener(new AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }
                        });

                        Animation slideout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.to_middle);

                        slideout.setAnimationListener(new AnimationListener() {

                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                logincontent.setVisibility(View.GONE);

                                signupcontent.setVisibility(View.VISIBLE);

                                signupcontent.startAnimation(slideinright);

                            }
                        });

                        donthaveaccounttextview.setVisibility(View.INVISIBLE);

                        Signup.startAnimation(slidedown);

                        Signup.setVisibility(View.INVISIBLE);

                        logincontent.startAnimation(slideout);

                        backtologin.setVisibility(View.VISIBLE);
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void focusListener() {


        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    username.setTypeface(typeface, Typeface.BOLD);

                else

                    username.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    password.setTypeface(typeface, Typeface.BOLD);

                else

                    password.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        emailaddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    emailaddress.setTypeface(typeface, Typeface.BOLD);

                else

                    emailaddress.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        passwordforsignup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    passwordforsignup.setTypeface(typeface, Typeface.BOLD);

                else

                    passwordforsignup.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        contactname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    contactname.setTypeface(typeface, Typeface.BOLD);

                else

                    contactname.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        phonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    phonenumber.setTypeface(typeface, Typeface.BOLD);

                else

                    phonenumber.setTypeface(typeface, Typeface.NORMAL);

            }
        });


    }
}
