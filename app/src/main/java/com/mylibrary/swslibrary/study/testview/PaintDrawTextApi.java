package com.mylibrary.swslibrary.study.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Locale;

/**
 * @author Sws
 * @time 10:26 2022/2/16
 * @dec drawText() 文字的绘制
 * 详细介绍及配图效果见： https://rengwuxian.com/ui-1-3/
 **/
public class PaintDrawTextApi {

    private static PaintDrawTextApi instance;
    private Paint mPaint = new Paint();

    public synchronized static PaintDrawTextApi getInstance() {
        if (instance == null) {
            synchronized (PaintDrawTextApi.class) {
                instance = new PaintDrawTextApi();
            }
        }
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initPaintDrawTextApi(Canvas canvas, Context context) {
        //Canvas 绘制文字的方式
        canvasDrawTextApi(canvas, context);
        //StaticLayout
        staticLayoutApi(canvas, context);
        //Paint对文字绘制的辅助
        paintDrawTextApi(canvas, context);
    }

    /**
     * TODO Paint对文字绘制的辅助
     * Paint 对文字绘制的辅助，有两类方法：设置显示效果的和测量文字尺寸的。
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void paintDrawTextApi(Canvas canvas, Context context) {
        //TODO 1.设置显示效果类
        setShowUIAPi(canvas, context);
        //TODO 2.测量文字尺寸类
        measureTextSizeApi(canvas, context);

    }

    /**
     * TODO 测量文字尺寸类
     * 不论是文字，还是图形或 Bitmap，只有知道了尺寸，才能更好地确定应该摆放的位置。由于文字的绘制和图形或 Bitmap 的绘制比起来，尺寸的计算复杂得多，所以它有一整套的方法来计算文字尺寸。
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void measureTextSizeApi(Canvas canvas, Context context) {

        /*//TODO float getFontSpacing() 获取推荐的行距。
        // 即推荐的两行文字的 baseline 的距离。这个值是系统根据文字的字体和字号自动计算的。
        // 它的作用是当你要手动绘制多行文字（而不是使用 StaticLayout）的时候，可以在换行的时候给 y 坐标加上这个值来下移文字。
        canvas.drawText("Hello View", 100, 150, mPaint);
        canvas.drawText("Hello View", 100, 150 + mPaint.getFontSpacing(), mPaint);
        canvas.drawText("Hello View", 100, 150 + mPaint.getFontSpacing() * 2, mPaint);*/

        /*//TODO FontMetircs getFontMetrics() 获取 Paint 的 FontMetrics。
        // FontMetrics 是个相对专业的工具类，它提供了几个文字排印方面的数值：ascent, descent, top, bottom, leading。(具体解释可查看网页: https://rengwuxian.com/ui-1-3/)
        // baseline：文字显示的基准线。 float 类型。
        // ascent / descent : 限制普通字符的顶部和底部范围（特殊字符会超出这个范围）。float 类型。
        // top / bottom : 限制全通字符的顶部和底部范围。float 类型。
        // leading : 间距。（当前行的bottom与下一个view的top之前的距离）float 类型。
        // 注意：另外，ascent 和 descent 这两个值还可以通过 Paint.ascent() 和 Paint.descent() 来快捷获取。
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float ascent = mPaint.ascent();
        float descent = mPaint.descent();*/

        /*//TODO FontMetrics 和 getFontSpacing()的区别：
        // 从定义可以看出，上图中两行文字的 font spacing (即相邻两行的 baseline 的距离) 可以通过 bottom \- top + leading (top 的值为负，前面刚说过，记得吧？）来计算得出。
        // 但你真的运行一下会发现， bottom \- top + leading 的结果是要大于 getFontSpacing() 的返回值的。
        // 两个方法计算得出的 font spacing 竟然不一样？
        // 这并不是 bug，而是因为 getFontSpacing() 的结果并不是通过 FontMetrics 的标准值计算出来的，而是另外计算出来的一个值，
        // 它能够做到在两行文字不显得拥挤的前提下缩短行距，以此来得到更好的显示效果。所以如果你要对文字手动换行绘制，
        // 多数时候应该选取 getFontSpacing() 来得到行距，不但使用更简单，显示效果也会更好。
        // getFontMetrics() 的返回值是 FontMetrics 类型。它还有一个重载方法 getFontMetrics(FontMetrics fontMetrics) ，
        // 计算结果会直接填进传入的 FontMetrics 对象，而不是重新创建一个对象。这种用法在需要频繁获取 FontMetrics 的时候性能会好些。
        // 另外，这两个方法还有一对同样结构的对应的方法 getFontMetricsInt() 和 getFontMetricsInt(FontMetricsInt fontMetrics) ，用于获取 FontMetricsInt 类型的结果。*/

        /*//TODO getTextBounds(String text, int start, int end, Rect bounds) 获取文字的显示范围。
        // 参数：
        // text : 要测量的文字
        // start : 文字的起始位置
        // end : 文字的结束位置
        // bounds : 是存储文字显示范围的对象，方法在测算完成之后会把结果写进 bounds。
        // 它有一个重载方法 getTextBounds(char[] text, int index, int count, Rect bounds)
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("Hello VIew", 100, 100, mPaint);

        Rect bounds = new Rect();
        mPaint.getTextBounds("Hello VIew", 0, "Hello VIew".length(), bounds);
        bounds.left += 100;
        bounds.top += 100;
        bounds.right += 100;
        bounds.bottom += 100;
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(bounds, mPaint);*/

        /*//TODO float measureText(String text) 测量文字的宽度并返回。
        canvas.drawText("Hello VIew", 100, 100, mPaint);
        float textWidth = mPaint.measureText("Hello VIew");
        canvas.drawLine(100, 100, 100 + textWidth, 100, mPaint);*/

        /*
         * TODO 前面有了 getTextBounds()与measureText()的区别？
         *  如果你用代码分别使用 getTextBounds() 和 measureText() 来测量文字的宽度，
         *  你会发现 measureText() 测出来的宽度总是比 getTextBounds() 大一点点。这是因为这两个方法其实测量的是两个不一样的东西。
         *  1.getTextBounds: 它测量的是文字的显示范围（关键词：显示）。形象点来说，你这段文字外放置一个可变的矩形，
         *  然后把矩形尽可能地缩小，一直小到这个矩形恰好紧紧包裹住文字，那么这个矩形的范围，就是这段文字的 bounds(界限)。
         *  2.measureText(): 它测量的是文字绘制时所占用的宽度（关键词：占用）。前面已经讲过，一个文字在界面中，往往需要占用比他的实际显示宽度更多一点的宽度，
         *  以此来让文字和文字之间保留一些间距，不会显得过于拥挤。上面的这幅图，我并没有设置 setLetterSpacing() ，
         *  这里的 letter spacing 是默认值 0，但你可以看到，图中每两个字母之间都是有空隙的。
         *  另外，下方那条用于表示文字宽度的横线，在左边超出了第一个字母 H 一段距离的，在右边也超出了最后一个字母 r（虽然右边这里用肉眼不太容易分辨），
         *  而就是两边的这两个「超出」，导致了 measureText() 比 getTextBounds() 测量出的宽度要大一些。
         *  注意：在实际的开发中，测量宽度要用 measureText() 还是 getTextBounds() ，需要根据情况而定。不过你只要掌握了上面我所说的它们的本质，在选择的时候就不会为难和疑惑了。
         */

        /*//TODO getTextWidths(String text, float[] widths) 获取字符串中每个字符的宽度，并把结果填入参数 widths。
        // 这相当于 measureText() 的一个快捷方法，它的计算等价于对字符串中的每个字符分别调用 measureText() ，并把它们的计算结果分别填入 widths 的不同元素。
        // getTextWidths() 同样也有好几个变种，使用大同小异，不再介绍。
        mPaint.getTextWidths();*/

        /*//TODO int breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth)
        // 这个方法也是用来测量文字宽度的。但和 measureText() 的区别是， breakText() 是在给出宽度上限的前提下测量文字的宽度。如果文字的宽度超出了上限，那么在临近超限的位置截断文字。
        // 参数及返回值：
        // breakText() 的返回值是截取的文字个数（如果宽度没有超限，则是文字的总个数）。
        // text 是要测量的文字；
        // measureForwards 表示文字的测量方向
        // true 表示由左往右测量；
        // maxWidth 是给出的宽度上限；
        // measuredWidth 是用于接受数据，而不是用于提供数据的：
        // 方法测量完成后会把截取的文字宽度（如果宽度没有超限，则为文字总宽度）赋值给 measuredWidth[0]。这个方法可以用于多行文字的折行计算。breakText() 也有几个重载方法，使用大同小异，不再介绍。
        int measuredCount;
        float[] measuredWidth = new float[]{0};
        // 宽度上限 300 （不够用，截断）
        measuredCount = mPaint.breakText("Hello HenCoder", 0, "Hello HenCoder".length(), true, 300, measuredWidth);
        canvas.drawText("Hello HenCoder", 0, measuredCount, 150, 150, mPaint);
        // 宽度上限 400 （不够用，截断）
        measuredCount = mPaint.breakText("Hello HenCoder", 0, "Hello HenCoder".length(), true, 400, measuredWidth);
        canvas.drawText("Hello HenCoder", 0, measuredCount, 150, 150 + mPaint.getFontSpacing(), mPaint);
        // 宽度上限 500 （够用）
        measuredCount = mPaint.breakText("Hello HenCoder", 0, "Hello HenCoder".length(), true, 500, measuredWidth);
        canvas.drawText("Hello HenCoder", 0, measuredCount, 150, 150 + mPaint.getFontSpacing() * 2, mPaint);
        // 宽度上限 600 （够用）
        measuredCount = mPaint.breakText("Hello HenCoder", 0, "Hello HenCoder".length(), true, 600, measuredWidth);
        canvas.drawText("Hello HenCoder", 0, measuredCount, 150, 150 + mPaint.getFontSpacing() * 3, mPaint);*/

        //TODO 光标相关
        /*//TODO getRunAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, int offset)
        // 介绍：对于一段文字，计算出某个字符处光标的 x 坐标。
        // start end 是文字的起始和结束坐标；
        // contextStart contextEnd 是上下文的起始和结束坐标；
        // isRtl 是文字的方向；
        // offset 是字数的偏移，即计算第几个字符处的光标。
        int length = "Hello View".length();
        float advance = mPaint.getRunAdvance("Hello View", 0, length, 0, length, false, length);
        canvas.drawText("Hello View", 100, 100, mPaint);
        canvas.drawLine(100 + advance, 100 - 50, 100 + advance, 100 + 10, mPaint);*/

        /* TODO 注意：
         *  其实，说是测量光标位置的，本质上这也是一个测量文字宽度的方法。
         *  上面这个例子中，start 和 contextStart 都是 0， end contextEnd 和 offset 都等于 text.length()。
         *  在这种情况下，它是等价于 measureText(text) 的，即完整测量一段文字的宽度。而对于更复杂的需求，getRunAdvance() 能做的事就比 measureText() 多了。
         */

        // 包含特殊符号的绘制（如 emoji 表情）
       /* String text = "Hello HenCoder \uD83C\uDDE8\uD83C\uDDF3";
        float advance1 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length);
        float advance2 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length - 1);
        float advance3 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length - 2);
        float advance4 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length - 3);
        float advance5 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length - 4);
        float advance6 = mPaint.getRunAdvance(text, 0, length, 0, length, false, length - 5);
        canvas.drawText(text, 100, 100, mPaint);*/

        /*//TODO getOffsetForAdvance(CharSequence text, int start, int end, int contextStart, int contextEnd, boolean isRtl, float advance)
        //  给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）。
        // 参数：
        // text 是要测量的文字；
        // start end 是文字的起始和结束坐标；
        // contextStart contextEnd 是上下文的起始和结束坐标；
        // isRtl 是文字方向；
        // advance 是给出的位置的像素值。填入参数，对应的字符偏移量将作为返回值返回。
        // 小技巧：getOffsetForAdvance() 配合上 getRunAdvance() 一起使用，就可以实现「获取用户点击处的文字坐标」的需求。
        mPaint.getOffsetForAdvance();*/

        /*//TODO hasGlyph(String string)
        // 检查指定的字符串中是否是一个单独的字形 (glyph）。最简单的情况是，string 只有一个字母（比如 a）。
        boolean ab = mPaint.hasGlyph("\uD83C\uDDE8\uD83C\uDDF3");
        Log.e("hasGlyph",""+ab);*/
    }

