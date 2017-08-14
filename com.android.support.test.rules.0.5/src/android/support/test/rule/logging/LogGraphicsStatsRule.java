package android.support.test.rule.logging;

import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.annotation.Beta;
import android.util.Log;
import java.io.File;
import java.io.IOException;

@Beta
public class LogGraphicsStatsRule extends LoggingBaseRule {
   private static final String TAG = "LogGraphicsStatsRule";
   private static final String DEFAULT_LOG_FILE_NAME = "gfxinfo.dumpsys.log";

   public LogGraphicsStatsRule() {
   }

   public LogGraphicsStatsRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      super(logFileOutputDirectory, fileName);
   }

   public void afterTest() {
      String[] commandParts;
      if (this.getAndroidRuntimeVersion() >= 23) {
         commandParts = new String[]{"dumpsys", "gfxinfo", this.getPackageNameUnderTest(), "framestats"};
      } else {
         commandParts = new String[]{"dumpsys", "gfxinfo", this.getPackageNameUnderTest()};
      }

      try {
         Trace.beginSection("Gfxinfo dumpsys");
         RuleLoggingUtils.startProcessAndLogToFile(commandParts, this.getLogFile(), this.getAndroidRuntimeVersion());
      } finally {
         Trace.endSection();
      }

   }

   public void beforeTest() {
      Process process = null;

      try {
         process = RuleLoggingUtils.startProcess(new String[]{"dumpsys", "gfxinfo", "--reset", this.getPackageNameUnderTest()});
         process.waitFor();
      } catch (IOException | InterruptedException var6) {
         Log.e("LogGraphicsStatsRule", "Unable to reset reset gfxinfo", var6);
      } finally {
         if (process != null) {
            process.destroy();
         }

      }

   }

   String getDefaultLogFileName() {
      return "gfxinfo.dumpsys.log";
   }
}
