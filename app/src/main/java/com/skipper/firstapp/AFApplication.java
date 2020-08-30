package com.skipper.firstapp;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConversionListener;

import java.util.Map;

public class AFApplication extends Application {
    private static final String AF_DEV_KEY = "e********************K";

    @Override
    public void onCreate() {
        super.onCreate();

        /*  Setting Up Conversion Listener to get attribution data */
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {

            /* Returns the attribution data.*/
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {

                for (String attrName : conversionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }
        };

        try {
            /* API to enable installations detection. */
            AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
            AppsFlyerLib.getInstance().startTracking(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        /* Setting setDebugLog to true to view debug logs. */
        AppsFlyerLib.getInstance().setDebugLog(true);
    }
}
