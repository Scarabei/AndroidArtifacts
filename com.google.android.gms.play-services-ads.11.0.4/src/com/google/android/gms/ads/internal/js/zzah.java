package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzajq;
import com.google.android.gms.internal.zzajs;
import com.google.android.gms.internal.zzajt;

public final class zzah extends zzajt {
   private zzy zzLA;

   public zzah(zzy var1) {
      this.zzLA = var1;
   }

   public final void zza(zzajs var1, zzajq var2) {
      this.zzLA.zza(var1, var2);
   }

   public final void reject() {
      this.zzLA.reject();
   }

   public final int getStatus() {
      return this.zzLA.getStatus();
   }

   public final void finalize() {
      this.zzLA.release();
      this.zzLA = null;
   }

   // $FF: synthetic method
   public final void zzf(Object var1) {
      zzai var2 = (zzai)var1;
      this.zzLA.zzf(var2);
   }
}
