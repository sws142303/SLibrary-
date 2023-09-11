package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mylibrary.swslibrary.R;

/**
 * @author Sws
 * @time 14:59 2022/2/22
 * @dec 练习绘制顺序-- 自定义View
 **/
public class MyDrawOrderView extends LinearLayout {
    private Paint mPaint = new Paint();

    public MyDrawOrderView(Context context) {
        super(context);
    }

    public MyDrawOrderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDrawOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * TODO 继承LinearLayout不会执行onDraw
     *  只会执行绘制子View的方法 dispatchDraw
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private void canvasCircle(Canvas canvas) {
        Log.e("Sws","canvasCircle");
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(200,200,100,mPaint);
        canvas.drawCircle(500,500,200,mPaint);
        canvas.drawCircle(800,800,100,mPaint);
        canvas.drawCircle(200,800,100,mPaint);
    }

    /**
     * TODO 绘制子View
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //画一些圆 覆盖掉子View
        canvasCircle(canvas);
    }

    /**
     * TODO 首先，再说一遍，这个方法是 API 23 才引入的，所以在重写这个方法的时候要确认你的 minSdk 达到了 23，不然低版本的手机装上你的软件会没有效果。
     *  主要是绘制：滑动边缘渐变、滑动条和前景。
     * @param canvas
     */
    @Override
    public void onDrawForeground(Canvas canvas) {
        /*
         * 如果你把绘制代码写在了 super.onDrawForeground() 的上面，
         * 绘制内容就会在 dispatchDraw() 和 super.onDrawForeground() 之间执行，那么绘制内容会盖住子 View，但被滑动边缘渐变、滑动条以及前景盖住：
         */
        super.onDrawForeground(canvas);
        /*
         * 如果你把绘制代码写在了 super.onDrawForeground() 的下面，
         * 绘制代码会在滑动边缘渐变、滑动条和前景之后被执行，那么绘制内容将会盖住滑动边缘渐变、滑动条和前景。
         */

//        canvas.drawTextRun();
    }
}
