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

        myCanvas = new MyCanvas01(this);

        setContentView(myCanvas);
    }


}
