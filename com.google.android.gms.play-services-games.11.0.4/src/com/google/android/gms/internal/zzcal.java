package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

public abstract class zzcal {
   private final AtomicReference zzbbL = new AtomicReference();

   protected abstract zzcaj zzuQ();

   public final void flush() {
      zzcaj var1;
      if ((var1 = (zzcaj)this.zzbbL.get()) != null) {
         var1.flush();
      }

   }

   public final void zzn(String var1, int var2) {
      zzcaj var3;
      if ((var3 = (zzcaj)this.zzbbL.get()) == null) {
         var3 = this.zzuQ();
         if (!this.zzbbL.compareAndSet((Object)null, var3)) {
            var3 = (zzcaj)this.zzbbL.get();
         }
      }

      var3.zzr(var1, var2);
   }
}
