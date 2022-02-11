package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
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

        //配置Paint相关参数
        initPaintParams();
        //设置Canvas相关参数
        initCanvasParams(canvas);
    }

    /**
     * Canvas相关操作
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCanvasParams(Canvas canvas) {

        //绘制固定图形操作
        canvasFixedGraphics(canvas);

        //绘制自定义图形操作
        canvasCustomGraphics(canvas);
    }

    /**
     * TODO 绘制自定义图形操作
     *  drawPath(Path path, Paint paint) 画自定义图形
     *  Path 可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。把这些图形结合起来，就可以描述出很多复杂的图形。下面我就说一下具体的怎么把这些图形描述出来。
     *  Path 有两类方法，一类是直接描述路径的，另一类是辅助的设置或计算。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void canvasCustomGraphics(Canvas canvas) {
        Path path = new Path(); // 初始化 Path 对象
        /*
         * TODO 根据路径绘制一个❤
         */
       /* // 使用 path对图形进行描述
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        mPaint.setStyle(Paint.Style.FILL); //填充模式 如果配置为填充模式的时候 会自动补充内容 形成封闭图形
        mPaint.setColor(Color.RED); //设置图形颜色
        canvas.drawPath(path, mPaint); //根据路径绘制一个❤*/




        //TODO Path 方法第一类：直接描述路径。
        // 这一类方法还可以细分为两组：添加子图形和画线（直线或曲线）

        /*
         * TODO 第一组： addXxx() ——添加子图形
         *  绘制一个圆形
         *  radius: 圆的半径
         *  dir:是画圆的路径的方向。
         *  路径方向有两种：顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise) 。
         *  对于普通情况，这个参数填 CW 还是填 CCW 没有影响。它只是在需要填充图形 (Paint.Style 为 FILL 或 FILL_AND_STROKE) ，
         *  并且图形出现自相交时，用于判断填充范围的。
         */
        /*mPaint.setStyle(Paint.Style.STROKE);
        path.addCircle(300, 300, 100, Path.Direction.CCW);
        path.addCircle(400, 300, 100, Path.Direction.CCW);
        canvas.drawPath(path, mPaint);*/

        /*
         * TODO 可以看出，path.AddCircle(x, y, radius, dir) + canvas.drawPath(path, paint) 这种写法，和直接使用 canvas.drawCircle(x, y, radius, paint) 的效果是一样的，
         *  区别只是它的写法更复杂。所以如果只画一个圆，没必要用 Path，直接用 drawCircle() 就行了。drawPath() 一般是在绘制组合图形时才会用到的。
         *  其他的 Path.add-() 方法和这类似，例如：
         *  addOval(float left, float top, float right, float bottom, Direction dir) / addOval(RectF oval, Direction dir) 添加椭圆
         *  addRect(float left, float top, float right, float bottom, Direction dir) / addRect(RectF rect, Direction dir) 添加矩形
         *  addRoundRect(RectF rect, float rx, float ry, Direction dir) / addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Direction dir)
         *  / addRoundRect(RectF rect, float[] radii, Direction dir)
         *  / addRoundRect(float left, float top, float right, float bottom, float[] radii, Direction dir) 添加圆角矩形
         *  addPath(Path path) 添加另一个 Path
         *  上面这几个方法和 addCircle() 的使用都差不多，不再做过多介绍。
         */


        /*
         * TODO 第二组：xxxTo() ——画线（直线或曲线）
         *  这一组和第一组 addXxx() 方法的区别在于，第一组是添加的完整封闭图形（除了 addPath() ），而这一组添加的只是一条线。
         *  从当前位置向目标位置画一条直线， x 和 y 是目标位置的坐标。这两个方法的区别是，lineTo(x, y) 的参数是绝对坐标，
         *  而 rLineTo(x, y) 的参数是相对当前位置的相对坐标 （前缀 r 指的就是 relatively 「相对地」)。
         */
