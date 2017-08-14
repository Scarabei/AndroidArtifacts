package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzbjo {
   private final acs zzaLk;

   public static zzbjo zza(int var0, int var1, double var2, long var4) {
      zzbo.zzaf(var2 >= 0.0D);
      return new zzbjo(zza(1, 1, var0, var1, var2, var2, 0L, var4));
   }

   public static zzbjo zza(int var0, int var1, double var2) {
      zzbo.zzaf(var2 >= 0.0D);
      return new zzbjo(zza(2, 1, var0, var1, var2, var2, 3000L, 0L));
   }

   public static zzbjo zzb(int var0, int var1, double var2) {
      zzbo.zzaf(var2 >= 0.0D);
      return new zzbjo(zza(3, 1, var0, var1, var2, var2, 3000L, 0L));
   }

   private static acs zza(int var0, int var1, int var2, int var3, double var4, double var6, long var8, long var10) {
      acs var12;
      (var12 = new acs()).zzcqq = var0;
      var12.zzcrm = 1;
      var12.zzcqr = var8;
      var12.zzcrr = var10;
      var12.zzcrn = var2;
      var12.zzcro = var3;
      var12.zzcrp = var4;
      var12.zzcrq = var6;
      return var12;
   }

   private zzbjo(acs var1) {
      this.zzaLk = (acs)zzbo.zzu(var1);
   }

   public final acs zzsG() {
      return this.zzaLk;
   }
}
