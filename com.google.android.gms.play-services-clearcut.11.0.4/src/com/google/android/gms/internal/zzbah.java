package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzbah implements zzazq {
   private static final Charset UTF_8 = Charset.forName("UTF-8");
   static Boolean zzazO = null;
   private zzbai zzazP;

   public zzbah(Context var1) {
      this(new zzbai(var1));
   }

   private zzbah(zzbai var1) {
      this.zzazP = (zzbai)zzbo.zzu(var1);
   }

   private static zzbaj zzcr(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = "";
         int var2;
         if ((var2 = var0.indexOf(44)) >= 0) {
            var1 = var0.substring(0, var2);
            ++var2;
         } else {
            var2 = 0;
         }

         String var10001;
         String var10002;
         String var10003;
         int var3;
         if ((var3 = var0.indexOf(47, var2)) <= 0) {
            var10002 = String.valueOf(var0);
            if (var10002.length() != 0) {
               var10001 = "Failed to parse the rule: ".concat(var10002);
            } else {
               var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Failed to parse the rule: ");
            }

            Log.e("LogSamplerImpl", var10001);
            return null;
         } else {
            long var4;
            long var6;
            try {
               var4 = Long.parseLong(var0.substring(var2, var3));
               var6 = Long.parseLong(var0.substring(var3 + 1));
            } catch (NumberFormatException var9) {
               var10002 = String.valueOf(var0);
               if (var10002.length() != 0) {
                  var10001 = "parseLong() failed while parsing: ".concat(var10002);
               } else {
                  var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("parseLong() failed while parsing: ");
               }

               Log.e("LogSamplerImpl", var10001, var9);
               return null;
            }

            if (var4 >= 0L && var6 >= 0L) {
               return new zzbaj(var1, var4, var6);
            } else {
               Log.e("LogSamplerImpl", (new StringBuilder(72)).append("negative values not supported: ").append(var4).append("/").append(var6).toString());
               return null;
            }
         }
      }
   }

   public final boolean zzg(String var1, int var2) {
      String var3 = null;
      if (var1 != null && !var1.isEmpty()) {
         var3 = var1;
      } else if (var2 >= 0) {
         var3 = String.valueOf(var2);
      }

      if (var3 == null) {
         return true;
      } else {
         zzbai var7 = this.zzazP;
         long var4 = this.zzazP.zzazQ == null ? 0L : hi.getLong(var7.zzazQ, "android_id", 0L);
         var7 = this.zzazP;
         String var10000;
         if (this.zzazP.zzazQ == null) {
            var10000 = null;
         } else {
            ContentResolver var20 = var7.zzazQ;
            String var10001 = String.valueOf("gms:playlog:service:sampling_");
            String var10002 = String.valueOf(var3);
            if (var10002.length() != 0) {
               var10001 = var10001.concat(var10002);
            } else {
               String var10003 = new String;
               var10002 = var10001;
               var10001 = var10003;
               var10003.<init>(var10002);
            }

            var10000 = hi.zza(var20, var10001, (String)null);
         }

         zzbaj var6;
         if ((var6 = zzcr(var10000)) == null) {
            return true;
         } else {
            String var17 = var6.zzazR;
            long var21;
            if (var6.zzazR != null && !var17.isEmpty()) {
               byte[] var10;
               ByteBuffer var11;
               (var11 = ByteBuffer.allocate((var10 = var17.getBytes(UTF_8)).length + 8)).put(var10);
               var11.putLong(var4);
               var21 = zzbac.zzf(var11.array());
            } else {
               var21 = zzbac.zzf(ByteBuffer.allocate(8).putLong(var4).array());
            }

            long var19 = var6.zzazT;
            long var9 = var6.zzazS;
            long var18 = var21;
            if (var9 >= 0L && var19 >= 0L) {
               return var19 > 0L && (var18 >= 0L ? var18 % var19 : (Long.MAX_VALUE % var19 + 1L + (var18 & Long.MAX_VALUE) % var19) % var19) < var9;
            } else {
               throw new IllegalArgumentException((new StringBuilder(72)).append("negative values not supported: ").append(var9).append("/").append(var19).toString());
            }
         }
      }
   }
}
