package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.util.Map;

final class zzbk extends zzbr {
   private static final String ID;
   private static final String zzbEH;
   private static final String zzbEI;
   private static final String zzbEJ;
   private static final String zzbEK;

   public zzbk() {
      super(ID, zzbEH);
   }

   public final boolean zzAE() {
      return true;
   }

   public final com.google.android.gms.internal.zzbr zzo(Map var1) {
      com.google.android.gms.internal.zzbr var2;
      if ((var2 = (com.google.android.gms.internal.zzbr)var1.get(zzbEH)) != null && var2 != zzgk.zzCh()) {
         String var3 = zzgk.zzb(var2);
         com.google.android.gms.internal.zzbr var4;
         String var5 = (var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbEJ)) == null ? "text" : zzgk.zzb(var4);
         com.google.android.gms.internal.zzbr var6;
         String var7 = (var6 = (com.google.android.gms.internal.zzbr)var1.get(zzbEK)) == null ? "base16" : zzgk.zzb(var6);
         byte var8 = 2;
         com.google.android.gms.internal.zzbr var9;
         if ((var9 = (com.google.android.gms.internal.zzbr)var1.get(zzbEI)) != null && zzgk.zzf(var9).booleanValue()) {
            var8 = 3;
         }

         byte[] var10;
         String var10000;
         String var10001;
         String var10002;
         try {
            if ("text".equals(var5)) {
               var10 = var3.getBytes();
            } else if ("base16".equals(var5)) {
               var10 = zzo.decode(var3);
            } else if ("base64".equals(var5)) {
               var10 = Base64.decode(var3, var8);
            } else {
               if (!"base64url".equals(var5)) {
                  var10001 = String.valueOf(var5);
                  if (var10001.length() != 0) {
                     var10000 = "Encode: unknown input format: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Encode: unknown input format: ");
                  }

                  zzdj.e(var10000);
                  return zzgk.zzCh();
               }

               var10 = Base64.decode(var3, var8 | 8);
            }
         } catch (IllegalArgumentException var12) {
            zzdj.e("Encode: invalid input:");
            return zzgk.zzCh();
         }

         String var11;
         if ("base16".equals(var7)) {
            var11 = zzo.encode(var10);
         } else if ("base64".equals(var7)) {
            var11 = Base64.encodeToString(var10, var8);
         } else {
            if (!"base64url".equals(var7)) {
               var10001 = String.valueOf(var7);
               if (var10001.length() != 0) {
                  var10000 = "Encode: unknown output format: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Encode: unknown output format: ");
               }

               zzdj.e(var10000);
               return zzgk.zzCh();
            }

            var11 = Base64.encodeToString(var10, var8 | 8);
         }

         return zzgk.zzI(var11);
      } else {
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdV.toString();
      zzbEH = zzbg.zzfR.toString();
      zzbEI = zzbg.zziy.toString();
      zzbEJ = zzbg.zzhR.toString();
      zzbEK = zzbg.zziI.toString();
   }
}
