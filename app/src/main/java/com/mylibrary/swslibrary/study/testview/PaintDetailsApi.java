package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposePathEffect;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;

import com.mylibrary.swslibrary.R;

/**
 * @author Sws
 * @time 13:42 2022/2/15
 * @dec Paint详解 (详情：https://rengwuxian.com/ui-1-2/)
 **/
public class PaintDetailsApi {

    private static PaintDetailsApi instance;
    private Paint mPaint = new Paint();

    synchronized static PaintDetailsApi getInstance() {
        if (instance == null) {
            synchronized (PaintDetailsApi.class) {
                instance = new PaintDetailsApi();
            }
        }
        return instance;
    }

    /**
     * TODO Paint 的 API 大致可以分为 4 类： 颜色, 效果, drawText()相关, 初始化。
     */
    public void testPaintApi(Canvas canvas, Context context) {
        //颜色相关Api
        initColorApi(canvas, context);
        //效果相关Api
        initEffectApi(canvas, context);
        //drawText相关Api
        initDrawTextApi(canvas,context);
        //初始化相关Api
        initPaintApi(canvas,context);
    }

    /**
     * TODO 初始化相关Api
     *  这一类方法很简单，它们是用来初始化 Paint 对象，或者是批量设置 Paint 的多个属性的方法。
     */
    private void initPaintApi(Canvas canvas, Context context) {
        /*//TODO 重置 Paint 的所有属性为默认值。相当于重新 new 一个，不过性能当然高一些啦。
        mPaint.reset();*/

        /*//TODO set(Paint src) 把 src 的所有属性全部复制过来。相当于调用 src 所有的 get 方法，然后调用这个 Paint 的对应的 set 方法来设置它们。
        mPaint.set(new Paint());*/

        /*//TODO setFlags(int flags) 批量设置 flags。相当于依次调用它们的 set 方法。
        // setFlags(flags) 对应的 get 方法是 int getFlags()。
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG); //这行代码，和下面这两行是等价的：

        mPaint.setAntiAlias(true);
        mPaint.setDither(true);*/
    }

    private void initDrawTextApi(Canvas canvas, Context context) {

    }

