package com.span.expressextension8868.controller.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;

import com.span.expressextension8868.utils.utility.SendException;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;

/**
 * Created by STS-099 on 1/7/2016.
 */
public class UnCaughtException implements Thread.UncaughtExceptionHandler {
    private static final String RECIPIENT = "neelavathi@spanllc.com";

    private Thread.UncaughtExceptionHandler previousHandler;

    private Context mContext;

    public UnCaughtException(Context ctx) {

        mContext = ctx;
    }

    private StatFs getStatFs() {

        File path = Environment.getDataDirectory();
        return new StatFs(path.getPath());
    }

    private long getAvailableInternalMemorySize(StatFs stat) {

        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    private long getTotalInternalMemorySize(StatFs stat) {

        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    private void addInformation(StringBuilder message) {

        message.append("Locale: ").append(Locale.getDefault()).append('\n');
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi;
            pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            message.append("Version: ").append(pi.versionName).append('\n');
            message.append("Package: ").append(pi.packageName).append('\n');
        } catch (Exception e) {

            message.append("Could not get Version information for ").append(mContext.getPackageName());
        }
        message.append("Phone Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Android Version: ").append(android.os.Build.VERSION.RELEASE).append('\n');
        message.append("Board: ").append(android.os.Build.BOARD).append('\n');
        message.append("Brand: ").append(android.os.Build.BRAND).append('\n');
        message.append("Device: ").append(android.os.Build.DEVICE).append('\n');
        // message.append("Host: ").append(android.os.Build.HOST).append('\n');
        message.append("ID: ").append(android.os.Build.ID).append('\n');
        message.append("Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Product: ").append(android.os.Build.PRODUCT).append('\n');
        message.append("Type: ").append(android.os.Build.TYPE).append('\n');
        StatFs stat = getStatFs();
        message.append("Total Internal memory: ").append(getTotalInternalMemorySize(stat)).append('\n');
        message.append("Available Internal memory: ").append(getAvailableInternalMemorySize(stat)).append('\n');
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        try {
            StringBuilder report = new StringBuilder();
            Date curDate = new Date();
            report.append("Error Report collected on : ").append(curDate.toString()).append('\n').append('\n');
            report.append("Informations :").append('\n');
            addInformation(report);
            report.append('\n').append('\n');
            report.append("Stack:\n");
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            report.append(result.toString());
            printWriter.close();
            report.append('\n');
            report.append("****  End of current Report ***");

            sendErrorMail(report);
        } catch (Throwable ignore) {
        }
        previousHandler.uncaughtException(t, e);
    }

    public void sendErrorMail(final StringBuilder errorContent) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        new Thread() {

            @Override
            public void run() {

                Looper.prepare();
                builder.setTitle("Sorry...!");
                builder.create();
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        System.exit(0);
                    }
                });
                builder.setPositiveButton("Report", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
//                            Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                            String subject = "App Force Closed! Fix it!";
//                            StringBuilder body = new StringBuilder("Error");
//                            body.append('\n').append('\n');
//                            body.append(errorContent).append('\n').append('\n');
//                            // sendIntent.setType("text/plain");
//                            sendIntent.setType("message/rfc822");
//                            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{RECIPIENT});
//                            sendIntent.putExtra(Intent.EXTRA_TEXT, body.toString());
//                            sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//                            // sendIntent.setType("message/rfc822");
//                            // context.startActivity(Intent.createChooser(sendIntent,
//                            // "Error Report"));
//
//                            ((Activity) mContext).startActivity(sendIntent);
//                            dialog.dismiss();
//                            System.exit(0);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            new SendException(mContext, e);
                        }
                    }
                });
                builder.setMessage("Unfortunately, The UnitWiseDev Has Stopped \n" + errorContent);
                builder.show();
                Looper.loop();
                Looper.myLooper().quit();
                Looper.getMainLooper().quit();

            }
        }.start();
    }

}
