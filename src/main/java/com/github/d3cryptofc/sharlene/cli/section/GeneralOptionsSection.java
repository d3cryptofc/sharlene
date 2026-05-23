package com.github.d3cryptofc.sharlene.cli.section;

import picocli.CommandLine.Option;

/** This class represents the general options section of the CLI. */
public class GeneralOptionsSection {

  /** Constructs a new {@code GeneralOptionsSection} instance. */
  public GeneralOptionsSection() {
    super();
  }

  /** Stores {@code true} when the help flag is used. */
  @Option(
      names = {"-h", "--help"},
      description = "Display this help information and exit",
      usageHelp = true)
  public boolean helpRequested = false;

  /** Stores {@code true} when the version flag is used. */
  @Option(
      names = {"-V", "--version"},
      description = "Display program version and exit",
      versionHelp = true)
  public boolean versionRequested = false;
}
