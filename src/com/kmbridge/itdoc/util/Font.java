package com.kmbridge.itdoc.util;

import java.lang.reflect.Field;

import android.app.Application;
import android.graphics.Typeface;

public class Font extends Application {

  public void onCreate() {
        super.onCreate();
        initDefaultTypeface();
    }

    private void initDefaultTypeface() {
        try {
            Typeface defaultTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");
            final Field field = Typeface.class.getDeclaredField("DEFAULT");
            field.setAccessible(true);
            field.set(null, defaultTypeface);
        } catch ( NoSuchFieldException e ) {
             e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
         }
    }
}
