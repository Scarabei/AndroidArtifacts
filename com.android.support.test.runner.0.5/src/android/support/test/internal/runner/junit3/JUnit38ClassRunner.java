package android.support.test.internal.runner.junit3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import junit.extensions.TestDecorator;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.runner.Describable;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.manipulation.Sortable;
import org.junit.runner.manipulation.Sorter;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class JUnit38ClassRunner extends Runner implements Filterable, Sortable {
   private volatile Test fTest;

   public JUnit38ClassRunner(Class klass) {
      this((Test)(new TestSuite(klass.asSubclass(TestCase.class))));
   }

   public JUnit38ClassRunner(Test test) {
      this.setTest(test);
   }

   public void run(RunNotifier notifier) {
      TestResult result = new TestResult();
      result.addListener(this.createAdaptingListener(notifier));
      this.getTest().run(result);
   }

   public TestListener createAdaptingListener(RunNotifier notifier) {
      return new JUnit38ClassRunner.OldTestClassAdaptingListener(notifier);
   }

   public Description getDescription() {
      return makeDescription(this.getTest());
   }

   static Description makeDescription(Test test) {
      if (test instanceof TestCase) {
         TestCase tc = (TestCase)test;
         return Description.createTestDescription(tc.getClass(), tc.getName(), getAnnotations(tc));
      } else if (!(test instanceof TestSuite)) {
         if (test instanceof Describable) {
            Describable adapter = (Describable)test;
            return adapter.getDescription();
         } else if (test instanceof TestDecorator) {
            TestDecorator decorator = (TestDecorator)test;
            return makeDescription(decorator.getTest());
         } else {
            return Description.createSuiteDescription(test.getClass());
         }
      } else {
         TestSuite ts = (TestSuite)test;
         String name = ts.getName() == null ? createSuiteDescription(ts) : ts.getName();
         Description description = Description.createSuiteDescription(name, new Annotation[0]);
         int n = ts.testCount();

         for(int i = 0; i < n; ++i) {
            Description made = makeDescription(ts.testAt(i));
            description.addChild(made);
         }

         return description;
      }
   }

   private static Annotation[] getAnnotations(TestCase test) {
      try {
         Method m = test.getClass().getMethod(test.getName());
         return m.getDeclaredAnnotations();
      } catch (SecurityException var2) {
         ;
      } catch (NoSuchMethodException var3) {
         ;
      }

      return new Annotation[0];
   }

   private static String createSuiteDescription(TestSuite ts) {
      int count = ts.countTestCases();
      String example = count == 0 ? "" : String.format(" [example: %s]", ts.testAt(0));
      return String.format("TestSuite with %s tests%s", count, example);
   }

   public void filter(Filter filter) throws NoTestsRemainException {
      if (this.getTest() instanceof Filterable) {
         Filterable adapter = (Filterable)this.getTest();
         adapter.filter(filter);
      } else if (this.getTest() instanceof TestSuite) {
         TestSuite suite = (TestSuite)this.getTest();
         TestSuite filtered = new TestSuite(suite.getName());
         int n = suite.testCount();

         for(int i = 0; i < n; ++i) {
            Test test = suite.testAt(i);
            if (filter.shouldRun(makeDescription(test))) {
               filtered.addTest(test);
            }
         }

         this.setTest(filtered);
         if (filtered.testCount() == 0) {
            throw new NoTestsRemainException();
         }
      }

   }

   public void sort(Sorter sorter) {
      if (this.getTest() instanceof Sortable) {
         Sortable adapter = (Sortable)this.getTest();
         adapter.sort(sorter);
      }

   }

   private void setTest(Test test) {
      this.fTest = test;
   }

   private Test getTest() {
      return this.fTest;
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static final class OldTestClassAdaptingListener implements TestListener {
      private final RunNotifier fNotifier;
      private Test mCurrentTest;
      private Description mDescription;

      private OldTestClassAdaptingListener(RunNotifier notifier) {
         this.mCurrentTest = null;
         this.mDescription = null;
         this.fNotifier = notifier;
      }

      public void endTest(Test test) {
         this.fNotifier.fireTestFinished(this.asDescription(test));
      }

      public void startTest(Test test) {
         this.fNotifier.fireTestStarted(this.asDescription(test));
      }

      public void addError(Test test, Throwable t) {
         Failure failure = new Failure(this.asDescription(test), t);
         this.fNotifier.fireTestFailure(failure);
      }

      private Description asDescription(Test test) {
         if (this.mCurrentTest != null && this.mCurrentTest.equals(test) && this.mDescription != null) {
            return this.mDescription;
         } else {
            this.mCurrentTest = test;
            if (test instanceof Describable) {
               Describable facade = (Describable)test;
               this.mDescription = facade.getDescription();
            } else if (test instanceof TestCase) {
               this.mDescription = JUnit38ClassRunner.makeDescription(test);
            } else {
               this.mDescription = Description.createTestDescription(this.getEffectiveClass(test), test.toString());
            }

            return this.mDescription;
         }
      }

      private Class getEffectiveClass(Test test) {
         return test.getClass();
      }

      public void addFailure(Test test, AssertionFailedError t) {
         this.addError(test, t);
      }

      // $FF: synthetic method
      OldTestClassAdaptingListener(RunNotifier x0, JUnit38ClassRunner.SyntheticClass_1 x1) {
         this(x0);
      }
   }
}
