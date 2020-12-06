package com.example.mobilelab07;

public class CanvCircle {
    private int cx;
    private int cy;
    private  int r;
    private int gravity;

    public CanvCircle(int cx, int cy, int r, int gravity) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.gravity = gravity;
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
}
