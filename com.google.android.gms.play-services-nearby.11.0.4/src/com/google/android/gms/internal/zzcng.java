package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcng extends zzee implements zzcnf {
   public zzcng() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IPayloadListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            zzcoc var6 = (zzcoc)zzef.zza(var2, zzcoc.CREATOR);
            this.zza(var6);
            break;
         case 3:
            zzcoe var5 = (zzcoe)zzef.zza(var2, zzcoe.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
