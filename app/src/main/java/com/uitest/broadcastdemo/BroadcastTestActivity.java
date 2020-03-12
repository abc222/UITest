package com.uitest.broadcastdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uitest.R;

public class BroadcastTestActivity extends AppCompatActivity {

    private Context mContext;
    private Button send, regist, notify;
    private MyReceiver myReceiver;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);
        mContext = this;
        send = (Button) findViewById(R.id.send_broadcast);
        regist = (Button) findViewById(R.id.regist_broadcast);
        notify = (Button) findViewById(R.id.send_notify);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intent = new IntentFilter("com.example.test.MY_BROADCAST");
                myReceiver = new MyReceiver();
                registerReceiver(myReceiver, intent);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.test.MY_BROADCAST");
                // intent.setClass(mContext, com.uitest.broadcastdemo.MyReceiver.class);
                intent.setComponent(new ComponentName("com.uitest.broadcastdemo", "com.uitest.broadcastdemo.MyReceiver"));
                sendBroadcast(intent);
            }
        });
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent intent = new Intent("com.example.test.MY_BROADCAST");
                // intent.setComponent(new ComponentName("com.uitest.broadcastdemo", "com.uitest.broadcastdemo.MyReceiver"));
                // intent.setClass(mContext, com.uitest.broadcastdemo.MyReceiver.class);

                 Intent intent = new Intent(mContext,MyReceiver.class);
                 intent.setAction("com.example.test.MY_BROADCAST");

                PendingIntent pendingIntent = PendingIntent
                        .getBroadcast(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);

                if (isTargetBeyondO(mContext)) {
                    mBuilder = new NotificationCompat.Builder(mContext, "test");
                } else {
                    mBuilder = new NotificationCompat.Builder(mContext);
                }
                //设置小图标
                mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
                //设置标题
                mBuilder.setContentTitle("这是标题");
                //设置通知正文
                mBuilder.setContentText("这是正文");
                Notification notification = mBuilder.build();
                notification.contentIntent = pendingIntent;
                if (mNotificationManager == null) {
                    mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
                }
                mNotificationManager.notify(0, notification);
            }
        });
    }

    public static boolean isTargetBeyondO(Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        if (appInfo == null) {
            return false;
        }
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                && appInfo.targetSdkVersion >= Build.VERSION_CODES.O);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
