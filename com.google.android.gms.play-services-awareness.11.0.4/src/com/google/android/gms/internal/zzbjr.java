package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import java.util.TimeZone;

public final class zzbjr {
   private final add zzaLm;

   public static zzbjr zze(long var0, long var2) {
      zzbo.zzaf(var0 >= 0L);
      zzbo.zzaf(var2 >= 0L);
      zzbo.zzaf(var0 <= var2);
      return new zzbjr(zzc(1, (TimeZone)null, var0, var2));
   }

   public static zzbjr zza(int var0, TimeZone var1, long var2, long var4) {
      zzbo.zzaf(var0 != 1);
      zzbo.zzaf(var2 >= 0L);
      zzbo.zzaf(var2 <= 86400000L);
      zzbo.zzaf(var4 >= 0L);
      zzbo.zzaf(var4 <= 86400000L);
      zzbo.zzaf(var2 <= var4);
      return new zzbjr(zzc(var0, var1, var2, var4));
   }

   public static zzbjr zzb(int var0, TimeZone var1, long var2, long var4) {
      zzbo.zzaf(var0 == 1 || var0 == 2 || var0 == 3 || var0 == 4 || var0 == 5 || var0 == 6 || var0 == 7);
      zzbo.zzaf(var2 >= 0L);
      zzbo.zzaf(var2 <= 86400000L);
      zzbo.zzaf(var4 >= 0L);
      zzbo.zzaf(var4 <= 86400000L);
      zzbo.zzaf(var2 <= var4);
      byte var6;
      switch(var0) {
      case 1:
         var6 = 5;
         break;
      case 2:
         var6 = 6;
         break;
      case 3:
         var6 = 7;
         break;
      case 4:
         var6 = 8;
         break;
      case 5:
         var6 = 9;
         break;
      case 6:
         var6 = 10;
         break;
      case 7:
         var6 = 11;
         break;
      default:
         return null;
      }

      return new zzbjr(zzc(var6, var1, var2, var4));
   }

   private static add zzc(int var0, TimeZone var1, long var2, long var4) {
      add var6;
      (var6 = new add()).zzcqq = var0;
      if (var0 == 1) {
         var6.zzcrI = false;
      } else if (var1 != null && !TextUtils.isEmpty(var1.getID())) {
         var6.zzcrF = var1.getID();
         var6.zzcrI = false;
      } else {
         var6.zzcrI = true;
      }

      var6.zzaTb = var2;
      var6.zzcrG = var4;
      return var6;
   }

   private zzbjr(add var1) {
      this.zzaLm = (add)zzbo.zzu(var1);
   }

   public final add zzsI() {
      return this.zzaLm;
   }
}
