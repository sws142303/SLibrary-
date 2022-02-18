package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mylibrary.swslibrary.R;

/**
 * @author Sws
 * @time 13:45 2022/2/18
 * @dec Canvas 对绘制的辅助：范围裁切和几何变换：https://rengwuxian.com/ui-1-3/
 **/
 class CanvasClipApi {
    private static CanvasClipApi instance;

     synchronized static CanvasClipApi getInstance(){
        if (instance == null){
            synchronized (CanvasClipApi.class){
                instance = new CanvasClipApi();
            }
        }
        return instance;
    }
    private Paint mPaint = new Paint();
     private Point mPoint;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initAPiTest(Context context, Canvas canvas){
         //范围裁切
        initRangeCuttingApi(context,canvas);
        //几何变换
        initGeometricTransformation(context,canvas);

    }

    public void setPoint(Point point){
        this.mPoint = point;
    }

    /**
     * TODO 几何变换
     *  几何变换的使用大概分为三类：
     *  1.使用 Canvas 来做常见的二维变换；
     *  2.使用 Matrix 来做常见和不常见的二维变换；
     *  3.使用 Camera 来做三维变换。
     */
    private void initGeometricTransformation(Context context, Canvas canvas) {
        //使用 Canvas 来做常见的二维变换；
        canvasApi(context,canvas);

        //使用 Matrix 来做变换
        matrixApi(context,canvas);

        //使用camera来做三维变换
        cameraApi(context,canvas);
    }

    /**
     * TODO 使用camera来做三维变换 （除了x轴，y轴之外 还多了一个z轴）
     *  Camera 的三维变换有三类：旋转、平移、移动相机。
     */
    private void cameraApi(Context context, Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);
        Camera mCamera = new Camera();

        /*//TODO 一、Camera.rotate*() 三维旋转
        // Camera.rotate*() 一共有四个方法： rotateX(deg) rotateY(deg) rotateZ(deg) rotate(x, y, z)。

        *//*
         * TODO 如果你需要图形左右对称，需要配合上 Canvas.translate()，
         *  在三维旋转之前把绘制内容的中心点移动到原点，即旋转的轴心，然后在三维旋转后再把投影移动回来：
         *//*

        *//**
         * TODO Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。
         *//*

        canvas.save();
        mCamera.save();// 保存 Camera 的状态
        mCamera.rotateX(30); // 旋转 Camera 的三维空间
        canvas.translate(100, 100); // 旋转之后把投影移动回来
        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-100, -100); // 旋转之前把绘制内容移动到轴心（原点）
        mCamera.restore();// 恢复 Camera 的状态
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/


        /*//TODO Camera.translate(float x, float y, float z) 移动
        mCamera.translate(100,100,100);*/

        /*//TODO Camera.setLocation(x, y, z) 设置虚拟相机的位置
        // 注意！这个方法有点奇葩，它的参数的单位不是像素，而是 inch，英寸。
        // 这种设计源自 Android 底层的图像引擎 Skia 。在 Skia 中，Camera 的位置单位是英寸，英寸和像素的换算单位在 Skia 中被写死为了 72 像素，
        // 而 Android 中把这个换算单位照搬了过来。是的，它。写。死。了。
        // 在 Camera 中，相机的默认位置是 (0, 0, -8)（英寸）。8 x 72 = 576，所以它的默认位置是 (0, 0, -576)（像素）。
        // 如果绘制的内容过大，当它翻转起来的时候，就有可能出现图像投影过大的「糊脸」效果。而且由于换算单位被写死成了 72 像素，
        // 而不是和设备 dpi 相关的，所以在像素越大的手机上，这种「糊脸」效果会越明显。
        canvas.save();
        mCamera.save();// 保存 Camera 的状态
        mCamera.rotateX(30); // 旋转 Camera 的三维空间
        mCamera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        mCamera.setLocation(0, 0, 10); // Camera.setLocation(x, y, z) 的 x 和 y 参数一般不会改变，直接填 0 就好。
        mCamera.restore();// 恢复 Camera 的状态
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/
    }

    /**
     * TODO 使用 Matrix 来做变换
     *  注意：与Canvas的区别。Matrix进行变换时有postTranslate()与preTranslate()。都是进行平移。其他旋转，缩放等都一样。都是有成对的方法。
     *   postXXX() : 往后插入。
     *   preXXX() : 往前插入。（与Canvas逻辑一致）
     */
    private void matrixApi(Context context, Canvas canvas) {
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);

        //TODO 一、使用 Matrix 来做常见变换
        /*// Matrix 做常见变换的方式：
        // 1.创建 Matrix 对象；
        // 2.调用 Matrix 的 pre/postTranslate/Rotate/Scale/Skew() 方法来设置几何变换；
        // 3.使用 Canvas.setMatrix(matrix) 或 Canvas.concat(matrix) 来把几何变换应用到 Canvas。
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.postTranslate(200,500);
        matrix.postRotate(180,200 + bitmap.getWidth() / 2,500 + bitmap.getHeight() / 2);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/

        /**
         * TODO 小知识：
         *  把 Matrix 应用到 Canvas 有两个方法：Canvas.setMatrix(matrix) 和 Canvas.concat(matrix)。
         *  1.Canvas.setMatrix(matrix)：用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，
         *  改用 Matrix 的变换（注：根据下面评论里以及我在微信公众号中收到的反馈，不同的系统中 setMatrix(matrix) 的行为可能不一致，所以还是尽量用 concat(matrix) 吧）；
         *  2.Canvas.concat(matrix)：用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。
         */

        /*//TODO 二、使用 Matrix 来做自定义变换
        // Matrix 的自定义变换使用的是 setPolyToPoly() 方法。
        // Matrix.setPolyToPoly(float[] src, int srcIndex, float[] dst, int dstIndex, int pointCount) 用点对点映射的方式设置变换
        // 参数:
        // src 和 dst 是源点集合目标点集；
        // srcIndex 和 dstIndex 是第一个点的偏移；
        // pointCount 是采集的点的个数（个数不能大于 4，因为大于 4 个点就无法计算变换了）。
        // 介绍：
        // poly 就是「多」的意思。setPolyToPoly() 的作用是通过多点的映射的方式来直接设置变换。
        // 「多点映射」的意思就是把指定的点移动到给出的位置，从而发生形变。
        // 例如：(0, 0) -> (100, 100) 表示把 (0, 0) 位置的像素移动到 (100, 100) 的位置，这个是单点的映射，单点映射可以实现平移。而多点的映射，就可以让绘制内容任意地扭曲。
        int left = 100;
        int top = 100;
        int right = bitmap.getWidth();
        int bottom = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float[] pointsSrc = new float[]{left, top, right, top, left, bottom, right, bottom};
        float[] pointsDst = new float[]{left - 100, top + 50, right + 120, top - 90, left + 20, bottom + 30, right + 20, bottom + 60};

        matrix.reset();
        matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);

        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/

    }

    /**
     * TODO 使用 Canvas 来做常见的二维变换；
     *  translate平移，rotate旋转，scale放缩，skew错切
     *  注意：Canvas这些二维变换是可以叠加使用的。但是是倒序。如果先通过 translate来移动，再通过rotate来旋转。实际执行结果是先旋转，后移动。
     */
    private void canvasApi(Context context, Canvas canvas) {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.test_su);

        /*//TODO Canvas.translate(float dx, float dy) 平移
        // 参数里的 dx 和 dy 表示横向和纵向的位移。
        canvas.save();
        canvas.translate(200,800); //将图像平移到200,800的位置
        canvas.drawBitmap(bitmap,200,100,mPaint);
        canvas.restore();*/

        /*//TODO Canvas.rotate(float degrees, float px, float py) 旋转
        // 参数里的 degrees 是旋转角度，单位是度（也就是一周有 360° 的那个单位），方向是顺时针为正向；
        // px 和 py 是轴心的位置。
        canvas.save();
        canvas.rotate(45, 400, 400);
        canvas.drawBitmap(bitmap, 200, 200, mPaint);
        canvas.restore();*/

        /*//TODO  Canvas.scale(float sx, float sy, float px, float py) 放缩
        // 参数里的 sx sy 是横向和纵向的放缩倍数；(默认是1.0f 大于1.0f 表示图像方法，小于1.0f表示图像缩小)
        // px py 是放缩的轴心。
        canvas.save();
        canvas.scale(1f, 1f, 100 + bitmap.getWidth() / 2, 100 + bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/

        /*//TODO skew(float sx, float sy) 错切
        // 参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数。(sx大于0f 图像向右斜，小于0f图像向左斜。sy大于0f 图像向下斜，小于0f图像向上斜。)
        canvas.save();
        canvas.skew(0f, -0.5f);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/

    }

    /**
     * TODO 范围裁切 clipRect
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initRangeCuttingApi(Context context, Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_my_header);

        /*//TODO 范围裁剪 clipRect()
        // 参数：需要裁剪的范围
        canvas.save();//保存范围裁剪
        canvas.clipRect(new Rect(10,10,300,300));
        canvas.drawBitmap(bitmap,100,100,mPaint);
        canvas.restore();//恢复绘制范围。不影响下次裁剪。
        */

        //TODO 范围裁剪 clipPath()
        // 通过传入Path来进行裁剪，比clipRect可裁剪的形状多了一些。
        /*
        //从图像中裁出一个圆形
        Path path = new Path();//绘制一个圆形Path
        path.addCircle(700,500,300, Path.Direction.CCW);
        canvas.save();
        canvas.clipPath(path); //通过Path来进行一个圆形访问的裁剪
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/


        /*
        //从图像中裁掉两个圆形
        Path path = new Path();//绘制一个圆形Path
        path.addRect(new RectF(100,100,bitmap.getWidth(),bitmap.getHeight()), Path.Direction.CW);
        path.addCircle(700,500,300, Path.Direction.CCW);
        path.addCircle(200,200,100, Path.Direction.CCW);
        canvas.save();
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 100, 100, mPaint);
        canvas.restore();*/

    }
}
