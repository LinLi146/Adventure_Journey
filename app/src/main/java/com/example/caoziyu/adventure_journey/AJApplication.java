package com.example.caoziyu.adventure_journey;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by CaoZiyu on 2017/6/9.
 */

public class AJApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirLTStd-Medium.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
