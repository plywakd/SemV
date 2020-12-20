package com.example.mobilelab09;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private int x;
    private int y;
    private int width;
    private int height;
    private List<Tile> tiles;
    private List<Point> places;
    private int tileSize;
    private int gapSize;
    private int numOfTiles;
    private Point blankPoint;
    private int moveCounter;

    public Puzzle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.numOfTiles = 9;
        this.tileSize = (int) ((width / Math.sqrt(numOfTiles)) * 0.9f);
        this.gapSize = (width - (tileSize * 3)) / 4;
        this.moveCounter = 0;
        initPuzzle();
        initTiles();
        this.blankPoint = new Point(x + ((3 * gapSize) + (2 * tileSize)), y + ((3 * gapSize) + (2 * tileSize)));
    }

    public void initPuzzle() {
        places = new ArrayList<>();
        int x1, y1;
        for (int i = 0; i < Math.sqrt(numOfTiles); i++) {
            y1 = x + (i * tileSize) + ((i + 1) * gapSize);
            for (int j = 0; j < Math.sqrt(numOfTiles); j++) {
                x1 = x + (j * tileSize) + ((j + 1) * gapSize);
                places.add(new Point(x1, y1));
            }
        }
    }

    public void initTiles() {
        tiles = new ArrayList<>();
        for (int i = 1; i < numOfTiles; i++) {
            Point pair = places.get(i - 1);
            tiles.add(new Tile(pair.x, pair.y, tileSize, tileSize, i, pair.x, pair.y));
        }
    }

    public Rect getRect() {
        return new Rect(x, y, x + width, y + height);
    }

    public void touchHandler(int tx, int ty) {
        Tile tile = getTouchedTile(tx, ty);
        if (tile != null) {
            if (checkTilesNearby(tile, blankPoint)) {
                moveTileToBlank(tile);
                moveCounter++;
            }
        }
    }

    public void moveTileToBlank(Tile s) {
        int tempX = s.getX();
        int tempY = s.getY();
        s.setX(blankPoint.x);
        s.setY(blankPoint.y);
        blankPoint.x = tempX;
        blankPoint.y = tempY;
    }

    public int getXDistance(Tile s1, Point s2) {
        return Math.abs(s1.getX() - s2.x);
    }

    public int getYDistance(Tile s1, Point s2) {
        return Math.abs(s1.getY() - s2.y);
    }

    public boolean checkTilesNearby(Tile s1, Point s2) {
        return ((getXDistance(s1, s2) == 0 && getYDistance(s1, s2) == (tileSize + gapSize))
                || (getYDistance(s1, s2) == 0 && getXDistance(s1, s2) == (tileSize + gapSize)));
    }

    public Tile getTouchedTile(int tx, int ty) {
        Tile result = null;
        for (Tile s : tiles) {
            int xDistance = tx - s.getX();
            int yDistance = ty - s.getY();
            if (xDistance >= 0 && xDistance <= tileSize && yDistance >= 0 && yDistance <= tileSize) {
                result = s;
                break;
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isGameDone() {
        return tiles.stream()
                .allMatch(s -> s.isOnTargetPosition());
    }


    public int getMoves() {
        return moveCounter;
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

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Point> getPlaces() {
        return places;
    }

    public void setPlaces(List<Point> places) {
        this.places = places;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public int getNumOfTiles() {
        return numOfTiles;
    }

    public void setNumOfTiles(int numOfTiles) {
        this.numOfTiles = numOfTiles;
    }
}
