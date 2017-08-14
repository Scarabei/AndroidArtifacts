package android.support.test.internal.runner;

import android.support.test.internal.runner.junit3.AndroidJUnit3Builder;
import android.support.test.internal.runner.junit3.AndroidSuiteBuilder;
import android.support.test.internal.runner.junit4.AndroidAnnotatedBuilder;
import android.support.test.internal.runner.junit4.AndroidJUnit4Builder;
import android.support.test.internal.util.AndroidRunnerParams;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.internal.builders.AnnotatedBuilder;
import org.junit.internal.builders.IgnoredBuilder;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runners.model.RunnerBuilder;

class AndroidRunnerBuilder extends AllDefaultPossibilitiesBuilder {
   private final AndroidJUnit3Builder mAndroidJUnit3Builder;
   private final AndroidJUnit4Builder mAndroidJUnit4Builder;
   private final AndroidSuiteBuilder mAndroidSuiteBuilder;
   private final AndroidAnnotatedBuilder mAndroidAnnotatedBuilder;
   private final IgnoredBuilder mIgnoredBuilder;

   public AndroidRunnerBuilder(AndroidRunnerParams runnerParams) {
      super(true);
      this.mAndroidJUnit3Builder = new AndroidJUnit3Builder(runnerParams);
      this.mAndroidJUnit4Builder = new AndroidJUnit4Builder(runnerParams);
      this.mAndroidSuiteBuilder = new AndroidSuiteBuilder(runnerParams);
      this.mAndroidAnnotatedBuilder = new AndroidAnnotatedBuilder(this, runnerParams);
      this.mIgnoredBuilder = new IgnoredBuilder();
   }

   protected JUnit4Builder junit4Builder() {
      return this.mAndroidJUnit4Builder;
   }

   protected JUnit3Builder junit3Builder() {
      return this.mAndroidJUnit3Builder;
   }

   protected AnnotatedBuilder annotatedBuilder() {
      return this.mAndroidAnnotatedBuilder;
   }

   protected IgnoredBuilder ignoredBuilder() {
      return this.mIgnoredBuilder;
   }

   protected RunnerBuilder suiteMethodBuilder() {
      return this.mAndroidSuiteBuilder;
   }
}
