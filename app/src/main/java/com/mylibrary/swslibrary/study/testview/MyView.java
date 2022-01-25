package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author Sws
 * @time 16:53 2022/1/25
 * @dec
 **/
public class MyView extends View {
    private Context context;

    public MyView(Context context) {
        super(context);
        this.context = context;
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private Paint mPaint = new Paint();
    private Point point = new Point();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDisplay().getRealSize(point);

        /**************************Paint相关操作************************************/
        /*
         * 设置绘制模式
         */
        mPaint.setStyle(Paint.Style.FILL); //填充模式
//        mPaint.setStyle(Paint.Style.STROKE);  //画线模式（即勾边模式）
        // mPaint.setStyle(Paint.Style.FILL_AND_STROKE);  //两种模式一并使用：既画线又填充。它的默认值是 FILL，填充模式。

        /*
         * 如果绘制模式选择的是STROKE 或者 FILL_AND_STROKE
         * 就可以通过setStrokeWidth来设置线条的宽度
         */
        mPaint.setStrokeWidth(5); // 线条宽度为 20 像素

        /*
         * 设置绘制图形的颜色
         */
        mPaint.setColor(Color.BLUE);
//        mPaint.setARGB(100, 100, 200, 100);

        /**
         * 开启抗锯齿
         * 1.setAntiAlias(true)
         * 2.在创建Paint时传递Paint.ANTI_ALIAS_FLAG参数。例：Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
         */
        mPaint.setAntiAlias(true);

        /****************************Canvas相关操作************************************/

        /*
         * 绘制view底色
         */
//        canvas.drawColor(Color.RED);
        //canvas.drawRGB(100, 200, 100);
//        canvas.drawARGB(100, 100, 200, 100);

        /*
         * 绘制圆
         */
        //canvas.drawCircle(point.x / 2, point.y / 2, 200, mPaint);

        /*
         * 绘制两个正方形
         */
        //mPaint.setStyle(Paint.Style.FILL); //使用填充模式绘制
        //canvas.drawRect(100, 100, 500, 500, mPaint);
        //mPaint.setStyle(Paint.Style.STROKE);//使用画线模式绘制
        //canvas.drawRect(700, 100, 1100, 500, mPaint);

        /*
         * 绘制点
         * setStrokeCap设置点的形状
         * （
         * 1.ROUND 圆头
         * 2.BUTT 平头
         * 3.SQUARE 方头
         * ）
         */
        //第一个点
        //mPaint.setStrokeWidth(20);
        //mPaint.setStrokeCap(Paint.Cap.ROUND);
        //canvas.drawPoint(50, 50, mPaint);
        //第二个点
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        //canvas.drawPoint(150, 50, mPaint);

        /*
         * 批量绘制点
         */
        //点的集合
        float[] points = {
                50,50,
                100,100,
                150,150,
                200,200,
                250,250,
                300,300,
                350,350,
                400,400
        };
        canvas.drawPoints(points, 0 /* 跳过两个数，即前两个 0 */,
                points.length /* 一共绘制 8 个数（4 个点）*/, mPaint);




    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
