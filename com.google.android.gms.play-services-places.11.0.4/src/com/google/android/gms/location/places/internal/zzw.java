package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;

public abstract class zzw extends zzee implements zzv {
   public zzw() {
      this.attachInterface(this, "com.google.android.gms.location.places.internal.IPlacesCallbacks");
   }

   public boolean onTransact(int var1, Parcel var2, Parcel var3, int var4) throws RemoteException {
      if (this.zza(var1, var2, var3, var4)) {
         return true;
      } else {
         DataHolder var5;
         switch(var1) {
         case 1:
            var5 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzO(var5);
            break;
         case 2:
            var5 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzP(var5);
            break;
         case 3:
            var5 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzQ(var5);
            break;
         case 4:
            Status var6 = (Status)zzef.zza(var2, Status.CREATOR);
            this.zzF(var6);
            break;
         case 5:
            var5 = (DataHolder)zzef.zza(var2, DataHolder.CREATOR);
            this.zzR(var5);
            break;
         default:
            return false;
         }

         return true;
      }
   }
}
