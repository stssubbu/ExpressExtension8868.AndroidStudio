package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.ExemptModel;
import com.span.expressextension8868.model.core.Timezonemodel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.MyCustomProgressDialog;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.CountryStateAndFormOrganizationURL;
import com.span.expressextension8868.webservice.webservices.CountryStateAndFormOrganizationURL.StaticDataInterface;
import com.span.expressextension8868.webservice.webservices.ExemptOrgURL;
import com.span.expressextension8868.webservice.webservices.ExemptOrgURL.ExemptInterface;
import com.span.expressextension8868.webservice.webservices.GetBusinessListDetailURL;
import com.span.expressextension8868.webservice.webservices.GetBusinessLookupURL;
import com.span.expressextension8868.webservice.webservices.GetBusinessLookupURL.OnAsyncResultBusinessLookUP;
import com.span.expressextension8868.webservice.webservices.TimezoneURL;
import com.span.expressextension8868.webservice.webservices.TimezoneURL.TimeZoneInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

public class AddExemptOrganization extends Fragment {

    Context mContext;

    View ExemptOrg;

    ExemptModel exemptModel;

    Typeface typeface;

    Vector<Timezonemodel> message;

    String typeofSave = "ADD";

    boolean stateBool, timezoneBool, countryBool, bookStateBool, bookCountryBool, fromDashboard, fromForm;

    LinearLayout DashboardOrgDetailsLayout, logolayout, allLayout;

    TextView DashBoardTitle, dashboardlink, DashboardOrgEin, DashboardOrgName;

    ImageView refresh, businesslistview;

    FrameLayout buttonLayout;

    // TextView's

    TextView OrgTitle, principalTitle, organizationnametextview, einssntextview; //confirmeinssntextview,

    TextView address1, address2, citytextView, Statetextview, address_websiteaddress, Countrytextview, ziptextView, Timezonetext, bookstateorprovincetextView, bookphonetextview, stateorprovincetextView, formoforganizationtextView;

    TextView nameofpersontextview, phonetextview, addressline1textview, addressline2textview, bookCityTextview, bookStateTextview, bookCountryText, bookZipCodeTextview;

    TextView emailaddresstextview; //primarynameText,TitleBusinessText,daytimephonenumbertextview,

    android.support.v7.widget.CardView cancelbutton, next;

    TextView cancelbuttonText, nextText;

    // EditText's

    com.rengwuxian.materialedittext.MaterialEditText organizationnameedittext, einedittext, doingbusinessas, OtherDBANames;//confirmeineditText,ssnedittext,,confirmssneditText

    com.rengwuxian.materialedittext.MaterialEditText addressEdittext1, addresseditText2, stateorprovinceEdit, cityeditText, address_phonenumber, zipeditText, nameofpersonedittext, bookaddress_phonenumber;

    com.rengwuxian.materialedittext.MaterialEditText bookaddressline1edittext, bookaddressline2edittext, bookCityEditText, bookstateorprovince, bookZipCodeEditText;//,FaxNumberEditText;

    com.rengwuxian.materialedittext.MaterialEditText emailaddressedittext, other_text;//primarynameEditText,TitleEditText,primaryphoneEditText,

    // CheckBox's

    CheckBox addressisaddressoutsidecheckbox, bookisaddressoutside, isbusinessaddresssamecheckbox;//isbusinesscheckbox,iusessncheckbox,

    // Spinner's

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner form_of_organization, countrySpinner, bookCountry, bookState, stateSpinner, timezone;


    // Database helper Class

    DatabaseHandler databasehandler;

    //Linear Layout's

    LinearLayout exceptEinLayout, principalLayout, timezonevert, statevertical, stateorpovinceLayout, bookstateorprovinceLayout, bookState_layout, countryvert, testId, booktestId, bookzipcodelayout, bookcountrylayout, wholecontent, organizationdetails, AddressDetails, BooksareinCareDetails;//,SigningAuthor;

    LinearLayout otherFormOfOrgLayout, boodAddressLayout, bookStateLayout;

    String stateid = "0", bookstateid = "0", countryid = "0", bookcountryid, timezoneid = "0";

    int statepos = 0, countrypos = 0, timezonepos = 0;

    // Vector objects

    Vector<Vector<String>> countryStateOrganizationVector;

    Vector<AddBussinessInputModel> businessLookUpModelVector = new Vector<AddBussinessInputModel>();

    Vector<String> timezoneidvector = new Vector<String>();

    Vector<String> timezonenamevector = new Vector<String>();

    ArrayList<String> timezonenamesortedlist = new ArrayList<String>();

    Vector<String> timezoneidlist = new Vector<String>();

    ArrayList<String> formOfOrgList = new ArrayList<String>();


    AddBussinessInputModel addbussinessinputmodel = new AddBussinessInputModel();

    AddBussinessInputModel businessLookupModel = new AddBussinessInputModel();

    //Button

    android.support.v7.widget.CardView searchbutton;


    ExemptModel exemptmodel;


    private ProgressDialog pd;

    //

    String businessId, EIN, OrgName;

    Utils utils;

    public AddExemptOrganization(Context mContext, boolean fromDashboard, boolean fromForm) {

        this.mContext = mContext;

        this.fromDashboard = fromDashboard;

        this.fromForm = fromForm;


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        typeface = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");

        businessId = AppConfigManager.getBID(mContext);

        EIN = AppConfigManager.getEIN(mContext);

        OrgName = AppConfigManager.getBusinessname(mContext);

        if (businessId == null || businessId.equalsIgnoreCase("null")) {
            businessId = "0";
        }

        try {


            ExemptOrg = inflater.inflate(R.layout.newexemptorganization, container, false);

            Initialization();

            setTypeFace();

            Textwatchers();

            //  focusListener();

            SetonclickListener();

            exemptmodel = new ExemptModel();


            if (!fromDashboard) {

                dashboardlink.setEnabled(false);

                logolayout.setEnabled(false);

                AppConfigManager.saveBID(mContext, "0");

                cancelbutton.setVisibility(View.INVISIBLE);

                NewUserCongratulation();

            } else {

                cancelbutton.setVisibility(View.VISIBLE);

                dashboardlink.setEnabled(true);

                if (EIN != null && OrgName != null && !businessId.equalsIgnoreCase("0")) {

                    logolayout.setEnabled(true);

                    typeofSave = "UPDATE";

                    einedittext.setText(EIN);

                    nextText.setText("Update");

                    organizationnameedittext.setText(OrgName);

                    searchbutton.setVisibility(View.GONE);

                    buttonLayout.setVisibility(View.VISIBLE);

                    getBusinessVaules();

                } else {

                    nextText.setText("Create");
                }


            }

            return ExemptOrg;

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return null;
    }


    private void NewUserCongratulation() {
        startActivity(new Intent(mContext, NewuserCongratulations.class));

    }


    private void focusListener() {

//        organizationnameedittext.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                if (organizationnameedittext.hasFocus())
//
//                    organizationnameedittext.setTypeface(typeface, Typeface.BOLD);
//
//                else
//
//                    organizationnameedittext.setTypeface(typeface, Typeface.NORMAL);
//                return false;
//            }
//        });

        organizationnameedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    organizationnameedittext.requestFocus();

                    organizationnameedittext.setTypeface(typeface, Typeface.BOLD);
                } else

                    organizationnameedittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });
        einedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    einedittext.requestFocus();

                    einedittext.setTypeface(typeface, Typeface.BOLD);
                } else

                    einedittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        doingbusinessas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)

                    doingbusinessas.setTypeface(typeface, Typeface.BOLD);

                else

                    doingbusinessas.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        OtherDBANames.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)

                    OtherDBANames.setTypeface(typeface, Typeface.BOLD);

                else

                    OtherDBANames.setTypeface(typeface, Typeface.NORMAL);

            }
        });


        addressEdittext1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    addressEdittext1.setTypeface(typeface, Typeface.BOLD);

                else

                    addressEdittext1.setTypeface(typeface, Typeface.NORMAL);

            }
        });
        addresseditText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    addresseditText2.setTypeface(typeface, Typeface.BOLD);

                else

                    addresseditText2.setTypeface(typeface, Typeface.NORMAL);

            }
        });
        stateorprovinceEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)

                    stateorprovinceEdit.setTypeface(typeface, Typeface.BOLD);

                else

                    stateorprovinceEdit.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        cityeditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    cityeditText.setTypeface(typeface, Typeface.BOLD);

                else

                    cityeditText.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        address_phonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    address_phonenumber.setTypeface(typeface, Typeface.BOLD);

                else

                    address_phonenumber.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        zipeditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    zipeditText.setTypeface(typeface, Typeface.BOLD);

                else

                    zipeditText.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        nameofpersonedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)

                    nameofpersonedittext.setTypeface(typeface, Typeface.BOLD);

                else

                    nameofpersonedittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        bookaddress_phonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookaddress_phonenumber.setTypeface(typeface, Typeface.BOLD);

                else

                    bookaddress_phonenumber.setTypeface(typeface, Typeface.NORMAL);

            }
        });


        bookaddressline1edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookaddressline1edittext.setTypeface(typeface, Typeface.BOLD);

                else

                    bookaddressline1edittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        bookaddressline2edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookaddressline2edittext.setTypeface(typeface, Typeface.BOLD);

                else

                    bookaddressline2edittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        bookCityEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookCityEditText.setTypeface(typeface, Typeface.BOLD);

                else

                    bookCityEditText.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        bookstateorprovince.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookstateorprovince.setTypeface(typeface, Typeface.BOLD);

                else

                    bookstateorprovince.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        bookZipCodeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    bookZipCodeEditText.setTypeface(typeface, Typeface.BOLD);

                else

                    bookZipCodeEditText.setTypeface(typeface, Typeface.NORMAL);

            }
        });


        emailaddressedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    emailaddressedittext.setTypeface(typeface, Typeface.BOLD);

                else

                    emailaddressedittext.setTypeface(typeface, Typeface.NORMAL);

            }
        });

        other_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus)

                    other_text.setTypeface(typeface, Typeface.BOLD);

                else

                    other_text.setTypeface(typeface, Typeface.NORMAL);

            }
        });


    }


    private void SetSpinnerInputsFromDB() {

        databasehandler = new DatabaseHandler(mContext);

        formOfOrgList = databasehandler.getFormofOrgNameList();

        ArrayAdapter<String> formOrgSpinnerAdapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, formOfOrgList);

        form_of_organization.setAdapter(formOrgSpinnerAdapter);

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, databasehandler.getCountrynameList());

        countrySpinner.setAdapter(countryAdapter);

        ArrayAdapter<String> bookcountryAdapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, databasehandler.getCountrynameList());


        bookCountry.setAdapter(bookcountryAdapter);

        Log.i("sate name list", "count : " + databasehandler.getStatenameList().size());

        ArrayAdapter<String> bookstateadapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, databasehandler.getStatenameList());

        bookState.setAdapter(bookstateadapter);

        ArrayAdapter<String> stateadapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, databasehandler.getStatenameList());


        stateSpinner.setAdapter(stateadapter);

        try {
            CountryStateAndFormOrganizationURL countryStateAndFormOrganizationURL = new CountryStateAndFormOrganizationURL(mContext);

            StaticDataInterface onasync2 = new StaticDataInterface() {

                @Override
                public void onResultSuccess(final Vector<Vector<String>> returnobject) {

                    countryStateOrganizationVector = returnobject;

                    timezonenamevector = returnobject.get(7);

                    timezonenamesortedlist = new ArrayList<String>(timezonenamevector);

                    handler1.post(orgRunnaable);

                }

            };
            countryStateAndFormOrganizationURL.setOnResultListener(onasync2);

            countryStateAndFormOrganizationURL.execute();
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    android.os.Handler handler1 = new android.os.Handler();

    Runnable orgRunnaable = new Runnable() {

        @Override
        public void run() {
            try {

                setSpinnerAdaptervector(timezonenamesortedlist, timezone);

                timezoneidvector = countryStateOrganizationVector.get(8);

                timezoneidlist = countryStateOrganizationVector.get(8);

                //  timezone.setText(returnobject.get(7).get(0));
            } catch (Exception e) {

                e.printStackTrace();

                new SendException(mContext, e);
            }
        }
    };

    public void SetonclickListener() {

        stateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        countrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        bookState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        bookCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


            }
        });

        allLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                wholecontent.performClick();
                return false;
            }
        });

        logolayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fromDashboard) {

                    InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                    in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                    startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                    ((Activity) mContext).finish();

                }


            }
        });

        searchbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                if (searchbutton.isShown()) {

                    businessLookupModel = new AddBussinessInputModel();

                    if (LookUpValidation()) {

                        searchbutton.setEnabled(false);

                        getValues();

                    }
                }

            }
        });


