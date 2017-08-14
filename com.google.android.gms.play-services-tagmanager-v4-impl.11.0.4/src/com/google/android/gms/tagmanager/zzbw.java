package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbf;
import com.google.android.gms.internal.zzbg;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

final class zzbw extends zzbr {
   private static final String ID;
   private static final String zzbEH;
   private static final String zzbEN;
   private static final String zzbEJ;

   public zzbw() {
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
         String var5 = (var4 = (com.google.android.gms.internal.zzbr)var1.get(zzbEN)) == null ? "MD5" : zzgk.zzb(var4);
         com.google.android.gms.internal.zzbr var6;
         String var7 = (var6 = (com.google.android.gms.internal.zzbr)var1.get(zzbEJ)) == null ? "text" : zzgk.zzb(var6);
         String var10000;
         String var10001;
         String var10002;
         byte[] var8;
         if ("text".equals(var7)) {
            var8 = var3.getBytes();
         } else {
            if (!"base16".equals(var7)) {
               var10001 = String.valueOf(var7);
               if (var10001.length() != 0) {
                  var10000 = "Hash: unknown input format: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Hash: unknown input format: ");
               }

               zzdj.e(var10000);
               return zzgk.zzCh();
            }

            var8 = zzo.decode(var3);
         }

         try {
            MessageDigest var10;
            (var10 = MessageDigest.getInstance(var5)).update(var8);
            return zzgk.zzI(zzo.encode(var10.digest()));
         } catch (NoSuchAlgorithmException var11) {
            var10001 = String.valueOf(var5);
            if (var10001.length() != 0) {
               var10000 = "Hash: unknown algorithm: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Hash: unknown algorithm: ");
            }

            zzdj.e(var10000);
            return zzgk.zzCh();
         }
      } else {
         return zzgk.zzCh();
      }
   }

   static {
      ID = zzbf.zzdX.toString();
      zzbEH = zzbg.zzfR.toString();
      zzbEN = zzbg.zzfH.toString();
      zzbEJ = zzbg.zzhR.toString();
   }
}
