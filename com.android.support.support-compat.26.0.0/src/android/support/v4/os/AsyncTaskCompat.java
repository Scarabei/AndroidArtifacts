package android.support.v4.os;

import android.os.AsyncTask;

/** @deprecated */
@Deprecated
public final class AsyncTaskCompat {
   /** @deprecated */
   @Deprecated
   public static AsyncTask executeParallel(AsyncTask task, Object... params) {
      if (task == null) {
         throw new IllegalArgumentException("task can not be null");
      } else {
         task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
         return task;
      }
   }
}
