package com.google.android.gms.internal;

public abstract class zzbzu {
   private final int zzBM;
   private final String zzBN;
   private final Object zzBO;

   private zzbzu(int var1, String var2, Object var3) {
      this.zzBM = var1;
      this.zzBN = var2;
      this.zzBO = var3;
      zzcaf.zzub().zza(this);
   }

   public final String getKey() {
      return this.zzBN;
   }

   public final Object zzdI() {
      return this.zzBO;
   }

   public static zzbzw zzb(int var0, String var1, Boolean var2) {
      return new zzbzw(0, var1, var2);
   }

   public static zzbzx zzb(int var0, String var1, int var2) {
      return new zzbzx(0, var1, var2);
   }

   public static zzbzy zzb(int var0, String var1, long var2) {
      return new zzbzy(0, var1, var2);
   }

   public final int getSource() {
      return this.zzBM;
   }

   protected abstract Object zza(zzcac var1);

   // $FF: synthetic method
   zzbzu(int var1, String var2, Object var3, zzbzv var4) {
      this(var1, var2, var3);
   }
}
