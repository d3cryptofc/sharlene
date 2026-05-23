package com.github.d3cryptofc.sharlene.io;

import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import com.github.d3cryptofc.sharlene.utils.JarUtils;
import java.io.File;
import java.util.Properties;
import java.util.jar.JarFile;
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
}
