package com.github.d3cryptofc.sharlene.utils;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * Utility class for working with JAR files.
 */
public class JarUtils {

   /**
    * Private constructor to prevent instantiation.
    */
   private JarUtils() {}

   /**
    * Opens a JAR file from the given file path.
    *
    * @param file The JAR file to open.
    * @return The opened JAR file.
    * @throws SharleneRuntimeException If an error occurs while opening the JAR.
    */
   public static JarFile openJarFile(File file) {
      // Log the JAR file path for debugging purposes.
      Main.LOGGER.debug("Opening JAR file: {}", file.getPath());

      try {
         // Return the opened JAR file.
         return new JarFile(file);
      } catch (IOException e) {
         // Throw a runtime exception if an error occurs while opening the JAR.
         throw new SharleneRuntimeException(
            "An error occurred while opening the JAR: " + e.getMessage()
         );
      }
   }

   /**
    * Closes the given JAR file, if it is not null.
    *
    * @param jarFile The JAR file to close.
    */
   public static void closeJarFile(JarFile jarFile) {
      // Return early if the JAR file is null.
      if (jarFile == null) return;

      try {
         // Close the JAR file.
         jarFile.close();
      } catch (IOException e) {
         // Throw a runtime exception if an error occurs while closing the JAR.
         throw new SharleneRuntimeException(
            "Failed to close JAR file: " + e.getMessage()
         );
      }
   }

   /**
    * Returns the ZIP entry with the given name from the given JAR file.
    *
    * @param jarFile The JAR file to search for the entry.
    * @param name    The name of the entry to find.
    * @return The ZIP entry with the given name.
    * @throws SharleneRuntimeException If the entry is not found.
    */
   public static ZipEntry getZipEntry(JarFile jarFile, String name) {
      // Log the entry name for debugging purposes.
      Main.LOGGER.debug("Obtaining JAR entry: {}", name);

      // Get the entry from the JAR file.
      ZipEntry entry = jarFile.getEntry(name);

      // Return the entry if it was found.
      if (entry != null) return entry;

      // Otherwise, throw an exception.
      throw new SharleneRuntimeException("JAR entry not found: " + name);
   }

   /**
    * Reads the properties from a ZIP entry in a JAR file.
    *
    * @param jarFile The JAR file to read from.
    * @param entry   The ZIP entry to read from.
    * @return The properties read from the entry.
    * @throws SharleneRuntimeException If the properties could not be read.
    */
   public static Properties getPropertiesFromZipEntry(
      JarFile jarFile,
      ZipEntry entry
   ) {
      // Initialize the properties object.
      Properties properties;

      // Log the entry name for debugging purposes.
      Main.LOGGER.debug("Reading properties for entry: {}", entry.getName());

      try (InputStream inputStream = jarFile.getInputStream(entry)) {
         // Load the properties from the input stream.
         properties = new Properties();
         properties.load(inputStream);
      } catch (IOException e) {
         // Throw a runtime exception if the properties could not be read.
         throw new SharleneRuntimeException(
            "Failed to read properties from JAR entry:" + e.getMessage()
         );
      }

      // Return the properties object.
      return properties;
   }
}
