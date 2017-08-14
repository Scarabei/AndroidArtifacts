package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public abstract class zzbk extends zzee implements zzbj {
   public zzbk() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnStreetViewPanoramaClickListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         StreetViewPanoramaOrientation var5 = (StreetViewPanoramaOrientation)zzef.zza(var2, StreetViewPanoramaOrientation.CREATOR);
         this.onStreetViewPanoramaClick(var5);
         var3.writeNoException();
         return true;
      } else {
         return false;
      }
   }
}
