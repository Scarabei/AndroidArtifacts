package android.support.test.internal.util;

import android.app.Instrumentation;
import android.os.Bundle;

public class AndroidRunnerParams {
   private final Instrumentation mInstrumentation;
   private final Bundle mBundle;
   private final boolean mSkipExecution;
   private final long mPerTestTimeout;
   private final boolean mIgnoreSuiteMethods;

   public AndroidRunnerParams(Instrumentation instrumentation, Bundle bundle, boolean skipExecution, long perTestTimeout, boolean ignoreSuiteMethods) {
      this.mInstrumentation = instrumentation;
      this.mBundle = bundle;
      this.mSkipExecution = skipExecution;
      this.mPerTestTimeout = perTestTimeout;
      this.mIgnoreSuiteMethods = ignoreSuiteMethods;
   }

   public Instrumentation getInstrumentation() {
      return this.mInstrumentation;
   }

   public Bundle getBundle() {
      return this.mBundle;
   }

   public boolean isSkipExecution() {
      return this.mSkipExecution;
   }

   public long getPerTestTimeout() {
      return this.mPerTestTimeout;
   }

   public boolean isIgnoreSuiteMethods() {
      return this.mIgnoreSuiteMethods;
   }
}
