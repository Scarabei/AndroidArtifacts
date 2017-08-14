package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import java.lang.ref.WeakReference;

final class zzog {
   private final WeakReference zzIh;
   private String zzIi;

   public zzog(zzaka var1) {
      this.zzIh = new WeakReference(var1);
   }

   private final zzrd zze(zzai var1) {
      return new zzoh(this, var1);
   }

   private final zzrd zzf(zzai var1) {
      return new zzoj(this, var1);
   }

   private final zzrd zzg(zzai var1) {
      return new zzok(this, var1);
   }

   private final zzrd zzh(zzai var1) {
      return new zzol(this, var1);
   }

   // $FF: synthetic method
   static WeakReference zza(zzog var0) {
      return var0.zzIh;
   }

   // $FF: synthetic method
   static String zza(zzog var0, String var1) {
      return var0.zzIi = var1;
   }

   // $FF: synthetic method
   static String zzb(zzog var0) {
      return var0.zzIi;
   }

   // $FF: synthetic method
   static zzrd zza(zzog var0, zzai var1) {
      return var0.zze(var1);
   }

   // $FF: synthetic method
   static zzrd zzb(zzog var0, zzai var1) {
      return var0.zzf(var1);
   }

   // $FF: synthetic method
   static zzrd zzc(zzog var0, zzai var1) {
      return var0.zzg(var1);
   }

   // $FF: synthetic method
   static zzrd zzd(zzog var0, zzai var1) {
      return var0.zzh(var1);
   }
}
