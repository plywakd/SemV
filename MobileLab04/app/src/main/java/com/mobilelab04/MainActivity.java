package com.mobilelab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button task01;
    private Button task02;
    private Button task03;
    private Intent task01Intent;
    private Intent task02Intent;
    private Intent task03Intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task01 = (Button) findViewById(R.id.dispatch);
        task02 = (Button) findViewById(R.id.dispatch2);
        task03 = (Button) findViewById(R.id.dispatch3);

        task01Intent = new Intent(MainActivity.this, Task01Activity.class);
        task02Intent = new Intent(MainActivity.this, Task02Activity.class);
        task03Intent = new Intent(MainActivity.this, Task03Activity.class);

        task01.setOnClickListener((click) -> {
            MainActivity.this.startActivity(task01Intent);
        });
        task02.setOnClickListener((click) -> {
            MainActivity.this.startActivity(task02Intent);
        });
        task03.setOnClickListener((click) -> {
            MainActivity.this.startActivity(task03Intent);
        });
    }
}