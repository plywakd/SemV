package com.mobilelab03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Task01Activity extends AppCompatActivity {

    private Button nextWord;
    private Button exit;
    private Toolbar header;
    private EditText points;
    private EditText wordCounter;
    private EditText toTranslate;
    private EditText translation;
    private EditText answer;

    public Map<Integer, List<String>> wordsToTranslate = new HashMap<>();
    public int userPoints = 0;
    public int userWordCount = 0;
    public int rand;
    public List<String> wordPair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task01);
        nextWord = (Button) findViewById(R.id.nextWord);
        exit = (Button) findViewById(R.id.exit);
        points = (EditText) findViewById(R.id.points);
        wordCounter = (EditText) findViewById(R.id.wordCounter);
        toTranslate = (EditText) findViewById(R.id.toTranslate);
        translation = (EditText) findViewById(R.id.translation);
        answer = (EditText) findViewById(R.id.answer);
        header = (Toolbar) findViewById(R.id.toolbar);

        initializeWordMap();
        points.setEnabled(false);
        wordCounter.setEnabled(false);
        toTranslate.setEnabled(false);
        answer.setEnabled(false);
        header.setTitle("Test angielskiego!");
        points.setText(String.valueOf(userPoints));
        wordCounter.setText(String.valueOf(userWordCount));
        rand = new Random().nextInt(wordsToTranslate.size()) + 1;
        wordPair = wordsToTranslate.get(rand);
        toTranslate.setText(wordPair.get(0));


        nextWord.setOnClickListener((click) -> {
            if (userWordCount != wordsToTranslate.size()/2) {
                String userInput = translation.getText().toString();
                checkWord(userInput, wordPair.get(1));
            } else if (userWordCount == wordsToTranslate.size()/2) {
                points.setText(String.valueOf(userPoints)+"/"+String.valueOf(userWordCount));
                wordCounter.setText("");
                answer.setText("");
                nextWord.setText("Start Again!");
            } else if (nextWord.getText().toString().equals("Start Again")) {
                userWordCount = 0;
                userPoints = 0;
            }
            rand = new Random().nextInt(wordsToTranslate.size()) + 1;
            wordPair = wordsToTranslate.get(rand);
            toTranslate.setText(wordPair.get(0));

        });

        exit.setOnClickListener((click) -> {
            System.exit(0);
        });

    }


    public void checkWord(String userTranslation, String oriTranslation) {
        if (userTranslation.equals(oriTranslation)) {
            userPoints++;
            userWordCount++;
            points.setText(String.valueOf(userPoints));
            wordCounter.setText(String.valueOf(userWordCount));
            answer.setText("Poprawna");
        } else {
            userWordCount++;
            wordCounter.setText(String.valueOf(userWordCount));
            answer.setText("Zła");
        }
        translation.setText("");

    }

    public void initializeWordMap() {
        wordsToTranslate.put(1, Arrays.asList("jabłko", "apple"));
        wordsToTranslate.put(2, Arrays.asList("drzwi", "door"));
        wordsToTranslate.put(3, Arrays.asList("samochód", "car"));
        wordsToTranslate.put(4, Arrays.asList("lustro", "mirror"));
        wordsToTranslate.put(5, Arrays.asList("biurko", "desk"));
        wordsToTranslate.put(6, Arrays.asList("nożyczki", "scissors"));
        wordsToTranslate.put(7, Arrays.asList("nazwisko", "surname"));
        wordsToTranslate.put(8, Arrays.asList("wiek", "age"));
        wordsToTranslate.put(9, Arrays.asList("człowiek", "human"));
        wordsToTranslate.put(10, Arrays.asList("funt", "pound"));
        wordsToTranslate.put(11, Arrays.asList("głośniki", "speakers"));
        wordsToTranslate.put(12, Arrays.asList("gra", "game"));
        wordsToTranslate.put(13, Arrays.asList("okno", "window"));
        wordsToTranslate.put(14, Arrays.asList("kurczak", "chicken"));
        wordsToTranslate.put(15, Arrays.asList("pieniądze", "money"));
        wordsToTranslate.put(16, Arrays.asList("woda", "water"));
    }
}
