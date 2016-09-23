package com.kimhyojung.android.rotatingbar;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageButton bar;
    int rotatingAngle;

    public static final int REQUEST_CODE = 456;
    private static final int RESULT_CODE = 123;
    public static final String PENDING_RESULT = "pending_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        bar = (ImageButton) findViewById(R.id.imageButton);


        btn.setOnClickListener(new View.OnClickListener (){       //new View.O후 엔터치면 자동 완성됨
            @Override
            public void onClick(View v) {

                PendingIntent pending = createPendingResult(REQUEST_CODE, new Intent(), 0);
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.putExtra(PENDING_RESULT, pending);
                startService(intent);
            }
        });

    }

    public void rotating(View player, float r){
        ObjectAnimator ani = ObjectAnimator.ofFloat(player,"rotation", r);
        ani.setDuration(2000);
        ani.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE && resultCode==RESULT_CODE) {
            int angle = data.getIntExtra(MyIntentService.RESULT, 0);
            rotating(bar, angle);
        }
    }

}
