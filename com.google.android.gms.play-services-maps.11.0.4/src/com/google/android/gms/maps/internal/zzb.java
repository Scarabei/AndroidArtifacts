package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zzed implements ICameraUpdateFactoryDelegate {
   zzb(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
   }

   public final IObjectWrapper zoomIn() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(1, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final IObjectWrapper zoomOut() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(2, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }

   public final IObjectWrapper scrollBy(float var1, float var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeFloat(var1);
      var3.writeFloat(var2);
      Parcel var4;
      IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var4 = this.zza(3, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final IObjectWrapper zoomTo(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(4, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zoomBy(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(5, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper zoomByWithFocus(float var1, int var2, int var3) throws RemoteException {
      Parcel var4;
      (var4 = this.zzZ()).writeFloat(var1);
      var4.writeInt(var2);
      var4.writeInt(var3);
      Parcel var5;
      IObjectWrapper var6 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var5 = this.zza(6, var4)).readStrongBinder());
      var5.recycle();
      return var6;
   }

   public final IObjectWrapper newCameraPosition(CameraPosition var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(7, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper newLatLng(LatLng var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(8, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper newLatLngZoom(LatLng var1, float var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeFloat(var2);
      Parcel var4;
      IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var4 = this.zza(9, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final IObjectWrapper newLatLngBounds(LatLngBounds var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      Parcel var4;
      IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var4 = this.zza(10, var3)).readStrongBinder());
      var4.recycle();
      return var5;
   }

   public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds var1, int var2, int var3, int var4) throws RemoteException {
      Parcel var5;
      zzef.zza(var5 = this.zzZ(), var1);
      var5.writeInt(var2);
      var5.writeInt(var3);
      var5.writeInt(var4);
      Parcel var6;
      IObjectWrapper var7 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var6 = this.zza(11, var5)).readStrongBinder());
      var6.recycle();
      return var7;
   }
}
