import java.util.*;
import java.io.*;

/**
 * _This class creates a Hangman object for the game __
 *
 * @author _Berfin KÃœÃ‡ÃœK__
 * @author _Ã‡aÄŸla SÃ¶zen__
 * @author _Ä°dil HANHAN__
 * @author _Merve SAÄ�YATANLAR__
 * @author _YaÄŸÄ±z Efe MERTOL__
 * @version _11.02.2017_
 */
public class Hangman {
   // properties
   
   //constants
   
   private int MAX_ALLOWED_INCORRECT_TRIES;
   
   
   //variables
   
   private StringBuffer secretWord;
   private StringBuffer allLetters = new StringBuffer();;
   private StringBuffer usedLetters;
   private int numberOfIncorrectTries;
   private StringBuffer knownSoFar;
   
   // constructors
   
   public Hangman() {
      numberOfIncorrectTries = 0;
      secretWord = chooseSecretWord();
      knownSoFar = new StringBuffer();
      usedLetters = new StringBuffer();
      MAX_ALLOWED_INCORRECT_TRIES = 6;
      
      for (int i = 0; i < 26; i++) {
         allLetters = allLetters.append((char) (i + 65));
      }
   }
   
   // methods
   
   /**
    * A method to return all letters that can be used
    *
    * @return allLetters
    */
   
   public StringBuffer getAllLetters() {
      return allLetters;
   }
   public StringBuffer getSecret() {
      return secretWord;
   }
   public void setKnownSoFar() {
      for (int i = 0; i < secretWord.length(); i++) {
         knownSoFar.append('*');
      }
   }
   
   /**
    * A method to get the letters used by the player
    *
    * @return usedLetters as a String
    */
   
   public StringBuffer getUsedLetters() {
      return usedLetters;
   }
   
   /**
    * A method to get the number of incorrect tries
    *
    * @return numberOfIncorrectTries as an int
    */
   
   public int getNumOfIncorrectTries() {
      return numberOfIncorrectTries;
   }
   
   /**
    * A method to get the maximum number of incorrect tries
    *
    * @return MAX_ALLOWED_INCORRECT_TRIES as an int
    */
   
   public int getMaxAllowedIncorrectTries() {
      return MAX_ALLOWED_INCORRECT_TRIES;
   }
   
   /**
    * A method to get the letters known so far
    *
    * @return knownSoFar as a String
    */
   
   public StringBuffer getKnownSoFar() {
      return knownSoFar;
   }
   
   /**
    * A method to indicate whether the game is over or not
    *
    * @return the status of the game as boolean
    */
   
   public boolean isGameOver() {
      return (numberOfIncorrectTries == MAX_ALLOWED_INCORRECT_TRIES || won());
   }
   
   /**
    * A method to indicate whether the game is lost or not
    *
    * @return a boolean according to the status of the game
    */
   
   public boolean won() {
      for (int i = 0; i < knownSoFar.length(); i++) {
         if (! ((knownSoFar.charAt(i)+"").equalsIgnoreCase(secretWord.charAt(i)+"")))
            return false;
      }
      return true;
   }
   
   /**
    * A method to choose a secret word
    *
    * @return secretWord as a String
    */
   
   public static StringBuffer chooseSecretWord() {
      StringBuffer randomWord = new StringBuffer();
      Scanner scn;
      ArrayList<String> words = new ArrayList<>(); // an ArrayList to contain all the words from the dictionary
      File dictionary = new File("C:\\Users\\pc\\Desktop\\src\\Dictionary.txt"); // the file location on your computer goes here
      
      scn = null; // to avoid the "Scanner may not have been initialised" error
      
      try {
         scn = new Scanner(dictionary);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      
      while (scn.hasNext()){
         words.add(scn.next());
      }
      
      randomWord.append(words.get( (int) (Math.random() * words.size())));
      return randomWord;
   }
   
   /**
    * A method to try a letter in the word
    *
    * @return the number of repetitions of the letter in the word as an int
    */
   
   public int tryThis(char letter) {
      int count;
      int count2;
      
      count = 0;
      count2 = 0;
      
      for (int a = 0; a < allLetters.length(); a++) {
         if ((allLetters.charAt(a) + "").equalsIgnoreCase(letter + ""))
            count2++;
      }
      if (count2 == 0)
         return -1;
      
      
      for (int a = 0; a < usedLetters.length(); a++) {
         if ((usedLetters.charAt(a) + "").equalsIgnoreCase(letter + ""))
            return -2;
      }
      
      if (isGameOver())
         return -3;
      
      usedLetters.append(letter);
      
      for (int a = 0; a < secretWord.length(); a++) {
         if ((secretWord.charAt(a) + "").equalsIgnoreCase(letter + "")) {
            knownSoFar.setCharAt(a, letter);
            
            count++;
         }
      }
      if (count == 0)
         numberOfIncorrectTries++;
      
      return count;
      
   }
   
   
}