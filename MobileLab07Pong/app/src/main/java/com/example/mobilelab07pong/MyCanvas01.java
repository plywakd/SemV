package com.example.mobilelab07pong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.Random;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private Context c;

    private float rectX;
    private float xDelta = -4;
    private float rectY;
    private float yDelta = 4;

    private int fieldLeft = 110;
    private int fieldTop = 200;
    private int fieldRight = 970;
    private int fieldBottom = 1350;

    private int playerLeft = 140;
    private int playerTop = 475;
    private int playerRight = 160;
    private int playerBottom = 675;

    private int player2Left = 920;
    private int player2Top = 475;
    private int player2Right = 940;
    private int player2Bottom = 675;

    private int playerScore = 0;
    private int computerScore = 0;
    Paint paint = new Paint();

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int getRandomDelta(){
        Random rand = new Random();
        return rand.nextInt(10+10)-10;
    }

    public MyCanvas01(Context context) {
        super(context);
        c = context;
        width = getScreenWidth();
        height = getScreenHeight();
        rectX = width / 2;
        rectY = height / 2;
        xDelta = getRandomDelta();
        yDelta = -xDelta;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        drawScore();
        drawField();
        drawPaddles();
        handleBall();
        invalidate();
    }

    public void drawScore() {
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(String.format("%d - %d", playerScore, computerScore), width / 2.5f, 100, paint);
    }

    public void drawField() {
        paint.setColor(Color.WHITE);
        canvas.drawRect(fieldLeft - 10, fieldTop - 10, fieldRight + 10, fieldBottom + 10, paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(fieldLeft, fieldTop, fieldRight, fieldBottom, paint);
    }

    public void drawPaddles() {
        paint.setColor(Color.WHITE);
        canvas.drawRect(playerLeft, playerTop, playerRight, playerBottom, paint);
        canvas.drawRect(player2Left, player2Top, player2Right, player2Bottom, paint);
    }

    public void handleBall() {
        paint.setColor(Color.WHITE);
        Rect toDraw = new Rect((int) rectX - 10, (int) rectY - 10, (int) rectX + 10, (int) rectY + 10);
        canvas.drawRect(toDraw, paint);
        moveBall();
        if ((rectX <= playerRight && (rectY < playerBottom && rectY > playerTop)) ||
                (rectX >= player2Left && (rectY < player2Bottom && rectY > player2Top))) {
            xDelta = (-xDelta);
        }
        if (rectX <= fieldLeft) {
            computerScore++;
            rectX = width / 2;
            rectY = height / 2;
            xDelta = getRandomDelta();
            yDelta = -xDelta;
        } else if (rectX >= fieldRight) {
            playerScore++;
            rectX = width / 2;
            rectY = height / 2;
            xDelta = getRandomDelta();
            yDelta = -xDelta;
        }
        if (rectY <= fieldTop || rectY >= fieldBottom) {
            yDelta = (-yDelta);
        }
    }

    public void moveBall() {
        rectX += xDelta;
        rectY += yDelta;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE: {
                int middleField = (int) event.getY();
                if (middleField + 100 > fieldBottom || middleField - 100 < fieldTop) {
                } else {
                    if(event.getX() > (width/2)){
                        player2Bottom = middleField + 100;
                        player2Top = middleField - 100;
                    }else{
                        playerBottom = middleField + 100;
                        playerTop = middleField - 100;
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

