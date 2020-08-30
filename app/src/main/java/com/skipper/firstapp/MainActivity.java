package com.skipper.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;
import java.util.HashMap;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button eventButton = findViewById(R.id.eventButton);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Track in app events */
                Map<String, Object> eventParameter = new HashMap<String, Object>();
                eventParameter.put(AFInAppEventParameterName.REVENUE, 200);
                eventParameter.put(AFInAppEventParameterName.PRICE, 99);
                eventParameter.put(AFInAppEventParameterName.CONTENT_TYPE, "Manufacturing");
                eventParameter.put(AFInAppEventParameterName.COUPON_CODE, "AmitB1000");
                eventParameter.put(AFInAppEventParameterName.CONTENT_ID, "12233445");
                eventParameter.put(AFInAppEventParameterName.CURRENCY, "INR");

                try {
                    AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), AFInAppEventType.PURCHASE, eventParameter);

                    TextView dispCoupon = findViewById(R.id.dispCoupon);
                    dispCoupon.setText("Use Coupon code: AmitB1000");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}