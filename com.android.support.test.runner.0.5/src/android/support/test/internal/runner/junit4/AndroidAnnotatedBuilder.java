package android.support.test.internal.runner.junit4;

import android.support.test.internal.runner.junit3.JUnit38ClassRunner;
import android.support.test.internal.runner.junit3.NonExecutingTestSuite;
import android.support.test.internal.util.AndroidRunnerBuilderUtil;
import android.support.test.internal.util.AndroidRunnerParams;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

public class AndroidAnnotatedBuilder extends AnnotatedBuilder {
   private static final String LOG_TAG = "AndroidAnnotatedBuilder";
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidAnnotatedBuilder(RunnerBuilder suiteBuilder, AndroidRunnerParams runnerParams) {
      super(suiteBuilder);
      this.mAndroidRunnerParams = runnerParams;
   }

   public Runner runnerForClass(Class testClass) throws Exception {
      try {
         if (this.mAndroidRunnerParams.isSkipExecution()) {
            if (AndroidRunnerBuilderUtil.isJUnit3Test(testClass)) {
               return new JUnit38ClassRunner(new NonExecutingTestSuite(testClass));
            }

            return new NonExecutingJUnit4ClassRunner(testClass);
         }

         RunWith annotation = (RunWith)testClass.getAnnotation(RunWith.class);
         if (annotation != null && annotation.value().equals(AndroidJUnit4.class)) {
            Class runnerClass = annotation.value();

            try {
               Runner runner = this.buildAndroidRunner(runnerClass, testClass);
               if (runner != null) {
                  return runner;
               }
            } catch (NoSuchMethodException var5) {
               return super.buildRunner(runnerClass, testClass);
            }
         }
      } catch (Throwable var6) {
         Log.e("AndroidAnnotatedBuilder", "Error constructing runner", var6);
         throw var6;
      }

      return super.runnerForClass(testClass);
   }

   public Runner buildAndroidRunner(Class runnerClass, Class testClass) throws Exception {
      return (Runner)runnerClass.getConstructor(Class.class, AndroidRunnerParams.class).newInstance(testClass, this.mAndroidRunnerParams);
   }
}
