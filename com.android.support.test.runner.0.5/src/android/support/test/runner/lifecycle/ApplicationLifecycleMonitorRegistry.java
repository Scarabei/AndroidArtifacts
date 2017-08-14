package android.support.test.runner.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

public final class ApplicationLifecycleMonitorRegistry {
   private static final AtomicReference sLifecycleMonitor = new AtomicReference((Object)null);

   public static ApplicationLifecycleMonitor getInstance() {
      ApplicationLifecycleMonitor instance = (ApplicationLifecycleMonitor)sLifecycleMonitor.get();
      if (null == instance) {
         throw new IllegalStateException("No lifecycle monitor registered! Are you running under an Instrumentation which registers lifecycle monitors?");
      } else {
         return instance;
      }
   }

   public static void registerInstance(ApplicationLifecycleMonitor monitor) {
      sLifecycleMonitor.set(monitor);
   }
}
