package com.span.expressextension8868.businesslogic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.controller.controller.AddExemptOrganization;
import com.span.expressextension8868.controller.controller.BooksInCareOf;
import com.span.expressextension8868.controller.controller.IRSPayment;
import com.span.expressextension8868.controller.controller.NewTaxYearFragment;
import com.span.expressextension8868.model.core.AuditModel;
import com.span.expressextension8868.utils.utility.AppConfigManager;
import com.span.expressextension8868.utils.utility.FragmentNameConfig;
import com.span.expressextension8868.utils.utility.Overridefonts;
import com.span.expressextension8868.utils.utility.SendException;

import java.util.Vector;

/**
 * Created by STS-099 on 11/30/2015.
 */
public class ReviewCautionListAdapter extends BaseAdapter {

    Context mContext;

    private Vector<AuditModel> Result;
    private int itemLayout;
    private LayoutInflater inflate;

    String BId, EIN, BN;

    FragmentActivity fA;

    public ReviewCautionListAdapter(Context context, Vector<AuditModel> result, int itemLayout, FragmentActivity fA, String BId, String EIN, String BN) {

        mContext = context;

        inflate = LayoutInflater.from(mContext);
        this.Result = result;
        this.itemLayout = itemLayout;

        this.fA = fA;
        this.BId = BId;
        this.EIN = EIN;
        this.BN = BN;
    }

    @Override
    public int getCount() {

        // TODO Auto-generated method stub
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub

        final ListViewHolder holder;


        try {
            if (convertView == null) {

                holder = new ListViewHolder();


                convertView = inflate.inflate(itemLayout, null);

                holder.WholeLayout = (LinearLayout) convertView.findViewById(R.id.WholeLayout);

                holder.error_Code = (TextView) convertView.findViewById(R.id.error_Code);

                holder.error_message = (TextView) convertView.findViewById(R.id.error_message);

                holder.fix_me = (TextView) convertView.findViewById(R.id.fix_me);

                final AuditModel item = Result.get(position);

                holder.error_Code.setText(item.getEC());

                holder.error_message.setText(item.getEM());
                Overridefonts.overrideFonts(mContext, holder.WholeLayout);

                holder.fix_me.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (item.getACT() != null && item.getACT().equalsIgnoreCase("Create")) {


                            Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.wholevertical, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                            transaction.commit();

                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("IRSPayment")) {

                            Fragment newFragment = new IRSPayment(mContext);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.IRS_PAYMENT_FRAGMENT);

// Commit the transaction
                            transaction.commit();

                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("Step1")) {

                            Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                            transaction.commit();

                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("Step2")) {
                            Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                            transaction.commit();


                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("Step3")) {

                            Fragment newFragment = new NewTaxYearFragment(mContext, 0);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            //transaction.addToBackStack(FragmentNameConfig.FORM_TAX_SELECTION_FRAGMENT);
// Commit the transaction
                            transaction.commit();


                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("BookInCareOf")) {

                            Fragment newFragment = new BooksInCareOf(mContext, false);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                            transaction.commit();   // TODO: 11/26/2015

                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("PrimaryExemptPurpose")) {

                            Fragment newFragment = new BooksInCareOf(mContext, false);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                            transaction.commit();   // TODO: 11/26/2015

                        } else if (item.getACT() != null && item.getACT().equalsIgnoreCase("ProgramServiceAccomplishment")) {

                            Fragment newFragment = new BooksInCareOf(mContext, false);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.rightFragment, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.BOOKS_IN_CARE_OF_FRAGMENT);

// Commit the transaction
                            transaction.commit();   // TODO: 11/26/2015

                        } else {


                            Fragment newFragment = new AddExemptOrganization(mContext, true, true);

                            FragmentTransaction transaction = fA.getSupportFragmentManager().beginTransaction();

                            transaction.setCustomAnimations(R.anim.slidein, R.anim.slideout);


// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.wholevertical, newFragment);

                            transaction.addToBackStack(FragmentNameConfig.ORG_DETAIL_FRAGMENT);

// Commit the transaction
                            transaction.commit();


                        }


                    }
                });


                convertView.setTag(holder);

            } else {

                holder = (ListViewHolder) convertView.getTag();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
        return convertView;
    }


    public class ListViewHolder {

        public TextView error_Code, error_message, fix_me;

        LinearLayout WholeLayout;


    }

}
