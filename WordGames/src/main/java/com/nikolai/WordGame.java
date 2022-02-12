package com.nikolai;

public abstract class WordGame {
    private String wordToGuess;
    private int numberOfIncorrectGuesses;
    private int maxIncorrectGuesses;
    private boolean gameComplete = false;
    private String promptForGuess; // to be used in UI loop, eg.: "Please guess a letter" or "Please enter your guess"

    public WordGame (String promptForGuess) {
        VocabList vocabList = new VocabList();
        this.wordToGuess = vocabList.getRandomWord();
        this.promptForGuess = promptForGuess;
    }

    public String getPromptForGuess() {
        return promptForGuess;
    }

    public void setGameComplete (boolean complete) {
        this.gameComplete = complete;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public abstract void makeGuess(String guess);

}
