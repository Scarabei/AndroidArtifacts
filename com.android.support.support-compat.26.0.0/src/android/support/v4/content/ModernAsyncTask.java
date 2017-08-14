package android.support.v4.content;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class ModernAsyncTask {
   private static final String LOG_TAG = "AsyncTask";
   private static final int CORE_POOL_SIZE = 5;
   private static final int MAXIMUM_POOL_SIZE = 128;
   private static final int KEEP_ALIVE = 1;
   private static final ThreadFactory sThreadFactory = new ThreadFactory() {
      private final AtomicInteger mCount = new AtomicInteger(1);

      public Thread newThread(Runnable r) {
         return new Thread(r, "ModernAsyncTask #" + this.mCount.getAndIncrement());
      }
   };
   private static final BlockingQueue sPoolWorkQueue = new LinkedBlockingQueue(10);
   public static final Executor THREAD_POOL_EXECUTOR;
   private static final int MESSAGE_POST_RESULT = 1;
   private static final int MESSAGE_POST_PROGRESS = 2;
   private static ModernAsyncTask.InternalHandler sHandler;
   private static volatile Executor sDefaultExecutor;
   private final ModernAsyncTask.WorkerRunnable mWorker;
   private final FutureTask mFuture;
   private volatile ModernAsyncTask.Status mStatus;
   private final AtomicBoolean mCancelled;
   private final AtomicBoolean mTaskInvoked;

   private static Handler getHandler() {
      Class var0 = ModernAsyncTask.class;
      synchronized(ModernAsyncTask.class) {
         if (sHandler == null) {
            sHandler = new ModernAsyncTask.InternalHandler();
         }

         return sHandler;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static void setDefaultExecutor(Executor exec) {
      sDefaultExecutor = exec;
   }

   public ModernAsyncTask() {
      this.mStatus = ModernAsyncTask.Status.PENDING;
      this.mCancelled = new AtomicBoolean();
      this.mTaskInvoked = new AtomicBoolean();
      this.mWorker = new ModernAsyncTask.WorkerRunnable() {
         public Object call() throws Exception {
            ModernAsyncTask.this.mTaskInvoked.set(true);
            Object result = null;

            try {
               Process.setThreadPriority(10);
               result = ModernAsyncTask.this.doInBackground(this.mParams);
               Binder.flushPendingCommands();
            } catch (Throwable var6) {
               ModernAsyncTask.this.mCancelled.set(true);
               throw var6;
            } finally {
               ModernAsyncTask.this.postResult(result);
            }

            return result;
         }
      };
      this.mFuture = new FutureTask(this.mWorker) {
         protected void done() {
            try {
               Object result = this.get();
               ModernAsyncTask.this.postResultIfNotInvoked(result);
            } catch (InterruptedException var2) {
               Log.w("AsyncTask", var2);
            } catch (ExecutionException var3) {
               throw new RuntimeException("An error occurred while executing doInBackground()", var3.getCause());
            } catch (CancellationException var4) {
               ModernAsyncTask.this.postResultIfNotInvoked((Object)null);
            } catch (Throwable var5) {
               throw new RuntimeException("An error occurred while executing doInBackground()", var5);
            }

         }
      };
   }

   void postResultIfNotInvoked(Object result) {
      boolean wasTaskInvoked = this.mTaskInvoked.get();
      if (!wasTaskInvoked) {
         this.postResult(result);
      }

   }

   Object postResult(Object result) {
      Message message = getHandler().obtainMessage(1, new ModernAsyncTask.AsyncTaskResult(this, new Object[]{result}));
      message.sendToTarget();
      return result;
   }

   public final ModernAsyncTask.Status getStatus() {
      return this.mStatus;
   }

   protected abstract Object doInBackground(Object... var1);

   protected void onPreExecute() {
   }

   protected void onPostExecute(Object result) {
   }

   protected void onProgressUpdate(Object... values) {
   }

   protected void onCancelled(Object result) {
      this.onCancelled();
   }

   protected void onCancelled() {
   }

   public final boolean isCancelled() {
      return this.mCancelled.get();
   }

   public final boolean cancel(boolean mayInterruptIfRunning) {
      this.mCancelled.set(true);
      return this.mFuture.cancel(mayInterruptIfRunning);
   }

   public final Object get() throws InterruptedException, ExecutionException {
      return this.mFuture.get();
   }

   public final Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      return this.mFuture.get(timeout, unit);
   }

   public final ModernAsyncTask execute(Object... params) {
      return this.executeOnExecutor(sDefaultExecutor, params);
   }

   public final ModernAsyncTask executeOnExecutor(Executor exec, Object... params) {
      if (this.mStatus != ModernAsyncTask.Status.PENDING) {
         switch(this.mStatus) {
         case RUNNING:
            throw new IllegalStateException("Cannot execute task: the task is already running.");
         case FINISHED:
            throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
         default:
            throw new IllegalStateException("We should never reach this state");
         }
      } else {
         this.mStatus = ModernAsyncTask.Status.RUNNING;
         this.onPreExecute();
         this.mWorker.mParams = params;
         exec.execute(this.mFuture);
         return this;
      }
   }

   public static void execute(Runnable runnable) {
      sDefaultExecutor.execute(runnable);
   }

   protected final void publishProgress(Object... values) {
      if (!this.isCancelled()) {
         getHandler().obtainMessage(2, new ModernAsyncTask.AsyncTaskResult(this, values)).sendToTarget();
      }

   }

   void finish(Object result) {
      if (this.isCancelled()) {
         this.onCancelled(result);
      } else {
         this.onPostExecute(result);
      }

      this.mStatus = ModernAsyncTask.Status.FINISHED;
   }

   static {
      THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
      sDefaultExecutor = THREAD_POOL_EXECUTOR;
   }

   private static class AsyncTaskResult {
      final ModernAsyncTask mTask;
      final Object[] mData;

      AsyncTaskResult(ModernAsyncTask task, Object... data) {
         this.mTask = task;
         this.mData = data;
      }
   }

   private abstract static class WorkerRunnable implements Callable {
      Object[] mParams;
   }

   private static class InternalHandler extends Handler {
      public InternalHandler() {
         super(Looper.getMainLooper());
      }

      public void handleMessage(Message msg) {
         ModernAsyncTask.AsyncTaskResult result = (ModernAsyncTask.AsyncTaskResult)msg.obj;
         switch(msg.what) {
         case 1:
            result.mTask.finish(result.mData[0]);
            break;
         case 2:
            result.mTask.onProgressUpdate(result.mData);
         }

      }
   }

   public static enum Status {
      PENDING,
      RUNNING,
      FINISHED;
   }
}
