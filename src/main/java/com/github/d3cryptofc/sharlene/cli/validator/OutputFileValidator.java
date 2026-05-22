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
    * If the file is a directory, adds the default output file name.
    * Otherwise, returns the file as-is.
    *
    * @param file The file to validate.
    * @return The validated file.
    */
   public static File orAddFileNameIfIsDirectory(File file) {
      // If the file is a directory, add the default output file name.
      if (file.isDirectory()) return new File(file, "output.jar");
      // Otherwise, return the file as-is.
      return file;
   }

   /**
    * Checks if the output file already exists.
    *
    * @param jarfile The output file to check.
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
