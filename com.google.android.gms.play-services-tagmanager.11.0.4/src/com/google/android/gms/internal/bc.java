package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class bc extends zzcxq {
   private final Context mContext;
   private final zzcvy zzbJZ;

   public bc(Context var1, zzcvy var2) {
      this.mContext = var1;
      this.zzbJZ = var2;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);
      String var3 = zzcxp.zzd(var2[0]);
      Object var4;
      if ((var4 = this.zzbJZ.zzCy().zzBh().get(var3)) == null && var2.length > 1) {
         var4 = var2[1];
      }

      return ed.zzQ(var4);
   }
}
