package com.example.mobilelab07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button task01;
    private Intent task01Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task01 = (Button) findViewById(R.id.dispatch);

        task01Intent = new Intent(MainActivity.this, Task01Activity.class);
        task01.setOnClickListener((click) -> {
            MainActivity.this.startActivity(task01Intent);
        });
    }
}