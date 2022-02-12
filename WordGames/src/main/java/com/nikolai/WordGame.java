package com.nikolai;

public abstract class WordGame {
    private String wordToGuess;
    private int numberOfIncorrectGuesses;
    private int maxIncorrectGuesses;
    private boolean gameComplete = false;
    private String promptForGuess; // to be used in UI loop, eg.: "Please guess a letter" or "Please enter your guess"

    public WordGame (String promptForGuess, int maxIncorrectGuesses) {
        VocabList vocabList = new VocabList();
        this.wordToGuess = vocabList.getRandomWord();
        this.promptForGuess = promptForGuess;
        this.maxIncorrectGuesses = maxIncorrectGuesses;
    }

    public WordGame (String promptForGuess, int maxIncorrectGuesses, int minWordLength, int maxWordLength) {
        VocabList vocabList = new VocabList();
        vocabList.filterVocabList(minWordLength, maxWordLength);
        this.wordToGuess = vocabList.getRandomWord();
        this.promptForGuess = promptForGuess;
        this.maxIncorrectGuesses = maxIncorrectGuesses;
    }

    public void increaseNumberOfIncorrectGuesses () {
        numberOfIncorrectGuesses++;
    }

    public String getPromptForGuess() {
        return promptForGuess;
    }

    public String getWordToGuess () {
        return wordToGuess;
    }

    public int getNumberOfIncorrectGuesses () {
        return numberOfIncorrectGuesses;
    }

    public int getMaxIncorrectGuesses () {
        return maxIncorrectGuesses;
    }

    public void setGameComplete (boolean complete) {
        this.gameComplete = complete;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public abstract void makeGuess(String guess);

}
