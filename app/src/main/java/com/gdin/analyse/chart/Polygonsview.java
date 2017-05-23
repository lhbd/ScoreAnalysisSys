package com.gdin.analyse.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gdin.analyse.R;

public class PolygonsView extends View {
    private int centerX;//中心点X
    private int centerY;//中心点Y
    private float outsideRadius; //外层菱形圆半径
    private int defalutSize = 300;//默认大小
    private float distance;//多边形之间的间距
    private String[] str = {"语文", "", "英语", "", "", "数学", ""};
    private Rect str_rect;//字体矩形
    private Paint rank_Paint;//各等级进度画笔
    private Paint str_paint;//字体画笔
    private Paint center_paint;//中心线画笔
    private Paint one_paint;//最外层多边形画笔
    private Paint two_paint;//第二层多边形画笔
    private Paint three_paint;//第三层多边形画笔
    private Paint four_paint;//第四层多边形画笔
    private float f1, f2, f3, f4, f5, f6, f7;
    private float chValue,enValue,mathValue;


    public PolygonsView(Context context) {
        this(context, null);
    }

    public PolygonsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolygonsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defalutSize = dp_px(defalutSize);
        //初始化字体画笔
        str_paint = new Paint();
        str_paint.setAntiAlias(true);
        str_paint.setColor(Color.BLACK);
        str_paint.setTextSize(dp_px(16));
        str_rect = new Rect();
        str_paint.getTextBounds(str[0], 0, str[0].length(), str_rect);

        //初始化各等级进度画笔
        rank_Paint = new Paint();
        rank_Paint.setAntiAlias(true);
        rank_Paint.setColor(Color.RED);
        rank_Paint.setStrokeWidth(8);
        rank_Paint.setStyle(Paint.Style.STROKE);//设置空心

        //初始化最外层多边形画笔
        one_paint = new Paint();
        one_paint.setAntiAlias(true);
        one_paint.setColor(getResources().getColor(R.color.one));
        one_paint.setStyle(Paint.Style.FILL);//设置实心

        //初始化第二层多边形画笔
        two_paint = new Paint();
        two_paint.setAntiAlias(true);
        two_paint.setColor(getResources().getColor(R.color.two));
        two_paint.setStyle(Paint.Style.FILL);//设置实心

        //初始化第三层多边形画笔
        three_paint = new Paint();
        three_paint.setAntiAlias(true);
        three_paint.setColor(getResources().getColor(R.color.three));
        three_paint.setStyle(Paint.Style.FILL);//设置实心

        //初始化最内层多边形画笔
        four_paint = new Paint();
        four_paint.setAntiAlias(true);
        four_paint.setColor(getResources().getColor(R.color.four));
        four_paint.setStyle(Paint.Style.FILL);//设置实心


        //初始化中心线画笔
        center_paint = new Paint();
        center_paint.setAntiAlias(true);
        center_paint.setColor(Color.WHITE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        PaintFont(canvas);
        onePolygons(canvas);
        twoPolygons(canvas);
        threePolygons(canvas);
        fourPolygons(canvas);
        center(canvas);
        PaintRank(canvas);
    }

