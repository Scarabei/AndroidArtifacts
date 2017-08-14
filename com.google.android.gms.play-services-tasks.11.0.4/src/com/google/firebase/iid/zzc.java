package com.google.firebase.iid;

import android.content.Intent;

final class zzc implements Runnable {
   // $FF: synthetic field
   private Intent val$intent;
   // $FF: synthetic field
   private Intent zzckg;
   // $FF: synthetic field
   private zzb zzckh;

   zzc(zzb var1, Intent var2, Intent var3) {
      this.zzckh = var1;
      this.val$intent = var2;
      this.zzckg = var3;
      super();
   }

   public final void run() {
      this.zzckh.handleIntent(this.val$intent);
      zzb.zza(this.zzckh, this.zzckg);
   }
}
