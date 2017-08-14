package android.support.test.internal.runner.listener;

import android.app.Instrumentation;
import android.support.test.internal.util.Checks;
import android.support.test.runner.MonitoringInstrumentation;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class ActivityFinisherRunListener extends RunListener {
   private static final String TAG = "ActivityFinisherRunListener";
   private final Instrumentation mInstrumentation;
   private final MonitoringInstrumentation.ActivityFinisher mActivityFinisher;

   public ActivityFinisherRunListener(Instrumentation instrumentation, MonitoringInstrumentation.ActivityFinisher finisher) {
      this.mInstrumentation = (Instrumentation)Checks.checkNotNull(instrumentation);
      this.mActivityFinisher = (MonitoringInstrumentation.ActivityFinisher)Checks.checkNotNull(finisher);
   }

   public void testStarted(Description description) throws Exception {
      this.mInstrumentation.runOnMainSync(this.mActivityFinisher);
   }

   public void testFinished(Description description) throws Exception {
      this.mInstrumentation.runOnMainSync(this.mActivityFinisher);
   }
}
