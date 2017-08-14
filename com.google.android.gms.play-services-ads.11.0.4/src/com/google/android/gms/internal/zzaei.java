package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;

final class zzaei implements Runnable {
   // $FF: synthetic field
   private zzajm zzXg;
   // $FF: synthetic field
   private zzaeg zzXf;

   zzaei(zzaeg var1, zzajm var2) {
      this.zzXf = var1;
      this.zzXg = var2;
      super();
   }

   public final void run() {
      try {
         Map var1 = (Map)this.zzXg.get();
         this.zzXf.zzi(var1);
         if (this.zzXf.zzXa) {
            synchronized(zzaeg.zza(this.zzXf)) {
               zzaeg.zzb(this.zzXf).zzcsJ = Integer.valueOf(9);
            }
         }

         this.zzXf.send();
      } catch (ExecutionException | JSONException | InterruptedException var8) {
         String var4 = "Failed to get SafeBrowsing metadata";
         zzme var6 = zzmo.zzGe;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
            zzafr.zza(var4, var8);
         }

      }
   }
}
