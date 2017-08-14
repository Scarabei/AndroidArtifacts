package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

public final class zzf extends zzed implements zze {
   zzf(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.ICreator");
   }

   public final IMapFragmentDelegate zzH(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IBinder var5;
      IInterface var6;
      Object var4 = (var5 = (var3 = this.zza(2, var2)).readStrongBinder()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate")) instanceof IMapFragmentDelegate ? (IMapFragmentDelegate)var6 : new zzj(var5));
      var3.recycle();
      return (IMapFragmentDelegate)var4;
   }

   public final IMapViewDelegate zza(IObjectWrapper var1, GoogleMapOptions var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var6;
      IInterface var7;
      Object var5 = (var6 = (var4 = this.zza(3, var3)).readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate")) instanceof IMapViewDelegate ? (IMapViewDelegate)var7 : new zzk(var6));
      var4.recycle();
      return (IMapViewDelegate)var5;
   }

   public final ICameraUpdateFactoryDelegate zzwh() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IBinder var4;
      IInterface var5;
      Object var3 = (var4 = (var2 = this.zza(4, var1)).readStrongBinder()) == null ? null : ((var5 = var4.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate")) instanceof ICameraUpdateFactoryDelegate ? (ICameraUpdateFactoryDelegate)var5 : new zzb(var4));
      var2.recycle();
      return (ICameraUpdateFactoryDelegate)var3;
   }

   public final com.google.android.gms.maps.model.internal.zza zzwi() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      com.google.android.gms.maps.model.internal.zza var3 = com.google.android.gms.maps.model.internal.zzb.zzaa((var2 = this.zza(5, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final void zzi(IObjectWrapper var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(6, var3);
   }

   public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper var1, StreetViewPanoramaOptions var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      zzef.zza(var3, var2);
      Parcel var4;
      IBinder var6;
      IInterface var7;
      Object var5 = (var6 = (var4 = this.zza(7, var3)).readStrongBinder()) == null ? null : ((var7 = var6.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate")) instanceof IStreetViewPanoramaViewDelegate ? (IStreetViewPanoramaViewDelegate)var7 : new zzbu(var6));
      var4.recycle();
      return (IStreetViewPanoramaViewDelegate)var5;
   }

   public final IStreetViewPanoramaFragmentDelegate zzI(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IBinder var5;
      IInterface var6;
      Object var4 = (var5 = (var3 = this.zza(8, var2)).readStrongBinder()) == null ? null : ((var6 = var5.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate")) instanceof IStreetViewPanoramaFragmentDelegate ? (IStreetViewPanoramaFragmentDelegate)var6 : new zzbt(var5));
      var3.recycle();
      return (IStreetViewPanoramaFragmentDelegate)var4;
   }
}
