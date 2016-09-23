package com.kimhyojung.android.rotatingbar;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MyIntentService extends IntentService {

    public static final int REQUEST_CODE = 456;
    private static final int RESULT_CODE = 123;
    public static final String RESULT = "R_ANGLE";
    public static final String PENDING_RESULT = "pending_result";

    public MyIntentService() {
        super("MyIntentService");
    }

int rotatingAngle;

    protected void onHandleIntent(Intent intent) {

        for(int i=1;i<=10;i++) {

            try {
                Intent result = new Intent();
                rotatingAngle = i * 36;
                result.putExtra(RESULT, rotatingAngle);
                PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT);
                reply.send(this, RESULT_CODE, result);
                Log.i("결과", "MyIntentService 전송 시작");
                Thread.sleep(1000);
            } catch (Exception e) {}

        }
    }
}

