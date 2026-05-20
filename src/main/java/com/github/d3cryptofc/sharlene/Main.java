package com.github.d3cryptofc.sharlene;

import com.github.d3cryptofc.sharlene.cli.SharleneCommand;
import com.github.d3cryptofc.sharlene.exception.SharleneRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.IExecutionExceptionHandler;

/**
 * The main program class.
 */
public class Main {

   /**
    * Global object logger.
    */
   public static final Logger LOGGER = LogManager.getLogger(Main.class);

   /**
    * Operating system name.
    */
   public static final String OSNAME = System.getProperty("os.name");

   /**
    * Program entrypoint that executes the CLI.
    *
    * @param args  Command line arguments.
    */
   public static void main(String[] args) {
      // Execute CLI and get exit code.
      int exitcode = createCommandLine().execute(args);
      // Exit program with the exit code.
      System.exit(exitcode);
   }

   /**
    * Creates a configured {@code CommandLine} instance.
    */
   private static CommandLine createCommandLine() {
      // Create color scheme to the CLI help usage.
      ColorScheme colorScheme = createColorScheme();
      // Create exception handler to handle exceptions.
      IExecutionExceptionHandler exceptionHandler = createExceptionHandler();

      // Create the CommandLine object.
      return new CommandLine(new SharleneCommand())
         // Set the CLI help usage color scheme
         .setColorScheme(colorScheme)
         // Set the CLI exception handler.
         .setExecutionExceptionHandler(exceptionHandler);
   }

   /**
    * Creates the color scheme to the CLI help usage.
    */
   private static ColorScheme createColorScheme() {
      // Build a color scheme to the CLI help usage.
      return new ColorScheme.Builder()
         .commands(Style.bold)
         .options(Style.bold, Style.fg_magenta)
         .optionParams(Style.italic)
         .errors(Style.bold, Style.fg_red)
         .build();
   }

   /**
    * Creates the exception handler insttance to the CLI.
    */
   private static IExecutionExceptionHandler createExceptionHandler() {
      return (e, c, p) -> {
         // Always displays the exception message.
         LOGGER.error(e.getMessage());

         // When is a unexpected exception.
         if (!(e instanceof SharleneRuntimeException)) {
            // Display the stack trace.
            e.printStackTrace();
         }

         return 1;
      };
   }
}
