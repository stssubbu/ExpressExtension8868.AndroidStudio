package com.span.expressextension8868.utils.utility;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFaceClass {

    Typeface tf;
    Context mContext;

    public TypeFaceClass(Context context) {

        mContext = context;
    }

    public Typeface fontawesome_webfont() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont.ttf");
    }


    public Typeface NotoSans_Regular() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Regular.ttf");
    }


    public Typeface NotoSans_Bold() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Bold.ttf");
    }

    public Typeface NotoSans_Italic() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-Italic.ttf");
    }

    public Typeface NotoSans_BoldItalic() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "NotoSans-BoldItalic.ttf");
    }

    public Typeface RobotoCondensed() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Condensed.ttf");
    }

    public Typeface RobotoLight() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Light.ttf");
    }

    public Typeface RobotoMedium() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Medium.ttf");
    }

    public Typeface RobotoBold() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "RobotoCondensed-Bold.ttf");
    }

    public Typeface RobotoBoldItalic() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "RobotoCondensed-BoldItalic.ttf");
    }

    public Typeface RobotoItalic() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "RobotoCondensed-Italic.ttf");
    }

    public Typeface RobotoLightItalic() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "RobotoCondensed-LightItalic.ttf");
    }

    public Typeface OCRAMedium() {
        return tf = Typeface.createFromAsset(mContext.getAssets(), "ocra-medium.ttf");
    }

}
