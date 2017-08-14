package android.support.test.internal.runner.listener;

import android.util.Log;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class LogRunListener extends RunListener {
   private static final String TAG = "TestRunner";

   public void testRunStarted(Description description) throws Exception {
      Log.i("TestRunner", String.format("run started: %d tests", description.testCount()));
   }

   public void testRunFinished(Result result) throws Exception {
      Log.i("TestRunner", String.format("run finished: %d tests, %d failed, %d ignored", result.getRunCount(), result.getFailureCount(), result.getIgnoreCount()));
   }

   public void testStarted(Description description) throws Exception {
      Log.i("TestRunner", "started: " + description.getDisplayName());
   }

   public void testFinished(Description description) throws Exception {
      Log.i("TestRunner", "finished: " + description.getDisplayName());
   }

   public void testFailure(Failure failure) throws Exception {
      Log.i("TestRunner", "failed: " + failure.getDescription().getDisplayName());
      Log.i("TestRunner", "----- begin exception -----");
      Log.i("TestRunner", failure.getTrace());
      Log.i("TestRunner", "----- end exception -----");
   }

   public void testAssumptionFailure(Failure failure) {
      Log.i("TestRunner", "assumption failed: " + failure.getDescription().getDisplayName());
      Log.i("TestRunner", "----- begin exception -----");
      Log.i("TestRunner", failure.getTrace());
      Log.i("TestRunner", "----- end exception -----");
   }

   public void testIgnored(Description description) throws Exception {
      Log.i("TestRunner", "ignored: " + description.getDisplayName());
   }
}
