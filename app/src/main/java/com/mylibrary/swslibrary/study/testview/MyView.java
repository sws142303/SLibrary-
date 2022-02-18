package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDisplay().getRealSize(point);

        //Canvas的drawXXX()及Paint的基本使用
//        initCanvasAndPaint(canvas);

        //Paint详解
        PaintDetailsApi.getInstance().testPaintApi(canvas,getContext());

        //范围裁切和几何变换
        CanvasClipApi.getInstance().setPoint(point);
        CanvasClipApi.getInstance().initAPiTest(getContext(),canvas);


  }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCanvasAndPaint(Canvas canvas) {
        CanvasAndPaint.getInstance().init(mPaint,canvas,point);
        //配置Paint相关参数
        CanvasAndPaint.getInstance().initPaintParams();
        //绘制固定图形操作
        CanvasAndPaint.getInstance().canvasFixedGraphics(canvas);
        //绘制自定义图形操作
        CanvasAndPaint.getInstance().canvasCustomGraphics(canvas);
        //文字绘制
        CanvasAndPaint.getInstance().canvasText(canvas,"Hello World");
        //练习
        CanvasAndPaint.getInstance().test(canvas);
    }

}
