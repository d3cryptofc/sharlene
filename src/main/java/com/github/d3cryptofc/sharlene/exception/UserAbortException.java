package com.github.d3cryptofc.sharlene.exception;

/**
 * Thrown to indicate a user abortion request.
 */
public class UserAbortException extends SharleneRuntimeException {

   /**
    * {@value #DEFAULT_MESSAGE}
    */
   public static final String DEFAULT_MESSAGE = "Operation aborted by user.";

   /**
    * Constructs a new exception with the predefined message: {@value #DEFAULT_MESSAGE}
    */
   public UserAbortException() {
      super(DEFAULT_MESSAGE);
   }
}
