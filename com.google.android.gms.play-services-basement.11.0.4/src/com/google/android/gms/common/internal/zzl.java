package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

public final class zzl implements ServiceConnection {
   private final int zzaHh;
   // $FF: synthetic field
   private zzd zzaHe;

   public zzl(zzd var1, int var2) {
      this.zzaHe = var1;
      super();
      this.zzaHh = var2;
   }

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      if (var2 == null) {
         zzd.zza(this.zzaHe, 16);
      } else {
         synchronized(zzd.zza(this.zzaHe)) {
            IInterface var6;
            zzd.zza((zzd)this.zzaHe, (zzaw)(var2 == null ? null : ((var6 = var2.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker")) != null && var6 instanceof zzaw ? (zzaw)var6 : new zzax(var2))));
         }

         this.zzaHe.zza(0, (Bundle)null, this.zzaHh);
      }
   }

   public final void onServiceDisconnected(ComponentName var1) {
      synchronized(zzd.zza(this.zzaHe)) {
         zzd.zza((zzd)this.zzaHe, (zzaw)null);
      }

      this.zzaHe.mHandler.sendMessage(this.zzaHe.mHandler.obtainMessage(6, this.zzaHh, 1));
   }
}
