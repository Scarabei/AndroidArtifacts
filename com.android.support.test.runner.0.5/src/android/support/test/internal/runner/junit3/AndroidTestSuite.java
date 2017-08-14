package android.support.test.internal.runner.junit3;

import android.os.Looper;
import android.support.test.internal.util.AndroidRunnerParams;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import org.junit.Ignore;

@Ignore
class AndroidTestSuite extends DelegatingFilterableTestSuite {
   private static final String TAG = "AndroidTestSuite";
   private final AndroidRunnerParams mAndroidRunnerParams;

   public AndroidTestSuite(Class testClass, AndroidRunnerParams runnerParams) {
      this((TestSuite)(new NonLeakyTestSuite(testClass)), runnerParams);
   }

   public AndroidTestSuite(TestSuite s, AndroidRunnerParams runnerParams) {
      super(s);
      this.mAndroidRunnerParams = runnerParams;
   }

   public void run(TestResult result) {
      AndroidTestResult androidTestResult = new AndroidTestResult(this.mAndroidRunnerParams.getBundle(), this.mAndroidRunnerParams.getInstrumentation(), result);
      long timeout = this.mAndroidRunnerParams.getPerTestTimeout();
      if (timeout > 0L) {
         this.runTestsWithTimeout(timeout, androidTestResult);
      } else {
         super.run(androidTestResult);
      }

   }

   private void runTestsWithTimeout(long timeout, AndroidTestResult result) {
      int suiteSize = this.testCount();

      for(int i = 0; i < suiteSize; ++i) {
         Test test = this.testAt(i);
         this.runTestWithTimeout(test, result, timeout);
      }

   }

   private void runTestWithTimeout(final Test test, final AndroidTestResult androidTestResult, long timeout) {
      ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
      Runnable execRunnable = new Runnable() {
         public void run() {
            test.run(androidTestResult);
         }
      };
      androidTestResult.setCurrentTimeout(timeout);
      Future result = threadExecutor.submit(execRunnable);
      threadExecutor.shutdown();

      try {
         boolean isTerminated = threadExecutor.awaitTermination(timeout, TimeUnit.MILLISECONDS);
         if (!isTerminated) {
            threadExecutor.shutdownNow();
            isTerminated = threadExecutor.awaitTermination(1L, TimeUnit.MINUTES);
            if (!isTerminated) {
               Log.e("AndroidTestSuite", "Failed to to stop test execution thread, the correctness of the test runner is at risk. Abort all execution!");

               try {
                  result.get(0L, TimeUnit.MILLISECONDS);
               } catch (ExecutionException var10) {
                  Log.e("AndroidTestSuite", "Exception from the execution thread", var10.getCause());
               } catch (TimeoutException var11) {
                  Log.e("AndroidTestSuite", "Exception from the execution thread", var11);
               }

               this.terminateAllRunnerExecution(new IllegalStateException(String.format("Test timed out after %d milliseconds but execution thread failed to terminate\nDumping instr and main threads:\n%s", timeout, this.getStackTraces())));
            }
         }
      } catch (InterruptedException var12) {
         Log.e("AndroidTestSuite", "The correctness of the test runner is at risk. Abort all execution!");
         this.terminateAllRunnerExecution(new IllegalStateException(String.format("Test execution thread got interrupted:\n%s\nDumping instr and main threads:\n%s", var12, this.getStackTraces())));
      }

   }

   private void terminateAllRunnerExecution(final RuntimeException exception) {
      Runnable r = new Runnable() {
         public void run() {
            throw exception;
         }
      };
      Thread t = new Thread(r, "Terminator");
      t.start();

      try {
         t.join();
      } catch (InterruptedException var5) {
         ;
      }

   }

   private String getStackTraces() {
      StringBuilder sb = new StringBuilder();
      Thread t = Thread.currentThread();
      sb.append(t.toString()).append('\n');
      StackTraceElement[] arr$ = t.getStackTrace();
      int len$ = arr$.length;

      int i$;
      StackTraceElement ste;
      for(i$ = 0; i$ < len$; ++i$) {
         ste = arr$[i$];
         sb.append("\tat ").append(ste.toString()).append('\n');
      }

      sb.append('\n');
      t = Looper.getMainLooper().getThread();
      sb.append(t.toString()).append('\n');
      arr$ = t.getStackTrace();
      len$ = arr$.length;

      for(i$ = 0; i$ < len$; ++i$) {
         ste = arr$[i$];
         sb.append("\tat ").append(ste.toString()).append('\n');
      }

      sb.append('\n');
      return sb.toString();
   }
}
