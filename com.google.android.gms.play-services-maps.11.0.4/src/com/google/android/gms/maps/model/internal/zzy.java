package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzy extends zzed implements zzw {
   zzy(IBinder var1) {
      super(var1, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
   }

   public final void remove() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(1, var1);
   }

   public final void clearTileCache() throws RemoteException {
      Parcel var1 = this.zzZ();
      this.zzb(2, var1);
   }

   public final String getId() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      String var3 = (var2 = this.zza(3, var1)).readString();
      var2.recycle();
      return var3;
   }

   public final void setZIndex(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(4, var2);
   }

   public final float getZIndex() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(5, var1)).readFloat();
      var2.recycle();
      return var3;
   }

   public final void setVisible(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(6, var2);
   }

   public final boolean isVisible() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(7, var1));
      var2.recycle();
      return var3;
   }

   public final boolean zza(zzw var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      boolean var4 = zzef.zza(var3 = this.zza(8, var2));
      var3.recycle();
      return var4;
   }

   public final int hashCodeRemote() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      int var3 = (var2 = this.zza(9, var1)).readInt();
      var2.recycle();
      return var3;
   }

   public final void setFadeIn(boolean var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      this.zzb(10, var2);
   }

   public final boolean getFadeIn() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      boolean var3 = zzef.zza(var2 = this.zza(11, var1));
      var2.recycle();
      return var3;
   }

   public final void setTransparency(float var1) throws RemoteException {
      Parcel var2;
      (var2 = this.zzZ()).writeFloat(var1);
      this.zzb(12, var2);
   }

   public final float getTransparency() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      float var3 = (var2 = this.zza(13, var1)).readFloat();
      var2.recycle();
      return var3;
   }
}
