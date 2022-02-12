package com.nikolai;

import java.util.ArrayList;
import java.util.List;

public class MastermindGame extends WordGame {

    private List<String> lettersInWord = new ArrayList<>();

    public MastermindGame () {
        super("Guess a word: ", 7, 3, 6);
        String randomWord = super.getWordToGuess();
        for (int i = 0; i < randomWord.length(); i++) {
            lettersInWord.add(randomWord.substring(i, i+1).toUpperCase());
        }
        System.out.println("Your word has " + lettersInWord.size() + " letters.");
        System.out.println("Cheat: Your word is " + randomWord);
    }

    @Override
    public void makeGuess(String guess) {
        // Not found in word = X | Found in that exact place: ! | Found elsewhere in word: ?
        // CURRENT WEAKNESS: does not indicate number of letters that match.
        //    eg., if the word is "class" and you guess "sssss", all letters will be marked "?" instead of just two
        guess = guess.toUpperCase();
        String displayString = "";
        int correctlyPlacedLetters = 0;
        if (guess.length() != lettersInWord.size()) {
            System.out.println("Sorry, that word is the wrong length. \nPlease guess a word with " + lettersInWord.size() + " letters.");
            return;
        } else {
            for (int i = 0; i < guess.length(); i++) {
                String thisLetter = guess.substring(i, i+1);
                if (thisLetter.equals(lettersInWord.get(i))) {
                    displayString += "!";
                    correctlyPlacedLetters++;
                } else if (lettersInWord.contains(thisLetter)) {
                    displayString += "?";
                } else {
                    displayString += "X";
                }
            }
        }
        System.out.println("Result: \n" +
                "   " + guess + "\n" +
                "   " + displayString);
        if (correctlyPlacedLetters == lettersInWord.size()) {
            System.out.println("Congratulations, you've guessed it!");
            super.setGameComplete(true);
        } else if (getNumberOfIncorrectGuesses() == getMaxIncorrectGuesses()) {
            System.out.println("Sorry, you've run out of guesses. Game over.");
            System.out.println("The correct word was: " + super.getWordToGuess());
            super.setGameComplete(true);
        } else {
            increaseNumberOfIncorrectGuesses();
            System.out.println("You have " + (getMaxIncorrectGuesses() - getNumberOfIncorrectGuesses()) + " guesses remaining.");
        }
    }

}
