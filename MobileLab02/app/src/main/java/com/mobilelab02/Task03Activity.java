package com.mobilelab02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Task03Activity extends AppCompatActivity {

    private Button restart;
    private Button check;
    private Button exit;
    private EditText inputNum;
    private EditText result;
    public int toGuess;
    public AtomicInteger counter;
    private Toolbar header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task03);
        header = (Toolbar) findViewById(R.id.toolbar);
        restart = (Button) findViewById(R.id.restart);
        check = (Button) findViewById(R.id.check);
        exit = (Button) findViewById(R.id.exit);
        inputNum = (EditText) findViewById(R.id.inputNum);
        result = (EditText) findViewById(R.id.result);

        header.setTitle("Guess a number!");
        toGuess = new Random().nextInt((50 - 1) + 1) + 1;
        counter = new AtomicInteger();

        check.setOnClickListener((click) ->{
            counter.getAndIncrement();
            checkGuess();
        });

        restart.setOnClickListener((click) ->{
            counter.set(0);
            toGuess = new Random().nextInt((50 - 1) + 1) + 1;
            result.setText("");
        });

        exit.setOnClickListener((click) -> {
            System.exit(0);
        });
    }

    public void checkGuess(){
        if (!inputNum.getText().toString().equals("")){
            int input = Integer.parseInt(inputNum.getText().toString());
            if (input == toGuess){
                result.setText("Bravo! You guessed it in "+counter.toString()+" moves!");
            }
            else if(input > toGuess){
                result.setText("Too high!");
            }
            else if(input < toGuess){
                result.setText("Too low!");
            }
        }
        else{
            createAlert("Wrong input!","param needs to be number from 1 to 50");
        }
    }

    public void createAlert(String title, String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(Task03Activity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }
}
