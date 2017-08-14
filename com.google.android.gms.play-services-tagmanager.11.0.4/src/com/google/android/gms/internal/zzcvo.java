package com.google.android.gms.internal;

final class zzcvo implements zzcwl {
   // $FF: synthetic field
   private zzcvn zzbIB;

   zzcvo(zzcvn var1) {
      this.zzbIB = var1;
      super();
   }

   public final void zza(zzcuw var1) {
      zzcvn.zza(this.zzbIB, var1.zzBm());
   }

   public final void zzb(zzcuw var1) {
      zzcvn.zza(this.zzbIB, var1.zzBm());
      long var2 = var1.zzBm();
      zzcvk.v((new StringBuilder(57)).append("Permanent failure dispatching hitId: ").append(var2).toString());
   }

   public final void zzc(zzcuw var1) {
      long var2;
      if ((var2 = var1.zzBn()) == 0L) {
         zzcvn.zza(this.zzbIB, var1.zzBm(), zzcvn.zza(this.zzbIB).currentTimeMillis());
      } else {
         if (var2 + 14400000L < zzcvn.zza(this.zzbIB).currentTimeMillis()) {
            zzcvn.zza(this.zzbIB, var1.zzBm());
            long var4 = var1.zzBm();
            zzcvk.v((new StringBuilder(47)).append("Giving up on failed hitId: ").append(var4).toString());
         }

      }
   }
}
