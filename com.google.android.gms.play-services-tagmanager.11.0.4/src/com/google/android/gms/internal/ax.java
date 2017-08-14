package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class ax extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);
      if (var2[0] == dv.zzbLu) {
         return dv.zzbLu;
      } else {
         String var3 = zzcxp.zzd(var2[0]);
         String var4 = "MD5";
         if (var2.length > 1) {
            var4 = var2[1] == dv.zzbLu ? "MD5" : zzcxp.zzd(var2[1]);
         }

         String var5 = "text";
         if (var2.length > 2) {
            var5 = var2[2] == dv.zzbLu ? "text" : zzcxp.zzd(var2[2]);
         }

         RuntimeException var10000;
         String var10002;
         String var10003;
         String var10004;
         byte[] var6;
         if ("text".equals(var5)) {
            var6 = var3.getBytes();
         } else {
            if (!"base16".equals(var5)) {
               var10000 = new RuntimeException;
               var10003 = String.valueOf(var5);
               if (var10003.length() != 0) {
                  var10002 = "Hash: Unknown input format: ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Hash: Unknown input format: ");
               }

               var10000.<init>(var10002);
               throw var10000;
            }

            var6 = zzcue.decode(var3);
         }

         try {
            MessageDigest var9;
            (var9 = MessageDigest.getInstance(var4)).update(var6);
            return new eb(zzcue.encode(var9.digest()));
         } catch (NoSuchAlgorithmException var10) {
            var10000 = new RuntimeException;
            var10003 = String.valueOf(var4);
            if (var10003.length() != 0) {
               var10002 = "Hash: Unknown algorithm: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Hash: Unknown algorithm: ");
            }

            var10000.<init>(var10002, var10);
            throw var10000;
         }
      }
   }
}