    /**
     * TODO 设置显示效果类
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setShowUIAPi(Canvas canvas, Context context) {

        /*//TODO setTextSize(float textSize) 设置文字大小。
        mPaint.setTextSize(18);
        canvas.drawText("Hello View", 100, 25, mPaint);
        mPaint.setTextSize(36);
        canvas.drawText("Hello View", 100, 70, mPaint);
        mPaint.setTextSize(60);
        canvas.drawText("Hello View", 100, 145, mPaint);
        mPaint.setTextSize(84);
        canvas.drawText("Hello View", 100, 240, mPaint);*/

        /*//TODO setTypeface(Typeface typeface) 设置字体。
        mPaint.setTypeface(Typeface.DEFAULT);
        canvas.drawText("Hello View", 100, 150, mPaint);
        mPaint.setTypeface(Typeface.SERIF);
        canvas.drawText("Hello View", 100, 300, mPaint);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Hello View", 100, 450, mPaint);*/

        /*//TODO setFakeBoldText(boolean fakeBoldText) 是否使用伪粗体。
        // 之所以叫伪粗体（ fake bold ），因为它并不是通过选用更高 weight 的字体让文字变粗，而是通过程序在运行时把文字给「描粗」了。
        mPaint.setFakeBoldText(false);
        canvas.drawText("未使用伪粗体", 100, 150, mPaint);
        mPaint.setFakeBoldText(true);
        canvas.drawText("使用伪粗体", 100, 230, mPaint);*/

