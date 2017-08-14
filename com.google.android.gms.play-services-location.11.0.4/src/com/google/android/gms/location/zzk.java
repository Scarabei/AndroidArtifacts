package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzk extends zzee implements zzj {
   public zzk() {
      this.attachInterface(this, "com.google.android.gms.location.ILocationCallback");
   }

   public static zzj zzY(IBinder var0) {
      if (var0 == null) {
         return null;
      } else {
         IInterface var1;
         return (zzj)((var1 = var0.queryLocalInterface("com.google.android.gms.location.ILocationCallback")) instanceof zzj ? (zzj)var1 : new zzl(var0));
      }
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            LocationResult var6 = (LocationResult)zzef.zza(var2, LocationResult.CREATOR);
            this.onLocationResult(var6);
            break;
         case 2:
            LocationAvailability var5 = (LocationAvailability)zzef.zza(var2, LocationAvailability.CREATOR);
            this.onLocationAvailability(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
