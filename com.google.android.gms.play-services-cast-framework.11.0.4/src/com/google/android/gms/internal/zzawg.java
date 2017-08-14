package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast.Listener;

final class zzawg extends Listener {
   // $FF: synthetic field
   private zzawf zzavF;

   zzawg(zzawf var1) {
      this.zzavF = var1;
      super();
   }

   public final void onVolumeChanged() {
      zzawf.zza(this.zzavF);
   }
}
