package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

@zzzn
public final class zzaah extends zzaaq {
   private final WeakReference zzTm;

   public zzaah(zzzw var1) {
      this.zzTm = new WeakReference(var1);
   }

   public final void zza(zzaai var1) {
      zzzw var2;
      if ((var2 = (zzzw)this.zzTm.get()) != null) {
         var2.zza(var1);
      }

   }
}
