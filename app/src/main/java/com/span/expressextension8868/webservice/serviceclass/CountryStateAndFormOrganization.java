package com.span.expressextension8868.webservice.serviceclass;

import android.content.Context;
import android.util.Log;

import com.span.expressextension8868.model.core.LoginModel;
import com.span.expressextension8868.utils.utility.ApplicationConfig;
import com.span.expressextension8868.utils.utility.SendException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Vector;


public class CountryStateAndFormOrganization {

    String jsonString;

    JSONArray jsoncountry;

    JSONArray jsonstate;

    JSONArray jsonformoforganization;

    JSONArray jsontimezone;

    Vector<String> countryName = new Vector<String>();

    Vector<String> countryId = new Vector<String>();

    Vector<String> stateName = new Vector<String>();

    Vector<String> stateId = new Vector<String>();

    Vector<String> organizationName = new Vector<String>();

    Vector<String> organizationId = new Vector<String>();

    Vector<String> TimeZonename = new Vector<String>();

    Vector<String> TimeZoneID = new Vector<String>();

    Vector<String> TimeZoneValue = new Vector<String>();

    Vector<Vector<String>> countryStateOrganizationVector = new Vector<Vector<String>>();

    Vector<LoginModel> returnobject = new Vector<LoginModel>();

    String Countryname;

    Context mContext;

    Vector<Vector<String>> countryStateOrganization = new Vector<Vector<String>>();

