package com.span.expressextension8868.utils.utility;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.span.expressextension8868.R;


public class MyCustomProgressDialog extends ProgressDialog {
    public AnimationDrawable animation;

    static MyCustomProgressDialog dialog;

    public static ProgressDialog ctor(Context context) {
        dialog = new MyCustomProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        return dialog;
    }

    public MyCustomProgressDialog(Context context) {
        super(context);
    }

    public MyCustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_progress_dialog);

        ImageView la = (ImageView) findViewById(R.id.animation);
        la.setBackgroundResource(R.drawable.custom_progress_dialog_animation);
        animation = (AnimationDrawable) la.getBackground();

    }

    @Override
    public void show() {

        try {
            super.show();
            animation.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (animation.isRunning()) {
            animation.stop();
        }

    }
}
