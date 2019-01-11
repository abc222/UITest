package com.example.xiaoxiong.uitest.prizewheeldemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xiaoxiong.uitest.R;

public class PrizeWheel extends RelativeLayout {

    private PrizeWheelBase mPrizeWheelBase;
    private Context mContext;
    private ImageView mStart;
    private RotateListener rotateListener;

    //记录当前是否是第一次回调onMeasure 只有第一次设置中间图片大小
    private boolean isFirst = true;

    public PrizeWheel(Context context) {
        super(context);
        init(context);
    }

    public PrizeWheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PrizeWheel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setRotateListener(RotateListener rotateListener) {
        this.rotateListener = rotateListener;
    }

    // 开始抽奖的图标
    private Integer myGoImg;

    private void init(Context context) {
        // 添加底盘
        mPrizeWheelBase = new PrizeWheelBase(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mPrizeWheelBase.setLayoutParams(layoutParams);
        addView(mPrizeWheelBase);

        // 添加开始按钮
        mStart = new ImageView(context);
        mStart.setImageResource(R.mipmap.node);

        // 给开始图片设置LayoutParams
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
        mStart.setLayoutParams(layoutParams1);
        addView(mStart);

        mStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateListener.rotateBefore((ImageView)v);
            }
        });
    }

    /**
     * 开始旋转
     *
     * @param position 旋转最终的位置 注意 从1 开始 而且是逆时针递增
     */
    public void startRotate(int position) {
        if(mPrizeWheelBase != null)
            mPrizeWheelBase.startRotate(position);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //视图是个正方形的 所以有宽就足够了 默认值是500 也就是WRAP_CONTENT的时候
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        // Children are just made to fill our space.
        final int childWidthSize = getMeasuredWidth();

        // onMeasure调用获取到当前视图大小之后，
        // 手动按照一定的比例计算出中间开始按钮的大小，
        // 再设置给那个按钮，防止用户传的图片不合适
        // 只设置一次
        if ( isFirst ) {
            isFirst = false;
            // 获取中间按钮的大小
            // 当view的可见状态发生变化的时候回调OnGlobalLayoutListener的onGlobalLayout()函数
            // 这个时候View的宽高肯定也是已经测量好了，所以这个时机是可以得到view的宽高的
            ViewTreeObserver vto = mStart.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi( Build.VERSION_CODES.KITKAT )
                @Override
                public void onGlobalLayout() {
                    mStart.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    float w = mStart.getMeasuredWidth();
                    float h = mStart.getMeasuredHeight();
                    //计算新的大小 默认为整个大小最大值的0.2 -> 尝试出一个合适的值
                    int newW = ( int ) ((( float ) childWidthSize) * 0.2);
                    int newH = ( int ) ((( float ) childWidthSize) * 0.2 * h / w);
                    ViewGroup.LayoutParams layoutParams = mStart.getLayoutParams();
                    layoutParams.width = newW;
                    layoutParams.height = newH;
                    mStart.setLayoutParams(layoutParams);
                }
            });
        }
    }
}
