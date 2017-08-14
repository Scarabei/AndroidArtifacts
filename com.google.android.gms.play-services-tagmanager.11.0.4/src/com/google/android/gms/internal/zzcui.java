package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.WorkerThread;
import java.util.Iterator;
import java.util.List;

final class zzcui implements Runnable {
   // $FF: synthetic field
   private zzcuf zzbHS;

   private zzcui(zzcuf var1) {
      this.zzbHS = var1;
      super();
   }

   @WorkerThread
   public final void run() {
      zzcuf.zza(this.zzbHS, 3);
      String var1 = zzcuf.zzd(this.zzbHS);
      zzcvk.zzaT((new StringBuilder(26 + String.valueOf(var1).length())).append("Container ").append(var1).append(" loading failed.").toString());
      if (zzcuf.zzi(this.zzbHS) != null) {
         Iterator var5 = zzcuf.zzi(this.zzbHS).iterator();

         while(var5.hasNext()) {
            zzcut var2;
            String var3;
            if ((var2 = (zzcut)var5.next()).zzCn()) {
               try {
                  zzcuf.zzj(this.zzbHS).logEventInternalNoInterceptor("app", var2.zzCk(), var2.zzCl(), var2.currentTimeMillis());
                  var3 = String.valueOf(var2.zzCk());
                  zzcvk.v((new StringBuilder(50 + String.valueOf(var3).length())).append("Logged event ").append(var3).append(" to Firebase (marked as passthrough).").toString());
               } catch (RemoteException var4) {
                  zzcup.zza("Error logging event with measurement proxy:", var4, zzcuf.zzk(this.zzbHS));
               }
            } else {
               var3 = String.valueOf(var2.zzCk());
               zzcvk.v((new StringBuilder(45 + String.valueOf(var3).length())).append("Discarded event ").append(var3).append(" (marked as non-passthrough).").toString());
            }
         }

         zzcuf.zza(this.zzbHS, (List)null);
      }

   }

   // $FF: synthetic method
   zzcui(zzcuf var1, zzcug var2) {
      this(var1);
   }
}
