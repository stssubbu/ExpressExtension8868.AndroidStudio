package com.span.expressextension8868.controller.controller;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.businesslogic.adapter.BusinessListAdapter;
import com.span.expressextension8868.businesslogic.adapter.CustomAdapter;
import com.span.expressextension8868.businesslogic.adapter.CustomAdapterReturnList;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.IRSReturnModel;
import com.span.expressextension8868.model.core.ManageBusinessModel1;
import com.span.expressextension8868.model.core.ReturnListModel;
import com.span.expressextension8868.model.core.ReturnModel;
import com.span.expressextension8868.repository.repository.DatabaseHandler;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.ConnectionDetector;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Logout;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;
import com.span.expressextension8868.utils.utility.Utils;
import com.span.expressextension8868.webservice.serviceclass.CountryStateAndFormOrganization;
import com.span.expressextension8868.webservice.webservices.DeleteBusines;
import com.span.expressextension8868.webservice.webservices.GetIRSPaymentDetailsURL;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by STS-099 on 11/14/2015.
 */
public class DashboardFragment extends Fragment {

    Context mContext;

    View dashboardView;

    AddBussinessInputModel businessListModel;

    Typeface typeface;

    String deleteSuccess, deleteError;
    //

    ImageView businesslistview;


    TextView DashBoardTitle;

    ArrayList<String> taxYearArrayList;

    Vector<ReturnModel> businessListReturnDetailVector;

    //

    //add dialog

    AlertDialog emailDialog;

    LinearLayout DashboardOrgDetailsLayout;

    TextView DashboardOrgEin, DashboardOrgName;

    android.support.v7.widget.CardView sendEmail, emailCancel;

    EditText emailTo, emailSubject, emailContent;

    JSONObject obj = new JSONObject();

    JSONObject obj1 = new JSONObject();

    String emailResponse;

    Utils utils;

    //irs payment detail

    //getValue

    IRSReturnModel getIRSReturnModel, responseIRSReturnModel;

    GetIRSPaymentDetailsURL getIRSPaymentDetailsURL;

    ListView lv;

    BusinessListAdapter adapter;

    DrawerLayout mDrawerLayout;

    ListView businesslist;

    AddBussinessInputModel BusinessListRequestModel, BusinessListDetailRequestModel;

    Vector<AddBussinessInputModel> businessListVector;

    ReturnModel returnListModel;


    public DashboardFragment(Context mContext, AddBussinessInputModel businessListModel, ReturnModel returnListModel, DrawerLayout mDrawerLayout, ListView businesslist, int selectionposition, BusinessClikInterface businessClikInterface) {

        this.mContext = mContext;

        this.businessListModel = businessListModel;

        this.returnListModel = returnListModel;

        this.mDrawerLayout = mDrawerLayout;

        this.businesslist = businesslist;

        this.selectionposition = selectionposition;

        this.businessClikInterface = businessClikInterface;


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

        typeface = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");


        try {

            connectiondetector = new ConnectionDetector(mContext);


            dashboardView = inflater.inflate(R.layout.dashboard_layout, container, false);


            if (mDrawerLayout != null)
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            initialization();

            setAdapterValue();

            Overridefonts.overrideFonts(mContext, rootlayout);

            OverrideBoldTexts();

            setonclicklistener();

            fillBusinessDetails(businessListModel);

            if (returnListModel != null && returnListModel.getCurrentYearVector() != null && returnListModel.getCurrentYearVector().size() > 0)

                setReturnList(returnListModel.getCurrentYearVector());

            else
                setReturnList(null);


            return dashboardView;

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return null;
    }

    com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner taxYearDropdown;

    TextView businessname, address, ein, businessdbaname, businesswebsite;


    LinearLayout infolayout;
    FrameLayout listHeaderLayout;


    ExpandableListView returnexpandablelist;


    ImageView Edit, Delete, logolink, refresh;

    CustomAdapter listadapter;

    EditText listfilter;

    String Bid;


    TextView textform990, eightitemstext, noReturn;

    // Button startnewreturnfornew;
    android.support.v7.widget.CardView startnewreturns, createreturns;

    Vector<ReturnListModel> messageforRID = new Vector<ReturnListModel>();

    ConnectionDetector connectiondetector;

    CountryStateAndFormOrganization countrylist;

    Vector<ManageBusinessModel1> message2 = new Vector<ManageBusinessModel1>();

    String POCID1;

    int position, totalposition;

    LinearLayout rootlayout, refreshlayout;

    int selectionposition = 0;


    CustomAdapterReturnList expadapter;

    int i = 0;

    //


    private void OverrideBoldTexts() {

        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        businessname.setTypeface(typeFaceClass.NotoSans_Bold());

        eightitemstext.setTypeface(typeFaceClass.NotoSans_Bold());

        textform990.setTypeface(typeFaceClass.NotoSans_Bold());

    }

//    public void clickOnListItem(int position, final ListView listview) {
//        listview.performItemClick(listview, position, listview.getItemIdAtPosition(position));
//
//        listview.post(new Runnable() {
//
//            @Override
//            public void run() {
//                listview.setSelection(0);
//
//            }
//        });
//
//    }


    public void setonclicklistener() {

        try {

            rootlayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    try {
                        InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });

            taxYearDropdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

                }
            });


            taxYearDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));

                    switch (position) {

                        case 0:

                            setReturnList(returnListModel.getCurrentYearVector());
                            break;

                        case 1:

                            setReturnList(returnListModel.getPrevioudYearVector());
                            break;

                        case 2:

                            setReturnList(returnListModel.getPrePreviousYearVector());
                            break;
                    }

                }
            });


            rootlayout.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    try {
                        InputMethodManager in = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });

