package com.mobilelab02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task02Activity extends AppCompatActivity {

    private Button generate;
    private Button exit;
    private EditText arrayDisplay;
    private EditText minDisplay;
    private EditText maxDisplay;
    private EditText sumDisplay;
    private EditText avgDisplay;
    private Toolbar header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task02);
        header = (Toolbar) findViewById(R.id.toolbar);
        generate = (Button) findViewById(R.id.generate);
        exit = (Button) findViewById(R.id.exit);
        arrayDisplay = (EditText) findViewById(R.id.arrayDisplay);
        minDisplay = (EditText) findViewById(R.id.min);
        maxDisplay = (EditText) findViewById(R.id.max);
        sumDisplay = (EditText) findViewById(R.id.sum);
        avgDisplay = (EditText) findViewById(R.id.avg);

        header.setTitle("Randomize array!");
        arrayDisplay.setEnabled(false);
        minDisplay.setEnabled(false);
        maxDisplay.setEnabled(false);
        sumDisplay.setEnabled(false);
        avgDisplay.setEnabled(false);

        generate.setOnClickListener((click) ->{
            createRandArray();
        });
        exit.setOnClickListener((click) -> {
            System.exit(0);
        });
    }

    public void createRandArray(){
        List<Integer> randArr = new ArrayList<>();
        int min = 0, max = 0 , sum = 0;
        double avg = 0;
        int el = new Random().nextInt(100);
        min = max = sum = el;
        randArr.add(el);
        for(int i=1; i<10; i++){
            el = new Random().nextInt(100);
            randArr.add(el);
            if (el<min){
                min = el;
            }
            else if (el>max){
                max = el;
            }
            sum += el;
            avg = sum/i;
        }
        arrayDisplay.setText(randArr.toString());
        minDisplay.setText(String.valueOf(min));
        maxDisplay.setText(String.valueOf(max));
        sumDisplay.setText(String.valueOf(sum));
        avgDisplay.setText(String.valueOf(avg));

    }
}