    public Vector<Vector<String>> GetCountryStateOrganization(Context mContext) {

        try {

            this.mContext = mContext;

            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpGet httpget = new HttpGet(ApplicationConfig.ASSIGN_STATIC_DATA);

            httpget.setHeader("Accept", "application/json");

            httpget.setHeader("Content-type", "application/json");

            HttpResponse response = httpclient.execute(httpget); // execute your
            // request and
            // parse
            // response

            HttpEntity entity = response.getEntity();

            jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            if (jsonString != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonString);

                    jsoncountry = jsonObj.getJSONArray("CountryList");

                    jsonstate = jsonObj.getJSONArray("StateList");

                    jsontimezone = jsonObj.getJSONArray("TimeZonesList");

                    jsonformoforganization = jsonObj.getJSONArray("FormOfOrganizationList");

                    countryId.add("0");

                    countryName.add("--Select Country--");

                    stateId.add("0");

                    stateName.add("--Select State--");

                    organizationId.add("0");

                    organizationName.add("--Select an Option--");

                    TimeZonename.add("--Select Time Zone--");

                    TimeZoneID.add("0");

                    TimeZoneValue.add("Default value");

                    for (int i = 0; i < jsoncountry.length(); i++) {
                        JSONObject c = jsoncountry.getJSONObject(i);

                        String id = c.getString("CID");                // ID String After Json
                        // Parsing

                        String name = c.getString("CN");            // Name String After
                        // Json Parsing

                        countryId.add(id);

                        countryName.add(name);

                    }
                    for (int i = 0; i < jsonstate.length(); i++) {
                        JSONObject c = jsonstate.getJSONObject(i);

                        String id = c.getString("SID");                // ID String After Json
                        // Parsing

                        String name = c.getString("SN");            // Name String After
                        // Json Parsing

                        stateId.add(id);

                        stateName.add(name);

                    }

                    for (int i = 0; i < jsontimezone.length(); i++) {
                        JSONObject c = jsontimezone.getJSONObject(i);

                        String id = c.getString("TZId");                // ID String After Json
                        // Parsing

                        String name = c.getString("TZTxt");            // Name String After
                        // Json Parsing

                        String value = c.getString("TZVal");

                        TimeZoneID.add(id);

                        TimeZonename.add(name);

                        TimeZoneValue.add(value);

                    }

                    for (int i = 0; i < jsonformoforganization.length(); i++) {
                        JSONObject c = jsonformoforganization.getJSONObject(i);

                        String id = c.getString("FOOID");                // ID String After
                        // Json Parsing

                        String name = c.getString("FOON");            // Name String After
                        // Json Parsing

                        organizationId.add(id);

                        organizationName.add(name);

                    }


                    countryStateOrganizationVector.add(countryId);

                    countryStateOrganizationVector.add(countryName);

                    countryStateOrganizationVector.add(stateId);

                    countryStateOrganizationVector.add(stateName);

                    countryStateOrganizationVector.add(organizationId);

                    countryStateOrganizationVector.add(organizationName);

                    countryStateOrganizationVector.add(TimeZoneID);

                    countryStateOrganizationVector.add(TimeZonename);

                    countryStateOrganizationVector.add(TimeZoneValue);

                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

        return countryStateOrganizationVector;
    }

    public Vector<Vector<String>> getcountrystateorganization(Context mContext) {

        try {

            this.mContext = mContext;

            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpGet httpget = new HttpGet(ApplicationConfig.ASSIGN_STATIC_DATA);

            httpget.setHeader("Accept", "application/json");

            httpget.setHeader("Content-type", "application/json");

            HttpResponse response = httpclient.execute(httpget); // execute your
            // request and
            // parse
            // response

            HttpEntity entity = response.getEntity();

            jsonString = EntityUtils.toString(entity); // if response in JSON
            // format

            if (jsonString != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonString);

                    jsoncountry = jsonObj.getJSONArray("CountryList");

                    jsonstate = jsonObj.getJSONArray("StateList");

                    jsonformoforganization = jsonObj.getJSONArray("FormOfOrganizationList");

                    jsontimezone = jsonObj.getJSONArray("TimeZonesList");

                    countryId.add("0");

                    countryName.add("--Select country--");

                    stateId.add("0");

                    stateName.add("--Select State--");

                    organizationId.add("0");

                    organizationName.add("--Select an Option--");

                    TimeZonename.add("--Select Time Zone--");

                    TimeZoneID.add("0");

                    for (int i = 0; i < jsoncountry.length(); i++) {
                        JSONObject c = jsoncountry.getJSONObject(i);

                        String id = c.getString("CID");                // ID String After Json
                        // Parsing

                        String name = c.getString("CN");            // Name String After
                        // Json Parsing

                        countryId.add(id);

                        countryName.add(name);

                    }
                    for (int i = 0; i < jsonstate.length(); i++) {
                        JSONObject c = jsonstate.getJSONObject(i);

                        String id = c.getString("SID");                // ID String After Json
                        // Parsing

                        String name = c.getString("SN");            // Name String After
                        // Json Parsing

                        stateId.add(id);

                        stateName.add(name);

                    }
                    for (int i = 0; i < jsonformoforganization.length(); i++) {
                        JSONObject c = jsonformoforganization.getJSONObject(i);

                        String id = c.getString("FOOID");                // ID String After
                        // Json Parsing

                        String name = c.getString("FOON");            // Name String After
                        // Json Parsing

                        organizationId.add(id);

                        organizationName.add(name);

                    }

                    for (int i = 0; i < jsontimezone.length(); i++) {
                        JSONObject c = jsontimezone.getJSONObject(i);

                        String id = c.getString("TZId");                // ID String After Json
                        // Parsing

                        String name = c.getString("TZTxt");            // Name String After
                        // Json Parsing

                        TimeZoneID.add(id);

                        TimeZonename.add(name);

                    }

                    Log.e("BALA", "SIZE OF COUNTRY ID" + countryId.size());

                    countryStateOrganization.add(countryId);

                    countryStateOrganization.add(countryName);

                    countryStateOrganization.add(stateId);

                    countryStateOrganization.add(stateName);

                    countryStateOrganization.add(organizationId);

                    countryStateOrganization.add(organizationName);

                    countryStateOrganization.add(TimeZoneID);

                    countryStateOrganization.add(TimeZonename);

                } catch (Exception e) {
                    e.printStackTrace();

                    new SendException(mContext, e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

        return countryStateOrganization;
    }

    public String getCountrynamewithID(String POCID) {
        int x;
        try {
            Log.e("Received POCID", POCID);
            x = countryId.size();
            for (int i = 0; i < x; i++) {
                Log.e("BALA", "I value" + i);
                if (countryId.get(i).equals(POCID)) {
                    Countryname = countryName.get(i);
                    x = i;
                    Log.e("COUNTRTYNAME", Countryname);
                }
            }
            Log.e("COUNTRTYNAME Before return", Countryname);
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return Countryname;

    }


}