package com.span.expressextension8868.controller.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.span.expressextension8868.model.core.HoldingModel;
import com.span.expressextension8868.businesslogic.parsing.DeleteHoldingCompanyService;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyApplication;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyCountURL;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyCountURL.GetHoldingCompanyCountInterface;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyURL;
import com.span.expressextension8868.webservice.webservices.GetHoldingCompanyURL.GetHoldingCompanyInterface;
import com.span.expressextension8868.webservice.webservices.SaveHoldingCompanyURL;
import com.span.expressextension8868.webservice.webservices.SaveHoldingCompanyURL.SaveHoldingCompanyInterface;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.span.expressextension8868.R;

public class HoldingPopUpActivity extends Activity {

    // Context

    Context mContext = HoldingPopUpActivity.this;

    //TextView's

    TextView isaddressoutsidetextview;

    // EditText's

    EditText BusinessNameeditText, EineditText, addressEdittext1, addresseditText2, cityeditText, stateorprovince, zipeditText;

    // Spinners's
    Spinner noeinspinner, stateSpinner, countrySpinner;

    //Button

    Button addButton;

    // ListView
    ListView holdinglistView;

    //CheckBox

    CheckBox noeincheckbox, isaddoutsidecheckbox;

    // Database Helper class
    DatabaseHandler databasehandler;

    // Flag's for Masking EIN's
    Boolean flag = true;

    //Linear Layout

    LinearLayout rootlayout, norecordsfound;

    //String's

    String countryid = "0", stateid = "0";

    HoldingModel holdingModelSave = new HoldingModel();

    // ImageView's

    ImageView closeicon;

    Utils utils;

    int HoldingCount = 0, EditTag = 0;

    String HoldingCompanyID = "0";

    NetworkChangeReceiver receiver;

