package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class di {
   private final List zzbLb = new ArrayList();
   private final List zzbLc = new ArrayList();
   private final List zzbLd = new ArrayList();
   private final List zzbLe = new ArrayList();

   public final di zzc(dd var1) {
      this.zzbLb.add(var1);
      return this;
   }

   public final di zzd(dd var1) {
      this.zzbLc.add(var1);
      return this;
   }

   public final di zze(dd var1) {
      this.zzbLd.add(var1);
      return this;
   }

   public final di zzf(dd var1) {
      this.zzbLe.add(var1);
      return this;
   }

   public final dg zzDf() {
      return new dg(this.zzbLb, this.zzbLc, this.zzbLd, this.zzbLe, (dh)null);
   }
}
