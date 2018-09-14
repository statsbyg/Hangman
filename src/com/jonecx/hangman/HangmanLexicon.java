package com.jonecx.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangmanLexicon {
    private static final List<String> words = new ArrayList<String>();

    public HangmanLexicon() {
        words.addAll(Arrays.asList("BUOY", "COMPUTER", "CONNOISSEUR", "DEHYDRATE", "FUZZY", "HUBBUB", "KEYHOLE", "QUAGMIRE", "SLITHER", "ZIRCON"));
    }

    public String getWord() {
        Random rnd = new Random();
        int index = rnd.nextInt(8) + 1;
        return words.get(index);
    }
}