        /*//TODO setStrikeThruText(boolean strikeThruText) 是否添加删除线
        mPaint.setStrikeThruText(false);
        canvas.drawText("未添加删除线", 100, 150, mPaint);
        mPaint.setStrikeThruText(true);
        canvas.drawText("添加删除线", 100, 230, mPaint);*/

        /*//TODO setUnderlineText(boolean underlineText) 是否添加下划线
        mPaint.setUnderlineText(false);
        canvas.drawText("未添加下滑线", 100, 150, mPaint);
        mPaint.setUnderlineText(true);
        canvas.drawText("添加下滑线", 100, 230, mPaint);*/

        /*//TODO setTextSkewX(float skewX) 设置文字横向错切角度。其实就是文字倾斜度的啦。
        // 负数向右偏，正数向左偏
        mPaint.setTextSkewX(0.5f);
        canvas.drawText("设置文字横向错切角度。", 100, 150, mPaint);*/

        /*//TODO setTextScaleX(float scaleX) 设置文字横向放缩。也就是文字变胖变瘦。
        mPaint.setTextScaleX(1);
        canvas.drawText("Hello View", 100, 150, mPaint);
        mPaint.setTextScaleX(0.8f);
        canvas.drawText("Hello View", 100, 230, mPaint);
        mPaint.setTextScaleX(1.2f);
        canvas.drawText("Hello View", 100, 310, mPaint);*/

