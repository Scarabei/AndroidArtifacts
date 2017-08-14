package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzant {
   private final Object zzahV;
   private final zzbez zzahW;

   private zzant(zzbez var1, Object var2) {
      zzbo.zzu(var1);
      this.zzahW = var1;
      this.zzahV = var2;
   }

   static zzant zza(String var0, boolean var1, boolean var2) {
      return new zzant(zzbez.zzg(var0, var2), var1);
   }

   static zzant zzc(String var0, String var1, String var2) {
      return new zzant(zzbez.zzv(var0, var2), var1);
   }

   static zzant zza(String var0, long var1, long var3) {
      return new zzant(zzbez.zza(var0, var3), var1);
   }

   static zzant zza(String var0, int var1, int var2) {
      return new zzant(zzbez.zza(var0, var2), var1);
   }

   static zzant zza(String var0, float var1, float var2) {
      return new zzant(zzbez.zza(var0, 0.5F), 0.5F);
   }

   public final Object get() {
      return this.zzahV;
   }
}
