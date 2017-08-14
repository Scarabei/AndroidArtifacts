package android.support.test.rule.logging;

import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.annotation.Beta;
import android.util.Log;
import java.io.File;
import java.io.IOException;

@Beta
public class LogNetStatsRule extends LoggingBaseRule {
   private static final String TAG = "LogNetStatsRule";

   public LogNetStatsRule() {
   }

   public LogNetStatsRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      super(logFileOutputDirectory, fileName);
   }

   String getDefaultLogFileName() {
      return "netstats.dumpsys.log";
   }

   public void beforeTest() {
      Process process = null;

      try {
         process = RuleLoggingUtils.startProcess(new String[]{"dumpsys", "netstats", "--reset", this.getPackageNameUnderTest()});
         process.waitFor();
      } catch (IOException | InterruptedException var6) {
         Log.e("LogNetStatsRule", "Unable to reset netstats", var6);
      } finally {
         if (process != null) {
            process.destroy();
         }

      }

   }

   public void afterTest() {
      String[] commandParts = new String[]{"dumpsys", "netstats"};

      try {
         Trace.beginSection("Netstats dumpsys");
         RuleLoggingUtils.startProcessAndLogToFile(commandParts, this.getLogFile(), this.getAndroidRuntimeVersion());
      } finally {
         Trace.endSection();
      }

   }
}
