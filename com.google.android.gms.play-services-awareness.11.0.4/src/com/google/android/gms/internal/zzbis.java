package com.google.android.gms.internal;

import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbo;

public final class zzbis {
   private final acj zzaKS;

   public static zzbis zza(BeaconState.TypeFilter[] var0) {
      return new zzbis(zza(1, var0, 3000L));
   }

   public static zzbis zzb(BeaconState.TypeFilter[] var0) {
      return new zzbis(zza(2, var0, 3000L));
   }

   public static zzbis zzc(BeaconState.TypeFilter[] var0) {
      return new zzbis(zza(3, var0, 3000L));
   }

   private static acj zza(int var0, BeaconState.TypeFilter[] var1, long var2) {
      acj var4;
      (var4 = new acj()).zzcqq = var0;
      var4.zzcqz = new ack[var1.length];
      var4.zzcqr = 3000L;

      for(int var5 = 0; var5 < var1.length; ++var5) {
         zzasv var6 = (zzasv)var1[var5];
         var4.zzcqz[var5] = var6.zzmV();
      }

      return var4;
   }

   private zzbis(acj var1) {
      this.zzaKS = (acj)zzbo.zzu(var1);
   }

   public final acj zzsF() {
      return this.zzaKS;
   }
}
