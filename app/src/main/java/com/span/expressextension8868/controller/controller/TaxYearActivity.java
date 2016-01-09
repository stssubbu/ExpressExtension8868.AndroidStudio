package com.span.expressextension8868.controller.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.DisableEdittext;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.NetworkChangeReceiver;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL;
import com.span.expressextension8868.webservice.webservices.GetReturnDetailsURL.GetReturnDetailsInterface;
import com.span.expressextension8868.webservice.webservices.SaveTaxYearURL;
import com.span.expressextension8868.webservice.webservices.SaveTaxYearURL.SaveReturnDetailsInterface;
import com.span.expressextension8868.R;

public class TaxYearActivity extends CommonHeaderClass {

//    Context mContext = TaxYearActivity.this;
//
//    View HeaderView;
//
//    DatabaseHandler databasehandler;
//
//    NetworkChangeReceiver receiver;
//
//    LinearLayout rootlayout, reasonlayout, orgtaxyearlayout, corporationlinear;
//
//    TextView cancelbutton, next;
//
//    TextView selectformtextview, whatisyourtaxyeartextview, selectoption, IntialTextView, FinalReturnTextview, AccountingtextView, currenttaxyearTextview, fiscaltaxyearTextView;
//
//    TextView begindateTextview, enddateTextview;//, errorenddate;
//
//    CheckBox IntialcheckBox, FinalReturncheckBox, AccountingCheckbox;
//
//    Spinner formspinner;
//
//    String CurrentTaxYear;
//
//    String currenttaxyearFlag = "true";
//
//    String prevcurrenttaxyearFlag = "false";
//
//    String formcode = "0", PTY;
//
//    int actualtotaldays;
//
//    int usertotaldays;
//
//    RadioButton currentaxyearradio, prevtaxyearradio;
//
//    //For Prev Tax Year
//
//    TextView prevwhatisyourtaxyeartextview, prevfiscaltaxyearTextView, prevbegindateTextview, prevenddateTextview, prevselectoption;
//
//    TextView prevIntialTextView, prevFinalReturnTextview, prevAccountingtextView, preverrorenddate;
//
//    //Linearlayout
//
//    LinearLayout prevtaxyearlayout, prevfiscaltaxyear, prevreasonlayout;
//
//    //ImageView prevchoosebegindate,prevchooseenddate;
//
//    CheckBox prevIntialcheckBox, prevFinalReturncheckBox, prevAccountingCheckbox;
//
//    ReturnModel returnmodelSave = new ReturnModel();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        final View viewToLoad = LayoutInflater.from(this).inflate(R.layout.taxyear, null);
//
//        this.setContentView(viewToLoad);
//
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//
//        HeaderView = viewToLoad.findViewById(R.id.home_headerView);
//
//        Initialization();
//
//        mappingWidgets(HeaderView);
//
//        databasehandler = new DatabaseHandler(mContext);
//
//        // Spinner Inputs Handler
//        handler.post(runnable);
//
//        Overridefonts.overrideFonts(mContext, rootlayout);
//
//        receiver = new NetworkChangeReceiver();
//
//        this.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
//
//        setOnclickListener();
//
//        GetReturnDetailsByID();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        unregisterReceiver(receiver);
//    }
//
//
//    public void GetReturnDetailsByID() {
//
//        ReturnModel returnModel = new ReturnModel();
//
//        returnModel.setAT(AppConfigManager.getAccessToken(mContext));
//
//        returnModel.setUId(AppConfigManager.getLoggedUid(mContext));
//
//        returnModel.setRID(AppConfigManager.getReturnRID(mContext));
//
//        returnModel.setDId(AppConfigManager.getDID(mContext));
//
//        GetReturnDetailsURL returnURL = new GetReturnDetailsURL(returnModel.GetReturnDetailsRequest(), mContext);
//
//        GetReturnDetailsInterface returnDetailsInterface = new GetReturnDetailsInterface() {
//
//            @Override
//            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {
//                final ReturnModel returnModel = returnDetailsParseVector.get(0);
//
//                CurrentTaxYear = returnModel.getCTY();
//
//                //CurrentTaxYear = "2015";
//
//                PTY = returnModel.getPTY();
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        if (returnModel.getOS().equalsIgnoreCase("Success")) {
//                            if (CurrentTaxYear != null & !CurrentTaxYear.equalsIgnoreCase("null")) {
//                                currentaxyearradio.setText("Current Tax Year " + CurrentTaxYear);
//
//                                prevtaxyearradio.setText("Previous Tax Year " + returnModel.getPTY());
//
//                                currenttaxyearTextview.setText("Calendar Tax Year (" + CurrentTaxYear + ")");
//
//                                begindateTextview.setText("01/01/" + CurrentTaxYear);
//
//                                enddateTextview.setText("12/31/" + CurrentTaxYear);
//
//                                prevbegindateTextview.setText("01/01/" + returnModel.getPTY());
//
//                                prevenddateTextview.setText("12/31/" + returnModel.getPTY());
//                            }
//
//                        } else if (returnModel.getOS().equalsIgnoreCase("Failure") && returnModel.getEM().equalsIgnoreCase("Access Token Invalid")) {
//                            Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_LONG).show();
//
//                            Logout.logout(mContext);
//                        }
//
//                    }
//                });
//            }
//
//        };
//        returnURL.setOnResultListener(returnDetailsInterface);
//
//        returnURL.execute();
//    }
//
//
//    public void setOnclickListener() {
//
//        next.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (ValidationCheck()) {
//                    SaveTaxYearDetails();
//                }
//
//            }
//        });
//
//
//        IntialTextView.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                IntialcheckBox.performClick();
//
//            }
//        });
//
//        FinalReturnTextview.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                FinalReturncheckBox.performClick();
//
//            }
//        });
//
//        AccountingtextView.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                AccountingCheckbox.performClick();
//
//            }
//        });
//
//        // For Prevtaxyear
//        prevIntialTextView.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                prevIntialcheckBox.performClick();
//
//            }
//        });
//
//        prevFinalReturnTextview.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                prevFinalReturncheckBox.performClick();
//
//            }
//        });
//
//        prevAccountingtextView.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                prevAccountingCheckbox.performClick();
//
//            }
//        });
//
//        currentaxyearradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                if (isChecked) {
//                    prevtaxyearradio.setChecked(false);
//
//                    corporationlinear.setVisibility(View.VISIBLE);
//
//                    prevtaxyearlayout.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        prevtaxyearradio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    currentaxyearradio.setChecked(false);
//
//                    corporationlinear.setVisibility(View.GONE);
//
//                    prevtaxyearlayout.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
//
//        currenttaxyear.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                currenttaxyear.setBackgroundResource(R.drawable.segmentedblue);
//
//                fiscaltaxyear.setBackgroundResource(R.drawable.segmentedwhite);
//
//                currenttaxyearTextview.setTextColor(Color.parseColor("#FFFFFF"));
//
//                fiscaltaxyearTextView.setTextColor(Color.parseColor("#8D8D8D"));
//
//                begindateTextview.setClickable(false);
//
//                enddateTextview.setClickable(false);
////
////				choosebegindate.setVisibility(View.GONE);
////
////				chooseenddate.setVisibility(View.GONE);
//
//                reasonlayout.setVisibility(View.INVISIBLE);
//
//                if (CurrentTaxYear != null && !CurrentTaxYear.equalsIgnoreCase("null")) {
//                    begindateTextview.setText("01/01/" + CurrentTaxYear);
//
//                    enddateTextview.setText("12/31/" + CurrentTaxYear);
//                }
//
//                currenttaxyearFlag = "true";
//
//            }
//        });
//
//        fiscaltaxyear.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                fiscaltaxyear.setBackgroundResource(R.drawable.segmentedblueself);
//
//                currenttaxyear.setBackgroundResource(R.drawable.segmentedwhiteself);
//
//                currenttaxyearTextview.setTextColor(Color.parseColor("#8D8D8D"));
//
//                fiscaltaxyearTextView.setTextColor(Color.parseColor("#FFFFFF"));
//
////				choosebegindate.setVisibility(View.VISIBLE);
////
////				chooseenddate.setVisibility(View.VISIBLE);
//
//                currenttaxyearFlag = "false";
//            }
//        });
//
//
////        prevchoosebegindate.setOnClickListener(new OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////
////                try {
////                    final Dialog dialog = new Dialog(v.getContext());
////
////                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////
////                    dialog.setContentView(R.layout.datepicker);
////
////                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
////
////                    final DatePicker pickdate = (DatePicker) dialog.findViewById(R.id.pickdate);
////
////                    @SuppressWarnings("deprecation")
////                    Date mindate = new Date("01/01/" + PTY);
////
////                    pickdate.setMinDate(mindate.getTime());
////
////                    Calendar c = Calendar.getInstance();
////
////                    c.setTime(mindate);
////
////                    c.add(Calendar.YEAR, 1);
////
////                    c.add(Calendar.DAY_OF_MONTH, -30);
////
////                    pickdate.setMaxDate(c.getTimeInMillis() - 1);
////
////                    String[] setdate = prevbegindateTextview.getText().toString().split("/");
////
////                    pickdate.updateDate(Integer.parseInt(setdate[2]), Integer.parseInt(setdate[0]) - 1, Integer.parseInt(setdate[1]));
////
////                    Button done = (Button) dialog.findViewById(R.id.done);
////
////                    done.setOnClickListener(new OnClickListener() {
////
////                        @Override
////                        public void onClick(View v) {
////
////                            prevbegindateTextview.setText(String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear());
////
////                            prevenddateTextview.setText("                      ");
////
////                            dialog.dismiss();
////
////                        }
////                    });
////
////                    dialog.show();
////                } catch (Exception e) {
////                    e.printStackTrace();
////
////                    new SendException(mContext, e);
////                }
////
////            }
////        });
////
//
////		choosebegindate.setOnClickListener(new OnClickListener()
////		{
////
////			@Override
////			public void onClick(View v)
////			{
////
////				try
////				{
////					final Dialog dialog = new Dialog(v.getContext());
////
////					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////
////					dialog.setContentView(R.layout.datepicker);
////
////					dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
////
////					final DatePicker pickdate = (DatePicker) dialog.findViewById(R.id.pickdate);
////
////					@SuppressWarnings("deprecation")
////					Date mindate = new Date("01/01/" + CurrentTaxYear);
////
////					pickdate.setMinDate(mindate.getTime());
////
////					Calendar c = Calendar.getInstance();
////
////					c.setTime(mindate);
////
////					c.add(Calendar.YEAR, 1);
////
////					c.add(Calendar.DAY_OF_MONTH, -30);
////
////					pickdate.setMaxDate(c.getTimeInMillis() - 1);
////
////					String[] setdate = begindateTextview.getText().toString().split("/");
////
////					pickdate.updateDate(Integer.parseInt(setdate[2]), Integer.parseInt(setdate[0]) - 1, Integer.parseInt(setdate[1]));
////
////					Button done = (Button) dialog.findViewById(R.id.done);
////
////					done.setOnClickListener(new OnClickListener()
////					{
////
////						@Override
////						public void onClick(View v)
////						{
////
////							begindateTextview.setText(String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear());
////
////							enddateTextview.setText("                      ");
////
////							dialog.dismiss();
////
////						}
////					});
////
////					dialog.show();
////				}
////				catch (Exception e)
////				{
////					e.printStackTrace();
////
////					new SendException(mContext, e);
////				}
////
////			}
////		});
//
//
////        prevchooseenddate.setOnClickListener(new OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////
////
////                try {
////                    final Dialog dialog = new Dialog(v.getContext());
////
////                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////
////                    dialog.setContentView(R.layout.datepicker);
////
////                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
////
////                    final DatePicker pickdate = (DatePicker) dialog.findViewById(R.id.pickdate);
////
////                    @SuppressWarnings("deprecation")
////                    Date mindate = new Date(prevbegindateTextview.getText().toString());
////
////                    pickdate.setMinDate(mindate.getTime());
////
////                    Calendar c = Calendar.getInstance();
////
////                    c.setTime(mindate);
////
////                    c.add(Calendar.YEAR, 1);
////
////                    pickdate.setMaxDate(c.getTimeInMillis() - 1);
////
////                    SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
////
////                    String date1 = "";
////
////                    if (!prevbegindateTextview.getText().toString().equalsIgnoreCase("")) {
////                        date1 = prevbegindateTextview.getText().toString();
////                    }
////
////                    Date date2date = new Date(c.getTimeInMillis() - 1);
////
////                    try {
////                        Date newdate1 = myFormat.parse(date1);
////
////                        long totaldays = date2date.getTime() - newdate1.getTime();
////
////                        actualtotaldays = (int) TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS);
////
////                        System.out.println("Total Days: " + TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS));
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////
////                    if (prevenddateTextview.getText().toString().trim().equalsIgnoreCase("") == false) {
////                        String[] setdate = prevenddateTextview.getText().toString().split("/");
////
////                        pickdate.updateDate(Integer.parseInt(setdate[2]), Integer.parseInt(setdate[0]) - 1, Integer.parseInt(setdate[1]));
////                    } else {
////                        pickdate.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
////                    }
////
////                    Button done = (Button) dialog.findViewById(R.id.done);
////
////                    done.setOnClickListener(new OnClickListener() {
////
////                        @Override
////                        public void onClick(View v) {
////                            preverrorenddate.setVisibility(View.INVISIBLE);
////
////                            String dateset = String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear();
////
////                            prevenddateTextview.setText(String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear());
////
////                            SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
////
////                            String inputString1 = "";
////
////                            String inputString2 = "";
////
////                            if (!prevbegindateTextview.getText().toString().equalsIgnoreCase("")) {
////                                inputString1 = prevbegindateTextview.getText().toString();
////                            }
////
////                            inputString2 = dateset;
////
////                            try {
////                                Date date1 = myFormat.parse(inputString1);
////
////                                Date date2 = myFormat.parse(inputString2);
////
////                                long userselectedtotaldays = date2.getTime() - date1.getTime();
////
////                                usertotaldays = (int) TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS);
////
////                                System.out.println("Days: " + TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS));
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
////
////                            if (usertotaldays < actualtotaldays) {
////                                prevreasonlayout.setVisibility(View.VISIBLE);
////                            } else {
////                                prevreasonlayout.setVisibility(View.GONE);
////                            }
////
////                            dialog.dismiss();
////                        }
////                    });
////
////                    dialog.show();
////                } catch (Exception e) {
////                    e.printStackTrace();
////
////                    new SendException(mContext, e);
////                }
////
////            }
////        });
//
////		chooseenddate.setOnClickListener(new OnClickListener()
////		{
////
////			@Override
////			public void onClick(View v)
////			{
////
////				try
////				{
////					final Dialog dialog = new Dialog(v.getContext());
////
////					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////
////					dialog.setContentView(R.layout.datepicker);
////
////					dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
////
////					final DatePicker pickdate = (DatePicker) dialog.findViewById(R.id.pickdate);
////
////					@SuppressWarnings("deprecation")
////					Date mindate = new Date(begindateTextview.getText().toString());
////
////					pickdate.setMinDate(mindate.getTime());
////
////					Calendar c = Calendar.getInstance();
////
////					c.setTime(mindate);
////
////					c.add(Calendar.YEAR, 1);
////
////					pickdate.setMaxDate(c.getTimeInMillis() - 1);
////
////					SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
////
////					String date1 = "";
////
////					if(!begindateTextview.getText().toString().equalsIgnoreCase(""))
////					{
////						date1 = begindateTextview.getText().toString();
////					}
////
////					Date date2date = new Date(c.getTimeInMillis()-1);
////
////					try
////					{
////					    Date newdate1 = myFormat.parse(date1);
////
////					    long totaldays = date2date.getTime()-newdate1.getTime();
////
////					    actualtotaldays=(int) TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS);
////
////					    System.out.println ("Total Days: " + TimeUnit.DAYS.convert(totaldays, TimeUnit.MILLISECONDS));
////					}
////					catch (Exception e)
////					{
////					    e.printStackTrace();
////					}
////
////					if (enddateTextview.getText().toString().trim().equalsIgnoreCase("") == false)
////					{
////						String[] setdate = enddateTextview.getText().toString().split("/");
////
////						pickdate.updateDate(Integer.parseInt(setdate[2]), Integer.parseInt(setdate[0]) - 1, Integer.parseInt(setdate[1]));
////					}
////					else
////					{
////						pickdate.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
////					}
////
////					Button done = (Button) dialog.findViewById(R.id.done);
////
////					done.setOnClickListener(new OnClickListener()
////					{
////
////						@Override
////						public void onClick(View v)
////						{
////							errorenddate.setVisibility(View.INVISIBLE);
////
////							String dateset = String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear();
////
////							enddateTextview.setText(String.format("%02d", pickdate.getMonth() + 1) + "/" + String.format("%02d", pickdate.getDayOfMonth()) + "/" + pickdate.getYear());
////
////							SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
////
////							String inputString1 = "";
////
////							String inputString2="";
////
////							if(!begindateTextview.getText().toString().equalsIgnoreCase(""))
////							{
////								inputString1 = begindateTextview.getText().toString();
////							}
////
////							inputString2 = dateset;
////
////							try
////							{
////							    Date date1 = myFormat.parse(inputString1);
////
////							    Date date2 = myFormat.parse(inputString2);
////
////							    long userselectedtotaldays = date2.getTime() - date1.getTime();
////
////							    usertotaldays=(int) TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS);
////
////							    System.out.println ("Days: " + TimeUnit.DAYS.convert(userselectedtotaldays, TimeUnit.MILLISECONDS));
////							}
////							catch (Exception e)
////							{
////							    e.printStackTrace();
////							}
////
////							if(usertotaldays<actualtotaldays)
////							{
////								reasonlayout.setVisibility(View.VISIBLE);
////							}
////							else
////							{
////								reasonlayout.setVisibility(View.GONE);
////							}
////
////							dialog.dismiss();
////						}
////					});
////
////					dialog.show();
////				}
////				catch (Exception e)
////				{
////					e.printStackTrace();
////
////					new SendException(mContext, e);
////				}
////
////			}
////		});
//
//        formspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                formcode = databasehandler.getFormList("FormCode").get(position);
//
//                currenttaxyear.performClick();
//
//                if (formspinner.getSelectedItemPosition() == 0) {
//                    DisableEdittext.disableAll(mContext, orgtaxyearlayout);
//
//                    currenttaxyear.performClick();
//
//                    currenttaxyear.setClickable(false);
//
//                    fiscaltaxyear.setClickable(false);
//                } else {
//                    DisableEdittext.EnableAll(mContext, orgtaxyearlayout);
//
//                    currenttaxyear.setClickable(true);
//
//                    fiscaltaxyear.setClickable(true);
//                }
//
//                if (formcode.equalsIgnoreCase("10")) {
//                    fiscaltaxyear.setVisibility(View.GONE);
//
//                    prevtaxyearradio.setVisibility(View.GONE);
//
//                    currenttaxyear.setBackgroundResource(R.drawable.segmentedbluecalendar);
//                } else {
//                    fiscaltaxyear.setVisibility(View.VISIBLE);
//
//                    prevtaxyearradio.setVisibility(View.VISIBLE);
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//    }
//
//    protected void SaveTaxYearDetails() {
//
//        SaveTaxYearURL saveReturnURL = new SaveTaxYearURL(returnmodelSave.GetSaveReturnDetailsRequest(), mContext);
//
//        SaveReturnDetailsInterface saveReturnDetailsInterface = new SaveReturnDetailsInterface() {
//
//            @Override
//            public void onResultSuccess(Vector<ReturnModel> returnDetailsParseVector) {
//
//                final ReturnModel savedReturnModel = returnDetailsParseVector.get(0);
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
//
//                        if (savedReturnModel.getOS() != null && savedReturnModel.getOS().equalsIgnoreCase("Success")) {
//                            Toast.makeText(getBaseContext(), "Tax Year Details Saved", Toast.LENGTH_SHORT).show();
//
//                            if (savedReturnModel.getRID() != null && !savedReturnModel.getRID().equalsIgnoreCase("null")) {
//                                AppConfigManager.saveReturnRid(mContext, savedReturnModel.getRID());
//                            }
//
//                            Intent dashboard = new Intent(TaxYearActivity.this, ExtensionTypeActivity.class);
//
//                            startActivity(dashboard);
//
//                            finish();
//                        } else if (savedReturnModel.getOS() != null && savedReturnModel.getOS().equalsIgnoreCase("Failure")) {
//                            if (savedReturnModel.getEM() != null && !savedReturnModel.getEM().equalsIgnoreCase("null")) {
//                                if (savedReturnModel.getEM().equalsIgnoreCase("Access Token is invalid")) {
//                                    Toast.makeText(getBaseContext(), "Your Session is Expired", Toast.LENGTH_SHORT).show();
//
//                                    Logout.logout(mContext);
//                                } else {
//                                    Toast.makeText(getBaseContext(), savedReturnModel.getEM(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//
//                    }
//                });
//
//            }
//        };
//
//        saveReturnURL.setOnResultListener(saveReturnDetailsInterface);
//
//        saveReturnURL.execute();
//
//    }
//
//    protected boolean ValidationCheck() {
//        boolean validationflag = true;
//
//        returnmodelSave.setAT(AppConfigManager.getAccessToken(mContext));
//
//        returnmodelSave.setDId(AppConfigManager.getDID(mContext));
//
//        returnmodelSave.setUId(AppConfigManager.getLoggedUid(mContext));
//
//        returnmodelSave.setRID(AppConfigManager.getReturnRID(mContext));
//
//        if (formspinner.getSelectedItem().toString().contains("Select")) {
//            validationflag = false;
//
//            Toast.makeText(getBaseContext(), "Please Select the Form", Toast.LENGTH_SHORT).show();
//        } else {
//            returnmodelSave.setFC(formcode);
//        }
//
//        if (reasonlayout.isShown()) {
//            if (!IntialcheckBox.isChecked() && !FinalReturncheckBox.isChecked() && !AccountingCheckbox.isChecked()) {
//                validationflag = false;
//
//                Toast.makeText(getBaseContext(), "Please Select Anyone Reason", Toast.LENGTH_SHORT).show();
//            }
//
//            if (IntialcheckBox.isShown() && IntialcheckBox.isChecked()) {
//                returnmodelSave.setISIR("true");
//            } else {
//                returnmodelSave.setISIR("false");
//            }
//            if (FinalReturncheckBox.isShown() && FinalReturncheckBox.isChecked()) {
//                returnmodelSave.setISFR("true");
//            } else {
//                returnmodelSave.setISFR("false");
//            }
//            if (AccountingCheckbox.isShown() && AccountingCheckbox.isChecked()) {
//                returnmodelSave.setISAPCH("true");
//            } else {
//                returnmodelSave.setISAPCH("false");
//            }
//        }
//
//        if (prevreasonlayout.isShown()) {
//            if (!prevIntialcheckBox.isChecked() && !prevFinalReturncheckBox.isChecked() && !prevAccountingCheckbox.isChecked()) {
//                validationflag = false;
//
//                Toast.makeText(getBaseContext(), "Please Select Anyone Reason", Toast.LENGTH_SHORT).show();
//            }
//
//            if (prevIntialcheckBox.isShown() && prevIntialcheckBox.isChecked()) {
//                returnmodelSave.setISIR("true");
//            } else {
//                returnmodelSave.setISIR("false");
//            }
//            if (prevFinalReturncheckBox.isShown() && prevFinalReturncheckBox.isChecked()) {
//                returnmodelSave.setISFR("true");
//            } else {
//                returnmodelSave.setISFR("false");
//            }
//            if (prevAccountingCheckbox.isShown() && prevAccountingCheckbox.isChecked()) {
//                returnmodelSave.setISAPCH("true");
//            } else {
//                returnmodelSave.setISAPCH("false");
//            }
//        }
//
//        if (currentaxyearradio.isChecked()) {
//            returnmodelSave.setTY(CurrentTaxYear);
//
//            if (corporationlinear.isShown()) {
//                if (currenttaxyearFlag.equalsIgnoreCase("true")) {
//                    returnmodelSave.setISCTY("true");
//                } else if (currenttaxyearFlag.equalsIgnoreCase("false")) {
//                    returnmodelSave.setISCTY("false");
//                }
//                if (begindateTextview.getText().toString().equalsIgnoreCase("")) {
//                    validationflag = false;
//
//                    begindateTextview.setError("Begin Date Required");
//                } else {
//                    begindateTextview.setError(null);
//
//                    returnmodelSave.setTYBD(begindateTextview.getText().toString());
//                }
////                if (enddateTextview.getText().toString().trim().equalsIgnoreCase("")) {
////                    validationflag = false;
////
////                    errorenddate.setVisibility(View.VISIBLE);
////                } else {
////                    errorenddate.setVisibility(View.GONE);
////
////                    returnmodelSave.setTYED(enddateTextview.getText().toString());
////                }
//            }
//        } else if (prevtaxyearradio.isChecked()) {
//            returnmodelSave.setTY(PTY);
//
//            if (prevtaxyearlayout.isShown()) {
//                if (prevcurrenttaxyearFlag.equalsIgnoreCase("true")) {
//                    returnmodelSave.setISCTY("true");
//                } else if (prevcurrenttaxyearFlag.equalsIgnoreCase("false")) {
//                    returnmodelSave.setISCTY("false");
//                }
//                if (prevbegindateTextview.getText().toString().equalsIgnoreCase("")) {
//                    validationflag = false;
//
//                    prevbegindateTextview.setError("Begin Date Required");
//                } else {
//                    prevbegindateTextview.setError(null);
//
//                    returnmodelSave.setTYBD(prevbegindateTextview.getText().toString());
//                }
//                if (prevenddateTextview.getText().toString().trim().equalsIgnoreCase("")) {
//                    validationflag = false;
//
//                    preverrorenddate.setVisibility(View.VISIBLE);
//                } else {
//                    preverrorenddate.setVisibility(View.GONE);
//
//                    returnmodelSave.setTYED(prevenddateTextview.getText().toString());
//                }
//                if (prevIntialcheckBox.isShown() && prevIntialcheckBox.isChecked()) {
//                    returnmodelSave.setISIR("true");
//                } else {
//                    returnmodelSave.setISIR("false");
//                }
//                if (prevFinalReturncheckBox.isShown() && prevFinalReturncheckBox.isChecked()) {
//                    returnmodelSave.setISIR("true");
//                } else {
//                    returnmodelSave.setISIR("false");
//                }
//                if (prevAccountingCheckbox.isShown() && prevAccountingCheckbox.isChecked()) {
//                    returnmodelSave.setISIR("true");
//                } else {
//                    returnmodelSave.setISIR("false");
//                }
//            }
//
//        }
//
//
//        return validationflag;
//    }
//
//    public void Initialization() {
//        // Linear layout
//
//        rootlayout = (LinearLayout) findViewById(R.id.rootlayout);
//
//        currenttaxyear = (LinearLayout) findViewById(R.id.currenttaxyear);
//
//        fiscaltaxyear = (LinearLayout) findViewById(R.id.fiscaltaxyear);
//
//        reasonlayout = (LinearLayout) findViewById(R.id.reasonlayout);
//
//        orgtaxyearlayout = (LinearLayout) findViewById(R.id.orgtaxyearlayout);
//
//        corporationlinear = (LinearLayout) findViewById(R.id.corporationlinear);
//
//        // TextView's
//
//        HeaderView.findViewById(R.id.FormInterviewtextView).setBackgroundResource(R.drawable.topfirstselected);
//
//        ((TextView) HeaderView.findViewById(R.id.FormInterviewtextView)).setTextColor(Color.WHITE);
//
//        cancelbutton = (TextView) findViewById(R.id.cancelbutton);
//
//        next = (TextView) findViewById(R.id.next);
//
//        selectformtextview = (TextView) findViewById(R.id.selectformtextview);
//
//        Utils.setfirstletterred(selectformtextview.getText().toString(), selectformtextview);
//
//        whatisyourtaxyeartextview = (TextView) findViewById(R.id.whatisyourtaxyeartextview);
//
//        Utils.setfirstletterred(whatisyourtaxyeartextview.getText().toString(), whatisyourtaxyeartextview);
//
//        selectoption = (TextView) findViewById(R.id.selectoption);
//
//        Utils.setfirstletterred(selectoption.getText().toString(), selectoption);
//
//        IntialTextView = (TextView) findViewById(R.id.IntialTextView);
//
//        FinalReturnTextview = (TextView) findViewById(R.id.FinalReturnTextview);
//
//        AccountingtextView = (TextView) findViewById(R.id.AccountingtextView);
//
//        currenttaxyearTextview = (TextView) findViewById(R.id.currenttaxyearTextview);
//
//        fiscaltaxyearTextView = (TextView) findViewById(R.id.fiscaltaxyearTextView);
//
//        begindateTextview = (TextView) findViewById(R.id.begindateTextview);
//
//        enddateTextview = (TextView) findViewById(R.id.enddateTextview);
//
//        //errorenddate = (TextView) findViewById(R.id.errorenddate);
//
//        //CheckBox
//
//        IntialcheckBox = (CheckBox) findViewById(R.id.IntialcheckBox);
//
//        FinalReturncheckBox = (CheckBox) findViewById(R.id.FinalReturncheckBox);
//
//        AccountingCheckbox = (CheckBox) findViewById(R.id.AccountingCheckbox);
//
//        //Spinner
//
//        formspinner = (Spinner) findViewById(R.id.formspinner);
//
//        //ImageView's
//
////		choosebegindate=(ImageView)findViewById(R.id.choosebegindate);
////
////		chooseenddate=(ImageView)findViewById(R.id.chooseenddate);
//
//        // Prev year.........................................
//
//        prevwhatisyourtaxyeartextview = (TextView) findViewById(R.id.prevwhatisyourtaxyeartextview);
//
//        Utils.setfirstletterred(prevwhatisyourtaxyeartextview.getText().toString(), prevwhatisyourtaxyeartextview);
//
//        prevfiscaltaxyearTextView = (TextView) findViewById(R.id.prevfiscaltaxyearTextView);
//
//        prevbegindateTextview = (TextView) findViewById(R.id.prevbegindateTextview);
//
//        prevenddateTextview = (TextView) findViewById(R.id.prevenddateTextview);
//
//        prevselectoption = (TextView) findViewById(R.id.prevselectoption);
//
//        Utils.setfirstletterred(prevselectoption.getText().toString(), prevselectoption);
//
//        prevIntialTextView = (TextView) findViewById(R.id.prevIntialTextView);
//
//        prevFinalReturnTextview = (TextView) findViewById(R.id.prevFinalReturnTextview);
//
//        prevAccountingtextView = (TextView) findViewById(R.id.prevAccountingtextView);
//
//        //  preverrorenddate = (TextView) findViewById(R.id.preverrorenddate);
//
//        //Linear Layout
//
//        prevtaxyearlayout = (LinearLayout) findViewById(R.id.prevtaxyearlayout);
//
//        prevfiscaltaxyear = (LinearLayout) findViewById(R.id.prevfiscaltaxyear);
//
//        prevreasonlayout = (LinearLayout) findViewById(R.id.prevreasonlayout);
//
//        //ImageView
//
////		prevchoosebegindate=(ImageView)findViewById(R.id.prevchoosebegindate);
////
////		prevchooseenddate=(ImageView)findViewById(R.id.prevchooseenddate);
//
//        //Checkbox
//
//        prevIntialcheckBox = (CheckBox) findViewById(R.id.prevIntialcheckBox);
//
//        prevFinalReturncheckBox = (CheckBox) findViewById(R.id.prevFinalReturncheckBox);
//
//        prevAccountingCheckbox = (CheckBox) findViewById(R.id.prevAccountingCheckbox);
//
//        //Radio Button
//
//        currentaxyearradio = (RadioButton) findViewById(R.id.currentaxyearradio);
//
//        prevtaxyearradio = (RadioButton) findViewById(R.id.prevtaxyearradio);
//
//
//    }
//
//
//    Handler handler = new Handler();
//
//    Runnable runnable = new Runnable() {
//
//        @Override
//        public void run() {
//
//            setSpinnerInputs(databasehandler.getFormList("FormName"), formspinner);
//
//        }
//    };
//
//
//    public void setSpinnerInputs(final ArrayList<String> list, final Spinner spinner) {
//
//        try {
//            ArrayAdapter<String> formadapter;
//
//            formadapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinnerdataxml, R.id.spinnertext, list) {
//
//                @Override
//                public View getView(int pos, View convertView, android.view.ViewGroup parent) {
//
//                    View v = convertView;
//
//                    if (v == null) {
//                        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                        v = vi.inflate(R.layout.spinnerdataxml, null);
//
//                    }
//
//                    TextView tv = (TextView) v.findViewById(R.id.spinnertext);
//
//                    tv.setText(list.get(pos));
//
//                    TypeFaceClass typeface = new TypeFaceClass(getApplicationContext());
//
//                    tv.setTypeface(typeface.RobotoCondensed());
//
//                    return v;
//
//                }
//
//                ;
//
//            };
//
//            spinner.setAdapter(formadapter);
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            new SendException(mContext, e);
//        }
//
//    }

}
