package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzcmt extends zzee implements zzcms {
   public zzcmt() {
      this.attachInterface(this, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 2:
            zzcno var7 = (zzcno)zzef.zza(var2, zzcno.CREATOR);
            this.zza(var7);
            break;
         case 3:
            zzcnu var6 = (zzcnu)zzef.zza(var2, zzcnu.CREATOR);
            this.zza(var6);
            break;
         case 4:
            zzcnw var5 = (zzcnw)zzef.zza(var2, zzcnw.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
