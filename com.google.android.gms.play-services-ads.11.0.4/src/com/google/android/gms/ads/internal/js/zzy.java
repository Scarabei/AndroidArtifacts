package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzajr;
import com.google.android.gms.internal.zzajt;

public final class zzy extends zzajt {
   private final Object mLock = new Object();
   private final zzac zzLr;
   private boolean zzLs;

   public zzy(zzac var1) {
      this.zzLr = var1;
   }

   public final void release() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (!this.zzLs) {
            this.zzLs = true;
            this.zza(new zzz(this), new zzajr());
            this.zza(new zzaa(this), new zzab(this));
         }
      }
   }

   // $FF: synthetic method
   static zzac zza(zzy var0) {
      return var0.zzLr;
   }
}
