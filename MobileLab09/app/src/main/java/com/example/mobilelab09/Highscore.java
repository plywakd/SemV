package com.example.mobilelab09;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Highscore extends AppCompatActivity {

    private Spinner levelOptions;
    private TextView scoreboard;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        context = this.getApplicationContext();

        levelOptions = (Spinner) findViewById(R.id.levelMenu);
        scoreboard = (TextView) findViewById(R.id.scores);

        levelOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scoreboard.setText("");
                String level = levelOptions.getSelectedItem().toString();
                System.out.println(level);
                readScoreBoard(context, level);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void readScoreBoard(Context context, String level) {
        System.out.println("reading from file");
        String fileName = level+"Puzzle.txt";
        List<String> outputString = new ArrayList<>();
        try{
            InputStream inputStream=context.openFileInput(fileName);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while(line != null){
                    outputString.add(line);
                    line=bufferedReader.readLine();
                }
            }
        }catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        if(!outputString.isEmpty()){
            scoreboard.setText(outputString.toString());
        }
    }
}
