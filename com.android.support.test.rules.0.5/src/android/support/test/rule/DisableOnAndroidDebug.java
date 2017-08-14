package android.support.test.rule;

import android.os.Debug;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DisableOnAndroidDebug implements TestRule {
   private final TestRule mRule;

   public DisableOnAndroidDebug(TestRule rule) {
      this.mRule = rule;
   }

   public final Statement apply(Statement base, Description description) {
      return this.isDebugging() ? base : this.mRule.apply(base, description);
   }

   public boolean isDebugging() {
      return Debug.isDebuggerConnected();
   }
}
