package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zzd implements zzi {
   // $FF: synthetic field
   private Bundle zzxV;
   // $FF: synthetic field
   private zza zzaSv;

   zzd(zza var1, Bundle var2) {
      this.zzaSv = var1;
      this.zzxV = var2;
      super();
   }

   public final int getState() {
      return 1;
   }

   public final void zzb(LifecycleDelegate var1) {
      zza.zzb(this.zzaSv).onCreate(this.zzxV);
   }
}
