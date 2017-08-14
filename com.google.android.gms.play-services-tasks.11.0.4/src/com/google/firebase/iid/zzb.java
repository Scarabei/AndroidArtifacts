package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
   @VisibleForTesting
   final ExecutorService zzbrV = Executors.newSingleThreadExecutor();
   private Binder zzckd;
   private final Object mLock = new Object();
   private int zzcke;
   private int zzckf = 0;

   public final synchronized IBinder onBind(Intent var1) {
      if (Log.isLoggable("EnhancedIntentService", 3)) {
         Log.d("EnhancedIntentService", "Service received bind request");
      }

      if (this.zzckd == null) {
         this.zzckd = new zzf(this);
      }

      return this.zzckd;
   }

   public final int onStartCommand(Intent var1, int var2, int var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         this.zzcke = var3;
         ++this.zzckf;
      }

      Intent var7;
      if ((var7 = this.zzn(var1)) == null) {
         this.zzm(var1);
         return 2;
      } else if (this.zzo(var7)) {
         this.zzm(var1);
         return 2;
      } else {
         this.zzbrV.execute(new zzc(this, var7, var1));
         return 3;
      }
   }

   private final void zzm(Intent var1) {
      if (var1 != null) {
         WakefulBroadcastReceiver.completeWakefulIntent(var1);
      }

      Object var2 = this.mLock;
      synchronized(this.mLock) {
         --this.zzckf;
         if (this.zzckf == 0) {
            int var4 = this.zzcke;
            this.stopSelfResult(this.zzcke);
         }

      }
   }

   protected Intent zzn(Intent var1) {
      return var1;
   }

   public boolean zzo(Intent var1) {
      return false;
   }

   public abstract void handleIntent(Intent var1);

   // $FF: synthetic method
   static void zza(zzb var0, Intent var1) {
      var0.zzm(var1);
   }
}
