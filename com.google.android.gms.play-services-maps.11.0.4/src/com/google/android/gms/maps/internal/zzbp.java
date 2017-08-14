package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbp extends zzed implements IProjectionDelegate {
   zzbp(IBinder var1) {
      super(var1, "com.google.android.gms.maps.internal.IProjectionDelegate");
   }

   public final LatLng fromScreenLocation(IObjectWrapper var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      LatLng var4 = (LatLng)zzef.zza(var3 = this.zza(1, var2), LatLng.CREATOR);
      var3.recycle();
      return var4;
   }

   public final IObjectWrapper toScreenLocation(LatLng var1) throws RemoteException {
      Parcel var2;
      zzef.zza(var2 = this.zzZ(), var1);
      Parcel var3;
      IObjectWrapper var4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM((var3 = this.zza(2, var2)).readStrongBinder());
      var3.recycle();
      return var4;
   }

   public final VisibleRegion getVisibleRegion() throws RemoteException {
      Parcel var1 = this.zzZ();
      Parcel var2;
      VisibleRegion var3 = (VisibleRegion)zzef.zza(var2 = this.zza(3, var1), VisibleRegion.CREATOR);
      var2.recycle();
      return var3;
   }
}
