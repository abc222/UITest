package com.uitest.scratchview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uitest.R;
import com.uitest.scratchview.guaguaka2.ScratchImageView;
import com.uitest.scratchview.guaguaka3.YScratchView;

/**
 * github: https://github.com/sharish/ScratchView
 *
 * isRevealed() - tells whether the text/image has been revealed.
 * reveal() - reveals the image/text if not revealed yet.
 * setRevealListener(IRevealListener) - a callback listener interface which gets called back when
 * user reveals the text/image through onReveal() method.
 */
public class ScratchTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_test);
        ScratchImageView scratchImageView = new ScratchImageView(this);

        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView tv) {
                // on reveal
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
                // on image percent reveal
            }
        });

        YScratchView mScratchView = findViewById(R.id.guaguaka3);

        mScratchView.setScratchListener(new YScratchView.ScratchListener() {
            @Override
            public void finish() {

            }
        });

//        LinearLayout layout=(LinearLayout) findViewById(R.id.main);
//        layout.addView(new CanvasTestView(this));

    }
}
