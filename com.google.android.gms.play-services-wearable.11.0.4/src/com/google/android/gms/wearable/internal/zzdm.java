package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import java.util.List;

public final class zzdm extends com.google.android.gms.internal.zzed implements zzdk {
   zzdm(IBinder var1) {
      super(var1, "com.google.android.gms.wearable.internal.IWearableListener");
   }

   public final void zzS(DataHolder var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(1, var2);
   }

   public final void zza(zzdx var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(2, var2);
   }

   public final void zza(zzeg var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(3, var2);
   }

   public final void zzb(zzeg var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(4, var2);
   }

   public final void onConnectedNodes(List var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeTypedList(var1);
      this.zzc(5, var2);
   }

   public final void zza(zzl var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(6, var2);
   }

   public final void zza(zzai var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(7, var2);
   }

   public final void zza(zzaa var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(8, var2);
   }

   public final void zza(zzi var1) throws RemoteException {
      Parcel var2;
      com.google.android.gms.internal.zzef.zza(var2 = this.zzZ(), var1);
      this.zzc(9, var2);
   }
}