        /*//TODO setLetterSpacing(float letterSpacing) 设置字符间距。默认值是 0。
        mPaint.setLetterSpacing(0.2f);
        canvas.drawText("Hello View", 100, 150, mPaint);*/

        /*//TODO setFontFeatureSettings(String settings) 用 CSS 的 font-feature-settings 的方式来设置文字。
        //  CSS 全称是 Cascading Style Sheets ，是网页开发用来设置页面各种元素的样式的。
        mPaint.setFontFeatureSettings("smcp"); // 设置 "small caps"
        canvas.drawText("Hello View", 100, 150, mPaint);*/

        /*//TODO setTextAlign(Paint.Align align) 设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT。
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Hello View", 500, 150, mPaint);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Hello View", 500, 150 + 100, mPaint);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Hello View", 500, 150 + 100 * 2, mPaint);*/

        /*//TODO setTextLocale(Locale locale) / setTextLocales(LocaleList locales) 设置绘制所使用的 Locale。
        // 描述：Locale 直译是「地域」，其实就是你在系统里设置的「语言」或「语言区域」（具体名称取决于你用的是什么手机），比如「简体中文（中国）」「English (US)」「English (UK)」。
        // 有些同源的语言，在文化发展过程中对一些相同的字衍生出了不同的写法（比如中国大陆和日本对于某些汉字的写法就有细微差别。注意，不是繁体和简体这种同音同义不同字，而真的是同样的一个字有两种写法）。
        // 系统语言不同，同样的一个字的显示就有可能不同。你可以试一下把自己手机的语言改成日文，然后打开微信看看聊天记录，你会明显发现文字的显示发生了很多细微的变化，这就是由于系统的 Locale 改变所导致的。
        // Canvas 绘制的时候，默认使用的是系统设置里的 Locale。而通过 Paint.setTextLocale(Locale locale) 就可以在不改变系统设置的情况下，直接修改绘制时的 Locale。
        // 注意：
        // 另外，由于 Android 7.0 ( API v24) 加入了多语言区域的支持，所以在 API v24 以及更高版本上，还可以使用 setTextLocales(LocaleList locales) 来为绘制设置多个语言区域。
        mPaint.setTextLocale(Locale.CHINA); // 简体中文
        canvas.drawText("雨骨底条今直沿微写", 150, 150, mPaint);
        mPaint.setTextLocale(Locale.TAIWAN); // 繁体中文
        canvas.drawText("雨骨底条今直沿微写", 150, 150 + 100, mPaint);
        mPaint.setTextLocale(Locale.JAPAN); // 日语
        canvas.drawText("雨骨底条今直沿微写", 150, 150 + 100 * 2, mPaint);*/

