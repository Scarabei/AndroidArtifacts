package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcmn extends zzee implements zzcmm {
   public zzcmn() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            zzcnq var6 = (zzcnq)zzef.zza(var2, zzcnq.CREATOR);
            this.zza(var6);
            break;
         case 3:
            zzcoi var5 = (zzcoi)zzef.zza(var2, zzcoi.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
