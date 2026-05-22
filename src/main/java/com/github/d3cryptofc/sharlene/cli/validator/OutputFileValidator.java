package com.github.d3cryptofc.sharlene.cli.validator;

import com.github.d3cryptofc.sharlene.Main;
import java.io.File;

/**
 * Validates the output file provided by the user.
 */
public class OutputFileValidator {

   /**
    * Private constructor to prevent instantiation.
    */
   private OutputFileValidator() {}

   /**
    * Checks if the output file already exists.
    *
    * @param jarfile the output file to check
    * @return {@code true} if the file already exists, {@code false} otherwise.
    */
   public static boolean checkFileAlreadyExists(File jarfile) {
      // Check if the file already exists
      if (!jarfile.isFile()) return false;
      // Log a warning if the file already exists
      Main.LOGGER.warn("It seems the output file already exists.");
      // Return true if the file already exists.
      return true;
   }
}
