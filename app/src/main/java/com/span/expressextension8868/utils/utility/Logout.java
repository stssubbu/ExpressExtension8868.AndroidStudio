package com.span.expressextension8868.utils.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.span.expressextension8868.controller.controller.LoginActivity;


public class Logout
{

	public static void logout(final Context mContext)
	{
		
			AppConfigManager.saveMode(mContext, null);

			Intent goHome = new Intent(Intent.ACTION_MAIN);

			goHome.setClass(mContext, LoginActivity.class);

			goHome.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

			mContext.startActivity(goHome);

			((Activity) mContext).finish();

	}

}