    HoldingCountListener holdingCountListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.groupreturnpopup);

        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);

        setFinishOnTouchOutside(false);

        Initialization();

        SetSpinnerInputsFromDB();

        setOnclickListener();

        GetHoldingCompanyListInView();

        Overridefonts.overrideFonts(mContext, rootlayout);

        receiver = new NetworkChangeReceiver();

        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        ((Activity) mContext).getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        if (AppConfigManager.getMode(mContext) != null && AppConfigManager.getMode(mContext).equalsIgnoreCase("VIEW")) {
            DisableEdittext.disableAll(mContext, rootlayout);

            addButton.setEnabled(false);
        } else {
            DisableEdittext.EnableAll(mContext, rootlayout);

            addButton.setEnabled(true);
        }

        // Get a Tracker (should auto-report)
        ((MyApplication) getApplication()).getTracker(MyApplication.TrackerName.APP_TRACKER);
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

    @Override
    public void onBackPressed() {

    }

    public void GetHoldingCompanyListInView() {

        Log.i("In Holding List", "In holding List");

        HoldingModel getholdingmodel = new HoldingModel();

        getholdingmodel.setAT(AppConfigManager.getAccessToken(mContext));

        getholdingmodel.setUID(AppConfigManager.getLoggedUid(mContext));

        getholdingmodel.setDID(AppConfigManager.getDID(mContext));

        getholdingmodel.setEODId(AppConfigManager.getEODIDD(mContext));

        getholdingmodel.setRID(AppConfigManager.getReturnRID(mContext));

        GetHoldingCompanyURL getHoldingcompanyURL = new GetHoldingCompanyURL(getholdingmodel.getHoldingListRequestByRID(), mContext);

        GetHoldingCompanyInterface getHoldingCompanyInterface = new GetHoldingCompanyInterface() {

            @Override
            public void onResultSuccess(final HoldingModel getHoldingcompanyModel) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if ((getHoldingcompanyModel.getOS() != null && getHoldingcompanyModel.getOS().equalsIgnoreCase("Success")) || getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {
                            Log.i("getHoldingcompanyModel.getHoldingmodelVector().size()", "Size " + getHoldingcompanyModel.getHoldingmodelVector().size());

                            if (getHoldingcompanyModel.getHoldingmodelVector() != null && getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {

                                Log.i("In list", "list");

                                holdinglistView.setVisibility(View.VISIBLE);

                                norecordsfound.setVisibility(View.GONE);

                                CustomAdapterHoldingList customAdapterHoldingList = new CustomAdapterHoldingList(mContext, getHoldingcompanyModel);

                                customAdapterHoldingList.notifyDataSetChanged();

                                holdinglistView.setAdapter(customAdapterHoldingList);

                            } else {
                                Log.i("In no list", "no list");

                                holdinglistView.setVisibility(View.GONE);

                                norecordsfound.setVisibility(View.VISIBLE);
                            }

                            Log.i("outside", "outside");
                        } else if (getHoldingcompanyModel.getOS().equalsIgnoreCase("Failure") && getHoldingcompanyModel.getEM().equalsIgnoreCase("Access Token is invalid")) {
                            utils.errorMessage(mContext, "Your Session is Expired");

                            Logout.logout(mContext);
                        }

                    }
                });

            }

        };

        getHoldingcompanyURL.setOnResultListener(getHoldingCompanyInterface);

        getHoldingcompanyURL.execute();

    }

    private void SetSpinnerInputsFromDB() {

        databasehandler = new DatabaseHandler(mContext);

        setSpinnerAdapter(databasehandler.getCountrynameList(), countrySpinner);

        setSpinnerAdapter(databasehandler.getStatenameList(), stateSpinner);

    }

    private void setOnclickListener() {

        rootlayout.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                return false;
            }
        });

        closeicon.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

				/*HoldingModel holdingmodelforCount=new HoldingModel();

				holdingmodelforCount.setAT(AppConfigManager.getAccessToken(mContext));

				holdingmodelforCount.setDID(AppConfigManager.getDID(mContext));

				holdingmodelforCount.setUID(AppConfigManager.getLoggedUid(mContext));

				holdingmodelforCount.setRID(AppConfigManager.getReturnRID(mContext));

				GetHoldingCompanyCountURL getHoldingCompanyCountURL=new GetHoldingCompanyCountURL(holdingmodelforCount.getHoldingListRequestByRID(), mContext);

				GetHoldingCompanyCountInterface getHoldingCompanyCountInterface=new GetHoldingCompanyCountInterface() {

					@Override
					public void onResultSuccess(HoldingModel getHoldingcompanyModel) 
					{

						HoldingCount=Integer.parseInt(getHoldingcompanyModel.getHCCount());

					}
				};
				getHoldingCompanyCountURL.setOnResultListener(getHoldingCompanyCountInterface);

				getHoldingCompanyCountURL.execute();*/

                finish();
            }
        });


        EineditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                masking(EineditText);
            }
        });

        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ValidationCheck() == 1 && EditTag == 0) {
                    SaveHoldingCompany("ADD");
                } else if (ValidationCheck() == 1 && EditTag == 1) {
                    SaveHoldingCompany("UPDATE");
                }

                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        isaddressoutsidetextview.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                isaddoutsidecheckbox.performClick();

                return false;
            }
        });


        isaddoutsidecheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    stateSpinner.setVisibility(View.GONE);

                    stateorprovince.setVisibility(View.VISIBLE);

                    countrySpinner.setVisibility(View.VISIBLE);
                } else {
                    stateSpinner.setVisibility(View.VISIBLE);

                    stateorprovince.setVisibility(View.GONE);

                    countrySpinner.setVisibility(View.GONE);
                }


            }
        });


        countrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryid = databasehandler.getCountryIDList().get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        stateSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                stateid = databasehandler.getStateIDList().get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        zipeditText.addTextChangedListener(new TextWatcher() {

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
                if (!isaddoutsidecheckbox.isChecked()) {
                    maskingzipcode(zipeditText);
                }

            }
        });

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

    protected void SaveHoldingCompany(final String LGT) {

        holdingModelSave.setAT(AppConfigManager.getAccessToken(mContext));

        holdingModelSave.setDID(AppConfigManager.getDID(mContext));

        holdingModelSave.setUID(AppConfigManager.getLoggedUid(mContext));

        holdingModelSave.setRID(AppConfigManager.getReturnRID(mContext));

        holdingModelSave.setEODId(AppConfigManager.getEODIDD(mContext));

        if (LGT.equalsIgnoreCase("ADD")) {
            holdingModelSave.setHoldingCompanyId("0");
        } else {
            holdingModelSave.setHoldingCompanyId(HoldingCompanyID);
        }

        holdingModelSave.setLGT(LGT);

        SaveHoldingCompanyURL saveHoldingCompanyURL = new SaveHoldingCompanyURL(holdingModelSave.getHoldingCompanySaveRequest(), mContext);

        SaveHoldingCompanyInterface saveHoldingCompanyInterface = new SaveHoldingCompanyInterface() {

            @Override
            public void onResultSuccess(final HoldingModel holdingModel) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        if (holdingModel != null && holdingModel.getOS() != null && holdingModel.getOS().equalsIgnoreCase("Success")) {
                            if (LGT.equalsIgnoreCase("UPDATE")) {
                                EditTag = 0;

                                addButton.setText("ADD");

                            }

                            if (LGT.equalsIgnoreCase("ADD")) {
                                // Toast goes here...
                            }

                            clearFields();

                            HoldingModel getholdingmodel = new HoldingModel();

                            getholdingmodel.setAT(AppConfigManager.getAccessToken(mContext));

                            getholdingmodel.setUID(AppConfigManager.getLoggedUid(mContext));

                            getholdingmodel.setDID(AppConfigManager.getDID(mContext));

                            getholdingmodel.setRID(AppConfigManager.getReturnRID(mContext));

                            GetHoldingCompanyURL getHoldingcompanyURL = new GetHoldingCompanyURL(getholdingmodel.getHoldingListRequestByRID(), mContext);

                            GetHoldingCompanyInterface getHoldingCompanyInterface = new GetHoldingCompanyInterface() {

                                @Override
                                public void onResultSuccess(final HoldingModel getHoldingcompanyModel) {

                                    runOnUiThread(new Runnable() {
                                        public void run() {

                                            if (getHoldingcompanyModel.getHoldingmodelVector().size() > 0) {

                                                holdinglistView.setVisibility(View.VISIBLE);

                                                norecordsfound.setVisibility(View.GONE);

                                                CustomAdapterHoldingList customAdapterHoldingList = new CustomAdapterHoldingList(mContext, getHoldingcompanyModel);

                                                holdinglistView.setAdapter(customAdapterHoldingList);

                                            } else {
                                                holdinglistView.setVisibility(View.GONE);

                                                norecordsfound.setVisibility(View.VISIBLE);
                                            }
                                        }
                                    });
                                }
                            };

                            getHoldingcompanyURL.setOnResultListener(getHoldingCompanyInterface);

                            getHoldingcompanyURL.execute();

                        } else if (holdingModel != null && holdingModel.getOS() != null && holdingModel.getOS().equalsIgnoreCase("Failure") && !holdingModel.getEM().equalsIgnoreCase("Access Token is Invalid")) {
                            utils.errorMessage(mContext, "holdingModel.getEM()");


                        } else if (holdingModel != null && holdingModel.getOS() != null && holdingModel.getOS().equalsIgnoreCase("Failure") && holdingModel.getEM().equalsIgnoreCase("Access Token is Invalid")) {


                            utils.errorMessage(mContext, "Your Session is Expired");

                            Logout.logout(mContext);
                        }

                    }


                });

            }
        };

        saveHoldingCompanyURL.setOnResultListener(saveHoldingCompanyInterface);

        saveHoldingCompanyURL.execute();

    }

    public void clearFields() {

        BusinessNameeditText.setText("");

        EineditText.setText("");

        isaddoutsidecheckbox.setChecked(false);

        addressEdittext1.setText("");

        addresseditText2.setText("");

        cityeditText.setText("");

        stateSpinner.setSelection(0);

        stateorprovince.setSelection(0);

        countrySpinner.setSelection(0);

        zipeditText.setText("");

    }

    public int ValidationCheck() {
        int flag_for_validation = 1;

        if (BusinessNameeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;

            BusinessNameeditText.setError("Organization Name Required");
        } else {
            BusinessNameeditText.setError(null);

            holdingModelSave.setHoldingCompanyName(BusinessNameeditText.getText().toString());

        }

        if (EineditText.isShown()) {
            if (EineditText.getText().toString().equalsIgnoreCase("")) {
                flag_for_validation = 0;
                EineditText.setError("EIN Required");
            } else if (EineditText.getText().toString().equalsIgnoreCase("00-0000000")) {
                flag_for_validation = 0;
                EineditText.setError("Invalid Entry");
            } else if (EineditText.getText().toString().length() < 10) {
                flag_for_validation = 0;
                EineditText.setError("Ein Should be 9 digits");
            } else {
                EineditText.setError(null);

                holdingModelSave.setHoldingCompanyEIN(EineditText.getText().toString());
            }
        } else {
            holdingModelSave.setHoldingCompanyEIN("");
        }

        if (isaddoutsidecheckbox.isChecked()) {
            holdingModelSave.setHoldingCompanyIsForeignAddress("true");
        } else {
            holdingModelSave.setHoldingCompanyIsForeignAddress("false");
        }

        if (addressEdittext1.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            addressEdittext1.setError("Address 1 Required");
        } else {
            addressEdittext1.setError(null);
            holdingModelSave.setHoldingCompanyAddress1(addressEdittext1.getText().toString());
        }

        holdingModelSave.setHoldingCompanyAddress2(addresseditText2.getText().toString());

        if (cityeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            cityeditText.setError("City Required");
        } else {
            cityeditText.setError(null);
            holdingModelSave.setHoldingCompanyCity(cityeditText.getText().toString());
        }

        if (zipeditText.getText().toString().equalsIgnoreCase("")) {
            flag_for_validation = 0;
            zipeditText.setError("ZIP Code Required");
        }
        if (!isaddoutsidecheckbox.isChecked() && !Utils.isValidZipCode(zipeditText.getText().toString())) {
            flag_for_validation = 0;
            zipeditText.setError("Invalid ZIP Code");
        } else {
            zipeditText.setError(null);

            holdingModelSave.setHoldingCompanyZip(zipeditText.getText().toString());
        }

        if (stateSpinner.isShown()) {
            if (stateSpinner.getSelectedItemPosition() == 0) {
                flag_for_validation = 0;

                utils.errorMessage(mContext, "State Required");

            } else {
                holdingModelSave.setHoldingCompanyStateId(stateid);
            }
        } else {
            holdingModelSave.setHoldingCompanyStateId("0");
        }

        if (countrySpinner.isShown()) {
            if (countrySpinner.getSelectedItemPosition() == 0) {
                flag_for_validation = 0;

                utils.errorMessage(mContext, "Country Required");

            } else {
                holdingModelSave.setHoldingCompanyCountryId(countryid);
            }
        } else {
            holdingModelSave.setHoldingCompanyCountryId("0");
        }

        if (stateorprovince.isShown()) {
            if (stateorprovince.getText().toString().equalsIgnoreCase("")) {
                flag_for_validation = 0;

                utils.errorMessage(mContext, "State or Province Required");

            } else {
                holdingModelSave.setHoldingCompanyState(stateorprovince.getText().toString());
            }
        } else {
            holdingModelSave.setHoldingCompanyState("");
        }

        return flag_for_validation;

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

    private void Initialization() {

        //Textview

        utils = new Utils();

        isaddressoutsidetextview = (TextView) findViewById(R.id.isaddressoutsidetextview);

        // EditText's

        BusinessNameeditText = (EditText) findViewById(R.id.BusinessNameeditText);

        Utils.setHintwithSuperScript(BusinessNameeditText);

        EineditText = (EditText) findViewById(R.id.EineditText);

        Utils.setHintwithSuperScript(EineditText);

        addressEdittext1 = (EditText) findViewById(R.id.addressEdittext1);

        Utils.setHintwithSuperScript(addressEdittext1);

        addresseditText2 = (EditText) findViewById(R.id.addresseditText2);

        cityeditText = (EditText) findViewById(R.id.cityeditText);

        Utils.setHintwithSuperScript(cityeditText);

        stateorprovince = (EditText) findViewById(R.id.stateorprovince);

        Utils.setHintwithSuperScript(stateorprovince);

        zipeditText = (EditText) findViewById(R.id.zipeditText);

        Utils.setHintwithSuperScript(zipeditText);

        // Spinner's

        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);

        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);

        //CheckBox's

        isaddoutsidecheckbox = (CheckBox) findViewById(R.id.isaddoutsidecheckbox);

        // Button

        addButton = (Button) findViewById(R.id.addButton);

        addButton.setText("ADD");

        //ListView

        holdinglistView = (ListView) findViewById(R.id.holdinglistView);

        //Linear Layout's

        rootlayout = (LinearLayout) findViewById(R.id.rootlayout);

        norecordsfound = (LinearLayout) findViewById(R.id.norecordsfound);

        // ImageView's

        closeicon = (ImageView) findViewById(R.id.closeicon);

    }

    public void setSpinnerAdapter(ArrayList<String> list, Spinner spinner) {

        try {
            ArrayAdapter<String> Spinneradapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnerdataxml, R.id.spinnertext, list);

            spinner.setAdapter(Spinneradapter);
        } catch (Exception e) {
            new SendException(mContext, e);

            e.printStackTrace();
        }

    }

    public interface HoldingCountListener {
        public abstract void OnHoldingDialogClosed(int count);

    }

    public class CustomAdapterHoldingList extends BaseAdapter {
        private final Context mContext;

        HoldingModel holdingmodel = new HoldingModel();

        private int selectedIndex;

        private final int[] colors = new int[]
                {0xFFc8e3f4, 0xFFD9EBF6};

        private final int selectedColor = Color.parseColor("#FFFFFF");

        public CustomAdapterHoldingList(Context activity, HoldingModel holdingModel) {
            this.holdingmodel = holdingModel;

            this.mContext = activity;

            selectedIndex = 0;
        }

        public void setSelectedIndex(int ind) {
            selectedIndex = ind;

            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (holdingmodel.getHoldingmodelVector().size() <= 0) {
                return 0;
            }
            return holdingmodel.getHoldingmodelVector().size();
        }

        @Override
        public Object getItem(int position) {
            return holdingmodel.getHoldingmodelVector().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Condensed.ttf");

            View rowView = convertView;

            ViewHolder view = null;

            Log.i("Value", "getview");
            try {
                if (rowView == null) {
                    Log.i("if", "getview");
                    // Get a new instance of the row layout view

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    rowView = inflater.inflate(R.layout.holdinglistinflate, null);

                    // Hold the view objects in an object, that way the don't need
                    // to be
                    // "re-  finded"
                    view = new ViewHolder();

                    view.businessName = (TextView) rowView.findViewById(R.id.businessName);

                    view.businessName.setTypeface(type);

                    view.ein = (TextView) rowView.findViewById(R.id.ein);

                    view.ein.setTypeface(type);

                    view.addressline1 = (TextView) rowView.findViewById(R.id.addressline1);

                    view.addressline2 = (TextView) rowView.findViewById(R.id.addressline2);

                    view.editholding = (ImageView) rowView.findViewById(R.id.editholding);

                    view.deleteholding = (ImageView) rowView.findViewById(R.id.deleteholding);

                    // view.managebusinesslistlayout.setBackgroundColor(0x003300);

                    rowView.setTag(view);
                } else {
                    Log.i("else", "getview");

                    view = (ViewHolder) rowView.getTag();
                }

                if (AppConfigManager.getMode(mContext) != null && AppConfigManager.getMode(mContext).equalsIgnoreCase("VIEW")) {
                    view.editholding.setEnabled(false);

                    view.deleteholding.setEnabled(false);
                } else {
                    view.editholding.setEnabled(true);

                    view.deleteholding.setEnabled(true);
                }


                /** Set data to your Views. */

                if (position == selectedIndex) {
                    view.ein.setTextColor(Color.parseColor("#ED7C18"));
                } else {
                    view.ein.setTextColor(Color.parseColor("#125F76"));
                }

                if (holdingmodel.getHoldingmodelVector().size() != 0) {
                    view.businessName.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName());

                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("") && !holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("null")) {
                        view.ein.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN());
                    }

                    String isforeignAddress = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyIsForeignAddress();

                    String Address1 = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1();

                    String Address2 = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2();

                    String city = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity();

                    String state = databasehandler.getStatenameFromSID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyStateId());

                    String stateorProvince = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyState();

                    String country = databasehandler.getCountryNameFromCID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCountryId());

                    String zipcode = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip();

                    if (holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("") || holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("null")) {
                        view.addressline1.setText(Address1);
                    } else {
                        view.addressline1.setText(Address1 + " " + Address2);
                    }

                    if (isforeignAddress.equalsIgnoreCase("false")) {
                        view.addressline2.setText(city + ", " + state + " " + zipcode);
                    } else if (isforeignAddress.equalsIgnoreCase("true")) {
                        view.addressline2.setText(city + ", " + stateorProvince + ", " + country + " - " + zipcode);
                    }

                    view.editholding.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    HoldingCompanyID = holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyId();

                                    EditTag = 1;

                                    addButton.setText("UPDATE");

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName().equalsIgnoreCase("null")) {
                                        BusinessNameeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyName());
                                    }

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN().equalsIgnoreCase("null")) {
                                        EineditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyEIN());
                                    }

                                    if (holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyIsForeignAddress().equalsIgnoreCase("true")) {
                                        isaddoutsidecheckbox.setChecked(true);
                                    } else {
                                        isaddoutsidecheckbox.setChecked(false);
                                    }

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1().equalsIgnoreCase("null")) {
                                        addressEdittext1.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress1());
                                    }

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2().equalsIgnoreCase("null")) {
                                        addresseditText2.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyAddress2());
                                    }

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity().equalsIgnoreCase("null")) {
                                        cityeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCity());
                                    }

                                    if (stateorprovince.isShown()) {
                                        stateorprovince.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyState());
                                    }

                                    if (stateSpinner.isShown()) {
                                        stateSpinner.setSelection(Integer.parseInt(databasehandler.getStatePositionFromSID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyStateId())));
                                    }

                                    if (countrySpinner.isShown()) {
                                        countrySpinner.setSelection(Integer.parseInt(databasehandler.getCountryPosFromCID(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyCountryId())));
                                    }

                                    if (!holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip().equalsIgnoreCase("null")) {
                                        zipeditText.setText(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyZip());
                                    }
                                }
                            });

                        }
                    });

                    view.deleteholding.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            new AlertDialog.Builder(mContext).setTitle("Delete Part of Group").setMessage("Are you sure you want to Delete this Part of Group?").setNegativeButton("No", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final HoldingModel holdingModelDelete = new HoldingModel();

                                    holdingModelDelete.setAT(AppConfigManager.getAccessToken(mContext));

                                    holdingModelDelete.setDID(AppConfigManager.getDID(mContext));

                                    holdingModelDelete.setUID(AppConfigManager.getLoggedUid(mContext));

                                    holdingModelDelete.setHoldingCompanyId(holdingmodel.getHoldingmodelVector().get(position).getHoldingCompanyId());

                                    new AsyncTask<Void, Void, ArrayList<String>>() {
                                        ProgressDialog pd;

                                        @Override
                                        protected void onPreExecute() {
                                            pd = MyCustomProgressDialog.ctor(mContext);

                                            pd.show();
                                        }

                                        ;

                                        @Override
                                        protected void onPostExecute(final ArrayList<String> receivedValue) {

                                            pd.dismiss();

                                            runOnUiThread(new Runnable() {
                                                public void run() {

                                                    if (receivedValue.get(0).equalsIgnoreCase("Success")) {
                                                        clearFields();

                                                        utils.errorMessage(mContext, "Part of Group Deleted");


                                                        GetHoldingCompanyListInView();

                                                    } else if (receivedValue.get(0).equalsIgnoreCase("Failure") && receivedValue.get(1) != null && !receivedValue.get(1).equalsIgnoreCase("null")) {

                                                        utils.errorMessage(mContext, "receivedValue.get(1)");
                                                    }

                                                }
                                            });

                                        }

                                        @Override
                                        protected ArrayList<String> doInBackground(Void... params) {
                                            ArrayList<String> receivedValue = new ArrayList<String>();

                                            DeleteHoldingCompanyService deleteHoldingCompanyService = new DeleteHoldingCompanyService();

                                            receivedValue = deleteHoldingCompanyService.getosfieldvalue(holdingModelDelete.getHoldingCompanyDeleteRequest(), ApplicationConfig.UPDATEFORM8868PARTOFGROUP, mContext);

                                            return receivedValue;
                                        }
                                    }.execute();
                                }
                            }).setIcon(R.drawable.delete).show();

                        }
                    });


                }

                if (selectedIndex != -1 && position == selectedIndex) {
                    rowView.setBackgroundColor(selectedColor);
                } else {
                    int colorPos = position % colors.length;

                    rowView.setBackgroundColor(colors[colorPos]);
                }

            } catch (Exception e) {
                e.printStackTrace();

                new SendException(mContext, e);
            }
            return rowView;
        }

        protected class ViewHolder {
            protected TextView businessName;

            protected TextView ein;

            protected TextView addressline1;

            protected TextView addressline2;

            protected ImageView editholding;

            protected ImageView deleteholding;

        }

    }

}
