package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.nearby.connection.Payload;
import java.io.IOException;

public final class zzckm extends zzz {
   private final long zzbwJ = (long)this.hashCode();
   private zzcom zzbwK;

   public zzckm(Context var1, Looper var2, zzq var3, ConnectionCallbacks var4, OnConnectionFailedListener var5) {
      super(var1, var2, 54, var3, var4, var5);
   }

   protected final Bundle zzmo() {
      Bundle var1;
      (var1 = new Bundle()).putLong("clientId", this.zzbwJ);
      return var1;
   }

   protected final String zzdb() {
      return "com.google.android.gms.nearby.connection.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.nearby.internal.connection.INearbyConnectionService";
   }

   public final void disconnect() {
      if (this.isConnected()) {
         try {
            ((zzcnd)this.zzrf()).zza(new zzckk());
         } catch (RemoteException var2) {
            Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", var2);
         }
      }

      if (this.zzbwK != null) {
         this.zzbwK.shutdown();
         this.zzbwK = null;
      }

      super.disconnect();
   }

   public final void zzj(zzbaz var1, String var2) throws RemoteException {
      ((zzcnd)this.zzrf()).zza(new zzcor((new zzclj(var1)).asBinder(), var2));
   }

   public final void zza(zzbaz var1, String[] var2, Payload var3, boolean var4) throws RemoteException {
      Pair var5;
      try {
         var5 = zzcoq.zza(var3);
      } catch (IOException var7) {
         var1.setResult(new Status(8013));
         return;
      }

      ((zzcnd)this.zzrf()).zza(new zzcov((new zzclj(var1)).asBinder(), var2, (zzcoo)var5.first, var4));
      if (var5.second != null) {
         Pair var6 = (Pair)var5.second;
         this.zzbwK.zza(var3.asStream().asInputStream(), new AutoCloseOutputStream((ParcelFileDescriptor)var6.first), new AutoCloseOutputStream((ParcelFileDescriptor)var6.second), var3.getId());
      }

   }

   // $FF: synthetic method
   protected final void zza(@NonNull IInterface var1) {
      zzcnd var3 = (zzcnd)var1;
      super.zza(var3);
      this.zzbwK = new zzcom();
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService")) instanceof zzcnd ? (zzcnd)var3 : new zzcne(var1));
      }
   }
}
