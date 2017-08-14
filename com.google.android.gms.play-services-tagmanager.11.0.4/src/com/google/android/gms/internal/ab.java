package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import org.json.JSONArray;
import org.json.JSONException;

public final class ab extends zzcxq {
   private final int zzamr;
   private final zzcwa zzbIJ;

   public ab(int var1, zzcwa var2) {
      this.zzamr = var1;
      this.zzbIJ = var2;
   }

   public final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof eb);

      try {
         zzcxn var4;
         (var4 = cb.zzO((new JSONArray((String)((eb)var2[0]).value())).getJSONArray(0))).zza(this.zzbIJ);
         dp var5 = var4.zzb(var1, new dp[0]);
         return (dp)(this.zzamr == 0 ? dv.zzbLu : var5);
      } catch (JSONException var6) {
         zzcvk.zzb("Unable to convert Custom Pixie to instruction", var6);
         return dv.zzbLu;
      }
   }
}
