package com.nikolai;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MastermindGame extends WordGame {

    private List<String> lettersInWord = new ArrayList<>();
    private int currentCorrectLetters = 0;

    public MastermindGame () {
        super("Guess a word: ", 7, "src/main/resources/animal-vocab.txt", 3, 6);
        String randomWord = super.getWordToGuess();
        for (int i = 0; i < randomWord.length(); i++) {
            lettersInWord.add(randomWord.substring(i, i+1).toUpperCase());
        }
        System.out.println("Your word has " + lettersInWord.size() + " letters.");
    }

    @Override
    public void makeGuess(String guess) {
        // Not found in word = X | Found in that exact place: ! | Found elsewhere in word: ?
        guess = guess.toUpperCase();
        String displayString = "";
        if (guess.length() != lettersInWord.size()) {
            System.out.println("Sorry, that word is the wrong length. \nPlease guess a word with " + lettersInWord.size() + " letters.");
            return;
        } else {
            displayString = compareGuessToWord(guess);
        }
        System.out.println("Result: \n" +
                "   " + guess + "\n" +
                "   " + displayString);
        if (currentCorrectLetters == lettersInWord.size()) {
            System.out.println("Congratulations, you've guessed it!");
            super.setGameComplete(true);
        } else {
            increaseNumberOfIncorrectGuesses();
            if (getNumberOfIncorrectGuesses() == getMaxIncorrectGuesses()) {
                System.out.println("Sorry, you've run out of guesses. Game over.");
                System.out.println("The correct word was: " + super.getWordToGuess());
                super.setGameComplete(true);
            } else {
                System.out.println("You have " + (getMaxIncorrectGuesses() - getNumberOfIncorrectGuesses()) + " guesses remaining.");
            }
        }


    }

    private String compareGuessToWord (String guess) {
        String displayString = "";
        Map<String, List<Integer>> letterMap = letterOccurrences();
        String[] markedPlaces = new String[lettersInWord.size()];
        currentCorrectLetters = 0;

        // first, find any correct placements and mark their positions in array
        // increment the number of guesses that have been marked ! or ? in List[0]
        for (int i = 0; i < guess.length(); i++) {
            String thisLetter = guess.substring(i, i+1);
            if (thisLetter.equals(lettersInWord.get(i))) {
                markedPlaces[i] = "!";
                currentCorrectLetters++;
                int currentCount = letterMap.get(thisLetter).get(0);
                letterMap.get(thisLetter).remove(0);
                letterMap.get(thisLetter).add(0, currentCount+1);
            }
        }

        // check that current letter exists in word but not in the exact place
        // if so, only mark as "?" if fewer guesses have been marked ?/! than exist in the word
        for (int i = 0; i < lettersInWord.size(); i++) {
            String thisLetter = guess.substring(i, i+1);
            if (!thisLetter.equals(lettersInWord.get(i)) && lettersInWord.contains(thisLetter)) {
                int letterGuesses = letterMap.get(thisLetter).get(0);
                int totalOccurrencesOfLetter = letterMap.get(thisLetter).get(1);
                if (letterGuesses < totalOccurrencesOfLetter) {
                    markedPlaces[i] = "?";
                    letterMap.get(thisLetter).remove(0);
                    letterMap.get(thisLetter).add(0, letterGuesses+1);
                } else {
                    markedPlaces[i] = "X";
                }
            } else if (!lettersInWord.contains(thisLetter)) {
                markedPlaces[i] = "X";
            }
        }

        for (String mark : markedPlaces) {
            displayString += mark;
        }

        return displayString;
    }

    private Map<String, List<Integer>> letterOccurrences () {
        // returns a map containing key: Letter, value: List of Occurrences
            // list[0] will be updated with number of marked guesses (!, then ?)
            // list[1] will be updated with number of occurrences in original word
        Map <String, List<Integer>> letterMap = new HashMap();
        for (String letter : lettersInWord) {
            if (!letterMap.containsKey(letter)) {
                List<Integer> newList = new ArrayList<>();
                newList.add(0);
                newList.add(1);
                letterMap.put(letter, newList);
            } else {
                int currentCount = letterMap.get(letter).get(1);
                letterMap.get(letter).remove(1);
                letterMap.get(letter).add(currentCount+1);
            }
        }
        return letterMap;
    }

}
