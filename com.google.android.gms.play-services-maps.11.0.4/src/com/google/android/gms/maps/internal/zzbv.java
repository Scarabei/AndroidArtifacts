package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzbv extends zzed implements IUiSettingsDelegate {
   zzbv(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
   }

   public final void setZoomControlsEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void setCompassEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void setMyLocationButtonEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void setScrollGesturesEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final void setZoomGesturesEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(5, var2);
   }

   public final void setTiltGesturesEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(6, var2);
   }

   public final void setRotateGesturesEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(7, var2);
   }

   public final void setAllGesturesEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(8, var2);
   }

   public final boolean isZoomControlsEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(9, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isCompassEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(10, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isMyLocationButtonEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isScrollGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(12, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isZoomGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(13, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isTiltGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(14, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isRotateGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(15, var1));
      var2.recycle();
      return var3;
   }

   public final void setIndoorLevelPickerEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(16, var2);
   }

   public final boolean isIndoorLevelPickerEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(17, var1));
      var2.recycle();
      return var3;
   }

   public final void setMapToolbarEnabled(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(18, var2);
   }

   public final boolean isMapToolbarEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(19, var1));
      var2.recycle();
      return var3;
   }
}
