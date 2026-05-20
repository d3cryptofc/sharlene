package com.github.d3cryptofc.sharlene.cli.section;

import java.io.File;
import picocli.CommandLine.Option;

/**
 * This class represents the file options section of the CLI.
 */
public class FileOptionsSection {

   /**
    * Stores the input file path.
    */
   @Option(
      names = {"-i", "--input"},
      paramLabel = "<jarfile>",
      description = "Input for original charles JAR file path"
   )
   public File inputFile;

   /**
    * Stores the output file path.
    */
   @Option(
      names = {"-o", "--output"},
      paramLabel = "<jarfile>",
      required = true,
      description = "Output for patched charles JAR file path"
   )
   public File outputFile;

   /**
    * Stores {@code true} if output file can be overwritten.
    */
   @Option(
      names = {"-w", "--overwrite"},
      description = "Overwrite the output file"
   )
   public boolean canOverwriteOutputFile = false;
}
