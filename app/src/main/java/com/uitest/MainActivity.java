package com.uitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uitest.animation.AnimatorActivity;
import com.uitest.broadcastdemo.BroadcastTestActivity;
import com.uitest.dropanimation.coindemo.DropCoinActivity;
import com.uitest.openscreenpic.GlideTestActivity;
import com.uitest.piechartdemo.PieViewTestActivity;
import com.uitest.prizewheeldemo.PrizeWheelActivity;
import com.uitest.ratingdialog.AppRaterActivity;
import com.uitest.scratchview.ScratchTestActivity;
import com.uitest.webviewtest.WebViewTestActivity;
import com.uitest.weeksighin.ui.activity.WeekSighinActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.prize_wheel_test).setOnClickListener(this);
        findViewById(R.id.pie_view_test).setOnClickListener(this);
        findViewById(R.id.rating_dialog_test).setOnClickListener(this);
        findViewById(R.id.webview_test).setOnClickListener(this);
        findViewById(R.id.glide_test).setOnClickListener(this);
        findViewById(R.id.weeksighin_test).setOnClickListener(this);
        findViewById(R.id.scratchview_test).setOnClickListener(this);
        findViewById(R.id.drop_test).setOnClickListener(this);
        findViewById(R.id.animator_test).setOnClickListener(this);
        findViewById(R.id.broadcast_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prize_wheel_test:
                Intent intent1 = new Intent(this, PrizeWheelActivity.class);
                startActivity(intent1);
                break;
            case R.id.pie_view_test:
                Intent intent2 = new Intent(this, PieViewTestActivity.class);
                startActivity(intent2);
                break;
            case R.id.rating_dialog_test:
                Intent intent3 = new Intent(this, AppRaterActivity.class);
                startActivity(intent3);
                break;
            case R.id.webview_test:
                Intent intent4 = new Intent(this, WebViewTestActivity.class);
                startActivity(intent4);
                break;
            case R.id.glide_test:
                Intent intent6 = new Intent(this, GlideTestActivity.class);
                startActivity(intent6);
                break;
            case R.id.weeksighin_test:
                Intent intent7 = new Intent(this, WeekSighinActivity.class);
                startActivity(intent7);
                break;
            case R.id.scratchview_test:
                Intent intent8 = new Intent(this, ScratchTestActivity.class);
                startActivity(intent8);
                break;
            case R.id.drop_test:
                Intent intent9 = new Intent(this, DropCoinActivity.class);
                startActivity(intent9);
                break;
            case R.id.animator_test:
                Intent intent10 = new Intent(this, AnimatorActivity.class);
                startActivity(intent10);
                break;
            case R.id.broadcast_test:
                Intent intent11 = new Intent(this, BroadcastTestActivity.class);
                startActivity(intent11);
                default:
                    break;
        }
    }
}
