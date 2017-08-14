package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Comparator;

final class zzcyj implements Comparator {
   // $FF: synthetic field
   private du zzbJU;
   // $FF: synthetic field
   private zzcwa zzbJV;

   zzcyj(zzcyh var1, du var2, zzcwa var3) {
      this.zzbJU = var2;
      this.zzbJV = var3;
      super();
   }

   // $FF: synthetic method
   public final int compare(Object var1, Object var2) {
      dp var10001 = (dp)var1;
      dp var5 = (dp)var2;
      dp var4 = var10001;
      if (var4 == null) {
         return var5 != null ? 1 : 0;
      } else if (var5 == null) {
         return var4 != null ? -1 : 0;
      } else {
         dp var6;
         zzbo.zzae((var6 = ((zzcxo)this.zzbJU.zzDp()).zzb(this.zzbJV, var4, var5)) instanceof dt);
         return (int)((Double)((dt)var6).zzDo()).doubleValue();
      }
   }
}
