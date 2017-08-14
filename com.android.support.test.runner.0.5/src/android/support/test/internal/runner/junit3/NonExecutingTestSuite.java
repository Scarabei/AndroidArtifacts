package android.support.test.internal.runner.junit3;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Ignore;

@Ignore
public class NonExecutingTestSuite extends DelegatingFilterableTestSuite {
   public NonExecutingTestSuite(Class testClass) {
      this(new TestSuite(testClass));
   }

   public NonExecutingTestSuite(TestSuite s) {
      super(s);
   }

   public void run(TestResult result) {
      super.run(new NonExecutingTestResult(result));
   }
}
