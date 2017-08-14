package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
   private static final boolean DEBUG;
   private final BlockingQueue zzg;
   private final BlockingQueue zzh;
   private final zzb zzi;
   private final zzw zzj;
   private volatile boolean zzk = false;

   public zzd(BlockingQueue var1, BlockingQueue var2, zzb var3, zzw var4) {
      this.zzg = var1;
      this.zzh = var2;
      this.zzi = var3;
      this.zzj = var4;
   }

   public final void quit() {
      this.zzk = true;
      this.interrupt();
   }

   public final void run() {
      if (DEBUG) {
         zzab.zza("start new dispatcher");
      }

      Process.setThreadPriority(10);
      this.zzi.initialize();

      while(true) {
         while(true) {
            while(true) {
               try {
                  while(true) {
                     zzp var1;
                     (var1 = (zzp)this.zzg.take()).zzb("cache-queue-take");
                     zzc var2;
                     if ((var2 = this.zzi.zza(var1.getUrl())) == null) {
                        var1.zzb("cache-miss");
                        this.zzh.put(var1);
                     } else if (var2.zzd < System.currentTimeMillis()) {
                        var1.zzb("cache-hit-expired");
                        var1.zza(var2);
                        this.zzh.put(var1);
                     } else {
                        var1.zzb("cache-hit");
                        zzt var3 = var1.zza(new zzn(var2.data, var2.zzf));
                        var1.zzb("cache-hit-parsed");
                        if (var2.zze < System.currentTimeMillis()) {
                           var1.zzb("cache-hit-refresh-needed");
                           var1.zza(var2);
                           var3.zzag = true;
                           this.zzj.zza(var1, var3, new zze(this, var1));
                        } else {
                           this.zzj.zza(var1, var3);
                        }
                     }
                  }
               } catch (InterruptedException var4) {
                  if (this.zzk) {
                     return;
                  }
               }
            }
         }
      }
   }

   // $FF: synthetic method
   static BlockingQueue zza(zzd var0) {
      return var0.zzh;
   }

   static {
      DEBUG = zzab.DEBUG;
   }
}
