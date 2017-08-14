package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzn extends zzee implements zzm {
   public zzn() {
      this.attachInterface(this, "com.google.android.gms.location.ILocationListener");
   }

   public static zzm zzZ(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzm)((var1 = var0.queryLocalInterface("com.google.android.gms.location.ILocationListener")) instanceof zzm ? (zzm)var1 : new zzo(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else if (var1 == 1) {
         Location var5 = (Location)zzef.zza(var2, Location.CREATOR);
         this.onLocationChanged(var5);
         return true;
      } else {
         return false;
      }
   }
}