//

//        logout.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    mDrawerLayout.closeDrawer(menulayout);
//
//                    new AlertDialog.Builder(mContext).setTitle("Log off?").setMessage("Are you sure you want to log off?").setPositiveButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            try {
//
//                                Logout.logout(mContext);
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//
//                                new SendException(mContext, e);
//                            }
//
//                        }
//                    }).setIcon(R.drawable.logoff).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                    new SendException(mContext, e);
//                }
//            }
//        });


        next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ValidationCheck()) {
                    try {

                        InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                        ArrayList<String> address = new ArrayList<String>();

                        address.add(addressEdittext1.getText().toString());

                        address.add(addresseditText2.getText().toString());

                        address.add(cityeditText.getText().toString());


                        if (addressisaddressoutsidecheckbox.isChecked())

                            address.add(stateorprovinceEdit.getText().toString());

                        else

                            address.add(stateSpinner.getText().toString());

                        address.add(countrySpinner.getText().toString());

                        address.add(zipeditText.getText().toString());

                        address.add(String.valueOf(addressisaddressoutsidecheckbox.isChecked()));

                        AppConfigManager.saveOrgAddress(mContext, address);

                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);

                    }

                    next.setEnabled(false);


                    SaveExemptOrganization();
                }

            }
        });

//		iusessncheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener()
//		{
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//			{
//				if(isChecked)
//				{
//					einedittext.setVisibility(View.GONE);
//
//				//	confirmeineditText.setVisibility(View.GONE);
//
//					//ssnedittext.setVisibility(View.VISIBLE);
//
//					//confirmssneditText.setVisibility(View.VISIBLE);
//
//					einssntextview.setText("*SSN");
//
//					Utils.setfirstletterred(einssntextview.getText().toString(), einssntextview);
//
////					confirmeinssntextview.setText("*Confirm SSN");
////
////					Utils.setfirstletterred(confirmeinssntextview.getText().toString(), confirmeinssntextview);
//				}
//				else
//				{
//					//ssnedittext.setVisibility(View.GONE);
//
//					//confirmssneditText.setVisibility(View.GONE);
//
//					einedittext.setVisibility(View.VISIBLE);
//
//				//	confirmeineditText.setVisibility(View.VISIBLE);
//
//					einssntextview.setText("*EIN");
//
//					Utils.setfirstletterred(einssntextview.getText().toString(), einssntextview);
//
////					confirmeinssntextview.setText("*Confirm EIN");
////
////					Utils.setfirstletterred(confirmeinssntextview.getText().toString(), confirmeinssntextview);
//				}
//
//			}
//		});

        form_of_organization.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    form_of_organization.setError("Form of Organization is required");
                } else {

                    form_of_organization.setError(null);

                    if (form_of_organization.getText().toString().contains("Other")) {

                        otherFormOfOrgLayout.setVisibility(View.VISIBLE);

                    }

                }


            }
        });

        countrySpinner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {


                    if (position == 0) {

                        countrySpinner.setError("Country is required");
                    } else {

                        countrySpinner.setError(null);
                    }

                    countryBool = true;

                    countrypos = position;

                    countryid = databasehandler.getCountryIDList().get(position);

//                    if (isbusinessaddresssamecheckbox.isChecked()) {
//
//                        bookCountry.setText(countrySpinner.getText().toString());
//                        bookcountryid = countryid;
//                    }


//                    if (countrySpinner.getText().toString().length() > 30) {
//                        ((TextView) parent.getChildAt(0)).setTextSize(12);
//                    } else {
//                        ((TextView) parent.getChildAt(0)).setTextSize(16);
//                    }
                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }

            }
        });


        addressisaddressoutsidecheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                isbusinessaddresssamecheckbox.setChecked(false);

                if (isChecked) {

                    Log.i("address", "checked");


                    statevertical.setVisibility(View.GONE);

                    Statetextview.setText("*");

                    Utils.setfirstletterred(Statetextview.getText().toString(), Statetextview, mContext);

                    stateorpovinceLayout.setVisibility(View.VISIBLE);

                    timezonevert.setVisibility(View.GONE);

                    countryvert.setVisibility(View.VISIBLE);

                    testId.setVisibility(View.GONE);

                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(16);

                    zipeditText.setHint("ZIP or Postal Code");

                    zipeditText.setFloatingLabelText("ZIP or Postal Code");

                    zipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                    zipeditText.setFilters(maxlengthzip);

                    zipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);


                } else {


                    Log.i("address", "unchecked");

                    statevertical.setVisibility(View.VISIBLE);

                    Statetextview.setText("*");

                    Utils.setfirstletterred(Statetextview.getText().toString(), Statetextview, mContext);

                    stateorpovinceLayout.setVisibility(View.GONE);

                    timezonevert.setVisibility(View.VISIBLE);

                    countryvert.setVisibility(View.GONE);

                    testId.setVisibility(View.VISIBLE);

                    InputFilter[] maxlengthzip = new InputFilter[1];

                    maxlengthzip[0] = new InputFilter.LengthFilter(10);

                    zipeditText.setHint("ZIP Code");

                    zipeditText.setFloatingLabelText("ZIP Code");

                    zipeditText.setFilters(maxlengthzip);

                    zipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);


                    zipeditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
                }
            }
        });

