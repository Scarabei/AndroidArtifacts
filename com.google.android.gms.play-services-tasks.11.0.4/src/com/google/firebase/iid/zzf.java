package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzf extends Binder {
   private final zzb zzckm;

   zzf(zzb var1) {
      this.zzckm = var1;
   }

   public final void zza(zzd var1) {
      if (Binder.getCallingUid() != Process.myUid()) {
         throw new SecurityException("Binding only allowed within app");
      } else {
         if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "service received new intent via bind strategy");
         }

         if (this.zzckm.zzo(var1.intent)) {
            var1.finish();
         } else {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
               Log.d("EnhancedIntentService", "intent being queued for bg execution");
            }

            this.zzckm.zzbrV.execute(new zzg(this, var1));
         }
      }
   }

   // $FF: synthetic method
   static zzb zza(zzf var0) {
      return var0.zzckm;
   }
}
