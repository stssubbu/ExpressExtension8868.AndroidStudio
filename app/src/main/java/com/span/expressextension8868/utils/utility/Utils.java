package com.span.expressextension8868.utils.utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.span.expressextension8868.R;
import com.span.expressextension8868.controller.controller.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    // Flag for Masking

    Boolean flag = true;

    public final static boolean isValidName(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "^[A-Za-z0-9 #&'()\\-]{1,75}$";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidCity(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidZipCode(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "^\\d{5}(-\\d{4})?$";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidRoutingNumber(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "(01|02|03|04|05|06|07|08|09|10|11|12|21|22|23|24|25|26|27|28|29|30|31|32)[0-9]{7}";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidPhone(String target) {
        Pattern phoneNumberPattern = Pattern.compile("\\(" + "[0-9]{3}" + "\\)" + " " + "[0-9]{3}" + "\\-" + "[0-9]{4}");

        Matcher phoneNumberMatches = phoneNumberPattern.matcher(target);

        boolean CheckPhoneNumberMatch = phoneNumberMatches.matches();

        return CheckPhoneNumberMatch;
    }

    public final static boolean isValidSSN(String target) {
        boolean isValid = false;

        String expression = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";

        CharSequence inputStr = target;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public final static boolean isValidZipCode1(String target) {
        if (target == null) {
            return false;
        } else {
            String text = "/[^a-zA-Z0-9 ]/";
            final Pattern ORG_NAME = Pattern.compile(text);
            // android Regex to check the email address Validation
            return ORG_NAME.matcher(target).matches();
        }
    }

    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            // android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String getCurrentDate() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("MM/dd/yyyy").format(now);

        return nowAsString;
    }

    public static int getCurrentyear() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("yyyy").format(now);

        int currentsupportingyear = Integer.parseInt(nowAsString);

        return currentsupportingyear;
    }

    public static int getCurrentmonth() {
        /*
         * Date now = new Date(); Date alsoNow =
		 * Calendar.getInstance().getTime(); //String nowAsString = new
		 * SimpleDateFormat("MONTH").format(now);
		 */
        int month = Calendar.getInstance().get(Calendar.MONTH);

        return month + 1;
    }

    public static void setfirstletterred(String text, TextView tv, Context mContext) {
        try {
            SpannableString spannable = new SpannableString(text);

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, 1, 0);


            //   spannable.setSpan(new SuperscriptSpan(), 0, 1, 0);

            tv.setTypeface(new TypeFaceClass(mContext).NotoSans_Regular());

            tv.setText(spannable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkForZero(String text) {

        int total = 0;

        text = text.replaceAll(" ", "");
        text = text.replaceAll("-", "");
        text = text.replaceAll("[()-+.^:,]", "");

        for (int i = 0; i < text.length(); i++) {

            if (i == 0) {
                total = Integer.parseInt(String.valueOf(text.charAt(i)));
            } else {
                total = total + Integer.parseInt(String.valueOf(text.charAt(i)));
            }
        }

        if (total == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static String getLocalIpAddress(Context mContext) {
        WifiManager wifiMan = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));

        return ip;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void HideError(EditText mEditText) {
        mEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public static void showError(EditText mEditText) {
        mEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error, 0);
    }

    public static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }

    public static void setHintwithSuperScript(EditText edittext) {
        try {
            SpannableString spannable = new SpannableString(edittext.getHint());

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#D74937")), 0, 1, 0);

            spannable.setSpan(new SuperscriptSpan(), 0, 1, 0);

            edittext.setHint(spannable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> listOfPattern() {
        ArrayList<String> listOfPattern = new ArrayList<String>();

        String ptVisa = "^4[0-9]$";

        listOfPattern.add(ptVisa);

        String ptMasterCard = "^5[1-5]$";

        listOfPattern.add(ptMasterCard);

        String ptDiscover = "^6(?:011|5[0-9]{2})$";

        listOfPattern.add(ptDiscover);

        String ptAmeExp = "^3[47]$";

        listOfPattern.add(ptAmeExp);

        return listOfPattern;
    }

    public static boolean isValidWebUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }

    public void maskingzipcode(Context mContext, EditText ed) {

        try {
            String Phone = ed.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if ((Phone.length() == 1)) {
                flag = true;

            } else if ((Phone.length() >= 6) && (WithOutSpecialChar.length() >= 5) && (Phone.length() == WithOutSpecialChar.length())) {
                ed.setText(String.format(WithOutSpecialChar.substring(0, 5) + "-" + WithOutSpecialChar.substring(5, WithOutSpecialChar.length())));

                ed.setSelection(ed.getText().length());
            } else if ((Phone.length() == 6)) {
                flag = false;
            } else if ((Phone.length() == 6) && flag == true) {
                ed.setText(String.format("%s-", WithOutSpecialChar.substring(0, 5)));

                ed.setSelection(ed.getText().length());

                flag = false;
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }
    }

    public void MaskingPhone(Context mContext, EditText phone) {
        try {
            String Phone = phone.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if (Phone.length() == 3 || Phone.length() == 6 || Phone.length() == 10) {
                flag = true;
            }

            if ((WithOutSpecialChar.length() == 3) && (flag == true)) {
                flag = false;

                phone.setText(String.format("(%s)", WithOutSpecialChar.substring(0, 3)));

                phone.setSelection(phone.getText().length());

            } else if ((WithOutSpecialChar.length() == 6) && (flag == true)) {

                flag = false;

                phone.setText(String.format("(%s) %s", WithOutSpecialChar.substring(0, 3),

                        WithOutSpecialChar.substring(3, 6)));

                phone.setSelection(phone.getText().length());

            } else if ((WithOutSpecialChar.length() == 10) && flag == true) {

                flag = false;

                phone.setText(String.format("(%s) %s-%s", WithOutSpecialChar.substring(0, 3),

                        WithOutSpecialChar.substring(3, 6), WithOutSpecialChar.substring(6, 10)));

                phone.setSelection(phone.getText().length());
            }
        } catch (Exception e) {
            new SendException(mContext, e);
        }

    }

    public void MaskingEin(Context mContext, EditText ed) {

        try {
            String Phone = ed.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if ((Phone.length() == 1)) {
                flag = true;

            } else if ((Phone.length() >= 3) && (WithOutSpecialChar.length() >= 3) && (Phone.length() == WithOutSpecialChar.length())) {
                ed.setText(String.format(WithOutSpecialChar.substring(0, 2) + "-" + WithOutSpecialChar.substring(2, WithOutSpecialChar.length())));

                ed.setSelection(ed.getText().length());
            } else if ((Phone.length() == 3)) {
                flag = false;
            } else if ((Phone.length() == 2) && flag == true) {
                ed.setText(String.format("%s-", WithOutSpecialChar.substring(0, 2)));

                ed.setSelection(ed.getText().length());

                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

    }

    public void MaskingSsn(Context mContext, EditText phonenumber) {

        try {
            String Phone = phonenumber.getText().toString();

            String WithOutSpecialChar = Phone.replaceAll("\\W", "");

            if (Phone.length() == 5 || Phone.length() == 9) {

                flag = true;
            }

            if ((WithOutSpecialChar.length() == 3) && (flag == true)) {
                flag = false;

                phonenumber.setText(String.format("%s-", WithOutSpecialChar.substring(0, 3)));

                phonenumber.setSelection(phonenumber.getText().length());

            } else if ((WithOutSpecialChar.length() == 5) && (flag == true)) {

                flag = false;

                phonenumber.setText(String.format("%s-%s-", WithOutSpecialChar.substring(0, 3), WithOutSpecialChar.substring(3, 5)));

                phonenumber.setSelection(phonenumber.getText().length());

            } else if ((WithOutSpecialChar.length() == 9) && flag == true) {

                flag = false;

                phonenumber.setText(String.format("%s-%s-%s", WithOutSpecialChar.substring(0, 3), WithOutSpecialChar.substring(3, 5), WithOutSpecialChar.substring(5, 9)));

                phonenumber.setSelection(phonenumber.getText().length());
            }
        } catch (Exception e) {
            e.printStackTrace();

            new SendException(mContext, e);
        }

    }

    public void successMessage(Context mContext, String msg) {

//        Snackbar bar = Snackbar.with(mContext);
//
//        View v = bar.getChildAt(0);
//
//        TextView tv = (TextView) v.findViewById(R.id.sb__text);
//
//        tv.setGravity(Gravity.CENTER);

        Snackbar snack = Snackbar.make(((Activity) mContext).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(mContext.getResources().getColor(R.color.White));
        tv.setGravity(Gravity.CENTER);

        TypeFaceClass ty = new TypeFaceClass(mContext);
        tv.setTypeface(ty.NotoSans_Regular());


        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.bottomMargin = 250;
        view.setLayoutParams(params);
        view.setBackgroundColor(mContext.getResources().getColor(R.color.snack_bg_color));
        snack.show();


//        SnackbarManager.show(
//
//
//                Snackbar.with(mContext).actionLabel(null)
//                        .text(msg)
//                        .textColor(mContext.getResources().getColor(R.color.White))
//                        .color(mContext.getResources().getColor(R.color.snack_bg_color))
//                        .duration(5000));


    }

    public void errorMessage(Context mContext, String msg) {

        Snackbar snack = Snackbar.make(((Activity) mContext).findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(mContext.getResources().getColor(R.color.BalanceRed));
        tv.setGravity(Gravity.CENTER);
        TypeFaceClass ty = new TypeFaceClass(mContext);
        tv.setTypeface(ty.NotoSans_Regular());

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        params.bottomMargin = 250;
        view.setLayoutParams(params);
        view.setBackgroundColor(mContext.getResources().getColor(R.color.snack_bg_color));
        snack.show();

//        SnackbarManager.show(
//                Snackbar.with(mContext)
//                        .text(msg)
//                        .textColor(mContext.getResources().getColor(R.color.BalanceRed))
//                        .color(mContext.getResources().getColor(R.color.snack_bg_color))
//                        .duration(5000));

    }

    public String addressOnly(Context mContext, String address1, String address2) {


        String address = address1;

        if (address2 != null && !address2.equalsIgnoreCase("null") && address2.trim().length() > 0) {

            address = address1 + ", " + address2;
        }


        return address;
    }

    public String CityStateOnly(Context mContext, String city, String state, String stateProvions, String country, String zip, boolean isForign) throws Exception {


        String address = "";

        if (city != null && !city.equalsIgnoreCase("null") && city.trim().length() > 0) {
            address = city;
        }

        Log.i("dashboar", "country : " + country + " " + isForign);


        if (isForign) {

            if (stateProvions != null && !stateProvions.equalsIgnoreCase("null") && stateProvions.trim().length() > 0) {
                address = address + ", " + stateProvions;
            }

            if (country != null && !country.equalsIgnoreCase("null") && country.trim().length() > 0) {
                address = address + ", " + country;
            }

        } else {

            if (state != null && !state.equalsIgnoreCase("null") && state.trim().length() > 0) {
                address = address + ", " + state;
            }

        }


        if (zip != null && !zip.equalsIgnoreCase("null") && zip.trim().length() > 0) {

            address = address + " " + zip;
        }


        return address;
    }


    public String BcoCityStateOnly(Context mContext, String city, String state, String stateProvions, String country, String zip, boolean isForign) throws Exception {


        String address = "";

        if (city != null && !city.equalsIgnoreCase("null") && city.trim().length() > 0) {
            address = city;
        }


        if (isForign) {

            if (stateProvions != null && !stateProvions.equalsIgnoreCase("null") && stateProvions.trim().length() > 0) {
                address = address + " " + stateProvions;
            }

            if (country != null && !country.equalsIgnoreCase("null") && country.trim().length() > 0) {
                address = address + "\n" + country;
            }

        } else {

            if (state != null && !state.equalsIgnoreCase("null") && state.trim().length() > 0) {
                address = address + ", " + state;
            }

        }


        if (zip != null && !zip.equalsIgnoreCase("null") && zip.trim().length() > 0) {

            address = address + " " + zip;
        }


        return address;
    }

    public String amountValue(Context mContext, String amt) throws Exception {

        return "$" + amt;

    }

    public String decimalAmountValue(Context mContext, String amt) {
        try {

            return "$" + amt;

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return null;
    }


    TextWatcher tw = new TextWatcher() {
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


    public void blockWatcher1(final com.rengwuxian.materialedittext.MaterialEditText editText, final String value, final TextWatcher textWatcher) {

        editText.removeTextChangedListener(textWatcher);
        editText.post(new Runnable() {
            @Override
            public void run() {
                editText.setText(value);
                editText.post(new Runnable() {
                    @Override
                    public void run() {
                        editText.addTextChangedListener(textWatcher);
                    }
                });
            }
        });

    }


}