//		isbusinesscheckbox.setOnCheckedChangeListener(new  OnCheckedChangeListener()
//		{
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//			{
//				if(isChecked)
//				{
//					nameofpersontextview.setText("*Name of Business:");
//
//					Utils.setfirstletterred(nameofpersontextview.getText().toString(), nameofpersontextview);
//				}
//				else
//				{
//					nameofpersontextview.setText("*Name of Person:");
//
//					Utils.setfirstletterred(nameofpersontextview.getText().toString(), nameofpersontextview);
//				}
//
//			}
//		});

        addressEdittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addresseditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cityeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        zipeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        stateorprovinceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bookisaddressoutside.setOnCheckedChangeListener(princeUsAddressChecked);

        isbusinessaddresssamecheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {


                    if (isChecked) {

                        if (addressisaddressoutsidecheckbox.isChecked()) {

                            bookisaddressoutside.setChecked(true);

                            bookState_layout.setVisibility(View.GONE);
                            bookstateorprovinceLayout.setVisibility(View.VISIBLE);
                            bookStateTextview.setText("*");
                            Utils.setfirstletterred(bookStateTextview.getText().toString(), bookStateTextview, mContext);
                            bookcountrylayout.setVisibility(View.VISIBLE);
                            booktestId.setVisibility(View.GONE);

                            InputFilter[] maxlengthzip = new InputFilter[1];
                            maxlengthzip[0] = new InputFilter.LengthFilter(16);
                            //   bookZipCodeEditText.setHint("ZIP or Postal Code");

                            bookZipCodeEditText.setHint("ZIP or Postal Code");
                            bookZipCodeEditText.setFloatingLabelText("ZIP or Postal Code");
                            bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                            bookZipCodeEditText.setFilters(maxlengthzip);
                            bookZipCodeEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                            utils.blockWatcher1(bookstateorprovince, stateorprovinceEdit.getText().toString(), princeStateTW);

                            bookCountry.setText(countrySpinner.getText().toString());

                            SetSpinnerInputsFromDB();


                        } else {

                            bookisaddressoutside.setChecked(false);

                            bookState_layout.setVisibility(View.VISIBLE);
                            bookstateorprovinceLayout.setVisibility(View.GONE);
                            bookStateTextview.setText("*");
                            Utils.setfirstletterred(bookStateTextview.getText().toString(), bookStateTextview, mContext);


                            bookcountrylayout.setVisibility(View.GONE);
                            booktestId.setVisibility(View.VISIBLE);
                            InputFilter[] maxlengthzip = new InputFilter[1];
                            maxlengthzip[0] = new InputFilter.LengthFilter(10);
                            bookZipCodeEditText.setHint("ZIP Code");
                            bookZipCodeEditText.setFloatingLabelText("ZIP Code");
                            bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                            bookZipCodeEditText.setFilters(maxlengthzip);
                            bookZipCodeEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                            bookState.setText(stateSpinner.getText().toString());

                            SetSpinnerInputsFromDB();
                        }


                        utils.blockWatcher1(bookaddressline1edittext, addressEdittext1.getText().toString(), princeAddress1TW);

                        utils.blockWatcher1(bookaddressline2edittext, addresseditText2.getText().toString(), princeAddress2TW);

                        utils.blockWatcher1(bookCityEditText, cityeditText.getText().toString(), princeCityTW);


                        utils.blockWatcher1(bookZipCodeEditText, zipeditText.getText().toString(), princeZipTW);


                        utils.blockWatcher1(bookaddress_phonenumber, address_phonenumber.getText().toString(), princePhoneTW);


                    } else {

                        Log.i("same", "unchecked");
                    }


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);

                }


            }
        });

        try {


            bookCountry.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if (position == 0)

                        bookCountry.setError("Country is required");


                    else

                        bookCountry.setError(null);

                    bookCountryBool = true;

                    bookcountryid = databasehandler.getCountryIDList().get(position);


                }
            });
        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);

        }

        try {


            bookState.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if (position == 0)

                        bookState.setError("State is required");

                    else

                        bookState.setError(null);

                    bookstateid = databasehandler.getStateIDList().get(position);

                    bookStateBool = true;
                }
            });
        } catch (Exception e) {

            e.printStackTrace();

            new SendException(mContext, e);

        }

        stateSpinner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                statepos = position;


                try {


                    stateid = databasehandler.getStateIDList().get(position);

                    if (position > 0) {

                        stateSpinner.setError(null);


                        SetTimezoneListFromSID(stateid);
                    }

                    if (position == 0) {

                        stateSpinner.setError("State is required");


                        timezonenamesortedlist = new ArrayList<String>(timezonenamevector);

                        setSpinnerAdaptervector(timezonenamesortedlist, timezone);

                        timezoneidvector = timezoneidlist;

                        timezone.setText("");
                    }


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }

            }
        });

        timezone.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    if (position == 0) {

                        timezone.setError("Time Zone is required");


                    } else {

                        timezone.setError(null);

                    }


                    timezonepos = position;

                    timezoneid = timezoneidvector.get(position);

                    //  timezone.setText(timezonenamesortedlist.get(position));


                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }
            }
        });


//        timezone.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                timezonedialog.show();
//
//            }
//        });

//        stateSpinner.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                statedialog.show();
//            }
//        });


        cancelbutton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                startActivity(new Intent(mContext, DashboardActivity.class).putExtra("TO_LAYOUT", "Dashboard"));

                ((Activity) mContext).finish();


            }

        });


        wholecontent.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        organizationdetails.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        AddressDetails.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });

        BooksareinCareDetails.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
