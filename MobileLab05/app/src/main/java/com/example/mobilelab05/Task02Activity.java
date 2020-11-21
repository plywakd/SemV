package com.example.mobilelab05;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Task02Activity extends AppCompatActivity {

    private MyCanvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas = new MyCanvas(this);

        setContentView(myCanvas);
    }
}
