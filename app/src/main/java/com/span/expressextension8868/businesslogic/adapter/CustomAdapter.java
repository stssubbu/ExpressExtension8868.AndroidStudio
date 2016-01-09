package com.span.expressextension8868.businesslogic.adapter;

import java.util.Locale;
import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.model.core.ManageBusinessModel1;
import com.span.expressextension8868.utils.utility.SendException;


public class CustomAdapter extends BaseAdapter {
    private final Context mContext;

    public Vector<ManageBusinessModel1> list = new Vector<ManageBusinessModel1>();

    public Vector<ManageBusinessModel1> arraylist;

    public Vector<ManageBusinessModel1> manageList = null;

    private int selectedIndex;

    private final int[] colors = new int[]
            {0xFFc8e3f4, 0xFFD9EBF6};

    private final int selectedColor = Color.parseColor("#FFFFFF");

    public CustomAdapter(Context activity, Vector<Vector<ManageBusinessModel1>> message) {
        super();

        this.manageList = message.get(1);

        this.arraylist = new Vector<ManageBusinessModel1>();

        this.arraylist.addAll(message.get(1));

        this.mContext = activity;

        selectedIndex = 0;
    }

    public void setSelectedIndex(int ind) {
        selectedIndex = ind;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (manageList.size() <= 0) {
            return 0;
        }
        return manageList.size();
    }

    @Override
    public Object getItem(int position) {
        return manageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface type = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Condensed.ttf");

        View rowView = convertView;

        ViewHolder view = null;

        Log.i("Value", "getview");
        try {
            if (rowView == null) {
                Log.i("if", "getview");
                // Get a new instance of the row layout view

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                rowView = inflater.inflate(R.layout.business_infliator, null);

                // Hold the view objects in an object, that way the don't need
                // to be
                // "re-  finded"
                view = new ViewHolder();

                view.stscolor = (ImageView) rowView.findViewById(R.id.stsColor);

                view.businessname = (TextView) rowView.findViewById(R.id.businessnameedittext);

                view.businessname.setTypeface(type);

                view.ein = (TextView) rowView.findViewById(R.id.ein);

                view.ein.setTypeface(type);

                view.managebusinesslistlayout = (RelativeLayout) rowView.findViewById(R.id.managebusinesslistlayout);

                // view.managebusinesslistlayout.setBackgroundColor(0x003300);

                rowView.setTag(view);
            } else {
                Log.i("else", "getview");

                view = (ViewHolder) rowView.getTag();
            }

            /** Set data to your Views. */

            if (position == selectedIndex) {
                view.stscolor.setVisibility(View.VISIBLE);
                view.ein.setTextColor(Color.parseColor("#ED7C18"));
            } else {
                view.stscolor.setVisibility(View.GONE);
                view.ein.setTextColor(Color.parseColor("#125F76"));
            }

            if (manageList.size() != 0) {
                view.businessname.setText(manageList.get(position).getBusinessnamestring());

                view.ein.setText(manageList.get(position).getEinstring());

            }

            if (selectedIndex != -1 && position == selectedIndex) {
                rowView.setBackgroundColor(selectedColor);
            } else {
                int colorPos = position % colors.length;
                rowView.setBackgroundColor(colors[colorPos]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return rowView;
    }

    protected static class ViewHolder {
        protected TextView businessname;

        protected TextView ein;

        protected RelativeLayout managebusinesslistlayout;

        protected ImageView stscolor;
    }

    // Filter Class
    public void filter(String charText) {

        try {
            charText = charText.toLowerCase(Locale.getDefault());

            manageList.clear();

            if (charText.length() == 0) {
                manageList.addAll(arraylist);
            } else {
                for (ManageBusinessModel1 wp : arraylist) {
                    if (wp.getBusinessnamestring().toLowerCase(Locale.getDefault()).contains(charText) || wp.getEinstring().contains(charText)) {
                        manageList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }
}
