package com.mobilelab03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class Task02Activity extends AppCompatActivity {

    private Button nextWord;
    private Button check;
    private Button exit;
    private Toolbar header;
    private EditText attempts;
    private EditText attemptsLeft;
    private EditText toGuess;
    private EditText letter;
    private EditText letters;

    public static final Random Randomizer = new Random();
    public static final String[] WORDS = {
            "obiekt", "funkcja", "dysk", "system", "gra", "studio", "rzut"};
    int chances;
    String wordToGuess;
    char[] wordToGuessSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task02);
        header = (Toolbar) findViewById(R.id.toolbar);
        nextWord = (Button) findViewById(R.id.nextWord);
        check = (Button) findViewById(R.id.check);
        exit = (Button) findViewById(R.id.exit);
        attempts = (EditText) findViewById(R.id.attempts);
        attemptsLeft = (EditText) findViewById(R.id.attemptsLeft);
        toGuess = (EditText) findViewById(R.id.toGuess);
        letter = (EditText) findViewById(R.id.letter);
        letters = (EditText) findViewById(R.id.letters);

        attempts.setEnabled(false);
        attemptsLeft.setEnabled(false);
        toGuess.setEnabled(false);
        letters.setEnabled(false);
        header.setTitle("Wisielec v1.0");
        newGame();


        check.setOnClickListener((click) -> {
            if (!letters.getText().toString().equals("WYGRANA!") || !letters.getText().toString().equals("PRZEGRANA!"))
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
        chances = wordToGuess.length() + 4;
        attemptsLeft.setText(String.valueOf(chances));
        letters.setText("");
    }

    public void checkLetter() {
        String letterInput = letter.getText().toString();
        if (!letter.getText().toString().equals("") && wordToGuess.contains(letterInput) && chances > 0) {
            int index = wordToGuess.indexOf(letterInput);
            while (index >= 0) {
                wordToGuessSecret[index] = letterInput.charAt(0);
                index = wordToGuess.indexOf(letterInput, index + 1);
            }
            toGuess.setText(String.valueOf(wordToGuessSecret));
            letters.setText(letters.getText().toString() + letterInput);
            if (wordToGuess.equals(toGuess.getText().toString())) {
                letters.setText("WYGRANA!");
            }
        } else if (chances <= 0) {
            letters.setText("PRZEGRANA!");
            toGuess.setText(wordToGuess);
        } else {
            chances--;
            attemptsLeft.setText(String.valueOf(chances));
            letters.setText(letters.getText().toString() + letterInput);
        }
        letter.setText("");
    }
}
