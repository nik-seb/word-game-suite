package com.nikolai;

public abstract class WordGame {
    private String wordToGuess;
    private int numberOfIncorrectGuesses;
    private int maxIncorrectGuesses;
    private boolean gameComplete = false;

    public WordGame () {
        VocabList vocabList = new VocabList();
        this.wordToGuess = vocabList.getRandomWord();
    }

}
