package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public final class ao implements zzcxo {
   private zze zzvw = zzi.zzrY();

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      return new dt((double)this.zzvw.currentTimeMillis());
   }

   public final void zza(zze var1) {
      this.zzvw = (zze)zzbo.zzu(var1);
   }
}
