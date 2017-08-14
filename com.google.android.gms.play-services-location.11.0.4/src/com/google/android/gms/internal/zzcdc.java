package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;

public abstract class zzcdc extends zzee implements zzcdb {
   public zzcdc() {
      this.attachInterface(this, "com.google.android.gms.location.internal.ISettingsCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         LocationSettingsResult var5 = (LocationSettingsResult)zzef.zza(var2, LocationSettingsResult.CREATOR);
         this.zza(var5);
         return true;
      } else {
         return false;
      }
   }
}
