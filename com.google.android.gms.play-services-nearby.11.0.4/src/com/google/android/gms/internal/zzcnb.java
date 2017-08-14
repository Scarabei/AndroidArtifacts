package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcnb extends zzee implements zzcna {
   public zzcnb() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            zzcny var7 = (zzcny)zzef.zza(var2, zzcny.CREATOR);
            this.zza(var7);
            break;
         case 3:
            zzcoa var6 = (zzcoa)zzef.zza(var2, zzcoa.CREATOR);
            this.zza(var6);
            break;
         case 4:
            zzcok var5 = (zzcok)zzef.zza(var2, zzcok.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
