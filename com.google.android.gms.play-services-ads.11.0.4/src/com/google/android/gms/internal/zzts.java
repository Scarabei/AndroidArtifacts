package com.google.android.gms.internal;

@zzzn
final class zzts {
   private static final zztp zzKJ = zztp.zzeN();
   private static final float zzKK;
   private static final long zzKL;
   private static final float zzKM;
   private static final long zzKN;

   static boolean zzeX() {
      int var0 = zzKJ.zzeU();
      int var1 = zzKJ.zzeV();
      int var2 = zzKJ.zzeT() + zzKJ.zzeS();
      return var1 <= (var0 < 16 && zzKN != 0L ? zzb(zzKN, var0) : (zzKM != 0.0F ? 1 + (int)(zzKM * (float)var0) : Integer.MAX_VALUE)) && var2 <= (var0 < 16 && zzKL != 0L ? zzb(zzKL, var0) : (zzKK != 0.0F ? (int)(zzKK * (float)var0) : Integer.MAX_VALUE));
   }

   private static int zzb(long var0, int var2) {
      return (int)(var0 >>> 4 * (var2 % 16) & 15L);
   }

   static {
      zzme var0 = zzmo.zzEk;
      zzKK = ((Float)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).floatValue();
      var0 = zzmo.zzEi;
      zzKL = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).longValue();
      var0 = zzmo.zzEl;
      zzKM = ((Float)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).floatValue();
      var0 = zzmo.zzEj;
      zzKN = ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var0)).longValue();
   }
}
