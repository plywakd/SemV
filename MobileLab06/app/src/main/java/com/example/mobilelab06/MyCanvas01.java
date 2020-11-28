package com.example.mobilelab06;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private float rectX;
    private float rectY;
    private Context c;
    Paint paint = new Paint();

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public MyCanvas01(Context context) {
        super(context);
        c = context;
        width = getScreenWidth();
        height = getScreenHeight();
        rectX = width / 2;
        rectY = height / 2;
        System.out.println(width + ", " + height);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawMainRect();
    }

    public void drawMainRect() {
        Rect toDraw = new Rect((int) rectX - 100, (int) rectY - 100, (int) rectX + 100, (int) rectY + 100);
        canvas.drawRect(toDraw, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                if ((event.getX() <= width && event.getX() >= 0) && (event.getY() <= height &&
                        event.getY() >= 0)) {
                    float xDirection = event.getX() - rectX; // if + then rect moving right, if - then rect moving left
                    float yDirection = event.getY() - rectY; // if + then rect moving down, if - then rect moving up
                    System.out.println(xDirection + ", " + yDirection);
                    rectX = event.getX();
                    rectY = event.getY();
                    if (xDirection > 0) {
                        paint.setColor(Color.YELLOW);
                    } else if (xDirection < 0) {
                        paint.setColor(Color.RED);
                    }
                    if (yDirection > 0) {
                        paint.setColor(Color.BLUE);
                    } else if (yDirection < 0) {
                        paint.setColor(Color.GREEN);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}

