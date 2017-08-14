package android.support.v4.content;

import android.os.AsyncTask;
import java.util.concurrent.Executor;

/** @deprecated */
@Deprecated
public final class ParallelExecutorCompat {
   /** @deprecated */
   @Deprecated
   public static Executor getParallelExecutor() {
      return AsyncTask.THREAD_POOL_EXECUTOR;
   }
}
