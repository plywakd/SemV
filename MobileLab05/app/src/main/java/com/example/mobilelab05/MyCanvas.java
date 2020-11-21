package com.example.mobilelab05;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.time.Instant;

public class MyCanvas extends View {

    private int height;
    private int width;
    private Context c;

    public MyCanvas(Context context) {
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
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        drawLinesThroughMiddle(canvas, paint);
        drawLeftLines(canvas, paint);
        drawRightLines(canvas, paint);
    }

    public void drawLinesThroughMiddle(Canvas canvas, Paint paint) {
        for (int i = 0; i < 10; i++) {
            float startX = 0.0f;
            float startY = this.height * (0.1f + (i * 0.1f));
            float stopX = this.width;
            float stopY = this.height * (9 - i) * 0.1f;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    public void drawLeftLines(Canvas canvas, Paint paint) {
        for (int i = 0; i < 10; i++) {
            float startX = 0.0f;
            float startY = this.height * i * 0.1f;
            float stopX = this.width * (i + 0.1f) * 0.1f;
            float stopY = this.height;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }

    public void drawRightLines(Canvas canvas, Paint paint) {
        for (int i = 0; i < 10; i++) {
            float startX = this.width * i * 0.1f;
            float startY = 0;
            float stopX = this.width;
            float stopY = this.height * i * 0.1f;
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
    }
}