//            cpaedit.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    try {
//                        if (connectiondetector.isConnectingToInternet()) {
//                            Intent i = new Intent(mContext, EditCPAactivity.class);
//
//                            startActivity(i);
//
//                            mContext.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
//
//                            finish();
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        new SendException(mContext, e);
//                    }
//                }
//            });


//            startnewreturnfornew.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    if (connectiondetector.isConnectingToInternet()) {
//
//                        // startActivity(new Intent(mContext,
//                        // StartNewReturn.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//
////                        try {
////                            Intent i = new Intent(mContext, TaxYearEligibility.class);
////
////                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////
////                            i.putExtra("RID", "0");
////
////                            AppConfigManager.saveReturnRid(mContext, "0");
////
////                            i.putExtra("MODE", "new");
////
////                            AppConfigManager.saveMode(mContext, "new");
////
////                            i.putExtra("FLAG", "0");
////
////                            startActivity(i);
////
////                            finish();
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                            new SendException(mContext, e);
////                        }
//
//                    } else {
//                        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//            });

            createreturns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (connectiondetector.isConnectingToInternet()) {

                        try {

                            AppConfigManager.saveReturnRid(mContext, "0");

                            AppConfigManager.saveMode(mContext, "Edit");

                            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);


                            if (businessListModel.getEin() != null && !businessListModel.getEin().equalsIgnoreCase("null") && businessListModel.getEin().length() > 0)

                                DashboardOrgEin.setText(businessListModel.getEin());

                            if (businessListModel.getOrganizationName() != null && !businessListModel.getOrganizationName().equalsIgnoreCase("null") && businessListModel.getOrganizationName().length() > 0)


                                DashboardOrgName.setText(businessListModel.getOrganizationName());


                            AppConfigManager.saveFlag(mContext, 0);

                            Fragment newFragment = new CommonTaxFragment(mContext);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.wholevertical, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);

// Commit the transaction
                            transaction.commit();

                        } catch (Exception e) {
                            e.printStackTrace();
                            new SendException(mContext, e);
                        }

                    } else {

                        utils.errorMessage(mContext, "No Internet Connection");

                    }
                }
            });

            startnewreturns.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (connectiondetector.isConnectingToInternet()) {

                        // startActivity(new Intent(mContext,
                        // StartNewReturn.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                        try {

                            AppConfigManager.saveReturnRid(mContext, "0");

                            AppConfigManager.saveMode(mContext, "Edit");

                            DashboardOrgDetailsLayout.setVisibility(View.VISIBLE);


                            if (businessListModel.getEin() != null && !businessListModel.getEin().equalsIgnoreCase("null") && businessListModel.getEin().length() > 0)

                                DashboardOrgEin.setText(businessListModel.getEin());

                            if (businessListModel.getOrganizationName() != null && !businessListModel.getOrganizationName().equalsIgnoreCase("null") && businessListModel.getOrganizationName().length() > 0)


                                DashboardOrgName.setText(businessListModel.getOrganizationName());


                            AppConfigManager.saveFlag(mContext, 0);

                            Fragment newFragment = new CommonTaxFragment(mContext);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.wholevertical, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);

// Commit the transaction
                            transaction.commit();


