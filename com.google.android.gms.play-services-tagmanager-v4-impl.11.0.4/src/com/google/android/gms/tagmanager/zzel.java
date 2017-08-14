package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzel extends zzbr {
   private static final String ID;
   private static final String zzbFP;
   private static final String zzbFQ;
   private static final String zzbFR;
   private static final String zzbFS;

   public zzel() {
      super(ID, zzbFP, zzbFQ);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      com.google.android.gms.internal.zzbr var2 = (com.google.android.gms.internal.zzbr)var1.get(zzbFP);
      com.google.android.gms.internal.zzbr var3 = (com.google.android.gms.internal.zzbr)var1.get(zzbFQ);
      if (var2 != null && var2 != zzgk.zzCh() && var3 != null && var3 != zzgk.zzCh()) {
         byte var4 = 64;
         if (zzgk.zzf((com.google.android.gms.internal.zzbr)var1.get(zzbFR)).booleanValue()) {
            var4 = 66;
         }

         int var5 = 1;
         com.google.android.gms.internal.zzbr var6;
         if ((var6 = (com.google.android.gms.internal.zzbr)var1.get(zzbFS)) != null) {
            Long var7;
            if ((var7 = zzgk.zzd(var6)) == zzgk.zzCc()) {
               return zzgk.zzCh();
            }

            if ((var5 = var7.intValue()) < 0) {
               return zzgk.zzCh();
            }
         }

         try {
            String var12 = zzgk.zzb(var2);
            String var8 = zzgk.zzb(var3);
            String var9 = null;
            Matcher var10;
            if ((var10 = Pattern.compile(var8, var4).matcher(var12)).find() && var10.groupCount() >= var5) {
               var9 = var10.group(var5);
            }

            return var9 == null ? zzgk.zzCh() : zzgk.zzI(var9);
         } catch (PatternSyntaxException var11) {
            return zzgk.zzCh();
         }
      } else {
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzeb.toString();
      zzbFP = zzbg.zzfR.toString();
      zzbFQ = zzbg.zzfS.toString();
      zzbFR = zzbg.zzhP.toString();
      zzbFS = zzbg.zzhI.toString();
   }
}
