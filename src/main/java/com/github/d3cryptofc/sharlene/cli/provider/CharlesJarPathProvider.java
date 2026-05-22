package com.github.d3cryptofc.sharlene.cli.provider;

import com.github.d3cryptofc.sharlene.Main;
import com.github.d3cryptofc.sharlene.exception.UnsupportedOSException;

/**
 * Provides the default charles JAR file path for the current operating system.
 */
public class CharlesJarPathProvider {

   /**
    * Private constructor to prevent instantiation.
    */
   private CharlesJarPathProvider() {}

   /**
    * Gets the default charles JAR file path for the current operating system.
    *
    * @return The default charles JAR file path for the current operating system.
    *
    * @throws UnsupportedOSException   If the current operating system is not supported
    *                                  or cannot be detected.
    */
   public static String getDefault() throws UnsupportedOSException {
      // Get the operating system name in lowercase.
      String platform = Main.OSNAME.toLowerCase();

      // Check OS is Linux.
      if (platform.startsWith("linux")) {
         return "/usr/share/charles-proxy/lib/charles.jar";
      }

      // If no return, throw unsupported OS.
      throw new UnsupportedOSException();
   }
}
