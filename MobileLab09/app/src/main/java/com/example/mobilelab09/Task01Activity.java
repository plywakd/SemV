package com.example.mobilelab09;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Task01Activity extends AppCompatActivity {

    private MyCanvas01 myCanvas;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getIntent().getStringExtra("name");
        String level = getIntent().getStringExtra("level");

        int tilesForLevel = 9; // default value on easy mode

        switch (level) {
            case "3x3 - SMALL":
                tilesForLevel = 9;
                break;
            case "4x4 - MEDIUM":
                tilesForLevel = 16;
                break;
            case "5x5 - HARD":
                tilesForLevel = 25;
                break;
        }

        myCanvas = new MyCanvas01(this, name, tilesForLevel);

        setContentView(myCanvas);
    }

}
