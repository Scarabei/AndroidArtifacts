package android.support.test.internal.runner.junit3;

import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestListener;
import junit.framework.TestResult;

class DelegatingTestResult extends TestResult {
   private TestResult mWrappedResult;

   DelegatingTestResult(TestResult wrappedResult) {
      this.mWrappedResult = wrappedResult;
   }

   public void addError(Test test, Throwable t) {
      this.mWrappedResult.addError(test, t);
   }

   public void addFailure(Test test, AssertionFailedError t) {
      this.mWrappedResult.addFailure(test, t);
   }

   public void addListener(TestListener listener) {
      this.mWrappedResult.addListener(listener);
   }

   public void removeListener(TestListener listener) {
      this.mWrappedResult.removeListener(listener);
   }

   public void endTest(Test test) {
      this.mWrappedResult.endTest(test);
   }

   public int errorCount() {
      return this.mWrappedResult.errorCount();
   }

   public Enumeration errors() {
      return this.mWrappedResult.errors();
   }

   public int failureCount() {
      return this.mWrappedResult.failureCount();
   }

   public Enumeration failures() {
      return this.mWrappedResult.failures();
   }

   public int runCount() {
      return this.mWrappedResult.runCount();
   }

   public void runProtected(Test test, Protectable p) {
      this.mWrappedResult.runProtected(test, p);
   }

   public boolean shouldStop() {
      return this.mWrappedResult.shouldStop();
   }

   public void startTest(Test test) {
      this.mWrappedResult.startTest(test);
   }

   public void stop() {
      this.mWrappedResult.stop();
   }

   public boolean wasSuccessful() {
      return this.mWrappedResult.wasSuccessful();
   }
}
