package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj extends zzed implements IMapFragmentDelegate {
   zzj(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
   }

   public final IGoogleMapDelegate getMap() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(1, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate")) instanceof IGoogleMapDelegate ? (IGoogleMapDelegate)var5 : new zzg(var4));
      var2.recycle();
      return (IGoogleMapDelegate)var3;
   }

   public final void onInflate(IObjectWrapper var1, GoogleMapOptions var2, Bundle var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      this.zzb(2, var4);
   }

   public final void onCreate(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final IObjectWrapper onCreateView(IObjectWrapper var1, IObjectWrapper var2, Bundle var3) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      zzef.zza(var4, var2);
      zzef.zza(var4, var3);
      Parcel var5;
      IObjectWrapper var6 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var5 = this.zza(4, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final void onResume() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(5, var1);
   }

   public final void onPause() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(6, var1);
   }

   public final void onDestroyView() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(7, var1);
   }

   public final void onDestroy() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(8, var1);
   }

   public final void onLowMemory() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(9, var1);
   }

   public final void onSaveInstanceState(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      if ((var3 = this.zza(10, var2)).readInt() != 0) {
         var1.readFromParcel(var3);
      }

      var3.recycle();
   }

   public final boolean isReady() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final void getMapAsync(zzap var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12, var2);
   }

   public final void onEnterAmbient(Bundle var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(13, var2);
   }

   public final void onExitAmbient() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(14, var1);
   }

   public final void onStart() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(15, var1);
   }

   public final void onStop() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(16, var1);
   }
}
