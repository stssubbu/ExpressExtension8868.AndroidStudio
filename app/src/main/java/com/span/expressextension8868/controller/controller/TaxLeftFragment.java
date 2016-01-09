package com.span.expressextension8868.controller.controller;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;
import com.span.expressextension8868.utils.utility.SendException;
import com.span.expressextension8868.utils.utility.TypeFaceClass;

/**
 * Created by STS-099 on 11/18/2015.
 */
public class TaxLeftFragment extends Fragment {

    Context mContext;

    View CommonTaxView;


    int SelectionId = 0;

    //

    TextView FormInterviewtextView, TaxDuetextView, SummarytextView, ReviewtextView, OurFeestextView, TransmittotheIRStextView;


    public TaxLeftFragment(Context mContext, int SelectionId) {

        this.mContext = mContext;

        this.SelectionId = SelectionId;


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(mContext));


        try {

            if (CommonTaxView == null) {

                CommonTaxView = inflater.inflate(R.layout.leftprogress, container, false);

                Initialization();

                onClick();

                leftSelection(SelectionId);

                return CommonTaxView;

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();

            new SendException(getActivity(), e);
        }

        return null;
    }


    private void Initialization() {
        // TODO: 11/18/2015


        FormInterviewtextView = (TextView) CommonTaxView.findViewById(R.id.FormInterviewtextView);

        TaxDuetextView = (TextView) CommonTaxView.findViewById(R.id.TaxDuetextView);

        SummarytextView = (TextView) CommonTaxView.findViewById(R.id.SummarytextView);

        ReviewtextView = (TextView) CommonTaxView.findViewById(R.id.ReviewtextView);

        OurFeestextView = (TextView) CommonTaxView.findViewById(R.id.OurFeestextView);

        TransmittotheIRStextView = (TextView) CommonTaxView.findViewById(R.id.TransmittotheIRStextView);


        TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

        FormInterviewtextView.setTypeface(typeFaceClass.NotoSans_Bold());

        TaxDuetextView.setTypeface(typeFaceClass.NotoSans_Bold());

        SummarytextView.setTypeface(typeFaceClass.NotoSans_Bold());

        ReviewtextView.setTypeface(typeFaceClass.NotoSans_Bold());

        OurFeestextView.setTypeface(typeFaceClass.NotoSans_Bold());

        TransmittotheIRStextView.setTypeface(typeFaceClass.NotoSans_Bold());


    }


    private void onClick() {

        FormInterviewtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });

        TaxDuetextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });

        SummarytextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });

        ReviewtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });

        OurFeestextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });

        TransmittotheIRStextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: 11/18/2015

            }
        });
    }

    private void leftSelection(int sId) {

        switch (sId) {
            case 0:

                // TODO: 11/18/2015

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstselected);

                FormInterviewtextView.setTextColor(Color.WHITE);

                TaxDuetextView.setBackgroundResource(R.drawable.topnotselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topnotselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topnotselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topnotselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;


            case 1:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstcompletedselected);

                FormInterviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TaxDuetextView.setBackgroundResource(R.drawable.topselected);

                TaxDuetextView.setTextColor(Color.WHITE);

                SummarytextView.setBackgroundResource(R.drawable.topnotselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topnotselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topnotselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;

            case 2:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstcompletedselected);

                FormInterviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TaxDuetextView.setBackgroundResource(R.drawable.topcompletedselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topselected);

                SummarytextView.setTextColor(Color.WHITE);

                ReviewtextView.setBackgroundResource(R.drawable.topnotselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topnotselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;

            case 3:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstcompletedselected);

                FormInterviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TaxDuetextView.setBackgroundResource(R.drawable.topcompletedselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topcompletedselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topselected);

                ReviewtextView.setTextColor(Color.WHITE);

                OurFeestextView.setBackgroundResource(R.drawable.topnotselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;

            case 4:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstcompletedselected);

                FormInterviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TaxDuetextView.setBackgroundResource(R.drawable.topcompletedselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topcompletedselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topcompletedselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topselected);

                OurFeestextView.setTextColor(Color.WHITE);

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;

            case 5:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstcompletedselected);

                FormInterviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TaxDuetextView.setBackgroundResource(R.drawable.topcompletedselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topcompletedselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topcompletedselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topcompletedselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topselected);

                TransmittotheIRStextView.setTextColor(Color.WHITE);

                break;

            default:

                FormInterviewtextView.setBackgroundResource(R.drawable.topfirstselected);

                FormInterviewtextView.setTextColor(Color.WHITE);

                TaxDuetextView.setBackgroundResource(R.drawable.topnotselected);

                TaxDuetextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                SummarytextView.setBackgroundResource(R.drawable.topnotselected);

                SummarytextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                ReviewtextView.setBackgroundResource(R.drawable.topnotselected);

                ReviewtextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                OurFeestextView.setBackgroundResource(R.drawable.topnotselected);

                OurFeestextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                TransmittotheIRStextView.setBackgroundResource(R.drawable.topnotselected);

                TransmittotheIRStextView.setTextColor(mContext.getResources().getColor(R.color.textSelected));

                break;
        }
    }
}