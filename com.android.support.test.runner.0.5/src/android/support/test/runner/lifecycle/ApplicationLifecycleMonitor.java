package android.support.test.runner.lifecycle;

public interface ApplicationLifecycleMonitor {
   void addLifecycleCallback(ApplicationLifecycleCallback var1);

   void removeLifecycleCallback(ApplicationLifecycleCallback var1);
}
