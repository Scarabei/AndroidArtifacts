package com.google.android.gms.nearby.messages.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzcpn;
import com.google.android.gms.internal.zzcpq;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcpz;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzah extends com.google.android.gms.common.internal.zzz {
   private final zzcpz zzbze = new zzcpz();
   private final ClientAppContext zzbzf;
   private final int zzbyi;

   @TargetApi(14)
   zzah(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, com.google.android.gms.common.internal.zzq var5, MessagesOptions var6) {
      super(var1, var2, 62, var5, var3, var4);
      String var7 = var5.zzrq();
      int var8 = var1 instanceof Activity ? 1 : (var1 instanceof Application ? 2 : (var1 instanceof Service ? 3 : 0));
      if (var6 != null) {
         this.zzbzf = new ClientAppContext(var7, (String)null, false, (String)null, var8);
         this.zzbyi = var6.zzbyg;
      } else {
         this.zzbzf = new ClientAppContext(var7, (String)null, false, (String)null, var8);
         this.zzbyi = -1;
      }

      if (var8 == 1) {
         Activity var9 = (Activity)var1;
         Log.v("NearbyMessagesClient", String.format("Registering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", var9.getPackageName()));
         var9.getApplication().registerActivityLifecycleCallbacks(new zzaj(var9, this, (zzai)null));
      }

   }

   @NonNull
   protected final Bundle zzmo() {
      Bundle var1;
      (var1 = super.zzmo()).putInt("NearbyPermissions", this.zzbyi);
      var1.putParcelable("ClientAppContext", this.zzbzf);
      return var1;
   }

   @NonNull
   protected final String zzdb() {
      return "com.google.android.gms.nearby.messages.service.NearbyMessagesService.START";
   }

   @NonNull
   protected final String zzdc() {
      return "com.google.android.gms.nearby.messages.internal.INearbyMessagesService";
   }

   public final void disconnect() {
      try {
         this.zzbs(2);
      } catch (RemoteException var2) {
         Log.v("NearbyMessagesClient", String.format("Failed to emit CLIENT_DISCONNECTED from override of GmsClient#disconnect(): %s", var2));
      }

      this.zzbze.clear();
      super.disconnect();
   }

   final void zza(zzbdw var1, zzaf var2) throws RemoteException {
      zzbc var3 = new zzbc(var2, new zzcpq(var1), this.zzbzf);
      ((zzs)this.zzrf()).zza(var3);
   }

   final void zza(zzbdw var1, zzbdw var2, @Nullable zzbdw var3, SubscribeOptions var4, @Nullable byte[] var5) throws RemoteException {
      SubscribeRequest var6 = new SubscribeRequest(this.zzbze.zzh(var2), var4.getStrategy(), new zzcpq(var1), var4.getFilter(), (PendingIntent)null, (byte[])null, var3 == null ? null : new zzcpx(var3), var4.zzbyA);
      ((zzs)this.zzrf()).zza(var6);
   }

   final void zza(zzbdw var1, @Nullable zzbdw var2) throws RemoteException {
      if (var2 != null) {
         zzbe var3 = new zzbe(this.zzbze.zzh(var2), new zzcpq(var1), (PendingIntent)null);
         ((zzs)this.zzrf()).zza(var3);
         this.zzbze.zzi(var2);
      }
   }

   final void zzb(zzbdw var1, zzbdw var2) throws RemoteException {
      zzaz var3;
      (var3 = new zzaz(new zzcpq(var1), this.zzbze.zzh(var2))).zzbzu = true;
      ((zzs)this.zzrf()).zza(var3);
   }

   final void zzc(zzbdw var1, @Nullable zzbdw var2) throws RemoteException {
      if (var2 != null) {
         zzaz var3;
         (var3 = new zzaz(new zzcpq(var1), this.zzbze.zzh(var2))).zzbzu = false;
         ((zzs)this.zzrf()).zza(var3);
         this.zzbze.zzi(var2);
      }
   }

   final void zzbs(int var1) throws RemoteException {
      String var2;
      switch(var1) {
      case 1:
         var2 = "ACTIVITY_STOPPED";
         break;
      case 2:
         var2 = "CLIENT_DISCONNECTED";
         break;
      default:
         Log.w("NearbyMessagesClient", String.format("Received unknown/unforeseen client lifecycle event %d, can't do anything with it.", var1));
         return;
      }

      if (this.isConnected()) {
         zzj var3 = new zzj(var1);
         Log.d("NearbyMessagesClient", String.format("Emitting client lifecycle event %s", var2));
         ((zzs)this.zzrf()).zza(var3);
      } else {
         Log.d("NearbyMessagesClient", String.format("Failed to emit client lifecycle event %s due to GmsClient being disconnected", var2));
      }
   }

   @Nullable
   final zzbdw zza(GoogleApiClient var1, @Nullable MessageListener var2) {
      return this.zzbze.zzb(var1, var2).zzzX();
   }

   @Nullable
   final zzbdw zza(GoogleApiClient var1, @Nullable StatusCallback var2) {
      return this.zzbze.zzb(var1, var2).zzzX();
   }

   @Nullable
   final zzbdw zza(@Nullable MessageListener var1) {
      zzcpn var2;
      return (var2 = this.zzbze.zzE(var1)) == null ? null : var2.zzzX();
   }

   @Nullable
   final zzbdw zza(@Nullable StatusCallback var1) {
      zzcpn var2;
      return (var2 = this.zzbze.zzE(var1)) == null ? null : var2.zzzX();
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesService")) instanceof zzs ? (zzs)var3 : new zzt(var1));
      }
   }
}
