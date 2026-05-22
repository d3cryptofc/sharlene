package com.github.d3cryptofc.sharlene.cli;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.cli.provider.SharleneUsageVersionProvider;
import com.github.d3cryptofc.sharlene.cli.section.FileOptionsSection;
import com.github.d3cryptofc.sharlene.cli.section.GeneralOptionsSection;
import com.github.d3cryptofc.sharlene.cli.validator.InputFileValidator;
import com.github.d3cryptofc.sharlene.cli.validator.OutputFileValidator;
import com.github.d3cryptofc.sharlene.exception.UserAbortException;
import com.github.d3cryptofc.sharlene.utils.LogUtils;
import com.github.d3cryptofc.sharlene.utils.StdinUtils;
import java.io.File;
import java.util.concurrent.Callable;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

/**
 * The main CLI command.
 */
@Command(
   name = "sharlene",
   header = "Sharlene is a powerful patcher for Charles Proxy.%n",
   versionProvider = SharleneUsageVersionProvider.class
)
public class SharleneCommand implements Callable<Integer> {

   /**
    * The general options section.
    */
   @ArgGroup(heading = "%nGeneral Options:%n", exclusive = true)
   private GeneralOptionsSection generalOptionsSection;

   /**
    * The file options section.
    */
   @ArgGroup(
      heading = "%nFile Options:%n",
      exclusive = false,
      multiplicity = "1"
   )
   private FileOptionsSection fileOptionsSection;

   /**
    * Constructs a new {@code SharleneCommand} instance.
    */
   public SharleneCommand() {}

   /**
    * Called when command line parsing is complete.
    */
   @Override
   public Integer call() {
      // Log the operating system name.
      Main.LOGGER.info("OS: {}", Main.OSNAME);

      // Validate the input file and ensure it is the default Charles JAR file.
      File inputFile = InputFileValidator.orEnsureDefaultCharlesJarFile(
         this.fileOptionsSection.inputFile
      );
      // Get the output file.
      File outputFile = OutputFileValidator.orAddFileNameIfIsDirectory(
         this.fileOptionsSection.outputFile
      );
      // Get the overwrite flag.
      Boolean canOverwriteOutputFile =
         this.fileOptionsSection.canOverwriteOutputFile;

      // Log the input and output file paths.
      LogUtils.section(Main.LOGGER, () -> {
         Main.LOGGER.info("Input JAR: {}", inputFile.getPath());
         Main.LOGGER.info("Output JAR: {}", outputFile.getPath());
      });

      // Validate the input file is a Charles JAR file.
      InputFileValidator.checkIsCharlesJarFile(inputFile);

      // Check if the output file already exists and prompt the user to confirm overwriting if necessary.
      if (
         !canOverwriteOutputFile &&
         OutputFileValidator.checkFileAlreadyExists(outputFile) &&
         !Boolean.TRUE.equals(StdinUtils.confirm("Overwrite? [y/N]: "))
      ) {
         // Throw an exception if the user does not confirm overwriting.
         throw new UserAbortException();
      }

      // Return success exit code.
      return 0;
   }
}
