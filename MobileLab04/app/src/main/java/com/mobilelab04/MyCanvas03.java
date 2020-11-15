package com.mobilelab04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyCanvas03 extends View {

    int height, width;

    public MyCanvas03(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height = canvas.getHeight();
        width = canvas.getWidth();
        System.out.println(height + "," + width);

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle((width / 2) - 120, height / 2, 50, paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, 50, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle((width / 2) + 120, height / 2, 50, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle((width / 2) - 62.5f, (height / 2) + 50, 50, paint);
        paint.setColor(Color.GREEN);
        canvas.drawCircle((width / 2) + 62.5f, (height / 2) + 50, 50, paint);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        canvas.drawText("Rio de Janeiro 2016", (width / 2) - 200, (height / 2) + 200, paint);
    }
}
