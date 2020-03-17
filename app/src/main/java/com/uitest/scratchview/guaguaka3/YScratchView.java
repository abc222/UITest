package com.uitest.scratchview.guaguaka3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.uitest.R;

/**
 * Created by YANG on 2019/5/7.
 * 自定义刮刮乐View
 */
public class YScratchView extends View {

    //灰色蒙层和底部图片
    private Bitmap mGrayBm, mBgBm, mBm;
    //手指触摸过的路径
    private Path mTouchPath;
    //灰色蒙层的画布
    private Canvas mGrayCanvas;
    //用来记录手指移动的坐标
    private float mMoveX, mMoveY;
    //路径画笔
    private Paint mPathPaint;
    //底部背景图画笔
    private Paint mBgPaint;
    //控件宽高
    private int mWidth, mHeight;
    //监听划出结果的线程
    private Thread mThread;
    //是否完成了控件测绘
    private boolean mIsInit;
    //当前刮开区域的像素值大小
    float mScratchSize;
    //是否刮出结果
    boolean mHasFinish;
    /**
     * 底部图片资源ID
     */
    private int mResId;
    /**
     * 刮开的路径的粗细
     */
    private int mScratchRadius;
    /**
     * 灰色蒙层的颜色
     */
    private int mMarkColor;

    public YScratchView(Context context) {
        super(context, null);
    }

    public YScratchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YScratchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        handleStyleable(context, attrs, defStyle);

        mBgPaint = new Paint();
        mBgPaint.setColor(mMarkColor);

        mPathPaint = new Paint();
        mPathPaint.setColor(mMarkColor);
        mPathPaint.setStrokeWidth(mScratchRadius);
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeJoin(Paint.Join.ROUND);
        //设置混合模式，路径划过的区域变为透明
        PorterDuffXfermode mDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        mPathPaint.setXfermode(mDuffXfermode);

        mTouchPath = new Path();

        if (mResId != -1) {
            mBgBm = BitmapFactory.decodeResource(getResources(), mResId);
        }
        //开启线程实时监听划出结果
        mThread = new Thread(mRunnable);
        mThread.start();
    }

    private void handleStyleable(Context context, AttributeSet attrs, int defStyle) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaveLoadView, defStyle, 0);
        try {
            mResId = ta.getInteger(R.styleable.YScratchView_scratch_drawable, -1);
            mScratchRadius = ta.getDimensionPixelSize(R.styleable.YScratchView_scratch_radius, 80);
            mMarkColor = ta.getColor(R.styleable.YScratchView_scratch_mark_color, Color.LTGRAY);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mResId == -1) {
            return;
        }
        setMeasuredDimension(mBgBm.getWidth(), mBgBm.getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right - left;
        mHeight = bottom - top;
        initGrayArea();
        mIsInit = true;
    }

    private void initGrayArea() {
        mGrayBm = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        //mBm = BitmapFactory.decodeResource(getResources(), R.drawable.motivate_scratch_surface).copy(Bitmap.Config.ARGB_8888, true);;
        mGrayCanvas = new Canvas(mGrayBm);
        //mGrayCanvas.drawBitmap(mGrayBm, 0, 0, null);
        mGrayCanvas.drawColor(Color.LTGRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mResId != -1) {
            canvas.drawBitmap(mBgBm, 0, 0, null);
        }
        canvas.drawBitmap(mGrayBm, 0, 0, mBgPaint);
        mGrayCanvas.drawRect(0, 0, mWidth, mHeight, mBgPaint);
        mGrayCanvas.drawPath(mTouchPath, mPathPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mHasFinish && mResId == -1) {
            return false;
        }
        mMoveX = event.getX();
        mMoveY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchPath.moveTo(mMoveX, mMoveY);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                mTouchPath.quadTo((endX - mMoveX) / 2 + mMoveX, (endY - mMoveY) / 2 + mMoveY, endX, endY);
                mMoveX = event.getX();
                mMoveY = event.getY();
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mThread.isInterrupted()) {
                return;
            }
            int pix[] = new int[mHeight * mWidth];
            //mGrayBm.getPixels(pix, 0, mWidth, 0, 0, mWidth, mHeight);
            while (!mHasFinish) {
                SystemClock.sleep(500);
                if (mIsInit) {
                    for (int i = 0; i < mWidth; i++) {
                        for (int j = 0; j < mHeight; j++) {
                            int index = i + j * mWidth;
                            //int pixel = pix[index];
                            int pixel = mGrayBm.getPixel(i, j);
                            if (pixel == 0) {
                                mScratchSize++;
                            }
                        }
                    }
                    checkFinish();
                }
                mScratchSize = 0;
            }

        }
    };

    /**
     * 计算检测是否划出结果
     */
    private void checkFinish() {
        float totalArea = mWidth * mHeight;
        if (mScratchSize / totalArea > 0.6f) {
            post(new Runnable() {
                @Override
                public void run() {
                    if (mListener != null) {
                        mListener.finish();
                    }
                }
            });
            mHasFinish = true;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //停止线程
        if (mThread != null) {
            mThread.interrupt();
        }
    }

    /**
     * 是否刮出结果的监听器
     */
    ScratchListener mListener;

    public void setScratchListener(ScratchListener mListener) {
        this.mListener = mListener;
    }

    public interface ScratchListener {
        void finish();
    }

}
