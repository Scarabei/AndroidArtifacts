package android.support.test.rule;

import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.internal.statement.UiThreadStatement;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UiThreadTestRule implements TestRule {
   private static final String LOG_TAG = "UiThreadTestRule";

   public Statement apply(Statement base, Description description) {
      return new UiThreadStatement(base, this.shouldRunOnUiThread(description));
   }

   protected boolean shouldRunOnUiThread(Description description) {
      return description.getAnnotation(UiThreadTest.class) != null;
   }

   public void runOnUiThread(Runnable runnable) throws Throwable {
      if (Looper.myLooper() == Looper.getMainLooper()) {
         Log.w("UiThreadTestRule", "Already on the UI thread, this method should not be called from the main application thread");
         runnable.run();
      } else {
         FutureTask task = new FutureTask(runnable, (Object)null);
         InstrumentationRegistry.getInstrumentation().runOnMainSync(task);

         try {
            task.get();
         } catch (ExecutionException var4) {
            throw var4.getCause();
         }
      }

   }
}
