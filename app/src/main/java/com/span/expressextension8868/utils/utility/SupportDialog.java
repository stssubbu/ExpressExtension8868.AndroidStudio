package com.span.expressextension8868.utils.utility;


import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.span.expressextension8868.R;

public class SupportDialog {

    Context mContext;

    public SupportDialog(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void showSupport() {
        try {
            final Dialog supportdialog = new Dialog(mContext);

            supportdialog.setCanceledOnTouchOutside(false);

            supportdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            supportdialog.setContentView(R.layout.support);

            WindowManager.LayoutParams widthheight = new WindowManager.LayoutParams();

            widthheight.height = LayoutParams.WRAP_CONTENT;
            widthheight.width = LayoutParams.WRAP_CONTENT;
            supportdialog.getWindow().setAttributes(widthheight);
            supportdialog.setCancelable(false);

            supportdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            LinearLayout rootLayout = (LinearLayout) supportdialog.findViewById(R.id.rootlayout);
            ImageView closebtnImageView = (ImageView) supportdialog.findViewById(R.id.closbtn);
            TextView emailsupport = (TextView) supportdialog.findViewById(R.id.emailsupport);
            TextView phonenumber = (TextView) supportdialog.findViewById(R.id.phonenumber);
            TextView phonetime = (TextView) supportdialog.findViewById(R.id.phonetime);
            TextView emailtime = (TextView) supportdialog.findViewById(R.id.emailtime);

            TypeFaceClass typeFaceClass = new TypeFaceClass(mContext);

            Overridefonts.overrideFonts(mContext, rootLayout);

            emailsupport.setTypeface(typeFaceClass.NotoSans_BoldItalic());
            phonenumber.setTypeface(typeFaceClass.NotoSans_BoldItalic());
            phonetime.setTypeface(typeFaceClass.NotoSans_BoldItalic());
            emailtime.setTypeface(typeFaceClass.NotoSans_BoldItalic());

            closebtnImageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    supportdialog.dismiss();
                }
            });
            supportdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            new SendException(mContext, e);
        }
    }
}
