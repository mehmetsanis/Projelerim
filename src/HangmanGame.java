import java.util.Scanner;

/**
 * Main method plays the game by creating instance of Hangman class & calling its methods
 *
 * @author Cihan Erkan, Beyza Kalkanli, Ghada Ibrahim, Can Soygur, Burak Tos, Umair Ahmed
 * @version 12.02.2017
 */
public class HangmanGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // variables
        Hangman hangmanInstance;
        StringBuffer secretWord;
        char letter;
        int letterOccurence;

        // program code
        hangmanInstance = new Hangman();

        secretWord = new StringBuffer(hangmanInstance.chooseSecretWord());
        hangmanInstance.setKnownSoFar();
        System.out.println(hangmanInstance.getAllLetters());
        do // The loop where the game is played.
        {
            // Prints the known-so-far and used letters.
            System.out.println(hangmanInstance.getKnownSoFar() + "\nUsed Letters: " + hangmanInstance.getUsedLetters());

            // Asks user for a guess.
            System.out.println("Guess a letter: ");
            letter = scan.next().charAt(0);

            // Checks the given inputs and tries the letter.
            letterOccurence = hangmanInstance.tryThis(letter);

            if (letterOccurence == -1) {
                System.out.println("Letter is not valid.");
            } else if (letterOccurence == -2) {
                System.out.println("Letter was already used.");
            } else if (letterOccurence == -3) {
                System.out.println("Game is over.");
            }
            System.err.println("Number of Tries Left:  " + (6-hangmanInstance.getNumOfIncorrectTries()));
        } while (!hangmanInstance.isGameOver()); // Checks if the game is over.

        // Prints the result.
        if (hangmanInstance.won()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }

    }

}