        /*//TODO setHinting(int mode) 设置是否启用字体的 hinting （字体微调）。
        // 介绍：
        // 现在的 Android 设备大多数都是是用的矢量字体。矢量字体的原理是对每个字体给出一个字形的矢量描述，然后使用这一个矢量来对所有的尺寸的字体来生成对应的字形。
        // 由于不必为所有字号都设计它们的字体形状，所以在字号较大的时候，矢量字体也能够保持字体的圆润，这是矢量字体的优势。
        // 不过当文字的尺寸过小（比如高度小于 16 像素），有些文字会由于失去过多细节而变得不太好看。
        // hinting 技术就是为了解决这种问题的：通过向字体中加入 hinting 信息，让矢量字体在尺寸过小的时候得到针对性的修正，从而提高显示效果。
        // 注意：
        // 现在（ 2017 年），手机屏幕的像素密度已经非常高，几乎不会再出现字体尺寸小到需要靠 hinting 来修正的情况，所以这个方法其实……没啥用了。可以忽略。
        mPaint.setHinting(1);
        canvas.drawText("雨骨底条今直沿微写", 150, 150 + 100 * 2, mPaint);*/

        /*//TODO setElegantTextHeight(boolean elegant) 设置是否开启文字的 elegant height 。开启之后，文字的高度就变优雅了。
        // 这个方法对中国人没用，不想看的话可以直接跳过，无毒副作用。
        // 他的作用：
        // 1.把「大高个」文字的高度恢复为原始高度；（如泰语中的某些字符）
        // 2.增大每行文字的上下边界，来容纳被加高了的文字。
        // 由于中国人常用的汉语和英语的文字并不会达到这种高度，所以这个方法对于中国人基本上是没用的。
        mPaint.setElegantTextHeight(true);
        canvas.drawText("雨骨底条今直沿微写", 150, 150 + 100 * 2, mPaint);*/

