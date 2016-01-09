package com.span.expressextension8868.businesslogic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.controller.controller.AddExemptOrganization;
import com.span.expressextension8868.controller.controller.BooksInCareOf;
import com.span.expressextension8868.controller.controller.IRSPayment;
import com.span.expressextension8868.controller.controller.NewTaxYearFragment;
import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.SendException;

import java.util.Vector;

/**
 * Created by STS-099 on 11/11/2015.
 */
public class BusinessListAdapter extends BaseAdapter {

    Context mContext;

    private Vector<AddBussinessInputModel> Result;
    private int itemLayout;

    int selectedIndex = 0;


    public BusinessListAdapter(Context mContext, Vector<AddBussinessInputModel> result, int itemLayout) {


        this.mContext = mContext;
        this.Result = result;
        this.itemLayout = itemLayout;
    }


    @Override
    public int getCount() {

        // TODO Auto-generated method stub

        if (Result.size() <= 0) {
            return 0;
        }
        return Result.size();
    }

    @Override
    public Object getItem(int position) {

        // TODO Auto-generated method stub
        if (Result != null) {

            return Result.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {

        // TODO Auto-generated method stub
        return position;
    }

    public void dataSet(int pos) {

        selectedIndex = pos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub

        View rowView = convertView;
        ListViewHolder holder = null;

        try {
            if (rowView == null) {

                holder = new ListViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                rowView = inflater.inflate(itemLayout, null);

                holder.changeBackground = (LinearLayout) rowView.findViewById(R.id.changeBackground);

                holder.BusinessName = (TextView) rowView.findViewById(R.id.Business_name);

                holder.Business_Ein = (TextView) rowView.findViewById(R.id.Business_Ein);


                rowView.setTag(holder);


            } else {
                holder = (ListViewHolder) rowView.getTag();

            }

            if (position == selectedIndex) {

                holder.changeBackground.setBackgroundColor(mContext.getResources().getColor(R.color.Listview_Bg));

                holder.BusinessName.setTextColor(mContext.getResources().getColor(R.color.White));

                holder.Business_Ein.setTextColor(mContext.getResources().getColor(R.color.Common_Header_Right_FG));


            } else {

                holder.changeBackground.setBackgroundColor(mContext.getResources().getColor(R.color.White));

                holder.BusinessName.setTextColor(mContext.getResources().getColor(R.color.Black));

                holder.Business_Ein.setTextColor(mContext.getResources().getColor(R.color.Grey));


            }

            if (Result != null && Result.size() > 0) {

                try {


                    AddBussinessInputModel item = Result.get(position);

                    holder.BusinessName.setText(item.getOrganizationName());

                    holder.Business_Ein.setText(item.getEin());

                } catch (Exception e) {
                    e.printStackTrace();
                    new SendException(mContext, e);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return rowView;
    }


    public class ListViewHolder {

        public TextView BusinessName, Business_Ein;

        LinearLayout changeBackground;


    }

}

