package android.support.test.rule.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.annotation.Beta;
import android.util.Log;
import java.io.File;

@Beta
public class LogBatteryInformationRule extends LoggingBaseRule {
   private static final String TAG = "LogBatteryInformationRule";
   private static final String DEFAULT_LOG_FILE_NAME = "battery.dumpsys.log";

   public LogBatteryInformationRule() {
   }

   public LogBatteryInformationRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      super(logFileOutputDirectory, fileName);
   }

   public void afterTest() {
      if (this.getAndroidRuntimeVersion() >= 21) {
         try {
            RuleLoggingUtils.startProcessAndLogToFile(new String[]{"dumpsys", "batterystats"}, this.getLogFile(), this.getAndroidRuntimeVersion());
         } catch (Exception var2) {
            Log.e("LogBatteryInformationRule", "Unable to reset dumpsys", var2);
         }
      } else {
         Log.w("LogBatteryInformationRule", "Batterystats dumpsys not supported on API versions less than 21");
      }

   }

   public void beforeTest() {
      if (this.getAndroidRuntimeVersion() >= 21) {
         Process process = null;

         try {
            process = RuleLoggingUtils.startProcess(new String[]{"dumpsys", "batterystats", "--reset"});
            process.waitFor();
         } catch (Exception var6) {
            Log.e("LogBatteryInformationRule", "Unable to reset dumpsys", var6);
         } finally {
            if (process != null) {
               process.destroy();
            }

         }
      } else {
         Log.i("LogBatteryInformationRule", "Batterystats dumpsys not supported on API versions less than 21");
      }

   }

   public String getDefaultLogFileName() {
      return "battery.dumpsys.log";
   }
}
