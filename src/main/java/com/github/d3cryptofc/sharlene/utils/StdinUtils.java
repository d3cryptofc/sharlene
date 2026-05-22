package com.github.d3cryptofc.sharlene.utils;

import java.util.Scanner;

/**
 * Utility class for reading input from the standard input stream.
 */
public class StdinUtils {

   /**
    * Scanner instance for reading input from the standard input stream.
    */
   private static final Scanner scanner = new Scanner(System.in);

   /**
    * Reads a formatted string from the standard input stream.
    *
    * @param format The format string.
    * @param args   The arguments to be formatted.
    * @return The formatted string read from the standard input stream.
    */
   public static String inputf(String format, Object... args) {
      // Print the formatted string to the standard output stream.
      System.out.printf(format, args);
      // Return the next line of input from the standard input stream.
      return scanner.nextLine();
   }

   /**
    * Reads a confirmation from the standard input stream.
    *
    * @param format The format string.
    * @param args   The arguments to be formatted.
    * @return The confirmation result.
    */
   public static Boolean confirm(String format, Object... args) {
      // Read the formatted string from the standard input stream.
      String text = inputf(format, args).stripLeading();

      // If the input is empty, return null.
      if (text.length() == 0) return null;

      // Get the first character of the input and convert it to lowercase.
      char letter = Character.toLowerCase(text.charAt(0));

      // Switch on the first character of the input.
      switch (letter) {
         // If the first character is 'y', return true.
         case 'y':
            return true;
         // If the first character is 'n', return false.
         case 'n':
            return false;
         // If the first character is not any of the above, return null.
         default:
            return null;
      }
   }
}
