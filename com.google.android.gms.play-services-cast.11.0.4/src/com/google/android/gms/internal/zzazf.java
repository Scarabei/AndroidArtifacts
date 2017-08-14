package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.os.IBinder.DeathRecipient;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;

public final class zzazf extends zzz implements DeathRecipient {
   private static final zzayo zzapq = new zzayo("CastRemoteDisplayClientImpl");
   private CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzazc;
   private CastDevice zzaoX;
   private Bundle zzazd;

   public zzazf(Context var1, Looper var2, zzq var3, CastDevice var4, Bundle var5, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks var6, ConnectionCallbacks var7, OnConnectionFailedListener var8) {
      super(var1, var2, 83, var3, var7, var8);
      zzapq.zzb("instance created");
      this.zzazc = var6;
      this.zzaoX = var4;
      this.zzazd = var5;
   }

   public final void disconnect() {
      zzapq.zzb("disconnect");
      this.zzazc = null;
      this.zzaoX = null;

      try {
         ((zzazj)this.zzrf()).disconnect();
         return;
      } catch (IllegalStateException | RemoteException var4) {
         ;
      } finally {
         super.disconnect();
      }

   }

   public final void zza(zzazh var1, zzazl var2, String var3) throws RemoteException {
      zzapq.zzb("startRemoteDisplay");
      zzazg var4 = new zzazg(this, var2);
      ((zzazj)this.zzrf()).zza(var1, var4, this.zzaoX.getDeviceId(), var3, this.zzazd);
   }

   public final void zza(zzazh var1) throws RemoteException {
      zzapq.zzb("stopRemoteDisplay");
      ((zzazj)this.zzrf()).zza(var1);
   }

   public final void binderDied() {
   }

   protected final String zzdc() {
      return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
   }

   protected final String zzdb() {
      return "com.google.android.gms.cast.remote_display.service.START";
   }

   // $FF: synthetic method
   public final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService")) instanceof zzazj ? (zzazj)var3 : new zzazk(var1));
      }
   }

   // $FF: synthetic method
   static zzayo zzoQ() {
      return zzapq;
   }

   // $FF: synthetic method
   static CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzb(zzazf var0) {
      return var0.zzazc;
   }
}
