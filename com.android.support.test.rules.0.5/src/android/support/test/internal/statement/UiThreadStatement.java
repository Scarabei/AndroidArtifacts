package android.support.test.internal.statement;

import android.support.test.InstrumentationRegistry;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.runners.model.Statement;

public class UiThreadStatement extends Statement {
   private final Statement mBase;
   private final boolean mRunOnUiThread;

   public UiThreadStatement(Statement base, boolean runOnUiThread) {
      this.mBase = base;
      this.mRunOnUiThread = runOnUiThread;
   }

   public void evaluate() throws Throwable {
      if (this.mRunOnUiThread) {
         final AtomicReference exceptionRef = new AtomicReference();
         InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
               try {
                  UiThreadStatement.this.mBase.evaluate();
               } catch (Throwable var2) {
                  exceptionRef.set(var2);
               }

            }
         });
         Throwable throwable = (Throwable)exceptionRef.get();
         if (throwable != null) {
            throw throwable;
         }
      } else {
         this.mBase.evaluate();
      }

   }
}
