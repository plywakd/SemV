package com.example.mobilelab09;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.Instant;

public class MyCanvas01 extends View {

    public Canvas canvas;
    private int height;
    private int width;
    private Context c;
    Paint paint = new Paint();
    private Puzzle puzzle;
    Instant time_start;
    OutputStreamWriter outputWrite;
    InputStreamReader inputRead;
    private MediaPlayer actionSound = null;
    private String username;
    private int currentLevel;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MyCanvas01(Context context, String name, int level) {
        super(context);
        c = context;
        width = getScreenWidth();
        height = getScreenHeight();
        this.username = name;
        this.currentLevel = (int) Math.sqrt(level);
        puzzle = new Puzzle((int) (width * 0.05f), (int) (width * 0.05f), (int) (width * 0.9f), (int) (width * 0.9f), level);
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

    public void drawPuzzle() {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(puzzle.getRect(), paint);
    }

    public void drawTiles() {
        paint.setTextSize(400 / (int)Math.sqrt(puzzle.getNumOfTiles()));
        for (Tile s : puzzle.getTiles()) canvas.drawRect(s.getRect(), paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (Tile s : puzzle.getTiles())
            canvas.drawText(
                    String.valueOf(s.getDisplay()), s.getX() + (puzzle.getTileSize() / 2),
                    s.getY() + (puzzle.getTileSize() / 2), paint);
    }

    public void drawMoves() {
        paint.setTextSize(100);
        String moves = "Moves: " + puzzle.getMoves();
        canvas.drawText(moves, 50, 1400, paint);
    }

    public void saveScore(long seconds, long minutes, int moves) {
        String score = String.format("Username: %s\tlevel: %d\tmoves:%d, %02d:%02d\n", this.username, this.currentLevel, moves, minutes, seconds);
        writeToFile(score,c);
    }

    private void writeToFile(String data, Context context) {
        String fileName = null;
        switch(this.currentLevel){
            case 3:
                fileName = "easyPuzzle.txt";
                break;
            case 4:
                fileName = "mediumPuzzle.txt";
                break;
            case 5:
                fileName = "hardPuzzle.txt";
                break;
        }
        try {
            outputWrite = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_APPEND));
            outputWrite.write(data);
            outputWrite.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void drawTime() {
        paint.setTextSize(50);
        long seconds = Duration.between(time_start, Instant.now()).getSeconds();
        String moves = String.format("Player: %s, Time: %02d:%02d",this.username, (seconds / 60), (seconds % 60));
        canvas.drawText(moves, 50, 1500, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void gameDoneAction() {
        System.out.println("Finished!");
        long time = Duration.between(time_start, Instant.now()).getSeconds();
        long minutes = time / 60;
        long seconds = time % 60;
        saveScore(seconds, minutes, puzzle.getMoves());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            puzzle.touchHandler((int) event.getAxisValue(MotionEvent.AXIS_X), (int) event.getAxisValue(MotionEvent.AXIS_Y));
            actionSound = MediaPlayer.create(c, R.raw.quack_sound_effect);
            actionSound.start();
            if (puzzle.isGameDone()){
                gameDoneAction();
            }
        }
        return true;
    }


}

