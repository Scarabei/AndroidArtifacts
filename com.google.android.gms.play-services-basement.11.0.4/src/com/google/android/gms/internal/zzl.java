package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzl extends Thread {
   private final BlockingQueue zzw;
   private final zzk zzx;
   private final zzb zzi;
   private final zzw zzj;
   private volatile boolean zzk = false;

   public zzl(BlockingQueue var1, zzk var2, zzb var3, zzw var4) {
      this.zzw = var1;
      this.zzx = var2;
      this.zzi = var3;
      this.zzj = var4;
   }

   public final void quit() {
      this.zzk = true;
      this.interrupt();
   }

   public final void run() {
      Process.setThreadPriority(10);

      while(true) {
         long var1;
         zzp var3;
         while(true) {
            var1 = SystemClock.elapsedRealtime();

            try {
               var3 = (zzp)this.zzw.take();
               break;
            } catch (InterruptedException var9) {
               if (this.zzk) {
                  return;
               }
            }
         }

         try {
            var3.zzb("network-queue-take");
            TrafficStats.setThreadStatsTag(var3.zzc());
            zzn var4 = this.zzx.zza(var3);
            var3.zzb("network-http-complete");
            if (var4.zzz && var3.zzl()) {
               var3.zzc("not-modified");
            } else {
               zzt var12 = var3.zza(var4);
               var3.zzb("network-parse-complete");
               if (var3.zzh() && var12.zzae != null) {
                  this.zzi.zza(var3.getUrl(), var12.zzae);
                  var3.zzb("network-cache-written");
               }

               var3.zzk();
               this.zzj.zza(var3, var12);
            }
         } catch (zzaa var10) {
            var10.zza(SystemClock.elapsedRealtime() - var1);
            this.zzj.zza(var3, var10);
         } catch (Exception var11) {
            zzab.zza(var11, "Unhandled exception %s", var11.toString());
            zzaa var5;
            (var5 = new zzaa(var11)).zza(SystemClock.elapsedRealtime() - var1);
            this.zzj.zza(var3, var5);
         }
      }
   }
}