//
//        SigningAuthor.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                return false;
//            }
//        });

    }

    protected boolean LookUpValidation() {


        boolean LookupFlag = true;

        if (einedittext.getText().toString().trim().equalsIgnoreCase("")) {
            LookupFlag = false;

            einedittext.setError("EIN is required");

        } else if (einedittext.getText().toString().length() < 10) {
            LookupFlag = false;

            einedittext.setError("Enter a valid EIN");


        } else if (einedittext.getText().toString().equalsIgnoreCase("00-0000000")) {
            LookupFlag = false;

            einedittext.setError("Enter a valid EIN");

        } else {
            einedittext.setError(null);


        }

        if (organizationnameedittext.getText() != null && organizationnameedittext.getText().toString().trim().length() > 0) {


        } else {

            LookupFlag = false;

            organizationnameedittext.setError("Organization Name is required");
        }


        return LookupFlag;
    }

    protected void SaveExemptOrganization() {

        ExemptOrgURL exemptOrgURL = new ExemptOrgURL(exemptmodel.getExemptorganizationSaveRequest(), mContext);

        ExemptInterface exemptInterface = new ExemptInterface() {

            @Override
            public void onResultSuccess(final ExemptModel exemptModel1) {

                exemptModel = exemptModel1;

                handler1.post(orUrlRunnable);

            }
        };

        exemptOrgURL.setOnResultListener(exemptInterface);

        exemptOrgURL.execute();

    }

    Runnable orUrlRunnable = new Runnable() {
        @Override
        public void run() {
            if (exemptModel.getOS() != null && !exemptModel.getOS().equalsIgnoreCase("null")) {
                if (exemptModel.getOS().equalsIgnoreCase("Success")) {

                    if (exemptModel.getEODId() != null && !exemptModel.getEODId().equalsIgnoreCase("null")) {
                        AppConfigManager.saveEODID(mContext, exemptModel.getEODId());
                    }

                    if (typeofSave != null && typeofSave.equalsIgnoreCase("ADD")) {


                        AppConfigManager.saveBID(mContext, exemptModel.getBId());

                        AppConfigManager.saveReturnRid(mContext, exemptModel.getRID());

                        utils.successMessage(mContext, "Organization Added");


                    } else {


                        utils.successMessage(mContext, "Organization Updated Successfully");

                    }

                    if (typeofSave != null && typeofSave.equalsIgnoreCase("UPDATE")) {

                        if (fromForm) {

                            TaxPageFragment();

                        } else {
                            logolayout.performClick();
                        }

                    } else {

                        TaxPageFragment();
                    }


                } else if (exemptModel.getOS().equalsIgnoreCase("Failure")) {

                    if (exemptModel.getEM() != null && !exemptModel.getEM().equalsIgnoreCase("null")) {

                        if (exemptModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your session is Expired");

                            Logout.logout(mContext);
                        } else {

                            utils.errorMessage(mContext, exemptModel.getEM());

                        }
                    }
                }
            }

            next.setEnabled(true);

        }
    };

    public boolean ValidationCheck() {

        boolean validationflag = true;

        exemptmodel.setAT(AppConfigManager.getAccessToken(mContext));

        exemptmodel.setDID(AppConfigManager.getDID(mContext));

        exemptmodel.setUID(AppConfigManager.getLoggedUid(mContext));

        exemptmodel.setBId(businessId);

        exemptmodel.setTypeofSave(typeofSave);

        if (einedittext.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            einedittext.setError("EIN is required");

        } else {

            exemptmodel.setEin(einedittext.getText().toString());
        }

        if (organizationnameedittext.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            organizationnameedittext.setError("Organization Name is required");


        } else {

            exemptmodel.setOrganizationname(organizationnameedittext.getText().toString());
        }

        exemptmodel.setDoingbusinessas(doingbusinessas.getText().toString());

        exemptmodel.setOtherDBANames(OtherDBANames.getText().toString());


        if (form_of_organization.getText().toString().equalsIgnoreCase("") || form_of_organization.getText().toString().trim().equalsIgnoreCase("--Select an Option--")) {

            form_of_organization.setError("Form of Organization is required");

            validationflag = false;


        } else if (form_of_organization.getText().toString().trim().equalsIgnoreCase("Other")) {

            if (other_text.getText().toString().trim().equalsIgnoreCase("")) {


                validationflag = false;

                other_text.setError("Form of Organization is required");

            } else {

                other_text.setError(null);

                String id = new DatabaseHandler(mContext).getFormOrgIdByName(form_of_organization.getText().toString());

                exemptmodel.setFormoforganization(id);

                exemptmodel.setFormoforganizationOther(other_text.getText().toString());

            }

        } else {

            form_of_organization.setError(null);

            String id = new DatabaseHandler(mContext).getFormOrgIdByName(form_of_organization.getText().toString());

            exemptmodel.setFormoforganization(id);

        }


//		if(iusessncheckbox.isChecked())
//		{
//			exemptmodel.setIdonthaveein("true");
//		}
//		else
//		{
//			exemptmodel.setIdonthaveein("false");
//		}

        if (addressisaddressoutsidecheckbox.isChecked()) {

            exemptmodel.setAddressisOutsideaddress("true");

        } else {

            exemptmodel.setAddressisOutsideaddress("false");

        }


        if (addressEdittext1.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            addressEdittext1.setError("Address Line1 is required");

        } else {

            exemptmodel.setAddressaddress1(addressEdittext1.getText().toString());

        }

        exemptmodel.setAddressaddress2(addresseditText2.getText().toString());

        if (cityeditText.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            cityeditText.setError("City is required");

        } else if (!Utils.isValidCity(cityeditText.getText().toString())) {

            validationflag = false;

            cityeditText.setError("City is required");

        } else {

            exemptmodel.setAddressCity(cityeditText.getText().toString());
        }

        if (stateSpinner.isShown() && !addressisaddressoutsidecheckbox.isChecked()) {

            if (stateSpinner.getText().toString().trim().equalsIgnoreCase("") || stateSpinner.getText().toString().trim().contains("--Select State--")) {

                validationflag = false;

                stateSpinner.setError("State is required");

            } else {

                stateSpinner.setError(null);

                exemptmodel.setAddressStateid(stateid);

            }
        }

        if (stateorpovinceLayout.isShown() && addressisaddressoutsidecheckbox.isChecked()) {

            if (stateorprovinceEdit.getText().toString().equalsIgnoreCase("")) {

                validationflag = false;

                stateorprovinceEdit.setError("State is required");

            } else {


                exemptmodel.setAddressStateorProvince(stateorprovinceEdit.getText().toString().trim());
            }
        } else {

            exemptmodel.setAddressStateorProvince("");

        }


        if (countrySpinner.isShown()) {

            if (countrySpinner.getText().toString().trim().equalsIgnoreCase("") || countrySpinner.getText().toString().trim().equalsIgnoreCase("--Select Country--")) {

                validationflag = false;

                countrySpinner.setError("Country is required");


            } else {

                countrySpinner.setError(null);

                exemptmodel.setAddressCountryId(countryid);

            }
        } else {
            exemptmodel.setAddressCountryId("0");
        }

        if (timezone.isShown()) {

            if (timezone.getText().toString().trim().equalsIgnoreCase("") || timezone.getText().toString().contains("--Select Time Zone--")) {

                validationflag = false;

                timezone.setError("Time Zone is required");

            } else {

                timezoneid = databasehandler.getTimezoneIDFromName(timezone.getText().toString().trim());


                exemptmodel.setAddresstimezoneid(timezoneid);


                timezone.setError(null);
            }
        } else {
            exemptmodel.setAddresstimezoneid("0");
        }

        if (zipeditText.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            if (addressisaddressoutsidecheckbox.isChecked())

                zipeditText.setError("ZIP or Postal Code is required");

            else

                zipeditText.setError("ZIP Code is required");

        } else if (!addressisaddressoutsidecheckbox.isChecked() && !Utils.isValidZipCode(zipeditText.getText().toString())) {

            validationflag = false;

            zipeditText.setError("ZIP Code is required");

        } else {


            exemptmodel.setAddressZipcode(zipeditText.getText().toString());
        }


        if (address_phonenumber.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            address_phonenumber.setError("Phone number is required");

        } else if (!Utils.isValidPhone(address_phonenumber.getText().toString())) {

            validationflag = false;

            address_phonenumber.setError("Phone number is required");

        } else if (Utils.checkForZero(address_phonenumber.getText().toString())) {

            validationflag = false;

            address_phonenumber.setError("Phone number is required");

        } else {

            exemptmodel.setAddress_phonenumber(address_phonenumber.getText().toString());

        }


        if (address_websiteaddress.getText().toString().trim().length() > 0 && !Utils.isValidWebUrl(address_websiteaddress.getText().toString())) {

            validationflag = false;

            address_websiteaddress.setError("Website Address is invalid");


        } else {

            exemptmodel.setWebsiteaddress(address_websiteaddress.getText().toString());
        }

        if (emailaddressedittext.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            emailaddressedittext.setError("Email is required");

        } else if (!Utils.isValidEmail(emailaddressedittext.getText().toString())) {

            validationflag = false;

            emailaddressedittext.setError("Email is required");

        } else {

            exemptmodel.setAddressEmailaddress(emailaddressedittext.getText().toString());
        }

//		if(isbusinesscheckbox.isChecked())
//		{
//			exemptmodel.setIsitBusinesscheckbox("true");
//		}
//		else
//		{
//			exemptmodel.setIsitBusinesscheckbox("false");
//		}

        if (nameofpersonedittext.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            nameofpersonedittext.setError("Name is required");

        } else {

            nameofpersonedittext.setError(null);

            exemptmodel.setNameofPerson(nameofpersonedittext.getText().toString());
        }

        if (bookaddress_phonenumber.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            bookaddress_phonenumber.setError("Phone number is required");

        } else if (!Utils.isValidPhone(bookaddress_phonenumber.getText().toString())) {

            validationflag = false;

            bookaddress_phonenumber.setError("Phone number is required");

        } else if (Utils.checkForZero(bookaddress_phonenumber.getText().toString())) {

            validationflag = false;

            bookaddress_phonenumber.setError("Phone number is required");

        } else {

            exemptmodel.setPhonenumber(bookaddress_phonenumber.getText().toString());

        }


        if (bookisaddressoutside.isChecked()) {

            exemptmodel.setBookisAddressoutSideUS("true");

        } else {

            exemptmodel.setBookisAddressoutSideUS("false");

        }

        if (isbusinessaddresssamecheckbox.isChecked()) {

            exemptmodel.setIsaddresssameasBusiness("true");

        } else {

            exemptmodel.setIsaddresssameasBusiness("false");

        }

        if (bookaddressline1edittext.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            bookaddressline1edittext.setError("Address Line1 is required");

        } else {

            exemptmodel.setBookAddressline1(bookaddressline1edittext.getText().toString());
        }

        exemptmodel.setBookAddressLine2(bookaddressline2edittext.getText().toString());

        if (bookCityEditText.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            bookCityEditText.setError("City is required");

        } else if (!Utils.isValidCity(bookCityEditText.getText().toString())) {

            validationflag = false;

            bookCityEditText.setError("City is required");

        } else {

            exemptmodel.setBookCity(bookCityEditText.getText().toString());
        }

        if (bookState.isShown()) {

            bookstateid = databasehandler.getStateIDFromName(bookState.getText().toString().trim());


            if (bookState.getText().toString().trim().equalsIgnoreCase("") || bookState.getText().toString().equalsIgnoreCase("--Select State--")) {

                validationflag = false;

                bookState.setError("State is required");

            } else {

                bookState.setError(null);

                bookstateid = databasehandler.getStateIDFromName(bookState.getText().toString().trim());

                exemptmodel.setBookStateid(bookstateid);
            }
        } else {
            exemptmodel.setBookStateid("0");
        }

        if (bookstateorprovince.isShown()) {
            if (bookstateorprovince.getText().toString().equalsIgnoreCase("")) {

                validationflag = false;

                bookstateorprovince.setError("State is required");

            } else {

                exemptmodel.setBookStateorProvince(bookstateorprovince.getText().toString());
            }
        } else {
            exemptmodel.setBookStateorProvince("");
        }

        if (bookCountry.isShown()) {
            if (bookCountry.getText().toString().trim().equalsIgnoreCase("") || bookCountry.getText().toString().trim().equalsIgnoreCase("--Select Country--")) {

                validationflag = false;

                bookCountry.setError("Country is required");

            } else {

                bookCountry.setError(null);

                bookcountryid = databasehandler.getCountryIDfromName(bookCountry.getText().toString().trim());

                Log.i("country", "id : " + bookcountryid);

                Log.i("country", "Name : " + bookCountry.getText().toString());

                exemptmodel.setBookCountryId(bookcountryid);
            }
        } else {

            exemptmodel.setBookCountryId("0");
        }

        if (bookZipCodeEditText.getText().toString().equalsIgnoreCase("")) {

            validationflag = false;

            if (bookisaddressoutside.isChecked())

                bookZipCodeEditText.setError("ZIP or Postal Code is required");

            bookZipCodeEditText.setError("ZIP Code is required");

        } else if (!bookisaddressoutside.isChecked() && !Utils.isValidZipCode(bookZipCodeEditText.getText().toString())) {

            validationflag = false;

            bookZipCodeEditText.setError("ZIP Code is required");

        } else {

            exemptmodel.setBookZipcode(bookZipCodeEditText.getText().toString());
        }


        return validationflag;
    }


    public void setSpinnerAdaptervector(final ArrayList<String> list, com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner timezoneSpinner) {

        try {

            if (list != null && list.size() > 0) {
                Log.i("exempt org", "time zone size : " + list.size());

                ArrayAdapter<String> Spinneradapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, list) {

                    public View getView(int position, View convertView, ViewGroup parent) {

                        try {


                            View v = super.getView(position, convertView, parent);
                            TextView tv = ((TextView) v.findViewById(R.id.spinnertext));
                            tv.setSingleLine();
                            tv.setEllipsize(TextUtils.TruncateAt.END);
                            tv.setTextSize(15);

                            tv.setTypeface(typeface);
                            View view = (View) v.findViewById(R.id.divider);

                            if (position == list.size() - 1)
                                view.setVisibility(View.GONE);
                            else
                                view.setVisibility(View.VISIBLE);
                            return v;

                        } catch (Exception e) {

                            e.printStackTrace();
                            new SendException(mContext, e);
                        }

                        return null;
                    }
                };

                timezoneSpinner.setAdapter(Spinneradapter);
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }


    protected void SetTimezoneListFromSID(String Sid) {

        JSONObject obj = new JSONObject();

        JSONObject obj1 = new JSONObject();

        try {
            obj.put("SID", Sid);

            obj1.put("Business", obj);
        } catch (JSONException e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

        Log.i("Timezone Request", obj1.toString());

        Log.i("Timezone URL", ApplicationConfig.GETTIMEZONEDETAILSBYSTATEID);

        TimezoneURL timezoneURL = new TimezoneURL(obj1.toString(), mContext);

        TimeZoneInterface timezoneinterface = new TimeZoneInterface() {

            @Override
            public void onResultSuccess(final Vector<Timezonemodel> message1) {

                message = message1;

                handler1.post(timeRunnable);

            }

        };

        timezoneURL.setOnResultListener(timezoneinterface);

        timezoneURL.execute();
    }

    Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {

            try {
                timezoneidvector = message.get(0).getTimezoneid();

                timezonenamesortedlist = message.get(0).getTimezonetext();

                timezone.setText(message.get(0).getTimezonetext().get(1));

                timezoneid = databasehandler.getTimezoneIDFromName(timezone.getText().toString().trim());

                setSpinnerAdaptervector(message.get(0).getTimezonetext(), timezone);

            } catch (Exception e) {

                e.printStackTrace();

                new SendException(mContext, e);
            }

        }
    };

    private void Initialization() {
        //TextView's

        utils = new Utils();


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);
        DashBoardTitle.setText("ADD EXEMPT ORGANIZATION DETAILS");

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        dashboardlink = (TextView) ((Activity) mContext).findViewById(R.id.Dashboardlink);


        businesslistview = (ImageView) ((Activity) mContext).findViewById(R.id.businesslistview);
        businesslistview.setEnabled(false);
        businesslistview.setVisibility(View.GONE);

        refresh = (ImageView) ((Activity) mContext).findViewById(R.id.refresh);
        refresh.setVisibility(View.GONE);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);
        DashboardOrgDetailsLayout.setVisibility(View.GONE);

        if (EIN != null && OrgName != null) {

            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);

            DashboardOrgEin.setText(EIN);
            DashboardOrgName.setText(OrgName);

        } else {

            DashboardOrgDetailsLayout.setVisibility(View.GONE);

        }

        allLayout = (LinearLayout) ExemptOrg.findViewById(R.id.allLayout);

        buttonLayout = (FrameLayout) ExemptOrg.findViewById(R.id.buttonLayout);

        buttonLayout.setVisibility(View.GONE);

        OrgTitle = (TextView) ExemptOrg.findViewById(R.id.OrgTitle);

        principalTitle = (TextView) ExemptOrg.findViewById(R.id.principalTitle);

        organizationnametextview = (TextView) ExemptOrg.findViewById(R.id.organizationnametextview);

        //	Utils.setfirstletterred(organizationnametextview.getText().toString(), organizationnametextview);

        einssntextview = (TextView) ExemptOrg.findViewById(R.id.einssntextview);

        Utils.setfirstletterred(einssntextview.getText().toString(), einssntextview, mContext);