/*        mPaint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100); // 由当前位置 (0, 0) 向 (100, 100) 画一条直线
        path.rLineTo(100, 0); // 由上次画线结束得到位置 (100, 100) 向正右方 100 像素的位置画一条直线
        canvas.drawPath(path, mPaint);*/

        /*
         * TODO 通过 moveTo(x, y) 或 rMoveTo() 来改变当前位置，从而间接地设置这些方法的起点。
         *  moveTo(x, y) 虽然不添加图形，但它会设置图形的起点，所以它是非常重要的一个辅助方法。
         */
  /*      mPaint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100); // 由屏幕0,0到100,100的位置画一条斜线（坐标默认是上次画线结束的位置,如果是首次 则为0,0）
        path.moveTo(200, 100); // 移动当前画笔的坐标到200,100
        path.lineTo(200, 0); // 由屏幕200,100到屏幕200,0的位置画一条竖线
        canvas.drawPath(path,mPaint);*/

        /*
         * TODO 第二组还有两个特殊的方法： arcTo() 和 addArc()。它们也是用来画线的，但并不使用当前位置作为弧线的起点。
         *  这个方法和 Canvas.drawArc() 比起来，少了一个参数 useCenter，而多了一个参数 forceMoveTo 。
         *  少了 useCenter ，是因为 arcTo() 只用来画弧形而不画扇形，所以不再需要 useCenter 参数；
         *  而多出来的这个 forceMoveTo 参数的意思是，绘制是要「抬一下笔移动过去」，还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。
         *  构造:
         *  arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
         *  arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)
         *  arcTo(RectF oval, float startAngle, float sweepAngle) 画弧形
         */
