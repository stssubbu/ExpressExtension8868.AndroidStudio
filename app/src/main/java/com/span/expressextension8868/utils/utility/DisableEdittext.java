package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class DisableEdittext {

    public static void disableedittext(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;

                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    disableedittext(mContext, child);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(false);
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public static void enableedittext(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    enableedittext(mContext, child);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(true);
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public static void setsizeedittext(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    enableedittext(mContext, child);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setTextSize(.7f);
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public static void disableAll(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    disableAll(mContext, child);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(false);
            } else if (v instanceof LinearLayout) {
                ((LinearLayout) v).setClickable(false);
            } else if (v instanceof TextView) {
                ((TextView) v).setEnabled(false);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setEnabled(false);
            } else if (v instanceof Button) {
                ((Button) v).setEnabled(false);
            } else if (v instanceof Spinner) {
                ((Spinner) v).setClickable(false);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setEnabled(false);
            } else if (v instanceof com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) {
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setEnabled(false);
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setClickable(false);
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setFocusable(false);
            } else if (v instanceof com.rengwuxian.materialedittext.MaterialEditText) {
                ((com.rengwuxian.materialedittext.MaterialEditText) v).setEnabled(false);
                ((com.rengwuxian.materialedittext.MaterialEditText) v).setClickable(false);
            } else {
                v.setEnabled(false);
                v.setClickable(false);
            }

        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public static void EnableAll(final Context mContext, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    EnableAll(mContext, child);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(true);
            } else if (v instanceof LinearLayout) {
                ((LinearLayout) v).setClickable(true);
            } else if (v instanceof TextView) {
                ((TextView) v).setEnabled(true);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setEnabled(true);
            } else if (v instanceof Button) {
                ((Button) v).setEnabled(true);
            } else if (v instanceof Spinner) {
                ((Spinner) v).setClickable(true);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setEnabled(true);
            } else if (v instanceof com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) {
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setEnabled(true);
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setClickable(true);
                ((com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner) v).setFocusable(true);
            } else if (v instanceof com.rengwuxian.materialedittext.MaterialEditText) {
                ((com.rengwuxian.materialedittext.MaterialEditText) v).setEnabled(true);
                ((com.rengwuxian.materialedittext.MaterialEditText) v).setClickable(true);

            } else {
                v.setEnabled(true);
                v.setClickable(true);
            }


        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

}
