package com.example.administrator.myapplication;

import android.content.Context;

/**
 * Created by Administrator on 2017/11/16.
 */

public class Utils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
