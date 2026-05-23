package com.github.d3cryptofc.sharlene.exception;

/** The root unchecked exception of Sharlene. */
public class SharleneRuntimeException extends RuntimeException {

  /**
   * Constructs a new exception with a formatted message.
   *
   * @param format A {@link String#format(String, Object...)} compatible format string.
   * @param args Arguments referenced by the format specifiers in the format string.
   */
  public SharleneRuntimeException(String format, Object... args) {
    super(String.format(format, args));
  }
}
