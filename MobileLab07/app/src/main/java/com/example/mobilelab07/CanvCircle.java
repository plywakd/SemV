package com.example.mobilelab07;

import java.util.Random;

public class CanvCircle {
    private int cx;
    private int cy;
    private  int r;
    private int gravity;
    private boolean falling;
    private float fallAngle;

    public CanvCircle(int cx, int cy, int r, int gravity, boolean falling) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.gravity = gravity;
        this.falling = falling;
        this.fallAngle = -1 + new Random().nextFloat() * 3;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public float getFallAngle() {
        return fallAngle;
    }

    public void setFallAngle(float fallAngle) {
        this.fallAngle = fallAngle;
    }
}
