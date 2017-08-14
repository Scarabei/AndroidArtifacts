package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final class zze implements Runnable {
   // $FF: synthetic field
   private Intent val$intent;
   // $FF: synthetic field
   private zzd zzckl;

   zze(zzd var1, Intent var2) {
      this.zzckl = var1;
      this.val$intent = var2;
      super();
   }

   public final void run() {
      String var1 = String.valueOf(this.val$intent.getAction());
      Log.w("EnhancedIntentService", (new StringBuilder(61 + String.valueOf(var1).length())).append("Service took too long to process intent: ").append(var1).append(" App may get closed.").toString());
      this.zzckl.finish();
   }
}
