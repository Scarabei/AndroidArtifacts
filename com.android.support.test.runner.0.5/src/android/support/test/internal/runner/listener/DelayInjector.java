package android.support.test.internal.runner.listener;

import android.util.Log;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class DelayInjector extends RunListener {
   private final int mDelayMsec;

   public DelayInjector(int delayMsec) {
      this.mDelayMsec = delayMsec;
   }

   public void testRunStarted(Description description) throws Exception {
      this.delay();
   }

   public void testFinished(Description description) throws Exception {
      this.delay();
   }

   private void delay() {
      try {
         Thread.sleep((long)this.mDelayMsec);
      } catch (InterruptedException var2) {
         Log.e("DelayInjector", "interrupted", var2);
      }

   }
}
