package com.span.expressextension8868.controller.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.mtp.MtpObjectInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.ReturnModel;
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
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL;
import com.span.expressextension8868.webservice.webservices.SaveTaxYearURL;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Created by STS-099 on 11/18/2015.
 */
public class NewTaxYearFragment extends Fragment {

    Context mContext;

    View CommonTaxView;

    Utils utils;

    ReturnModel returnModel;

    String EIN, BN, Mode;

    int SelectionId = 0, formSelectedPosition = 0;

    String RID = "0", BId = "0", selectedFormId;

    String CurrentTaxYear, PTY;

    boolean currenttaxyearFlag = true, prevcurrenttaxyearFlag;

    int actualtotaldays, usertotaldays;

    //

    LinearLayout rootlayout, logolayout, DashboardOrgDetailsLayout;

    TextView DashBoardTitle, DashboardOrgEin, DashboardOrgName;

    //


    android.support.v7.widget.CardView taxyearCancelbutton, taxyearnext;

    TextView text1, text2, taxyearCancelbuttonText, taxyearnextText;

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner formspinner;

    RadioGroup taxYearRadioGroup, currentYearRadioGroup;

    RadioButton currentaxyearradio, prevtaxyearradio, fiscaltaxyearTextView, currenttaxyearTextview;

    LinearLayout form, orgtaxyearlayout, corporationlinear, prevtaxyearlayout, reasonlayout, prevreasonlayout;


    TextView selectformtextview, whatisyourtaxyeartextview, selectoption, prevwhatisyourtaxyeartextview, prevselectoption;

    com.rengwuxian.materialedittext.MaterialEditText begindateTextview, enddateTextview, prevbegindateTextview, prevenddateTextview;

    CheckBox IntialcheckBox, FinalReturncheckBox, AccountingCheckbox, prevIntialcheckBox, prevFinalReturncheckBox, prevAccountingCheckbox;

    //

    DatabaseHandler databaseHandler;

    ArrayList<String> formList;

    // progress

    private ProgressDialog pd;

    //save

    ReturnModel returnmodelSave, savedReturnModel;

    public NewTaxYearFragment(Context mContext, int SelectionId) {

        this.mContext = mContext;

        this.SelectionId = SelectionId;


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

        try {

            EIN = AppConfigManager.getEIN(mContext);

            BN = AppConfigManager.getBusinessname(mContext);

            Mode = AppConfigManager.getMode(mContext);

            this.RID = AppConfigManager.getReturnRID(mContext);

            this.BId = AppConfigManager.getBID(mContext);


            CommonTaxView = inflater.inflate(R.layout.taxyear, container, false);

            Initialization();

            onClick();

            Overridefonts.overrideFonts(mContext, rootlayout);

            setTypeFace();


            // addRightFragment();

            addLeftFragment(0);


            loadFormList();


            GetReturnDetailsByID();


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

        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);


        DashBoardTitle = (TextView) ((Activity) mContext).findViewById(R.id.DashBoardTitle);

        logolayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.logolayout);

        DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
        DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
        DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

        DashBoardTitle.setText("FORM SELECTION & TAX YEAR");

        if (EIN != null && BN != null) {

            DashboardOrgEin.setText(EIN);

            DashboardOrgName.setText(BN);
        }

        rootlayout = (LinearLayout) CommonTaxView.findViewById(R.id.rootlayout);

