package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzej extends zzbr {
   private static final String ID;
   private static final String zzbFN;
   private static final String zzbFO;

   public zzej() {
      super(ID);
   }

   public final boolean zzAE() {
      return false;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      double var2 = 0.0D;
      double var4 = 2.147483647E9D;
      com.google.android.gms.internal.zzbr var6 = (com.google.android.gms.internal.zzbr)var1.get(zzbFN);
      com.google.android.gms.internal.zzbr var7 = (com.google.android.gms.internal.zzbr)var1.get(zzbFO);
      if (var6 != null && var6 != zzgk.zzCh() && var7 != null && var7 != zzgk.zzCh()) {
         zzgj var8 = zzgk.zzc(var6);
         zzgj var9 = zzgk.zzc(var7);
         if (var8 != zzgk.zzCf() && var9 != zzgk.zzCf()) {
            double var10 = var8.doubleValue();
            double var12 = var9.doubleValue();
            if (var10 <= var12) {
               var2 = var10;
               var4 = var12;
            }
         }
      }

      return zzgk.zzI(Math.round(Math.random() * (var4 - var2) + var2));
   }

   static {
      ID = zzbf.zzdL.toString();
      zzbFN = zzbg.zzip.toString();
      zzbFO = zzbg.zzin.toString();
   }
}
