package com.example.mobilelab05;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Task01Activity extends AppCompatActivity {

    private Button nextWord;
    private Button check;
    private Button exit;
    private Toolbar header;
    private EditText toGuess;
    private EditText letter;
    private EditText letters;
    private ImageView gameImage;

    public static final Random Randomizer = new Random();
    public static final String[] WORDS = {"obiekt", "funkcja", "dysk", "system", "gra", "studio",
            "rzut"};
    public static final List<String> GAMEIMAGENAME = Arrays.asList("w0", "w1", "w2",
            "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10");
    int chances;
    String wordToGuess;
    char[] wordToGuessSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task01);
        getSupportActionBar().hide();
        header = (Toolbar) findViewById(R.id.toolbar);
        nextWord = (Button) findViewById(R.id.nextWord);
        check = (Button) findViewById(R.id.check);
        exit = (Button) findViewById(R.id.exit);
        toGuess = (EditText) findViewById(R.id.toGuess);
        letter = (EditText) findViewById(R.id.letter);
        letters = (EditText) findViewById(R.id.letters);
        gameImage = (ImageView) findViewById(R.id.gameImage);

        toGuess.setEnabled(false);
        letters.setEnabled(false);
        header.setTitle("Wisielec v2.0");
        newGame();


        check.setOnClickListener((click) -> {
            if (!letters.getText().toString().equals("WYGRANA!") ||
                    !letters.getText().toString().equals("PRZEGRANA!"))
                checkLetter();
        });

        nextWord.setOnClickListener((click) -> {
            newGame();
        });

        exit.setOnClickListener((click) -> {
            System.exit(0);
        });
    }

    private String selectRandomWord() {
        return WORDS[Randomizer.nextInt(WORDS.length)];
    }

    public void newGame() {
        wordToGuess = selectRandomWord();
        wordToGuessSecret = new char[wordToGuess.length()];
        System.out.println(wordToGuess);
        for (int i = 0; i < wordToGuess.length(); i++) {
            wordToGuessSecret[i] = '_';
        }
        toGuess.setText(String.valueOf(wordToGuessSecret));
        chances = 11;
        letters.setText("");
        setGameImage();
    }

    public void checkLetter() {
        String letterInput = letter.getText().toString();
        if (!letter.getText().toString().equals("")
                && wordToGuess.contains(letterInput) && chances > 0) {
            int index = wordToGuess.indexOf(letterInput);
            while (index >= 0) {
                wordToGuessSecret[index] = letterInput.charAt(0);
                index = wordToGuess.indexOf(letterInput, index + 1);
            }
            toGuess.setText(String.valueOf(wordToGuessSecret));
            letters.setText(letters.getText().toString() + letterInput);
            if (wordToGuess.equals(toGuess.getText().toString())) letters.setText("WYGRANA!");
        } else if (chances <= 0) {
            setGameImage();
            letters.setText("PRZEGRANA!");
            toGuess.setText(wordToGuess);
        } else {
            chances--;
            setGameImage();
            letters.setText(letters.getText().toString() + letterInput);
        }
        letter.setText("");
    }

    public void setGameImage() {
        String imageName = GAMEIMAGENAME.get(11 - chances);
        gameImage.setImageResource(getResources().getIdentifier(imageName, "drawable", getPackageName()));
    }
}
