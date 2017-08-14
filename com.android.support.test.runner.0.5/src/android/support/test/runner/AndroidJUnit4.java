package android.support.test.runner;

import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.internal.util.AndroidRunnerParams;
import org.junit.runners.model.InitializationError;

public final class AndroidJUnit4 extends AndroidJUnit4ClassRunner {
   public AndroidJUnit4(Class klass, AndroidRunnerParams runnerParams) throws InitializationError {
      super(klass, runnerParams);
   }
}
