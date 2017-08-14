package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.Arrays;

@zzzn
final class zztm {
   private final Object[] mParams;

   zztm(zzir var1, String var2, int var3) {
      zzme var4 = zzmo.zzEa;
      this.mParams = zzp.zza((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4), var1, var2, var3, (zziv)null);
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zztm)) {
         return false;
      } else {
         zztm var2 = (zztm)var1;
         return Arrays.equals(this.mParams, var2.mParams);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(this.mParams);
   }

   public final String toString() {
      String var1 = String.valueOf(Arrays.toString(this.mParams));
      return (new StringBuilder(24 + String.valueOf(var1).length())).append("[InterstitialAdPoolKey ").append(var1).append("]").toString();
   }
}