//		confirmeinssntextview = (TextView) ExemptOrg.findViewById(R.id.confirmeinssntextview);
//
//		Utils.setfirstletterred(confirmeinssntextview.getText().toString(), confirmeinssntextview);

        address1 = (TextView) ExemptOrg.findViewById(R.id.address1);

        Utils.setfirstletterred(address1.getText().toString(), address1, mContext);

        address2 = (TextView) ExemptOrg.findViewById(R.id.address2);

        citytextView = (TextView) ExemptOrg.findViewById(R.id.citytextView);

        Utils.setfirstletterred(citytextView.getText().toString(), citytextView, mContext);

        Statetextview = (TextView) ExemptOrg.findViewById(R.id.Statetextview);

        Utils.setfirstletterred(Statetextview.getText().toString(), Statetextview, mContext);


        Countrytextview = (TextView) ExemptOrg.findViewById(R.id.Countrytextview);

        Utils.setfirstletterred(Countrytextview.getText().toString(), Countrytextview, mContext);

        ziptextView = (TextView) ExemptOrg.findViewById(R.id.ziptextView);

        Utils.setfirstletterred(ziptextView.getText().toString(), ziptextView, mContext);

        Timezonetext = (TextView) ExemptOrg.findViewById(R.id.Timezonetext);

        Utils.setfirstletterred(Timezonetext.getText().toString(), Timezonetext, mContext);


        nameofpersontextview = (TextView) ExemptOrg.findViewById(R.id.nameofpersontextview);

        Utils.setfirstletterred(nameofpersontextview.getText().toString(), nameofpersontextview, mContext);

        phonetextview = (TextView) ExemptOrg.findViewById(R.id.phonetextview);

        Utils.setfirstletterred(phonetextview.getText().toString(), phonetextview, mContext);

        addressline1textview = (TextView) ExemptOrg.findViewById(R.id.addressline1textview);

        Utils.setfirstletterred(addressline1textview.getText().toString(), addressline1textview, mContext);

        addressline2textview = (TextView) ExemptOrg.findViewById(R.id.addressline2textview);

        bookCityTextview = (TextView) ExemptOrg.findViewById(R.id.bookCityTextview);

        Utils.setfirstletterred(bookCityTextview.getText().toString(), bookCityTextview, mContext);

        bookStateTextview = (TextView) ExemptOrg.findViewById(R.id.bookStateTextview);


        Utils.setfirstletterred(bookStateTextview.getText().toString(), bookStateTextview, mContext);


        bookCountryText = (TextView) ExemptOrg.findViewById(R.id.bookCountryText);

        Utils.setfirstletterred(bookCountryText.getText().toString(), bookCountryText, mContext);

        bookZipCodeTextview = (TextView) ExemptOrg.findViewById(R.id.bookZipCodeTextview);

        Utils.setfirstletterred(bookZipCodeTextview.getText().toString(), bookZipCodeTextview, mContext);

        bookstateorprovincetextView = (TextView) ExemptOrg.findViewById(R.id.bookstateorprovincetextView);

        Utils.setfirstletterred(bookstateorprovincetextView.getText().toString(), bookstateorprovincetextView, mContext);

        bookphonetextview = (TextView) ExemptOrg.findViewById(R.id.bookphonetextview);

        Utils.setfirstletterred(bookphonetextview.getText().toString(), bookphonetextview, mContext);

        stateorprovincetextView = (TextView) ExemptOrg.findViewById(R.id.stateorprovincetextView);

        Utils.setfirstletterred(stateorprovincetextView.getText().toString(), stateorprovincetextView, mContext);

        formoforganizationtextView = (TextView) ExemptOrg.findViewById(R.id.formoforganizationtextView);

        Utils.setfirstletterred(formoforganizationtextView.getText().toString(), formoforganizationtextView, mContext);


//		primarynameText = (TextView) ExemptOrg.findViewById(R.id.primarynameText);
//
//		Utils.setfirstletterred(primarynameText.getText().toString(), primarynameText);

//		TitleBusinessText = (TextView) ExemptOrg.findViewById(R.id.TitleBusinessText);
//
//		Utils.setfirstletterred(TitleBusinessText.getText().toString(), TitleBusinessText);

