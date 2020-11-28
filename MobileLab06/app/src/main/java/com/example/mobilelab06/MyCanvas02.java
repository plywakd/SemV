package com.example.mobilelab06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.time.Instant;

public class MyCanvas02 extends View {

    private int height;
    private int width;
    private Context c;

    public MyCanvas02(Context context) {
        super(context);
        c = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Instant start = Instant.now();
        height = canvas.getHeight();
        width = canvas.getWidth();

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);

        drawRect(canvas, paint);
    }

    public void drawRect(Canvas canvas, Paint paint) {
        int left = (width / 2) - 100;
        int top = (height / 2) + 100;
        int right = (width / 2) + 100;
        int bottom = (height / 2) - 100;
        Rect toDraw = new Rect(left, top, right, bottom);
        canvas.drawRect(toDraw, paint);
    }

}