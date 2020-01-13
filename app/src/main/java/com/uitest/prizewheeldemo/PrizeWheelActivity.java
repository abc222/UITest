package com.uitest.prizewheeldemo;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.uitest.R;

public class PrizeWheelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_wheel);
        final PrizeWheel prizeWheel = (PrizeWheel)findViewById(R.id.prize_wheel);

        prizeWheel.setRotateListener(new RotateListener() {
            @Override
            public void rotateBefore(ImageView goImg) {
                prizeWheel.startRotate(3);
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateEnd(int position, String des) {

            }
        });

        findViewById(R.id.start_rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prizeWheel.startRotate(5);
            }
        });
    }

}
