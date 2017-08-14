package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal {
   private boolean mIsCanceled;
   private CancellationSignal.OnCancelListener mOnCancelListener;
   private Object mCancellationSignalObj;
   private boolean mCancelInProgress;

   public boolean isCanceled() {
      synchronized(this) {
         return this.mIsCanceled;
      }
   }

   public void throwIfCanceled() {
      if (this.isCanceled()) {
         throw new OperationCanceledException();
      }
   }

   public void cancel() {
      CancellationSignal.OnCancelListener listener;
      Object obj;
      synchronized(this) {
         if (this.mIsCanceled) {
            return;
         }

         this.mIsCanceled = true;
         this.mCancelInProgress = true;
         listener = this.mOnCancelListener;
         obj = this.mCancellationSignalObj;
      }

      boolean var13 = false;

      try {
         var13 = true;
         if (listener != null) {
            listener.onCancel();
         }

         if (obj != null) {
            if (VERSION.SDK_INT >= 16) {
               ((android.os.CancellationSignal)obj).cancel();
               var13 = false;
            } else {
               var13 = false;
            }
         } else {
            var13 = false;
         }
      } finally {
         if (var13) {
            synchronized(this) {
               this.mCancelInProgress = false;
               this.notifyAll();
            }
         }
      }

      synchronized(this) {
         this.mCancelInProgress = false;
         this.notifyAll();
      }
   }

   public void setOnCancelListener(CancellationSignal.OnCancelListener listener) {
      synchronized(this) {
         this.waitForCancelFinishedLocked();
         if (this.mOnCancelListener == listener) {
            return;
         }

         this.mOnCancelListener = listener;
         if (!this.mIsCanceled || listener == null) {
            return;
         }
      }

      listener.onCancel();
   }

   public Object getCancellationSignalObject() {
      if (VERSION.SDK_INT < 16) {
         return null;
      } else {
         synchronized(this) {
            if (this.mCancellationSignalObj == null) {
               this.mCancellationSignalObj = new android.os.CancellationSignal();
               if (this.mIsCanceled) {
                  ((android.os.CancellationSignal)this.mCancellationSignalObj).cancel();
               }
            }

            return this.mCancellationSignalObj;
         }
      }
   }

   private void waitForCancelFinishedLocked() {
      while(this.mCancelInProgress) {
         try {
            this.wait();
         } catch (InterruptedException var2) {
            ;
         }
      }

   }

   public interface OnCancelListener {
      void onCancel();
   }
}
