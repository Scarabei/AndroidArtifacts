package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.WorkerThread;

final class zzcuk implements Runnable {
   private final zzcut zzbHT;
   // $FF: synthetic field
   private zzcuf zzbHS;

   public zzcuk(zzcuf var1, zzcut var2) {
      this.zzbHS = var1;
      super();
      this.zzbHT = var2;
   }

   @WorkerThread
   public final void run() {
      String var10000;
      String var10001;
      String var10002;
      if (zzcuf.zza(this.zzbHS) == 2) {
         var10001 = String.valueOf(this.zzbHT.zzCk());
         if (var10001.length() != 0) {
            var10000 = "Evaluating tags for event ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Evaluating tags for event ");
         }

         zzcvk.v(var10000);
         zzcuf.zzb(this.zzbHS).zzb(this.zzbHT);
      } else {
         String var1;
         if (zzcuf.zza(this.zzbHS) == 1) {
            zzcuf.zzi(this.zzbHS).add(this.zzbHT);
            var1 = String.valueOf(this.zzbHT.zzCk());
            zzcvk.v((new StringBuilder(30 + String.valueOf(var1).length())).append("Added event ").append(var1).append(" to pending queue.").toString());
         } else {
            if (zzcuf.zza(this.zzbHS) == 3) {
               var1 = String.valueOf(this.zzbHT.zzCk());
               zzcvk.v((new StringBuilder(61 + String.valueOf(var1).length())).append("Failed to evaluate tags for event ").append(var1).append(" (container failed to load)").toString());
               if (this.zzbHT.zzCn()) {
                  try {
                     zzcuf.zzj(this.zzbHS).logEventInternalNoInterceptor("app", this.zzbHT.zzCk(), this.zzbHT.zzCl(), this.zzbHT.currentTimeMillis());
                     var1 = String.valueOf(this.zzbHT.zzCk());
                     zzcvk.v((new StringBuilder(38 + String.valueOf(var1).length())).append("Logged passthrough event ").append(var1).append(" to Firebase.").toString());
                     return;
                  } catch (RemoteException var2) {
                     zzcup.zza("Error logging event with measurement proxy:", var2, zzcuf.zzk(this.zzbHS));
                     return;
                  }
               }

               var10001 = String.valueOf(this.zzbHT.zzCk());
               if (var10001.length() != 0) {
                  var10000 = "Discarded non-passthrough event ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Discarded non-passthrough event ");
               }

               zzcvk.v(var10000);
            }

         }
      }
   }
}
