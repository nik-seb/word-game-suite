package com.nikolai;

import java.util.Scanner;

public class UI {
    private Scanner keyScanner = new Scanner(System.in);

    public UI () {
        launchGame();
    }

    private void launchGame () {
        System.out.println("Welcome to the Word Game Suite. Which game would you like to play?");
        System.out.println("1) Hangman\n2) Word Scramble\n3) Word Mastermind");
        String gameSelection = keyScanner.nextLine();
        WordGame newGame = null;
        if (gameSelection.equals("1") || gameSelection.toLowerCase().equals("hangman")) {
            newGame = new HangmanGame();
        } else if (gameSelection.equals("3") || gameSelection.toLowerCase().equals("word mastermind")) {
            newGame = new MastermindGame();
        }
        // other else ifs here
        promptUserInput(newGame);
    }

    public void promptUserInput (WordGame newGame) {
        while (!newGame.isGameComplete()) {
            System.out.print(newGame.getPromptForGuess());
            String guess = keyScanner.nextLine();
            newGame.makeGuess(guess);
        }
    }
}
