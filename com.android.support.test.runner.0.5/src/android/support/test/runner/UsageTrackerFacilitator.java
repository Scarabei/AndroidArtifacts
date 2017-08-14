package android.support.test.runner;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.internal.runner.RunnerArgs;
import android.support.test.internal.runner.tracker.UsageTracker;
import android.support.test.internal.runner.tracker.UsageTrackerRegistry;
import android.support.test.internal.util.Checks;
import android.util.Log;

public class UsageTrackerFacilitator implements UsageTracker {
   private static final String TAG = "UsageTrackerFacilitator";
   private final RunnerArgs mRunnerArgs;

   public UsageTrackerFacilitator(@NonNull RunnerArgs runnerArgs) {
      this.mRunnerArgs = (RunnerArgs)Checks.checkNotNull(runnerArgs, "runnerArgs cannot be null!");
   }

   public boolean shouldTrackUsage() {
      return !this.mRunnerArgs.disableAnalytics;
   }

   public void registerUsageTracker(@Nullable UsageTracker usageTracker) {
      if (usageTracker != null && this.shouldTrackUsage()) {
         Log.i("UsageTrackerFacilitator", "Usage tracking enabled");
         UsageTrackerRegistry.registerInstance(usageTracker);
      } else {
         Log.i("UsageTrackerFacilitator", "Usage tracking disabled");
         UsageTrackerRegistry.registerInstance(new UsageTracker.NoOpUsageTracker());
      }
   }

   public void trackUsage(String usage) {
      if (this.shouldTrackUsage()) {
         UsageTrackerRegistry.getInstance().trackUsage(usage);
      }

   }

   public void sendUsages() {
      if (this.shouldTrackUsage()) {
         UsageTrackerRegistry.getInstance().sendUsages();
      }

   }
}
