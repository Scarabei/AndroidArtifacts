package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzaa;

public final class zzcdj extends zzcbx {
   private final zzcdd zzbiS;

   public zzcdj(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, String var5) {
      this(var1, var2, var3, var4, var5, zzq.zzaA(var1));
   }

   public zzcdj(Context var1, Looper var2, ConnectionCallbacks var3, OnConnectionFailedListener var4, String var5, zzq var6) {
      super(var1, var2, var3, var4, var5, var6);
      this.zzbiS = new zzcdd(var1, this.zzbiB);
   }

   public final void disconnect() {
      zzcdd var1 = this.zzbiS;
      synchronized(this.zzbiS) {
         if (this.isConnected()) {
            try {
               this.zzbiS.removeAllListeners();
               this.zzbiS.zzvR();
            } catch (Exception var4) {
               Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", var4);
            }
         }

         super.disconnect();
      }
   }

   public final void zza(long var1, PendingIntent var3) throws RemoteException {
      this.zzre();
      zzbo.zzu(var3);
      zzbo.zzb(var1 >= 0L, "detectionIntervalMillis must be >= 0");
      ((zzccz)this.zzrf()).zza(var1, true, var3);
   }

   public final void zzc(PendingIntent var1) throws RemoteException {
      this.zzre();
      zzbo.zzu(var1);
      ((zzccz)this.zzrf()).zzc(var1);
   }

   public final void zza(GeofencingRequest var1, PendingIntent var2, zzbaz var3) throws RemoteException {
      this.zzre();
      zzbo.zzb(var1, "geofencingRequest can't be null.");
      zzbo.zzb(var2, "PendingIntent must be specified.");
      zzbo.zzb(var3, "ResultHolder not provided.");
      zzcdk var4 = new zzcdk(var3);
      ((zzccz)this.zzrf()).zza((GeofencingRequest)var1, (PendingIntent)var2, (zzccx)var4);
   }

   public final void zza(zzaa var1, zzbaz var2) throws RemoteException {
      this.zzre();
      zzbo.zzb(var1, "removeGeofencingRequest can't be null.");
      zzbo.zzb(var2, "ResultHolder not provided.");
      zzcdl var3 = new zzcdl(var2);
      ((zzccz)this.zzrf()).zza(var1, var3);
   }

   public final Location getLastLocation() {
      return this.zzbiS.getLastLocation();
   }

   public final LocationAvailability zzvQ() {
      return this.zzbiS.zzvQ();
   }

   public final void zza(zzcdn var1, zzbdw var2, zzccu var3) throws RemoteException {
      zzcdd var4 = this.zzbiS;
      synchronized(this.zzbiS) {
         this.zzbiS.zza(var1, var2, var3);
      }
   }

   public final void zza(LocationRequest var1, zzbdw var2, zzccu var3) throws RemoteException {
      zzcdd var4 = this.zzbiS;
      synchronized(this.zzbiS) {
         this.zzbiS.zza(var1, var2, var3);
      }
   }

   public final void zza(LocationRequest var1, PendingIntent var2, zzccu var3) throws RemoteException {
      this.zzbiS.zza(var1, var2, var3);
   }

   public final void zza(zzbdy var1, zzccu var2) throws RemoteException {
      this.zzbiS.zza(var1, var2);
   }

   public final void zza(PendingIntent var1, zzccu var2) throws RemoteException {
      this.zzbiS.zza(var1, var2);
   }

   public final void zzb(zzbdy var1, zzccu var2) throws RemoteException {
      this.zzbiS.zzb(var1, var2);
   }

   public final void zzai(boolean var1) throws RemoteException {
      this.zzbiS.zzai(var1);
   }

   public final void zzc(Location var1) throws RemoteException {
      this.zzbiS.zzc(var1);
   }

   public final void zza(zzccu var1) throws RemoteException {
      this.zzbiS.zza(var1);
   }

   public final void zza(LocationSettingsRequest var1, zzbaz var2, String var3) throws RemoteException {
      this.zzre();
      zzbo.zzb(var1 != null, "locationSettingsRequest can't be null nor empty.");
      zzbo.zzb(var2 != null, "listener can't be null.");
      zzcdm var4 = new zzcdm(var2);
      ((zzccz)this.zzrf()).zza((LocationSettingsRequest)var1, (zzcdb)var4, (String)var3);
   }
}
