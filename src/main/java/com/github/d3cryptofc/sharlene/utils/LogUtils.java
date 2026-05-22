package com.github.d3cryptofc.sharlene.utils;

import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging.
 */
public class LogUtils {

   /**
    * The width of the section separator ({@value SEPARATOR_WIDTH}).
    */
   private static final int SEPARATOR_WIDTH = 60;

   /**
    * Private constructor to prevent instantiation.
    */
   private LogUtils() {}

   /**
    * Logs a section separator using the given logger.
    *
    * @param logger The logger to use for logging.
    */
   public static void section(Logger logger) {
      // Log the section separator.
      logger.info("-".repeat(SEPARATOR_WIDTH));
   }

   /**
    * Logs a section separator using the given logger, then runs the given runnable.
    *
    * @param logger   The logger to use for logging.
    * @param runnable The runnable to run after logging the section separator.
    */
   public static void section(Logger logger, Runnable runnable) {
      // Log the section separator before running the runnable.
      section(logger);
      // Run the runnable.
      runnable.run();
      // Log the section separator after running the runnable.
      section(logger);
   }
}
