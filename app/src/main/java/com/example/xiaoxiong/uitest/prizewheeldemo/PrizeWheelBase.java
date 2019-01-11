package com.example.xiaoxiong.uitest.prizewheeldemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.xiaoxiong.uitest.R;

import java.util.ArrayList;
import java.util.List;

public class PrizeWheelBase extends View {

    private static final String TAG = "PrizeWheel";

    //

    private float mAngle = 45;
    private int mTypeNum = 8;
    private int mMinTimes = 5;
    //每个扇形旋转的时间
    private int mVarTime = 75;
    private int mWidth, mHeight, mRadius;
    private Paint mPaint = new Paint();
    private Paint textPaint = new Paint();
    private List<Bitmap> mListBitmap;

    //动画回调监听
    private RotateListener rotateListener;

    // 颜色表
    private Integer[] myColors = {0xFDFAF4FF, 0xFBDED0FF, 0xF6C6A9FF, 0xF7E0CFFF, 0xFDFAF4FF, 0xF5C6A8FF,
            0xF7ECE7FF, 0xF6C6A9FF};

    // 文字表
    private String[] myDeses = {"10 积分", "20 积分", "50 积分", "谢谢参与", "10 积分", "20 积分", "50 积分", "谢谢参与"};

    // 图片表
    private Integer[] myIcons = {};//暂时未用到

    public PrizeWheelBase(Context context) {
        super(context);
        initPrizeWheel(context);
    }

    public PrizeWheelBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPrizeWheel(context);
    }

    private void initPrizeWheel(Context context) {
        mListBitmap = new ArrayList();
        for (int i = 0; i < mTypeNum; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.iphone);
            int ww = bitmap.getWidth();
            int hh = bitmap.getHeight();
            // 定义矩阵对象
            Matrix matrix = new Matrix();
            // 缩放原图
            matrix.postScale(1f, 1f);
            // 向左旋转mAngle度，旋转为正则向右旋转
            matrix.postRotate(mAngle * i);
            //bmp.getWidth(), 500分别表示重绘后的位图宽高
            Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, ww, hh, matrix, true);
            mListBitmap.add(dstbmp);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //视图是个正方形的 所以有宽就足够了 默认值是800 也就是WRAP_CONTENT的时候
        int desiredWidth = 800;
        int desiredHeigh = 800;


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            mWidth = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            mWidth = desiredWidth;
        }

        //Measure Width
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            mHeight = widthSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            mHeight = Math.min(desiredHeigh, heightSize);
        } else {
            //Be whatever you want
            mHeight = desiredWidth;
        }


        //绘制扇形的半径 防止边界溢出
        mRadius = (int) (Math.min(mWidth, mHeight) / 2 * 0.8);
        //MUST CALL THIS
        setMeasuredDimension(mWidth, mHeight);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mWidth = w;
