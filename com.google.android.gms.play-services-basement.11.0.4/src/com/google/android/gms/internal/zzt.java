package com.google.android.gms.internal;

public final class zzt {
   public final Object result;
   public final zzc zzae;
   public final zzaa zzaf;
   public boolean zzag = false;

   public static zzt zza(Object var0, zzc var1) {
      return new zzt(var0, var1);
   }

   public static zzt zzc(zzaa var0) {
      return new zzt(var0);
   }

   private zzt(Object var1, zzc var2) {
      this.result = var1;
      this.zzae = var2;
      this.zzaf = null;
   }

   private zzt(zzaa var1) {
      this.result = null;
      this.zzae = null;
      this.zzaf = var1;
   }
}
