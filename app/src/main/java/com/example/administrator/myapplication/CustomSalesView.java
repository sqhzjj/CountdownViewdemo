package com.example.administrator.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/11/16.
 */

public class CustomSalesView extends View {

    private Context mContext;
    private String apv_text;
    private boolean apv_isCurrenComplete;
    private boolean isFirstStep;
    private boolean isLastStep;
    private boolean isNextComplete;
    private int apv_stepCount;
    private Bitmap bitmap;
    private int width;
    private int height;
    private Paint paint = new Paint();

    public CustomSalesView(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomSalesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AuditProgressView);
        apv_text = array.getString(R.styleable.AuditProgressView_apv_text);
        apv_isCurrenComplete = array.getBoolean(R.styleable.AuditProgressView_apv_isCurrentComplete, false);
        isFirstStep = array.getBoolean(R.styleable.AuditProgressView_apv_isFirstStep, false);
        isLastStep = array.getBoolean(R.styleable.AuditProgressView_apv_isLastStep, false);
        isNextComplete = array.getBoolean(R.styleable.AuditProgressView_apv_isNextComplete, false);
        apv_stepCount = array.getInteger(R.styleable.AuditProgressView_apv_stepCount, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //高度不是精确模式时，定义最小宽高
        if (widthMode != MeasureSpec.EXACTLY){
            width = getDisplayMetrics(getContext()).widthPixels / apv_stepCount;
        }

        if (heightMode != MeasureSpec.EXACTLY) {
            height = Utils.dip2px(getContext(), 90);
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (apv_isCurrenComplete){
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }else{
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        }
        //获取宽高
        width = getWidth();
        height = getHeight();

        canvas.drawBitmap(bitmap, width / 2 - bitmap.getWidth() / 2, height / 2 - bitmap.getHeight() / 2, paint);

        // 根据当前步骤是否完成 确定绘制文字颜色
        String mString = apv_text;
        TextPaint tp = new TextPaint();
        if (apv_isCurrenComplete) {
            tp.setColor(Color.parseColor("#03A9F5"));
        } else {
            tp.setColor(Color.parseColor("#757575"));
        }

        // 绘制多行文字
        tp.setStyle(Paint.Style.FILL);
        Point point = new Point(width / 2, Utils.dip2px(getContext(), 70));
        tp.setTextSize( Utils.dip2px(getContext(), 14));
        textCenter(mString, tp, canvas, point,  Utils.dip2px(getContext(), 57), Layout.Alignment.ALIGN_CENTER, 1, 0, false);

        // 绘制线条
        paint.setStrokeWidth( Utils.dip2px(getContext(), 2));

        // 根据是不是第一个步骤 确定是否有左边线条
        if (!isFirstStep) {
            // 左边
            // 根据当前步骤是否完成 来确定左边线条的颜色
            if (apv_isCurrenComplete) {
                paint.setColor(Color.parseColor("#03A9F5"));
            } else {
                paint.setColor(Color.parseColor("#E4E4E4"));
            }
            canvas.drawLine(0, height / 2, width / 2 - bitmap.getWidth() / 2 - Utils.dip2px(getContext(), 8), height / 2, paint);
        }

        // 根据是不是最后的步骤 确定是否有右边线条
        if (!isLastStep) {
            // 右边
            // 根据下一个步骤是否完成 来确定右边线条的颜色
            if (isNextComplete) {
                paint.setColor(Color.parseColor("#03A9F5"));
            } else {
                paint.setColor(Color.parseColor("#E4E4E4"));
            }
            canvas.drawLine(width / 2 + bitmap.getWidth() / 2 + Utils.dip2px(getContext(), 8), height / 2, width, height / 2, paint);
        }
    }

    //绘制多行文字
    private void textCenter(String string, TextPaint textPaint, Canvas canvas, Point point, int width, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        StaticLayout staticLayout = new StaticLayout(string, textPaint, width, align, spacingmult, spacingadd, includepad);
        canvas.save();
        canvas.translate(-staticLayout.getWidth() / 2 + point.x, -staticLayout.getHeight() / 2 + point.y);
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}
