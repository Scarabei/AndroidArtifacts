package android.support.test.rule.logging;

import android.os.Trace;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.annotation.Beta;
import android.util.Log;
import java.io.File;
import java.io.IOException;

@Beta
public class LogLogcatRule extends LoggingBaseRule {
   private static final String TAG = "LogLogcatRule";
   private static final String DEFAULT_LOG_FILE_NAME = "logcat.log";

   public LogLogcatRule() {
   }

   public LogLogcatRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      super(logFileOutputDirectory, fileName);
   }

   private static void dumpLogcat(File logFile) {
      Process process = null;

      try {
         if (VERSION.SDK_INT >= 18) {
            Trace.beginSection("Recording logcat");
         }

         process = RuleLoggingUtils.startProcess(new String[]{"logcat", "-d", "-f", logFile.getAbsolutePath()});
         process.waitFor();
      } catch (InterruptedException | IOException var6) {
         RuleLoggingUtils.writeErrorToFileAndLogcat(logFile, "LogLogcatRule", "Error recording logcat output.", var6);
      } finally {
         if (process != null) {
            process.destroy();
         }

         if (VERSION.SDK_INT >= 18) {
            Trace.endSection();
         }

      }

   }

   public static void clearLogcat() {
      Process process = null;

      try {
         process = RuleLoggingUtils.startProcess(new String[]{"logcat", "-c"});
         process.waitFor();
         if (process.exitValue() != 0) {
            throw new IllegalStateException("Error while clearing logcat, exitValue=" + process.exitValue());
         }
      } catch (IOException | InterruptedException | IllegalStateException var5) {
         Log.e("LogLogcatRule", "Exception clearing logcat.", var5);
      } finally {
         if (process != null) {
            process.destroy();
         }

      }

      Log.i("LogLogcatRule", "Logcat cleared by test rule");
   }

   public void afterTest() {
      this.dumpLogcat(this.getLogFileName());
   }

   public void beforeTest() {
      clearLogcat();
   }

   public File dumpLogcat(String logFileName) {
      File logFile;
      if (null == this.getLogFileOutputDirectory()) {
         logFile = RuleLoggingUtils.getTestFile(this.getTestClass(), this.getTestName(), logFileName, this.getTestRunNumber());
      } else {
         logFile = new File(this.getLogFileOutputDirectory(), logFileName);
      }

      dumpLogcat(logFile);
      return logFile;
   }

   public String getDefaultLogFileName() {
      return "logcat.log";
   }
}
