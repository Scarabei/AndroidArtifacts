package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.BroadcastReceiver.PendingResult;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class zzh implements ServiceConnection {
   private final Context zzqD;
   private final Intent zzckp;
   private final ScheduledExecutorService zzckq;
   private final Queue zzckr;
   private zzf zzcks;
   private boolean zzckt;

   public zzh(Context var1, String var2) {
      this(var1, var2, new ScheduledThreadPoolExecutor(0));
   }

   @VisibleForTesting
   private zzh(Context var1, String var2, ScheduledExecutorService var3) {
      this.zzckr = new LinkedList();
      this.zzckt = false;
      this.zzqD = var1.getApplicationContext();
      this.zzckp = (new Intent(var2)).setPackage(this.zzqD.getPackageName());
      this.zzckq = var3;
   }

   public final synchronized void zza(Intent var1, PendingResult var2) {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
         Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
      }

      this.zzckr.add(new zzd(var1, var2, this.zzckq));
      this.zzJO();
   }

   private final synchronized void zzJO() {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
         Log.d("EnhancedIntentService", "flush queue called");
      }

      while(!this.zzckr.isEmpty()) {
         if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "found intent to be delivered");
         }

         if (this.zzcks == null || !this.zzcks.isBinderAlive()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
               boolean var3 = !this.zzckt;
               Log.d("EnhancedIntentService", (new StringBuilder(39)).append("binder is dead. start connection? ").append(var3).toString());
            }

            if (!this.zzckt) {
               this.zzckt = true;

               try {
                  if (zza.zzrU().zza(this.zzqD, this.zzckp, this, 65)) {
                     return;
                  }

                  Log.e("EnhancedIntentService", "binding to the service failed");
               } catch (SecurityException var2) {
                  Log.e("EnhancedIntentService", "Exception while binding the service", var2);
               }

               while(!this.zzckr.isEmpty()) {
                  ((zzd)this.zzckr.poll()).finish();
               }
            }

            return;
         }

         if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
         }

         zzd var1 = (zzd)this.zzckr.poll();
         this.zzcks.zza(var1);
      }

   }

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      synchronized(this) {
         this.zzckt = false;
         this.zzcks = (zzf)var2;
         if (Log.isLoggable("EnhancedIntentService", 3)) {
            String var4 = String.valueOf(var1);
            Log.d("EnhancedIntentService", (new StringBuilder(20 + String.valueOf(var4).length())).append("onServiceConnected: ").append(var4).toString());
         }

         this.zzJO();
      }
   }

   public final void onServiceDisconnected(ComponentName var1) {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
         String var2 = String.valueOf(var1);
         Log.d("EnhancedIntentService", (new StringBuilder(23 + String.valueOf(var2).length())).append("onServiceDisconnected: ").append(var2).toString());
      }

      this.zzJO();
   }
}
