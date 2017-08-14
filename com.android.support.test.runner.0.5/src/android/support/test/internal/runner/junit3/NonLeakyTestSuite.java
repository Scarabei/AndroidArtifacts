package android.support.test.internal.runner.junit3;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Ignore;
import org.junit.runner.Describable;
import org.junit.runner.Description;

@Ignore
public class NonLeakyTestSuite extends TestSuite {
   public NonLeakyTestSuite(Class testClass) {
      super(testClass);
   }

   public void addTest(Test test) {
      super.addTest(new NonLeakyTestSuite.NonLeakyTest(test));
   }

   private static class NonLeakyTest implements Test, Describable {
      private Test mDelegate;
      private final Description mDesc;

      NonLeakyTest(Test delegate) {
         this.mDelegate = delegate;
         this.mDesc = JUnit38ClassRunner.makeDescription(this.mDelegate);
      }

      public int countTestCases() {
         return this.mDelegate != null ? this.mDelegate.countTestCases() : 0;
      }

      public void run(TestResult result) {
         this.mDelegate.run(result);
         this.mDelegate = null;
      }

      public Description getDescription() {
         return this.mDesc;
      }

      public String toString() {
         return this.mDelegate != null ? this.mDelegate.toString() : this.mDesc.toString();
      }
   }
}
