package com.google.android.gms.internal;

@zzzn
public final class zzair {
   private long zzaau;
   private long zzaav = Long.MIN_VALUE;
   private Object mLock = new Object();

   public zzair(long var1) {
      this.zzaau = var1;
   }

   public final boolean tryAcquire() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         long var2 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();
         if (this.zzaav + this.zzaau > var2) {
            return false;
         } else {
            this.zzaav = var2;
            return true;
         }
      }
   }
}
