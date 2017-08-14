package android.support.test.internal.runner.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

class NonExecutingJUnit4ClassRunner extends BlockJUnit4ClassRunner {
   private static final Statement NON_EXECUTING_STATEMENT = new Statement() {
      public void evaluate() throws Throwable {
      }
   };

   public NonExecutingJUnit4ClassRunner(Class klass) throws InitializationError {
      super(klass);
   }

   protected Statement methodBlock(FrameworkMethod method) {
      return NON_EXECUTING_STATEMENT;
   }
}
