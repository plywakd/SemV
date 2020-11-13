package com.mobilelab04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyCanvas01 extends View {
    public MyCanvas01(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect xd = new Rect();
        xd.set(0, 0, 100, 100);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(xd, blue);
    }
}
