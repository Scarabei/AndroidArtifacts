package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;

abstract class zzbcn implements Runnable {
   // $FF: synthetic field
   private zzbcd zzaDp;

   private zzbcn(zzbcd var1) {
      this.zzaDp = var1;
      super();
   }

   @WorkerThread
   public void run() {
      zzbcd.zzc(this.zzaDp).lock();

      try {
         if (Thread.interrupted()) {
            return;
         }

         this.zzpV();
      } catch (RuntimeException var5) {
         zzbcd.zzd(this.zzaDp).zza(var5);
         return;
      } finally {
         zzbcd.zzc(this.zzaDp).unlock();
      }

   }

   @WorkerThread
   protected abstract void zzpV();

   // $FF: synthetic method
   zzbcn(zzbcd var1, zzbce var2) {
      this(var1);
   }
}
