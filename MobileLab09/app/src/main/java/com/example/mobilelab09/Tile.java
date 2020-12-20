package com.example.mobilelab09;

import android.graphics.Rect;

public class Tile {
    private int x;
    private int y;
    private int width;
    private int height;
    private int display;
    private int targetX;
    private int targetY;

    public Tile(int x, int y, int width, int height, int display, int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.display = display;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public boolean isOnRightPosition() {
        return (x == targetX && y == targetY);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public Rect getRect() {
        return new Rect(x, y, x + width, y + height);
    }

    public boolean isOnTargetPosition() {
        return (this.x == this.targetX && this.y == this.targetY);
    }
}
