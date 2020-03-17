package com.uitest.scratchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class CanvasTestView extends View {

    public CanvasTestView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建笔刷
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式
        mPaint.setStrokeWidth(10f);         //设置画笔宽度

        // canvas 简单绘图
        // canvas.drawLine(300,300,500,600,mPaint);

        // canvas 平移
        // 在坐标原点绘制一个黑色圆形
//        mPaint.setColor(Color.BLACK);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);
//
//        // 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100,mPaint);

        // path demo1
//        canvas.translate(300, 300);
//
//        Path path = new Path();                     // 创建Path
//
//        path.lineTo(200, 200);                      // lineTo
//        path.lineTo(200,0);
//
//        canvas.drawPath(path, mPaint);              // 绘制Path

        // path demo2
        canvas.translate(300, 300);  // 移动坐标系
        canvas.scale(1, -1);                         // <-- 注意 翻转y坐标轴
        Path path = new Path();
        Path src = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);
        path.addPath(src, 0, 200);
        canvas.drawPath(path, mPaint); // 绘制合并后的路径
    }
}
