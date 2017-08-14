package android.support.test.internal.runner.tracker;

public interface UsageTracker {
   void trackUsage(String var1);

   void sendUsages();

   public static class NoOpUsageTracker implements UsageTracker {
      public void trackUsage(String unused) {
      }

      public void sendUsages() {
      }
   }
}
