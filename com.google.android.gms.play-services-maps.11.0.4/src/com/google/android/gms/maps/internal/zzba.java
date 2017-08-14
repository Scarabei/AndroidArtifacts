package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.PointOfInterest;

public abstract class zzba extends zzee implements zzaz {
   public zzba() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnPoiClickListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         PointOfInterest var5 = (PointOfInterest)zzef.zza(var2, PointOfInterest.CREATOR);
         this.zza(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
