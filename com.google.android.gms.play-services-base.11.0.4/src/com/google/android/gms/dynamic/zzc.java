package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zzc implements zzi {
   // $FF: synthetic field
   private Activity val$activity;
   // $FF: synthetic field
   private Bundle zzaSw;
   // $FF: synthetic field
   private Bundle zzxV;
   // $FF: synthetic field
   private zza zzaSv;

   zzc(zza var1, Activity var2, Bundle var3, Bundle var4) {
      this.zzaSv = var1;
      this.val$activity = var2;
      this.zzaSw = var3;
      this.zzxV = var4;
      super();
   }

   public final int getState() {
      return 0;
   }

   public final void zzb(LifecycleDelegate var1) {
      zza.zzb(this.zzaSv).onInflate(this.val$activity, this.zzaSw, this.zzxV);
   }
}