    /**
     * TODO 效果类的 API ，指的就是抗锯齿、填充/轮廓、线条宽度等等这些。
     */
    private void initEffectApi(Canvas canvas, Context context) {

        /*//TODO 设置抗锯齿：setAntiAlias (boolean aa)
        // 抗锯齿默认是关闭的，如果需要抗锯齿，需要显式地打开。另外，除了 setAntiAlias(aa) 方法，打开抗锯齿还有一个更方便的方式：构造方法。
        // 创建 Paint 对象的时候，构造方法的参数里加一个 ANTI_ALIAS_FLAG 的 flag，就可以在初始化的时候就开启抗锯齿。
        mPaint.setAntiAlias(true); //开启抗锯齿方式一,默认是关闭的。
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); //开启抗锯齿方式二*/

        /*//TODO 设置图形是线条风格还是填充风格的（也可以二者并用）: setStyle(Paint.Style style)
        // FILL 模式是默认模式，所以如果之前没有设置过其他的 Style，可以不用 setStyle(Paint.Style.FILL) 这句。

        // FILL 模式，填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 300, 200, mPaint);
        // STROKE 模式，画线
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(800, 300, 200, mPaint);
        // FILL_AND_STROKE 模式，填充 + 画线
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(300, 800, 200, mPaint);*/

        /*
         * TODO 设置线条形状
         *  设置线条形状的一共有 4 个方法：setStrokeWidth(float width), setStrokeCap(Paint.Cap cap), setStrokeJoin(Paint.Join join), setStrokeMiter(float miter) 。
         */
        /*//TODO 方法1：setStrokeWidth(float width)
        // 设置线条宽度。单位为像素，默认值是 0。
        // 问题思考：
        // 默认情况下，线条宽度为 0，但你会发现，这个时候它依然能够画出线，线条的宽度为 1 像素。那么它和线条宽度为 1 有什么区别呢？
        // 其实这个和后面要讲的一个「几何变换」有关：你可以为 Canvas 设置 Matrix 来实现几何变换（如放大、缩小、平移、旋转），
        // 在几何变换之后 Canvas 绘制的内容就会发生相应变化，包括线条也会加粗，例如 2 像素宽度的线条在 Canvas 放大 2 倍后会被以 4 像素宽度来绘制。而当线条宽度被设置为 0 时，
        // 它的宽度就被固定为 1 像素，就算 Canvas 通过几何变换被放大，它也依然会被以 1 像素宽度来绘制。Google 在文档中把线条宽度为 0 时称作「hairline mode（发际线模式）」。
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(150, 125, 100, mPaint);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(400, 125, 100, mPaint);
        mPaint.setStrokeWidth(40);
        canvas.drawCircle(650, 125, 100, mPaint);*/

        /*//TODO 方法2：setStrokeCap(Paint.Cap cap)
        // 设置线头的形状。线头形状有三种：BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT。
        // 当线条的宽度是 1 像素时，这三种线头的表现是完全一致的，全是 1 个像素的点；而当线条变粗的时候，它们就会表现出不同的样子：
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        //平头
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 100, 500, 100, mPaint);
        //方头
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 300, 500, 300, mPaint);
        //圆头
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 500, 500, 500, mPaint);*/

        /*//TODO 方式3：setStrokeJoin(Paint.Join join)
        // 设置拐角的形状。有三个值可以选择：MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER(尖角)。
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(500, 100);
        path.lineTo(100, 500);

        //尖角
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, mPaint);

        //平角
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);
//        canvas.drawPath(path, mPaint);

        //圆角
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        canvas.drawPath(path, mPaint);*/

        /*//TODO 方式4：setStrokeMiter(float miter)
        // 这个方法是对于 setStrokeJoin() 的一个补充，它用于设置 MITER 型拐角的延长线的最大值。所谓「延长线的最大值」，是这么一回事：
        // 当线条拐角为 MITER 时，拐角处的外缘需要使用延长线来补偿：而这种补偿方案会有一个问题：如果拐角的角度太小，就有可能由于出现连接点过长的情况。
        // 所以为了避免意料之外的过长的尖角出现， MITER 型连接点有一个额外的规则：当尖角过长时，自动改用 BEVEL（平头） 的方式来渲染连接点。
        // 至于多尖的角属于过于尖，尖到需要转为使用 BEVEL 来绘制，则是由一个属性控制的，而这个属性就是 setStrokeMiter(miter) 方法中的 miter 参数。
        // miter 参数是对于转角长度的限制，具体来讲，是指尖角的外缘端点和内部拐角的距离与线条宽度的比。这个 miter limit 的默认值是 4，对应的是一个大约 29° 的锐角：
//        mPaint.setStrokeMiter(4);*/

        /*//TODO 色彩优化
        // Paint 的色彩优化有两个方法： setDither(boolean dither) 和 setFilterBitmap(boolean filter) 。它们的作用都是让画面颜色变得更加「顺眼」，但原理和使用场景是不同的。

        //1.setDither(boolean b) 是否增加抖动
        // 只要加这么一行代码，之后的绘制就是加抖动的了。
        // 不过对于现在（2017年）而言， setDither(dither) 已经没有当年那么实用了，
        // 因为现在的 Android 版本的绘制，默认的色彩深度已经是 32 位的 ARGB_8888 ，效果已经足够清晰了。
        // 只有当你向自建的 Bitmap 中绘制，并且选择 16 位色的 ARGB_4444 或者 RGB_565 的时候，开启它才会有比较明显的效果。
        mPaint.setDither(true);

        //2.setFilterBitmap(boolean filter) 设置是否使用双线性过滤来绘制 Bitmap 。
        // 图像在放大绘制的时候，默认使用的是最近邻插值过滤，这种算法简单，但会出现马赛克现象；而如果开启了双线性过滤，就可以让结果图像显得更加平滑。
        mPaint.setFilterBitmap(true);*/

        //TODO setPathEffect(PathEffect effect)
        // 使用 PathEffect 来给图形的轮廓设置效果。对 Canvas 所有的图形绘制有效，也就是 drawLine() drawCircle() drawPath() 这些方法。
        // PathEffect 分为两类:
        // 单一效果的 CornerPathEffect DiscretePathEffect DashPathEffect PathDashPathEffect,
        // 和组合效果的 SumPathEffect ComposePathEffect。
        // 注意： PathEffect 在有些情况下不支持硬件加速，需要关闭硬件加速才能正常使用：
        // 1.Canvas.drawLine() 和 Canvas.drawLines() 方法画直线时，setPathEffect() 是不支持硬件加速的；
        // 2.PathDashPathEffect 对硬件加速的支持也有问题，所以当使用 PathDashPathEffect 的时候，最好也把硬件加速关了。

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        Path path = new Path();
        path.moveTo(100,500);
        path.lineTo(300,50);
        path.lineTo(700,600);
        path.lineTo(1000,100);
        //绘制原图形
        //canvas.drawPath(path,mPaint);

        /*//TODO 一、CornerPathEffect，把所有的拐角变成圆角。
        // 构造参数：radius（圆角的半径）
        PathEffect cornerPathEffect = new CornerPathEffect(20);
        mPaint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path,mPaint);*/

        /*//TODO 二、DiscretePathEffect。把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定。
        // DiscretePathEffect 具体的做法是，把绘制改为使用定长的线段来拼接，并且在拼接的时候对路径进行随机偏离。
        // 它的构造方法 DiscretePathEffect(float segmentLength, float deviation) 的两个参数中，
        // segmentLength 是用来拼接的每个线段的长度，
        // deviation 是偏离量。这两个值设置得不一样，显示效果也会不一样
        PathEffect discretePathEffect = new DiscretePathEffect(10, 10);
        mPaint.setPathEffect(discretePathEffect);
        canvas.drawPath(path,mPaint);*/

        /*//TODO 三、DashPathEffect。使用虚线来绘制线条。
        // 它的构造方法 DashPathEffect(float[] intervals, float phase) 中，
        // 第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素个数必须为偶数（最少是 2 个），按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
        // 例如 [20, 5, 10, 5]  就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；第二个参数 phase 是虚线的偏移量。
        PathEffect dashPathEffect = new DashPathEffect(new float[]{20, 5, 10, 5,80,10}, 0);
        mPaint.setPathEffect(dashPathEffect);
        canvas.drawPath(path,mPaint);*/

        /*//TODO 四、PathDashPathEffect。这个方法比 DashPathEffect 多一个前缀 Path ，所以顾名思义，它是使用一个 Path 来绘制「虚线」。
        // 它的构造方法 PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style) 中，
        // shape 参数是用来绘制的 Path ；
        // advance 是两个相邻的 shape 段之间的间隔，不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
        // phase 和 DashPathEffect 中一样，是虚线的偏移；
        // 最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。style 的类型为 PathDashPathEffect.Style ，是一个 enum。包括{TRANSLATE（位移），ROTATE（旋转），MORPH（变体）}
        Path dashPath = new Path();
        dashPath.moveTo(0,20);
        dashPath.lineTo(20,0);
        dashPath.lineTo(40,20);
        dashPath.close();
        PathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 40, 0,
                PathDashPathEffect.Style.MORPH);
        mPaint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path,mPaint);*/

        /*//TODO 五、SumPathEffect。这是一个组合效果类的 PathEffect 。它的行为特别简单，就是分别按照两种 PathEffect 分别对目标进行绘制。（相当于用两种绘制模式在相同的位置绘制两次图形）
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        PathEffect sumPathEffect = new SumPathEffect(dashEffect, discreteEffect);
        mPaint.setPathEffect(sumPathEffect);
        canvas.drawPath(path,mPaint);*/

        /*//TODO 六、ComposePathEffect。这也是一个组合效果类的 PathEffect 。不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        // 它的构造方法 ComposePathEffect(PathEffect outerpe, PathEffect innerpe) 中的两个 PathEffect 参数，
        // innerpe 是先应用的，
        // outerpe 是后应用的。所以上面的代码就是「先偏离，再变虚线」。而如果把两个参数调换，就成了「先变虚线，再偏离」。
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        PathEffect composePathEffect = new ComposePathEffect(dashEffect, discreteEffect);
        mPaint.setPathEffect(composePathEffect);
        canvas.drawPath(path,mPaint);*/

        /*
         * TODO setShadowLayer(float radius, float dx, float dy, int shadowColor) 设置的是「附加效果」，也就是基于在绘制内容的额外效果。
         *  在之后的绘制内容下面加一层阴影。
         *  构造参数：
         *  radius 是阴影的模糊范围；
         *  dx dy 是阴影的偏移量；
         *  shadowColor 是阴影的颜色
         *  如果要清除阴影层，使用 clearShadowLayer() 。
         *  注意点：
         *  1.在硬件加速开启的情况下， setShadowLayer() 只支持文字的绘制，文字之外的绘制必须关闭硬件加速才能正常绘制阴影。
         *  2.如果 shadowColor 是半透明的，阴影的透明度就使用 shadowColor 自己的透明度；而如果 shadowColor 是不透明的，阴影的透明度就使用 paint 的透明度。
         */
        /*mPaint.setShadowLayer(10, 0, 0, Color.RED);
        mPaint.setTextSize(100);
        canvas.drawText("setShadowLayer", 80, 300, mPaint);*/

        /*
         * TODO setMaskFilter(MaskFilter maskfilter)
         *  为之后的绘制设置 MaskFilter。上一个方法 setShadowLayer() 是设置的在绘制层下方的附加效果；而这个 MaskFilter 和它相反，设置的是在绘制层上方的附加效果。
         *  MaskFilter 有两种： BlurMaskFilter 和 EmbossMaskFilter.
         */

       /* //TODO BlurMaskFilter (模糊效果的 MaskFilter。)
        // 构造参数：
        // radius 参数是模糊的范围
        // style 是模糊的类型。一共有四种：NORMAL（内外都模糊绘制）,SOLID（内部正常绘制，外部模糊）,INNER（内部模糊，外部不绘制）,OUTER（内部不绘制，外部模糊）。
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap, 100, 100, mPaint);*/

        /*//TODO EmbossMaskFilter (浮雕效果的 MaskFilter。)
        // 构造参数：
        // direction 是一个 3 个元素的数组，指定了光源的方向；
        // ambient 是环境光的强度，数值范围是 0 到 1；
        // specular 是炫光的系数；
        // blurRadius 是应用光线的范围。
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
        canvas.drawBitmap(bitmap, 100, 100, mPaint);*/

        //TODO 获取绘制的 Path,根据 paint 的设置，计算出绘制 Path 或文字时的实际 Path。
        // 详情请见文件描述中的网页。（https://rengwuxian.com/ui-1-2/）
    }

