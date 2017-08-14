package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public abstract class zzd extends zzee implements zzc {
   public zzd() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.ICancelableCallback");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onFinish();
            break;
         case 2:
            this.onCancel();
            break;
         default:
            return false;
         }

         var3.writeNoException();
         return true;
      }
   }
}
