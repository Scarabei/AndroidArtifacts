package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbo;

public final class ag extends zzcxq {
   private final Context mContext;
   private final zzcvy zzbJZ;

   public ag(Context var1, zzcvy var2) {
      this.mContext = (Context)zzbo.zzu(var1);
      this.zzbJZ = var2;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      if (var2.length != 0 && var2[0] != dv.zzbLu) {
         Object var3;
         if ((var3 = this.zzbJZ.zzCy().zzBh().get("_ldl")) == null) {
            return new eb("");
         } else {
            dp var4;
            if (!((var4 = ed.zzQ(var3)) instanceof eb)) {
               return new eb("");
            } else {
               String var5;
               if (!zzcvj.zzV(var5 = (String)((eb)var4).value(), "conv").equals(zzcxp.zzd(var2[0]))) {
                  return new eb("");
               } else {
                  String var6 = null;
                  if (var2.length > 1) {
                     var6 = var2[1] == dv.zzbLu ? null : zzcxp.zzd(var2[1]);
                  }

                  return (var5 = zzcvj.zzV(var5, var6)) != null ? new eb(var5) : new eb("");
               }
            }
         }
      } else {
         return new eb("");
      }
   }
}
