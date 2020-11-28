package com.example.mobilelab06;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.util.Random;

public class MyCanvas02 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private float rectX;
    private float xDelta;
    private float rectY;
    private float yDelta;
    private Context c;
    Paint paint = new Paint();

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float getRandomDelta() {
        Random rand = new Random();
        return 1 + rand.nextFloat() * (10 - 1);
    }

    public MyCanvas02(Context context) {
        super(context);
        c = context;
        width = getScreenWidth();
        height = getScreenHeight();
        rectX = width / 2;
        rectY = height / 2;
        paint.setColor(Color.BLACK);
        xDelta = getRandomDelta();
        yDelta = getRandomDelta();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        mainRectHandler();
        invalidate();
    }

    public void mainRectHandler() {
        Rect toDraw = new Rect((int) rectX - 100, (int) rectY - 100, (int) rectX + 100, (int) rectY + 100);
        canvas.drawRect(toDraw, paint);
        moveMainRect();
        if (rectX <= 0 || rectX >= width) {
            xDelta = (-xDelta);
        }
        if (rectY <= 0 || rectY >= height) {
            yDelta = (-yDelta);
        }

    }

    public void moveMainRect() {
        rectX += xDelta;
        rectY += yDelta;
        invalidate();
    }


}