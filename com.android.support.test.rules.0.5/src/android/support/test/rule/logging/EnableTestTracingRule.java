package android.support.test.rule.logging;

import android.os.Trace;
import android.support.test.annotation.Beta;

@Beta
public class EnableTestTracingRule extends LoggingBaseRule {
   boolean mTraceActive = false;

   public void afterTest() {
      if (this.getAndroidRuntimeVersion() >= 16) {
         this.mTraceActive = false;
         Trace.endSection();
      }

   }

   public void beforeTest() {
      if (this.getAndroidRuntimeVersion() >= 16) {
         this.mTraceActive = true;
         Trace.beginSection(this.getTestName());
      }

   }

   String getDefaultLogFileName() {
      return null;
   }
}
