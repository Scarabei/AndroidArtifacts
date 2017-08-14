package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzav extends zzee implements zzau {
   public zzav() {
      this.attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         int var5;
         switch(var1) {
         case 1:
            var5 = var2.readInt();
            IBinder var8 = var2.readStrongBinder();
            Bundle var7 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var5, var8, var7);
            break;
         case 2:
            var5 = var2.readInt();
            Bundle var6 = (Bundle)zzef.zza(var2, Bundle.CREATOR);
            this.zza(var5, var6);
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