//		daytimephonenumbertextview = (TextView) ExemptOrg.findViewById(R.id.daytimephonenumbertextview);
//
//		Utils.setfirstletterred(daytimephonenumbertextview.getText().toString(), daytimephonenumbertextview);

        cancelbutton = (android.support.v7.widget.CardView) ExemptOrg.findViewById(R.id.cancelbutton);

        next = (android.support.v7.widget.CardView) ExemptOrg.findViewById(R.id.next);


        cancelbuttonText = (TextView) ExemptOrg.findViewById(R.id.cancelbuttonText);
        nextText = (TextView) ExemptOrg.findViewById(R.id.nextText);
        emailaddresstextview = (TextView) ExemptOrg.findViewById(R.id.emailaddresstextview);


        //EditText's

        organizationnameedittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.organizationnameedittext);

        einedittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.einedittext);

        doingbusinessas = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.doingbusinessas);

        OtherDBANames = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.OtherDBANames);


        //confirmeineditText = (EditText) ExemptOrg.findViewById(R.id.confirmeineditText);

        //ssnedittext = (EditText) ExemptOrg.findViewById(R.id.ssnedittext);

        //	confirmssneditText = (EditText) ExemptOrg.findViewById(R.id.confirmssneditText);

        organizationnameedittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.organizationnameedittext);

        //confirmssneditText = (EditText) ExemptOrg.findViewById(R.id.confirmssneditText);


        addressEdittext1 = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.addressEdittext1);

        addresseditText2 = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.addresseditText2);

        cityeditText = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.cityeditText);

        stateorprovinceEdit = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.stateorprovinceEdit);

        zipeditText = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.zipeditText);

        nameofpersonedittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.nameofpersonedittext);

        address_phonenumber = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.address_phonenumber);

        bookaddress_phonenumber = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookaddress_phonenumber);

        bookaddressline1edittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookaddressline1edittext);

        bookaddressline2edittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookaddressline2edittext);

        bookCityEditText = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookCityEditText);

        bookstateorprovince = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookstateorprovince);

        bookZipCodeEditText = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.bookZipCodeEditText);

        address_websiteaddress = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.address_websiteaddress);

        other_text = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.other_text);


        boodAddressLayout = (LinearLayout) ExemptOrg.findViewById(R.id.boodAddressLayout);

        bookStateLayout = (LinearLayout) ExemptOrg.findViewById(R.id.bookStateLayout);

        //FaxNumberEditText = (EditText) ExemptOrg.findViewById(R.id.FaxNumberEditText);

