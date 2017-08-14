package com.google.android.gms.analytics;

/** @deprecated */
@Deprecated
public interface Logger {
   /** @deprecated */
   @Deprecated
   void verbose(String var1);

   /** @deprecated */
   @Deprecated
   void info(String var1);

   /** @deprecated */
   @Deprecated
   void warn(String var1);

   /** @deprecated */
   @Deprecated
   void error(String var1);

   /** @deprecated */
   @Deprecated
   void error(Exception var1);

   /** @deprecated */
   @Deprecated
   void setLogLevel(int var1);

   /** @deprecated */
   @Deprecated
   int getLogLevel();

   /** @deprecated */
   @Deprecated
   public static class LogLevel {
      public static final int VERBOSE = 0;
      public static final int INFO = 1;
      public static final int WARNING = 2;
      public static final int ERROR = 3;
   }
}
