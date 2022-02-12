package com.nikolai;

import java.util.ArrayList;
import java.util.List;

public class HangmanGame extends WordGame {
    private List<String> lettersInWord = new ArrayList<>();
    private List<String> lettersNotInWord = new ArrayList<>(); // incorrect guesses
    private List<String> lettersToDisplay = new ArrayList<>(); // correct guesses + placeholders to print


    public HangmanGame () {
        super("Please guess a letter: ", 6);
        String randomWord = super.getWordToGuess();
        for (int i = 0; i < randomWord.length(); i++) {
            lettersInWord.add(randomWord.substring(i, i+1).toUpperCase());
            lettersToDisplay.add(" _ "); // so displayed blank characters match length
        }
        printLettersToDisplay();
    }

    @Override
    public void makeGuess(String letter) {
        letter = letter.toUpperCase();
        if (lettersInWord.contains(letter)) {
            System.out.println("Correct!");
            populateLettersToDisplay(letter);
            printLettersToDisplay();
            if (!lettersToDisplay.contains(" _ ")) {
                super.setGameComplete(true);
                System.out.println("Congratulations, you won!");
            }
        } else {
            super.increaseNumberOfIncorrectGuesses();
            lettersNotInWord.add(letter);
            drawHangman();
            System.out.println("Oh no! There's no " + letter + " in this word.");
            if (super.getNumberOfIncorrectGuesses() == super.getMaxIncorrectGuesses()) {
                System.out.println("Game over!");
                super.setGameComplete(true);
            } else {
                System.out.println("You have " + (super.getMaxIncorrectGuesses() - super.getNumberOfIncorrectGuesses()) + " incorrect guesses remaining");
                System.out.println("Incorrect guesses: " + lettersNotInWord);
                printLettersToDisplay();
            }
        }
    }

    private void drawHangman () {
        //   ______
        //   |    |
        //   O    |
        //  /|\   |
        //  / \   |
        // ------------
        int incorrectGuesses = super.getNumberOfIncorrectGuesses();
        System.out.println("   ______");
        System.out.println("   |    |");
        if (incorrectGuesses >= 1) {    //head
            System.out.println("   O    |");
        } else {
            System.out.println("        |");
        }
        if (incorrectGuesses == 2) { // body
            System.out.println("   |    |");
        } else if (incorrectGuesses == 3) {
            System.out.println("  /|    |");
        } else if (incorrectGuesses >= 4) {
            System.out.println("  /|\\   |");
        } else {
            System.out.println("        |");
        }
        if (incorrectGuesses == 5) { // legs
            System.out.println("  /     |");
        } else if (incorrectGuesses == 6) {
            System.out.println("  / \\   |");
        } else {
            System.out.println("        |");
        }
        System.out.println("------------");
    }

    private void populateLettersToDisplay (String letter) {
        // loop through and input every instance of guessed letter into displayed word
        for (int i = 0; i < lettersInWord.size(); i++) {
            if (lettersInWord.get(i).equals(letter)) {
                lettersToDisplay.remove(i);
                lettersToDisplay.add(i, letter);
            }
        }
    }

    private void printLettersToDisplay () {
        System.out.print("Word to guess: ");
        for (String letter : lettersToDisplay) {
            System.out.print(letter);
        }
        System.out.println("");
    }

}
