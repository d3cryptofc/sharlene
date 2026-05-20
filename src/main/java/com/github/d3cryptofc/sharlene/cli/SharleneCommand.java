package com.github.d3cryptofc.sharlene.cli;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.cli.provider.SharleneUsageVersionProvider;
import com.github.d3cryptofc.sharlene.cli.section.FileOptionsSection;
import com.github.d3cryptofc.sharlene.cli.section.GeneralOptionsSection;
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
    * Called when command line parsing is complete.
    */
   @Override
   public Integer call() {
      // Log the operating system name.
      Main.LOGGER.info("OS: {}", Main.OSNAME);

      return 0;
   }
}
