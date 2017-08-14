package android.support.v4.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.os.OperationCanceledException;
import android.support.v4.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader extends Loader {
   static final String TAG = "AsyncTaskLoader";
   static final boolean DEBUG = false;
   private final Executor mExecutor;
   volatile AsyncTaskLoader.LoadTask mTask;
   volatile AsyncTaskLoader.LoadTask mCancellingTask;
   long mUpdateThrottle;
   long mLastLoadCompleteTime;
   Handler mHandler;

   public AsyncTaskLoader(Context context) {
      this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
   }

   private AsyncTaskLoader(Context context, Executor executor) {
      super(context);
      this.mLastLoadCompleteTime = -10000L;
      this.mExecutor = executor;
   }

   public void setUpdateThrottle(long delayMS) {
      this.mUpdateThrottle = delayMS;
      if (delayMS != 0L) {
         this.mHandler = new Handler();
      }

   }

   protected void onForceLoad() {
      super.onForceLoad();
      this.cancelLoad();
      this.mTask = new AsyncTaskLoader.LoadTask();
      this.executePendingTask();
   }

   protected boolean onCancelLoad() {
      if (this.mTask != null) {
         if (!this.mStarted) {
            this.mContentChanged = true;
         }

         if (this.mCancellingTask != null) {
            if (this.mTask.waiting) {
               this.mTask.waiting = false;
               this.mHandler.removeCallbacks(this.mTask);
            }

            this.mTask = null;
            return false;
         } else if (this.mTask.waiting) {
            this.mTask.waiting = false;
            this.mHandler.removeCallbacks(this.mTask);
            this.mTask = null;
            return false;
         } else {
            boolean cancelled = this.mTask.cancel(false);
            if (cancelled) {
               this.mCancellingTask = this.mTask;
               this.cancelLoadInBackground();
            }

            this.mTask = null;
            return cancelled;
         }
      } else {
         return false;
      }
   }

   public void onCanceled(Object data) {
   }

   void executePendingTask() {
      if (this.mCancellingTask == null && this.mTask != null) {
         if (this.mTask.waiting) {
            this.mTask.waiting = false;
            this.mHandler.removeCallbacks(this.mTask);
         }

         if (this.mUpdateThrottle > 0L) {
            long now = SystemClock.uptimeMillis();
            if (now < this.mLastLoadCompleteTime + this.mUpdateThrottle) {
               this.mTask.waiting = true;
               this.mHandler.postAtTime(this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
               return;
            }
         }

         this.mTask.executeOnExecutor(this.mExecutor, (Void[])null);
      }

   }

   void dispatchOnCancelled(AsyncTaskLoader.LoadTask task, Object data) {
      this.onCanceled(data);
      if (this.mCancellingTask == task) {
         this.rollbackContentChanged();
         this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
         this.mCancellingTask = null;
         this.deliverCancellation();
         this.executePendingTask();
      }

   }

   void dispatchOnLoadComplete(AsyncTaskLoader.LoadTask task, Object data) {
      if (this.mTask != task) {
         this.dispatchOnCancelled(task, data);
      } else if (this.isAbandoned()) {
         this.onCanceled(data);
      } else {
         this.commitContentChanged();
         this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
         this.mTask = null;
         this.deliverResult(data);
      }

   }

   public abstract Object loadInBackground();

   protected Object onLoadInBackground() {
      return this.loadInBackground();
   }

   public void cancelLoadInBackground() {
   }

   public boolean isLoadInBackgroundCanceled() {
      return this.mCancellingTask != null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public void waitForLoader() {
      AsyncTaskLoader.LoadTask task = this.mTask;
      if (task != null) {
         task.waitForLoader();
      }

   }

   public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
      super.dump(prefix, fd, writer, args);
      if (this.mTask != null) {
         writer.print(prefix);
         writer.print("mTask=");
         writer.print(this.mTask);
         writer.print(" waiting=");
         writer.println(this.mTask.waiting);
      }

      if (this.mCancellingTask != null) {
         writer.print(prefix);
         writer.print("mCancellingTask=");
         writer.print(this.mCancellingTask);
         writer.print(" waiting=");
         writer.println(this.mCancellingTask.waiting);
      }

      if (this.mUpdateThrottle != 0L) {
         writer.print(prefix);
         writer.print("mUpdateThrottle=");
         TimeUtils.formatDuration(this.mUpdateThrottle, writer);
         writer.print(" mLastLoadCompleteTime=");
         TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), writer);
         writer.println();
      }

   }

   final class LoadTask extends ModernAsyncTask implements Runnable {
      private final CountDownLatch mDone = new CountDownLatch(1);
      boolean waiting;

      protected Object doInBackground(Void... params) {
         try {
            Object data = AsyncTaskLoader.this.onLoadInBackground();
            return data;
         } catch (OperationCanceledException var3) {
            if (!this.isCancelled()) {
               throw var3;
            } else {
               return null;
            }
         }
      }

      protected void onPostExecute(Object data) {
         try {
            AsyncTaskLoader.this.dispatchOnLoadComplete(this, data);
         } finally {
            this.mDone.countDown();
         }

      }

      protected void onCancelled(Object data) {
         try {
            AsyncTaskLoader.this.dispatchOnCancelled(this, data);
         } finally {
            this.mDone.countDown();
         }

      }

      public void run() {
         this.waiting = false;
         AsyncTaskLoader.this.executePendingTask();
      }

      public void waitForLoader() {
         try {
            this.mDone.await();
         } catch (InterruptedException var2) {
            ;
         }

      }
   }
}
