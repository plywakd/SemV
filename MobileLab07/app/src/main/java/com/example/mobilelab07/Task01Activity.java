package com.example.mobilelab07;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class Task01Activity extends AppCompatActivity {

    private MyCanvas01 myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas = new MyCanvas01(this);

        setContentView(myCanvas);
    }


}
