package com.example.xiaoxiong.uitest.ratingdialog;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xiaoxiong.uitest.R;

public class AppRaterActivity extends AppCompatActivity {

    private final static String TAG = "AppRaterActivity";
    public static final String GOOGLE_PLAY = "com.android.vending";
    private static final String marketLink = "market://details?id=";

    private RatingDialog ratingDialog;
    private Toast toast;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rater);
        ratingDialog = new RatingDialog(this);
        imageView = ratingDialog.findViewById(R.id.hand_image);
        ratingDialog.setOnDialogClickListener(new RatingDialog.OnDialogClickListener() {
            @Override
            public void onRatingChangedListener() {
                if (ratingDialog.stars == 5 && ratingDialog.starTag == 0) {
                    Log.d("AppRaterActivity", "5 stars");
                    goToGP();
                }
            }

            @Override
            public void onImageBackButtonClickListenes() {
                ratingDialog.dismiss();
                showThanksToast();
            }

            @Override
            public void onEncourageButtonClickListener() {
                ratingDialog.dismiss();
                if (ratingDialog.stars < 3) {
                    showThanksToast();
                } else {
                    goToGP();
                }
            }

        });

        ratingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                toast.cancel();
            }
        });
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingDialog.show();
            }
        });

    }

    private void goToGP() {
        Intent playIntent = new Intent(Intent.ACTION_VIEW);
        playIntent.setData(Uri.parse(marketLink + getApplication().getPackageName()));
        playIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        playIntent.setPackage(GOOGLE_PLAY);
        try {
            Log.d(TAG, "Goto Play");
            getApplication().startActivity(playIntent);
        } catch (Exception e) {
            Log.d(TAG, "Goto Play failed:", e);
        }
    }

    private void showThanksToast() {
        //注意Run On UI
        // 带文字
//                Toast toast = Toast.makeText(getApplicationContext(), "谢谢评价", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                LinearLayout toastView = (LinearLayout) toast.getView();
//                ImageView imageCodeProject = new ImageView(getApplicationContext());
//                imageCodeProject.setImageResource(R.drawable.thanks);
//                toastView.addView(imageCodeProject, 0);
//                toast.show();

        // 只有图片
        ImageView imageCodeProject = new ImageView(getApplicationContext());
        imageCodeProject.setImageResource(R.drawable.thanks);
        toast = new Toast(getApplicationContext());
        toast.setView(imageCodeProject);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
