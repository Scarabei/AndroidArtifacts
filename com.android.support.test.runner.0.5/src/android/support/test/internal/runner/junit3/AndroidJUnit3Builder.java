package android.support.test.internal.runner.junit3;

import android.support.test.internal.util.AndroidRunnerBuilderUtil;
import android.support.test.internal.util.AndroidRunnerParams;
import android.util.Log;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.runner.Runner;

public class AndroidJUnit3Builder extends JUnit3Builder {
   private static final String LOG_TAG = "AndroidJUnit3Builder";
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidJUnit3Builder(AndroidRunnerParams runnerParams) {
      this.mAndroidRunnerParams = runnerParams;
   }

   public Runner runnerForClass(Class testClass) throws Throwable {
      try {
         if (AndroidRunnerBuilderUtil.isJUnit3Test(testClass)) {
            return this.mAndroidRunnerParams.isSkipExecution() ? new JUnit38ClassRunner(new NonExecutingTestSuite(testClass)) : new JUnit38ClassRunner(new AndroidTestSuite(testClass, this.mAndroidRunnerParams));
         } else {
            return null;
         }
      } catch (Throwable var3) {
         Log.e("AndroidJUnit3Builder", "Error constructing runner", var3);
         throw var3;
      }
   }
}
