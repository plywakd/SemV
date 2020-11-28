package com.example.mobilelab06;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Task02Activity extends AppCompatActivity {

    private MyCanvas02 myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas = new MyCanvas02(this);

        setContentView(myCanvas);
    }
}