//        mHeight = h;
//        mCenter = mWidth / 2;
//        //绘制扇形的半径 减掉50是为了防止边界溢出  具体效果注释掉-50测试一下
//        mRadius = mWidth / 2 - 50;
//
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 计算初始角度 从最上面开始绘制扇形，mAngle指的是每一个扇形的角度
        float startAngle = -mAngle / 2 - 90;
        // 将画布坐标原点移到位置中心
        canvas.translate(mWidth / 2, mHeight / 2);
        for (int i = 0; i < mTypeNum; i++) {
            // 设置绘制时画笔的颜色
            mPaint.setColor(myColors[i]);
            // 画一个扇形 指定范围 范围就是整个圆盘的大小
            RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
            canvas.drawArc(rectF, startAngle, mAngle, true, mPaint);
            // 画文字
            textPaint.setTextSize(60);
            drawText(startAngle, myDeses[i], mRadius, textPaint, canvas);
            // 画图片
            int imgWidth = mRadius / 3;
            // 计算实际宽高
            int w = ( int ) (Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mAngle * i)))) *
                    imgWidth + imgWidth * Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mAngle * i)))));
            int h = ( int ) (Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mAngle * i)))) *
                    imgWidth + imgWidth * Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mAngle * i)))));
            Log.d(TAG, "imgWidth：" + imgWidth + ", w: " + w + ", h: " + h);
            float angle = (float) Math.toRadians(startAngle + mAngle / 2);
            // 确定图片在圆弧中 中心点的位置 mRadius/2 表示图片中心在1/2半径处 mRadius/15 用来做微调
            float x = (float) ((mRadius / 2 + mRadius / 15) * Math.cos(angle));
            float y = (float) ((mRadius / 2 + mRadius / 15) * Math.sin(angle));
            //Log.d(TAG, "Math.cos(angle): " + Math.cos(angle) + ", Math.sin(angle): " + Math.sin(angle));
            //Log.d(TAG, "angle：" + angle + ", x: " + x + ", y: " + y);
            // 确定绘制图片的位置
            RectF rect1 = new RectF(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
            canvas.drawBitmap(mListBitmap.get(i), null, rect1, null);
            // 把图片的范围绘出来看看
//            Paint mTextPaint = new Paint();
//            mTextPaint.setColor(Color.BLACK);
//            mTextPaint.setStyle(Paint.Style.STROKE);
//            canvas.drawRect(rect1, mTextPaint);
            //最后绘制圆环
            Bitmap yuanHuan = BitmapFactory.decodeResource(getResources(), R.mipmap.yuanhuan);
            Rect mDestRect = new Rect(-mWidth / 2, -mWidth / 2, mWidth / 2, mWidth / 2);
            canvas.drawBitmap(yuanHuan, null, mDestRect, mPaint);
            //重置开始角度
            startAngle = startAngle + mAngle;
        }
    }

    private void drawText(float startAngle, String string, int radius, Paint textPaint, Canvas canvas) {
        //创建绘制路径
        Path circlePath = new Path();
        //范围也是整个圆盘
        RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        //给定扇形的范围
        circlePath.addArc(rectF, startAngle, mAngle);

        //圆弧的水平偏移 与路径起始点的水平偏移距离
        float vOffset = radius / 4;
        //圆弧的垂直偏移 与路径中心的垂直偏移量
        float textWidth = textPaint.measureText(string);
        float hOffset = (float) (Math.sin(mAngle / 2 / 180 * Math.PI) * radius) - textWidth / 2;//还没看明白
        //绘制文字
        canvas.drawTextOnPath(string, circlePath, hOffset, vOffset, textPaint);
    }

    //目前的角度
    private float currAngle = 0;
    //记录上次的位置
    private int lastPosition;

    /**
     * 外部调用 根据参数指定停在哪个扇形确定旋转的角度
     * pos 位置 1 开始 这里的位置上是按照逆时针递增的 比如当前指的那个选项是第一个  那么他左边的那个是第二个 以此类推
     */
    public void startRotate(final int pos) {
        // 最低圈数是mMinTimes圈
        int newAngle = ( int ) (360 * mMinTimes + (pos - 1) * mAngle + currAngle - (lastPosition == 0 ?
                0 : ((lastPosition - 1) * mAngle)));
        // 计算目前的角度划过的扇形份数
        int num = (int)((newAngle - currAngle) / mAngle);
        ObjectAnimator anim = ObjectAnimator.ofFloat(PrizeWheelBase.this,"rotation",currAngle, newAngle);
        currAngle = newAngle;
        lastPosition = pos;
        // 动画的持续时间
        anim.setDuration(num * mVarTime);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //将动画的过程态回调给调用者
                if ( rotateListener != null )
                    rotateListener.rotating(animation);
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //当旋转结束的时候回调给调用者当前所选择的内容
                if ( rotateListener != null ) {
//                    if ( mType == 1 ) {
//                        // 去空格和前后空格后输出
//                        String des = mDeses[(mTypeNum - pos + 1) %
//                                mTypeNum].trim().replaceAll(" ", "");
//                        rotateListener.rotateEnd(pos, des);
//                    } else {
//                        rotateListener.rotateEnd(pos, "");
//                    }
                }
            }
        });
        // 正式开始启动执行动画
        anim.start();
    }

}
