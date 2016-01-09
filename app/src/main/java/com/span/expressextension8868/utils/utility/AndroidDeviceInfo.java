package com.span.expressextension8868.utils.utility;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;

public class AndroidDeviceInfo {

	public static final String DEVICEINFO_PREFRENCE = "AndroidDeviceInfo";

	public static final String DEVICE_NAME = "AndroidDeviceName";

	public static final String DEVICE_MODEL = "AndroidDeviceModel";

	public static final String DEVICE_SDKVERSION = "AndroidDeviceSdkVersion";

	public static final String DEVICE_OSVERSION = "AndroidDeviceOsVersion";

	public static final String DEVICE_MANUFACTURER = "AndroidDeviceManufacturer";

	public static final String DEVICE_ID = "DeviceId";

	public static final String DEVICE_IMEI_NO = "ImeiNo";

	private HashMap<String, String> mDeviceInfoHashMap;

	public static final String IS_TABLET = "IS_TABLET";

	public static Context DEVICEINFOCONTEXT;

	public AndroidDeviceInfo(Context mContext) {

		DEVICEINFOCONTEXT = mContext;

		mDeviceInfoHashMap = new HashMap<String, String>();

	}

	public void addDeviceInfo(String Key, String Value) {

		mDeviceInfoHashMap.put(Key, Value);

	}

	public void saveAndroidDeviceInfo() {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = settings.edit();

		editor.putString(DEVICE_NAME, mDeviceInfoHashMap.get(DEVICE_NAME));

		editor.putString(DEVICE_MODEL, mDeviceInfoHashMap.get(DEVICE_MODEL));

		editor.putString(DEVICE_SDKVERSION, mDeviceInfoHashMap.get(DEVICE_SDKVERSION));

		editor.putString(DEVICE_OSVERSION, mDeviceInfoHashMap.get(DEVICE_OSVERSION));

		editor.putString(DEVICE_MANUFACTURER, mDeviceInfoHashMap.get(DEVICE_MANUFACTURER));

		editor.putString(DEVICE_ID, mDeviceInfoHashMap.get(DEVICE_ID));

		editor.putString(DEVICE_IMEI_NO, mDeviceInfoHashMap.get(DEVICE_IMEI_NO));

		editor.commit();
	}

	public static void clearAndroidDeviceInfo() {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = settings.edit();

		editor.clear();

		editor.commit();
	}

	public static void saveDeviceType(Context context, boolean isTablet) {

		SharedPreferences settings = context.getSharedPreferences(DEVICEINFO_PREFRENCE, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = settings.edit();

		editor.putBoolean(IS_TABLET, isTablet);

		editor.commit();
	}

	public String getDeviceModel(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_MODEL, DEVICE_MODEL);

	}

	public static String getDeviceID(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_ID, DEVICE_ID);

	}

	public String getDeviceName(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_NAME, DEVICE_NAME);

	}

	public String getDeviceOsVersion(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_OSVERSION, DEVICE_OSVERSION);

	}

	public String getDeviceSdk(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_SDKVERSION, DEVICE_SDKVERSION);

	}

	public String getDeviceManufacturer(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_MANUFACTURER, DEVICE_MANUFACTURER);

	}

	public String getDeviceImeiNo(Context mContext) {

		SharedPreferences settings = DEVICEINFOCONTEXT.getSharedPreferences(DEVICEINFO_PREFRENCE, mContext.MODE_PRIVATE);

		return settings.getString(DEVICE_IMEI_NO, DEVICE_IMEI_NO);

	}

	public static boolean getDeviceType(Context context) {

		SharedPreferences settings = context.getSharedPreferences(DEVICEINFO_PREFRENCE, Context.MODE_PRIVATE);

		return settings.getBoolean(IS_TABLET, false);
	}

}
