package com.example.mobilelab07;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private Context c;
    private Random rand = new Random();
    private List<CanvCircle> snow = new ArrayList<>();
    private boolean created = false;
    public int counter;
    Paint paint = new Paint();


    public MyCanvas01(Context context) {
        super(context);
        c = context;
        createSnowParticles(100);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        snowHandler();
        invalidate();
    }

    public void createSnowParticles(int quantity) {
        for (int i = 0; i < quantity; i++) {
            int cx = rand.nextInt(1080);
            int cy = rand.nextInt(300);
            int r = rand.nextInt(9) + 3;
            int gravity = rand.nextInt(9) + 3;
            snow.add(new CanvCircle(cx, cy, r, gravity, true));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void snowHandler() {
        paint.setColor(Color.WHITE);
        for (CanvCircle particle : snow) {
            if (particle.isFalling()) {
                if (particle.getCy() < height - particle.getR()) {
                    moveSnow(particle);
                } else {
                    counter++;
                    particle.setFalling(false);
                }
            }
            canvas.drawCircle(particle.getCx(), particle.getCy(), particle.getR(), paint);
        }
        createSnowParticles(counter);
        counter = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void moveSnow(CanvCircle particle) {
        int deltaCy = particle.getCy() + particle.getGravity();
        Optional collision = Optional.of(snow.stream()
                .filter(p -> !p.isFalling())
                .filter(p -> Math.abs(p.getCx() - particle.getCx()) <= p.getR())
                .filter(p -> p.getCy() - (particle.getCy() + (particle.getR() / 2)) <= p.getR())
                .findAny()).orElse(null);
        if (collision.isPresent()) {
            counter++;
            particle.setFalling(false);
        } else {
            particle.setCy(deltaCy);
        }
        invalidate();
    }

}

