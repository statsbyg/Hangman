package com.jonecx.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> guessWordCharIndexed = new ArrayList<>();
    private static String selectedGuessWord = null;
    private static String[] guessedLetters = null;
    private static int guessedLettersSize = 0;
    private static int penultimateSize = 0;
    private static int ultimateSize = 0;

    public static void main(String[] args) {
        initiate();
        welcomeMessage();

        for (int currentIndex = 0; currentIndex < guessedLettersSize; currentIndex++) {
            currentState(currentIndex);
            processGuess(currentIndex);
        }
        concludeGame();
    }


    public static void initiate() {
        HangmanLexicon lexicon = new HangmanLexicon();
        selectedGuessWord = lexicon.getWord();

        guessedLetters = new String[selectedGuessWord.length()];

        guessedLettersSize = selectedGuessWord.length();
        penultimateSize = guessedLettersSize - 2;
        ultimateSize = guessedLettersSize - 1;

        for (int index = 0; index < selectedGuessWord.length(); index++) {
            guessWordCharIndexed.add(index, selectedGuessWord.substring(index, index + 1));
            guessedLetters[index] = "_";
        }
    }

    public static void welcomeMessage() {
        System.out.print("Welcome to Hangman!");
    }

    public static void currentState(int currentIndex) {
        System.out.printf("\nThe word now looks like this: %s", printGuessedLetters(guessedLetters));

        if (currentIndex == ultimateSize)
            System.out.print("\nYou have only one guess left");
        else if (currentIndex <= penultimateSize)
            System.out.printf("\nYou have %d guesses left", guessedLettersSize - currentIndex);
    }

    private static void concludeGame() {
        String result = printGuessedLetters(guessedLetters);
        if (result.contains("_")) {
            System.out.printf("\nYou're completely hung.\nThe word was: %s \nYou lose.", selectedGuessWord);
        } else {
            System.out.printf("\nYou guessed the word: %s \nYou win.", selectedGuessWord);
        }
    }

    private static String printGuessedLetters(String[] strArray) {
        return String.join("", strArray);
    }

    public static void processGuess(int currentIndex) {
        int inputCount = 0;
        String guess = "";
        while (inputCount != 1) {
            System.out.print("\nYour guess: ");
            guess = (scanner.nextLine()).toUpperCase();

            inputCount = guess.length();
            if (inputCount != 1)
                System.out.print("Your guess was illegal. Please enter a single letter.");
        }
        int guessIndex = guessWordCharIndexed.indexOf(guess);
        if (guessIndex >= 0 && guessIndex < guessedLettersSize) {
            guessWordCharIndexed.add(guessIndex, "*");
            guessWordCharIndexed.remove(guessIndex + 1);
            guessedLetters[guessIndex] = guess;
            System.out.print("That guess is correct.");
        } else {
            if (currentIndex <= ultimateSize)
                System.out.printf("There are no %s's in the word.", guess);
        }
    }
}
