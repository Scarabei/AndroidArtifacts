package com.google.android.gms.ads.internal;

import android.os.Debug;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

final class zzb extends TimerTask {
   // $FF: synthetic field
   private CountDownLatch zzsT;
   // $FF: synthetic field
   private Timer zzsU;
   // $FF: synthetic field
   private zza zzsV;

   zzb(zza var1, CountDownLatch var2, Timer var3) {
      this.zzsV = var1;
      this.zzsT = var2;
      this.zzsU = var3;
      super();
   }

   public final void run() {
      zzme var4 = zzmo.zzFG;
      if ((long)((Integer)zzbs.zzbL().zzd(var4)).intValue() != this.zzsT.getCount()) {
         zzafr.zzaC("Stopping method tracing");
         Debug.stopMethodTracing();
         if (this.zzsT.getCount() == 0L) {
            this.zzsU.cancel();
            return;
         }
      }

      String var1 = String.valueOf(this.zzsV.zzsP.zzqD.getPackageName()).concat("_adsTrace_");

      try {
         zzafr.zzaC("Starting method tracing");
         this.zzsT.countDown();
         long var2 = zzbs.zzbF().currentTimeMillis();
         String var10000 = (new StringBuilder(20 + String.valueOf(var1).length())).append(var1).append(var2).toString();
         var4 = zzmo.zzFH;
         Debug.startMethodTracing(var10000, ((Integer)zzbs.zzbL().zzd(var4)).intValue());
      } catch (Exception var5) {
         zzafr.zzc("Exception occurred while starting method tracing.", var5);
      }
   }
}
