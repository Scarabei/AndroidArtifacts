package android.support.test.internal.runner.junit3;

import android.app.Instrumentation;
import android.os.Bundle;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import java.util.concurrent.TimeoutException;
import junit.framework.AssertionFailedError;
import junit.framework.Protectable;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

class AndroidTestResult extends DelegatingTestResult {
   private final Instrumentation mInstr;
   private final Bundle mBundle;
   private long mTimeout;

   AndroidTestResult(Bundle bundle, Instrumentation instr, TestResult result) {
      super(result);
      this.mBundle = bundle;
      this.mInstr = instr;
   }

   protected void run(TestCase test) {
      if (test instanceof AndroidTestCase) {
         ((AndroidTestCase)test).setContext(this.mInstr.getTargetContext());
      }

      if (test instanceof InstrumentationTestCase) {
         ((InstrumentationTestCase)test).injectInstrumentation(this.mInstr);
      }

      super.run(test);
   }

   void setCurrentTimeout(long timeout) {
      this.mTimeout = timeout;
   }

   public void runProtected(Test test, Protectable p) {
      try {
         p.protect();
      } catch (AssertionFailedError var4) {
         super.addFailure(test, var4);
      } catch (ThreadDeath var5) {
         throw var5;
      } catch (InterruptedException var6) {
         super.addError(test, new TimeoutException(String.format("Test timed out after %d milliseconds", this.mTimeout)));
      } catch (Throwable var7) {
         super.addError(test, var7);
      }

   }
}
