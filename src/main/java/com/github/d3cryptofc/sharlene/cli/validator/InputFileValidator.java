package com.github.d3cryptofc.sharlene.cli.validator;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.cli.provider.CharlesJarPathProvider;
import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import com.github.d3cryptofc.sharlene.exception.UnsupportedOSException;
import java.io.File;

/** Validates the input file provided by the user. */
public class InputFileValidator {

  /** Private constructor to prevent instantiation. */
  private InputFileValidator() {}

  /**
   * Returns the input file if it is not null, otherwise returns the default {@code 'charles.jar'}
   * file.
   *
   * @param jarfile The input file provided by the user.
   * @return The input file or the default {@code 'charles.jar'} file.
   */
  public static File orEnsureDefaultCharlesJarFile(File jarfile) {
    // If the input file is not null, return it.
    if (jarfile != null) return jarfile;

    try {
      // Get the default 'charles.jar' path for the current OS.
      return new File(CharlesJarPathProvider.getDefault());
    } catch (UnsupportedOSException e) {
      // If the OS is unsupported, throw an runtime exception.
      throw new SharleneRuntimeException(
          "Could not detect the default 'charles.jar' path for your operating system."
              + "Please provide it using the -i flag.");
    }
  }

  /**
   * Checks that the file is a JAR file (ends with {@code '.jar'}) and starts with {@code
   * 'charles'}.
   *
   * @param jarfile The file to check.
   */
  public static void checkIsCharlesJarFile(File jarfile) {
    //
    String name = jarfile.getName().toLowerCase();

    // If the file is not a JAR file or does not start with 'charles', throw an exception.
    if (!jarfile.isFile() || !name.endsWith(".jar")) {
      throw new SharleneRuntimeException(
          "'%s' must be the charles JAR file! Try using a different path as an argument.",
          jarfile.getPath());
    }

    // If the file name does not start with 'charles', log a warning.
    if (!name.startsWith("charles")) {
      Main.LOGGER.warn(
          "You selected a JAR file containing a different name. Is this a mistake? "
              + "Be sure that it is the 'charles.jar'.",
          jarfile.getPath());
    }
  }
}
