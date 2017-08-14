package android.support.test.rule.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.annotation.Beta;
import java.io.File;

@Beta
public class LogDeviceGetPropInfoRule extends LoggingBaseRule {
   private static final String DEFAULT_LOG_FILE_NAME = "deviceprops.log";

   public LogDeviceGetPropInfoRule() {
   }

   public LogDeviceGetPropInfoRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      super(logFileOutputDirectory, fileName);
   }

   public void afterTest() {
      String[] commandParts = new String[]{"getprop"};
      RuleLoggingUtils.startCmdAndLogOutputPostL(commandParts, this.getLogFile());
   }

   public void beforeTest() {
   }

   String getDefaultLogFileName() {
      return "deviceprops.log";
   }
}
