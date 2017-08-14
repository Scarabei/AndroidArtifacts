package android.support.test.runner.intent;

import java.util.concurrent.atomic.AtomicReference;

public final class IntentMonitorRegistry {
   private static final AtomicReference mMonitorRef = new AtomicReference((Object)null);

   public static IntentMonitor getInstance() {
      IntentMonitor instance = (IntentMonitor)mMonitorRef.get();
      if (null == instance) {
         throw new IllegalStateException("No intent monitor registered! Are you running under an Instrumentation which registers intent monitors?");
      } else {
         return instance;
      }
   }

   public static void registerInstance(IntentMonitor monitor) {
      mMonitorRef.set(monitor);
   }
}
