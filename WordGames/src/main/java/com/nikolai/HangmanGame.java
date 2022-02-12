package com.nikolai;

import java.util.ArrayList;
import java.util.List;

public class HangmanGame extends WordGame {
    private List<String> lettersInWord = new ArrayList<>();
    private List<String> lettersNotInWord = new ArrayList<>(); // incorrect guesses
    private List<String> lettersToDisplay = new ArrayList<>(); // correct guesses + placeholders to print


    public HangmanGame () {
        super("Please guess a letter: ");
        String randomWord = super.getWordToGuess();
        for (int i = 0; i < randomWord.length(); i++) {
            lettersInWord.add(randomWord.substring(i, i+1));
            lettersToDisplay.add(" _ "); // so displayed blank characters match length
        }
    }

    @Override
    public void makeGuess(String guess) {
        String thisWord = getWordToGuess();
    }

}
