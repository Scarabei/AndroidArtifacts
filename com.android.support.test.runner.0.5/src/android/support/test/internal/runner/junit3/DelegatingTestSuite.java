package android.support.test.internal.runner.junit3;

import java.util.Enumeration;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Ignore;

@Ignore
class DelegatingTestSuite extends TestSuite {
   private TestSuite mWrappedSuite;

   public DelegatingTestSuite(TestSuite suiteDelegate) {
      this.mWrappedSuite = suiteDelegate;
   }

   public TestSuite getDelegateSuite() {
      return this.mWrappedSuite;
   }

   public void setDelegateSuite(TestSuite newSuiteDelegate) {
      this.mWrappedSuite = newSuiteDelegate;
   }

   public void addTest(Test test) {
      this.mWrappedSuite.addTest(test);
   }

   public int countTestCases() {
      return this.mWrappedSuite.countTestCases();
   }

   public String getName() {
      return this.mWrappedSuite.getName();
   }

   public void runTest(Test test, TestResult result) {
      this.mWrappedSuite.runTest(test, result);
   }

   public void setName(String name) {
      this.mWrappedSuite.setName(name);
   }

   public Test testAt(int index) {
      return this.mWrappedSuite.testAt(index);
   }

   public int testCount() {
      return this.mWrappedSuite.testCount();
   }

   public Enumeration tests() {
      return this.mWrappedSuite.tests();
   }

   public String toString() {
      return this.mWrappedSuite.toString();
   }

   public void run(TestResult result) {
      this.mWrappedSuite.run(result);
   }
}
