package com.google.android.gms.tagmanager;

final class zzed implements zzfx {
   // $FF: synthetic field
   private zzec zzbFA;

   zzed(zzec var1) {
      this.zzbFA = var1;
      super();
   }

   public final void zza(zzbx var1) {
      zzec.zza(this.zzbFA, var1.zzBm());
   }

   public final void zzb(zzbx var1) {
      zzec.zza(this.zzbFA, var1.zzBm());
      long var2 = var1.zzBm();
      zzdj.v((new StringBuilder(57)).append("Permanent failure dispatching hitId: ").append(var2).toString());
   }

   public final void zzc(zzbx var1) {
      long var2;
      if ((var2 = var1.zzBn()) == 0L) {
         zzec.zza(this.zzbFA, var1.zzBm(), zzec.zza(this.zzbFA).currentTimeMillis());
      } else {
         if (var2 + 14400000L < zzec.zza(this.zzbFA).currentTimeMillis()) {
            zzec.zza(this.zzbFA, var1.zzBm());
            long var4 = var1.zzBm();
            zzdj.v((new StringBuilder(47)).append("Giving up on failed hitId: ").append(var4).toString());
         }

      }
   }
}
