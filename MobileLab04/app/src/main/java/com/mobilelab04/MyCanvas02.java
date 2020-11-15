package com.mobilelab04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class MyCanvas02 extends View {

    private Context c;
    int height, width;

    public MyCanvas02(Context context) {
        super(context);
        c = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height = canvas.getHeight();
        width = canvas.getWidth();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        drawLines(canvas);
        drawRects(canvas);
        drawTriangles(canvas);
        drawCircles(canvas);
        drawArcs(canvas);
    }

    public void drawLines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        for (int i = 0; i < 10; i++) {
            canvas.drawLine(i * width / 10, 0, i * width / 10 + ((i * width / 10) / 2), (height / 5) - 10, paint);
        }
    }

    public void drawRects(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        for (int i = 0; i < 10; i++) {
            int left = i * width / 10;
            int top = height / 5;
            int right = (i * width / 10) + 100;
            int bottom = height / 5 + 100;
            Rect toDraw = new Rect(left, top, right, bottom);
            canvas.drawRect(toDraw, paint);
        }
    }

    public void drawTriangles(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        for (int i = 0; i < 10; i++) {
            int x = i * width / 10;
            int y = (height / 5) * 2;
            Point a = new Point(x, y);
            Point b = new Point(x + 50, y + 100);
            Point c = new Point(x + 100, y);
            Path path = new Path();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    public void drawCircles(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(3);
        for (int i = 0; i < 5; i++) {
            int cx = ((i + 1) * width / 5) / 2;
            int r = 30 + 2 * i;
            int cy = ((height / 5) * 3) - ((height / 5) / 2);
            canvas.drawCircle(cx, cy, r, paint);
        }
    }

    public void drawArcs(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        for (int i = 0; i < 7; i++) {
            float left = i * width / 7;
            float top = ((height / 5) * 4);
            float right = left + 100;
            float bottom = top + 100;
            RectF oval = new RectF(left, top, right, bottom);
            canvas.drawArc(oval, i * 90, 90, true, paint);
        }

    }
}
