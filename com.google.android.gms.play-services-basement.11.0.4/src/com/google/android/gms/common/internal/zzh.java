package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

final class zzh extends Handler {
   // $FF: synthetic field
   private zzd zzaHe;

   public zzh(zzd var1, Looper var2) {
      this.zzaHe = var1;
      super(var2);
   }

   public final void handleMessage(Message var1) {
      if (this.zzaHe.zzaHb.get() != var1.arg1) {
         if (zzb(var1)) {
            zza(var1);
         }

      } else if ((var1.what == 1 || var1.what == 7 || var1.what == 4 || var1.what == 5) && !this.zzaHe.isConnecting()) {
         zza(var1);
      } else {
         ConnectionResult var5;
         if (var1.what == 4) {
            zzd.zza(this.zzaHe, new ConnectionResult(var1.arg2));
            if (zzd.zzb(this.zzaHe) && !zzd.zzc(this.zzaHe)) {
               zzd.zza((zzd)this.zzaHe, 3, (IInterface)null);
            } else {
               var5 = zzd.zzd(this.zzaHe) != null ? zzd.zzd(this.zzaHe) : new ConnectionResult(8);
               this.zzaHe.zzaGQ.zzf(var5);
               this.zzaHe.onConnectionFailed(var5);
            }
         } else if (var1.what == 5) {
            var5 = zzd.zzd(this.zzaHe) != null ? zzd.zzd(this.zzaHe) : new ConnectionResult(8);
            this.zzaHe.zzaGQ.zzf(var5);
            this.zzaHe.onConnectionFailed(var5);
         } else if (var1.what == 3) {
            PendingIntent var4 = var1.obj instanceof PendingIntent ? (PendingIntent)var1.obj : null;
            ConnectionResult var3 = new ConnectionResult(var1.arg2, var4);
            this.zzaHe.zzaGQ.zzf(var3);
            this.zzaHe.onConnectionFailed(var3);
         } else if (var1.what == 6) {
            zzd.zza((zzd)this.zzaHe, 5, (IInterface)null);
            if (zzd.zze(this.zzaHe) != null) {
               zzd.zze(this.zzaHe).onConnectionSuspended(var1.arg2);
            }

            this.zzaHe.onConnectionSuspended(var1.arg2);
            zzd.zza(this.zzaHe, 5, 1, (IInterface)null);
         } else if (var1.what == 2 && !this.zzaHe.isConnected()) {
            zza(var1);
         } else if (zzb(var1)) {
            ((zzi)var1.obj).zzrk();
         } else {
            int var2 = var1.what;
            Log.wtf("GmsClient", (new StringBuilder(45)).append("Don't know how to handle message: ").append(var2).toString(), new Exception());
         }
      }
   }

   private static void zza(Message var0) {
      ((zzi)var0.obj).unregister();
   }

   private static boolean zzb(Message var0) {
      return var0.what == 2 || var0.what == 1 || var0.what == 7;
   }
}
