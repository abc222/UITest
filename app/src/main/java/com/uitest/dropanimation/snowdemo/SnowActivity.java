package com.uitest.dropanimation.snowdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.uitest.R;

public class SnowActivity extends AppCompatActivity {

    SnowView snow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow);

        snow = (SnowView) findViewById(R.id.snow);
        snow.LoadSnowImage();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        snow.SetView(dm.heightPixels, dm.widthPixels);
        update();
    }

    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            //snow.addRandomSnow();
            snow.invalidate();
            sleep(100);
        }
        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    /**
     * Handles the basic update loop, checking to see if we are in the running
     * state, determining if a move should be made, updating the snake's
     * location.
     */
    public void update() {
        snow.addRandomSnow();
        mRedrawHandler.sleep(600);
    }
}
