package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcmq extends zzee implements zzcmp {
   public zzcmq() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            zzcoc var7 = (zzcoc)zzef.zza(var2, zzcoc.CREATOR);
            this.zza(var7);
            break;
         case 3:
            zzcnw var6 = (zzcnw)zzef.zza(var2, zzcnw.CREATOR);
            this.zza(var6);
            break;
         case 4:
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
