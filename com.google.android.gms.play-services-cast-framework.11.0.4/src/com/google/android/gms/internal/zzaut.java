package com.google.android.gms.internal;

import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzh;

final class zzaut implements zzh {
   // $FF: synthetic field
   final zzaus zzasF;

   zzaut(zzaus var1) {
      this.zzasF = var1;
   }

   public final void zznL() {
      if (zzaus.zza(this.zzasF)) {
         IntroductoryOverlay.zza.zzal(zzaus.zzb(this.zzasF));
         zzaus.zze(this.zzasF).zzi(new zzauu(this));
      }
   }

   public final void dismiss() {
      if (zzaus.zza(this.zzasF)) {
         IntroductoryOverlay.zza.zzal(zzaus.zzb(this.zzasF));
         zzaus.zze(this.zzasF).zzh(new zzauv(this));
      }
   }
}
