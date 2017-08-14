package com.google.android.gms.internal;

import android.util.Base64;
import com.google.android.gms.common.internal.zzbo;

public final class at extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);
      String var3 = zzcxp.zzd(var2[0]);
      String var4 = "text";
      if (var2.length > 1) {
         var4 = zzcxp.zzd(var2[1]);
      }

      String var5 = "base16";
      if (var2.length > 2) {
         var5 = zzcxp.zzd(var2[2]);
      }

      boolean var6 = var2.length > 3 && zzcxp.zza(var2[3]);
      byte var7 = 2;
      if (var6) {
         var7 = 3;
      }

      byte[] var8;
      RuntimeException var10000;
      String var10002;
      String var10003;
      String var10004;
      try {
         if ("text".equals(var4)) {
            var8 = var3.getBytes();
         } else if ("base16".equals(var4)) {
            var8 = zzcue.decode(var3);
         } else if ("base64".equals(var4)) {
            var8 = Base64.decode(var3, var7);
         } else {
            if (!"base64url".equals(var4)) {
               UnsupportedOperationException var11 = new UnsupportedOperationException;
               var10003 = String.valueOf(var4);
               if (var10003.length() != 0) {
                  var10002 = "Encode: unknown input format: ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Encode: unknown input format: ");
               }

               var11.<init>(var10002);
               throw var11;
            }

            var8 = Base64.decode(var3, var7 | 8);
         }
      } catch (IllegalArgumentException var10) {
         var10000 = new RuntimeException;
         var10003 = String.valueOf(var4);
         if (var10003.length() != 0) {
            var10002 = "Encode: invalid input:".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Encode: invalid input:");
         }

         var10000.<init>(var10002);
         throw var10000;
      }

      String var9;
      if ("base16".equals(var5)) {
         var9 = zzcue.encode(var8);
      } else if ("base64".equals(var5)) {
         var9 = Base64.encodeToString(var8, var7);
      } else {
         if (!"base64url".equals(var5)) {
            var10000 = new RuntimeException;
            var10003 = String.valueOf(var5);
            if (var10003.length() != 0) {
               var10002 = "Encode: unknown output format: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Encode: unknown output format: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         var9 = Base64.encodeToString(var8, var7 | 8);
      }

      return new eb(var9);
   }
}