    /**
     * 绘制等级进度
     */
    private void PaintRank(Canvas canvas) {
        Path path = new Path();
        path.moveTo(centerX, getPaddingTop() + 2 * str_rect.height() + f1);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - f2)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - f2))));
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - f3)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - f3)) + centerY);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - f4)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - f4)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - f5)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - f5)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - f6)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - f6)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - f7)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - f7))));
        path.close();
        canvas.drawPath(path, rank_Paint);

    }


    /**
     * 绘制字体
     *
     * @param canvas
     */
    private void PaintFont(Canvas canvas) {
        canvas.drawText(str[0], centerX - str_rect.width() / 2,
                (float) (getPaddingTop() + 1.5 * str_rect.height()), str_paint);
        canvas.drawText(str[1],
                (float) (centerX + Math.sin(Math.toRadians(360 / 7)) * outsideRadius + str_rect.height() / 2),
                (float) ((getPaddingTop() + 2 * str_rect.height() + outsideRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * outsideRadius))), str_paint);
        canvas.drawText(str[2],
                (float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius + str_rect.height() / 2),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius) + centerY + str_rect.height() / 2, str_paint);
        canvas.drawText(str[3],
                (float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * outsideRadius - str_rect.height() / 2 + str_rect.width() / 2),
                (float) ((Math.cos(Math.toRadians(360 / 7 / 2)) * outsideRadius) + centerY + str_rect.height()), str_paint);
        canvas.drawText(str[4],
                (float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * outsideRadius + str_rect.height() / 2 - str_rect.width() * 1.5),
                (float) ((Math.cos(Math.toRadians(360 / 7 / 2)) * outsideRadius) + centerY + str_rect.height()), str_paint);
        canvas.drawText(str[5],
                (float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius - str_rect.height() / 2 - str_rect.width()),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius) + centerY + str_rect.height() / 2, str_paint);
        canvas.drawText(str[6],
                (float) (centerX - Math.sin(Math.toRadians(360 / 7)) * outsideRadius - str_rect.height() / 2 - str_rect.width()),
                (float) ((getPaddingTop() + 2 * str_rect.height() + outsideRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * outsideRadius))), str_paint);

    }

    /**
     * 绘制中心线
     *
     * @param canvas
     */
    private void center(Canvas canvas) {
        //绘制七边形中心线
        canvas.save();//保存当前状态
        canvas.rotate(0, centerX, centerY);
        float startY = getPaddingTop() + 2*str_rect.height();
        float endY = centerY;
        float du = (float) (360 / 7 + 0.5);
        for (int i = 0; i < 7; i++) {
            canvas.drawLine(centerX, startY, centerX, endY, center_paint);
            canvas.rotate(du, centerX, centerY);

        }
        canvas.restore();//恢复之前状态
    }
    /**
     * //绘制最外层多边形
     *
     * @param canvas
     */

        private void onePolygons(Canvas canvas) {

        Path path = new Path();
        path.moveTo(centerX, getPaddingTop() + 2 * str_rect.height());                           //起始点
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7)) * outsideRadius),
                (float) (getPaddingTop() + 2 * str_rect.height() + outsideRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * outsideRadius)));
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius) + centerY);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * outsideRadius),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * outsideRadius) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * outsideRadius),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * outsideRadius) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * outsideRadius) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7)) * outsideRadius),
                (float) (getPaddingTop() + 2 * str_rect.height() + outsideRadius - Math.abs(Math.cos(Math.toRadians(360 / 7)) * outsideRadius)));
        path.close();
        canvas.drawPath(path, one_paint);
    }

    /**
     * 绘制第二层多边形
     *
     * @param canvas
     */
    private void twoPolygons(Canvas canvas) {
        distance = outsideRadius / 4;
        Path path = new Path();
        path.moveTo(centerX, getPaddingTop() + 2 * str_rect.height() + distance);                           //起始点
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));
        path.close();
        canvas.drawPath(path, two_paint);
    }

    /**
     * 绘制第三层多边形
     *
     * @param canvas
     */
    private void threePolygons(Canvas canvas) {
        distance = outsideRadius / 2;
        Path path = new Path();
        path.moveTo(centerX, getPaddingTop() + 2 * str_rect.height() + distance);                           //起始点
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));

        path.close();
        canvas.drawPath(path, three_paint);
    }

    /**
     * 绘制最内层多边形
     *
     * @param canvas
     */
    private void fourPolygons(Canvas canvas) {
        distance = outsideRadius / 2 + outsideRadius / 4;
        Path path = new Path();
        path.moveTo(centerX, getPaddingTop() + 2 * str_rect.height() + distance);                           //起始点
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX + Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)),
                (float) (Math.cos(Math.toRadians(360 / 7 + 360 / 7 / 2)) * (outsideRadius - distance)) + centerY);
        path.lineTo((float) (centerX - Math.sin(Math.toRadians(360 / 7)) * (outsideRadius - distance)),
                (float) (getPaddingTop() + 2 * str_rect.height() + (outsideRadius) - Math.abs(Math.cos(Math.toRadians(360 / 7)) * (outsideRadius - distance))));

        path.close();
        canvas.drawPath(path, four_paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (wMode == MeasureSpec.EXACTLY) {
            width = wSize;
        } else {
            width = Math.min(wSize, defalutSize);
        }

        if (hMode == MeasureSpec.EXACTLY) {
            height = hSize;
        } else {
            height = Math.min(hSize, defalutSize);
        }
        centerX = width/2;
        centerY = height/2;
        outsideRadius = centerY - getPaddingTop() - 2* str_rect.height();
        f1 = outsideRadius - outsideRadius / 4 * chValue;
        f2 = outsideRadius - outsideRadius / 4 * 1;
        f3 = outsideRadius - outsideRadius / 4 * enValue;
        f4 = outsideRadius - outsideRadius / 4 * 1;
        f5 = outsideRadius - outsideRadius / 4 * 1;
        f6 = outsideRadius - outsideRadius / 4 * mathValue;
        f7 = outsideRadius - outsideRadius / 4 * 1;
        setMeasuredDimension(width, height);
    }

    /**
     * dp转px
     *
     * @param values
     * @return
     */
    public int dp_px(int values) {

        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

    public void setChValue(float chValue) {
        this.chValue = chValue;
        f1 = outsideRadius - outsideRadius / 4 * chValue;
    }

    public void setEnValue(float enValue) {
        this.enValue = enValue;
        f3 = outsideRadius - outsideRadius / 4 * enValue;
    }

    public void setMathValue(float mathValue) {
        this.mathValue = mathValue;
        f6 = outsideRadius - outsideRadius / 4 * mathValue;

    }
}