//		primarynameEditText = (EditText) ExemptOrg.findViewById(R.id.primarynameEditText);
//
//		TitleEditText = (EditText) ExemptOrg.findViewById(R.id.TitleEditText);
//
//		primaryphoneEditText = (EditText) ExemptOrg.findViewById(R.id.primaryphoneEditText);

        emailaddressedittext = (com.rengwuxian.materialedittext.MaterialEditText) ExemptOrg.findViewById(R.id.emailaddressedittext);

        if (AppConfigManager.getUserName(mContext) != null) {
            emailaddressedittext.setText(AppConfigManager.getUserName(mContext));
        }


        //CheckBox's

        //iusessncheckbox = (CheckBox) ExemptOrg.findViewById(R.id.iusessncheckbox);

        addressisaddressoutsidecheckbox = (CheckBox) ExemptOrg.findViewById(R.id.addressisaddressoutsidecheckbox);

        //isbusinesscheckbox = (CheckBox) ExemptOrg.findViewById(R.id.isbusinesscheckbox);

        bookisaddressoutside = (CheckBox) ExemptOrg.findViewById(R.id.bookisaddressoutside);

        isbusinessaddresssamecheckbox = (CheckBox) ExemptOrg.findViewById(R.id.isbusinessaddresssamecheckbox);

        //Spinner's

        form_of_organization = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.form_of_organization);

        countrySpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.countrySpinner);

        bookCountry = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.bookCountry);

        bookState = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.bookState);

        stateSpinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.stateSpinner);

        timezone = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) ExemptOrg.findViewById(R.id.timezone);


        //Linear Layout's

        exceptEinLayout = (LinearLayout) ExemptOrg.findViewById(R.id.exceptEinLayout);

        exceptEinLayout.setVisibility(View.GONE);

        principalLayout = (LinearLayout) ExemptOrg.findViewById(R.id.principalLayout);

        principalLayout.setVisibility(View.GONE);

        otherFormOfOrgLayout = (LinearLayout) ExemptOrg.findViewById(R.id.otherFormOfOrgLayout);

        otherFormOfOrgLayout.setVisibility(View.GONE);

        timezonevert = (LinearLayout) ExemptOrg.findViewById(R.id.timezonevert);

        countryvert = (LinearLayout) ExemptOrg.findViewById(R.id.countryvert);

        testId = (LinearLayout) ExemptOrg.findViewById(R.id.testId);

        bookcountrylayout = (LinearLayout) ExemptOrg.findViewById(R.id.bookcountrylayout);

        booktestId = (LinearLayout) ExemptOrg.findViewById(R.id.booktestId);

        bookzipcodelayout = (LinearLayout) ExemptOrg.findViewById(R.id.bookzipcodelayout);

        wholecontent = (LinearLayout) ExemptOrg.findViewById(R.id.wholecontent);

        organizationdetails = (LinearLayout) ExemptOrg.findViewById(R.id.organizationdetails);

        AddressDetails = (LinearLayout) ExemptOrg.findViewById(R.id.AddressDetails);

        BooksareinCareDetails = (LinearLayout) ExemptOrg.findViewById(R.id.BooksareinCareDetails);

        stateorpovinceLayout = (LinearLayout) ExemptOrg.findViewById(R.id.stateorpovinceLayout);

        statevertical = (LinearLayout) ExemptOrg.findViewById(R.id.statevertical);

        bookState_layout = (LinearLayout) ExemptOrg.findViewById(R.id.bookState_layout);

        bookstateorprovinceLayout = (LinearLayout) ExemptOrg.findViewById(R.id.bookstateorprovinceLayout);

        //	SigningAuthor=(LinearLayout)ExemptOrg.findViewById(R.id.SigningAuthor);


        //Button

        searchbutton = (android.support.v7.widget.CardView) ExemptOrg.findViewById(R.id.searchbutton);


    }

    private void setTypeFace() {

        try {
            Typeface type = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");

            OrgTitle.setTypeface(type, Typeface.BOLD);


            principalTitle.setTypeface(type, Typeface.BOLD);

            organizationnametextview.setTypeface(type);
            einssntextview.setTypeface(type);

            address1.setTypeface(type);
            address2.setTypeface(type);
            citytextView.setTypeface(type);
            Statetextview.setTypeface(type);
            address_websiteaddress.setTypeface(type);
            Countrytextview.setTypeface(type);
            ziptextView.setTypeface(type);
            Timezonetext.setTypeface(type);


            nameofpersontextview.setTypeface(type);
            phonetextview.setTypeface(type);
            addressline1textview.setTypeface(type);
            addressline2textview.setTypeface(type);
            bookCityTextview.setTypeface(type);
            bookStateTextview.setTypeface(type);
            bookCountryText.setTypeface(type);
            bookZipCodeTextview.setTypeface(type);


            emailaddresstextview.setTypeface(type);

            cancelbuttonText.setTypeface(type, typeface.BOLD);
            nextText.setTypeface(type, typeface.BOLD);


            // EditText's

            organizationnameedittext.setTypeface(type);
            einedittext.setTypeface(type);
            doingbusinessas.setTypeface(type);
            OtherDBANames.setTypeface(type);

            addressEdittext1.setTypeface(type);
            addresseditText2.setTypeface(type);
            stateorprovinceEdit.setTypeface(type);
            cityeditText.setTypeface(type);
            address_phonenumber.setTypeface(type);
            zipeditText.setTypeface(type);
            nameofpersonedittext.setTypeface(type);
            bookaddress_phonenumber.setTypeface(type);


            bookaddressline1edittext.setTypeface(type);
            bookaddressline2edittext.setTypeface(type);
            bookCityEditText.setTypeface(type);
            bookstateorprovince.setTypeface(type);
            bookZipCodeEditText.setTypeface(type);


            emailaddressedittext.setTypeface(type);
            other_text.setTypeface(type);


            // CheckBox's

            addressisaddressoutsidecheckbox.setTypeface(type);
            bookisaddressoutside.setTypeface(type);
            isbusinessaddresssamecheckbox.setTypeface(type);


            // Spinner's

            form_of_organization.setTypeface(type);
            countrySpinner.setTypeface(type);
            bookCountry.setTypeface(type);
            bookState.setTypeface(type);
            stateSpinner.setTypeface(type);
            timezone.setTypeface(type);


        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }


    }

    //GetBusinessLookupURL result

    OnAsyncResultBusinessLookUP onAsyncResultBusinessLookUP = new OnAsyncResultBusinessLookUP() {
        @Override
        public void onResultSuccess(Vector<AddBussinessInputModel> result) {

            businessLookUpModelVector = new Vector<AddBussinessInputModel>();

            businessLookUpModelVector = result;

            handler.post(businessRunnable);

        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    android.os.Handler handler = new android.os.Handler();

    Runnable businessRunnable = new Runnable() {
        @Override
        public void run() {

            if (businessLookUpModelVector != null && businessLookUpModelVector.size() > 0) {

                searchbutton.setVisibility(View.GONE);

                buttonLayout.setVisibility(View.VISIBLE);

                AddBussinessInputModel model = businessLookUpModelVector.get(0);

                if (model.getOS() != null && model.getOS().equalsIgnoreCase("Success")) {


                    if (model.getOrganizationName() != null && !model.getOrganizationName().equalsIgnoreCase("null"))

                        organizationnameedittext.setText(model.getOrganizationName());

                    if (model.getDoingBusinessas() != null && !model.getDoingBusinessas().equalsIgnoreCase("null"))

                        doingbusinessas.setText(model.getDoingBusinessas());

                    if (model.getDoingBusinessas() != null && !model.getDoingBusinessas().equalsIgnoreCase("null"))

                        doingbusinessas.setText(model.getDoingBusinessas());

                    if (model.getFormOfOrganizationId() != null && !model.getFormOfOrganizationId().equalsIgnoreCase("null")) {

                        if (databasehandler == null)
                            databasehandler = new DatabaseHandler(mContext);


                        String name = databasehandler.getFormOrgNameByID(model.getFormOfOrganizationId());

                        if (name != null && !name.equalsIgnoreCase("null")) {
                            try {

                                form_of_organization.setText(name);

                            } catch (Exception e) {

                                e.printStackTrace();
                            }

                        }

                        if (form_of_organization.getText().toString().equalsIgnoreCase("Other")) {

                            if (model.getFormOfOrganizationOther() != null && !model.getFormOfOrganizationOther().equalsIgnoreCase("null")) {

                                other_text.setText(model.getFormOfOrganizationOther());
                            }

                        }

                    }


                    if (model.getAddress_Address1() != null && !model.getAddress_Address1().equalsIgnoreCase("null")) {

                        addressEdittext1.setText(model.getAddress_Address1());
                    }

                    if (model.getAddress_Address2() != null && !model.getAddress_Address2().equalsIgnoreCase("null")) {

                        addresseditText2.setText(model.getAddress_Address2());
                    }

                    if (model.getAddress_City() != null && !model.getAddress_City().equalsIgnoreCase("null")) {

                        cityeditText.setText(model.getAddress_City());
                    }


                    if (model.getAddress_Phonenumber() != null && !model.getAddress_Phonenumber().equalsIgnoreCase("null")) {

                        address_phonenumber.setText(model.getAddress_Phonenumber());
                    }

                    if (model.getAddress_Websiteaddress() != null && !model.getAddress_Websiteaddress().equalsIgnoreCase("null")) {

                        address_websiteaddress.setText(model.getAddress_Websiteaddress());
                    }

                    if (model.getAddress_Websiteaddress() != null && !model.getAddress_Websiteaddress().equalsIgnoreCase("null")) {

                        address_websiteaddress.setText(model.getAddress_Websiteaddress());
                    }

                    if (model.getAddress_Emailaddress() != null && !model.getAddress_Emailaddress().equalsIgnoreCase("null")) {

                        emailaddressedittext.setText(model.getAddress_Emailaddress());
                    }

                    if (model.getAddress_Zipcode() != null && !model.getAddress_Zipcode().equalsIgnoreCase("null")) {

                        zipeditText.setText(model.getAddress_Zipcode());
                    }


                    if (model.getAddressOutSideus_Address().equalsIgnoreCase("false")) {

                        addressisaddressoutsidecheckbox.setChecked(false);

                        statevertical.setVisibility(View.VISIBLE);

                        stateorpovinceLayout.setVisibility(View.GONE);

                        timezonevert.setVisibility(View.VISIBLE);

                        countryvert.setVisibility(View.GONE);

                        testId.setVisibility(View.VISIBLE);

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(10);

                        zipeditText.setHint("ZIP Code");

                        zipeditText.setFloatingLabelText("ZIP Code");

                        zipeditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                        zipeditText.setFilters(maxlengthzip);


                        if (model.getAddressStateId() != null && !model.getAddressStateId().equalsIgnoreCase("null") && !model.getAddressStateId().equalsIgnoreCase("0")) {

                            if (databasehandler == null)
                                databasehandler = new DatabaseHandler(mContext);

                            String pos = databasehandler.getStatenameFromSID(model.getAddressStateId());

                            if (pos != null && !pos.equalsIgnoreCase("null")) {
                                try {

                                    stateSpinner.setText(pos);

                                    stateid = model.getAddressStateId();


                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }


                        }

                        if (model.getTZID() != null && !model.getTZID().equalsIgnoreCase("null") && !model.getTZID().equalsIgnoreCase("0")) {

                            if (databasehandler == null)
                                databasehandler = new DatabaseHandler(mContext);

                            String pos = databasehandler.getTimezoneFromTzid(model.getTZID());

                            if (pos != null && !pos.equalsIgnoreCase("null")) {
                                try {

                                    timezone.setText(pos);
                                    timezoneid = model.getTZID();
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }


                        } else {

                            if (model.getAddressStateId() != null && !model.getAddressStateId().equalsIgnoreCase("null") && !model.getAddressStateId().equalsIgnoreCase("0")) {

                                SetTimezoneListFromSID(model.getAddressStateId());
                            } else
                                timezone.setText("");
                        }

                    } else {

                        addressisaddressoutsidecheckbox.setChecked(true);

                        statevertical.setVisibility(View.GONE);

                        stateorpovinceLayout.setVisibility(View.VISIBLE);

                        timezonevert.setVisibility(View.GONE);

                        countryvert.setVisibility(View.VISIBLE);

                        testId.setVisibility(View.GONE);


                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(16);


                        zipeditText.setHint("ZIP or Postal Code");

                        zipeditText.setFloatingLabelText("ZIP or Postal Code");

                        zipeditText.setInputType(InputType.TYPE_CLASS_TEXT);

                        zipeditText.setFilters(maxlengthzip);


                        if (model.getAddressCountryId() != null && !model.getAddressCountryId().equalsIgnoreCase("null") && !model.getAddressCountryId().equalsIgnoreCase("0")) {

                            if (databasehandler == null)
                                databasehandler = new DatabaseHandler(mContext);

                            String pos = databasehandler.getCountryNameFromCID(model.getAddressCountryId());

                            if (pos != null && !pos.equalsIgnoreCase("null")) {
                                try {

                                    countrySpinner.setText(pos);

                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }


                        }

                        if (model.getAddress_Stateorprovince() != null && !model.getAddress_Stateorprovince().equalsIgnoreCase("null")) {

                            stateorprovinceEdit.setText(model.getAddress_Stateorprovince());
                        }


                    }


                    if (model.getPrincipalOfcName() != null && !model.getPrincipalOfcName().equalsIgnoreCase("null")) {

                        nameofpersonedittext.setText(model.getPrincipalOfcName());
                    }


                    if (model.getPrincipalAddress1() != null && !model.getPrincipalAddress1().equalsIgnoreCase("null")) {

                        utils.blockWatcher1(bookaddressline1edittext, model.getPrincipalAddress1(), princeAddress1TW);

                    }

                    if (model.getPrincipalAddress2() != null && !model.getPrincipalAddress2().equalsIgnoreCase("null")) {

                        utils.blockWatcher1(bookaddressline2edittext, model.getPrincipalAddress2(), princeAddress2TW);

                    }


                    if (model.getPrincipalCity() != null && !model.getPrincipalCity().equalsIgnoreCase("null")) {

                        utils.blockWatcher1(bookCityEditText, model.getPrincipalCity(), princeCityTW);

                    }
                    if (model.getPrincipalZipcode() != null && !model.getPrincipalZipcode().equalsIgnoreCase("null")) {


                        utils.blockWatcher1(bookZipCodeEditText, model.getPrincipalZipcode(), princeZipTW);

                    }

                    if (model.getPrincipal_Address_Same_As_Business().equalsIgnoreCase("false")) {

                        isbusinessaddresssamecheckbox.setChecked(false);
                    } else {

                        isbusinessaddresssamecheckbox.setChecked(true);


                    }
                    if (model.getPrincipal_Address_Outside_Us().equalsIgnoreCase("false")) {

                        bookisaddressoutside.setChecked(false);

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(10);

                        bookZipCodeEditText.setHint("ZIP Code");

                        bookZipCodeEditText.setFloatingLabelText("ZIP Code");

                        bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                        bookZipCodeEditText.setFilters(maxlengthzip);


                        bookState_layout.setVisibility(View.VISIBLE);

                        bookstateorprovinceLayout.setVisibility(View.GONE);

                        bookcountrylayout.setVisibility(View.GONE);

                        booktestId.setVisibility(View.VISIBLE);

                        if (model.getPrincipalStateId() != null && !model.getPrincipalStateId().equalsIgnoreCase("null")) {

                            if (databasehandler == null)
                                databasehandler = new DatabaseHandler(mContext);

                            String pos = databasehandler.getStatenameFromSID(model.getPrincipalStateId());

                            if (pos != null && !pos.equalsIgnoreCase("null")) {
                                try {

                                    bookState.setText(pos);

                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }


                        }


                    } else {


                        isbusinessaddresssamecheckbox.setChecked(true);


                        bookState_layout.setVisibility(View.GONE);

                        InputFilter[] maxlengthzip = new InputFilter[1];

                        maxlengthzip[0] = new InputFilter.LengthFilter(16);

                        bookZipCodeEditText.setHint("ZIP or Postal Code");

                        bookZipCodeEditText.setFloatingLabelText("ZIP or Postal Code");

                        bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_TEXT);

                        bookZipCodeEditText.setFilters(maxlengthzip);


                        bookstateorprovinceLayout.setVisibility(View.VISIBLE);

                        bookcountrylayout.setVisibility(View.VISIBLE);

                        booktestId.setVisibility(View.GONE);

                        if (model.getPrincipalCountryId() != null && !model.getPrincipalCountryId().equalsIgnoreCase("null") && !model.getPrincipalCountryId().equalsIgnoreCase("0")) {

                            if (databasehandler == null)
                                databasehandler = new DatabaseHandler(mContext);

                            String pos = databasehandler.getCountryNameFromCID(model.getPrincipalCountryId());

                            if (pos != null && !pos.equalsIgnoreCase("null")) {
                                try {

                                    bookCountry.setText(pos);

                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }


                        }

                        if (model.getPrincipalStateorProvince() != null && !model.getPrincipalStateorProvince().equalsIgnoreCase("null")) {

                            utils.blockWatcher1(bookstateorprovince, model.getPrincipalStateorProvince(), princeStateTW);

                        }


                    }

                    if (model.getPrincipalOfcPhonenumber() != null && !model.getPrincipalOfcPhonenumber().equalsIgnoreCase("null")) {


                        utils.blockWatcher1(bookaddress_phonenumber, model.getPrincipalOfcPhonenumber(), princePhoneTW);


                    }
                } else if (model.getOS().equalsIgnoreCase("Failure")) {

                    if (model.getEM() != null && !model.getEM().equalsIgnoreCase("null")) {

                        if (model.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your session is Expired");

                            Logout.logout(mContext);
                        } else {

                            utils.errorMessage(mContext, model.getEM());

                        }
                    }
                }


                organizationnametextview.setVisibility(View.VISIBLE);

                searchbutton.setEnabled(true);

                exceptEinLayout.setVisibility(View.VISIBLE);

                principalLayout.setVisibility(View.VISIBLE);
            }
            SetSpinnerInputsFromDB();

            // SetTimezoneListFromSID(stateid);
        }


    };

    private void getValues() {


        businessLookupModel.setAT(AppConfigManager.getAccessToken(mContext));

        businessLookupModel.setDID(AppConfigManager.getDID(mContext));

        businessLookupModel.setUId(AppConfigManager.getLoggedUid(mContext));

        businessLookupModel.setEin(einedittext.getText().toString());

        GetBusinessLookupURL getBusinessLookupURL = new GetBusinessLookupURL(businessLookupModel.getJsonObjBusinessLookUP(), mContext);

        getBusinessLookupURL.setOnResultListener(onAsyncResultBusinessLookUP);

        getBusinessLookupURL.execute();


    }

    private void getBusinessVaules() {


        businessLookupModel.setAT(AppConfigManager.getAccessToken(mContext));

        businessLookupModel.setDID(AppConfigManager.getDID(mContext));

        businessLookupModel.setUId(AppConfigManager.getLoggedUid(mContext));

        businessLookupModel.setBId(businessId);

        GetBusinessListDetailURL getBusinessLookupURL = new GetBusinessListDetailURL(businessLookupModel.getBusinessDetail(), mContext);

        getBusinessLookupURL.setOnResultListener(asyncBusinessList);

        getBusinessLookupURL.execute();


    }

    GetBusinessListDetailURL.OnAsyncResultGetBusinessListDetailURL asyncBusinessList = new GetBusinessListDetailURL.OnAsyncResultGetBusinessListDetailURL() {

        @Override
        public void onResultSuccess(Vector<AddBussinessInputModel> message) {

            businessLookUpModelVector = new Vector<AddBussinessInputModel>();

            businessLookUpModelVector = message;

            handler.post(businessRunnable);
        }

        @Override
        public void onResultFail(int resultCode, String errorMessage) {

        }
    };

    private void TaxPageFragment() {

        DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);

        DashboardOrgEin.setText(einedittext.getText().toString().trim());

        DashboardOrgName.setText(organizationnameedittext.getText().toString());

        AppConfigManager.saveFlag(mContext, 0);

        AppConfigManager.saveBusinessNameEIN(mContext, organizationnameedittext.getText().toString(), einedittext.getText().toString().trim());

        Fragment newFragment = new CommonTaxFragment(mContext);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.wholevertical, newFragment);

        transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }

    TextWatcher address1TW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher address2TW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher cityTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher stateTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher zipTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher phoneTW = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("test", "phone text");

            Utils.HideError(address_phonenumber);

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            utils.MaskingPhone(mContext, address_phonenumber);
        }
    };

    TextWatcher princeAddress1TW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("test", "add text");

            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher princeAddress2TW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("test", "add2 text");

            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher princeCityTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("test", "city text");

            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher princeStateTW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("test", "state text");

            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher princeZipTW = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Utils.HideError(bookZipCodeEditText);


            Log.i("test", "zip text");
            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!bookisaddressoutside.isChecked()) {
                utils.maskingzipcode(mContext, bookZipCodeEditText);
            }
        }
    };

    TextWatcher princePhoneTW = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Log.i("test", "phone text");

            bookaddress_phonenumber.setError(null);

            if (isbusinessaddresssamecheckbox.isChecked())

                isbusinessaddresssamecheckbox.setChecked(false);

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            utils.MaskingPhone(mContext, bookaddress_phonenumber);
        }
    };

    OnCheckedChangeListener princeUsAddressChecked = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            if (isChecked) {

                Log.i("price", "checked");

                bookState_layout.setVisibility(View.GONE);

                Log.i("price", "checked 1");

                bookstateorprovinceLayout.setVisibility(View.VISIBLE);

                Log.i("price", "checked 2");

                bookStateTextview.setText("*");

                Log.i("price", "checked 3");

                Utils.setfirstletterred(bookStateTextview.getText().toString(), bookStateTextview, mContext);

                Log.i("price", "checked 4");

                bookcountrylayout.setVisibility(View.VISIBLE);
                Log.i("price", "checked 5");


                booktestId.setVisibility(View.GONE);

                Log.i("price", "checked 6");

                InputFilter[] maxlengthzip = new InputFilter[1];

                Log.i("price", "checked 7");

                maxlengthzip[0] = new InputFilter.LengthFilter(16);
                //   bookZipCodeEditText.setHint("ZIP or Postal Code");

                bookZipCodeEditText.setHint("ZIP or Postal Code");

                Log.i("price", "checked 8");

                bookZipCodeEditText.setFloatingLabelText("ZIP or Postal Code");

                Log.i("price", "checked 9");

                bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_TEXT);

                Log.i("price", "checked 10");

                bookZipCodeEditText.setFilters(maxlengthzip);

                Log.i("price", "checked 11");

                bookZipCodeEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

                Log.i("price", "checked 12");

            } else {

                Log.i("prince", "unchecked");

                bookState_layout.setVisibility(View.VISIBLE);

                bookstateorprovinceLayout.setVisibility(View.GONE);

                bookStateTextview.setText("*");

                Utils.setfirstletterred(bookStateTextview.getText().toString(), bookStateTextview, mContext);


                bookcountrylayout.setVisibility(View.GONE);

                booktestId.setVisibility(View.VISIBLE);

                InputFilter[] maxlengthzip = new InputFilter[1];

                maxlengthzip[0] = new InputFilter.LengthFilter(10);

                bookZipCodeEditText.setHint("ZIP Code");

                bookZipCodeEditText.setFloatingLabelText("ZIP Code");

                bookZipCodeEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

                bookZipCodeEditText.setFilters(maxlengthzip);

                bookZipCodeEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }
        }
    };

    OnCheckedChangeListener UsAddressChecked = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };


    private void Textwatchers() {
        einedittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utils.HideError(einedittext);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                utils.MaskingEin(mContext, einedittext);
            }
        });

        address_phonenumber.addTextChangedListener(phoneTW);


        bookaddress_phonenumber.addTextChangedListener(princePhoneTW);


        bookZipCodeEditText.addTextChangedListener(princeZipTW);

        bookaddressline1edittext.addTextChangedListener(princeAddress1TW);


        bookaddressline2edittext.addTextChangedListener(princeAddress2TW);

        bookCityEditText.addTextChangedListener(princeCityTW);


        bookstateorprovince.addTextChangedListener(princeStateTW);

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
                if (!addressisaddressoutsidecheckbox.isChecked()) {
                    utils.maskingzipcode(mContext, zipeditText);
                }

            }
        });

    }


}
