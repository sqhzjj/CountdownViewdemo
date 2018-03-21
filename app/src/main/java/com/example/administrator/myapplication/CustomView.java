package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/11/13.
 */

public class CustomView extends View {

    Paint paint = new Paint();
    private Context mContext;
    private SeekBar seekBar;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100, 100, 800, 115, paint);



    }

}
