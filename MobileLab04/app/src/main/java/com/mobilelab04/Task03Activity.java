package com.mobilelab04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Task03Activity extends AppCompatActivity {

    private MyCanvas03 myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas = new MyCanvas03(this);

        setContentView(myCanvas);
    }
}
