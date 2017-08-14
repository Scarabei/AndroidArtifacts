package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;

public final class zzr extends zzed implements zzp {
   zzr(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
   }

   public final void remove() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final String getId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(2, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void setPosition(LatLng var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(3, var2);
   }

   public final LatLng getPosition() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      LatLng var3 = (LatLng)zzef.zza(var2 = this.zza(4, var1), LatLng.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setTitle(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(5, var2);
   }

   public final String getTitle() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(6, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void setSnippet(String var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeString(var1);
      this.zzb(7, var2);
   }

   public final String getSnippet() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(8, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void setDraggable(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(9, var2);
   }

   public final boolean isDraggable() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(10, var1));
      var2.recycle();
      return var3;
   }

   public final void showInfoWindow() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(11, var1);
   }

   public final void hideInfoWindow() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(12, var1);
   }

   public final boolean isInfoWindowShown() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(13, var1));
      var2.recycle();
      return var3;
   }

   public final void setVisible(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(14, var2);
   }

   public final boolean isVisible() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(15, var1));
      var2.recycle();
      return var3;
   }

   public final boolean zzj(zzp var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(16, var2));
      var3.recycle();
      return var4;
   }

   public final int hashCodeRemote() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(17, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void zzK(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(18, var2);
   }

   public final void setAnchor(float var1, float var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeFloat(var1);
      var3.writeFloat(var2);
      this.zzb(19, var3);
   }

   public final void setFlat(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(20, var2);
   }

   public final boolean isFlat() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(21, var1));
      var2.recycle();
      return var3;
   }

   public final void setRotation(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(22, var2);
   }

   public final float getRotation() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(23, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setInfoWindowAnchor(float var1, float var2) throws RemoteException {
      Parcel var3;
      (var3 = this.zzZ()).writeFloat(var1);
      var3.writeFloat(var2);
      this.zzb(24, var3);
   }

   public final void setAlpha(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(25, var2);
   }

   public final float getAlpha() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(26, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setZIndex(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(27, var2);
   }

   public final float getZIndex() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(28, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setTag(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(29, var2);
   }

   public final IObjectWrapper getTag() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(30, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
