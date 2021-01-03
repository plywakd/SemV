package com.example.mobilelab09;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

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

    public Puzzle(int x, int y, int width, int height, int numOfTiles) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.numOfTiles = numOfTiles;
        int tilesInRow = (int) sqrt(numOfTiles);
        this.tileSize = (int) ((width / tilesInRow) * 0.9f);
        this.gapSize = (int) ((width - (tileSize * tilesInRow)) / (tilesInRow + 1));
        this.moveCounter = 0;
        initPuzzle();
        initTiles();
        this.blankPoint = new Point(x + ((tilesInRow * gapSize) + ((tilesInRow - 1) * tileSize)),
                y + ((tilesInRow * gapSize) + (tilesInRow - 1) * tileSize));
        randomizeTiles();
    }

    public void initPuzzle() {
        places = new ArrayList<>();
        int x1, y1;
        for (int i = 0; i < sqrt(numOfTiles); i++) {
            y1 = x + (i * tileSize) + ((i + 1) * gapSize);
            for (int j = 0; j < sqrt(numOfTiles); j++) {
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

    public void randomizeTiles() {
        Random random = new Random();
        int blankPosition = random.nextInt(numOfTiles);
        if (blankPosition != numOfTiles - 1) moveTileToBlank(tiles.get(blankPosition));
        int s1, s2;
        for (int i = 0; i < 20; i++) {
            s1 = random.nextInt(numOfTiles - 1);
            s2 = random.nextInt(numOfTiles - 1);
            while (s2 == s1) s2 = random.nextInt(numOfTiles - 1);
            swapTiles(tiles.get(s1), tiles.get(s2));
        }
    }

    public void swapTiles(Tile firstTile, Tile secondTile) {
        int tempX = firstTile.getX();
        int tempY = firstTile.getY();
        firstTile.setX(secondTile.getX());
        firstTile.setY(secondTile.getY());
        secondTile.setX(tempX);
        secondTile.setY(tempY);
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
