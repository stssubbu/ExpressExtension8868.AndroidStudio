package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class AppConfigManager {

    public static final String MODE = "MODE";

    public static final String FIRSTMODE = "FIRSTMODE";

    public static final String USERNAME = "USERNAME";

    public static final String BUSINESSNAME = "BUSINESSNAME";

    public static final String EIN = "EIN";

    public static final String CONTACTNAME = "CONTACTNAME";

    public static final String PASSWORD = "PASSWORD";

    public static final String RN = "RN";

    public static final String UID = "UID";

    public static final String PDID = "PDID";

    public static final String PD = "PD";

    public static final String RID = "RID";

    public static final String DID = "DID";

    public static final String AT = "AT";

    public static final String CPA = "CPA";

    public static final String CTY = "CTY";

    public static final String APPLICATION_PREFRENCE = "APPLICATION_PREFRENCE";

    public static final String REMEMBER = "REMEMBER";

    public static final String PHONE = "PHONE";

    public static final String EODID = "EODId";

    public static final String BID = "BID";

    public static final String FCID = "FCID";

    public static final String ADDRESS1 = "Address1";

    public static final String ADDRESS2 = "Address2";

    public static final String CITY = "City";

    public static final String STATE = "Sate";

    public static final String COUNTRY = "Country";

    public static final String ZIP = "Zip";

    public static final String ISFORIGN = "ISForign";

    public static final String FLAG = "Flag";


    public static void saveEODID(Context mContext, String EODId) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(EODID, EODId);

        editor.commit();

    }

    public static String getEODIDD(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(EODID, null);
    }


    public static void savePD(Context mContext, String Pd) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(PD, Pd);

        editor.commit();

    }

    public static String getPD(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(PD, null);
    }


    public static void saveMode(Context mContext, String Mode) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(MODE, Mode);

        editor.commit();

    }

    public static String getMode(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(MODE, null);
    }

    public static void savePhone(Context mContext, String phone) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(PHONE, phone);

        editor.commit();

    }

    public static String getPhone(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(PHONE, null);
    }

    public static void saveFirstMode(Context mContext, String Mode) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(FIRSTMODE, Mode);

        editor.commit();

    }

    public static String getFirstMode(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(FIRSTMODE, null);
    }

    public static void saveRememberme(Context mContext, String Mode) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(REMEMBER, Mode);

        editor.commit();

    }

    public static String getRememberme(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(REMEMBER, null);
    }

    public static void saveCTY(Context mContext, String Cty) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(CTY, Cty);

        editor.commit();

    }

    public static String getCTY(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(CTY, null);
    }

    public static void saveUserName(Context mContext, String mUserName, String mPassWord) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(USERNAME, mUserName);
        editor.putString(PASSWORD, mPassWord);

        editor.commit();

    }

    public static void saveBusinessNameEIN(Context mContext, String Businessname, String Ein) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(BUSINESSNAME, Businessname);

        editor.putString(EIN, Ein);

        editor.commit();

    }

    public static String getBusinessname(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(BUSINESSNAME, null);
    }

    public static String getEIN(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(EIN, null);
    }

    public static void saveContactName(Context mContext, String mContactname) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(CONTACTNAME, mContactname);

        editor.commit();

    }

    public static String getContactname(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(CONTACTNAME, null);
    }

    public static void saveDID(Context mContext, String _DID) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(DID, _DID);

        editor.commit();

    }

    public static String getDID(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(DID, null);
    }

    public static void saveRN(Context mContext, String Rn) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(RN, Rn);

        editor.commit();

    }

    public static String getRN(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(RN, null);
    }

    public static void saveLoggedUid(Context mContext, String uid) {
        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(UID, uid);

        editor.commit();

    }

    public static void saveReturnPDID(Context mContext, String PDId) {
        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(PDID, PDId);

        editor.commit();

    }

    public static void saveReturnRid(Context mContext, String Rid) {
        System.out.println("SAVING RID " + Rid);

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(RID, Rid);

        editor.commit();

    }

    public static String getUserName(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(USERNAME, null);
    }

    public static String getPassWord(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(PASSWORD, null);
    }

    public static String getLoggedUid(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(UID, null);

    }

    public static String getReturnPDID(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(PDID, null);

    }

    public static String getReturnRID(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        System.out.println("READING RID " + settings.getString(RID, null));

        return settings.getString(RID, null);

    }

    public static void saveAccessToken(Context mContext, String At) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(AT, At);

        editor.commit();

    }

    public static String getAccessToken(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(AT, null);

    }

    public static void saveCPA(Context mContext, String CPa) {
        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(CPA, CPa);

        editor.commit();

    }

    public static String getCPA(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(CPA, null);

    }

    public static void saveBID(Context mContext, String Bid) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(BID, Bid);

        editor.commit();

    }

    public static String getBID(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(BID, null);
    }

    public static void saveFCID(Context mContext, String Fcid) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(FCID, Fcid);

        editor.commit();

    }

    public static String getFCID(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getString(FCID, null);
    }

    public static void saveOrgAddress(Context mContext, ArrayList<String> add) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putString(ADDRESS1, add.get(0));

        editor.putString(ADDRESS2, add.get(1));

        editor.putString(CITY, add.get(2));

        editor.putString(STATE, add.get(3));

        editor.putString(COUNTRY, add.get(4));

        editor.putString(ZIP, add.get(5));

        editor.putString(ISFORIGN, add.get(6));

        editor.commit();


    }

    public static ArrayList<String> getOrgAddress(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        ArrayList<String> address = new ArrayList<String>();

        address.add(settings.getString(ADDRESS1, null));
        address.add(settings.getString(ADDRESS2, null));
        address.add(settings.getString(CITY, null));
        address.add(settings.getString(STATE, null));
        address.add(settings.getString(COUNTRY, null));
        address.add(settings.getString(ZIP, null));
        address.add(settings.getString(ISFORIGN, null));

        return address;
    }

    public static void saveFlag(Context mContext, int id) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        editor.putInt(FLAG, id);

        editor.commit();

    }

    public static int getFlag(Context mContext) {

        SharedPreferences settings = mContext.getSharedPreferences(APPLICATION_PREFRENCE, Context.MODE_PRIVATE);

        return settings.getInt(FLAG, 0);
    }


}
