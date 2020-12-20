package com.example.mobilelab09;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private Context c;
    Paint paint = new Paint();
    private Random rand = new Random();
    private Puzzle puzzle;
    Instant time_start;
    OutputStreamWriter outputWrite;
    InputStreamReader inputRead;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyCanvas01(Context context) {
        super(context);
        c = context;
        width = getScreenWidth();
        height = getScreenHeight();
        puzzle = new Puzzle((int) (width*0.05f), (int)(width*0.05f), (int)(width*0.9f), (int)(width*0.9f));
        time_start = Instant.now();
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
        drawPuzzle();
        drawTiles();
        drawMoves();
        drawTime();
        invalidate();
    }
    public void drawPuzzle(){
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(puzzle.getRect(), paint);
    }

    public void drawTiles(){
        paint.setTextSize(140);
        for(Tile s : puzzle.getTiles()) canvas.drawRect(s.getRect(), paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        for(Tile s : puzzle.getTiles()) canvas.drawText(
                String.valueOf(s.getDisplay()), s.getX()+(puzzle.getTileSize()/2),
                s.getY()+(puzzle.getTileSize()/2), paint);
    }

    public void drawMoves(){
        paint.setTextSize(100);
        String moves = "Moves: "+puzzle.getMoves();
        canvas.drawText(moves, 100, 1300, paint);
    }

    public void saveScore(long seconds, long minutes, int moves){
        String string = String.format("%d;%02d:%02d\n", moves, minutes, seconds);
        try {
            outputWrite.write(string);
            outputWrite.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawTime(){
        paint.setTextSize(50);
        long seconds = Duration.between(time_start, Instant.now()).getSeconds();
        String moves = String.format("Time: %02d:%02d", (seconds/60), (seconds%60));
        canvas.drawText(moves, 100, 1500, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void gameDoneAction(){
        System.out.println("DONE!!!!!!!!!!!!!");
        long time = Duration.between(time_start, Instant.now()).getSeconds();
        long minutes = time/60;
        long seconds = time%60;
        saveScore(seconds, minutes, puzzle.getMoves());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            puzzle.touchHandler((int) event.getAxisValue(MotionEvent.AXIS_X), (int) event.getAxisValue(MotionEvent.AXIS_Y));
//            if(puzzle.isGameDone()) gameDoneAction();//TODO - change to some exit screen or something
        }
        return true;
    }

}

