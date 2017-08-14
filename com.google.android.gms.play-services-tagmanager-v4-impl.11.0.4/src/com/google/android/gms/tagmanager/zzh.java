package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzh extends zzbr {
   private static final String ID;
   private static final String zzbDo;
   private static final String zzbDp;
   private final Context zzqD;

   public zzh(Context var1) {
      super(ID, zzbDp);
      this.zzqD = var1;
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      com.google.android.gms.internal.zzbr var2;
      if ((var2 = (com.google.android.gms.internal.zzbr)var1.get(zzbDp)) == null) {
         return zzgk.zzCh();
      } else {
         String var3 = zzgk.zzb(var2);
         com.google.android.gms.internal.zzbr var4;
         String var5 = (var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbDo)) != null ? zzgk.zzb(var4) : null;
         Context var7 = this.zzqD;
         String var10;
         if ((var10 = (String)zzcx.zzbEY.get(var3)) == null) {
            SharedPreferences var11;
            if ((var11 = var7.getSharedPreferences("gtm_click_referrers", 0)) != null) {
               var10 = var11.getString(var3, "");
            } else {
               var10 = "";
            }

            zzcx.zzbEY.put(var3, var10);
         }

         String var6;
         return (var6 = zzcx.zzV(var10, var5)) != null ? zzgk.zzI(var6) : zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdT.toString();
      zzbDo = zzbg.zzgu.toString();
      zzbDp = zzbg.zzgx.toString();
   }
}
