package com.github.d3cryptofc.sharlene.exception;

/**
 * Thrown to indicate an unsupported operating system.
 */
public class UnsupportedOSException extends SharleneException {

   /**
    * {@value #DEFAULT_MESSAGE}
    */
   public static final String DEFAULT_MESSAGE =
      "Operation not supported by your operating system.";

   /**
    * Constructs a new exception with the predefined message: {@value #DEFAULT_MESSAGE}
    */
   public UnsupportedOSException() {
      super(DEFAULT_MESSAGE);
   }
}
