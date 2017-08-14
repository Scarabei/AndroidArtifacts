package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzbjq {
   private final adb zzaLl;

   public static zzbjq zzc(long var0, long var2) {
      return new zzbjq(zza(1, var0, var2));
   }

   public static zzbjq zzd(long var0, long var2) {
      return new zzbjq(zza(2, var0, var2));
   }

   private static adb zza(int var0, long var1, long var3) {
      zzbo.zzaf(var3 > var1);
      zzbo.zzaf(Math.abs(var1) <= 86400000L);
      zzbo.zzaf(Math.abs(var3) <= 86400000L);
      adb var5;
      (var5 = new adb()).zzcqq = var0;
      var5.zzcrD = var1;
      var5.zzcrE = var3;
      return var5;
   }

   private zzbjq(adb var1) {
      this.zzaLl = (adb)zzbo.zzu(var1);
   }

   public final adb zzsH() {
      return this.zzaLl;
   }
}
