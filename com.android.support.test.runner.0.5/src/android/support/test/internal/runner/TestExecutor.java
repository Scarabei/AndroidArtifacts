package android.support.test.internal.runner;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.test.internal.runner.listener.InstrumentationRunListener;
import android.support.test.internal.util.Checks;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public final class TestExecutor {
   private static final String LOG_TAG = "TestExecutor";
   private final List mListeners;
   private final Instrumentation mInstr;

   private TestExecutor(TestExecutor.Builder builder) {
      this.mListeners = (List)Checks.checkNotNull(builder.mListeners);
      this.mInstr = builder.mInstr;
   }

   public Bundle execute(TestRequest testRequest) {
      Bundle resultBundle = new Bundle();
      Result junitResults = new Result();
      boolean var11 = false;

      ByteArrayOutputStream summaryStream;
      PrintStream summaryWriter;
      label50: {
         try {
            var11 = true;
            JUnitCore testRunner = new JUnitCore();
            this.setUpListeners(testRunner);
            junitResults = testRunner.run(testRequest.getRequest());
            junitResults.getFailures().addAll(testRequest.getFailures());
            var11 = false;
            break label50;
         } catch (Throwable var12) {
            String msg = "Fatal exception when running tests";
            Log.e("TestExecutor", "Fatal exception when running tests", var12);
            junitResults.getFailures().add(new Failure(Description.createSuiteDescription("Fatal exception when running tests", new Annotation[0]), var12));
            var11 = false;
         } finally {
            if (var11) {
               ByteArrayOutputStream summaryStream = new ByteArrayOutputStream();
               PrintStream summaryWriter = new PrintStream(summaryStream);
               this.reportRunEnded(this.mListeners, summaryWriter, resultBundle, junitResults);
               summaryWriter.close();
               resultBundle.putString("stream", String.format("\n%s", summaryStream.toString()));
            }
         }

         summaryStream = new ByteArrayOutputStream();
         summaryWriter = new PrintStream(summaryStream);
         this.reportRunEnded(this.mListeners, summaryWriter, resultBundle, junitResults);
         summaryWriter.close();
         resultBundle.putString("stream", String.format("\n%s", summaryStream.toString()));
         return resultBundle;
      }

      summaryStream = new ByteArrayOutputStream();
      summaryWriter = new PrintStream(summaryStream);
      this.reportRunEnded(this.mListeners, summaryWriter, resultBundle, junitResults);
      summaryWriter.close();
      resultBundle.putString("stream", String.format("\n%s", summaryStream.toString()));
      return resultBundle;
   }

   private void setUpListeners(JUnitCore testRunner) {
      Iterator i$ = this.mListeners.iterator();

      while(i$.hasNext()) {
         RunListener listener = (RunListener)i$.next();
         Log.d("TestExecutor", "Adding listener " + listener.getClass().getName());
         testRunner.addListener(listener);
         if (listener instanceof InstrumentationRunListener) {
            ((InstrumentationRunListener)listener).setInstrumentation(this.mInstr);
         }
      }

   }

   private void reportRunEnded(List listeners, PrintStream summaryWriter, Bundle resultBundle, Result jUnitResults) {
      Iterator i$ = listeners.iterator();

      while(i$.hasNext()) {
         RunListener listener = (RunListener)i$.next();
         if (listener instanceof InstrumentationRunListener) {
            ((InstrumentationRunListener)listener).instrumentationRunFinished(summaryWriter, resultBundle, jUnitResults);
         }
      }

   }

   // $FF: synthetic method
   TestExecutor(TestExecutor.Builder x0, TestExecutor.SyntheticClass_1 x1) {
      this(x0);
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   public static class Builder {
      private final List mListeners = new ArrayList();
      private final Instrumentation mInstr;

      public Builder(Instrumentation instr) {
         this.mInstr = instr;
      }

      public TestExecutor.Builder addRunListener(RunListener listener) {
         this.mListeners.add(listener);
         return this;
      }

      public TestExecutor build() {
         return new TestExecutor(this);
      }
   }
}
