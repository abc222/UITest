package com.uitest.ratingdialog;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.uitest.R;

public class RatingDialog extends Dialog {

    private ImageView img_back;
    private RatingBar ratingBar;
    private TextView textView1;
    private TextView textView2;
    private Button encourage;
    private ImageView handPointUp;

    private Animation myAnimation_Alpha;
    private Animation myAnimation_Scale;
    private Animation myAnimation_Translate;
    private Animation myAnimation_Rotate;

    public float stars = 0;
    public int starTag = 0;

    public interface OnDialogClickListener {
        void onRatingChangedListener();

        void onEncourageButtonClickListener();

        void onImageBackButtonClickListenes();
    }

    private OnDialogClickListener onDialogClickListener;

    public RatingDialog(Context context) {
        super(context, R.style.Dialog);
    }

    public RatingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RatingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.apprater_dailog);

        initView();

        //设置外部点击Dialog不消失
        setCanceledOnTouchOutside(false);
    }

    public void initView() {
        img_back = (ImageView) findViewById(R.id.close);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
//        Animation rotation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
//        ratingBar.startAnimation(rotation);
        //ratingBar.setRating(5);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        encourage = (Button) findViewById(R.id.encourage_button);
        handPointUp = (ImageView) findViewById(R.id.hand_image);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onImageBackButtonClickListenes();
                }
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView1.setText(R.string.apprater_textview1_after);
                textView2.setVisibility(View.INVISIBLE);
                encourage.setVisibility(View.VISIBLE);
                stars = rating;
//                if(myAnimation_Translate.hasEnded()) {
//                    handPointUp.setVisibility(View.INVISIBLE);
//                }
                if(onDialogClickListener != null) {
                    onDialogClickListener.onRatingChangedListener();
                }
            }
        });
        encourage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onEncourageButtonClickListener();
                }
            }
        });

        ObjectAnimator animator = ObjectAnimator.ofFloat(handPointUp,"alpha",1,0,1);
        // 动画的持续时间
        animator.setDuration(200);
        // 正式开始启动执行动画
        animator.start();

        myAnimation_Alpha = new AlphaAnimation(0.1f, 1.0f);

        myAnimation_Translate = new TranslateAnimation(-520f, 0f, 0f, 0f);
        myAnimation_Translate.setDuration(1200);

        myAnimation_Scale = new ScaleAnimation(1.0f, 0.7f, 1.0f, 0.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
        myAnimation_Scale.setDuration(1000);

        myAnimation_Rotate = new RotateAnimation(0.0f, +350.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);

        handPointUp.startAnimation(myAnimation_Scale);
        mHandler.sendEmptyMessageDelayed(0,500);
        mHandler.sendEmptyMessageDelayed(1,1200);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    starTag = 1;
                    ratingBar.setRating(5);
                    break;
                case 1:
                    starTag = 0;
                    ratingBar.setRating(0);
                    break;
                    default:
            }
        }
    };
}
