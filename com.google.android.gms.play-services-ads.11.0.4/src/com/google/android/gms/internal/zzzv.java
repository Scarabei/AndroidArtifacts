package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzj;

final class zzzv implements zzzx {
   // $FF: synthetic field
   private Context zztF;

   zzzv(Context var1) {
      this.zztF = var1;
      super();
   }

   public final boolean zza(zzaje var1) {
      zzji.zzds();
      boolean var2 = zzaiy.zzX(this.zztF);
      zzme var4 = zzmo.zzGF;
      boolean var3 = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue() && var1.zzaaQ;
      if (zzzu.zzd(this.zztF, var1.zzaaQ) && var2 && !var3) {
         if (zzj.zzaI(this.zztF)) {
            var4 = zzmo.zzCL;
            if (!((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue()) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
