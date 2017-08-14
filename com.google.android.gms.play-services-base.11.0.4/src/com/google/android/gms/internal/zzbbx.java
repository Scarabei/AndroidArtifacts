package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzbbx implements zzbdz {
   private final DataHolder zzaCX;

   protected zzbbx(DataHolder var1) {
      this.zzaCX = var1;
   }

   public final void zzq(Object var1) {
      this.zza(var1, this.zzaCX);
   }

   public final void zzpT() {
      if (this.zzaCX != null) {
         this.zzaCX.close();
      }

   }

   protected abstract void zza(Object var1, DataHolder var2);
}
