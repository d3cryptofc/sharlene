package com.github.d3cryptofc.sharlene.io;

import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;

/**
 * A processor that handles walking over a JAR file and copying its contents to an output JAR file.
 */
public class WalkCopyProcessor {

  /**
   * Accepts a JAR entry and returns its contents as a byte array.
   *
   * @param charlesJarFile The Charles JAR file being processed.
   * @param entry The JAR entry to process.
   * @return The contents of the JAR entry as a byte array.
   */
  public byte[] accept(CharlesJarFile charlesJarFile, ZipEntry entry) {
    try (
    // Get an input stream for the entry.
    InputStream inputStream = charlesJarFile.getJarFile().getInputStream(entry)) {
      // Read the entry contents into a byte array.
      return inputStream.readAllBytes();
    } catch (IOException e) {
      // Throw a `SharleneRuntimeException` if an I/O error occurs.
      throw new SharleneRuntimeException("Failed to read JAR entry: %s", e.getMessage());
    }
  }
}
