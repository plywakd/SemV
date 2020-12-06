package com.example.mobilelab07;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private Context c;
    private Random rand = new Random();
    private List<CanvCircle> snow= new ArrayList<>();
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        snowFall(canvas);
        snowHandler(canvas);
    }

    public void snowFall(Canvas canvas){
        paint.setColor(Color.WHITE);
        for(int i=0;i<100;i++){
            int cx = rand.nextInt(1080);
            int cy = rand.nextInt(300);
            int r = rand.nextInt(10)+1;
            int gravity = rand.nextInt(10)+1;
            snow.add(new CanvCircle(cx,cy,r,gravity));
        }
    }

    public void snowHandler(Canvas canvas){
        for(CanvCircle particle : snow){
            System.out.println("Gravity: "+particle.getGravity());
            canvas.drawCircle(particle.getCx(),particle.getCy(),particle.getR(),paint);
            int deltaCy = particle.getCy()+particle.getGravity();
            if (deltaCy<height){
                particle.setCy(deltaCy);
            }
            else{
                System.out.println("End particle");
            }
            invalidate();
        }
    }

}

