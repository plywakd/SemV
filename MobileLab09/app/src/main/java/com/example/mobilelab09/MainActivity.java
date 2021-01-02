package com.example.mobilelab09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Button task01;
    private Button scoreBoard;
    private Intent task01Intent;
    private Intent task02Intent;
    private EditText usernameInput;
    private Spinner levelOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task01 = (Button) findViewById(R.id.dispatch);
        scoreBoard = (Button) findViewById(R.id.dispatch2);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        levelOptions = (Spinner) findViewById(R.id.levelMenu);

        task01Intent = new Intent(MainActivity.this, Task01Activity.class);
        task02Intent = new Intent(MainActivity.this, Highscore.class);

        task01.setOnClickListener((click) -> {
            if (usernameInput.getText().toString().equals("")) {
                createAlert("Login failed!", "Please provide username first!");
            } else {
                task01Intent.putExtra("name", usernameInput.getText().toString());
                task01Intent.putExtra("level", levelOptions.getSelectedItem().toString());
                MainActivity.this.startActivity(task01Intent);
            }
        });

        scoreBoard.setOnClickListener((click) -> {
            MainActivity.this.startActivity(task02Intent);
        });
    }

    public void createAlert(String title, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }
}