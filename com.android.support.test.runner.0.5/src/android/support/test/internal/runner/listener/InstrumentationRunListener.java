package android.support.test.internal.runner.listener;

import android.app.Instrumentation;
import android.os.Bundle;
import java.io.PrintStream;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public abstract class InstrumentationRunListener extends RunListener {
   private Instrumentation mInstr;

   public Instrumentation getInstrumentation() {
      return this.mInstr;
   }

   public void setInstrumentation(Instrumentation instr) {
      this.mInstr = instr;
   }

   public void sendStatus(int code, Bundle bundle) {
      this.getInstrumentation().sendStatus(code, bundle);
   }

   public void sendString(String msg) {
      Bundle b = new Bundle();
      b.putString("stream", msg);
      this.sendStatus(0, b);
   }

   public void instrumentationRunFinished(PrintStream streamResult, Bundle resultBundle, Result junitResults) {
   }
}
