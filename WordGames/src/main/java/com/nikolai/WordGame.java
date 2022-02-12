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

    public void setGameComplete (boolean complete) {
        this.gameComplete = complete;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public abstract void makeGuess(String guess);

}
