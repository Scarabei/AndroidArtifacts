package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzee;

public abstract class zzaa extends zzee implements zzz {
   public zzaa() {
      this.attachInterface(this, "com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         switch(var1) {
         case 1:
            this.onIndoorBuildingFocused();
            break;
         case 2:
            com.google.android.gms.maps.model.internal.zzj var5 = com.google.android.gms.maps.model.internal.zzk.zzad(var2.readStrongBinder());
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
