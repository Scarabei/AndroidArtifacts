package android.support.test.internal.runner.junit3;

import junit.framework.TestCase;
import junit.framework.TestResult;

class NonExecutingTestResult extends DelegatingTestResult {
   NonExecutingTestResult(TestResult wrappedResult) {
      super(wrappedResult);
   }

   protected void run(TestCase test) {
      this.startTest(test);
      this.endTest(test);
   }
}
