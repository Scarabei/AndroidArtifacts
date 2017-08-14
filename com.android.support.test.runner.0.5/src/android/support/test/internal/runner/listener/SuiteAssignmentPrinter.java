package android.support.test.internal.runner.listener;

import android.support.test.internal.runner.TestSize;
import android.util.Log;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class SuiteAssignmentPrinter extends InstrumentationRunListener {
   long mStartTime;
   long mEndTime;
   boolean mTimingValid;

   public void testStarted(Description description) throws Exception {
      this.mTimingValid = true;
      this.mStartTime = this.getCurrentTimeMillis();
   }

   public void testFinished(Description description) throws Exception {
      this.mEndTime = this.getCurrentTimeMillis();
      if (this.mTimingValid && this.mStartTime >= 0L) {
         long runTime = this.mEndTime - this.mStartTime;
         TestSize assignmentSuite = TestSize.getTestSizeForRunTime((float)runTime);
         TestSize currentRenameSize = TestSize.fromDescription(description);
         if (!assignmentSuite.equals(currentRenameSize)) {
            this.sendString(String.format("\n%s#%s: current size: %s. suggested: %s runTime: %d ms\n", description.getClassName(), description.getMethodName(), currentRenameSize, assignmentSuite.getSizeQualifierName(), runTime));
         } else {
            this.sendString(".");
            Log.d("SuiteAssignmentPrinter", String.format("%s#%s assigned correctly as %s. runTime: %d ms\n", description.getClassName(), description.getMethodName(), assignmentSuite.getSizeQualifierName(), runTime));
         }
      } else {
         this.sendString("F");
         Log.d("SuiteAssignmentPrinter", String.format("%s#%s: skipping suite assignment due to test failure\n", description.getClassName(), description.getMethodName()));
      }

      this.mStartTime = -1L;
   }

   public void testFailure(Failure failure) throws Exception {
      this.mTimingValid = false;
   }

   public void testAssumptionFailure(Failure failure) {
      this.mTimingValid = false;
   }

   public void testIgnored(Description description) throws Exception {
      this.mTimingValid = false;
   }

   public long getCurrentTimeMillis() {
      return System.currentTimeMillis();
   }
}
