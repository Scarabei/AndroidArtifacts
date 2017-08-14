package android.support.test.rule;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.internal.util.Checks;
import android.util.Log;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ActivityTestRule extends UiThreadTestRule {
   private static final String TAG = "ActivityTestRule";
   private final Class mActivityClass;
   private Instrumentation mInstrumentation;
   private boolean mInitialTouchMode;
   private boolean mLaunchActivity;
   private Activity mActivity;

   public ActivityTestRule(Class activityClass) {
      this(activityClass, false);
   }

   public ActivityTestRule(Class activityClass, boolean initialTouchMode) {
      this(activityClass, initialTouchMode, true);
   }

   public ActivityTestRule(Class activityClass, boolean initialTouchMode, boolean launchActivity) {
      this.mInitialTouchMode = false;
      this.mLaunchActivity = false;
      this.mActivityClass = activityClass;
      this.mInitialTouchMode = initialTouchMode;
      this.mLaunchActivity = launchActivity;
      this.mInstrumentation = InstrumentationRegistry.getInstrumentation();
   }

   protected Intent getActivityIntent() {
      return new Intent("android.intent.action.MAIN");
   }

   protected void beforeActivityLaunched() {
   }

   protected void afterActivityLaunched() {
   }

   protected void afterActivityFinished() {
   }

   public Activity getActivity() {
      if (this.mActivity == null) {
         Log.w("ActivityTestRule", "Activity wasn't created yet");
      }

      return this.mActivity;
   }

   public Statement apply(Statement base, Description description) {
      return new ActivityTestRule.ActivityStatement(super.apply(base, description));
   }

   public Activity launchActivity(@Nullable Intent startIntent) {
      this.mInstrumentation.setInTouchMode(this.mInitialTouchMode);
      String targetPackage = this.mInstrumentation.getTargetContext().getPackageName();
      if (null == startIntent) {
         startIntent = this.getActivityIntent();
         if (null == startIntent) {
            Log.w("ActivityTestRule", "getActivityIntent() returned null using default: Intent(Intent.ACTION_MAIN)");
            startIntent = new Intent("android.intent.action.MAIN");
         }
      }

      startIntent.setClassName(targetPackage, this.mActivityClass.getName());
      startIntent.addFlags(268435456);
      Log.d("ActivityTestRule", String.format("Launching activity %s", this.mActivityClass.getName()));
      this.beforeActivityLaunched();
      this.mActivity = (Activity)this.mActivityClass.cast(this.mInstrumentation.startActivitySync(startIntent));
      this.mInstrumentation.waitForIdleSync();
      if (this.mActivity != null) {
         this.afterActivityLaunched();
      } else {
         String errorMessage = String.format("Activity %s, failed to launch", this.mActivityClass.getName());
         Bundle bundle = new Bundle();
         bundle.putString("stream", "ActivityTestRule " + errorMessage);
         this.mInstrumentation.sendStatus(0, bundle);
         Log.e("ActivityTestRule", errorMessage);
      }

      return this.mActivity;
   }

   void setInstrumentation(Instrumentation instrumentation) {
      this.mInstrumentation = (Instrumentation)Checks.checkNotNull(instrumentation, "instrumentation cannot be null!");
   }

   void finishActivity() {
      if (this.mActivity != null) {
         this.mActivity.finish();
         this.afterActivityFinished();
         this.mActivity = null;
      }

   }

   private class ActivityStatement extends Statement {
      private final Statement mBase;

      public ActivityStatement(Statement base) {
         this.mBase = base;
      }

      public void evaluate() throws Throwable {
         try {
            if (ActivityTestRule.this.mLaunchActivity) {
               ActivityTestRule.this.mActivity = ActivityTestRule.this.launchActivity(ActivityTestRule.this.getActivityIntent());
            }

            this.mBase.evaluate();
         } finally {
            ActivityTestRule.this.finishActivity();
         }

      }
   }
}
