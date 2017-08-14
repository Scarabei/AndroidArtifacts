package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzaa;

public final class zzcda extends zzed implements zzccz {
   zzcda(IBinder var1) {
      super(var1, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
   }

   public final void zza(GeofencingRequest var1, PendingIntent var2, zzccx var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(57, var4);
   }

   public final void zza(zzaa var1, zzccx var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      this.zzb(74, var3);
   }

   public final void zza(long var1, boolean var3, PendingIntent var4) throws RemoteException {
      Parcel var5;
      (var5 = this.zzZ()).writeLong(var1);
      zzef.zza(var5, true);
      zzef.zza(var5, var4);
      this.zzb(5, var5);
   }

   public final void zzc(PendingIntent var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(6, var2);
   }

   public final void zza(zzcdp var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(59, var2);
   }

   public final void zzai(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12, var2);
   }

   public final void zzc(Location var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(13, var2);
   }

   public final Location zzdv(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      Location var4 = (Location)zzef.zza(var3 = this.zza(21, var2), Location.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zza(zzccu var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(67, var2);
   }

   public final LocationAvailability zzdw(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      Parcel var3;
      LocationAvailability var4 = (LocationAvailability)zzef.zza(var3 = this.zza(34, var2), LocationAvailability.CREATOR);
      var3.recycle();
      return var4;
   }

   public final void zza(LocationSettingsRequest var1, zzcdb var2, String var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      var4.writeString(var3);
      this.zzb(63, var4);
   }
}
