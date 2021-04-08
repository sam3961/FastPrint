package com.app.fastprint.utills;

import android.content.Context;
import android.graphics.Typeface;

public class UtilsFontFamily {

    public static Typeface typeFaceForRobotoRegular(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
    }

    public static Typeface typeFaceForRobotoMedium(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Medium.ttf");
    }

    public static Typeface typeFaceForRobotoLight(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Light.ttf");
    }

    public static Typeface typeFaceForRobotoBold(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Bold.ttf");
    }
}