/*        mPaint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100);
        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
        canvas.drawPath(path,mPaint);*/

        /*
         * TODO 又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。
         *  构造:
         *  addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
         *  addArc(RectF oval, float startAngle, float sweepAngle)
         */
        mPaint.setStyle(Paint.Style.STROKE);
        path.lineTo(100, 100);
        path.addArc(100, 100, 300, 300, -90, 90);
        canvas.drawPath(path,mPaint);
    }

    /**
     * 绘制固定图形操作
     */
    private void canvasFixedGraphics(Canvas canvas) {
        /*
         * TODO 绘制view底色
         */
       /* canvas.drawColor(Color.RED);
        canvas.drawRGB(100, 200, 100);
        canvas.drawARGB(100, 100, 200, 100);*/

        /*
         * TODO 绘制圆
         */
        //canvas.drawCircle(point.x / 2, point.y / 2, 200, mPaint);

        /*
         * TODO 绘制两个正方形
         */
/*        mPaint.setStyle(Paint.Style.FILL); //使用填充模式绘制
        canvas.drawRect(100, 100, 500, 500, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);//使用画线模式绘制
        canvas.drawRect(700, 100, 1100, 500, mPaint);*/

        /*
         * TODO 绘制点
         * setStrokeCap设置点的形状
         * （
         * 1.ROUND 圆头
         * 2.BUTT 平头
         * 3.SQUARE 方头
         * ）
         */
        //第一个点
/*        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(50, 50, mPaint);
        //第二个点
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(150, 50, mPaint);*/

        /*
         * TODO 批量绘制点
         */
        //点的集合
        /*float[] points = {
                50,50,
                100,100,
                150,150,
                200,200,
                250,250,
                300,300,
                350,350,
                400,400
        };
        canvas.drawPoints(points, 0 *//* 从集合的那个位置开始绘制*//*,
                points.length *//* 一共绘制集合中的几个元素 两个数为一个点*//*, mPaint);*/


        /*
         * TODO 奥运五环
         */

        /*mPaint.setStyle(Paint.Style.STROKE);  //画线模式（即勾边模式）
        mPaint.setStrokeWidth(15);//设置边距宽度

        mPaint.setColor(Color.BLUE); //设置内容颜色
        canvas.drawCircle(point.x / 2 - 220,point.y / 2,100,mPaint); //绘制圆形
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(point.x / 2,point.y / 2,100,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(point.x / 2 + 220,point.y / 2,100,mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(point.x / 2 - 120,point.y / 2 + 100,100,mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(point.x / 2 + 120,point.y / 2 + 100,100,mPaint);*/


        /*
         * TODO 椭圆
         */
/*        mPaint.setStyle(Paint.Style.FILL); //设置为填充模式
        canvas.drawOval(50, 50, 350, 200, mPaint);//绘制椭圆

        mPaint.setStyle(Paint.Style.STROKE); //设置为画线模式
        mPaint.setStrokeWidth(5); //设置边距宽度
        canvas.drawOval(400, 50, 700, 200, mPaint);//绘制椭圆

        RectF rectF = new RectF(750,50,1050,200);//通过 RectF来设置图形坐标点
        mPaint.setStyle(Paint.Style.FILL); //设置为填充模式
        canvas.drawOval(rectF, mPaint);//绘制椭圆*/

        /*
         * TODO 画线
         *  注意：由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。
         */
/*        mPaint.setStyle(Paint.Style.FILL); //设置为填充模式
        canvas.drawLine(50,50,500,50,mPaint); //绘制一条直线

        mPaint.setStyle(Paint.Style.STROKE); //设置为画线模式
        canvas.drawLine(50,100,500,300,mPaint);//绘制一条斜线*/

        /*
         * TODO 批量画线 效果为：工 口,共计7条线，数组中每四个坐标为一条线
         */
        /*float[] points = {
                20, 20, 120, 20,
                70, 20, 70, 120,
                20, 120, 120, 120,
                150, 20, 250, 20,
                150, 20, 150, 120,
                250, 20, 250, 120,
                150, 120, 250, 120};
        canvas.drawLines(points, mPaint); //默认加载数组中的全部点位
        canvas.drawLines(points, 0, points.length - 1, mPaint); //可以配置从数据中的那个位置开始画线,那个位置停止画线。*/

        /*
         * TODO 画圆角矩形
         * left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
         */
/*        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, mPaint); //方式一

        RectF rectF = new RectF(100,400,500,600);//通过 RectF来设置图形坐标点
        mPaint.setStyle(Paint.Style.FILL); //设置为填充模式
        canvas.drawRoundRect(rectF, 50, 50, mPaint);//方式二*/

        /*
         * TODO 绘制弧形或扇形
         *  drawArc() 是使用一个椭圆来描述弧形的。
         *  left, top, right, bottom 描述的是这个弧形所在的椭圆；
         *  startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         *  sweepAngle 是弧形划过的角度；
         *  useCenter 表示绘制的线条是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */
/*        mPaint.setStyle(Paint.Style.FILL); // 填充模式
        canvas.drawArc(200, 100, 800, 500,-110 , 100, true, mPaint); // 绘制扇形
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, mPaint); // 绘制弧形
        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形*/
    }

    /**
     * Paint相关操作
     */
    private void initPaintParams() {
        /*
         * TODO 设置绘制模式
         */
//        mPaint.setStyle(Paint.Style.FILL); //填充模式
        mPaint.setStyle(Paint.Style.STROKE);  //画线模式（即勾边模式）
        // mPaint.setStyle(Paint.Style.FILL_AND_STROKE);  //两种模式一并使用：既画线又填充。它的默认值是 FILL，填充模式。

        /*
         * TODO 如果绘制模式选择的是STROKE 或者 FILL_AND_STROKE
         * 就可以通过setStrokeWidth来设置线条的宽度
         */
        mPaint.setStrokeWidth(5);

        /*
         * TODO 设置绘制图形的颜色
         */
        mPaint.setColor(Color.BLACK);
//        mPaint.setARGB(100, 100, 200, 100);

        /**
         * TODO 开启抗锯齿
         * 1.setAntiAlias(true)
         * 2.在创建Paint时传递Paint.ANTI_ALIAS_FLAG参数。例：Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
         */
        mPaint.setAntiAlias(true);
    }
}
