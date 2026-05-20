package com.github.d3cryptofc.sharlene.exception;

/**
 * The root checked exception of Sharlene.
 */
public class SharleneException extends Exception {

   /**
    * Constructs a new exception with a formatted message.
    *
    * @param format  A {@link String#format(String, Object...)} compatible format string.
    * @param args    Arguments referenced by the format specifiers in the format string.
    */
   public SharleneException(String format, Object... args) {
      super(String.format(format, args));
   }
}
