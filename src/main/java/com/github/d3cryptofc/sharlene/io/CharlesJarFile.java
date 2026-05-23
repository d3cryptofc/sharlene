package com.github.d3cryptofc.sharlene.io;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import com.github.d3cryptofc.sharlene.utils.JarUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/** Represents a Charles JAR file and provides methods to read and patch it. */
public class CharlesJarFile implements AutoCloseable {

  /** The path to the Charles JAR file. */
  private final File file;

  /** The `JarFile` instance. */
  private JarFile jarFile;

  /** The Charles version extracted from the JAR file. */
  private String charlesVersion;

  /**
   * Constructs a `CharlesJarFile` instance for the specified Charles JAR file.
   *
   * @param file the path to the Charles JAR file
   */
  public CharlesJarFile(File file) {
    // Initialize the `file` field.
    this.file = file;
    // Load the JAR file into memory.
    this.loadJarFile();
    // Load the Charles version from the JAR file.
    this.loadCharlesVersion();
  }

  /** Closes the JAR file and releases any resources. */
  @Override
  public void close() {
    // Close the JAR file and release resources.
    JarUtils.closeJarFile(this.jarFile);
  }

  /** Loads the JAR file into memory. */
  private void loadJarFile() {
    // Load the JAR file into memory.
    this.jarFile = JarUtils.openJarFile(this.file);
  }

  /** Loads the Charles version from the JAR file. */
  private void loadCharlesVersion() {
    // Get the `init.properties` entry from the JAR file.
    ZipEntry entry = JarUtils.getZipEntry(this.jarFile, "com/charlesproxy/init.properties");

    // Load the properties from the entry.
    Properties properties = JarUtils.getPropertiesFromZipEntry(this.jarFile, entry);

    // Get the `charlesVersion` property from the properties object.
    String version = (String) properties.get("charlesVersion");

    // If the version is null, throw an exception.
    if (version == null) {
      throw new SharleneRuntimeException(
          "We were unable to obtain the Charles Proxy version.  "
              + "The structure may have been changed.");
    }

    // Set the `charlesVersion` field to the version obtained from the properties.
    this.charlesVersion = version;
  }

  /**
   * Returns the Charles Proxy version obtained from the JAR file.
   *
   * @return The Charles Proxy version.
   */
  public String getCharlesVersion() {
    // Return the `charlesVersion` field.
    return this.charlesVersion;
  }

  /**
   * Returns the `JarFile` instance used to read the JAR file.
   *
   * @return The `JarFile` instance.
   */
  public JarFile getJarFile() {
    // Return the `jarFile` field.
    return this.jarFile;
  }

  /**
   * Walks over the entries in the JAR file and copies them to the destination JAR file using the provided processors.
   *
   * @param destination         The destination JAR file.
   * @param walkCopyProcessors  The processors to use for copying each entry.
   */
  public void walkCopy(File destination, Collection<WalkCopyProcessor> walkCopyProcessors) {
    // Log the start of the JAR walking copy.
    Main.LOGGER.debug("Starting JAR walking copy.");

    // Get the entries from the JAR file and iterate over them.
    Enumeration<JarEntry> entries = this.jarFile.entries();
    // Initialize the `entry` variable.
    ZipEntry entry;
    // Initialize the `content` variable to an empty byte array.
    byte[] content = new byte[0];

    // If no walk copy processors are provided, use a default one.
    if (walkCopyProcessors.size() == 0) {
      walkCopyProcessors = Collections.singletonList(new WalkCopyProcessor());
    }

    try (
    // Create a `JarOutputStream` to write the output JAR file.
    JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(destination))) {
      // Iterate over the entries in the JAR file.
      while (entries.hasMoreElements()) {
        // Get the next entry from the JAR file.
        entry = entries.nextElement();
        // Log the entry being processed.
        Main.LOGGER.debug("Received entry: {}", entry);

        // Call the `accept` method of each `walkCopyProcessor` to handle the entry.
        for (WalkCopyProcessor walkCopyProcessor : walkCopyProcessors) {
          // Set the `content` variable to the result of the `accept` method.
          content = walkCopyProcessor.accept(this, entry);
        }
        // Put the current entry into the output JAR file.
        jarOutputStream.putNextEntry(entry);
        // Write the content of the entry to the output JAR file.
        jarOutputStream.write(content);
        // Close the current entry in the output JAR file.
        jarOutputStream.closeEntry();
      }
    } catch (IOException e) {
      // Throw a `SharleneRuntimeException` if an I/O error occurs.
      throw new SharleneRuntimeException("Failed to open JAR output stream: %s", e.getMessage());
    }
  }
}
