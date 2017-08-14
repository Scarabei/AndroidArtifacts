package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbo;

@zzzn
public final class zzaio {
   private HandlerThread zzaap = null;
   private Handler mHandler = null;
   private int zzaaq = 0;
   private final Object mLock = new Object();

   public final Looper zzie() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzaaq == 0) {
            if (this.zzaap == null) {
               zzafr.v("Starting the looper thread.");
               this.zzaap = new HandlerThread("LooperProvider");
               this.zzaap.start();
               this.mHandler = new Handler(this.zzaap.getLooper());
               zzafr.v("Looper thread started.");
            } else {
               zzafr.v("Resuming the looper thread");
               this.mLock.notifyAll();
            }
         } else {
            zzbo.zzb(this.zzaap, "Invalid state: mHandlerThread should already been initialized.");
         }

         ++this.zzaaq;
         return this.zzaap.getLooper();
      }
   }

   public final Handler getHandler() {
      return this.mHandler;
   }
}
