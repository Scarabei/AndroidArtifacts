package com.google.android.gms.location.places.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public final class zzs extends zzed implements zzr {
   zzs(IBinder var1) {
      super(var1, "com.google.android.gms.location.places.internal.IGooglePlacesService");
   }

   public final void zza(List var1, zzat var2, zzv var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeStringList(var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(17, var4);
   }

   public final void zza(String var1, LatLngBounds var2, AutocompleteFilter var3, zzat var4, zzv var5) throws RemoteException {
      Parcel var6;
      (var6 = this.zzZ()).writeString(var1);
      zzef.zza(var6, var2);
      zzef.zza(var6, var3);
      zzef.zza(var6, var4);
      zzef.zza(var6, var5);
      this.zzb(13, var6);
   }

   public final void zza(AddPlaceRequest var1, zzat var2, zzv var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(14, var4);
   }

   public final void zza(String var1, zzat var2, zzt var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeString(var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(19, var4);
   }

   public final void zza(String var1, int var2, int var3, int var4, zzat var5, zzt var6) throws RemoteException {
      Parcel var7;
      (var7 = this.zzZ()).writeString(var1);
      var7.writeInt(var2);
      var7.writeInt(var3);
      var7.writeInt(var4);
      zzef.zza(var7, var5);
      zzef.zza(var7, var6);
      this.zzb(20, var7);
   }
}
