package android.support.test.rule.logging;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.Beta;
import android.support.test.internal.util.Checks;
import java.io.File;
import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@Beta
public abstract class LoggingBaseRule extends ExternalResource {
   private final String mPackageNameUnderTest;
   private int mAndroidRuntimeVersion;
   private File mLogFile;
   private String mLogFileName;
   private File mLogFileOutputDirectory;
   private String mTestClass;
   private String mTestName;
   private int mTestRunNumber;

   public LoggingBaseRule() {
      this.mAndroidRuntimeVersion = VERSION.SDK_INT;
      this.mLogFile = null;
      this.mLogFileOutputDirectory = null;
      this.mTestRunNumber = 1;
      this.mPackageNameUnderTest = InstrumentationRegistry.getTargetContext().getPackageName();
      this.mLogFileName = this.getDefaultLogFileName();
   }

   public LoggingBaseRule(@NonNull File logFileOutputDirectory, @Nullable String fileName) {
      this();
      Checks.checkNotNull(logFileOutputDirectory, "Log directory must be provided when using this constructor");
      this.mLogFileOutputDirectory = logFileOutputDirectory;
      if (fileName != null) {
         this.mLogFileName = fileName;
      }

   }

   void initialize() {
      if (this.mLogFileName != null) {
         if (null == this.mLogFileOutputDirectory) {
            this.mLogFile = RuleLoggingUtils.getTestFile(this.mTestClass, this.mTestName, this.mLogFileName, this.mTestRunNumber);
         } else {
            this.mLogFile = new File(this.mLogFileOutputDirectory, this.mLogFileName);
         }
      }

   }

   public final void after() {
      this.afterTest();
   }

   abstract void afterTest();

   public final Statement apply(Statement base, Description description) {
      this.mTestName = description.getMethodName();
      this.mTestClass = description.getClassName();
      return super.apply(base, description);
   }

   public final void before() {
      this.initialize();
      this.beforeTest();
   }

   abstract void beforeTest();

   int getAndroidRuntimeVersion() {
      return this.mAndroidRuntimeVersion;
   }

   void setAndroidRuntimeVersion(int version) {
      this.mAndroidRuntimeVersion = version;
   }

   abstract String getDefaultLogFileName();

   File getLogFile() {
      return this.mLogFile;
   }

   protected String getLogFileName() {
      return this.mLogFileName;
   }

   protected File getLogFileOutputDirectory() {
      return this.mLogFileOutputDirectory;
   }

   protected String getPackageNameUnderTest() {
      return this.mPackageNameUnderTest;
   }

   protected String getTestClass() {
      return this.mTestClass;
   }

   protected String getTestName() {
      return this.mTestName;
   }

   protected int getTestRunNumber() {
      return this.mTestRunNumber;
   }

   public void setTestRunNumber(int testRunNumber) {
      Checks.checkState(testRunNumber >= 0, "Invalid test run number (" + testRunNumber + ")");
      this.mTestRunNumber = testRunNumber;
   }
}
