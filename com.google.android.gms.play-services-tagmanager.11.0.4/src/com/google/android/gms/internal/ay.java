package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class ay implements zzcxo {
   private Context mContext;

   public ay(Context var1) {
      this.mContext = (Context)zzbo.zzu(var1);
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      String var3 = null;
      if (var2.length > 0 && var2[0] != dv.zzbLu) {
         var3 = zzcxp.zzd(ed.zza(var1, var2[0]));
      }

      String var4;
      return (dp)((var4 = zzcvj.zzL(this.mContext, var3)) != null ? new eb(var4) : dv.zzbLu);
   }
}
