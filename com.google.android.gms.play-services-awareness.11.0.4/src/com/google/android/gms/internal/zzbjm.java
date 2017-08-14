package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzbjm extends zzee implements zzbjl {
   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            zzbjh var6 = (zzbjh)zzef.zza(var2, zzbjh.CREATOR);
            this.zza(var6);
            break;
         case 2:
            zzbjd var5 = (zzbjd)zzef.zza(var2, zzbjd.CREATOR);
            this.zza(var5);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
