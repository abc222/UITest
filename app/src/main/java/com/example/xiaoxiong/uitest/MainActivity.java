package com.example.xiaoxiong.uitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.xiaoxiong.uitest.facebooktest.FacebookNativeAdActivity;
import com.example.xiaoxiong.uitest.piechartdemo.PieViewTestActivity;
import com.example.xiaoxiong.uitest.prizewheeldemo.PrizeWheelActivity;
import com.example.xiaoxiong.uitest.ratingdialog.AppRaterActivity;
import com.example.xiaoxiong.uitest.webviewtest.WebViewTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.prize_wheel_test).setOnClickListener(this);
        findViewById(R.id.pie_view_test).setOnClickListener(this);
        findViewById(R.id.rating_dialog_test).setOnClickListener(this);
        findViewById(R.id.webview_test).setOnClickListener(this);
        findViewById(R.id.facebook_test).setOnClickListener(this);
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
            case R.id.facebook_test:
                Intent intent5 = new Intent(this, FacebookNativeAdActivity.class);
                startActivity(intent5);
                default:
                    break;
        }
    }
}
