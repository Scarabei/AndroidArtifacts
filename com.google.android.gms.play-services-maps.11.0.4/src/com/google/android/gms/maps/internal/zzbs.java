package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public final class zzbs extends zzed implements IStreetViewPanoramaDelegate {
   zzbs(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
   }

   public final void enableZoom(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(1, var2);
   }

   public final void enablePanning(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(2, var2);
   }

   public final void enableUserNavigation(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final void enableStreetNames(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(4, var2);
   }

   public final boolean isZoomGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(5, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isPanningGesturesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(6, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isUserNavigationEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(7, var1));
      var2.recycle();
      return var3;
   }

   public final boolean isStreetNamesEnabled() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(8, var1));
      var2.recycle();
      return var3;
   }

   public final void animateTo(StreetViewPanoramaCamera var1, long var2) throws RemoteException {
      Parcel var4;
      zzef.zza(var4 = this.zzZ(), var1);
      var4.writeLong(var2);
      this.zzb(9, var4);
   }

   public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      StreetViewPanoramaCamera var3 = (StreetViewPanoramaCamera)zzef.zza(var2 = this.zza(10, var1), StreetViewPanoramaCamera.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setPositionWithID(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(11, var2);
   }

   public final void setPosition(LatLng var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(12, var2);
   }

   public final void setPositionWithRadius(LatLng var1, int var2) throws RemoteException {
      Parcel var3;
      zzef.zza(var3 = this.zzZ(), var1);
      var3.writeInt(var2);
      this.zzb(13, var3);
   }

   public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      StreetViewPanoramaLocation var3 = (StreetViewPanoramaLocation)zzef.zza(var2 = this.zza(14, var1), StreetViewPanoramaLocation.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setOnStreetViewPanoramaChangeListener(zzbh var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(15, var2);
   }

   public final void setOnStreetViewPanoramaCameraChangeListener(zzbf var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(16, var2);
   }

   public final void setOnStreetViewPanoramaClickListener(zzbj var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(17, var2);
   }

   public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      StreetViewPanoramaOrientation var4 = (StreetViewPanoramaOrientation)zzef.zza(var3 = this.zza(18, var2), StreetViewPanoramaOrientation.CREATOR);
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(19, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final void setOnStreetViewPanoramaLongClickListener(zzbl var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(20, var2);
   }
}
