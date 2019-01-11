package com.example.xiaoxiong.uitest.piechartdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class PieView extends View {

    // 颜色表
    private int[] myColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null == mData) {
            return;
        }
        float currentStartAngle = mStartAngle;                  //当前起始角度
        canvas.translate(mWidth/2, mHeight/2);          //将画布坐标原点移到中心位置
        float r = (float)(Math.min(mWidth, mHeight)/2 * 0.8);   //饼状图半径
        RectF rectF = new RectF(-r, -r, r, r);
        for(int i = 0;i < mData.size();i ++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF, currentStartAngle, pie.getAngle(), true, mPaint);
            currentStartAngle += pie.getAngle();
        }
    }

    // 设置起始角度
    public void setStartAngle(float angle) {
        this.mStartAngle = angle;
        invalidate();
    }

    // 设置数据
    public void setData(ArrayList<PieData> data) {
        mData = data;
        initData(data);
        invalidate();
    }

    private void initData(ArrayList<PieData> data) {
        if(null == data || data.size() == 0) {
            return; //数据有问题，直接返回
        }
        float sumValue = 0;
        for(int i = 0;i < data.size();i ++) {
            PieData pie = data.get(i);
            sumValue += pie.getValue();     //计算数值和
            int j = i % myColors.length;    //设置颜色
            pie.setColor(myColors[j]);
        }
        float sumAngle = 0;
        for(int i = 0;i < data.size();i ++) {
            PieData pie = data.get(i);
            float percentage = pie.getValue() / sumValue;  //计算百分比
            float angle = percentage * 360;                     //计算角度

            pie.setPercentage(percentage);  //记录百分比
            pie.setAngle(angle);            //记录角度

            sumAngle += angle;
            Log.d("PieView", "angle: " + pie.getAngle());
        }
    }
}
