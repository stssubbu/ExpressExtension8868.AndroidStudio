package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Overridefonts {

    public static void overrideFonts(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(mContext, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf"));
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public static void overrideFontslight(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(mContext, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(mContext.getAssets(), "Roboto-Light.ttf"));
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

}
