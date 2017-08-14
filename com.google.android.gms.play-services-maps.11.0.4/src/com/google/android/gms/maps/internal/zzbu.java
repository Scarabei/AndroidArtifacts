package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzbu extends zzed implements IStreetViewPanoramaViewDelegate {
   zzbu(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
   }

   public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(1, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate")) instanceof IStreetViewPanoramaDelegate ? (IStreetViewPanoramaDelegate)var5 : new zzbs(var4));
      var2.recycle();
      return (IStreetViewPanoramaDelegate)var3;
   }

   public final void onCreate(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void onResume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(3, var1);
   }

   public final void onPause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(4, var1);
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void onLowMemory() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void onSaveInstanceState(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      if ((var3 = this.zza(7, var2)).readInt() != 0) {
         var1.readFromParcel(var3);
      }

      var3.recycle();
   }

   public final IObjectWrapper getView() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(8, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void getStreetViewPanoramaAsync(zzbn var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }
}
