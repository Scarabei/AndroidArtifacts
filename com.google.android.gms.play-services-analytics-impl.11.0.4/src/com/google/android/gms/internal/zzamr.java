package com.google.android.gms.internal;

import android.content.ComponentName;

final class zzamr implements Runnable {
   // $FF: synthetic field
   private ComponentName val$name;
   // $FF: synthetic field
   private zzamp zzago;

   zzamr(zzamp var1, ComponentName var2) {
      this.zzago = var1;
      this.val$name = var2;
      super();
   }

   public final void run() {
      zzamn.zza(this.zzago.zzagk, this.val$name);
   }
}
