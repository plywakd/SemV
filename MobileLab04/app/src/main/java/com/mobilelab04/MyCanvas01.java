package com.mobilelab04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyCanvas01 extends View {

    public static List<Integer> ColorsList = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.GRAY, Color.YELLOW, Color.BLACK, Color.WHITE, Color.CYAN, Color.MAGENTA);
    public int rand;
    public int randX;
    public int randY;
    private Context c;

    public MyCanvas01(Context context) {
        super(context);
        c = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Instant start = Instant.now();
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        for (int i = 0; i < 100; i++) {
            rand = new Random().nextInt(9);
            List<Point> trianglePoints = new ArrayList<Point>();
            for (int j = 0; j < 3; j++) {
                randX = new Random().nextInt(width);
                randY = new Random().nextInt(height);
                trianglePoints.add(new Point(randX, randY));
                if (j == 2) {
                    if (!checkIfTriangle(trianglePoints.get(0), trianglePoints.get(1), trianglePoints.get(2))) {
                        j = 0;
                    }
                }
            }
            int colorFill = ColorsList.get(rand);
            drawTriangle(canvas, trianglePoints.get(0), trianglePoints.get(1), trianglePoints.get(2), colorFill);
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        createAlert("TASK ENDED", "TIME TAKEN : " + timeElapsed);
    }

    public boolean checkIfTriangle(Point a, Point b, Point c) {
        List<Double> lenghts = new ArrayList<>();
        lenghts.add(Math.sqrt(Math.pow(Math.abs(b.x - a.x), 2) + Math.pow(Math.abs(b.y - a.y), 2)));
        lenghts.add(Math.sqrt(Math.pow(Math.abs(c.x - b.x), 2) + Math.pow(Math.abs(c.y - b.y), 2)));
        lenghts.add(Math.sqrt(Math.pow(Math.abs(c.x - a.x), 2) + Math.pow(Math.abs(c.y - a.y), 2)));
        Collections.sort(lenghts);
        if (lenghts.get(2) > (lenghts.get(0) + lenghts.get(1))) return false;
        return true;
    }

    public void drawTriangle(Canvas canvas, Point a, Point b, Point c, int colorFill) {
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        Path path = new Path();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(colorFill);
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, paint);
    }

    public void createAlert(String title, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }
}