        /*//TODO  setSubpixelText(boolean subpixelText) 是否开启次像素级的抗锯齿（ sub-pixel anti-aliasing ）。
        // 次像素级抗锯齿这个功能解释起来很麻烦，简单说就是根据程序所运行的设备的屏幕类型，来进行针对性的次像素级的抗锯齿计算，从而达到更好的抗锯齿效果。
        // 不过，和前面讲的字体 hinting 一样，由于现在手机屏幕像素密度已经很高，所以默认抗锯齿效果就已经足够好了，一般没必要开启次像素级抗锯齿，所以这个方法基本上没有必要使用。
        mPaint.setSubpixelText(true);
        canvas.drawText("雨骨底条今直沿微写", 150, 150 + 100 * 2, mPaint);*/

    }

    /**
     * TODO StaticLayout 这个也是使用 Canvas 来进行文字的绘制，不过并不是使用 Canvas 的方法。
     * 与Canvas绘制文字的区别：
     * 1.Canvas.drawText() 只能绘制单行的文字，不能换行。不能在 View 的边缘自动折行，到了 View 的边缘处，文字继续向后绘制到看不见的地方，而不是自动换行
     * 2.Canvas.drawText() 不能在换行符 \n 处换行。（在换行符 \n 的位置并没有换行，而只是加了个空格）
     * 3.如果需要绘制多行的文字，你必须自行把文字切断后分多次使用 drawText() 来绘制，或者使用 StaticLayout 。
     * StaticLayout介绍：
     * 1.StaticLayout 并不是一个 View 或者 ViewGroup ，而是 android.text.Layout 的子类，它是纯粹用来绘制文字的。 StaticLayout 支持换行，它既可以为文字设置宽度上限来让文字自动换行，也会在 \n 处主动换行。
     * 2.如果你需要进行多行文字的绘制，并且对文字的排列和样式没有太复杂的花式要求，那么使用 StaticLayout 就好。
     * 构造参数：
     * 1.width 是文字区域的宽度，文字到达这个宽度后就会自动换行；
     * 2.align 是文字的对齐方向；
     * 3.spacingmult 是行间距的倍数，通常情况下填 1 就好；
     * 4.spacingadd 是行间距的额外增加值，通常情况下填 0 就好；
     * 5.includepad 是指是否在文字上下添加额外的空间，来避免某些过高的字符的绘制出现越界。
     */
    private void staticLayoutApi(Canvas canvas, Context context) {
        /*String text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        StaticLayout staticLayout1 = new StaticLayout(text1, new TextPaint(), 600,
                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        String text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz";
        StaticLayout staticLayout2 = new StaticLayout(text2, new TextPaint(), 600,
                Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        canvas.save();
        canvas.translate(50, 100);
        staticLayout1.draw(canvas);
        canvas.translate(0, 200);
        staticLayout2.draw(canvas);
        canvas.restore();*/
    }

    /**
     * TODO Canvas 绘制文字的方式
     * Canvas 的文字绘制方法有三个：drawText() drawTextRun() 和 drawTextOnPath()。
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void canvasDrawTextApi(Canvas canvas, Context context) {

        mPaint.setTextSize(30);

        /*//TODO drawText(String text, float x, float y, Paint paint) ,drawText() 是 Canvas 最基本的绘制文字的方法：给出文字的内容和位置， Canvas 按要求去绘制文字。
        // 参数：
        // 1.text 是文字内容，
        // 2.x 和 y 是文字的坐标。但需要注意：这个坐标并不是文字的左上角，而是一个与左下角比较接近的位置。
        // 注意：如果你像绘制其他内容一样，在绘制文字的时候把坐标填成 (0, 0)，文字并不会显示在 View 的左上角，而是会几乎完全显示在 View 的上方，到了 View 外部看不到的位置.
        canvas.drawText("测试测试",100,100,mPaint);*/

        /*//TODO drawTextRun() drawTextRun() 是在 API 23 新加入的方法。它和 drawText() 一样都是绘制文字，但加入了两项额外的设置——上下文和文字方向——用于辅助一些文字结构比较特殊的语言的绘制。
        // 参数：
        // text：要绘制的文字
        // start：从那个字开始绘制
        // end：绘制到哪个字结束
        // contextStart：上下文的起始位置。contextStart 需要小于等于 start
        // contextEnd：上下文的结束位置。contextEnd 需要大于等于 end
        // x：文字左边的坐标
        // y：文字的基线坐标
        // isRtl：是否是 RTL（Right-To-Left，从右向左）
        // 声明：这个方法对中国人没用。所以如果你有兴趣，可以继续看；而如果你想省时间，直接跳过这个方法看后面的就好了，没有任何毒副作用。
        canvas.drawTextRun("测试测试",0,4,0,4,100,100,false,mPaint);*/

        /*//TODO drawTextOnPath() 沿着一条 Path 来绘制文字。这是一个耍杂技的方法。
        // 参数：
        // text：要绘制的文字
        // path：文字展示的路径
        // hOffset 和 vOffset。它们是文字相对于 Path 的水平偏移量和竖直偏移量，利用它们可以调整文字的位置。例如你设置 hOffset 为 5， vOffset 为 10，文字就会右移 5 像素和下移 10 像素。
        // 注意：drawTextOnPath() 使用的 Path ，拐弯处全用圆角，别用尖角。
        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(300,200);
        path.lineTo(500,100);
        path.lineTo(900,600);
        mPaint.setStrokeJoin(Paint.Join.ROUND); //拐角使用圆角的方式
        canvas.drawTextOnPath("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试",path,5,10,mPaint);*/
    }
}
