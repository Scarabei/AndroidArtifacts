package android.support.v4.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({Scope.LIBRARY_GROUP})
public class SelfDestructiveThread {
   private final Object mLock = new Object();
   @GuardedBy("mLock")
   private HandlerThread mThread;
   @GuardedBy("mLock")
   private Handler mHandler;
   @GuardedBy("mLock")
   private int mGeneration;
   private static final int MSG_INVOKE_RUNNABLE = 1;
   private static final int MSG_DESTRUCTION = 0;
   private Callback mCallback = new Callback() {
      public boolean handleMessage(Message msg) {
         switch(msg.what) {
         case 0:
            SelfDestructiveThread.this.onDestruction();
            return true;
         case 1:
            SelfDestructiveThread.this.onInvokeRunnable((Runnable)msg.obj);
            return true;
         default:
            return true;
         }
      }
   };
   private final int mDestructAfterMillisec;
   private final int mPriority;
   private final String mThreadName;

   public SelfDestructiveThread(String threadName, int priority, int destructAfterMillisec) {
      this.mThreadName = threadName;
      this.mPriority = priority;
      this.mDestructAfterMillisec = destructAfterMillisec;
      this.mGeneration = 0;
   }

   @VisibleForTesting
   public boolean isRunning() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.mThread != null;
      }
   }

   @VisibleForTesting
   public int getGeneration() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.mGeneration;
      }
   }

   private void post(Runnable runnable) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.mThread == null) {
            this.mThread = new HandlerThread(this.mThreadName, this.mPriority);
            this.mThread.start();
            this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
            ++this.mGeneration;
         }

         this.mHandler.removeMessages(0);
         this.mHandler.sendMessage(this.mHandler.obtainMessage(1, runnable));
      }
   }

   public void postAndReply(final Callable callable, final SelfDestructiveThread.ReplyCallback reply) {
      final Handler callingHandler = new Handler();
      this.post(new Runnable() {
         public void run() {
            final Object t;
            try {
               t = callable.call();
            } catch (Exception var3) {
               t = null;
            }

            callingHandler.post(new Runnable() {
               public void run() {
                  reply.onReply(t);
               }
            });
         }
      });
   }

   public Object postAndWait(final Callable callable, int timeoutMillis) throws InterruptedException {
      final ReentrantLock lock = new ReentrantLock();
      final Condition cond = lock.newCondition();
      final AtomicReference holder = new AtomicReference();
      final AtomicBoolean running = new AtomicBoolean(true);
      this.post(new Runnable() {
         public void run() {
            try {
               holder.set(callable.call());
            } catch (Exception var6) {
               ;
            }

            lock.lock();

            try {
               running.set(false);
               cond.signal();
            } finally {
               lock.unlock();
            }

         }
      });
      lock.lock();

      Object var7;
      try {
         if (running.get()) {
            long remaining = TimeUnit.MILLISECONDS.toNanos((long)timeoutMillis);

            do {
               try {
                  remaining = cond.awaitNanos(remaining);
               } catch (InterruptedException var13) {
                  ;
               }

               if (!running.get()) {
                  Object var9 = holder.get();
                  return var9;
               }
            } while(remaining > 0L);

            throw new InterruptedException("timeout");
         }

         var7 = holder.get();
      } finally {
         lock.unlock();
      }

      return var7;
   }

   private void onInvokeRunnable(Runnable runnable) {
      runnable.run();
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.mHandler.removeMessages(0);
         this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), (long)this.mDestructAfterMillisec);
      }
   }

   private void onDestruction() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.mHandler.hasMessages(1)) {
            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
         }
      }
   }

   public interface ReplyCallback {
      void onReply(Object var1);
   }
}