        form = (LinearLayout) CommonTaxView.findViewById(R.id.rootlayout);
        taxyearCancelbutton = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.taxyearCancelbutton);

        taxyearnext = (android.support.v7.widget.CardView) CommonTaxView.findViewById(R.id.taxyearnext);

        formspinner = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) CommonTaxView.findViewById(R.id.formspinner);

        text1 = (TextView) CommonTaxView.findViewById(R.id.text1);


        text2 = (TextView) CommonTaxView.findViewById(R.id.text2);


        taxyearCancelbuttonText = (TextView) CommonTaxView.findViewById(R.id.taxyearCancelbuttonText);

        taxyearnextText = (TextView) CommonTaxView.findViewById(R.id.taxyearnextText);

        //taxyear

        taxYearRadioGroup = (RadioGroup) CommonTaxView.findViewById(R.id.taxYearRadioGroup);

        currentaxyearradio = (RadioButton) CommonTaxView.findViewById(R.id.currentaxyearradio);

        prevtaxyearradio = (RadioButton) CommonTaxView.findViewById(R.id.prevtaxyearradio);

        corporationlinear = (LinearLayout) CommonTaxView.findViewById(R.id.corporationlinear);

        prevtaxyearlayout = (LinearLayout) CommonTaxView.findViewById(R.id.prevtaxyearlayout);

        currentaxyearradio.setChecked(true);

        prevtaxyearradio.setChecked(false);

        corporationlinear.setVisibility(View.VISIBLE);

        prevtaxyearlayout.setVisibility(View.GONE);

        selectformtextview = (TextView) CommonTaxView.findViewById(R.id.selectformtextview);

        Utils.setfirstletterred(selectformtextview.getText().toString(), selectformtextview, mContext);

        whatisyourtaxyeartextview = (TextView) CommonTaxView.findViewById(R.id.whatisyourtaxyeartextview);

        Utils.setfirstletterred(whatisyourtaxyeartextview.getText().toString(), whatisyourtaxyeartextview, mContext);

        selectoption = (TextView) CommonTaxView.findViewById(R.id.selectoption);

        Utils.setfirstletterred(selectoption.getText().toString(), selectoption, mContext);

        prevwhatisyourtaxyeartextview = (TextView) CommonTaxView.findViewById(R.id.prevwhatisyourtaxyeartextview);

        Utils.setfirstletterred(prevwhatisyourtaxyeartextview.getText().toString(), prevwhatisyourtaxyeartextview, mContext);

        prevselectoption = (TextView) CommonTaxView.findViewById(R.id.prevselectoption);

        Utils.setfirstletterred(prevselectoption.getText().toString(), prevselectoption, mContext);


        currentYearRadioGroup = (RadioGroup) CommonTaxView.findViewById(R.id.currentYearRadioGroup);

        currenttaxyearTextview = (RadioButton) CommonTaxView.findViewById(R.id.currenttaxyearTextview);

        fiscaltaxyearTextView = (RadioButton) CommonTaxView.findViewById(R.id.fiscaltaxyearTextView);


        begindateTextview = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.begindateTextview);

        enddateTextview = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.enddateTextview);

        begindateTextview.setEnabled(false);

        enddateTextview.setEnabled(false);

        begindateTextview.setKeyListener(null);

        enddateTextview.setKeyListener(null);

        prevbegindateTextview = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.prevbegindateTextview);

        prevenddateTextview = (com.rengwuxian.materialedittext.MaterialEditText) CommonTaxView.findViewById(R.id.prevenddateTextview);

        prevbegindateTextview.setKeyListener(null);

        prevbegindateTextview.setKeyListener(null);

        reasonlayout = (LinearLayout) CommonTaxView.findViewById(R.id.reasonlayout);

        reasonlayout.setVisibility(View.GONE);

        prevreasonlayout = (LinearLayout) CommonTaxView.findViewById(R.id.prevreasonlayout);

        prevreasonlayout.setVisibility(View.GONE);

        orgtaxyearlayout = (LinearLayout) CommonTaxView.findViewById(R.id.orgtaxyearlayout);
        orgtaxyearlayout.setVisibility(View.GONE);


        IntialcheckBox = (CheckBox) CommonTaxView.findViewById(R.id.IntialcheckBox);

        FinalReturncheckBox = (CheckBox) CommonTaxView.findViewById(R.id.FinalReturncheckBox);

        AccountingCheckbox = (CheckBox) CommonTaxView.findViewById(R.id.AccountingCheckbox);

        prevIntialcheckBox = (CheckBox) CommonTaxView.findViewById(R.id.prevIntialcheckBox);

        prevFinalReturncheckBox = (CheckBox) CommonTaxView.findViewById(R.id.prevFinalReturncheckBox);

        prevAccountingCheckbox = (CheckBox) CommonTaxView.findViewById(R.id.prevAccountingCheckbox);


    }

    private void onClick() {

        rootlayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });


        taxyearCancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


                Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                transaction.replace(R.id.wholevertical, newFragment);

                transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                transaction.commit();


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

        taxyearnext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    TaxPageFragment();

                } else {
                    if (ValidationCheck()) {
                        SaveTaxYearDetails();
                    }
                }
            }
        });

        formspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                formSelectedPosition = position;

                formspinner.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NONE);

                if (position == 0) {

                    orgtaxyearlayout.setVisibility(View.GONE);

                    formspinner.setError("Please select an option");

                } else {

                    orgtaxyearlayout.setVisibility(View.VISIBLE);

                    selectedFormId = null;

                    if (databaseHandler == null)

                        databaseHandler = new DatabaseHandler(mContext);

                    selectedFormId = databaseHandler.getFormCodeByFormName(formList.get(position));

                    if (selectedFormId != null && selectedFormId.equalsIgnoreCase("8") || selectedFormId.equalsIgnoreCase("10")) {

                        fiscaltaxyearTextView.setVisibility(View.GONE);

                        prevtaxyearradio.setVisibility(View.GONE);

                        prevtaxyearlayout.setVisibility(View.GONE);

                        corporationlinear.setVisibility(View.VISIBLE);

                        currentaxyearradio.setChecked(true);

                    } else {

                        fiscaltaxyearTextView.setVisibility(View.VISIBLE);

                        prevtaxyearradio.setVisibility(View.VISIBLE);
                    }


                }


            }
        });

        taxYearRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.currentaxyearradio:

                        corporationlinear.setVisibility(View.VISIBLE);

                        prevtaxyearlayout.setVisibility(View.GONE);

                        prevcurrenttaxyearFlag = true;


                        // TODO: 11/18/2015
                        break;

                    case R.id.prevtaxyearradio:

                        corporationlinear.setVisibility(View.GONE);

                        prevtaxyearlayout.setVisibility(View.VISIBLE);

                        prevcurrenttaxyearFlag = false;

                        //// TODO: 11/18/2015
                        break;

                    default:

                        corporationlinear.setVisibility(View.VISIBLE);

                        prevtaxyearlayout.setVisibility(View.GONE);

                        prevcurrenttaxyearFlag = true;

                        // TODO: 11/18/2015
                        break;

                }

            }
        });

        currentYearRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.currenttaxyearTextview:

                        currenttaxyearFlag = true;

                        begindateTextview.setClickable(false);

                        enddateTextview.setClickable(false);

                        begindateTextview.setEnabled(false);

                        enddateTextview.setEnabled(false);

                        reasonlayout.setVisibility(View.GONE);

                        IntialcheckBox.setChecked(false);

                        FinalReturncheckBox.setChecked(false);

                        AccountingCheckbox.setChecked(false);


                        if (CurrentTaxYear != null && !CurrentTaxYear.equalsIgnoreCase("null")) {

                            begindateTextview.setText("01/01/" + CurrentTaxYear);

                            enddateTextview.setText("12/31/" + CurrentTaxYear);
                        }
                        break;

                    case R.id.fiscaltaxyearTextView:

                        currenttaxyearFlag = false;

                        begindateTextview.setClickable(true);

                        enddateTextview.setClickable(true);

                        begindateTextview.setEnabled(true);

                        enddateTextview.setEnabled(true);
                        break;

                    default:

                        begindateTextview.setClickable(false);

                        enddateTextview.setClickable(false);

                        begindateTextview.setEnabled(false);

                        enddateTextview.setEnabled(false);

                        reasonlayout.setVisibility(View.GONE);

                        IntialcheckBox.setChecked(false);

                        FinalReturncheckBox.setChecked(false);

                        AccountingCheckbox.setChecked(false);


                        if (CurrentTaxYear != null && !CurrentTaxYear.equalsIgnoreCase("null")) {

                            begindateTextview.setText("01/01/" + CurrentTaxYear);

                            enddateTextview.setText("12/31/" + CurrentTaxYear);
                        }
                        break;
                }

            }
        });


        begindateTextview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                try {


                    final Calendar c = Calendar.getInstance();

                    final Calendar cMax = Calendar.getInstance();

                    c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 1);
                    c.set(Calendar.MONTH, 0);
                    c.set(Calendar.DATE, 1);
                    cMax.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 1);
                    cMax.set(Calendar.MONTH, Calendar.getInstance().getMaximum(Calendar.MONTH));
                    cMax.set(Calendar.DATE, Calendar.getInstance().getMaximum(Calendar.DATE));


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

                                    Calendar c = Calendar.getInstance();


                                    Date mindate = new Date(begindateTextview.getText().toString());

                                    c.setTime(mindate);

                                    c.add(Calendar.YEAR, 1);

                                    c.add(Calendar.DATE, -1);

                                    enddateTextview.setText(String.format("%02d", c.get(Calendar.MONTH) + 1) + "/" + String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + "/" + c.get(Calendar.YEAR));

                                }
                            },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setMinDate(c);
                    dpd.setMaxDate(cMax);

                    dpd.setYearRange(c.get(Calendar.YEAR), cMax.get(Calendar.YEAR));

                    dpd.setAccentColor(mContext.getResources().getColor(R.color.listview_active_Fg));

                    dpd.setTitle("Select Previous Tax Year Begin Date");

                    dpd.show(((Activity) mContext).getFragmentManager(), "Datepickerdialog");


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }


            }
        });

        enddateTextview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                try {


                    Date mindate = new Date(begindateTextview.getText().toString());

                    Date maxdate = new Date(enddateTextview.getText().toString());

                    Calendar c1 = Calendar.getInstance();

                    c1.setTime(mindate);

                    c1.add(Calendar.YEAR, 1);

                    c1.add(Calendar.DATE, -1);

                    Date dayCal = new Date(begindateTextview.getText().toString());

                    dayCal.setTime(c1.getTimeInMillis());


                    long totaldays = dayCal.getTime() - mindate.getTime();

                    actualtotaldays = (int) TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS);

                    final Calendar c = Calendar.getInstance();

                    final Calendar cMax = Calendar.getInstance();

                    c.setTime(mindate);

                    cMax.setTime(maxdate);

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
                                    enddateTextview.setText(date);

                                    Date mindate = new Date(begindateTextview.getText().toString());

                                    Date maxdate = new Date(enddateTextview.getText().toString());

                                    long userselectedtotaldays = maxdate.getTime() - mindate.getTime();

                                    usertotaldays = (int) TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS);

                                    Log.i("day", "act days : " + String.valueOf(actualtotaldays));

                                    Log.i("day", "user days : " + String.valueOf(usertotaldays));

                                    if (usertotaldays < actualtotaldays) {

                                        reasonlayout.setVisibility(View.VISIBLE);

                                    } else {

                                        reasonlayout.setVisibility(View.GONE);

                                    }
                                }
                            },
                            cMax.get(Calendar.YEAR),
                            cMax.get(Calendar.MONTH),
                            cMax.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setMinDate(c);
                    dpd.setMaxDate(c1);


                    dpd.setYearRange(c.get(Calendar.YEAR), cMax.get(Calendar.YEAR));

                    dpd.setAccentColor(mContext.getResources().getColor(R.color.listview_active_Fg));

                    dpd.setTitle("Select Previous Tax Year End Date");

                    dpd.show(((Activity) mContext).getFragmentManager(), "Datepickerdialog");


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }

            }
        });

        prevbegindateTextview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {


                try {


                    final Calendar c = Calendar.getInstance();

                    final Calendar cMax = Calendar.getInstance();

                    c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 2);
                    c.set(Calendar.MONTH, 0);
                    c.set(Calendar.DATE, 1);
                    cMax.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - 2);
                    cMax.set(Calendar.MONTH, Calendar.getInstance().getMaximum(Calendar.MONTH));
                    cMax.set(Calendar.DATE, Calendar.getInstance().getMaximum(Calendar.DATE));


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
                                    prevbegindateTextview.setText(date);

                                    Calendar c = Calendar.getInstance();


                                    Date mindate = new Date(prevbegindateTextview.getText().toString());

                                    c.setTime(mindate);

                                    c.add(Calendar.YEAR, 1);

                                    c.add(Calendar.DATE, -1);

                                    prevenddateTextview.setText(String.format("%02d", c.get(Calendar.MONTH) + 1) + "/" + String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) + "/" + c.get(Calendar.YEAR));

                                }
                            },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setMinDate(c);
                    dpd.setMaxDate(cMax);

                    dpd.setYearRange(c.get(Calendar.YEAR), cMax.get(Calendar.YEAR));

                    dpd.setAccentColor(mContext.getResources().getColor(R.color.listview_active_Fg));

                    dpd.setTitle("Select Previous Tax Year Begin Date");

                    dpd.show(((Activity) mContext).getFragmentManager(), "Datepickerdialog");


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }


            }
        });

        prevenddateTextview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                try {


                    Date mindate = new Date(prevbegindateTextview.getText().toString());

                    Date maxdate = new Date(prevenddateTextview.getText().toString());


                    Calendar c1 = Calendar.getInstance();

                    c1.setTime(mindate);

                    c1.add(Calendar.YEAR, 1);

                    c1.add(Calendar.DATE, -1);


                    Date dayCal = new Date(prevbegindateTextview.getText().toString());

                    dayCal.setTime(c1.getTimeInMillis());

                    long totaldays = dayCal.getTime() - mindate.getTime();

                    actualtotaldays = (int) TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS);

                    final Calendar c = Calendar.getInstance();

                    final Calendar cMax = Calendar.getInstance();


                    c.setTime(mindate);

                    cMax.setTime(maxdate);

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
                                    prevenddateTextview.setText(date);

                                    Date mindate = new Date(prevbegindateTextview.getText().toString());

                                    Date maxdate = new Date(prevenddateTextview.getText().toString());

                                    long userselectedtotaldays = maxdate.getTime() - mindate.getTime();

                                    usertotaldays = (int) TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS);

                                    if (usertotaldays < actualtotaldays) {

                                        prevreasonlayout.setVisibility(View.VISIBLE);

                                    } else {

                                        prevreasonlayout.setVisibility(View.GONE);

                                    }

                                }
                            },
                            cMax.get(Calendar.YEAR),
                            cMax.get(Calendar.MONTH),
                            cMax.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setMinDate(c);
                    dpd.setMaxDate(c1);


                    dpd.setYearRange(c.get(Calendar.YEAR), cMax.get(Calendar.YEAR));

                    dpd.setAccentColor(mContext.getResources().getColor(R.color.listview_active_Fg));

                    dpd.setTitle("Select Previous Tax Year End Date");

                    dpd.show(((Activity) mContext).getFragmentManager(), "Datepickerdialog");


                } catch (Exception e) {

                    e.printStackTrace();

                    new SendException(mContext, e);
                }


            }
        });

    }


    private void addLeftFragment(int sId) {

//// TODO: 11/18/2015

        Fragment newFragment = new TaxLeftFragment(mContext, sId);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.leftFragment, newFragment);


