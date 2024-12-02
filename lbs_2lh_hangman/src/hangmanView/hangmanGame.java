package hangmanView;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class hangmanGame {
    private String[] words;
    private String wordToGuess;
    private StringBuilder guessedWord;
    private int attemptsLeft;
    private boolean wordGuessed;
    private Set<Character> guessedLetters;

    public hangmanGame() {
        words = loadWordsFromFile("lbs_2lh_hangman/src/words.txt");
        Random random = new Random();
        wordToGuess = words[random.nextInt(words.length)];
        guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        attemptsLeft = 9;
        wordGuessed = false;
        guessedLetters = new HashSet<>();
    }

    private String[] loadWordsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            return br.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; // Rückgabe eines leeren Arrays im Fehlerfall
        }
    }

    public String getGuessedWord() {
        return guessedWord.toString();
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public boolean isWordGuessed() {
        return wordGuessed;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public String[] getWords() {
        return words;
    }

    public String makeGuess(char guess) {
        // Überprüfen, ob der Buchstabe bereits geraten wurde
        if (guessedWord.toString().contains(String.valueOf(guess))) {
            return "du hast diesen Buchstaben bereits geraten du eierschwammerl -.-'";
        }
        guessedLetters.add(guess);



        // Buchstabe richtig erraten
        if (wordToGuess.indexOf(guess) >= 0) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    guessedWord.setCharAt(i, guess);
                }
            }
            if (guessedWord.toString().equals(wordToGuess)) {
                wordGuessed = true;
                return "wowser du hast '" + wordToGuess + "' erraten.";
            }
            return "Des haut ja hin!";
        } else {
            attemptsLeft--;
            if (attemptsLeft <= 0) {
                return "oof. das wort wäre '" + wordToGuess + "'.";
            }
            return "Der Buchstabe ist nicht im Wort LOL. Versuche übrig: " + attemptsLeft;
        }
    }
    public String getGuessedLetters() {
        StringBuilder letters = new StringBuilder();
        for (char letter : guessedLetters) {
            letters.append(letter).append(" ");
        }
        return letters.toString().trim();
    }
}
