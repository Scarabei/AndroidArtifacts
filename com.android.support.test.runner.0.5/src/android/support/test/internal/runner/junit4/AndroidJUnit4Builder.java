package android.support.test.internal.runner.junit4;

import android.support.test.internal.util.AndroidRunnerParams;
import android.util.Log;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;

public class AndroidJUnit4Builder extends JUnit4Builder {
   private static final String LOG_TAG = "AndroidJUnit4Builder";
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidJUnit4Builder(AndroidRunnerParams runnerParams) {
      this.mAndroidRunnerParams = runnerParams;
   }

   public Runner runnerForClass(Class testClass) throws Throwable {
      try {
         return (Runner)(this.mAndroidRunnerParams.isSkipExecution() ? new NonExecutingJUnit4ClassRunner(testClass) : new AndroidJUnit4ClassRunner(testClass, this.mAndroidRunnerParams));
      } catch (Throwable var3) {
         Log.e("AndroidJUnit4Builder", "Error constructing runner", var3);
         throw var3;
      }
   }
}
