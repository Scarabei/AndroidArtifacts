package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzaet;
import com.google.android.gms.internal.zzzn;
import java.util.Map;

@zzzn
public final class zzw {
   private boolean zztq;
   private zzaet zztr;

   public zzw(zzaet var1) {
      this.zztr = var1;
   }

   public final void recordClick() {
      this.zztq = true;
   }

   public final boolean zzaR() {
      return !(this.zztr == null ? false : this.zztr.zzgY().zzXw) || this.zztq;
   }

   public final void zzt(@Nullable String var1) {
      if (this.zztr != null) {
         this.zztr.zza(var1, (Map)null, 3);
      }
   }
}
