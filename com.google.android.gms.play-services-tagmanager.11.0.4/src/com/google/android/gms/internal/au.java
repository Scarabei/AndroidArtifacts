package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class au extends zzcxq {
   private static final Pattern zzbKe = Pattern.compile("(.+)/(.+)/(.+)");

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length >= 3);
      String var3 = zzcxp.zzd(var2[0]);
      String var4 = zzcxp.zzd(var2[1]);
      String var5 = zzcxp.zzd(var2[2]);
      String var6 = var2.length < 4 ? "AES/CBC/NoPadding" : zzcxp.zzd(var2[3]);
      Matcher var7;
      RuntimeException var10000;
      String var10002;
      String var10003;
      String var10004;
      if (!(var7 = zzbKe.matcher(var6)).matches()) {
         var10000 = new RuntimeException;
         var10003 = String.valueOf(var6);
         if (var10003.length() != 0) {
            var10002 = "Encrypt: invalid transformation:".concat(var10003);
         } else {
            var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Encrypt: invalid transformation:");
         }

         var10000.<init>(var10002);
         throw var10000;
      } else {
         String var8 = var7.group(1);
         SecretKeySpec var9 = new SecretKeySpec(var4.getBytes(), var8);
         IvParameterSpec var10 = new IvParameterSpec(var5.getBytes());

         Cipher var11;
         try {
            var11 = Cipher.getInstance(var6);
         } catch (NoSuchPaddingException | NoSuchAlgorithmException var12) {
            var10000 = new RuntimeException;
            var10003 = String.valueOf(var6);
            if (var10003.length() != 0) {
               var10002 = "Encrypt: invalid transformation:".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Encrypt: invalid transformation:");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         return new eb(zza(var11, var3, var9, var10));
      }
   }

   private static String zza(Cipher var0, String var1, SecretKeySpec var2, IvParameterSpec var3) {
      if (var1 != null && var1.length() != 0) {
         try {
            var0.init(1, var2, var3);
            return zzcue.encode(var0.doFinal(var1.getBytes()));
         } catch (Exception var5) {
            RuntimeException var10000 = new RuntimeException;
            String var10003 = String.valueOf(var5.getMessage());
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Encrypt: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Encrypt: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      } else {
         throw new RuntimeException("Encrypt: empty input string");
      }
   }
}
