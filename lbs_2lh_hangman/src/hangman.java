import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        String[] words = {"java", "programming", "hangman", "gesundheit", "redbull", "hunger", "windoof", "burger", "vegan", "test", "streichholzschächtelchen", "Donaudampfschifffahrtselektrizitätenhauptbetriebswerkbauunterbeamtengeselschaft"};
        String wordToGuess = words[(int) (Math.random() * words.length)];
        StringBuilder guessedWord = new StringBuilder("_".repeat(wordToGuess.length()));
        int attemptsLeft = 9;
        boolean wordGuessed = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("HANGMAN.");

        // wenn mehr als 0 versuche gehts weiter
        while (attemptsLeft > 0 && !wordGuessed) {
            System.out.println("aktueller stand: " + guessedWord);
            System.out.println("versuche übrig: " + attemptsLeft);
            System.out.print("gib einen buchstaben ein: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            // buchstabe doppelt?
            if (guessedWord.toString().contains(String.valueOf(guess))) {
                System.out.println("du hast diesen buchstaben bereits geraten -.-");
                continue;
            }

            // buchstabe richtig erraten
            //wenn das zeichen an der position i gleich dem geratenen buchstaben guess ist, wird das Zeichen im guessedWord an der richtigen stelle dazugemacht
            if (wordToGuess.indexOf(guess) >= 0) {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guess) {
                        guessedWord.setCharAt(i, guess);
                    }
                }
            } else {
                attemptsLeft--;
                System.out.println("der buchstabe ist nicht im wort.");
            }

            if (guessedWord.toString().equals(wordToGuess)) {
                wordGuessed = true;
            }
        }

        if (wordGuessed) {
            System.out.println("wowser du hast '" + wordToGuess + "' erraten.");
        } else {
            System.out.println("oof. das wort wäre '" + wordToGuess + "'.");
        }

        scanner.close();
    }
}
