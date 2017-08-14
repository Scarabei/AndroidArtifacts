package android.support.test.internal.runner.junit4;

import android.support.test.internal.util.AndroidRunnerParams;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class AndroidJUnit4ClassRunner extends BlockJUnit4ClassRunner {
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidJUnit4ClassRunner(Class klass, AndroidRunnerParams runnerParams) throws InitializationError {
      super(klass);
      this.mAndroidRunnerParams = runnerParams;
   }

   protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next) {
      long timeout = this.getTimeout((Test)method.getAnnotation(Test.class));
      if (timeout > 0L) {
         return new FailOnTimeout(next, timeout);
      } else {
         return (Statement)(this.mAndroidRunnerParams.getPerTestTimeout() > 0L ? new FailOnTimeout(next, this.mAndroidRunnerParams.getPerTestTimeout()) : next);
      }
   }

   private long getTimeout(Test annotation) {
      return annotation == null ? 0L : annotation.timeout();
   }
}
