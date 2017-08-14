package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
   // $FF: synthetic field
   private zzd zzckn;
   // $FF: synthetic field
   private zzf zzcko;

   zzg(zzf var1, zzd var2) {
      this.zzcko = var1;
      this.zzckn = var2;
      super();
   }

   public final void run() {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
         Log.d("EnhancedIntentService", "bg processing of the intent starting now");
      }

      zzf.zza(this.zzcko).handleIntent(this.zzckn.intent);
      this.zzckn.finish();
   }
}
