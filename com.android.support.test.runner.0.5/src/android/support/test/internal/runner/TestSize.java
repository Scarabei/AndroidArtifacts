package android.support.test.internal.runner;

import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.junit.runner.Description;

public final class TestSize {
   public static final TestSize SMALL = new TestSize("small", SmallTest.class, android.test.suitebuilder.annotation.SmallTest.class, 200.0F);
   public static final TestSize MEDIUM = new TestSize("medium", MediumTest.class, android.test.suitebuilder.annotation.MediumTest.class, 1000.0F);
   public static final TestSize LARGE = new TestSize("large", LargeTest.class, android.test.suitebuilder.annotation.LargeTest.class, Float.MAX_VALUE);
   public static final TestSize NONE = new TestSize("", (Class)null, (Class)null, 0.0F);
   private static final Set ALL_SIZES;
   private final String mSizeQualifierName;
   private final Class mPlatformAnnotationClass;
   private final Class mRunnerFilterAnnotationClass;
   private final float mTestSizeRunTimeThreshold;

   public TestSize(String sizeQualifierName, Class platformAnnotationClass, Class runnerFilterAnnotationClass, float testSizeRuntimeThreshold) {
      this.mSizeQualifierName = sizeQualifierName;
      this.mPlatformAnnotationClass = platformAnnotationClass;
      this.mRunnerFilterAnnotationClass = runnerFilterAnnotationClass;
      this.mTestSizeRunTimeThreshold = testSizeRuntimeThreshold;
   }

   public String getSizeQualifierName() {
      return this.mSizeQualifierName;
   }

   public boolean testMethodIsAnnotatedWithTestSize(Description description) {
      return description.getAnnotation(this.mRunnerFilterAnnotationClass) != null || description.getAnnotation(this.mPlatformAnnotationClass) != null;
   }

   public boolean testClassIsAnnotatedWithTestSize(Description description) {
      Class testClass = description.getTestClass();
      if (null == testClass) {
         return false;
      } else {
         return testClass.isAnnotationPresent(this.mRunnerFilterAnnotationClass) || testClass.isAnnotationPresent(this.mPlatformAnnotationClass);
      }
   }

   public float getRunTimeThreshold() {
      return this.mTestSizeRunTimeThreshold;
   }

   public static TestSize getTestSizeForRunTime(float testRuntime) {
      if (runTimeSmallerThanThreshold(testRuntime, SMALL.getRunTimeThreshold())) {
         return SMALL;
      } else {
         return runTimeSmallerThanThreshold(testRuntime, MEDIUM.getRunTimeThreshold()) ? MEDIUM : LARGE;
      }
   }

   public static boolean isAnyTestSize(Class annotationClass) {
      Iterator i$ = ALL_SIZES.iterator();

      TestSize testSize;
      do {
         if (!i$.hasNext()) {
            return false;
         }

         testSize = (TestSize)i$.next();
      } while(testSize.getRunnerAnnotation() != annotationClass && testSize.getFrameworkAnnotation() != annotationClass);

      return true;
   }

   public static TestSize fromString(String testSize) {
      TestSize testSizeFromString = NONE;
      Iterator i$ = ALL_SIZES.iterator();

      while(i$.hasNext()) {
         TestSize testSizeValue = (TestSize)i$.next();
         if (testSizeValue.getSizeQualifierName().equals(testSize)) {
            testSizeFromString = testSizeValue;
         }
      }

      return testSizeFromString;
   }

   public static TestSize fromDescription(Description description) {
      TestSize testSize = NONE;
      Iterator i$ = ALL_SIZES.iterator();

      TestSize testClassSizeValue;
      while(i$.hasNext()) {
         testClassSizeValue = (TestSize)i$.next();
         if (testClassSizeValue.testMethodIsAnnotatedWithTestSize(description)) {
            testSize = testClassSizeValue;
            break;
         }
      }

      if (NONE.equals(testSize)) {
         i$ = ALL_SIZES.iterator();

         while(i$.hasNext()) {
            testClassSizeValue = (TestSize)i$.next();
            if (testClassSizeValue.testClassIsAnnotatedWithTestSize(description)) {
               testSize = testClassSizeValue;
               break;
            }
         }
      }

      return testSize;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         TestSize testSize = (TestSize)o;
         return Objects.equals(this.mSizeQualifierName, testSize.mSizeQualifierName);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.mSizeQualifierName});
   }

   private static boolean runTimeSmallerThanThreshold(float testRuntime, float runtimeThreshold) {
      return Float.compare(testRuntime, runtimeThreshold) < 0;
   }

   private Class getFrameworkAnnotation() {
      return this.mPlatformAnnotationClass;
   }

   private Class getRunnerAnnotation() {
      return this.mRunnerFilterAnnotationClass;
   }

   static {
      ALL_SIZES = Collections.unmodifiableSet(new HashSet(Arrays.asList(SMALL, MEDIUM, LARGE)));
   }
}