// Commit the transaction
        transaction.commit();

    }

    private void loadFormList() {

        if (databaseHandler == null)

            databaseHandler = new DatabaseHandler(mContext);

        formList = databaseHandler.getFormList("FormName");

        if (formList != null && formList.size() > 0) {


            ArrayAdapter<String> bookstateadapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, formList)

            {

                public View getView(int position, View convertView, ViewGroup parent) {

                    try {


                        View v = super.getView(position, convertView, parent);

                        TextView tv = ((TextView) v.findViewById(R.id.spinnertext));

                        View divider = (View) v.findViewById(R.id.divider);

                        if (position == formList.size() - 1)
                            divider.setVisibility(View.GONE);
                        else
                            divider.setVisibility(View.VISIBLE);

                        tv.setSingleLine();
                        tv.setEllipsize(TextUtils.TruncateAt.END);
                        tv.setTextSize(15);
                        tv.setTypeface(new TypeFaceClass(mContext).NotoSans_Regular());

                        return v;

                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                    return null;
                }


            };
            formspinner.setAdapter(bookstateadapter);

        }


    }

    public void GetReturnDetailsByID() {

        ReturnModel returnModelRequest = new ReturnModel();

        returnModelRequest.setAT(AppConfigManager.getAccessToken(mContext));

        returnModelRequest.setUId(AppConfigManager.getLoggedUid(mContext));

        returnModelRequest.setRID(AppConfigManager.getReturnRID(mContext));

        returnModelRequest.setDId(AppConfigManager.getDID(mContext));

        final GetReturnDetailsURL returnURL = new GetReturnDetailsURL(returnModelRequest.GetReturnDetailsRequest(), mContext);

        GetReturnDetailsURL.GetReturnDetailsInterface returnDetailsInterface = new GetReturnDetailsURL.GetReturnDetailsInterface() {

            @Override
            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {

                if (returnDetailsParseVector != null && returnDetailsParseVector.size() > 0)
                    returnModel = returnDetailsParseVector.get(0);

                handler.post(returnDetailsRunnable);


            }

        };
        returnURL.setOnResultListener(returnDetailsInterface);

        returnURL.execute();
    }


    Handler handler = new Handler();

    Runnable returnDetailsRunnable = new Runnable() {
        @Override
        public void run() {

            if (returnModel != null) {

                CurrentTaxYear = returnModel.getCTY();

                //CurrentTaxYear = "2015";

                PTY = returnModel.getPTY();

                if (returnModel.getOS().equalsIgnoreCase("Success")) {


                    if (CurrentTaxYear != null & !CurrentTaxYear.equalsIgnoreCase("null")) {

                        currentaxyearradio.setText("Current Tax Year - " + CurrentTaxYear);

                        prevtaxyearradio.setText("Previous Tax Year - " + returnModel.getPTY());

                        currenttaxyearTextview.setText("Calendar Tax Year (" + CurrentTaxYear + ")");

                        begindateTextview.setText("01/01/" + CurrentTaxYear);

                        enddateTextview.setText("12/31/" + CurrentTaxYear);

                        prevbegindateTextview.setText("01/01/" + returnModel.getPTY());

                        prevenddateTextview.setText("12/31/" + returnModel.getPTY());

                        if (returnModel.getFC() != null && !returnModel.getFC().equalsIgnoreCase("null") && !returnModel.getFC().equalsIgnoreCase("0")) {

                            if (databaseHandler == null)

                                databaseHandler = new DatabaseHandler(mContext);

                            selectedFormId = databaseHandler.getFormCodeIDByFormCode(returnModel.getFC());

                            if (selectedFormId != null && !selectedFormId.equalsIgnoreCase("null") && !selectedFormId.trim().equalsIgnoreCase(""))
                                try {
                                    formSelectedPosition = Integer.parseInt(selectedFormId);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    new SendException(mContext, e);
                                }

                            formspinner.setText(databaseHandler.getFormNameByFormCode(returnModel.getFC()));
                            formspinner.setFloatingLabel(MaterialEditText.FLOATING_LABEL_NONE);


                            orgtaxyearlayout.setVisibility(View.VISIBLE);

                            if (returnModel.getIsCurrentYear() != null && returnModel.getIsCurrentYear().equalsIgnoreCase("true")) {

                                currentaxyearradio.setChecked(true);
                                prevtaxyearradio.setChecked(false);

                                if (returnModel.getISCTY() != null && returnModel.getISCTY().equalsIgnoreCase("true")) {


                                    currenttaxyearFlag = true;


                                    currenttaxyearTextview.setChecked(true);
                                    fiscaltaxyearTextView.setChecked(false);

                                    begindateTextview.setClickable(false);

                                    enddateTextview.setClickable(false);

                                    begindateTextview.setEnabled(false);

                                    enddateTextview.setEnabled(false);

                                    reasonlayout.setVisibility(View.GONE);

                                } else {

                                    currenttaxyearFlag = false;

                                    currenttaxyearTextview.setChecked(false);
                                    fiscaltaxyearTextView.setChecked(true);

                                    if (returnModel.getTYBD() != null && !returnModel.getTYBD().equalsIgnoreCase("null") && !returnModel.getTYBD().trim().equalsIgnoreCase(""))


                                        begindateTextview.setText(returnModel.getTYBD());

                                    if (returnModel.getTYED() != null && !returnModel.getTYED().equalsIgnoreCase("null") && !returnModel.getTYED().trim().equalsIgnoreCase(""))


                                        enddateTextview.setText(returnModel.getTYED());


                                    begindateTextview.setClickable(true);

                                    enddateTextview.setClickable(true);

                                    begindateTextview.setEnabled(true);

                                    enddateTextview.setEnabled(true);

                                    reasonlayout.setVisibility(View.GONE);

                                    if (returnModel.getISIR() != null && returnModel.getISIR().equalsIgnoreCase("true")) {

                                        reasonlayout.setVisibility(View.VISIBLE);

                                        IntialcheckBox.setChecked(true);
                                    }

                                    if (returnModel.getISFR() != null && returnModel.getISFR().equalsIgnoreCase("true")) {

                                        reasonlayout.setVisibility(View.VISIBLE);

                                        FinalReturncheckBox.setChecked(true);
                                    }

                                    if (returnModel.getISAPCH() != null && returnModel.getISAPCH().equalsIgnoreCase("true")) {

                                        reasonlayout.setVisibility(View.VISIBLE);

                                        AccountingCheckbox.setChecked(true);
                                    }


                                }


                            } else {

                                currentaxyearradio.setChecked(false);
                                prevtaxyearradio.setChecked(true);

                                prevreasonlayout.setVisibility(View.GONE);

                                if (returnModel.getTYBD() != null && !returnModel.getTYBD().equalsIgnoreCase("null") && !returnModel.getTYBD().trim().equalsIgnoreCase(""))


                                    prevbegindateTextview.setText(returnModel.getTYBD());

                                if (returnModel.getTYED() != null && !returnModel.getTYED().equalsIgnoreCase("null") && !returnModel.getTYED().trim().equalsIgnoreCase(""))


                                    prevenddateTextview.setText(returnModel.getTYED());


                                if (returnModel.getISIR() != null && returnModel.getISIR().equalsIgnoreCase("true")) {

                                    prevreasonlayout.setVisibility(View.VISIBLE);

                                    prevIntialcheckBox.setChecked(true);
                                }

                                if (returnModel.getISFR() != null && returnModel.getISFR().equalsIgnoreCase("true")) {

                                    prevreasonlayout.setVisibility(View.VISIBLE);

                                    prevFinalReturncheckBox.setChecked(true);
                                }

                                if (returnModel.getISAPCH() != null && returnModel.getISAPCH().equalsIgnoreCase("true")) {

                                    prevreasonlayout.setVisibility(View.VISIBLE);

                                    prevAccountingCheckbox.setChecked(true);
                                }

                            }
                        }

                    }

                } else if (returnModel.getOS().equalsIgnoreCase("Failure")) {

                    if (returnModel.getEM() != null && !returnModel.getEM().equalsIgnoreCase("null")) {

                        if (returnModel.getEM().equalsIgnoreCase("Access Token is invalid")) {

                            utils.errorMessage(mContext, "Your session is Expired");

                            Logout.logout(mContext);
                        } else {

                            utils.errorMessage(mContext, returnModel.getEM());

                        }
                    }
                }

                if (Mode != null && Mode.equalsIgnoreCase("View")) {

                    disableLayout();

                }
            }

        }
    };

    protected boolean ValidationCheck() {

        boolean validationflag = true;

        returnmodelSave = new ReturnModel();

        returnmodelSave.setAT(AppConfigManager.getAccessToken(mContext));

        returnmodelSave.setDId(AppConfigManager.getDID(mContext));

        returnmodelSave.setUId(AppConfigManager.getLoggedUid(mContext));

        returnmodelSave.setRID(AppConfigManager.getReturnRID(mContext));

        returnmodelSave.setEODId(AppConfigManager.getBID(mContext));

        returnmodelSave.setBId(AppConfigManager.getBID(mContext));

        if (formSelectedPosition == 0) {

            validationflag = false;

            formspinner.setError("Please Select the Form");

        } else {

            returnmodelSave.setFC(selectedFormId);
        }


        if (currentaxyearradio.isChecked()) {

            returnmodelSave.setTY(CurrentTaxYear);

            returnmodelSave.setIsCurrentYear("true");

            if (begindateTextview.getText().toString().equalsIgnoreCase("")) {
                validationflag = false;

                begindateTextview.setError("Begin Date Required");
            } else {
                begindateTextview.setError(null);

                returnmodelSave.setTYBD(begindateTextview.getText().toString());
            }
            if (enddateTextview.getText().toString().trim().equalsIgnoreCase("")) {
                validationflag = false;

                enddateTextview.setError("End date Required");

            } else {

                enddateTextview.setError(null);

                returnmodelSave.setTYED(enddateTextview.getText().toString());
            }

            if (currenttaxyearTextview.isChecked()) {

                returnmodelSave.setISCTY("true");


            } else {
                returnmodelSave.setISCTY("false");

                if (reasonlayout.isShown()) {

                    if (!IntialcheckBox.isChecked() && !FinalReturncheckBox.isChecked() && !AccountingCheckbox.isChecked()) {
                        validationflag = false;

                        utils.errorMessage(mContext, "Please Select Anyone Reason");

                    }

                    if (IntialcheckBox.isShown() && IntialcheckBox.isChecked()) {
                        returnmodelSave.setISIR("true");
                    } else {
                        returnmodelSave.setISIR("false");
                    }
                    if (FinalReturncheckBox.isShown() && FinalReturncheckBox.isChecked()) {
                        returnmodelSave.setISFR("true");
                    } else {
                        returnmodelSave.setISFR("false");
                    }
                    if (AccountingCheckbox.isShown() && AccountingCheckbox.isChecked()) {
                        returnmodelSave.setISAPCH("true");
                    } else {
                        returnmodelSave.setISAPCH("false");
                    }
                }
            }


        } else if (prevtaxyearradio.isChecked()) {
            returnmodelSave.setTY(PTY);
            returnmodelSave.setIsCurrentYear("false");

            returnmodelSave.setISCTY("false");

            if (prevbegindateTextview.getText().toString().equalsIgnoreCase("")) {
                validationflag = false;

                prevbegindateTextview.setError("Begin Date Required");
            } else {
                prevbegindateTextview.setError(null);

                returnmodelSave.setTYBD(prevbegindateTextview.getText().toString());
            }
            if (prevenddateTextview.getText().toString().trim().equalsIgnoreCase("")) {
                validationflag = false;

                prevenddateTextview.setError("End date Required");

            } else {
                prevenddateTextview.setError(null);

                returnmodelSave.setTYED(prevenddateTextview.getText().toString());
            }
            if (prevreasonlayout.isShown()) {
                if (!prevIntialcheckBox.isChecked() && !prevFinalReturncheckBox.isChecked() && !prevAccountingCheckbox.isChecked()) {
                    validationflag = false;

                    utils.errorMessage(mContext, "Please Select Anyone Reason");

                }

                if (prevIntialcheckBox.isShown() && prevIntialcheckBox.isChecked()) {
                    returnmodelSave.setISIR("true");
                } else {
                    returnmodelSave.setISIR("false");
                }
                if (prevFinalReturncheckBox.isShown() && prevFinalReturncheckBox.isChecked()) {
                    returnmodelSave.setISFR("true");
                } else {
                    returnmodelSave.setISFR("false");
                }
                if (prevAccountingCheckbox.isShown() && prevAccountingCheckbox.isChecked()) {
                    returnmodelSave.setISAPCH("true");
                } else {
                    returnmodelSave.setISAPCH("false");
                }
            }

        }


        return validationflag;
    }

    protected void SaveTaxYearDetails() {

        SaveTaxYearURL saveReturnURL = new SaveTaxYearURL(returnmodelSave.GetSaveReturnDetailsRequest(), mContext);

        SaveTaxYearURL.SaveReturnDetailsInterface saveReturnDetailsInterface = new SaveTaxYearURL.SaveReturnDetailsInterface() {

            @Override
            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {

                savedReturnModel = returnDetailsParseVector.get(0);

                handler.post(saveDetailRunnable);

            }
        };

        saveReturnURL.setOnResultListener(saveReturnDetailsInterface);

        saveReturnURL.execute();

    }

    Runnable saveDetailRunnable = new Runnable() {
        @Override
        public void run() {

            if (savedReturnModel.getOS() != null && savedReturnModel.getOS().equalsIgnoreCase("Success")) {

                utils.successMessage(mContext, "Tax Year Details Saved Successfully");

                if (savedReturnModel.getRID() != null && !savedReturnModel.getRID().equalsIgnoreCase("null")) {
                    AppConfigManager.saveReturnRid(mContext, savedReturnModel.getRID());
                }

                TaxPageFragment();

            } else if (savedReturnModel.getOS() != null && savedReturnModel.getOS().equalsIgnoreCase("Failure")) {

                if (savedReturnModel.getEM() != null && !savedReturnModel.getEM().equalsIgnoreCase("null")) {

                    if (savedReturnModel.getEM().equalsIgnoreCase("Access Token is invalid")) {


                        utils.errorMessage(mContext, "Your Session is Expired");
                        Logout.logout(mContext);

                    } else {

                        utils.errorMessage(mContext, savedReturnModel.getEM());

                    }
                }
            }

        }
    };


    private void TaxPageFragment() {

        AppConfigManager.saveFCID(mContext, selectedFormId);

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

    private void setTypeFace() {

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        text1.setTypeface(typeFaceClass.NotoSans_Bold());
        text2.setTypeface(typeFaceClass.NotoSans_Bold());
    }

    private void disableLayout() {

        DisableEdittext.disableAll(mContext, rootlayout);

        formspinner.setAdapter(null);
        formspinner.setEnabled(false);
        formspinner.setClickable(false);
        formspinner.setFocusable(false);

    }

}