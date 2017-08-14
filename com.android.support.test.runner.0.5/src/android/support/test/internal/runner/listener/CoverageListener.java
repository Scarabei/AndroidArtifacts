package android.support.test.internal.runner.listener;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.runner.Result;

public class CoverageListener extends InstrumentationRunListener {
   private String mCoverageFilePath;
   private static final String REPORT_KEY_COVERAGE_PATH = "coverageFilePath";
   private static final String DEFAULT_COVERAGE_FILE_NAME = "coverage.ec";
   private static final String LOG_TAG = null;

   public CoverageListener(String customCoverageFilePath) {
      this.mCoverageFilePath = customCoverageFilePath;
   }

   public void setInstrumentation(Instrumentation instr) {
      super.setInstrumentation(instr);
      if (this.mCoverageFilePath == null) {
         this.mCoverageFilePath = instr.getTargetContext().getFilesDir().getAbsolutePath() + File.separator + "coverage.ec";
      }

   }

   public void instrumentationRunFinished(PrintStream writer, Bundle results, Result junitResults) {
      this.generateCoverageReport(writer, results);
   }

   private void generateCoverageReport(PrintStream writer, Bundle results) {
      File coverageFile = new File(this.mCoverageFilePath);

      try {
         Class emmaRTClass = Class.forName("com.vladium.emma.rt.RT");
         Method dumpCoverageMethod = emmaRTClass.getMethod("dumpCoverageData", coverageFile.getClass(), Boolean.TYPE, Boolean.TYPE);
         dumpCoverageMethod.invoke((Object)null, coverageFile, false, false);
         results.putString("coverageFilePath", this.mCoverageFilePath);
         writer.format("\nGenerated code coverage data to %s", this.mCoverageFilePath);
      } catch (ClassNotFoundException var6) {
         this.reportEmmaError(writer, "Is emma jar on classpath?", var6);
      } catch (SecurityException var7) {
         this.reportEmmaError(writer, var7);
      } catch (NoSuchMethodException var8) {
         this.reportEmmaError(writer, var8);
      } catch (IllegalArgumentException var9) {
         this.reportEmmaError(writer, var9);
      } catch (IllegalAccessException var10) {
         this.reportEmmaError(writer, var10);
      } catch (InvocationTargetException var11) {
         this.reportEmmaError(writer, var11);
      }

   }

   private void reportEmmaError(PrintStream writer, Exception e) {
      this.reportEmmaError(writer, "", e);
   }

   private void reportEmmaError(PrintStream writer, String hint, Exception e) {
      String msg = "Failed to generate emma coverage. " + hint;
      Log.e(LOG_TAG, msg, e);
      writer.format("\nError: %s", msg);
   }
}
