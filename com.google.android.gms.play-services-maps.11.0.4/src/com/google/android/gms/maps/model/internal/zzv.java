package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzv extends zzed implements IPolylineDelegate {
   zzv(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
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

   public final void setPoints(List var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeTypedList(var1);
      this.zzb(3, var2);
   }

   public final List getPoints() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = (var2 = this.zza(4, var1)).createTypedArrayList(LatLng.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setWidth(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(5, var2);
   }

   public final float getWidth() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(6, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setColor(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(7, var2);
   }

   public final int getColor() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(8, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void setZIndex(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(9, var2);
   }

   public final float getZIndex() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(10, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setVisible(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(11, var2);
   }

   public final boolean isVisible() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(12, var1));
      var2.recycle();
      return var3;
   }

   public final void setGeodesic(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(13, var2);
   }

   public final boolean isGeodesic() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(14, var1));
      var2.recycle();
      return var3;
   }

   public final boolean equalsRemote(IPolylineDelegate var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(15, var2));
      var3.recycle();
      return var4;
   }

   public final int hashCodeRemote() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(16, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void setClickable(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(17, var2);
   }

   public final boolean isClickable() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(18, var1));
      var2.recycle();
      return var3;
   }

   public final void setStartCap(Cap var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(19, var2);
   }

   public final Cap getStartCap() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Cap var3 = (Cap)zzef.zza(var2 = this.zza(20, var1), Cap.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setEndCap(Cap var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(21, var2);
   }

   public final Cap getEndCap() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      Cap var3 = (Cap)zzef.zza(var2 = this.zza(22, var1), Cap.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setJointType(int var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeInt(var1);
      this.zzb(23, var2);
   }

   public final int getJointType() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(24, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void setPattern(List var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeTypedList(var1);
      this.zzb(25, var2);
   }

   public final List getPattern() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      ArrayList var3 = (var2 = this.zza(26, var1)).createTypedArrayList(PatternItem.CREATOR);
      var2.recycle();
      return var3;
   }

   public final void setTag(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(27, var2);
   }

   public final IObjectWrapper getTag() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      IObjectWrapper var3 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var2 = this.zza(28, var1)).readStrongBinder());
      var2.recycle();
      return var3;
   }
}
