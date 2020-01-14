package com.uitest.scratchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uitest.R;

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
//        ScratchImageView scratchImageView = new ScratchImageView(this);
//
//        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
//            @Override
//            public void onRevealed(ScratchImageView tv) {
//                // on reveal
//            }
//
//            @Override
//            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
//                // on image percent reveal
//            }
//        });
    }
}
