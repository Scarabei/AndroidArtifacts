package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;

final class zzae implements zzdi {
   // $FF: synthetic field
   private zzy zzbDX;

   private zzae(zzy var1) {
      this.zzbDX = var1;
      super();
   }

   public final void zzbw(int var1) {
      if (var1 == zzda.zzbFk) {
         zzy.zzc(this.zzbDX).zzAU();
      }

      zzy var2 = this.zzbDX;
      synchronized(this.zzbDX) {
         if (!this.zzbDX.isReady()) {
            if (zzy.zzb(this.zzbDX) != null) {
               this.zzbDX.setResult(zzy.zzb(this.zzbDX));
            } else {
               this.zzbDX.setResult(this.zzbDX.zzI(Status.zzaBp));
            }
         }
      }

      long var5 = zzy.zzc(this.zzbDX).zzAT();
      zzy.zza(this.zzbDX, var5);
   }

   // $FF: synthetic method
   public final void onSuccess(Object var1) {
      com.google.android.gms.internal.zzbq var3 = (com.google.android.gms.internal.zzbq)var1;
      zzae var2 = this;
      zzy.zzc(this.zzbDX).zzAV();
      zzy var4 = this.zzbDX;
      synchronized(this.zzbDX) {
         long var5;
         if (var3.zzlB == null) {
            if (zzy.zzf(var2.zzbDX).zzlB == null) {
               zzdj.e("Current resource is null; network resource is also null");
               var5 = zzy.zzc(var2.zzbDX).zzAT();
               zzy.zza(var2.zzbDX, var5);
               return;
            }

            var3.zzlB = zzy.zzf(var2.zzbDX).zzlB;
         }

         zzy.zza(var2.zzbDX, var3, zzy.zzd(var2.zzbDX).currentTimeMillis(), false);
         var5 = zzy.zzg(var2.zzbDX);
         zzdj.v((new StringBuilder(58)).append("setting refresh time to current time: ").append(var5).toString());
         if (!zzy.zzh(var2.zzbDX)) {
            zzy.zza(var2.zzbDX, var3);
         }

      }
   }

   // $FF: synthetic method
   zzae(zzy var1, zzz var2) {
      this(var1);
   }
}