    /**
     * TODO
     * Canvas 绘制的内容，有三层对颜色的处理：
     * 1.Canvas 的颜色填充类方法 drawColor/RGB/ARGB() 的颜色，是直接写在方法的参数里，通过参数来设置的；
     * 2.drawBitmap() 的颜色，是直接由 Bitmap 对象来提供的；
     * 3.除此之外，是图形和文字的绘制，它们的颜色就需要使用 paint 参数来额外设置了。Paint 设置颜色的方法有两种：一种是直接用 Paint.setColor/ARGB() 来设置颜色，另一种是使用 Shader 来指定着色方案。
     * 这里主要介绍Paint相关APi操作
     */
    private void initColorApi(Canvas canvas, Context context) {
        /*
         * TODO Paint颜色的第一层处理：直接设置颜色（setColor,setARGB,setShader）
         */

        /*//TODO 方式一：setColor(int color)，setColor() 对应的 get 方法是 getColor()
        mPaint.setColor(Color.parseColor("#009688"));
        canvas.drawRect(30, 30, 230, 180, mPaint);
        mPaint.setColor(Color.parseColor("#FF9800"));
        canvas.drawLine(300, 30, 450, 180, mPaint);
        mPaint.setColor(Color.parseColor("#E91E63"));
        canvas.drawText("HenCoder", 500, 130, mPaint);*/

        /*//TODO 方式二： setARGB(int a, int r, int g, int b)。其实和 setColor(color) 都是一样一样儿的，
        // 只是它的参数用的是更直接的三原色与透明度的值。实际运用中，setColor() 和 setARGB() 哪个方便和顺手用哪个吧。
        mPaint.setARGB(100, 255, 0, 0);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        mPaint.setARGB(100, 0, 0, 0);
        canvas.drawLine(0, 0, 200, 200, mPaint);*/

        /*
         * TODO setShader(Shader shader) 设置 Shader(着色器)
         *  除了直接设置颜色， Paint 还可以使用 Shader 。
         *  Shader 这个英文单词很多人没有见过，它的中文叫做「着色器」，也是用于设置绘制颜色的。
         *  「着色器」不是 Android 独有的，它是图形领域里一个通用的概念，它和直接设置颜色的区别是，着色器设置的是一个颜色方案，或者说是一套着色规则。
         *  当设置了 Shader 之后，Paint 在绘制图形和文字时就不使用 setColor/ARGB() 设置的颜色了，而是使用 Shader 的方案中的颜色。
         *  在 Android 的绘制里使用 Shader ，并不直接用 Shader 这个类，而是用它的几个子类。
         *  具体来讲有 LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader 这么几个：
         */

        /*//TODO 方式一：LinearGradient 线性渐变
        // 构造参数：x0 y0 x1 y1：渐变的两个端点的位置。
        // color0 color1 是端点的颜色。
        // tile：端点范围之外的着色规则，类型是 TileMode。
        // TileMode一共有 3 个值可选： CLAMP, MIRROR 和 REPEAT。CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。
        // 注意：在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用。
        Shader linearGradient = new LinearGradient(
                100, 100, 500, 500,
                Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),
                Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        canvas.drawCircle(300,300,200,mPaint);*/

        /*//TODO 方式二：RadialGradient 辐射渐变（辐射渐变很好理解，就是从中心向周围辐射状的渐变）
        // 构造参数：
        // 1.centerX
        // 2.centerY：辐射中心的坐标
        // 3.radius：辐射半径
        // 4.centerColor：辐射中心的颜色
        // 5.edgeColor：辐射边缘的颜色
        // 6.tileMode：辐射范围之外的着色模式。（取值同LinearGradient）
        Shader radialGradient = new RadialGradient(
                300, 300,
                200,
                Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"),
                Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(300,300,200,mPaint);*/

        /*//TODO 方式三：SweepGradient 扫描渐变
        // 构造参数：
        // 1.cx
        // 2.cy ：扫描的中心
        // 3.color0：扫描的起始颜色
        // 4.color1：扫描的终止颜色
        Shader sweepGradient = new SweepGradient(
                300, 300,
                Color.RED,
                Color.YELLOW);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(300, 300, 200, mPaint);*/

        /*//TODO 方式四： BitmapShader。用 Bitmap 来着色（终于不是渐变了）。其实也就是用 Bitmap 的像素来作为图形或文字的填充。
        // 构造参数：
        // 1.bitmap：用来做模板的 Bitmap 对象
        // 2.tileX：横向的 TileMode。（取值同LinearGradient）
        // 3.tileY：纵向的 TileMode。（取值同LinearGradient）
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        Shader bitmapShader = new BitmapShader(
                bitmap,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT
        );
        mPaint.setShader(bitmapShader);
        canvas.drawRect(new RectF(0,0,800,500), mPaint);*/

        /*//TODO 方式五：ComposeShader 混合着色器
        // 构造参数：
        // 1.shaderA, shaderB：两个相继使用的 Shader
        // 2.mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。（取值同LinearGradient）
        // 3.PorterDuff.Mode 是用来指定两个图像共同绘制时的颜色策略的。它是一个 enum，不同的 Mode 可以指定不同的策略。（取值同LinearGradient）
        // 「颜色策略」的意思，就是说把源图像绘制到目标图像处时应该怎样确定二者结合后的颜色，而对于 ComposeShader(shaderA, shaderB, mode) 这个具体的方法，
        // 就是指应该怎样把 shaderB 绘制在 shaderA 上来得到一个结合后的 Shader。（想知道这个enum具体的类型及对应的效果图可参考当前文件描述中给出的网页地址）。
        // 注意：上面这段代码中我使用了两个 BitmapShader 来作为 ComposeShader() 的参数，而 ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果。

        mPaint.setAntiAlias(true);//开启抗锯齿
        // 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

            // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.loading);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.OVERLAY);
        mPaint.setShader(shader);
        canvas.drawRect(new RectF(0,0,800,500), mPaint);*/

        /*
         * TODO Paint颜色的第二层处理：setColorFilter(ColorFilter colorFilter) 具体详情及效果图可通过文件描述中的网址查看。
         *  ColorFilter 这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来。
         *  在 Paint 里设置 ColorFilter，使用的是 Paint.setColorFilter(ColorFilter filter) 方法。
         *  ColorFilter 并不直接使用，而是使用它的子类。它共有三个子类：LightingColorFilter PorterDuffColorFilter 和 ColorMatrixColorFilter。
         */

        /*//TODO 方式一：LightingColorFilter
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        mPaint.setColorFilter(lightingColorFilter);
        canvas.drawBitmap(bitmap1,300,300, mPaint);*/

        /*//TODO 方式二：PorterDuffColorFilter
        // 构造方法：
        // color：添加的颜色
        // model：取值同LinearGradient
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader1);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.OVERLAY);
        mPaint.setColorFilter(porterDuffColorFilter);
        canvas.drawBitmap(bitmap1,300,300, mPaint);*/

        /*//TODO 方式三：ColorMatrixColorFilter 详见网页*/


        /*
         * TODO Paint颜色的第三层处理：setXfermode(Xfermode xfermode)
         *  "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用的简写方式。
         *  严谨地讲， Xfermode 指的是你要绘制的内容和 Canvas 的目标位置的内容应该怎样结合计算出最终的颜色。
         *  但通俗地说，其实就是要你以绘制的内容作为源图像，以 View 中已有的内容作为目标图像，选取一个 PorterDuff.Mode 作为绘制内容的颜色处理方案。
         */
        /*//TODO 创建 Xfermode 的时候其实是创建的它的子类 PorterDuffXfermode。而事实上，Xfermode 也只有这一个子类。所以在设置 Xfermode 的时候不用多想，直接用 PorterDuffXfermode 吧。
        // 其实在更早的 Android 版本中，Xfermode 还有别的子类，但别的子类现在已经 deprecated 了，如今只剩下了 PorterDuffXfermode。所以目前它的使用看起来好像有点啰嗦，但其实是由于历史遗留问题。
        // 构造参数：
        // mode:取值同LinearGradient
        // 注意：这里关于PorterDuffXfermode不是很详细，如需要详细了解，可通过文件描述中的网址去查看。
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        canvas.drawBitmap(bitmap1, 0, 0, mPaint); // 画方
        mPaint.setXfermode(xfermode); // 设置 Xfermode
        canvas.drawBitmap(bitmap1, 300, 300, mPaint); // 画圆
        mPaint.setXfermode(null); // 用完及时清除 Xfermode
        canvas.restoreToCount(saved);*/
    }
}
