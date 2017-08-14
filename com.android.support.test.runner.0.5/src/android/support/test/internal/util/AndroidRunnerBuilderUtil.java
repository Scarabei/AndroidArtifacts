package android.support.test.internal.util;

import junit.framework.TestCase;

public class AndroidRunnerBuilderUtil {
   public static boolean isJUnit3Test(Class testClass) {
      return TestCase.class.isAssignableFrom(testClass);
   }
}
