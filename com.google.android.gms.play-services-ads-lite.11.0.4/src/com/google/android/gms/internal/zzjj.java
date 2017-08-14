package com.google.android.gms.internal;

import java.util.Random;

@zzzn
public final class zzjj extends zzkl {
   private final Random zzAO = new Random();
   private long zzAP;
   private Object mLock = new Object();

   public zzjj() {
      this.zzdu();
   }

   public final void zzdu() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         long var2 = 0L;
         int var4 = 3;

         do {
            --var4;
         } while(var4 > 0 && ((var2 = (long)this.zzAO.nextInt() + 2147483648L) == this.zzAP || var2 == 0L));

         this.zzAP = var2;
      }
   }

   public final long getValue() {
      return this.zzAP;
   }
}
