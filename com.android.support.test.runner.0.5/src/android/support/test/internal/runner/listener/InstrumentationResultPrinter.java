package android.support.test.internal.runner.listener;

import android.os.Bundle;
import android.util.Log;
import java.io.PrintStream;
import org.junit.internal.TextListener;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class InstrumentationResultPrinter extends InstrumentationRunListener {
   private static final String LOG_TAG = "InstrumentationResultPrinter";
   public static final String REPORT_VALUE_ID = "AndroidJUnitRunner";
   public static final String REPORT_KEY_NUM_TOTAL = "numtests";
   public static final String REPORT_KEY_NUM_CURRENT = "current";
   public static final String REPORT_KEY_NAME_CLASS = "class";
   public static final String REPORT_KEY_NAME_TEST = "test";
   public static final int REPORT_VALUE_RESULT_START = 1;
   public static final int REPORT_VALUE_RESULT_OK = 0;
   /** @deprecated */
   @Deprecated
   public static final int REPORT_VALUE_RESULT_ERROR = -1;
   public static final int REPORT_VALUE_RESULT_FAILURE = -2;
   public static final int REPORT_VALUE_RESULT_IGNORED = -3;
   public static final int REPORT_VALUE_RESULT_ASSUMPTION_FAILURE = -4;
   public static final String REPORT_KEY_STACK = "stack";
   private final Bundle mResultTemplate;
   Bundle mTestResult;
   int mTestNum = 0;
   int mTestResultCode = -999;
   String mTestClass = null;
   private Description mDescription;

   public InstrumentationResultPrinter() {
      this.mDescription = Description.EMPTY;
      this.mResultTemplate = new Bundle();
   }

   public void testRunStarted(Description description) throws Exception {
      this.mResultTemplate.putString("id", "AndroidJUnitRunner");
      this.mResultTemplate.putInt("numtests", description.testCount());
   }

   public void testStarted(Description description) throws Exception {
      this.mDescription = description;
      String testClass = description.getClassName();
      String testName = description.getMethodName();
      this.mTestResult = new Bundle(this.mResultTemplate);
      this.mTestResult.putString("class", testClass);
      this.mTestResult.putString("test", testName);
      this.mTestResult.putInt("current", ++this.mTestNum);
      if (testClass != null && !testClass.equals(this.mTestClass)) {
         this.mTestResult.putString("stream", String.format("\n%s:", testClass));
         this.mTestClass = testClass;
      } else {
         this.mTestResult.putString("stream", "");
      }

      this.sendStatus(1, this.mTestResult);
      this.mTestResultCode = 0;
   }

   public void testFinished(Description description) throws Exception {
      if (this.mTestResultCode == 0) {
         this.mTestResult.putString("stream", ".");
      }

      this.sendStatus(this.mTestResultCode, this.mTestResult);
   }

   public void testFailure(Failure failure) throws Exception {
      this.mTestResultCode = -2;
      this.reportFailure(failure);
   }

   public void testAssumptionFailure(Failure failure) {
      this.mTestResultCode = -4;
      this.mTestResult.putString("stack", failure.getTrace());
   }

   private void reportFailure(Failure failure) {
      this.mTestResult.putString("stack", failure.getTrace());
      this.mTestResult.putString("stream", String.format("\nError in %s:\n%s", failure.getDescription().getDisplayName(), failure.getTrace()));
   }

   public void testIgnored(Description description) throws Exception {
      this.testStarted(description);
      this.mTestResultCode = -3;
      this.testFinished(description);
   }

   public void reportProcessCrash(Throwable t) {
      this.mTestResultCode = -2;
      Failure failure = new Failure(this.mDescription, t);
      this.mTestResult.putString("stack", failure.getTrace());
      this.mTestResult.putString("stream", String.format("\nProcess crashed while executing %s:\n%s", this.mDescription.getDisplayName(), failure.getTrace()));

      try {
         this.testFinished(this.mDescription);
      } catch (Exception var4) {
         Log.e("InstrumentationResultPrinter", "Failed to mark test " + this.mDescription.getDisplayName() + " as finished after process crash");
      }

   }

   public void instrumentationRunFinished(PrintStream streamResult, Bundle resultBundle, Result junitResults) {
      (new TextListener(streamResult)).testRunFinished(junitResults);
   }
}
