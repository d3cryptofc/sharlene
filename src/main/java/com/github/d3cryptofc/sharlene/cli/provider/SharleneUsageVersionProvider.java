package com.github.d3cryptofc.sharlene.cli.provider;

import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

/** Provides the current program usage version. */
public class SharleneUsageVersionProvider implements IVersionProvider {

  /** Injected by picocli to provide the command spec. */
  @Spec CommandSpec spec;

  /** Constructs a new {@code SharleneUsageVersionProvider} instance. */
  public SharleneUsageVersionProvider() {}

  /**
   * Gets the current program usage version (for example: {@code Program v1.x.y}).
   *
   * @return An array where each element is a line of text.
   */
  public String[] getVersion() {
    // Get the command name.
    String commandName = spec.qualifiedName();

    // Get the current program version.
    String programVersion = getClass().getPackage().getImplementationVersion();

    // Returns an array containing the program usage version.
    return new String[] {commandName + " v" + programVersion};
  }
}
