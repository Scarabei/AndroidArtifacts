package android.support.test.internal.runner.junit3;

import android.support.test.internal.util.AndroidRunnerParams;
import android.util.Log;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.internal.builders.SuiteMethodBuilder;
import org.junit.internal.runners.SuiteMethod;
import org.junit.runner.Runner;

public class AndroidSuiteBuilder extends SuiteMethodBuilder {
   private static final String LOG_TAG = "AndroidSuiteBuilder";
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidSuiteBuilder(AndroidRunnerParams runnerParams) {
      this.mAndroidRunnerParams = runnerParams;
   }

   public Runner runnerForClass(Class testClass) throws Throwable {
      if (this.mAndroidRunnerParams.isIgnoreSuiteMethods()) {
         return null;
      } else {
         try {
            if (this.hasSuiteMethod(testClass)) {
               Test t = SuiteMethod.testFromSuiteMethod(testClass);
               if (!(t instanceof TestSuite)) {
                  throw new IllegalArgumentException(testClass.getName() + "#suite() did not return a TestSuite");
               } else {
                  return this.mAndroidRunnerParams.isSkipExecution() ? new JUnit38ClassRunner(new NonExecutingTestSuite((TestSuite)t)) : new JUnit38ClassRunner(new AndroidTestSuite((TestSuite)t, this.mAndroidRunnerParams));
               }
            } else {
               return null;
            }
         } catch (Throwable var3) {
            Log.e("AndroidSuiteBuilder", "Error constructing runner", var3);
            throw var3;
         }
      }
   }
}