//                            Intent i = new Intent(mContext, TaxYearEligibility.class);
//
//                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                            i.putExtra("RID", "0");
//
//                            AppConfigManager.saveReturnRid(mContext, "0");
//
//                            i.putExtra("MODE", "new");
//
//                            AppConfigManager.saveMode(mContext, "new");
//
//                            i.putExtra("FLAG", "0");
//
//                            startActivity(i);
//
//                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                            new SendException(mContext, e);
                        }

                    } else {

                        utils.errorMessage(mContext, "No Internet Connection");
                    }
                }
            });


            Edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        if (connectiondetector.isConnectingToInternet()) {

                            addOrgDetailsFragment(Bid);

//                            if (Integer.parseInt(message1.get(1).get(selectionposition).getMODE()) == 1) {
//                                Log.d("In if getmode edit", message1.get(1).get(selectionposition).getMODE());
//
//                                Intent i = new Intent(mContext, EditBusinessActivity.class);
//
//                                i.putExtra("BID", Bid);
//
//                                i.putExtra("Dis", "1");
//
//                                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
//
//                                startActivity(i);
//
//                                finish();
//                            } else if (Integer.parseInt(message1.get(1).get(selectionposition).getMODE()) == 0 || Integer.parseInt(message1.get(1).get(selectionposition).getMODE()) == 2) {
//                                Log.d("In else getmode edit", message1.get(1).get(selectionposition).getMODE());
//
//                                Intent i = new Intent(mContext, EditBusinessActivity.class);
//
//                                i.putExtra("BID", Bid);
//
//                                i.putExtra("Dis", "0");
//
//                                startActivity(i);
//
//                                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
//
//                                finish();
//                            }

                        } else {
                            utils.errorMessage(mContext, "No Internet Connection");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                }
            });

            Delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    try {
                        new AlertDialog.Builder(mContext).setTitle("Delete Organization").setMessage("Are you sure you want to delete this Organization?").setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (connectiondetector.isConnectingToInternet()) {
                                    try {


                                        DeleteBusines deletebusiness = new DeleteBusines(Bid, AppConfigManager.getLoggedUid(mContext), mContext, AppConfigManager.getDID(mContext), AppConfigManager.getAccessToken(mContext));

                                        DeleteBusines.Deleterefresh deletereferesh = new DeleteBusines.Deleterefresh() {

                                            @Override
                                            public void onResultSuccess(final String message, final String messageerror) {

                                                deleteSuccess = message;
                                                deleteError = messageerror;


                                                deleteHandler.post(deletRunnable);

                                            }

                                            @Override
                                            public void onResultFail(int resultCode, String errorMessage) {

                                            }

                                        };

                                        deletebusiness.setOnResultListener(deletereferesh);
                                        deletebusiness.execute();

                                    } catch (Exception e) {
                                        new SendException(mContext, e);
                                        e.printStackTrace();
                                    }
                                } else {

                                    utils.errorMessage(mContext, "No Internet Connection");
                                }

                            }
                        }).setIcon(R.drawable.delete).show();
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

    Handler deleteHandler = new Handler();

    Runnable deletRunnable = new Runnable() {
        @Override
        public void run() {

            if (deleteSuccess.equalsIgnoreCase("Success")) {

                Log.i("dashboard fragment", "selected list pos : " + businesslist.getSelectedItemPosition());

                utils.successMessage(mContext, "Organization Deleted");


                businessClikInterface.setOnBusinessClick(selectionposition);

            }
            if (deleteSuccess.equalsIgnoreCase("Failure") && deleteError != null) {

                utils.errorMessage(mContext, "Your Session is Expired");

                Logout.logout(mContext);
            }

        }
    };

    public void initialization() {

        try {

            Bid = AppConfigManager.getBID(mContext);


            businesslistview = (ImageView) ((Activity) mContext).findViewById(R.id.businesslistview);


            DashboardOrgDetailsLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.DashboardOrgDetailsLayout);
            DashboardOrgEin = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgEin);
            DashboardOrgName = (TextView) ((Activity) mContext).findViewById(R.id.DashboardOrgName);

            rootlayout = (LinearLayout) dashboardView.findViewById(R.id.rootlayout);


            textform990 = (TextView) dashboardView.findViewById(R.id.textform990);

            eightitemstext = (TextView) dashboardView.findViewById(R.id.eightitemstext);

            listHeaderLayout = (FrameLayout) dashboardView.findViewById(R.id.listHeaderLayout);

            infolayout = (LinearLayout) dashboardView.findViewById(R.id.infolayout);

            taxYearDropdown = (com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) dashboardView.findViewById(R.id.taxYearDropdown);

            businessdbaname = (TextView) dashboardView.findViewById(R.id.businessdbaname);

            businesswebsite = (TextView) dashboardView.findViewById(R.id.businesswebsite);

            startnewreturns = (android.support.v7.widget.CardView) dashboardView.findViewById(R.id.startnewreturns);

            //      startnewreturnfornew = (Button) dashboardView.findViewById(R.id.startnewreturnfornew);

            startnewreturns.setVisibility(View.VISIBLE);

            createreturns = (android.support.v7.widget.CardView) dashboardView.findViewById(R.id.createreturns);

            //   startnewreturnfornew.setVisibility(View.GONE);


            refresh = (ImageView) dashboardView.findViewById(R.id.refresh);

            logolink = (ImageView) dashboardView.findViewById(R.id.logolink);


            listfilter = (EditText) dashboardView.findViewById(R.id.listfilter);


            Edit = (ImageView) dashboardView.findViewById(R.id.Edit);

            Delete = (ImageView) dashboardView.findViewById(R.id.Delete);


            returnexpandablelist = (ExpandableListView) dashboardView.findViewById(R.id.returnexpandablelist);

            returnexpandablelist.setGroupIndicator(null);

            noReturn = (TextView) dashboardView.findViewById(R.id.noReturn);
            noReturn.setVisibility(View.GONE);

            businessname = (TextView) dashboardView.findViewById(R.id.businessnameedittext);

            address = (TextView) dashboardView.findViewById(R.id.address1);

            ein = (TextView) dashboardView.findViewById(R.id.ein);


        } catch (Exception e) {

            e.printStackTrace();
            new SendException(mContext, e);
        }
    }

    private void setAdapterValue() {

        taxYearArrayList = new ArrayList<String>();

        int currentYear = Utils.getCurrentyear() - 1;

        taxYearArrayList.add("Current Tax Year (" + currentYear-- + ")");

        taxYearArrayList.add("Tax Year (" + currentYear-- + ")");

        taxYearDropdown.setText(taxYearArrayList.get(0));

        try {
            ArrayAdapter<String> Spinneradapter = new ArrayAdapter<String>(mContext, R.layout.spinnerdataxml, R.id.spinnertext, taxYearArrayList) {

                public View getView(int position, View convertView, ViewGroup parent) {

                    try {


                        View v = super.getView(position, convertView, parent);
                        TextView tv = (TextView) v.findViewById(R.id.spinnertext);

                        View divider = (View) v.findViewById(R.id.divider);
                        tv.setSingleLine();
                        tv.setTypeface(typeface);

                        if (position == taxYearArrayList.size() - 1)
                            divider.setVisibility(View.GONE);
                        else
                            divider.setVisibility(View.VISIBLE);

                        return v;

                    } catch (Exception e) {

                        e.printStackTrace();
                        new SendException(mContext, e);
                    }

                    return null;
                }
            };
            taxYearDropdown.setAdapter(Spinneradapter);


        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    DatabaseHandler databaseHandler;

    private void fillBusinessDetails(AddBussinessInputModel businessListDetailModel1) {

        if (businessListDetailModel1 != null) {

            StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

            if (databaseHandler == null)
                databaseHandler = new DatabaseHandler(mContext);


            if (utils == null)
                utils = new Utils();


            if (businessListDetailModel1.getOrganizationName() != null && !businessListDetailModel1.getOrganizationName().equalsIgnoreCase("null"))

                businessname.setText(businessListDetailModel1.getOrganizationName());

            if (businessListDetailModel1.getEin() != null && !businessListDetailModel1.getEin().equalsIgnoreCase("null")) {

                ein.setText(businessListDetailModel1.getEin());

            }
            String countryName = null;

            try {

                countryName = databaseHandler.getCountryNameFromCID(businessListDetailModel1.getAddressCountryId());


            } catch (Exception e) {

                e.printStackTrace();

                new SendException(mContext, e);

            }

            String address1 = utils.addressOnly(mContext, businessListDetailModel1.getAddress_Address1(), businessListDetailModel1.getAddress_Address2());


            if (address1 != null)
                address.setText(address1);

            try {

                Log.i("dashboar", "country : " + countryName + " " + businessListDetailModel1.getAddressOutSideus_Address());

                String state = utils.CityStateOnly(mContext, businessListDetailModel1.getAddress_City(), businessListDetailModel1.getAddressStateShort(), businessListDetailModel1.getAddress_Stateorprovince(), countryName, businessListDetailModel1.getAddress_Zipcode(), Boolean.parseBoolean(businessListDetailModel1.getAddressOutSideus_Address()));

                if (state != null) {

                    if (address1 != null)

                        address.append(", " + state);

                    else
                        address.setText(state);

                }


            } catch (Exception e) {

                e.printStackTrace();

                new SendException(mContext, e);

            }

            if (businessListDetailModel1.getDoingBusinessas() != null && !businessListDetailModel1.getDoingBusinessas().equalsIgnoreCase("null")) {

                businessdbaname.setText(businessListDetailModel1.getDoingBusinessas());

            } else {

                String text1 = "DBA Name: " + "-";

                SpannableString spannable1 = new SpannableString(text1);

                spannable1.setSpan(boldSpan, 0, 9, 0);

                spannable1.setSpan(new ForegroundColorSpan(Color.parseColor("#065664")), 0, 9, 0);

                businessdbaname.setVisibility(View.GONE);
            }


            if (businessListDetailModel1.getAddress_Websiteaddress() != null && !businessListDetailModel1.getAddress_Websiteaddress().equalsIgnoreCase("null")) {

                businesswebsite.setText(businessListDetailModel1.getAddress_Websiteaddress());

            } else {

//                String text2 = "Website: " + "-";
//
//                SpannableString spannable2 = new SpannableString(text2);
//
//                spannable2.setSpan(boldSpan, 0, 8, 0);
//
//                spannable2.setSpan(new ForegroundColorSpan(Color.parseColor("#065664")), 0, 8, 0);

                businesswebsite.setVisibility(View.GONE);

            }
        }

    }

    private void setReturnList(Vector<ReturnModel> businessListReturnDetailVector) {


        if (returnListModel != null) {

            Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


            this.businessListReturnDetailVector = businessListReturnDetailVector;
            try {

                if (businessListReturnDetailVector != null && businessListReturnDetailVector.size() > 0) {

                    expadapter = new CustomAdapterReturnList(mContext, businessListReturnDetailVector, businessListModel, lv, getActivity(), R.id.wholevertical, businesslistview, mDrawerLayout, businesslist, selectionposition, businessClikInterface);

                    expadapter.setSelectedIndex(selectionposition);

                    returnexpandablelist.setAdapter(expadapter);

                    returnexpandablelist.setDivider(null);
                    returnexpandablelist.setChildDivider(null);

                    // returnexpandablelist.expandGroup(0);

                    expadapter.notifyDataSetChanged();

                    returnexpandablelist.invalidateViews();

                    totalposition = businessListReturnDetailVector.size();

                    if (businessListReturnDetailVector.size() > 1) {


                        //startnewreturn.setVisibility(View.GONE);

                        Delete.setVisibility(View.GONE);

                        infolayout.setVisibility(View.GONE);

                        noReturn.setVisibility(View.GONE);

                        returnexpandablelist.setVisibility(View.VISIBLE);
                    } else if (businessListReturnDetailVector.size() < 1) {
                        AppConfigManager.saveReturnRid(mContext, "0");

                        AppConfigManager.saveMode(mContext, "Edit");

                        //startnewreturn.setVisibility(View.VISIBLE);


                        Delete.setVisibility(View.VISIBLE);
                        noReturn.setVisibility(View.GONE);
                        returnexpandablelist.setVisibility(View.GONE);

                        infolayout.setVisibility(View.VISIBLE);

                    } else if (businessListReturnDetailVector.size() == 1) {

                        //startnewreturn.setVisibility(View.GONE);


                        Delete.setVisibility(View.GONE);

                        infolayout.setVisibility(View.GONE);

                        returnexpandablelist.setVisibility(View.VISIBLE);
                    }

                    noReturn.setVisibility(View.GONE);
                } else {

                    AppConfigManager.saveReturnRid(mContext, "0");

                    AppConfigManager.saveMode(mContext, "Edit");

                    //startnewreturn.setVisibility(View.VISIBLE);

                    listHeaderLayout.setVisibility(View.VISIBLE);

                    returnexpandablelist.setVisibility(View.GONE);

                    infolayout.setVisibility(View.GONE);

                    noReturn.setVisibility(View.VISIBLE);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else

        {

            Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));
            Delete.setVisibility(View.VISIBLE);

            listHeaderLayout.setVisibility(View.GONE);

            returnexpandablelist.setVisibility(View.GONE);

            infolayout.setVisibility(View.VISIBLE);
        }
    }

    private void addOrgDetailsFragment(String id) {

        businesslistview.setEnabled(false);

        businesslistview.setClickable(false);

        if (mDrawerLayout != null)
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //   refresh.setVisibility(View.GONE);


        //     DashBoardTitle.setText("ADD EXEMPT ORGANIZATION DETAILS");


        Fragment newFragment = new AddExemptOrganization(mContext, true, false);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.wholevertical, newFragment);

        transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
        transaction.commit();

    }


    BusinessClikInterface businessClikInterface;


    public interface BusinessClikInterface {

        public void setOnBusinessClick(int position);

    }

}
